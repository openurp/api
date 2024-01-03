insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.36.0',now(),'重构基础信息');

--更改考勤表
alter table edu.std_leaves add days int;
create table edu.std_leave_files (id bigint not null, file_path varchar(255) not null, leave_id bigint not null);
create table edu.std_leave_lessons (id bigint not null, clazz_id bigint not null, leave_id bigint, leave_type integer not null, lesson_on date not null, lesson_time varchar(11) not null, semester_id integer not null, std_id bigint not null);

alter table edu.std_leave_files add constraint pk_dr8s0l3a2j9jhm17kyp5no1n1 primary key (id);
alter table edu.std_leave_lessons add constraint pk_fvos8v6qystkps2poftah2g92 primary key (id);

create index idx_c642h40ncdah4eq1q71d1jat1 on edu.std_leave_files (leave_id);
create index idx_b7n6dqvs1vm7wjyc5mcwern37 on edu.std_leave_lessons (clazz_id);
alter table edu.std_leaves add column days integer default 0;
alter table edu.std_leaves alter days set not null;
alter table edu.std_leaves add column lessons integer default 0;
alter table edu.std_leaves alter lessons set not null;
create index idx_c642h40ncdah4eq1q71d1jat1 on edu.std_leave_files (leave_id);
create index idx_b7n6dqvs1vm7wjyc5mcwern37 on edu.std_leave_lessons (clazz_id);
alter table edu.std_leave_files add constraint fk_6iw9dbpma99lp6p2sjn6wtocv foreign key (leave_id) references edu.std_leaves (id);
alter table edu.std_leave_lessons add constraint fk_gqf4ubf1rjpyyxcxupc78sn95 foreign key (std_id) references base.students (id);
alter table edu.std_leave_lessons add constraint fk_mybnsq8acelrwwuun0qq65076 foreign key (leave_id) references edu.std_leaves (id);
alter table edu.std_leave_lessons add constraint fk_n2j9b2iax6x0eopde0o3y4epk foreign key (semester_id) references base.semesters (id);
alter table edu.std_leave_lessons add constraint fk_gmr2yv6ln2f6i91cjxg4dxgl6 foreign key (clazz_id) references edu.clazzes (id);

--更新基础信息的配置项
update base.project_properties set name='base.std.squad_supported' where name='std.info.squad_supported';
update base.project_properties set name='base.std.tutor_supported' where name='std.info.tutor_supported';
update base.project_properties set name='base.std.advisor_supported' where name='std.info.advisor_supported';

update base.project_properties set name='base.edu.teacher_same_depart_with_staff' where name='edu.teacher.same_depart_with_staff';
update base.project_properties set name='base.hr.teacher_same_depart_with_staff' where name='base.edu.teacher_same_depart_with_staff';
update base.project_properties set name='base.edu.course_hours_per_credit' where name='edu.course.hours_per_credit';
update base.project_properties set name='base.edu.course_level_credit_supported' where name='edu.course.level_credit_supported';
update base.project_properties set name='edu.exempt.score_needed' where name='std.exchange.exemption_need_score';

--创建学生申请缓考记录表
create table base.c_exam_defer_reasons (id integer not null, begin_on date not null, code varchar(20) not null, en_name varchar(300), end_on date, name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);
create table cfg.edu_exam_defer_settings (id bigint not null, apply_opened boolean default false not null, days_before_apply integer default 0 not null, exam_type_id integer not null, project_id integer not null);
create table edu.exam_defer_applies (id bigint not null, clazz_id bigint not null, exam_begin_at timestamptz, exam_type_id integer not null, mobile varchar(255), passed boolean, reason_id integer, remark varchar(300), status varchar(50) not null, std_id bigint not null, updated_at timestamptz default current_timestamp not null);

alter table base.c_exam_defer_reasons add constraint pk_gns6x3x63qxymksism3oqu17w primary key (id);
alter table cfg.edu_exam_defer_settings add constraint pk_3q2pcggd70a60qvuei1nyi4vl primary key (id);
alter table edu.exam_defer_applies add constraint pk_8xdjyc2to9kovj035mxi66d5i primary key (id);
alter table base.c_exam_defer_reasons add constraint pk_gns6x3x63qxymksism3oqu17w primary key (id);
alter table cfg.edu_exam_defer_settings add constraint fk_wqldjh36yp77lxqx7v1s08ek foreign key (project_id) references base.projects (id);
alter table cfg.edu_exam_defer_settings add constraint fk_k4040od5g9n5xlfe8lye8ck8x foreign key (exam_type_id) references code.exam_types (id);
alter table base.c_exam_defer_reasons add constraint c_exam_defer_reasons_code_key unique (code);

insert into base.c_exam_defer_reasons(id,code,name,begin_on,updated_at) values(1,'1','考试冲突',current_date -1,now());
insert into base.c_exam_defer_reasons(id,code,name,begin_on,updated_at) values(2,'2','因病',current_date -1,now());
insert into base.c_exam_defer_reasons(id,code,name,begin_on,updated_at) values(3,'3','因事',current_date -1,now());
insert into base.c_exam_defer_reasons(id,code,name,begin_on,updated_at) values(9,'9','其他',current_date -1,now());
alter table edu.exam_defer_applies add constraint fk_7relykc6r7k0ow927o69rerte foreign key (reason_id) references base.c_exam_defer_reasons (id);
alter table edu.exam_defer_applies add constraint fk_hpaeb0tt59knpaur4tjl3wp1j foreign key (std_id) references base.students (id);
alter table edu.exam_defer_applies add constraint fk_h43h7piw9ij2700s7jd4ngah4 foreign key (exam_type_id) references code.exam_types (id);
alter table edu.exam_defer_applies add constraint fk_t3i6ug1y90qmvjdeocdt8f7ds foreign key (clazz_id) references edu.clazzes (id);
alter table edu.exam_defer_applies add constraint uk_9jj3qou7fs4flo140oqk44w4y unique (std_id,clazz_id,exam_type_id);

--更改校外成绩认定免修表名
alter table edu.exch_exempt_applies rename to extern_exempt_applies;
alter table edu.exch_exempt_credits rename to extern_exempt_credits;
alter table cfg.edu_cert_exempt_settings add column score_expr varchar(100);
alter table cfg.edu_cert_exempt_settings add column subjects varchar(500);
alter table edu.cert_exempt_applies add column subject varchar(200);
alter table edu.certificate_grades alter subject type varchar(80);

--教学秘书-干部
create table base.officials (id bigint not null, begin_on date not null, department_id integer not null, duty varchar(100) not null, end_on date, parttime boolean default false not null, staff_id bigint not null);
create table base.secretaries (id bigint not null, begin_on date not null, end_on date, office_addr varchar(100), office_email varchar(100), office_phone varchar(50), staff_id bigint not null);
create table base.secretaries_projects (project_id integer not null, secretary_id bigint not null);

alter table base.officials add constraint pk_qvktne4gnlja38gqv0dh07sgx primary key (id);
alter table base.secretaries add constraint pk_odcqp0n1ah7oa1cwl4qtwvn9a primary key (id);
alter table base.secretaries_projects add constraint pk_357if8u51k232ik8fttkgq0j1 primary key (secretary_id,project_id);
alter table base.officials add constraint fk_ehtnbb858qli3rh3c3vklahci foreign key (staff_id) references base.staffs (id);
alter table base.officials add constraint fk_aj2by4og6cnn9vkvffmngr77g foreign key (department_id) references base.departments (id);
alter table base.secretaries add constraint fk_t22af1eygk5b942whkte0ldoq foreign key (staff_id) references base.staffs (id);
alter table base.secretaries_projects add constraint fk_gcpfs8f8p5i6d24j2wl0vvn3w foreign key (secretary_id) references base.secretaries (id);
alter table base.secretaries_projects add constraint fk_2tbh920jwj61fnm05vr4tdfbb foreign key (project_id) references base.projects (id);

create index idx_4751fgfwuvlgoag1als5q4hm2 on base.secretaries_projects (secretary_id);

--people
alter table base.people rename column formated_name to formatted_name;

--注释
comment on table base.c_exam_defer_reasons is '缓考原因@edu.code';
comment on column base.c_exam_defer_reasons.id is '非业务主键:code';
comment on column base.c_exam_defer_reasons.begin_on is '生效日期';
comment on column base.c_exam_defer_reasons.code is '代码';
comment on column base.c_exam_defer_reasons.en_name is '英文名称';
comment on column base.c_exam_defer_reasons.end_on is '失效日期';
comment on column base.c_exam_defer_reasons.name is '名称';
comment on column base.c_exam_defer_reasons.remark is '备注';
comment on column base.c_exam_defer_reasons.updated_at is '修改时间';
comment on table base.officials is '领导干部@hr';
comment on column base.officials.id is '非业务主键:datetime';
comment on column base.officials.begin_on is '生效日期';
comment on column base.officials.duty is '行政职务';
comment on column base.officials.end_on is '失效日期';
comment on column base.officials.staff_id is '教职工信息ID';
comment on table base.secretaries is '教学秘书@hr';
comment on column base.secretaries.id is '非业务主键:datetime';
comment on column base.secretaries.begin_on is '生效日期';
comment on column base.secretaries.end_on is '失效日期';
comment on column base.secretaries.office_addr is '办公室地址';
comment on column base.secretaries.office_email is '办公邮件';
comment on column base.secretaries.office_phone is '办公电话';
comment on column base.secretaries.staff_id is '教职工信息ID';
comment on table cfg.edu_exam_defer_settings is '缓考设置@exam.config';
comment on column cfg.edu_exam_defer_settings.id is '非业务主键:datetime';
comment on column cfg.edu_exam_defer_settings.apply_opened is '是否开放申请';
comment on column cfg.edu_exam_defer_settings.days_before_apply is '申请提前量(天)';
comment on column cfg.edu_exam_defer_settings.exam_type_id is '考试类型ID';
comment on column cfg.edu_exam_defer_settings.project_id is '项目ID';
comment on table cfg.edu_cert_exempt_settings is '校外证书免修科目设置';
comment on column cfg.edu_cert_exempt_settings.score_expr is '认定成绩表达式';
comment on column cfg.edu_cert_exempt_settings.subjects is '证书内科目';
comment on table edu.exam_defer_applies is '考试延期申请@exam';
comment on column edu.exam_defer_applies.id is '非业务主键:datetime';
comment on column edu.exam_defer_applies.clazz_id is '教学任务ID';
comment on column edu.exam_defer_applies.exam_begin_at is '考试开始时间';
comment on column edu.exam_defer_applies.exam_type_id is '考试类型ID';
comment on column edu.exam_defer_applies.mobile is '移动电话';
comment on column edu.exam_defer_applies.passed is '是否通过';
comment on column edu.exam_defer_applies.reason_id is '申请原因ID';
comment on column edu.exam_defer_applies.remark is '备注';
comment on column edu.exam_defer_applies.status is '状态';
comment on column edu.exam_defer_applies.std_id is '学籍信息实现ID';
comment on column edu.exam_defer_applies.updated_at is '更新时间';
comment on table edu.extern_exempt_applies is '外校成绩免修申请@exempt';
comment on column edu.extern_exempt_applies.id is '非业务主键:datetime';
comment on column edu.extern_exempt_applies.audit_opinion is '审核意见';
comment on column edu.extern_exempt_applies.credits is '申请冲抵的外校课程学分总计';
comment on column edu.extern_exempt_applies.exemption_credits is '冲抵本校课程的学分总计';
comment on column edu.extern_exempt_applies.extern_student_id is '外校学习经历ID';
comment on column edu.extern_exempt_applies.status is '审核状态';
comment on column edu.extern_exempt_applies.transcript_path is '成绩单附件路径';
comment on column edu.extern_exempt_applies.updated_at is '更新时间';
comment on table edu.extern_exempt_credits is '外校成绩免修学分上限@exempt';
comment on column edu.extern_exempt_credits.id is '非业务主键:datetime';
comment on column edu.extern_exempt_credits.exempted is '已免修学分数';
comment on column edu.extern_exempt_credits.max_value is '免修学分总和的最大值';
comment on column edu.extern_exempt_credits.remark is '备注';
comment on column edu.extern_exempt_credits.std_id is '学籍信息实现ID';
comment on column edu.extern_exempt_credits.updated_at is '更新时间';
comment on table edu.std_leave_files is '学生请假附件@attendance';
comment on column edu.std_leave_files.id is '非业务主键:datetime';
comment on column edu.std_leave_files.file_path is '附件路径';
comment on column edu.std_leave_files.leave_id is '学生请假ID';
comment on table edu.std_leave_lessons is '学生课程请假记录@attendance';
comment on column edu.std_leave_lessons.id is '非业务主键:datetime';
comment on column edu.std_leave_lessons.clazz_id is '教学任务ID';
comment on column edu.std_leave_lessons.leave_id is '学生请假ID';
comment on column edu.std_leave_lessons.leave_type is '请假类型';
comment on column edu.std_leave_lessons.lesson_on is '上课日期';
comment on column edu.std_leave_lessons.lesson_time is '上课时间';
comment on column edu.std_leave_lessons.semester_id is '学年学期ID';
comment on column edu.std_leave_lessons.std_id is '学籍信息实现ID';
comment on column edu.cert_exempt_applies.subject is '科目';
comment on column edu.std_leaves.days is '请假天数';
comment on column edu.std_leaves.lessons is '影响上课次数';
