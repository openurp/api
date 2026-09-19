#!/usr/bin/env bash
#
# 数据库变更一键脚本：生成全量 DDL → 更新 init/ → 生成增量迁移脚本 → 更新 database.xml → 复核列序。
#
# 用法：
#   scripts/db-migration.sh <旧版本> <新版本> [变更说明] [--old-file <快照>]   完整流程
#   scripts/db-migration.sh --verify                                           只跑列序复核
#   scripts/db-migration.sh -h                                                 帮助
#
# 例：
#   scripts/db-migration.sh 1.4.10 1.6.0 "课程审核结果改用未通过后续途径"
#
# 说明：
#   - 旧快照默认取 git HEAD 里的 database.xml，要求它的 version 等于 <旧版本>；
#     已经提交过新版本时用 --old-file 指定旧快照文件。
#   - 已存在的 migrate 脚本不会被覆盖，新生成的 ddlDiff 结果留在 target 目录里供人工合并。
#   - migrate 脚本的变更说明与数据回填仍需人工补充（脚本只给骨架并分组排好语句顺序）。
#   - 环境变量：SBT_CMD（默认 "sbt -batch"）、DB_OUT_DIR（默认自动推导生成目录）。
#
# 见 docs/db-migration.md
set -euo pipefail

MODULE=all
DBDIR="${MODULE}/src/main/resources/db/postgresql"

die() { printf '错误: %s\n' "$*" >&2; exit 1; }
info() { printf '==> %s\n' "$*"; }
warn() { printf '警告: %s\n' "$*" >&2; }

usage() {
  cat <<'USAGE'
用法：
  scripts/db-migration.sh <旧版本> <新版本> [变更说明] [--old-file <快照>]  完整流程
  scripts/db-migration.sh --verify                                        只跑列序复核
  scripts/db-migration.sh -h                                              帮助

例：
  scripts/db-migration.sh 1.4.10 1.6.0 "课程审核结果改用未通过后续途径"
  scripts/db-migration.sh 1.4.10 1.6.0 --old-file /tmp/db-1.4.10.xml

选项：
  --old-file <文件>  旧快照来源；默认取 git HEAD 里的 database.xml

环境变量：
  SBT_CMD      默认 "sbt -batch"
  DB_OUT_DIR   默认 target/out/jvm/u/openurp-api-all/db/postgresql

见 docs/db-migration.md
USAGE
}

repo_root=$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)
cd "$repo_root"
[ -d "$DBDIR" ] || die "在 $repo_root 下找不到 $DBDIR"

SBT_CMD=${SBT_CMD:-sbt -batch}
DB_OUT_DIR=${DB_OUT_DIR:-target/out/jvm/u/openurp-api-${MODULE}/db/postgresql}

# 列序复核：主键列整体置顶、主键列内与其余列均按名称升序，且 SQL 与 database.xml 列序一致
verify_columns() {
  local init_dir="${1:-$DBDIR/init}" dbxml="${2:-$DBDIR/database.xml}"
  python3 - "$init_dir/1-tables.sql" "$init_dir/2-keys.sql" "$dbxml" <<'PY'
import re, sys, xml.etree.ElementTree as ET

tables_sql, keys_sql, dbxml = sys.argv[1:4]

def split_cols(s):                      # 按逗号切分，忽略 decimal(5,3) 这类括号内的逗号
    out = []; depth = 0; cur = ''
    for ch in s:
        if ch == '(': depth += 1
        elif ch == ')': depth -= 1
        if ch == ',' and depth == 0: out.append(cur); cur = ''
        else: cur += ch
    out.append(cur)
    return [x.strip() for x in out if x.strip()]

tables = {}
for name, raw in re.findall(r'create table ([\w.]+) \((.*?)\);', open(tables_sql, encoding='utf-8').read(), re.S):
    tables[name] = [col.split()[0] for col in split_cols(raw)]

pk = {}
for m in re.finditer(r'alter table ([\w.]+) add constraint \w+ primary key \(([^)]*)\)',
                     open(keys_sql, encoding='utf-8').read()):
    pk[m.group(1)] = [x.strip() for x in m.group(2).split(',')]

bad = []
for name, cols in tables.items():
    k = len(pk[name])
    if cols[:k] != sorted(pk[name]) or cols[k:] != sorted(cols[k:]):
        bad.append((name, pk[name], cols[:k]))

xml = {s.get('name') + '.' + t.get('name'): [c.get('name') for c in t.find('columns')]
       for s in ET.fromstring(open(dbxml, encoding='utf-8').read()).find('schemas') for t in s.find('tables')}
mismatch = [t for t in tables if tables[t] != xml.get(t)]

print('表数=%d 列数=%d 复合主键=%d 列序违规=%d SQL与database.xml不一致=%d'
      % (len(tables), sum(map(len, tables.values())), sum(1 for p in pk.values() if len(p) > 1), len(bad), len(mismatch)))
for name, p, head in bad: print('  列序违规', name, 'pk=', p, 'head=', head)
for t in mismatch: print('  XML列序不同', t)
sys.exit(1 if (bad or mismatch) else 0)
PY
}

# 把 ddlDiff 的语句重排为"新增 → 其他 → 删除 → 注释"，避免直接照抄时先删后加；
# 人工的数据回填插在新增与删除之间
group_statements() {
  local src=$1
  awk '
    /^[[:space:]]*$/ { next }
    /alter table .* add (column|constraint) / { print "1\t" $0; next }
    /^drop |alter table .* drop /             { print "3\t" $0; next }
    /^comment on /                            { print "4\t" $0; next }
    { print "2\t" $0 }
  ' "$src" | sort -s -k1,1 | cut -f2-
}

OLD_VERSION=""
NEW_VERSION=""
DESC=""
OLD_FILE=""
VERIFY_ONLY=0
while [ $# -gt 0 ]; do
  case "$1" in
    -h|--help)  usage; exit 0 ;;
    --verify)   VERIFY_ONLY=1; shift ;;
    --old-file) OLD_FILE=${2:?--old-file 需要路径}; shift 2 ;;
    --*) die "未知参数 $1" ;;
    *)
      if [ -z "$OLD_VERSION" ]; then OLD_VERSION=$1
      elif [ -z "$NEW_VERSION" ]; then NEW_VERSION=$1
      elif [ -z "$DESC" ]; then DESC=$1
      else die "多余参数 $1"; fi
      shift ;;
  esac
done

if [ "$VERIFY_ONLY" = 1 ]; then
  info "复核列序"
  if verify_columns; then info "列序正常"; else warn "列序复核未通过"; exit 1; fi
  exit 0
fi

[ -n "$OLD_VERSION" ] || { usage; exit 2; }
[ -n "$NEW_VERSION" ] || die "缺少新版本号"
[ "$OLD_VERSION" != "$NEW_VERSION" ] || die "新旧版本号不能相同"
[ -n "$DESC" ] || { DESC="<待补充变更说明>"; warn "未提供变更说明，migrate 脚本里会写入 $DESC，记得手工改掉"; }

info "仓库: $repo_root；版本: $OLD_VERSION -> $NEW_VERSION"

# 1. 生成全量 DDL
info "生成全量 DDL: $SBT_CMD \"$MODULE/Compile/ormDdl\""
$SBT_CMD "$MODULE/Compile/ormDdl"
[ -f "$DB_OUT_DIR/database.xml" ] || die "没有生成 $DB_OUT_DIR/database.xml，检查上面的 ormDdl 输出"
if [ -f "$DB_OUT_DIR/warnings.txt" ]; then warn "生成告警见 $DB_OUT_DIR/warnings.txt"; fi

# 2. 准备新旧快照（先做 diff，确认可用后再动 init/ 与 database.xml）
info "准备快照 db-$NEW_VERSION.xml / db-$OLD_VERSION.xml"
cp "$DB_OUT_DIR/database.xml" "$DBDIR/db-$NEW_VERSION.xml"
sed -i "s/version=\"UNDEFINED\"/version=\"$NEW_VERSION\"/" "$DBDIR/db-$NEW_VERSION.xml"
trap 'rm -f "$DBDIR/db-$NEW_VERSION.xml" "$DBDIR/db-$OLD_VERSION.xml"' EXIT

# 只认 <db ... version="x"> 里的版本，避免匹配到 <?xml version="1.0" ?>
db_version() { sed -n 's/.*<db .*version="\([^"]*\)".*/\1/p' "$1" | head -1; }
[ "$(db_version "$DBDIR/db-$NEW_VERSION.xml")" = "$NEW_VERSION" ] || die "新快照版本号写入失败"
if [ -n "$OLD_FILE" ]; then
  cp "$OLD_FILE" "$DBDIR/db-$OLD_VERSION.xml"
else
  git show "HEAD:$DBDIR/database.xml" > "$DBDIR/db-$OLD_VERSION.xml" || die "取不到 HEAD:$DBDIR/database.xml，用 --old-file 指定旧快照"
fi
old_in_file=$(db_version "$DBDIR/db-$OLD_VERSION.xml")
[ "$old_in_file" = "$OLD_VERSION" ] || die "旧快照里的版本是 $old_in_file，不是 $OLD_VERSION；用 --old-file 指定或先提交当前版本"

# 3. 生成增量结构 SQL（失败就不要覆盖 init/ 与 database.xml）
info "生成增量结构 SQL: $SBT_CMD \"$MODULE/Compile/ddlDiff $OLD_VERSION $NEW_VERSION\""
$SBT_CMD "$MODULE/Compile/ddlDiff $OLD_VERSION $NEW_VERSION"
DIFF_SQL="$DB_OUT_DIR/migrate/$OLD_VERSION-$NEW_VERSION.sql"
[ -s "$DIFF_SQL" ] || die "没有生成 $DIFF_SQL，检查两个快照是否放对"
info "结构变更 $(grep -c ';' "$DIFF_SQL") 条："
sed 's/^/    /' "$DIFF_SQL"

# 4. 覆盖全量脚本
info "覆盖 $DBDIR/init/*.sql"
cp "$DB_OUT_DIR"/*.sql "$DBDIR/init/"

# 5. 生成 migrate 骨架（已存在则不覆盖）
YEAR=$(date +%Y)
MIGRATE_FILE="$DBDIR/migrate/$YEAR/$(date +%F) $NEW_VERSION.sql"
mkdir -p "$DBDIR/migrate/$YEAR"
if [ -e "$MIGRATE_FILE" ]; then
  warn "$MIGRATE_FILE 已存在，保留不动；新的 ddlDiff 结果在 $DIFF_SQL，请人工合并"
else
  info "生成 $MIGRATE_FILE"
  statements=$(group_statements "$DIFF_SQL") || die "整理 $DIFF_SQL 里的语句失败"
  adds=$(printf '%s\n' "$statements" | grep -E '^alter table .* add (column|constraint) ' || :)
  rest=$(printf '%s\n' "$statements" | grep -Ev '^alter table .* add (column|constraint) ' || :)
  {
    echo "insert into base.versions(id,version,updated_at,description)"
    echo "values(next_id('base.versions'),'$NEW_VERSION',now(),'$DESC');"
    echo
    printf '%s\n' "$adds"
    echo
    echo "--TODO 数据迁移：把旧列数据回填到新列（例 update t set new=old where ...），必须写在下面的删列之前"
    echo
    printf '%s\n' "$rest"
  } > "$MIGRATE_FILE"
fi

# 6. 新快照成为当前快照
info "更新 $DBDIR/database.xml"
cp "$DBDIR/db-$NEW_VERSION.xml" "$DBDIR/database.xml"

# 7. 复核列序
info "复核列序"
if verify_columns; then
  info "列序正常"
else
  warn "列序复核未通过，见上面的明细"
fi

cat <<EOF

完成。下一步：
  1. 检查 $MIGRATE_FILE：补变更说明与数据回填语句，确认语句顺序（新增/回填/删除）
  2. 核对 git diff：应当只有 database.xml、init/*.sql 和新增的 migrate 脚本
  3. 提交
EOF
