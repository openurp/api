insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.41.5',now(),'恢复课程的开课阶段');

alter table edu.executive_course_groups alter term_credits type varchar(60);
alter table edu.major_course_groups alter term_credits type varchar(60);
alter table edu.std_course_groups alter term_credits type varchar(60);

alter table edu.executive_plan_courses add column stage_id integer;
alter table edu.major_plan_courses add column stage_id integer;
alter table edu.syllabus_assessments alter score_table type varchar(10000);
alter table edu.executive_plan_courses add constraint fk_18f6shjjvisy7v08ajv64f86d foreign key (stage_id) references base.calendar_stages (id);
alter table edu.major_plan_courses add constraint fk_cpur8aumdrrxdxdcxosq9i8e9 foreign key (stage_id) references base.calendar_stages (id);
comment on column edu.executive_plan_courses.stage_id is '开课阶段ID';
comment on column edu.major_plan_courses.stage_id is '开课阶段ID';
