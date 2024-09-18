insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.41.4',now(),'开课周数可选');

alter table base.staffs alter id_number type varchar(20);
alter table edu.exam_analyses alter contents type varchar(4000);
alter table edu.lesson_design_sections alter details type varchar(10000);
alter table edu.lesson_design_sections alter summary type varchar(10000);
alter table edu.lesson_design_sections alter title type varchar(400);
alter table edu.syllabus_assessments alter score_table type varchar(10000);
alter table edu.syllabus_experiments alter credit_hours type float4;
comment on table code.education_results is '毕结业结论';

alter table base.course_journals alter weeks drop default;
alter table base.course_journals alter weeks drop not null;
alter table base.courses alter weeks drop default;
alter table base.courses alter weeks drop not null;
