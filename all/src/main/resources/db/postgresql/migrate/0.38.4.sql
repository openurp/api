insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.38.4',now(),'优化授课计划');

alter table degree.thesis_reviews add course_grade_synced boolean default false;
alter table base.graduate_seasons add graduate_on date;
update base.graduate_seasons set graduate_on=to_date(graduate_year::varchar||'06','yyyyMM');

alter table edu.teaching_plans add learning_hours int4 default 0;
alter table edu.teaching_plans add exam_hours int4 default 0;

alter table edu.lessons add homework varchar(400);
alter table edu.lessons add learning varchar(400);
alter table edu.lessons add learning_hours int4 default 0;

alter table edu.lessons rename column places to remark;
alter table edu.lessons drop column open_on;
alter table edu.lessons drop column units;
alter table edu.lessons drop column begin_at;
alter table edu.lessons drop column end_at;
alter table edu.lessons drop column teaching_method_id;
alter table edu.lessons drop column teaching_nature_id;

create table edu.lessons_methods (lesson_id bigint not null, teaching_method_id integer not null);
alter table edu.lessons_methods add constraint pk_d00iywe107cqvgvvbbh0664sf primary key (lesson_id,teaching_method_id);

create table edu.teaching_plan_hours (credit_hours integer default 0 not null, id bigint not null, plan_id bigint not null, section_id integer not null);
alter table edu.teaching_plan_hours add constraint pk_mq1y62tqmus492b65m9a6faq7 primary key (id);
create index idx_j0h0ocfs1y51jv8vjm0xg1ekd on edu.teaching_plan_hours (plan_id);

create table code.teaching_sections (id integer not null, begin_on date not null, end_on date, code varchar(20) not null, en_name varchar(300), name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);
alter table code.teaching_sections add constraint pk_docvjyxvufaf2y8wly8pqnp4d primary key (id);
alter table code.teaching_sections add constraint teaching_sections_code_key unique (code);

INSERT INTO code.teaching_sections (id, begin_on, code, en_name, end_on, name, updated_at) VALUES (1, '2016-05-04', '01', NULL, NULL, '讲授', '2016-05-04 00:00:00');
INSERT INTO code.teaching_sections (id, begin_on, code, en_name, end_on, name, updated_at) VALUES (2, '2016-05-04', '02', NULL, '2016-05-05 00:00:00', '实验', '2016-05-04 00:00:00');
INSERT INTO code.teaching_sections (id, begin_on, code, en_name, end_on, name, updated_at) VALUES (3, '2016-05-04', '03', NULL, NULL, '习题讲解', '2016-05-04 00:00:00');
INSERT INTO code.teaching_sections (id, begin_on, code, en_name, end_on, name, updated_at) VALUES (4, '2016-05-04', '04', NULL, NULL, '期中考试', '2016-05-04 00:00:00');
INSERT INTO code.teaching_sections (id, begin_on, code, en_name, end_on, name, updated_at) VALUES (5, '2016-05-04', '05', NULL, NULL, '案例讨论', '2016-05-04 00:00:00');
INSERT INTO code.teaching_sections (id, begin_on, code, en_name, end_on, name, updated_at) VALUES (6, '2016-05-04', '06', NULL, NULL, '项目训练', '2016-05-04 00:00:00');
INSERT INTO code.teaching_sections (id, begin_on, code, en_name, end_on, name, updated_at) VALUES (7, '2016-05-04', '07', NULL, '2016-05-05 00:00:00', '上机', '2016-05-04 00:00:00');

---check--
create table std.person_check_items (field integer not null, id bigint not null, check_id bigint not null, old_value varchar(200) not null, new_value varchar(200) not null);
create table std.person_checks (confirmed boolean, id bigint not null, mobile varchar(255), std_id bigint not null, audit_opinion varchar(100), details varchar(255) not null, attachment varchar(255), updated_at timestamptz default current_timestamp not null);
alter table std.person_check_items add constraint pk_5l9cllp6owmy5ni6yldt59wxp primary key (id);
alter table std.person_checks add constraint pk_gu67dbl0npvd2vc7mfb8fe51l primary key (id);
create index idx_i1rmelienjou7tluo440dlom1 on std.person_check_items (check_id);

--program
create table edu.programs_degree_certificates (program_id bigint not null, certificate_id integer not null);
alter table edu.programs_degree_certificates add constraint pk_4coy86rddcbvfyvyoxdone26l primary key (program_id,certificate_id);
create index idx_7w0yqeo8hxaggvl60x9vedi3d on edu.programs_degree_certificates (program_id);
