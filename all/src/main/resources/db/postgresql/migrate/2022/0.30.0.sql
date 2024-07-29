insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.30.0',now(),'首个长期支持版');

--使用这个查询出来的值，一般是18
select * from base.project_properties where name='edu.course.hours_per_credit';

alter table edu.major_course_groups add credit_hours int4;
alter table edu.major_course_groups add hour_ratios varchar(20);
update edu.major_course_groups set credit_hours= credits*15;

alter table edu.execution_course_groups add credit_hours int4;
alter table edu.execution_course_groups add hour_ratios varchar(20);
update edu.execution_course_groups set credit_hours= credits*15;

alter table edu.std_course_groups add credit_hours int4;
alter table edu.std_course_groups add hour_ratios varchar(20);
update edu.std_course_groups set credit_hours= credits*15;
---staff and teacher---------
create table base.staffs (school_id integer not null, degree_award_by varchar(255), political_status_id integer,
                         code varchar(20) not null, id bigint not null, formal_hr boolean not null, homepage varchar(200),
                         title_id integer, end_on date, birthday date, id_type_id integer, organization varchar(200),
                         name varchar(100) not null, updated_at timestamp not null, email varchar(100), nation_id integer,
                         degree_id integer, department_id integer not null, education_degree_id integer,
                         degree_level_id integer, begin_on date not null, id_number varchar(18),
                         status_id integer not null, mobile varchar(20), staff_type_id integer not null,
                         parttime boolean not null, gender_id integer not null,external_ boolean not null);
insert into base.staffs(id,school_id,nation_id,political_status_id,code,department_id,formal_hr,title_id,staff_type_id,birthday,
                        id_type_id,id_number,name,updated_at,status_id,gender_id,begin_on,end_on,degree_id,education_degree_id,parttime,external_)
select t.id,t.school_id,p.nation_id,p.political_status_id,u.code,u.department_id,t.formal_hr,t.title_id,t.teacher_type_id,p.birthday,
                               p.id_type_id,p.code,u.name,t.updated_at,t.status_id,u.gender_id,t.begin_on,t.end_on,t.degree_id,t.education_degree_id,ttype.parttime,ttype.external_ from
                               base.teachers t join base.users u on u.id=t.user_id join base.c_teacher_types ttype on t.teacher_type_id=ttype.id
                               left outer join base.people p on p.id=t.person_id;
alter table hr.teacher_profiles rename teaching_career to career;
alter table base.staffs add constraint pk_qi7hj56hag2uu4e5eu4egoxrl primary key (id);
comment on table base.staffs is '教职工信息@common';
comment on column base.staffs.id is '非业务主键:datetime';
comment on column base.staffs.begin_on is '生效日期';
comment on column base.staffs.birthday is '出生日期';
comment on column base.staffs.code is '代码';
comment on column base.staffs.degree_award_by is '最高学位授予单位';
comment on column base.staffs.degree_id is '学位ID';
comment on column base.staffs.degree_level_id is '最高学位ID';
comment on column base.staffs.department_id is '部门组织机构信息ID';
comment on column base.staffs.education_degree_id is '最高学历ID';
comment on column base.staffs.email is '电子邮件';
comment on column base.staffs.end_on is '失效日期';
comment on column base.staffs.external_ is '是否外聘';
comment on column base.staffs.formal_hr is '是否在编';
comment on column base.staffs.gender_id is '性别ID';
comment on column base.staffs.homepage is '个人主页';
comment on column base.staffs.id_number is '证件号码';
comment on column base.staffs.id_type_id is '证件类型ID';
comment on column base.staffs.mobile is '联系手机';
comment on column base.staffs.name is '名称';
comment on column base.staffs.nation_id is '民族ID';
comment on column base.staffs.organization is '全职工作单位';
comment on column base.staffs.parttime is '是否兼职';
comment on column base.staffs.political_status_id is '政治面貌ID';
comment on column base.staffs.school_id is '学校信息ID';
comment on column base.staffs.staff_type_id is '教职工类别ID';
comment on column base.staffs.status_id is '在职状态ID';
comment on column base.staffs.title_id is '最高职称ID';
comment on column base.staffs.updated_at is '更新时间';

create table code.staff_types (id integer not null, begin_on date not null, end_on date, parent_id integer, updated_at timestamp not null, code varchar(20) not null unique, en_name varchar(300), name varchar(100) not null, remark varchar(200));
create table code.staff_source_types (id integer not null, begin_on date not null, end_on date, parent_id integer, updated_at timestamp not null, code varchar(20) not null unique, en_name varchar(300), name varchar(100) not null, remark varchar(200));
alter table code.staff_source_types add constraint pk_jsm380evxdo115plu90m5dmtc primary key (id);
alter table code.staff_types add constraint pk_a4368uis6hkcnv1jksw7hntap primary key (id);

alter table code.staff_types alter column parent_id drop not null;
alter table code.staff_source_types alter column parent_id drop not null;
insert into code.staff_types(id,code,name,begin_on,updated_at) select id ,code,name,begin_on,updated_at from base.c_teacher_types;

alter table base.staffs add constraint pk_qi7hj56hag2uu4e5eu4egoxrl primary key (id);
alter table base.staffs add constraint uk_ksaq070k32jb6aey065dd9xv0 unique (school_id,code);
alter table base.staffs add constraint fk_hgomx5css6ae570euyh78c1cu foreign key (gender_id) references code.genders (id);
alter table base.staffs add constraint fk_adnalkarjsuc6ea1orayn7fxl foreign key (nation_id) references code.nations (id);
alter table base.staffs add constraint fk_d90vgr3jj7o5ue23gk796a6uq foreign key (title_id) references code.professional_titles (id);
alter table base.staffs add constraint fk_7fi8qjxmgmkp1ideqkdpvfsxy foreign key (school_id) references base.schools (id);
alter table base.staffs add constraint fk_1fvbesq01b23ayv2ksysfhf6j foreign key (department_id) references base.departments (id);
alter table base.staffs add constraint fk_eule2gkjbra9etfa0tm7qcsxd foreign key (id_type_id) references code.id_types (id);
alter table base.staffs add constraint fk_mk2ysra7xf1kxck41linp1pc foreign key (political_status_id) references code.political_statuses (id);
alter table base.staffs add constraint fk_420icw78jj3rsu1i16hgkxsry foreign key (degree_id) references code.degrees (id);
alter table base.staffs add constraint fk_ipkufpinodstkxuyxrirqo8bk foreign key (staff_type_id) references code.staff_types (id);
alter table base.staffs add constraint fk_rswm63aod14l5g97k66xlnlo0 foreign key (degree_level_id) references code.degree_levels (id);
alter table base.staffs add constraint fk_e3cvddfl42pgisf9vt4wjanji foreign key (education_degree_id) references code.education_degrees (id);
alter table base.staffs add constraint fk_kcmhcp000r933mi2jk9wm6ns foreign key (status_id) references code.work_statuses (id);

alter table base.teachers add staff_id bigint;
alter table base.teachers add name varchar(100);
update base.teachers set staff_id=id;
update base.teachers t set (name)=(select u.name from base.users u where u.id=t.user_id);

alter table code.divisions alter column parent_id drop not null;
alter table base.people alter column birthday drop not null;

create table base.teachers_campuses (teacher_id bigint not null, campus_id integer not null);
comment on table base.teachers_campuses is '任教校区@edu';
comment on column base.teachers_campuses.campus_id is '校区信息ID';
comment on column base.teachers_campuses.teacher_id is '教师信息ID';
alter table base.teachers_campuses add constraint pk_4wtyv7xyavsa0tlo5g8rl0txy primary key (teacher_id,campus_id);
create index idx_dryob4n9h2g16emfu7mhc2b7w on base.teachers_campuses (teacher_id);
alter table base.teachers  add tqc_number varchar(20);
alter table base.teachers  add oqc varchar(100);
alter table base.teachers  add tutor_type_id int4;

--#if code.degree_levels is empty,insert them.
INSERT INTO code.degree_levels(id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '1', '学士学位', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.degree_levels(id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (2, '2', '硕士学位', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.degree_levels(id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (3, '3', '博士学位', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
------------grades--------------
alter table base.grades add project_id int4;
alter table base.grades alter column id type int8;
alter table base.grades add en_name varchar(50);
alter table base.grades add begin_on date;
alter table base.grades add end_on date;

update base.grades set project_id = (select min(id) from base.projects);
update base.grades set begin_on= to_date(substr(code,1,4)||'0901','YYYYMMDD');
update base.grades set end_on= to_date((substr(code,1,4)::int +4)::varchar||'0901','YYYYMMDD'); --4年制

insert into base.grades(id,code,name,project_id)
select distinct (s.project_id::varchar||replace(s.grade,'-',''))::int8,s.grade,s.grade,s.project_id from base.squads s
where not exists(select * from base.grades g where g.project_id=s.project_id and g.name=s.grade);

update base.grades set begin_on=to_date(substr(code,1,4)||'-09-01','yyyy-MM-dd') where begin_on is null;
update base.grades set end_on=to_date(substr(code,1,4)::int4+5||'-07-01','yyyy-MM-dd') where end_on is null;

alter table base.grades add constraint uk_enpnepu169ecfvnnni3oayeva unique (project_id,code);

alter table base.squads add grade_id int8;
update base.squads s set grade_id=(select g.id from base.grades g where g.project_id=s.project_id and g.code=s.grade);

alter table base.student_states add grade_id int8;
update base.student_states ss set grade_id=(select g.id from base.grades g,base.students s where g.project_id=s.project_id and s.id=ss.std_id and g.code=ss.grade);

alter table edu.programs add grade_id int8;
update edu.programs s set grade_id=(select g.id from base.grades g where g.project_id=s.project_id and g.code=s.grade);

alter table base.school_lengths add column to_grade_id bigint;
alter table base.school_lengths add column from_grade_id bigint;
update base.school_lengths s set from_grade_id=(select g.id from base.grades g,base.majors m where m.id=s.major_id and m.project_id=g.project_id and g.code=s.from_grade);
update base.school_lengths s set to_grade_id=(select g.id from base.grades g,base.majors m where m.id=s.major_id and m.project_id=g.project_id and g.code=s.to_grade);
alter table base.school_lengths add constraint fk_j1yagjj51c2c0kawbwj0id46w foreign key (to_grade_id) references base.grades (id);
alter table base.school_lengths add constraint fk_ny033y7pameoqo6j69sw3xguk foreign key (from_grade_id) references base.grades (id);
alter table base.school_lengths alter from_grade_id set not null;

alter table edu.major_alt_courses add column from_grade_id bigint;
alter table edu.major_alt_courses add column to_grade_id bigint;
update edu.major_alt_courses s set from_grade_id=(select g.id from base.grades g where g.code=s.from_grade) where from_grade_id is null;
update edu.major_alt_courses s set to_grade_id=(select g.id from base.grades g  where g.code=s.to_grade) where to_grade_id is null;

alter table edu.share_plans add column from_grade_id bigint;
alter table edu.share_plans add column to_grade_id bigint;
update edu.share_plans s set from_grade_id=(select g.id from base.grades g  where  g.project_id=s.project_id and g.code=s.from_grade);
update edu.share_plans s set to_grade_id=(select g.id from base.grades g  where  g.project_id=s.project_id and g.code=s.to_grade);

alter table std.cfg_tuition_configs add column project_id int4;
--视情况决定手工更改
update std.cfg_tuition_configs set project_id=(select min(id) from base.projects);
alter table std.cfg_tuition_configs add column from_grade_id bigint;
alter table std.cfg_tuition_configs add column to_grade_id bigint;
update std.cfg_tuition_configs s set from_grade_id=(select g.id from base.grades g where g.project_id=s.project_id and g.code=s.from_grade);
update std.cfg_tuition_configs s set to_grade_id=(select g.id from base.grades g where g.project_id=s.project_id and g.code=s.to_grade);

alter table std.transfer_applies add column from_grade_id bigint;
update std.transfer_applies s set from_grade_id=(select g.id from base.grades g,base.students std where g.project_id=std.project_id and std.id=s.std_id and g.code=s.from_grade);
alter table std.transfer_applies alter from_grade_id set not null;
alter table std.transfer_applies add constraint fk_ia2lww4yn37qo57xd6766b7se foreign key (from_grade_id) references base.grades (id);
alter table std.cfg_transfer_schemes alter grade_id type bigint;

alter table edu.clazzes rename grade to grades;
alter table edu.clazzes alter grades type varchar(40);
alter table edu.clazzes add shared bool default false;
---------level---------------
insert into base.project_properties(id,project_id,name,value_,type_name,description)
values(datetime_id(),(select min(id) from base.projects),'edu.course.level_credit_supported','false','boolean','课程是否支持不同层次学分不同');

alter table edu.major_alt_courses alter column level_id drop not null;

create table base.course_levels (course_id bigint not null, credits float4, id bigint not null, level_id integer not null);
alter table base.course_levels add constraint pk_1mqdklheeg8p8nx65q4l3og8g primary key (id);
alter table base.course_levels add constraint uk_59cbmhw5789an69kcq0j5g9i8 unique (course_id,level_id);

insert into base.course_levels(id,course_id,level_id)
select next_id('base.course_levels'),cl.course_id,l.id from base.courses_levels cl,code.education_levels l
 where cl.academic_level_id=l.to_level_id and exists(select * from base.projects_levels pl where pl.education_level_id=l.id);

alter table base.courses rename column credits to default_credits;
----------education types-----------
create table base.c_education_types (id integer not null, begin_on date not null, end_on date, updated_at timestamp not null, code varchar(20) not null unique, en_name varchar(300), name varchar(100) not null, remark varchar(200));
insert into base.c_education_types(id,code,name,begin_on,updated_at) values(1,'1','普通',current_date -10*365,now());
alter table base.c_education_types add constraint pk_ct9svvtbjc2g6i7erusn11hgs primary key (id);

create table base.projects_edu_types (project_id integer not null, education_type_id integer not null);
insert into base.projects_edu_types(project_id,education_type_id)select p.id,e.id from base.projects p,base.c_education_types e;
comment on table base.projects_edu_types is '项目-培养类型@common';
comment on column base.projects_edu_types.education_type_id is '培养类型ID';
comment on column base.projects_edu_types.project_id is '项目ID';
alter table base.projects_edu_types add constraint pk_dbq66x3eva45j6uofa2qrre3n primary key (project_id,education_type_id);
create index idx_59q82b4p5aojkd254d4wy0u4f on base.projects_edu_types (project_id);
alter table base.projects_edu_types add constraint fk_enkev9tonpxja5hipw919c0wt foreign key (project_id) references base.projects (id);
alter table base.projects_edu_types add constraint fk_qrd90wdrvpq5vy02rhxwmsgb7 foreign key (education_type_id) references base.c_education_types (id);

alter table base.squads add edu_type_id int4;
alter table base.students add edu_type_id int4;
alter table edu.programs add edu_type_id int4;
alter table edu.program_doc_templates add edu_type_id int4;
alter table edu.major_alt_courses add edu_type_id int4;
alter table edu.share_plans add edu_type_id int4;
update base.squads set edu_type_id=1;
update base.students set edu_type_id=1;
update edu.programs set edu_type_id=1;
update edu.program_doc_templates set edu_type_id=1;
update edu.major_alt_courses set edu_type_id=1;
update edu.share_plans set edu_type_id=1;

comment on table base.c_education_types is '培养类型@edu.code';
comment on column base.c_education_types.id is '非业务主键:auto_increment';
comment on column base.c_education_types.begin_on is '生效日期';
comment on column base.c_education_types.code is '代码';
comment on column base.c_education_types.en_name is '英文名称';
comment on column base.c_education_types.end_on is '失效日期';
comment on column base.c_education_types.name is '名称';
comment on column base.c_education_types.remark is '备注';
comment on column base.c_education_types.updated_at is '修改时间';

-----------mentor and tutor-------------
alter table base.mentors add staff_id bigint;
alter table base.mentors add name varchar(100);
update base.mentors m set (staff_id, name )=(select s.id,s.name from base.staffs s where s.id=m.id);

alter table base.squads drop constraint fk_ev1tw6auwpif0qyjddjac2wf4;
alter table base.squads drop constraint fk_c2swxumbmnqw4bre3vk4rah3g;
alter table base.squads drop constraint fk_ag4kov5ron4bpfk8j91d6slb0;
------------student---------------
alter table base.students add code varchar(15);
alter table base.students add name varchar(50);
alter table base.students add en_name varchar(100);
alter table base.students add gender_id int4;
update base.students m set (code,name,en_name,gender_id)=(select u.code,u.name,en_name,u.gender_id from base.users u where u.id=m.user_id);
alter table base.students alter column code set not null;
alter table base.students alter column name set not null;
alter table base.students alter column gender_id set not null;
alter table base.students add constraint uk_50fnp38f8oyhuolhke7nu920t unique (code,project_id);
insert into base.project_properties(id,project_id,name,value_,type_name,description)
values(datetime_id(),(select max(id) from base.projects),'std.info.squad_supported','true','boolean','学籍信息是否支持分班管理');
insert into base.project_properties(id,project_id,name,value_,type_name,description)
values(datetime_id(),(select max(id) from base.projects),'std.info.tutor_supported','false','boolean','学籍信息中是否支持导师');

alter table base.students drop column user_id;
drop index base.idx_g4fwvutq09fjdlb4bb0byp7t;
create index idx_eqa1d4jiyg5m5rnuja7ifgw73 on base.students (code);
------------examinee-----------------
drop table std.examinees_scores cascade;
alter table std.examinees drop school_no cascade;
alter table std.examinees drop province cascade;
alter table std.examinees add column enroll_mode_id integer;
alter table std.examinees add column major_id bigint;
alter table std.examinees add column client varchar(255);
alter table std.examinees add column scores varchar(255);
alter table std.examinees add column education_mode_id integer;
alter table std.examinees add column letter_no varchar(30);
alter table std.examinees add constraint fk_pmvt794yxsrbdf2asttcagv0j foreign key (enroll_mode_id) references code.enroll_modes (id);
alter table std.examinees add constraint fk_qkvgf74yg71uom0fv2fmlcq6b foreign key (education_mode_id) references code.education_modes (id);
alter table std.examinees add constraint fk_6e33icgq0qssekehpga9n77pr foreign key (major_id) references base.majors (id);

alter table std.examinees add scores varchar(100);
alter table std.examinees add major_id bigint;
alter table std.examinees add education_mode_id int4;
alter table std.examinees add enroll_mode_id int4;

alter table std.examinees add client varchar(100);
alter table std.examinees add letter_no varchar(100);

drop table std.examinees_scores;

-----graduate grade------
alter table base.graduate_grades add column code varchar(255);
alter table base.graduate_grades add column project_id integer;
update  base.graduate_grades set code=graduate_year;
update base.graduate_grades set project_id=(select min(id) from base.projects);

alter table base.graduate_grades add constraint uk_op90s66v46iqanqv5ryumkvs0 unique (project_id,code);


----external signup-----------
create table edu.cfg_cert_signup_configs (id bigint not null, begin_at timestamp not null, category_id integer not null, code varchar(255) not null, end_at timestamp not null, name varchar(255) not null, notice varchar(255) not null, opened boolean not null, prediction boolean not null, project_id integer not null, semester_id integer not null);
comment on table edu.cfg_cert_signup_configs is '资格考试报名设置@extern.config';
comment on column edu.cfg_cert_signup_configs.id is '非业务主键:datetime';
comment on column edu.cfg_cert_signup_configs.begin_at is '开始时间';
comment on column edu.cfg_cert_signup_configs.category_id is '校外考试种类ID';
comment on column edu.cfg_cert_signup_configs.code is '代码';
comment on column edu.cfg_cert_signup_configs.end_at is '结束时间';
comment on column edu.cfg_cert_signup_configs.name is '名称';
comment on column edu.cfg_cert_signup_configs.notice is '通知';
comment on column edu.cfg_cert_signup_configs.opened is '在规定的时间段内,是否可以开放';
comment on column edu.cfg_cert_signup_configs.prediction is '是否预报名';
comment on column edu.cfg_cert_signup_configs.project_id is '项目ID';
comment on column edu.cfg_cert_signup_configs.semester_id is '学年学期ID';
alter table edu.cfg_cert_signup_configs add constraint pk_6vjd0wcog5utmxtwspwysdm6a primary key (id);
alter table edu.cfg_cert_signup_configs add constraint fk_byvorr6undyptrclap7sdtm5m foreign key (project_id) references base.projects (id);
alter table edu.cfg_cert_signup_configs add constraint fk_eam9n5w01683tr2v0c2n7ddt4 foreign key (semester_id) references base.semesters (id);
alter table edu.cfg_cert_signup_configs add constraint fk_bmj41ar7v04d1y814pinik1ik foreign key (category_id) references edu.c_certificate_categories (id);
create table edu.cfg_cert_signup_scopes (id bigint not null, codes varchar(20000), grades varchar(255), included boolean not null, level_id integer not null, setting_id bigint not null);
comment on table edu.cfg_cert_signup_scopes is '校外考试报名学生范围@extern.config';
comment on column edu.cfg_cert_signup_scopes.id is '非业务主键:datetime';
comment on column edu.cfg_cert_signup_scopes.codes is '学号串';
comment on column edu.cfg_cert_signup_scopes.grades is '年级';
comment on column edu.cfg_cert_signup_scopes.included is '包含还是排除';
comment on column edu.cfg_cert_signup_scopes.level_id is '培养层次ID';
comment on column edu.cfg_cert_signup_scopes.setting_id is '资格考试报名科目设置ID';
alter table edu.cfg_cert_signup_scopes add constraint pk_j10g1nix5jyy7hxenfrx0fw8v primary key (id);
create index idx_edov1lquvcs0l602abdn6wlaq on edu.cfg_cert_signup_scopes (setting_id);
alter table edu.cfg_cert_signup_scopes add constraint fk_lu18cx32pedjtp0ixb0gpc3ql foreign key (level_id) references code.education_levels (id);
alter table edu.cfg_cert_signup_scopes add constraint fk_q9le8nbjwuu5cjo8gxv26dmya foreign key (setting_id) references edu.cfg_cert_signup_settings (id);
create table edu.cert_signups (id bigint not null, exam_no varchar(255), fee integer not null, ip varchar(255) not null, semester_id integer not null, std_id bigint not null, subject_id integer not null, updated_at timestamp not null);
comment on table edu.cert_signups is '资格考试报名记录@extern';
comment on column edu.cert_signups.id is '非业务主键:datetime';
comment on column edu.cert_signups.exam_no is '准考证号码';
comment on column edu.cert_signups.fee is '报名费';
comment on column edu.cert_signups.ip is 'IP';
comment on column edu.cert_signups.semester_id is '学年学期ID';
comment on column edu.cert_signups.std_id is '学籍信息实现ID';
comment on column edu.cert_signups.subject_id is '校外考试科目ID';
comment on column edu.cert_signups.updated_at is '更新时间';
alter table edu.cert_signups add constraint pk_buvpy5j8eye2vtlladutvfisn primary key (id);
alter table edu.cert_signups add constraint fk_bbwvkmuee7sqkxmv9ehfaig3f foreign key (std_id) references base.students (id);
alter table edu.cert_signups add constraint fk_9tqhefo5ucxv5rerkboiufkeo foreign key (subject_id) references edu.c_certificate_subjects (id);
alter table edu.cert_signups add constraint fk_iuwcqfqj72asuoguvjb0q1qr0 foreign key (semester_id) references base.semesters (id);
create table edu.cfg_cert_exempt_courses (cert_exempt_setting_id bigint not null, course_id bigint not null);
alter table edu.cfg_cert_signup_exclusives add constraint pk_7nbbxkricr676498aw4csydsv primary key (cert_signup_setting_id,certificate_subject_id);
create index idx_f02a2m7bqy6jxa231mv45l6xj on edu.cfg_cert_signup_exclusives (cert_signup_setting_id);
alter table edu.cfg_cert_signup_exclusives add constraint fk_dgdhkbcwckcfhdq2g0vemolp4 foreign key (cert_signup_setting_id) references edu.cfg_cert_signup_settings (id);
alter table edu.cfg_cert_signup_exclusives add constraint fk_8ci8knp8ownasy9w3w0uof12u foreign key (certificate_subject_id) references edu.c_certificate_subjects (id);

create table edu.cfg_cert_signup_settings (id bigint not null, config_id bigint not null, depends_on_id integer, exam_begin_at smallint not null, exam_end_at smallint not null, exam_on date, fee_of_material integer not null, fee_of_outline integer not null, fee_of_signup integer not null, max_std integer not null, re_exam_allowed boolean not null, subject_id integer not null);
comment on table edu.cfg_cert_signup_settings is '资格考试报名科目设置@extern.config';
comment on column edu.cfg_cert_signup_settings.id is '非业务主键:datetime';
comment on column edu.cfg_cert_signup_settings.config_id is '资格考试报名设置ID';
comment on column edu.cfg_cert_signup_settings.depends_on_id is '报名时要求通过的科目ID';
comment on column edu.cfg_cert_signup_settings.exam_begin_at is '考试开始时间';
comment on column edu.cfg_cert_signup_settings.exam_end_at is '考试结束时间';
comment on column edu.cfg_cert_signup_settings.exam_on is '考试日期';
comment on column edu.cfg_cert_signup_settings.fee_of_material is '要求材料费';
comment on column edu.cfg_cert_signup_settings.fee_of_outline is '要求考纲费';
comment on column edu.cfg_cert_signup_settings.fee_of_signup is '要求报名费';
comment on column edu.cfg_cert_signup_settings.max_std is '最大学生数(0或者null表示不限制)';
comment on column edu.cfg_cert_signup_settings.re_exam_allowed is '通过后是否可以重考';
comment on column edu.cfg_cert_signup_settings.subject_id is '校外考试科目ID';
alter table edu.cfg_cert_signup_settings add constraint pk_nricass8hv7unl7pqu13owjt3 primary key (id);
create index idx_e1jm14m4lkrxvokb269vlrw46 on edu.cfg_cert_signup_settings (config_id);
alter table edu.cfg_cert_signup_settings add constraint fk_f9ex359j1sv1550aoka9lnk7x foreign key (depends_on_id) references edu.c_certificate_subjects (id);
alter table edu.cfg_cert_signup_settings add constraint fk_5agxjc89ilra33ftv8ed0ti72 foreign key (subject_id) references edu.c_certificate_subjects (id);
alter table edu.cfg_cert_signup_settings add constraint fk_g9aks6oc67st2ar2dh6n4y9vd foreign key (config_id) references edu.cfg_cert_signup_configs (id);

-----exchange grades-------------------
alter table std.exemption_applies rename to exchange_exempt_applies;
alter table std.exemption_credits rename to exchange_exempt_credits;

----external cert exemption---------------
create table edu.cert_exempt_applies_courses (cert_exempt_apply_id bigint not null, course_id bigint not null);
comment on table edu.cert_exempt_applies_courses is '免修课程@extern';
comment on column edu.cert_exempt_applies_courses.cert_exempt_apply_id is '校外证书成绩申请ID';
comment on column edu.cert_exempt_applies_courses.course_id is '课程基本信息ID';
alter table edu.cert_exempt_applies_courses add constraint pk_jin6irw0guks0awaktsymujxt primary key (cert_exempt_apply_id,course_id);
create index idx_8xnd8bvmns043fxpppt0rso0f on edu.cert_exempt_applies_courses (cert_exempt_apply_id);
alter table edu.cert_exempt_applies_courses add constraint fk_7k48jlbrrj2ce4fx6xh58qiaa foreign key (cert_exempt_apply_id) references edu.cert_exempt_applies (id);
alter table edu.cert_exempt_applies_courses add constraint fk_o7rpxkx5n1vmu0wikcylphn6m foreign key (course_id) references base.courses (id);
create table edu.cfg_cert_exempt_configs (id bigint not null, begin_at timestamp not null, edu_type_id integer not null, end_at timestamp not null, level_id integer not null, notice varchar(2000) not null, project_id integer not null, semester_id integer not null);
comment on table edu.cfg_cert_exempt_configs is '校外考试免修设置@extern.config';
comment on column edu.cfg_cert_exempt_configs.id is '非业务主键:datetime';
comment on column edu.cfg_cert_exempt_configs.begin_at is '开始时间';
comment on column edu.cfg_cert_exempt_configs.edu_type_id is '培养类型ID';
comment on column edu.cfg_cert_exempt_configs.end_at is '结束时间';
comment on column edu.cfg_cert_exempt_configs.level_id is '培养层次ID';
comment on column edu.cfg_cert_exempt_configs.notice is '通知';
comment on column edu.cfg_cert_exempt_configs.project_id is '项目ID';
comment on column edu.cfg_cert_exempt_configs.semester_id is '学年学期ID';
alter table edu.cfg_cert_exempt_configs add constraint pk_j19n9ybw47xlseaxywdjqmfpy primary key (id);
alter table edu.cfg_cert_exempt_configs add constraint fk_7ok5emdc7ixkuhb4vs6n4ylkq foreign key (edu_type_id) references base.c_education_types (id);
alter table edu.cfg_cert_exempt_configs add constraint fk_3bf9b3u54ilkal7isl0v8jaue foreign key (level_id) references code.education_levels (id);
alter table edu.cfg_cert_exempt_configs add constraint fk_j19gysut0rqh56dibrl0rps2i foreign key (project_id) references base.projects (id);
alter table edu.cfg_cert_exempt_configs add constraint fk_jqfmgypmunms74de7j5pa3591 foreign key (semester_id) references base.semesters (id);

comment on table edu.cfg_cert_exempt_courses is '免修课程@extern.config';
comment on column edu.cfg_cert_exempt_courses.cert_exempt_setting_id is '校外考试免修科目设置ID';
comment on column edu.cfg_cert_exempt_courses.course_id is '课程基本信息ID';
alter table edu.cfg_cert_exempt_courses add constraint pk_eji2737f7vdc4v19sshl41eki primary key (cert_exempt_setting_id,course_id);
create index idx_hvlgcfiojes594vw9s3qbmr2s on edu.cfg_cert_exempt_courses (cert_exempt_setting_id);
alter table edu.cfg_cert_exempt_courses add constraint fk_elupa1m35cxgdg8erltft23qk foreign key (cert_exempt_setting_id) references edu.cfg_cert_exempt_settings (id);
alter table edu.cfg_cert_exempt_courses add constraint fk_1xl9fj2xa8m745brv6696etac foreign key (course_id) references base.courses (id);
create table edu.cfg_cert_signup_exclusives (cert_signup_setting_id bigint not null, certificate_subject_id integer not null);
comment on table edu.cfg_cert_signup_exclusives is '有冲突的科目@extern.config';
comment on column edu.cfg_cert_signup_exclusives.cert_signup_setting_id is '资格考试报名科目设置ID';
comment on column edu.cfg_cert_signup_exclusives.certificate_subject_id is '校外考试科目ID';

create table edu.cfg_cert_exempt_settings (id bigint not null, audit_depart_id integer not null, config_id bigint not null, min_score float4, remark varchar(255), subject_id integer not null, valid_months integer);
comment on table edu.cfg_cert_exempt_settings is '校外考试免修科目设置@extern.config';
comment on column edu.cfg_cert_exempt_settings.id is '非业务主键:datetime';
comment on column edu.cfg_cert_exempt_settings.audit_depart_id is '审核部门ID';
comment on column edu.cfg_cert_exempt_settings.config_id is '校外考试免修设置ID';
comment on column edu.cfg_cert_exempt_settings.min_score is '最低分';
comment on column edu.cfg_cert_exempt_settings.remark is '备注';
comment on column edu.cfg_cert_exempt_settings.subject_id is '校外考试科目ID';
comment on column edu.cfg_cert_exempt_settings.valid_months is '有效期长度，以月为单位';
alter table edu.cfg_cert_exempt_settings add constraint pk_rbosw8xiqs3g7q9hwqe2jnrce primary key (id);
create index idx_al164spgi1lk7vl66nht01qse on edu.cfg_cert_exempt_settings (config_id);
alter table edu.cfg_cert_exempt_settings add constraint fk_nt9lyaf8cu0l3ce7w98e8bq4l foreign key (audit_depart_id) references base.departments (id);
alter table edu.cfg_cert_exempt_settings add constraint fk_citbgvc55hglrodv369oikqr4 foreign key (subject_id) references edu.c_certificate_subjects (id);
alter table edu.cfg_cert_exempt_settings add constraint fk_1qvyy7di15rpre9be94t48nor foreign key (config_id) references edu.cfg_cert_exempt_configs (id);
create table edu.cert_exempt_applies (id bigint not null, acquired_on date not null, attachment_path varchar(100) not null, audit_depart_id integer not null, audit_opinion varchar(255), certificate varchar(80), grading_mode_id integer not null, reasons varchar(500) not null, score_text varchar(255) not null, semester_id integer not null, status integer not null, std_id bigint not null, subject_id integer not null, updated_at timestamp not null);
comment on table edu.cert_exempt_applies is '校外证书成绩申请@extern';
comment on column edu.cert_exempt_applies.id is '非业务主键:datetime';
comment on column edu.cert_exempt_applies.acquired_on is '获得日期';
comment on column edu.cert_exempt_applies.attachment_path is '成绩单附件路径';
comment on column edu.cert_exempt_applies.audit_depart_id is '审核部门ID';
comment on column edu.cert_exempt_applies.audit_opinion is '审核意见';
comment on column edu.cert_exempt_applies.certificate is '证书编号';
comment on column edu.cert_exempt_applies.grading_mode_id is '成绩记录方式ID';
comment on column edu.cert_exempt_applies.reasons is '申请理由';
comment on column edu.cert_exempt_applies.score_text is '成绩';
comment on column edu.cert_exempt_applies.semester_id is '学年学期ID';
comment on column edu.cert_exempt_applies.status is '申请状态';
comment on column edu.cert_exempt_applies.std_id is '学籍信息实现ID';
comment on column edu.cert_exempt_applies.subject_id is '校外考试科目ID';
comment on column edu.cert_exempt_applies.updated_at is '更新时间';
alter table edu.cert_exempt_applies add constraint pk_9mw6wwhdud4s410804223kes5 primary key (id);
alter table edu.cert_exempt_applies add constraint fk_ommhqie3h0ghxogq76jvn6cd8 foreign key (audit_depart_id) references base.departments (id);
alter table edu.cert_exempt_applies add constraint fk_or7qark1i94jgvq7la7neptkl foreign key (std_id) references base.students (id);
alter table edu.cert_exempt_applies add constraint fk_e57o4cyo3l03jsic3ectaayd4 foreign key (subject_id) references edu.c_certificate_subjects (id);
alter table edu.cert_exempt_applies add constraint fk_pdjvkbhelebcqjv0c2elhtsdr foreign key (grading_mode_id) references code.grading_modes (id);
alter table edu.cert_exempt_applies add constraint fk_72ghyx5kpvd8norg54u587q89 foreign key (semester_id) references base.semesters (id);
-----std credit stat------
create table edu.cfg_std_credit_stats (id bigint not null, max_credits float4 not null, max_new_count integer not null, repeat_count integer not null, semester_id integer not null, std_id bigint not null, total_credits float4 not null, total_new_count integer not null);
comment on table edu.cfg_std_credit_stats is '学生每学期选择教学班限制和统计@clazz.config';
comment on column edu.cfg_std_credit_stats.id is '非业务主键:datetime';
comment on column edu.cfg_std_credit_stats.max_credits is '学分上限';
comment on column edu.cfg_std_credit_stats.max_new_count is '最多新选课程门数';
comment on column edu.cfg_std_credit_stats.repeat_count is '重修数量';
comment on column edu.cfg_std_credit_stats.semester_id is '学年学期ID';
comment on column edu.cfg_std_credit_stats.std_id is '学籍信息实现ID';
comment on column edu.cfg_std_credit_stats.total_credits is '已选学分';
comment on column edu.cfg_std_credit_stats.total_new_count is '已选新课程门数(不含重修)';
alter table edu.cfg_std_credit_stats add constraint pk_1l485f6rmlnl41j59lt9wlxre primary key (id);
alter table edu.cfg_std_credit_stats add constraint fk_5aas9tyryicmrt3icq2djm47u foreign key (std_id) references base.students (id);
alter table edu.cfg_std_credit_stats add constraint fk_bowf4nqkwexy5e47iawl7qu8x foreign key (semester_id) references base.semesters (id);

-----clazz material and notice----------
create table edu.clazz_materials (id bigint not null, file_path varchar(400), updated_by_id bigint not null, name varchar(300) not null, updated_at timestamp not null, clazz_id bigint not null, url varchar(400));
create table edu.clazz_notice_files (id bigint not null, notice_id bigint not null, file_path varchar(200) not null, media_type varchar(100) not null, name varchar(255) not null, updated_at timestamp not null);
create table edu.clazz_notices (updated_at timestamp not null, clazz_id bigint not null, contents varchar(1500) not null, id bigint not null, title varchar(300) not null, updated_by_id bigint not null);
alter table edu.clazz_materials add constraint pk_nrnf4gf44n88mvd5efhg9ror0 primary key (id);
alter table edu.clazz_notice_files add constraint pk_tc2n52103b3130x5jaaaxj7n1 primary key (id);
alter table edu.clazz_notices add constraint pk_dhgey9skl0nde8lg46v723xi2 primary key (id);
create index idx_ftsvqtl0wf5lx885wwiw514hp on edu.clazz_materials (clazz_id);
create index idx_ifw02k8m49nnnsixxeic5irmd on edu.clazz_notice_files (notice_id);
create index idx_etjnc5p0edykl4yjenl8f5aqo on edu.clazz_notices (clazz_id);
----graduate--------
alter table std.graduations rename to graduates;
alter table std.graduates set schema base;
alter table base.graduates rename education_result_id to result_id;
alter table base.graduates add grade_id int4;

-----tutor-----------
create table code.tutor_types (id integer not null, begin_on date not null, end_on date, updated_at timestamp not null, code varchar(20) not null unique, en_name varchar(300), name varchar(100) not null, remark varchar(200));
-----warning----------
alter table base.teachers drop column school_id;
alter table base.teachers drop column updated_at;
alter table base.teachers drop column teacher_type_id ;
alter table base.teachers drop column formal_hr;
alter table base.teachers drop column status_id ;
alter table base.teachers drop column user_id;
alter table base.teachers drop column person_id;
alter table base.teachers drop column title_id;
alter table base.teachers drop column degree_id;
alter table base.teachers drop column education_degree_id;

alter table base.mentors drop column user_id;
alter table base.students drop column user_id;
alter table base.squads drop column grade;
alter table base.student_states drop column grade;
alter table base.school_lengths drop to_grade cascade;
alter table base.school_lengths drop from_grade cascade;

alter table std.cfg_tuition_configs drop to_grade cascade;
alter table std.cfg_tuition_configs drop from_grade cascade;
alter table std.transfer_applies drop from_grade cascade;
alter table edu.major_alt_courses drop from_grade;
alter table edu.major_alt_courses drop to_grade;
alter table edu.major_alt_courses drop column level_id;
alter table edu.share_plans drop from_grade;
alter table edu.share_plans drop to_grade;

alter table edu.restriction_items rename column include_in to included;
drop table base.c_teacher_types cascade;
drop table base.courses_levels cascade;
drop table hr.staff_states cascade;
drop table hr.duty_infoes cascade;
drop table hr.tutor_infoes cascade;
drop table hr.work_infoes cascade;
drop table hr.education_infoes cascade;
drop table hr.staffs cascade;
drop table std.admissions cascade;
