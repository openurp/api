insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.37.0',now(),'重构基础代码，历史数据信息');

alter table edu.c_certificate_categories set schema code;
alter table code.c_certificate_categories rename to certificate_categories;

alter table edu.c_certificates  set schema code;
alter table code.c_certificates rename to certificates;

alter table edu.c_clazz_tags  set schema code;
alter table code.c_clazz_tags rename to clazz_tags;

alter table base.c_book_award_types set schema code;
alter table code.c_book_award_types rename to book_award_types;

alter table base.c_book_types set schema code;
alter table code.c_book_types rename to book_types;

alter table base.c_course_ability_rates set schema code;
alter table code.c_course_ability_rates rename to course_ability_rates;

alter table base.c_course_categories set schema code;
alter table code.c_course_categories rename to course_categories;

alter table base.c_course_types set schema code;
alter table code.c_course_types rename to course_types;

alter table base.c_education_types set schema code;
alter table code.c_education_types rename to education_types;

alter table base.c_exam_defer_reasons set schema code;
alter table code.c_exam_defer_reasons rename to exam_defer_reasons;

alter table base.c_fee_types set schema code;
alter table code.c_fee_types rename to fee_types;

alter table base.c_std_label_types set schema code;
alter table code.c_std_label_types rename to std_label_types;

alter table base.c_std_labels set schema code;
alter table code.c_std_labels rename to std_labels;

alter table base.c_std_types set schema code;
alter table code.c_std_types rename to std_types;

alter table base.c_rd_achievement_types set schema code;
alter table code.c_rd_achievement_types rename to rd_achievement_types;

alter table base.c_rd_award_grades set schema code;
alter table code.c_rd_award_grades rename to rd_award_grades;

alter table base.c_rd_levels set schema code;
alter table code.c_rd_levels rename to rd_levels;

alter table base.c_rd_project_categories set schema code;
alter table code.c_rd_project_categories rename to rd_project_categories;

alter table base.c_rd_project_statuses set schema code;
alter table code.c_rd_project_statuses rename to rd_project_statuses;

--alternative courses
alter table edu.major_alt_courses_news rename column major_alternative_course_id to major_alt_course_id;
alter table edu.major_alt_courses_olds rename column major_alternative_course_id to major_alt_course_id;
alter table edu.std_alt_courses_news rename column std_alternative_course_id to std_alt_course_id;
alter table edu.std_alt_courses_olds rename column std_alternative_course_id to std_alt_course_id;
alter table edu.std_year_gpas alter school_year type varchar(10);
alter table edu.major_alt_courses_news drop constraint if exists pk_q055pvumg14plkk3p3dyb4v5o cascade;
alter table edu.major_alt_courses_news add constraint pk_q055pvumg14plkk3p3dyb4v5o primary key (major_alt_course_id,course_id);
alter table edu.major_alt_courses_olds drop constraint if exists pk_tfl42phv9abi60dnauh3cgtx5 cascade;
alter table edu.major_alt_courses_olds add constraint pk_tfl42phv9abi60dnauh3cgtx5 primary key (major_alt_course_id,course_id);
alter table edu.std_alt_courses_news drop constraint if exists pk_ff9ka4gw208d53tn5bmg8v3yo cascade;
alter table edu.std_alt_courses_news add constraint pk_ff9ka4gw208d53tn5bmg8v3yo primary key (std_alt_course_id,course_id);
alter table edu.std_alt_courses_olds drop constraint if exists pk_9lvrx45u53eynsj63a4c345rl cascade;
alter table edu.std_alt_courses_olds add constraint pk_9lvrx45u53eynsj63a4c345rl primary key (std_alt_course_id,course_id);
alter table edu.major_alt_courses_news drop constraint if exists fk_60va5eeqjkq8v11ccjaebj37l cascade;
alter table edu.major_alt_courses_news add constraint fk_q74bnkaxqtuleop0lyw1w1xeg foreign key (major_alt_course_id) references edu.major_alt_courses (id);
alter table edu.major_alt_courses_olds drop constraint if exists fk_jfmmnwo9lc6wi48fpmbkuhru7 cascade;
alter table edu.major_alt_courses_olds add constraint fk_f488kjk8bfxelb3ob2m081k5r foreign key (major_alt_course_id) references edu.major_alt_courses (id);
alter table edu.std_alt_courses_news drop constraint if exists fk_3598npm3ybogfvypjbqkdhlsm cascade;
alter table edu.std_alt_courses_news add constraint fk_kf2ddu7dt808aivpblhcrxrmy foreign key (std_alt_course_id) references edu.std_alt_courses (id);
alter table edu.std_alt_courses_olds drop constraint if exists fk_88jtb7a3cqnhssy92wmgd7bsg cascade;
alter table edu.std_alt_courses_olds add constraint fk_n7rxknr6pcsfqb2yxr1krt01h foreign key (std_alt_course_id) references edu.std_alt_courses (id);
create index idx_9l1h3gh5tajelrpwlja4rvoeu on edu.course_takers (semester_id);
drop index edu.idx_8s5d0wiirdi101qp238i6ovru;
create index idx_hnm0j0sfpkc5gqn2s07ln80th on edu.major_alt_courses (project_id);
drop index edu.idx_4hvjp5b9sm4mb3gn32sdn86ch;
create index idx_afx0rbkspks71gaexr83lu1oo on edu.major_alt_courses_news (major_alt_course_id);
drop index edu.idx_2v1a5cnldp9ct2bepe4510lry;
create index idx_l2bh1c8bo4hsur07k9yo62lu8 on edu.major_alt_courses_olds (major_alt_course_id);
drop index edu.idx_mstilk5giawk7vh78kv73x7tr;
create index idx_qayu145ivc7x931n23vnaxskr on edu.std_alt_courses (std_id);
drop index edu.idx_1swj196xyqjha21237i35824w;
create index idx_oqnutavt0ni5equ6cxmx15yv9 on edu.std_alt_courses_news (std_alt_course_id);
drop index edu.idx_xtxet4oto3m3lt6fkyq6l9vj;
create index idx_limcsyiq30whrglyd56hbsv1a on edu.std_alt_courses_olds (std_alt_course_id);

--his models
create schema his;
create table his.edu_course_grades (semester_id integer not null, course_id bigint not null, project_id integer not null, score float4, id bigint not null, remark varchar(200), crn varchar(20) not null, gp float4, score_text varchar(5), free_listening boolean default false not null, course_type_id integer not null, provider varchar(80), clazz_id bigint, passed boolean default false not null, std_id bigint not null, exam_mode_id integer not null, operator varchar(100), status integer default 0 not null, grading_mode_id integer not null, created_at timestamptz not null, course_take_type_id integer not null, school_year integer not null, updated_at timestamptz default current_timestamp not null);
create table his.edu_course_takers (course_id bigint not null, course_type_id integer not null, clazz_id bigint not null, std_id bigint not null, alternative boolean default false not null, id bigint not null, take_type_id integer not null, free_listening boolean default false not null, subclazz_id bigint, semester_id integer not null, election_mode_id integer not null, remark varchar(100), school_year integer not null, updated_at timestamptz default current_timestamp not null);
create table his.edu_exam_grades (exam_status_id integer not null, score float4, passed boolean default false not null, id bigint not null, score_percent smallint, grade_type_id integer not null, course_grade_id bigint not null, status integer default 0 not null, created_at timestamptz not null, grading_mode_id integer not null, school_year integer not null, score_text varchar(5), operator varchar(100), updated_at timestamptz default current_timestamp not null);
create table his.edu_exam_takers (semester_id integer not null, exam_status_id integer not null, clazz_id bigint not null, exam_type_id integer not null, std_id bigint not null, id bigint not null, remark varchar(255), seat_no smallint default 0 not null, activity_id bigint, exam_room_id bigint, school_year integer not null);
create table his.edu_ga_grades (score float4, passed boolean default false not null, id bigint not null, remark varchar(255), gp float4, grade_type_id integer not null, course_grade_id bigint not null, status integer default 0 not null, delta float4, created_at timestamptz not null, grading_mode_id integer not null, school_year integer not null, score_text varchar(5), operator varchar(100), updated_at timestamptz default current_timestamp not null);
create table his.edu_regular_grades (clazz_id bigint not null, score float4 not null, std_id bigint not null, id bigint not null, status integer default 0 not null, school_year integer not null, updated_at timestamptz default current_timestamp not null);
create table his.edu_regular_test_grades (regular_grade_id bigint not null, score integer default 0 not null, score_percent integer default 0 not null, test_type_id integer not null, id bigint not null, school_year integer not null);

alter table his.edu_course_grades add constraint pk_chti3605ct02gr38n41fa3w2r primary key (id,school_year);
alter table his.edu_course_takers add constraint pk_ft65qhx6or053wtcbkflg9kuw primary key (id,school_year);
alter table his.edu_exam_grades add constraint pk_51dkrdiskm07r9s0g0r5mv1ei primary key (id,school_year);
alter table his.edu_exam_takers add constraint pk_s7u6opjyel7xwlqxxavdnikq3 primary key (id,school_year);
alter table his.edu_ga_grades add constraint pk_jmcwmshd9usxt264y2pyj8wt4 primary key (id,school_year);
alter table his.edu_regular_grades add constraint pk_lliu205c73xio4nmhoawmok7t primary key (id,school_year);
alter table his.edu_regular_test_grades add constraint pk_bafso9a1qa1fb98sjcgaaohkq primary key (id,school_year);
create index idx_4qtd3v5e09e2i9bck3oakp4yr on his.edu_course_grades (clazz_id);
create index idx_5jetp4mnl4e0wv0xf65xpolo7 on his.edu_course_grades (std_id);
create index idx_mhoqx05hw4fqqupyfa3wf6sb2 on his.edu_course_grades (project_id);
create index idx_3pvbkxqc36l8ngt03321vf1hy on his.edu_course_takers (clazz_id);
create index idx_senti97jh80q5xhh9q5rkvknd on his.edu_course_takers (semester_id);
create index idx_6qs3494fnm7ifknq96a1b8r89 on his.edu_exam_grades (course_grade_id);
create index idx_bqer3r9thk2y3grmr8xalkp9u on his.edu_exam_takers (exam_room_id);
create index idx_peop4o043mp7t9vik78bkwnnc on his.edu_exam_takers (clazz_id);
create index idx_7jjbv89v7xsd97ul68dg75q48 on his.edu_ga_grades (course_grade_id);
create index idx_ge26c8oiqt7qus9se6lhkm1lm on his.edu_regular_grades (clazz_id);
create index idx_qveyhipxc0xk5wlfghb0ypuoe on his.edu_regular_grades (std_id);
create index idx_cgqdfncp4cyc44didyefmiem2 on his.edu_regular_test_grades (regular_grade_id);

--device
create table base.devices (id bigint not null, device_type_id integer not null, ip varchar(40), name varchar(40) not null, remark varchar(100), room_id bigint, updated_at timestamptz default current_timestamp not null, uuid varchar(255));
create table code.device_types (id integer not null, begin_on date not null, code varchar(20) not null, en_name varchar(300), end_on date, name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);
create table code.std_doc_archive_types (id integer not null, begin_on date not null, code varchar(20) not null, en_name varchar(300), end_on date, name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);
create table std.std_doc_archives (id bigint not null, doc_type_id integer not null, file_path varchar(200) not null, file_size integer default 0 not null, remark varchar(255), std_id bigint not null, updated_at timestamptz default current_timestamp not null);
alter table base.devices add constraint pk_q34m206q6wm6qh5d14b281wo9 primary key (id);
alter table code.device_types add constraint pk_av3t8mqkliomcbnbg38tcllfe primary key (id);
alter table code.std_doc_archive_types add constraint pk_a65gaefr5yvww0tspce06vf1b primary key (id);
alter table std.std_doc_archives add constraint pk_ipo0wobilk95gxr34trymdybe primary key (id);
alter table base.devices add constraint fk_thsup9yv35eehh6hkt0jj3naw foreign key (device_type_id) references code.device_types (id);
alter table base.devices add constraint fk_dnkla0ytc6j9494sxoe0gib62 foreign key (room_id) references base.classrooms (id);
alter table std.std_doc_archives add constraint fk_53axo13rebn8oau3miwwmaixr foreign key (std_id) references base.students (id);
alter table std.std_doc_archives add constraint fk_721choq2tdmoa9bws1pbdor5y foreign key (doc_type_id) references code.std_doc_archive_types (id);
alter table code.device_types add constraint device_types_code_key unique (code);
alter table code.std_doc_archive_types add constraint std_doc_archive_types_code_key unique (code);
create index idx_ovshoc5u1nrmelxy454pix0gm on base.devices (room_id);

alter table degree.guidances drop constraint if exists uk_o1tr5322a778ojj610im4awwf cascade;
create index idx_o1tr5322a778ojj610im4awwf on degree.guidances (writer_id);

comment on table base.devices is '教室设备@resource';
comment on column base.devices.id is '非业务主键:datetime';
comment on column base.devices.device_type_id is '教学仪器设备产品ID';
comment on column base.devices.ip is 'IP地址';
comment on column base.devices.name is '名称';
comment on column base.devices.remark is '备注';
comment on column base.devices.room_id is '教室ID';
comment on column base.devices.updated_at is '更新时间';
comment on column base.devices.uuid is 'UUID';
comment on table code.device_types is '教学仪器设备产品@asset';
comment on column code.device_types.id is '非业务主键:code';
comment on column code.device_types.begin_on is '生效日期';
comment on column code.device_types.code is '代码';
comment on column code.device_types.en_name is '英文名称';
comment on column code.device_types.end_on is '失效日期';
comment on column code.device_types.name is '名称';
comment on column code.device_types.remark is '备注';
comment on column code.device_types.updated_at is '修改时间';
comment on table code.std_doc_archive_types is '学生文档归档类型@std';
comment on column code.std_doc_archive_types.id is '非业务主键:code';
comment on column code.std_doc_archive_types.begin_on is '生效日期';
comment on column code.std_doc_archive_types.code is '代码';
comment on column code.std_doc_archive_types.en_name is '英文名称';
comment on column code.std_doc_archive_types.end_on is '失效日期';
comment on column code.std_doc_archive_types.name is '名称';
comment on column code.std_doc_archive_types.remark is '备注';
comment on column code.std_doc_archive_types.updated_at is '修改时间';
comment on table std.std_doc_archives is '学生文档归档记录@std.archive';
comment on column std.std_doc_archives.id is '非业务主键:datetime';
comment on column std.std_doc_archives.doc_type_id is '学生文档归档类型ID';
comment on column std.std_doc_archives.file_path is '归档路径';
comment on column std.std_doc_archives.file_size is '文件大小';
comment on column std.std_doc_archives.remark is '备注';
comment on column std.std_doc_archives.std_id is '学籍信息实现ID';
comment on column std.std_doc_archives.updated_at is '更新时间';


comment on column his.edu_course_grades.clazz_id is '教学任务ID';
comment on column his.edu_course_grades.course_id is '课程ID';
comment on column his.edu_course_grades.course_take_type_id is '修读类别ID';
comment on column his.edu_course_grades.course_type_id is '课程类别ID';
comment on column his.edu_course_grades.created_at is '创建时间';
comment on column his.edu_course_grades.crn is '课程序号';
comment on column his.edu_course_grades.exam_mode_id is '考核方式ID';
comment on column his.edu_course_grades.free_listening is '是否免听';
comment on column his.edu_course_grades.gp is '绩点';
comment on column his.edu_course_grades.grading_mode_id is '成绩记录方式ID';
comment on column his.edu_course_grades.id is '非业务主键:assigned';
comment on column his.edu_course_grades.operator is '操作者';
comment on column his.edu_course_grades.passed is '是否通过';
comment on column his.edu_course_grades.project_id is '项目ID';
comment on column his.edu_course_grades.provider is '成绩来源';
comment on column his.edu_course_grades.remark is '备注';
comment on column his.edu_course_grades.school_year is '学年度';
comment on column his.edu_course_grades.score is '得分';
comment on column his.edu_course_grades.score_text is '得分字面值';
comment on column his.edu_course_grades.semester_id is '学期ID';
comment on column his.edu_course_grades.status is '成绩状态';
comment on column his.edu_course_grades.std_id is '学生ID';
comment on column his.edu_course_grades.updated_at is '更新时间';
comment on column his.edu_course_takers.alternative is '是否替代';
comment on column his.edu_course_takers.clazz_id is '教学任务ID';
comment on column his.edu_course_takers.course_id is '课程基本信息ID';
comment on column his.edu_course_takers.course_type_id is '课程类别ID';
comment on column his.edu_course_takers.election_mode_id is '选课方式ID';
comment on column his.edu_course_takers.free_listening is '是否免听';
comment on column his.edu_course_takers.id is '非业务主键:assigned';
comment on column his.edu_course_takers.remark is '备注';
comment on column his.edu_course_takers.school_year is '学年度';
comment on column his.edu_course_takers.semester_id is '学年学期ID';
comment on column his.edu_course_takers.std_id is '学生ID';
comment on column his.edu_course_takers.subclazz_id is '教学任务小班ID';
comment on column his.edu_course_takers.take_type_id is '修读类别ID';
comment on column his.edu_course_takers.updated_at is '更新时间';
comment on column his.edu_exam_grades.course_grade_id is '对应的课程成绩ID';
comment on column his.edu_exam_grades.created_at is '创建时间';
comment on column his.edu_exam_grades.exam_status_id is '考试情况ID';
comment on column his.edu_exam_grades.grade_type_id is '成绩类型ID';
comment on column his.edu_exam_grades.grading_mode_id is '成绩记录方式ID';
comment on column his.edu_exam_grades.id is '非业务主键:assigned';
comment on column his.edu_exam_grades.operator is '操作者';
comment on column his.edu_exam_grades.passed is '是否通过';
comment on column his.edu_exam_grades.school_year is '学年度';
comment on column his.edu_exam_grades.score is '得分';
comment on column his.edu_exam_grades.score_percent is '百分比描述';
comment on column his.edu_exam_grades.score_text is '得分字面值';
comment on column his.edu_exam_grades.status is '成绩状态';
comment on column his.edu_exam_grades.updated_at is '更新时间';
comment on column his.edu_exam_takers.activity_id is '排考活动ID';
comment on column his.edu_exam_takers.clazz_id is '教学任务ID';
comment on column his.edu_exam_takers.exam_room_id is '考场ID';
comment on column his.edu_exam_takers.exam_status_id is '考试情况ID';
comment on column his.edu_exam_takers.exam_type_id is '考试类型ID';
comment on column his.edu_exam_takers.id is '非业务主键:assigned';
comment on column his.edu_exam_takers.remark is '缓考申请原因/记录处分';
comment on column his.edu_exam_takers.school_year is '学年度';
comment on column his.edu_exam_takers.seat_no is '考场座位号';
comment on column his.edu_exam_takers.semester_id is '学期ID';
comment on column his.edu_exam_takers.std_id is '学生ID';
comment on column his.edu_ga_grades.course_grade_id is '对应的课程成绩ID';
comment on column his.edu_ga_grades.created_at is '创建时间';
comment on column his.edu_ga_grades.delta is '加减修正分';
comment on column his.edu_ga_grades.gp is '绩点';
comment on column his.edu_ga_grades.grade_type_id is '成绩类型ID';
comment on column his.edu_ga_grades.grading_mode_id is '成绩记录方式ID';
comment on column his.edu_ga_grades.id is '非业务主键:assigned';
comment on column his.edu_ga_grades.operator is '操作者';
comment on column his.edu_ga_grades.passed is '是否通过';
comment on column his.edu_ga_grades.remark is '备注';
comment on column his.edu_ga_grades.school_year is '学年度';
comment on column his.edu_ga_grades.score is '得分';
comment on column his.edu_ga_grades.score_text is '得分字面值';
comment on column his.edu_ga_grades.status is '成绩状态';
comment on column his.edu_ga_grades.updated_at is '更新时间';
comment on column his.edu_regular_grades.clazz_id is '教学任务ID';
comment on column his.edu_regular_grades.id is '非业务主键:assigned';
comment on column his.edu_regular_grades.school_year is '学年度';
comment on column his.edu_regular_grades.score is '分数';
comment on column his.edu_regular_grades.status is '状态';
comment on column his.edu_regular_grades.std_id is '学籍信息实现ID';
comment on column his.edu_regular_grades.updated_at is '更新时间';
comment on column his.edu_regular_test_grades.id is '非业务主键:assigned';
comment on column his.edu_regular_test_grades.regular_grade_id is '历史平时过程总评成绩ID';
comment on column his.edu_regular_test_grades.school_year is '学年度';
comment on column his.edu_regular_test_grades.score is '分数';
comment on column his.edu_regular_test_grades.score_percent is '百分比';
comment on column his.edu_regular_test_grades.test_type_id is '平时测试类型ID';
