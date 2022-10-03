insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.27.0',now(),'支持学位课程');

drop table edu.execution_plan_courses_labels;
drop table edu.major_plan_courses_labels;
drop table edu.std_plan_courses_labels;
drop table base.c_plan_course_labels;

create table edu.programs_degree_courses (program_id bigint not null, course_id bigint not null);
alter table edu.programs_degree_courses add constraint pk_c56n0ogp63uo4vcl3pp16l842 primary key (program_id,course_id);

alter table edu.course_takers add course_type_id int4;
update edu.course_takers ct set course_type_id=(select cg.course_type_id from edu.course_grades cg where cg.clazz_id=ct.clazz_id and cg.std_id=ct.std_id)
where ct.course_type_id is null;

update edu.course_takers ct set course_type_id=(select clz.course_type_id from edu.clazzes clz where clz.id=ct.clazz_id)
where ct.course_type_id is null;

alter table edu.course_takers alter column course_type_id set not null;
alter table base.projects_properties alter column value_ type varchar(2000);
update base.student_states ss set end_on=(select std.end_on from base.students std where std.id=ss.std_id) where end_on is null;
alter table base.student_states alter column end_on set not null;
