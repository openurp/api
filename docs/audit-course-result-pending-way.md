# 课程审核结果：未通过后续途径（pendingWay）

适用版本：openurp-edu-api 1.6.0 起（数据库版本 1.6.0）

## 1. 背景：为什么要改

计划审核的结果行 `AuditCourseResult` 表示"某学生某门计划课程"的完成情况。改造前用两个布尔字段表达"这门课没通过，但还有希望"：

| 字段 | 含义 | 取值来源 |
| --- | --- | --- |
| `taking` | 是否正在修读（有在读记录、还没出成绩） | 有在读的上课记录；有尚未出成绩的补缓考记录 |
| `predicted` | 是否预计能通过 | 毕业学年课程；毕业生的在读课程 |

三个问题：

1. **补缓考无处安放**：有补/缓考应考记录、还没出补缓考成绩时，只能写 `taking=true`，与"正在修读"混为一谈；如果学生既有成绩（例如正考成绩）又有补缓考记录，`taking` 更说不通——而这恰恰是最需要被看见的一种情况。
2. **`taking` 与 `predicted` 语义重叠**：对毕业生来说"在读"同时就是"预计能通过"，两个布尔存在无意义的组合，谁优先只能靠使用方自己约定。历史上 `owed_credits2`/`owed_credits3` 的改名就是这种重叠的产物。
3. **只记状态、不记原因**：页面与统计只能知道"在读"，无法区分是在读、补缓考还是毕业学年课程，也不能按原因筛选；想看原因只能去解析备注文本。

因此，本次改造把"没通过时还在等什么"从布尔状态提升为一等公民：用一个可枚举的"未通过后续途径"来表达。

## 2. 模型设计

新增枚举（与既有的 `CoursePassedWay` 对称）：

```scala
// edu/src/main/scala/org/openurp/edu/grade/model/CoursePendingWay.scala
enum CoursePendingWay(val id: Int, val name: String) {
  case Taking extends CoursePendingWay(1, "在读")               // 有在读的修读记录，尚未出成绩
  case Makeup extends CoursePendingWay(2, "补缓考")             // 有补缓考记录，尚未出成绩
  case GraduateYear extends CoursePendingWay(3, "毕业学年课程")  // 毕业学年课程（毕业论文等），尚未出成绩也预计能完成
}
```

`AuditCourseResult` 相应调整：

```scala
/** 未通过时尚未出成绩的后续途径 */
var pendingWay: Option[CoursePendingWay] = None

/** 是否预计能通过：存在未出成绩的后续途径即视为预计能通过 */
def predicted: Boolean = pendingWay.nonEmpty
```

设计要点：

- **`pendingWay` 取代 `taking`**：`taking` 字段删除，也不保留只读方法，避免新旧两套语义并存。
- **`predicted` 退化为只读派生属性**：数据库列 `predicted` 删除。"预计能通过"不再是独立事实，而是"存在后续途径"的推论，二者不可能再互相矛盾。
- **`None` 就是没有后续途径**：既无成绩、也没在读/补缓考/毕业学年标记的课，表示"未通过且没有已知的后续安排"（`predicted = false`）。
- **与 `passedWay` 分工**：`passedWay`（`CoursePassedWay`）回答"通过的原因"，`pendingWay` 回答"没通过时还在等什么"。`passed = true` 时 `pendingWay` 应为空。
- **为什么用枚举而不是布尔**：值本身带 `id` 和 `name`——`id` 落库、可查询筛选，`name` 直接用于展示；将来新增情形（如毕业设计延期）只需加一个枚举值，不必再加布尔字段、也不必再让使用方约定优先级。备注仍记录事实细节（哪学期、哪个课程序号），但不承担表达"原因"的职责。

## 3. 语义约定

- **不变量**：`predicted == pendingWay.nonEmpty`。任何"预计"类的判断都应基于这个派生值，而不是再单独维护一个标志。
- **预计缺口口径**：1.4.3 迁移删除了 `audit_group_results.owed_credits2` 并把 `owed_credits3` 改名成 `owed_credits2`，其含义是"考虑预计通过之后仍缺的学分"。本次改造把它固定为"只减掉有后续途径的课"，于是组/计划级 `owedCredits2` 不再需要第三种欠分口径；相应地 `DefaultPlanAuditor.cleanupElectiveCourses` 用 `!x.predicted && !x.hasGrade` 清理空行。
- **非毕业生的在读课程**：`pendingWay = Taking` ⇒ `predicted = true` 是正常且期望的语义（课程在读，预计能通过），不是数据异常。

## 4. 值的产生规则

| 触发情形 | 写入 |
| --- | --- |
| 上课名单中有修读记录，且该学期没有已发布成绩 | `Taking` |
| 有补/缓考应考记录，且没有对应的补缓考成绩（半年内） | `Makeup` |
| 毕业学年 + 课程名命中毕业课程名单（毕业论文、军训等） | `GraduateYear` |
| 合并历史审核结果 | 原样同步 `pendingWay` |

两条前提：

- **在读/补缓考优先于毕业学年课程**：只有在 `pendingWay` 为空（既没在读记录也没补缓考记录）时，才标记毕业学年课程。
- **写入顺序保证优先级**：默认审核规则中，标记毕业学年课程的环节排在处理上课名单与补缓考记录的环节之后；规则顺序可由项目配置覆盖，覆盖时需保持这个相对次序。

## 5. 数据库变更

DDL 见 `all/src/main/resources/db/postgresql/`（`database.xml` 版本升到 1.6.0），数据迁移见 `migrate/2026/2026-09-19 1.6.0.sql`：

```sql
alter table edu.audit_course_results add column pending_way integer;
--原 taking 同时表示在读和补缓考, 用备注区分出补缓考
update edu.audit_course_results set pending_way=1 where taking=true and remark not like '未出补缓考成绩%';
update edu.audit_course_results set pending_way=2 where taking=true and remark like '未出补缓考成绩%';
--原 predicted 为预计能通过(在读或毕业学年课程), taking=false 的即为毕业学年课程
update edu.audit_course_results set pending_way=3 where taking=false and predicted=true;
alter table edu.audit_course_results drop taking cascade;
alter table edu.audit_course_results drop predicted cascade;
comment on column edu.audit_course_results.pending_way is '未通过后续途径';
```

- 旧 `taking` 同时覆盖"在读"和"补缓考"，迁移按备注前缀区分，备注不可靠时统一记作"在读"。
- 旧 `predicted` 为真且 `taking` 为假的，是毕业学年课程；两个都为真的（毕业生的在读课程）归入"在读"。
- 迁移只做旧值映射，不重算审核结果——`pendingWay` 的权威来源始终是下一次计划审核。
- 语句顺序必须"先加列、再回填、最后删旧列"：`ddlDiff` 生成的只有加列与删列，回填要人工插在中间。

## 6. 待办

- `AuditGroupResult.takingCourses` 与 `AuditCourseLevel.Taking` 已无写入点，可考虑删除（需确认没有项目自定义审核环节在使用）。
- 组级 `AuditGroupResult.predicted`（数据库列仍在）是否也收敛为"组内存在 `pendingWay`"的派生属性。

## 7. 相关代码

| 位置 | 内容 |
| --- | --- |
| `edu/src/main/scala/org/openurp/edu/grade/model/CoursePendingWay.scala` | 枚举定义 |
| `edu/src/main/scala/org/openurp/edu/grade/model/AuditCourseResult.scala` | `pendingWay` 与派生的 `predicted` |
| `edu/src/test/scala/org/openurp/edu/grade/model/CoursePendingWayTest.scala` | 枚举 id 与名称约定的测试 |
| `all/src/main/resources/db/postgresql/migrate/2026/2026-09-19 1.6.0.sql` | 数据迁移 |
