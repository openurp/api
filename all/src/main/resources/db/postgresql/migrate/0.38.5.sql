insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.38.5',now(),'简化课程教学大纲和授课计划');

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

--drop table edu.syllabus_topics_methods
--drop table edu.syllabuses_methods
--drop table edu.lessons_methods

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
