# 数据库变更流程：DDL 生成与迁移脚本

适用对象：openurp/api 的实体/映射改动。改完模型后，需要同步"全量建表脚本"和"增量迁移脚本"，
两者都放在 `all/src/main/resources/db/postgresql/` 下。

## 1. 涉及的目录与文件

| 路径 | 作用 | 维护方式 |
| --- | --- | --- |
| `db/postgresql/init/` | 全量建表脚本（0-schemas、1-tables、2-keys、3-indices、4-constraints、6-comments、7-auxiliaries，有序列时才有 5-sequences） | 由 DDL 生成结果覆盖，`seed.sql` 另由人工维护 |
| `db/postgresql/database.xml` | 当前数据库结构快照（PostgreSQL） | 由 DDL 生成结果覆盖，版本号人工指定 |
| `db/postgresql/migrate/<年份>/<日期> <版本>.sql` | 增量迁移脚本，按版本累积执行 | 由 `ddlDiff` 结果 + 人工补充（版本登记、数据迁移） |
| `db/postgresql/db-<版本>.xml` | 生成增量脚本时用的新旧快照，仅在流程中存在 | 用完即删，不提交 |
| `db/postgresql/report.xml` | 结构报告（`ddlReport`）配置 | 人工维护 |

任务由 beangle 的 sbt 插件提供（自动启用）：`ormDdl` 生成全量 DDL，`ddlDiff` 生成增量 SQL。

上面的流程已经脚本化，日常改动直接跑脚本即可（等价于 §3 的第 1–6 步 + §3.7 复核）：

```bash
scripts/db-migration.sh 1.4.10 1.6.0 "课程审核结果改用未通过后续途径"
```

- 旧快照默认取 `git HEAD` 里的 `database.xml`，要求它的 `version` 等于第一个参数；
  HEAD 已经是新版本时用 `--old-file <文件>` 指定旧快照。
- 已存在的 migrate 脚本不会被覆盖，新的 `ddlDiff` 结果留在 `target/.../migrate/` 下供人工合并。
- migrate 脚本生成的是骨架：版本登记 + 按"新增 → 删除 → 注释"重排好的结构语句 + 数据回填 TODO，
  变更说明与回填语句仍需人工补。
- 任何一步失败都不会动 `init/` 与 `database.xml`；临时快照用完即删。
- `scripts/db-migration.sh --verify` 只跑 §3.7 的列序复核（不生成、不改文件），适合提交前自检。

## 2. 前置条件：beanmeta.idx

`ormDdl` 会 fork `org.beangle.data.orm.DdlGenerator`，它通过 `META-INF/beangle/beanmeta.idx`
读取实体的 Bean 元数据。classpath 上缺少该索引时，反射回退无法解析继承来的泛型主键
（`Entity[ID]` / `NumId[ID]` 的 `id`），实体的 `id` 会退化成 `java.lang.Object`，生成直接失败：

```
java.lang.RuntimeException: Cannot find sqltype for java.lang.Object
    at org.beangle.data.orm.Mappings.bindId(...)
```

因此 `all` 模块（聚合终端模块，`publish / skip := true`）启用 `MetaPlugin`，在 Compile 期
生成 `beanmeta.idx` 到 `resource_managed/main`，随产物进入 `Runtime / fullClasspath`；
`build.sbt` 中不要为 `all` 再关掉 `Compile / metaIndex`，否则 `ormDdl` 必然报上述错误。

另一个前置条件是依赖版本：`project/plugin.sbt` 里的 `sbt-openurp-parent` 不低于 0.12.8，
它锁定的 beangle-jdbc 1.1.19 / beangle-data-model 5.12.14 / beangle-data-hibernate 5.12.13
才包含固定列序所需的能力（见 3.1）。版本偏低时生成仍会成功，但列序退回旧行为。

## 3. 操作步骤

以本次改动（1.4.10 → 1.6.0）为例。

### 3.1 生成全量 DDL

```bash
sbt "all/Compile/ormDdl"
```

产物在仓库根目录的 `target/out/jvm/u/openurp-api-all/db/postgresql/`（以 sbt 输出里的
`DDl generated in ...` 为准）：各 `*.sql` 加上 `database.xml`；生成过程中的告警写在同级的
`db/warnings.txt`——**只在有告警时才生成该文件**，没有告警时生成器会删掉它（1.6.0 这次
生成没有告警，所以目录里看不到 `warnings.txt`）。

三点注意：

- `database.xml` 的 `version` 属性固定是 `UNDEFINED`：`DdlGenerator` 从系统属性
  `database.version` 取版本，而 sbt 任务不传该参数，需要人工改成新版本号。
- 列顺序由调用方显式固定：`Relation.sortColumns`（beangle-jdbc 新增，随 1.1.19 发布）排序关系自身的
  内部列，`Table` 覆写为"主键列置顶、其余列按名称升序，主键列之间也按名称升序"，视图按名称升序。
  `DdlGenerator` 在 `autobind` 之后对全部表、视图各调用一次，之后的 `createTable(table)`、
  `Serializer.toXml` 等既有调用照原样使用 `table.columns`——签名不变、也不用传列序参数。因此
  `init/*.sql` 与 `database.xml` 的列序一致、重复生成结果相同；`createTable`/`Serializer` 本身
  不隐式排序，`ddlDiff` 按列名比较结构，列序变化不产生多余语句。
- 1.6.0 之前 `database.xml` 里只把字面 `id` 置顶（`Serializer` 里的 `sortWith`），而 SQL 侧完全
  没排序；现在两侧统一为整个主键置顶。因此复合主键表（如 `his.*` 的 `(id, school_year)`）会有
  一次性的列序调整：`school_year` 从中间提到 `id` 后面。这次归位已经完成，1.6.0 的
  `database.xml` 与 `init/*.sql` 同时落在新列序上，后续生成只会重复出同样的顺序。

### 3.2 覆盖全量脚本

```bash
cp target/out/jvm/u/openurp-api-all/db/postgresql/*.sql \
   all/src/main/resources/db/postgresql/init/
```

> 首次按新列序生成时，`1-tables.sql` 的 diff 会很大，这属于一次性归位：历史上该文件与
> `database.xml` 的列序并不一致（602 张表里 559 张不同）。核对时不要逐行看，比较每张表的
> 列集合即可，正常应只有本次改动涉及的表有增删列。

### 3.3 准备新旧快照

```bash
P=all/src/main/resources/db/postgresql
G=target/out/jvm/u/openurp-api-all/db/postgresql

# 新版本：生成结果 + 版本号
cp $G/database.xml $P/db-1.6.0.xml
sed -i 's/version="UNDEFINED"/version="1.6.0"/' $P/db-1.6.0.xml

# 旧版本：本次改动前的快照（工作区已改过时用 git 取上一版）
git show HEAD:$P/database.xml > $P/db-1.4.10.xml
```

### 3.4 生成增量 SQL

```bash
sbt "all/Compile/ddlDiff 1.4.10 1.6.0"
```

`ddlDiff` 读取 `${模块}/src/main/resources/db/postgresql/db-<旧>.xml` 与 `db-<新>.xml`，
输出到 `target/out/jvm/u/openurp-api-all/db/postgresql/migrate/1.4.10-1.6.0.sql`。
任一快照不存在时只打印 `Cannot find ...` 并跳过。

生成结果只含**结构**变更（drop/add column、comment 等），例如：

```sql
alter table edu.audit_course_results drop predicted cascade;
alter table edu.audit_course_results drop taking cascade;
alter table edu.audit_course_results add column pending_way integer;
comment on column edu.audit_course_results.pending_way is '未通过后续途径';
```

### 3.5 整理并放入 migrate 目录

把生成结果放入 `migrate/<年份>/<日期> <版本>.sql`（本次为 `migrate/2026/2026-09-19 1.6.0.sql`），
并人工补充两类内容：

1. 版本登记（脚本首行）：

   ```sql
   insert into base.versions(id,version,updated_at,description)
   values(next_id('base.versions'),'1.6.0',now(),'课程审核结果改用未通过后续途径');
   ```

2. 数据迁移。`ddlDiff` 只做结构对比，会直接 `drop` 旧列，必须自己把旧数据搬到新列上，
   并调整语句顺序（先新增、再回填、最后删除）：

   ```sql
   alter table edu.audit_course_results add column pending_way integer;
   update edu.audit_course_results set pending_way=1 where taking=true and remark not like '未出补缓考成绩%';
   update edu.audit_course_results set pending_way=2 where taking=true and remark like '未出补缓考成绩%';
   update edu.audit_course_results set pending_way=3 where taking=false and predicted=true;
   alter table edu.audit_course_results drop taking cascade;
   alter table edu.audit_course_results drop predicted cascade;
   comment on column edu.audit_course_results.pending_way is '未通过后续途径';
   ```

### 3.6 收尾

```bash
cd all/src/main/resources/db/postgresql
cp db-1.6.0.xml database.xml   # 新快照成为当前快照
rm db-1.6.0.xml db-1.4.10.xml  # 临时快照不入库
```

最后检查 `git status` / `git diff`：正常应只包含 `database.xml`、`init/*.sql` 和新增的
`migrate/.../<日期> <版本>.sql`。

### 3.7 复核列序（可选）

核对三件事：主键列是否整体置顶、主键列内部是否按名称升序、其余列是否按名称升序；顺带比对
`database.xml` 与 SQL 的列序是否一致。

```bash
cd all/src/main/resources/db/postgresql/init
python3 - <<'PY'
import re, xml.etree.ElementTree as ET
def split_cols(s):                      # 按逗号切分，忽略 decimal(5,3) 这类括号内的逗号
    out=[];depth=0;cur=''
    for ch in s:
        if ch=='(': depth+=1
        elif ch==')': depth-=1
        if ch==',' and depth==0: out.append(cur); cur=''
        else: cur+=ch
    out.append(cur)
    return [x.strip() for x in out if x.strip()]
tables={}
for name,raw in re.findall(r'create table ([\w.]+) \((.*?)\);', open('1-tables.sql',encoding='utf-8').read(), re.S):
    tables[name]=[col.split()[0] for col in split_cols(raw)]
pk={}
for m in re.finditer(r'alter table ([\w.]+) add constraint \w+ primary key \(([^)]*)\)', open('2-keys.sql',encoding='utf-8').read()):
    pk[m.group(1)]=[x.strip() for x in m.group(2).split(',')]
bad=[]
for name,cols in tables.items():
    k=len(pk[name])
    if cols[:k]!=sorted(pk[name]) or cols[k:]!=sorted(cols[k:]): bad.append((name,pk[name],cols[:k]))
xml={s.get('name')+'.'+t.get('name'):[c.get('name') for c in t.find('columns')]
     for s in ET.fromstring(open('../database.xml',encoding='utf-8').read()).find('schemas') for t in s.find('tables')}
mismatch=[t for t in tables if tables[t]!=xml.get(t)]
print('表数=%d 列数=%d 复合主键=%d 列序违规=%d SQL与database.xml不一致=%d'
      %(len(tables),sum(map(len,tables.values())),sum(1 for p in pk.values() if len(p)>1),len(bad),len(mismatch)))
for name,p,head in bad: print('  列序违规',name,'pk=',p,'head=',head)
for t in mismatch: print('  XML列序不同',t)
PY
```

1.6.0 的正常输出：

```
表数=602 列数=4818 复合主键=87 列序违规=0 SQL与database.xml不一致=0
```

复合主键表的建表列序与主键约束里的列序可以不同：`2-keys.sql` 里
`base.courses_categories` 是 `primary key (course_id, course_category_id)`，而建表列序是
`course_category_id, course_id`——列序按名称排，主键约束保留原顺序。

## 4. 小结（命令清单）

```bash
sbt "all/Compile/ormDdl"                        # 1. 生成全量 DDL
cp target/out/jvm/u/openurp-api-all/db/postgresql/*.sql \
   all/src/main/resources/db/postgresql/init/   # 2. 覆盖 init
# 3. 生成 db-<旧>.xml / db-<新>.xml（新快照记得改 version）
sbt "all/Compile/ddlDiff 1.4.10 1.6.0"          # 4. 生成增量结构 SQL
# 5. 整理成 migrate/<年>/<日期> <版本>.sql（补版本登记与数据迁移）
cp db-1.6.0.xml database.xml                    # 6. 更新当前快照并删除临时文件
```

以上六步可以直接用 `scripts/db-migration.sh 1.4.10 1.6.0 "变更说明"` 代替。

## 5. 常见问题

| 现象 | 原因与处理 |
| --- | --- |
| `Cannot find sqltype for java.lang.Object` | classpath 上没有 `beanmeta.idx`，检查 `all` 模块的 `MetaPlugin` / `Compile / metaIndex` 配置和构建日志中的 `Generated beanmeta.idx ...` |
| `Cannot find .../db-<版本>.xml` | 旧/新快照未放到 `all/src/main/resources/db/postgresql/` 下，或版本号写错 |
| 生成的 `database.xml` 里 `version="UNDEFINED"` | 任务不传版本，人工改为新版本号 |
| `init/1-tables.sql` 出现与本次改动无关的列序变化 | 生成器已固定列序（主键在前、其余按名称升序）；若仍出现，确认 `sbt-openurp-parent` ≥ 0.12.8（beangle-jdbc ≥ 1.1.19、beangle-data-model ≥ 5.12.14）——排序由 `Relation.sortColumns` 提供、`DdlGenerator` 在 `autobind` 后调用 |
| 生成目录里没有 `db/warnings.txt` | 正常：该文件只在有告警时才写，无告警时被删除 |
| migrate 脚本执行后数据丢失 | `ddlDiff` 只生成结构语句，删除列前必须自行回填数据 |

## 6. 执行记录：1.4.10 → 1.6.0（sbt-openurp-parent 0.12.8）

| 项 | 结果 |
| --- | --- |
| 依赖 | sbt-openurp-parent 0.12.8 → beangle-jdbc 1.1.19、beangle-data-model 5.12.14、beangle-data-hibernate 5.12.13（均为正式发布版，非本地覆盖） |
| `ormDdl` | `target/out/jvm/u/openurp-api-all/db/postgresql/`，无 `warnings.txt`（无告警） |
| 覆盖 `init/` 后与上一版对比 | 602 张表里只有 `edu.audit_course_results` 的**列集合**变了（`taking`/`predicted` → `pending_way`），其余 601 张只有列序变化 |
| `ddlDiff 1.4.10 1.6.0` | 4 条结构语句：`add column pending_way`、`drop taking cascade`、`drop predicted cascade`、`comment on column` |
| `migrate/2026/2026-09-19 1.6.0.sql` | 在 ddlDiff 结果上补版本登记与 3 条数据回填（见 `audit-course-result-pending-way`） |
| 列序复核（3.7） | `表数=602 列数=4818 复合主键=87 列序违规=0 SQL与database.xml不一致=0` |
| 可重复性 | 换用正式发布的 jdbc 1.1.19 / data 5.12.14 重新生成，`init/*.sql` 与 `database.xml` 与上一轮（本地覆盖依赖）逐字节一致 |
