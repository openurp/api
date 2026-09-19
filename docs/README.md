# openurp/api 文档

本目录存放 openurp/api 的设计说明，按主题一个文件，新增文档请在下面登记。

## 目录

| 文档 | 内容 |
| --- | --- |
| [audit-course-result-pending-way](audit-course-result-pending-way.md) | 课程审核结果改用"未通过后续途径"（`CoursePendingWay`：在读/补缓考/毕业学年课程）：1.6.0 起取代 `taking`/`predicted` 两个布尔字段，含改动缘由、模型设计与语义约定、取值规则、数据迁移 |
| [db-migration](db-migration.md) | 数据库变更流程：`ormDdl` 生成全量 DDL、更新 `init/` 与 `database.xml`、`ddlDiff` 生成增量迁移脚本的操作步骤与注意事项；含一键脚本 `scripts/db-migration.sh`、依赖版本要求（`sbt-openurp-parent` ≥ 0.12.8）与 1.6.0 执行记录 |
