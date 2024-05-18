insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.39.0',now(),'简化课程教学大纲和授课计划');

--base
alter table base.squads drop column tutor_id;
alter table base.schools add en_name varchar(200);
alter table base.calendar_stages add en_name varchar(200);
update base.schools set en_name =name;

--edu.program
alter table edu.major_course_groups add required bool default false;
alter table edu.execution_course_groups add required bool default false;
alter table edu.std_course_groups add required bool default false;
alter table edu.execution_course_groups rename to edu.executive_course_groups;
alter table edu.execution_plans rename to edu.executive_plans;
alter table edu.execution_plan_courses rename to edu.executive_plan_courses;

--std
alter table std.examinees alter column code drop not null;

--edu.course
alter table edu.syllabuses rename column exam_hours to exam_credit_hours;
alter table edu.syllabuses add methods varchar(100);
update edu.syllabuses s set methods =
(select string_agg(m.name,'、') from edu.syllabuses_methods ms,code.teaching_methods m where m.id = ms.teaching_method_id and ms.syllabus_id=s.id);

create table edu.syllabus_exam_hours (credit_hours integer default 0 not null, id bigint not null, nature_id integer not null, syllabus_id bigint not null);
alter table edu.syllabus_exam_hours add constraint pk_ect6q20arbiqruymsfh9iabsf primary key (id);
create index idx_sekt0eg3iv22sxqbigc26w5m1 on edu.syllabus_exam_hours (syllabus_id);

alter table edu.syllabus_topics add methods varchar(100);
update edu.syllabus_topics s set methods =
(select string_agg(m.name,'、') from edu.syllabus_topics_methods ms,code.teaching_methods m where m.id = ms.teaching_method_id and ms.syllabus_topic_id=s.id);

alter table edu.lessons add forms varchar(100);
update edu.lessons s set forms =
(select string_agg(m.name,'、') from edu.lessons_methods ms,code.teaching_methods m where m.id = ms.teaching_method_id and ms.lesson_id=s.id);

drop table edu.syllabus_topics_methods;
drop table edu.syllabuses_methods;
drop table edu.lessons_methods;

alter table edu.teaching_plan_hours rename to teaching_plan_sections;
alter table edu.teaching_plan_sections add name varchar(50);
update edu.teaching_plan_sections s set name=(select n.name from code.teaching_sections n where n.id=s.section_id);
alter table edu.teaching_plan_sections drop column section_id;

alter table edu.teaching_plans rename author_id to writer_id;
alter table edu.teaching_plans add dean_id bigint;
alter table edu.teaching_plans add status int4 default 0;
alter table edu.syllabuses add dean_id bigint;
alter table edu.teaching_plans rename audit_at to publish_at;
alter table edu.syllabuses rename audit_at to publish_at;

alter table edu.syllabuses add office_id bigint;
alter table edu.teaching_plans add office_id bigint;
alter table edu.syllabus_outcomes alter column contents type varchar(1000)
alter table edu.syllabus_objectives alter column contents type varchar(1000);
alter table edu.syllabus_texts alter column contents type varchar(4000);
alter table edu.syllabuses alter column description type varchar(4000);
alter table edu.syllabus_topic_elements alter column contents type varchar(1000);
alter table edu.syllabus_method_designs alter column contents type varchar(4000);
alter table edu.syllabuses rename auditor_id to reviewer_id;
alter table edu.teaching_plans rename auditor_id to reviewer_id;
alter table edu.syllabuses rename dean_id to approver_id;
alter table edu.teaching_plans rename dean_id to approver_id;

--misc
update code.teaching_natures set en_name='Theory' where name='理论';
update code.teaching_natures set en_name='Practice' where name='实践';
update code.teaching_natures set en_name='Experiment' where name='实验';

update code.syllabus_topic_labels set en_name ='Course leading value' where name='课程思政';
update code.syllabus_topic_labels set en_name ='Emphasis in this topic' where name='本章重点';
update code.syllabus_topic_labels set en_name ='Difficulties in this topic' where name='本章难点';
update code.syllabus_topic_labels set en_name ='Students’ learning outcomes' where name='学生学习成果';
