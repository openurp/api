insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.39.0',now(),'简化课程教学大纲和授课计划');

--base
alter table base.squads drop column tutor_id;
alter table base.schools add en_name varchar(200);
alter table base.calendar_stages add en_name varchar(200);
update base.schools set en_name =name;
create table base.user_group_members (id bigint not null, group_id integer not null, updated_at timestamptz default current_timestamp not null, user_id bigint not null);
create table base.user_groups (id integer not null, auto_manage boolean default false not null, code varchar(255) not null, enabled boolean default false not null, indexno varchar(255) not null, manager_id bigint, name varchar(100) not null, parent_id integer, remark varchar(255), school_id integer not null);
alter table base.user_group_members add constraint pk_5d31qxmuo9ojas4mdpbus7se4 primary key (id);
alter table base.user_groups add constraint pk_it5g3etsarg9hkfu7e664rw1b primary key (id);
alter table base.user_group_members add constraint fk_2a4njyry37th1luw0b2ycpaip foreign key (user_id) references base.users (id);
alter table base.user_group_members add constraint fk_tojippg1wvsa2cybvlq4gbi4l foreign key (group_id) references base.user_groups (id);
alter table base.user_groups add constraint fk_ps8lxr5y8ayileomp50g5p84v foreign key (parent_id) references base.user_groups (id);
alter table base.user_groups add constraint fk_p52a4ej904fycrv2r4us8grgr foreign key (manager_id) references base.users (id);
alter table base.user_groups add constraint fk_iy0l5y1wvvx5ha19ag7pg2lvx foreign key (school_id) references base.schools (id);
alter table base.user_group_members add constraint uk_6d0ao0fduegrwrn87d89nvkj7 unique (group_id,user_id);
alter table base.user_groups add constraint idx_group unique (school_id,code);
alter table base.users add column group_id integer;
alter table base.users add constraint fk_2e2079vao3svqhmmjkbafth32 foreign key (group_id) references base.user_groups (id);
--alter table base.teachers drop tutor_type_id cascade;
alter table base.teachers drop user_id cascade;

comment on table base.user_group_members is '用户组成员@common';
comment on column base.user_group_members.id is '非业务主键:datetime';
comment on column base.user_group_members.group_id is '用户组ID';
comment on column base.user_group_members.updated_at is '更新时间';
comment on column base.user_group_members.user_id is '通用人员信息ID';
comment on table base.user_groups is '用户组@common';
comment on column base.user_groups.id is '非业务主键:auto_increment';
comment on column base.user_groups.auto_manage is '自动管理';
comment on column base.user_groups.code is '代码';
comment on column base.user_groups.enabled is '是否启用';
comment on column base.user_groups.indexno is '顺序号';
comment on column base.user_groups.manager_id is '通用人员信息ID';
comment on column base.user_groups.name is '名称';
comment on column base.user_groups.parent_id is '用户组ID';
comment on column base.user_groups.remark is '备注';
comment on column base.user_groups.school_id is '学校信息ID';
comment on column base.calendar_stages.en_name is '英文名';
comment on column base.schools.en_name is '英文名';
comment on column base.users.group_id is '用户组ID';

create table base.tutors (name varchar(150) not null, tutor_type_id integer not null, id bigint not null, remark varchar(255), begin_on date not null, staff_id bigint not null, end_on date);
create table base.tutor_journals (tutor_type_id integer not null, id bigint not null, begin_on date not null, staff_id bigint not null, end_on date);
create table base.tutor_majors (id bigint not null, staff_id bigint not null, major_id bigint not null, edu_type_id integer not null, level_id integer not null);
create table base.tutor_majors_directions (tutor_major_id bigint not null, direction_id bigint not null);

alter table base.tutors add constraint pk_8r47vuh10lfcefyfvo79t8rfv primary key (id);
alter table base.tutors add constraint uk_lonogs2jkkjbfaxouv5vlstd unique (staff_id);
alter table base.tutor_journals add constraint pk_32owy21iwr7ww8f1c3jvg1sbf primary key (id);
alter table base.tutor_majors add constraint pk_ox9qxt7x077f85q0wtepv4abq primary key (id);
alter table base.tutor_majors_directions add constraint pk_6pqyqlrugulyv0it8b4txbyap primary key (tutor_major_id,direction_id);

create index idx_xwp64q9jqda0yiypgwq9mw5n on base.tutor_majors_directions (tutor_major_id);

alter table base.tutor_journals add constraint fk_jg5x47w71obyvh9uhfgh5l70m foreign key (staff_id) references base.staffs (id);
alter table base.tutor_journals add constraint fk_taqn2nd8jw64b2hilpx22wofm foreign key (tutor_type_id) references code.tutor_types (id);
alter table base.tutor_majors add constraint fk_3la34qsb7vinn23ls3v2aw3kd foreign key (level_id) references code.education_levels (id);
alter table base.tutor_majors add constraint fk_o03mqi9gghvymej67sqa3lmn9 foreign key (major_id) references base.majors (id);
alter table base.tutor_majors add constraint fk_qk49ybofykqdejd70ynqnsxp5 foreign key (edu_type_id) references code.education_types (id);
alter table base.tutor_majors add constraint fk_r8chc8ye8heuxuoyx7gunhghg foreign key (staff_id) references base.staffs (id);
alter table base.tutor_majors_directions add constraint fk_ca7s2ggg6iad9yq9hv2k815eq foreign key (tutor_major_id) references base.tutor_majors (id);
alter table base.tutor_majors_directions add constraint fk_kbn9pgxumminghp19n839yorn foreign key (direction_id) references base.directions (id);
alter table base.tutors add constraint fk_jr7962nrthbdgq93d92jd5o8 foreign key (tutor_type_id) references code.tutor_types (id);
alter table base.tutors add constraint fk_l73myokv8i5bsml70eusrd4ps foreign key (staff_id) references base.staffs (id);

--edu.program
alter table edu.major_course_groups add required bool default false;
alter table edu.execution_course_groups add required bool default false;
alter table edu.std_course_groups add required bool default false;
alter table edu.execution_course_groups rename to edu.executive_course_groups;
alter table edu.execution_plans rename to edu.executive_plans;
alter table edu.execution_plan_courses rename to edu.executive_plan_courses;

--std
alter table std.examinees alter column code drop not null;
alter table std.contacts alter mobile type varchar(50);

--edu.exempt
alter table edu.cert_exempt_applies_courses set schema flow;
alter table flow.cert_exempt_applies_courses rename to edu_cert_exempt_applies_courses;

alter table edu.cert_exempt_applies set schema flow;
alter table flow.cert_exempt_applies rename to edu_cert_exempt_applies;

alter table edu.exam_defer_applies set schema flow;
alter table flow.exam_defer_applies rename to edu_exam_defer_applies;

alter table edu.extern_exempt_applies set schema flow;
alter table flow.extern_exempt_applies rename to edu_extern_exempt_applies;

--edu.course
alter table edu.syllabuses rename column exam_hours to exam_credit_hours;
alter table edu.syllabuses add methods varchar(100);
update edu.syllabuses s set methods =
(select string_agg(m.name,'、') from edu.syllabuses_methods ms,code.teaching_methods m where m.id = ms.teaching_method_id and ms.syllabus_id=s.id);

create table edu.syllabus_exam_hours (credit_hours integer default 0 not null, id bigint not null, nature_id integer not null, syllabus_id bigint not null);
alter table edu.syllabus_exam_hours add constraint pk_ect6q20arbiqruymsfh9iabsf primary key (id);
create index idx_sekt0eg3iv22sxqbigc26w5m1 on edu.syllabus_exam_hours (syllabus_id);
alter table edu.syllabus_exam_hours add constraint pk_ect6q20arbiqruymsfh9iabsf primary key (id);

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
alter table edu.teaching_plans add approver_id bigint;
alter table edu.teaching_plans add status int4 default 0;
alter table edu.syllabuses add approver_id bigint;
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

alter table edu.syllabus_credit_hours drop learning cascade;
alter table edu.syllabus_topics alter contents type varchar(1000);
alter table edu.syllabus_topics add column methods varchar(255);
--misc
update code.teaching_natures set en_name='Theory' where name='理论';
update code.teaching_natures set en_name='Practice' where name='实践';
update code.teaching_natures set en_name='Experiment' where name='实验';

update code.syllabus_topic_labels set en_name ='Course leading value' where name='课程思政';
update code.syllabus_topic_labels set en_name ='Emphasis in this topic' where name='本章重点';
update code.syllabus_topic_labels set en_name ='Difficulties in this topic' where name='本章难点';
update code.syllabus_topic_labels set en_name ='Students’ learning outcomes' where name='学生学习成果';

