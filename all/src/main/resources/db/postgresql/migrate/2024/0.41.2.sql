insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.41.2',now(),'删除课程类别的多余属性');

insert into base.course_journals(id,course_id,name,en_name,credit_hours,department_id,
exam_mode_id,week_hours,weeks,begin_on,end_on,updated_at)
select datetime_id(),id,name,en_name,credit_hours,department_id,
exam_mode_id,week_hours,weeks,begin_on,end_on,updated_at from base.courses c
where not exists(select * from base.course_journals cj where cj.course_id=c.id);

alter table edu.regular_test_grades rename to regular_grade_items;
alter table edu.regular_test_types rename to regular_components;
alter table edu.regular_grade_states_percents rename to regular_grade_state_items;
alter table edu.regular_grade_state_items rename column value_ to score_percent;
alter table edu.regular_grade_state_items rename column regular_grade_state_id to state_id;
alter table edu.regular_grade_state_items rename column regular_test_type_id to component_id;

alter table edu.regular_grade_state_items add column id bigint;
update edu.regular_grade_state_items set id= datetime_id();
alter table edu.regular_grade_state_items add constraint pk_5xpxhr1ltbqfxiqgmhpmnmh7a primary key (id);

alter table his.edu_course_grades alter score_text type varchar(10);
alter table his.edu_exam_grades alter score_text type varchar(10);
alter table his.edu_ga_grades alter score_text type varchar(10);

create table log.edu_grade_changes (id bigint not null, course_grade_id bigint default 0 not null, course_id bigint not null, grade_type_id integer not null, operate_at timestamptz not null, operator_id bigint not null, project_id integer not null, remark varchar(100), score_after varchar(10), score_before varchar(10), semester_id integer not null, std_id bigint not null);
alter table log.edu_grade_changes add constraint pk_avwxfilyf3q6wgc4oepc6up77 primary key (id);
alter table log.edu_grade_changes add constraint fk_dhtwpadqf2mrrs4q669g69fdv foreign key (std_id) references base.students (id);
alter table log.edu_grade_changes add constraint fk_bbf68h65kecxthfl3usdru1yr foreign key (project_id) references base.projects (id);
alter table log.edu_grade_changes add constraint fk_3thetdmwvjo7ocgbxj1w43fp5 foreign key (operator_id) references base.users (id);
alter table log.edu_grade_changes add constraint fk_mhydxpk49ayt92b9qj04i0dlm foreign key (grade_type_id) references code.grade_types (id);
alter table log.edu_grade_changes add constraint fk_mq4k6fykdvubeknsumes38xaj foreign key (course_id) references base.courses (id);
alter table log.edu_grade_changes add constraint fk_ay1t1sv01m6kjjxxs68l0lsu1 foreign key (semester_id) references base.semesters (id);

comment on table log.edu_grade_changes is '成绩变化历史@grade.log';
comment on column log.edu_grade_changes.id is '非业务主键:datetime';
comment on column log.edu_grade_changes.course_grade_id is '课程成绩ID';
comment on column log.edu_grade_changes.course_id is '课程基本信息ID';
comment on column log.edu_grade_changes.grade_type_id is '成绩类型ID';
comment on column log.edu_grade_changes.operate_at is '变更时间';
comment on column log.edu_grade_changes.operator_id is '变更人ID';
comment on column log.edu_grade_changes.project_id is '项目ID';
comment on column log.edu_grade_changes.remark is '备注';
comment on column log.edu_grade_changes.score_after is '变更后分数';
comment on column log.edu_grade_changes.score_before is '变更前分数';
comment on column log.edu_grade_changes.semester_id is '学年学期ID';
comment on column log.edu_grade_changes.std_id is '学生ID';
