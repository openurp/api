insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.44.0',now(),'增加实验教学、删除个人计划');

create schema lab;
alter table base.experiments set schema exp;
alter table lab.experiments add level_id int4;
drop table base.experiments_levels cascade;
create table lab.experiment_activities (id bigint not null, begin_unit smallint default 0 not null, start_on date not null, end_at smallint not null, begin_at smallint not null, weekstate bigint not null, end_unit smallint default 0 not null, experiment_id bigint not null);
create table lab.experiment_activities_labs (experiment_activity_id bigint not null, laboratory_id bigint not null);
alter table lab.experiment_activities add constraint pk_faduj0gr0b26fal96vmmpw5as primary key (id);
alter table lab.experiment_activities add constraint fk_qudxn3hwrria27ahil4qtvhwi foreign key (experiment_id) references lab.experiments (id);
alter table lab.experiment_activities_labs add constraint pk_6d6sxl429xb47llm9k41unvgo primary key (experiment_activity_id,laboratory_id);

create table edu.std_program_bindings (id bigint not null, program_id bigint not null, std_id bigint not null, updated_at timestamptz default current_timestamp not null);
drop table edu.std_course_groups cascade;
drop table edu.std_plan_courses cascade;
drop table edu.std_plans cascade;

alter table base.grades rename column begin_on to begin_in;
alter table base.grades rename column end_on to end_in;
alter table base.graduate_seasons rename column graduate_on to graduate_in;
alter table base.textbooks rename column published_on to published_in;
alter table degree.thesis_checks rename column enroll_on to enroll_in;
alter table degree.thesis_checks rename column graduate_on to graduate_in;

alter table edu.certificate_grades rename column acquired_on to acquired_in;
alter table edu.extern_grades rename column acquired_on to acquired_in;
alter table edu.syllabus_experiments drop experiment_id cascade;
alter table flow.edu_cert_exempt_applies rename column acquired_on to acquired_in;
alter table prac.ability_credit_applies rename column acquired_on to acquired_in;

alter table std.edu_work_records rename column begin_on to begin_in;
alter table std.edu_work_records rename column end_on to end_in;
alter table trd.rd_achievements rename column begin_on to begin_in;
alter table trd.rd_achievements rename column end_on to end_in;
alter table trd.rd_projects rename column finished_on to finished_in;
alter table trd.rd_projects rename column begin_on to begin_in;
alter table trd.rd_projects rename column end_on to end_in;
alter table trd.teaching_teams rename column begin_on to begin_in;
alter table trd.textbook_achievements rename column published_on to published_in;

alter table edu.std_program_bindings add constraint pk_9n0qo30hsm8dlofhfqppv2o94 primary key (id);
alter table edu.std_program_bindings add constraint fk_20a1u2fkkwntttaj61a2d3gt2 foreign key (std_id) references base.students (id);
alter table edu.std_program_bindings add constraint fk_n1e76uwd9xe1cw1hb93lx3ibc foreign key (program_id) references edu.programs (id);
alter table edu.syllabus_experiments drop constraint if exists fk_8d9d58buigjgts7ix2fjdmaqe cascade;
alter table edu.std_program_bindings add constraint uk_c966tfqv5ixyy0sxufc8w6osc unique (std_id);
alter table edu.certificate_grades drop constraint if exists uk_2hfsohm94fvg6oujbs1o0wq3n cascade;
alter table edu.certificate_grades add constraint uk_f23otx93d7vvrx9t7jxd7bajc unique (std_id,certificate_id,acquired_in);
comment on column base.grades.begin_in is '起始年月';
comment on column base.grades.end_in is '结束年月';
comment on column base.graduate_seasons.graduate_in is '毕业年月';
comment on column base.textbooks.published_in is '出版年月';
comment on table edu.std_program_bindings is '特殊学生方案绑定@program';
comment on column edu.std_program_bindings.id is '非业务主键:datetime';
comment on column edu.std_program_bindings.program_id is '专业培养方案ID';
comment on column edu.std_program_bindings.std_id is '学生ID';
comment on column edu.std_program_bindings.updated_at is '更新时间';
comment on column edu.certificate_grades.acquired_in is '获得年月';
comment on column std.edu_work_records.end_in is '结束年月';
