insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.25.0',now(),'统一审核状态');

alter table std.graduate_sessions rename begin_on to graduate_on;
alter table std.graduate_sessions drop column end_on;
alter table std.graduate_sessions add column gaduate_grade_id integer;
alter table edu.clazzes add column  status int4;
update edu.clazzes set status =
case when audit_state=2 then 100  when audit_state=3 then 99  else audit_state end;
alter table edu.clazzes drop column audit_state;

---plans--------------
alter table edu.execution_plans add column  status int4;
update edu.execution_plans set status =
case when audit_state=2 then 100  when audit_state=3 then 99  else audit_state end;
alter table edu.execution_plans drop column audit_state;

alter table edu.major_plans add column  status int4;
update edu.major_plans set status =
case when audit_state=2 then 100  when audit_state=3 then 99  else audit_state end;
alter table edu.major_plans drop column audit_state;

alter table edu.std_plans add column  status int4;
update edu.std_plans set status =
case when audit_state=2 then 100  when audit_state=3 then 99  else audit_state end;
alter table edu.std_plans drop column audit_state;

alter table edu.programs add column  status int4;
update edu.programs set status =
case when audit_state=2 then 100  when audit_state=3 then 99  else audit_state end;
alter table edu.programs drop column audit_state;

--exchange-----------
alter table std.exchange_grades add column  status int4;
update std.exchange_grades set status =
case when audit_state=2 then 50  when audit_state=3 then 51  when audit_state=4 then 100  when audit_state=5 then 99 else audit_state end;
--alter table std.exchange_grades drop column audit_state;

alter table std.exemption_applies add column  status int4;
update std.exemption_applies set status =
case when audit_state=2 then 50  when audit_state=3 then 51  when audit_state=4 then 100  when audit_state=5 then 99 else audit_state end;
alter table std.exemption_applies drop column audit_state;

alter table std.transfer_applies add column  status int4;
update std.transfer_applies set status =
case when audit_state=2 then 50  when audit_state=3 then 51  when audit_state=4 then 100  when audit_state=5 then 99 else audit_state end;
alter table std.transfer_applies drop column audit_state;

--syllabus---
update edu.syllabuses set status =
case when status=2 then 99  when status=3 then 100  when status=4 then 200 else status end;
-----squad----------
alter table base.squads add master_id bigint;
--drop constraint on tutor_id and instructor_id
alter table base.squads drop constraint fk_keowvc6huvp681vrxrii0wau3;
alter table base.squads drop constraint fk_5yld2xcem9qgfuq2tbb7t8dfw;
alter table base.squads add mentor_id bigint;
update base.squads s set  tutor_id=(select t.user_id from base.teachers t where t.id=s.tutor_id);
update base.squads s set  mentor_id=(select t.user_id from base.instructors t where t.id=s.instructor_id);
--alter table  base.squads drop column instructor_id;
alter table base.instructors rename to mentors;
----teaching-office--
alter table base.teaching_groups rename to teaching_offices;
alter table base.teaching_groups_members rename to teaching_offices_members;
alter table base.teaching_offices_members rename teaching_group_id to teaching_office_id;
alter table edu.syllabuses rename column teaching_group_id to teaching_office_id;
alter table base.courses rename column teaching_group_id to teaching_office_id;
--------calendar----------
alter table base.projects add column calendar_id integer;
update base.projects p set calendar_id=(select max(pc.calendar_id) from base.projects_calendars pc where pc.project_id=p.id);
drop table base.projects_calendars;
----graduate-grades------------
create table base.graduate_grades (id integer not null, graduate_year integer not null, name varchar(255) not null);
comment on table base.graduate_grades is '毕业界别@edu';
comment on column base.graduate_grades.id is '非业务主键:auto_increment';
comment on column base.graduate_grades.graduate_year is '毕业年份';
comment on column base.graduate_grades.name is '名称';
alter table base.graduate_grades add constraint pk_2m9oojtihkt53wes2jddk6umt primary key (id);
--innovation-----
alter table prac.experts rename to inno_experts;
alter table prac.introes rename to inno_introes;
alter table prac.init_review_details rename to inno_init_review_details;
alter table prac.closure_review_groups_experts rename to inno_closure_review_groups_experts;
alter table prac.closures rename to inno_closures;
alter table prac.closure_reviews rename to inno_closure_reviews;
alter table prac.project_categories rename to inno_project_categories;
alter table prac.init_review_groups rename to inno_init_review_groups;
alter table prac.init_reviews rename to inno_init_reviews;
alter table prac.stages rename to inno_stages;
alter table prac.batches rename to inno_batches;
alter table prac.innov_projects rename to inno_projects;
alter table prac.project_states rename to inno_project_states;
alter table prac.materials rename to inno_materials;
alter table prac.closure_review_groups rename to inno_closure_review_groups;
alter table prac.stage_types rename to inno_stage_types;
alter table prac.closure_review_details rename to inno_closure_review_details;
alter table prac.project_levels rename to inno_project_levels;
alter table prac.init_review_groups_experts rename to inno_init_review_groups_experts;
alter table prac.members rename to inno_members;
alter table prac.level_jounals rename to inno_level_jounals;
alter table prac.innov_projects_instructors rename to inno_innov_projects_instructors;

---rd schema rename---------
alter schema rd rename to trd;
----project code-----------
alter table base.project_codes add code_ids varchar(500);
alter table base.project_codes add class_name varchar(100);
--alter table base.project_codes drop column code_id;
--alter table base.project_codes drop column meta_id;

