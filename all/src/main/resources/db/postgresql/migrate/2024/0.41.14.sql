insert into base.versions(id,version,updated_at,description) values(next_id('base.versions'),'0.41.14',now(),'增加实践活动');

create table cfg.prac_ability_credit_configs (id bigint not null, begin_at timestamptz not null, credits integer default 0 not null, edu_type_id integer not null, end_at timestamptz not null, notice varchar(255) not null, project_id integer not null);
create table cfg.prac_ability_credit_configs_levels (ability_credit_config_id bigint not null, education_level_id integer not null);
create table cfg.prac_ability_credit_settings (id bigint not null, audit_depart_id integer, certificate_id integer not null, college_review_required boolean default false not null, config_id bigint not null, credits float4 default 0 not null, remark varchar(255), special boolean default false not null, valid_months integer);
create table cfg.prac_ability_credit_settings_departs (ability_credit_setting_id bigint not null, department_id integer not null);
create table cfg.prac_ability_credit_settings_majors (ability_credit_setting_id bigint not null, major_id bigint not null);
create table code.prac_activity_types (id integer not null, begin_on date not null, code varchar(20) not null, en_name varchar(300), end_on date, name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);
create table code.std_practice_categories (id integer not null, begin_on date not null, code varchar(20) not null, en_name varchar(300), end_on date, name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);
create table code.std_practice_types (id integer not null, begin_on date not null, category_id integer not null, code varchar(20) not null, en_name varchar(300), end_on date, name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);
create table prac.ability_credit_applies (id bigint not null, acquired_on date not null, attachment_path varchar(100) not null, audit_depart_id integer not null, audit_opinion varchar(255), certificate_id integer not null, certificate_no varchar(80), credits float4, finished boolean default false not null, reasons varchar(500), semester_id integer not null, status integer not null, std_id bigint not null, subject_cnt integer, subjects varchar(200) not null, updated_at timestamptz default current_timestamp not null);
create table prac.ability_credits (id bigint not null, course_grade_id bigint, credits float4 default 0 not null, std_id bigint not null, updated_at timestamptz default current_timestamp not null);
create table prac.prac_activities (id bigint not null, activity_name varchar(255) not null, activity_type_id integer not null, credits float4, department_id integer not null, description varchar(255) not null, extern_teacher varchar(255), project_id integer not null, semester_id integer not null, std_count integer default 0 not null);
create table prac.prac_activities_teachers (prac_activity_id bigint not null, user_id bigint not null);
create table prac.prac_activity_schedules (id bigint not null, activity_id bigint not null, begin_at smallint not null, begin_on date not null, end_at smallint not null, end_on date not null, extern_teacher varchar(255), places varchar(255) not null, remark varchar(255), start_on date not null, teaching_method_id integer not null, times varchar(255), topic varchar(255), weekstate bigint not null);
create table prac.prac_activity_schedules_teachers (prac_activity_schedule_id bigint not null, user_id bigint not null);
create table prac.prac_clazz_schedules (id bigint not null, activity_id bigint not null, begin_at smallint not null, begin_on date not null, end_at smallint not null, end_on date not null, extern_teacher varchar(255), places varchar(255) not null, remark varchar(255), start_on date not null, teaching_method_id integer not null, times varchar(255), topic varchar(255), weekstate bigint not null);
create table prac.prac_clazz_schedules_teachers (prac_clazz_schedule_id bigint not null, user_id bigint not null);
create table prac.prac_clazzes (id bigint not null, clazz_name varchar(255) not null, course_id bigint, course_name varchar(255) not null, course_type_id integer, credits float4, department_id integer not null, extern_teacher varchar(255), lang_type_id integer not null, project_id integer not null, semester_id integer not null, std_count integer default 0 not null);
create table prac.prac_clazzes_teachers (prac_clazz_id bigint not null, user_id bigint not null);
create table prac.std_practice_hours (id bigint not null, category_id integer not null, hours integer default 0 not null, std_id bigint not null, updated_at timestamptz default current_timestamp not null);
create table prac.std_practice_infoes (id bigint not null, category_id integer not null, datetime varchar(255), hours integer default 0 not null, name varchar(255) not null, place varchar(100), practice_type_id integer not null, remark varchar(100), std_id bigint not null, updated_at timestamptz default current_timestamp not null);

alter table prac.std_practice_hours add column course_grade_id bigint;
alter table prac.std_practice_hours add column required_hours integer default 0;
alter table prac.std_practice_hours alter required_hours set not null;

alter table cfg.std_audit_settings alter drule_ids type varchar(500);
alter table cfg.std_audit_settings alter grule_ids type varchar(500);
alter table cfg.prac_ability_credit_configs add constraint pk_q7ppm0ehkq9web8a8gyhbsmxg primary key (id);
alter table cfg.prac_ability_credit_configs_levels add constraint pk_phhxtljaprkioq86sfhc5ltto primary key (ability_credit_config_id,education_level_id);
alter table cfg.prac_ability_credit_settings add constraint pk_nmkfo90wlehg370nok9dlyeln primary key (id);
alter table cfg.prac_ability_credit_settings_departs add constraint pk_rrijg4or8b4gim6fnogh8q4vj primary key (ability_credit_setting_id,department_id);
alter table cfg.prac_ability_credit_settings_majors add constraint pk_sulshfuifdasduxm70lnvxx1r primary key (ability_credit_setting_id,major_id);
alter table code.prac_activity_types add constraint pk_1280lnc80k9gw3rpx92i02qhp primary key (id);
alter table code.std_practice_categories add constraint pk_plovwy97rxnw2kxt8ew0mri5s primary key (id);
alter table code.std_practice_types add constraint pk_gjdq9tpb799gj5hgk7aqw2c48 primary key (id);
alter table prac.ability_credit_applies add constraint pk_jrx2ap3o6wenlo8879ife098j primary key (id);
alter table prac.ability_credits add constraint pk_94rnfwyukm6a9ch8lh4b24jtu primary key (id);
alter table prac.prac_activities add constraint pk_jclfmqktn7gbydoinimflkuge primary key (id);
alter table prac.prac_activities_teachers add constraint pk_m6rcxc6wq5ladivqpne64hrg9 primary key (prac_activity_id,user_id);
alter table prac.prac_activity_schedules add constraint pk_en5y6abajte148s2vp97tbye7 primary key (id);
alter table prac.prac_activity_schedules_teachers add constraint pk_6sbuukfwaggk7mw12rhcvkh3m primary key (prac_activity_schedule_id,user_id);
alter table prac.prac_clazz_schedules add constraint pk_ths47bln2u831lk23ws7lqux3 primary key (id);
alter table prac.prac_clazz_schedules_teachers add constraint pk_4efp5dstwyj6rb2a05gtg6211 primary key (prac_clazz_schedule_id,user_id);
alter table prac.prac_clazzes add constraint pk_ahrdt1c1amwedh0v6cw76rkko primary key (id);
alter table prac.prac_clazzes_teachers add constraint pk_ktwf35buybr0fj5k0f47lvcoo primary key (prac_clazz_id,user_id);
alter table prac.std_practice_hours add constraint pk_re8dtd5sef7xvesjfjhfgayhw primary key (id);
alter table prac.std_practice_infoes add constraint pk_18vfbtkmyahbpya2j6hc90go5 primary key (id);
alter table cfg.prac_ability_credit_configs add constraint fk_hnpnj4e7vbvdi6fjcd0vtoahh foreign key (edu_type_id) references code.education_types (id);
alter table cfg.prac_ability_credit_configs add constraint fk_66b8b88y1rhgjmbykc4fanaj2 foreign key (project_id) references base.projects (id);
alter table cfg.prac_ability_credit_configs_levels add constraint fk_1pivofma8ptl9lnue2qwq5ih1 foreign key (ability_credit_config_id) references cfg.prac_ability_credit_configs (id);
alter table cfg.prac_ability_credit_configs_levels add constraint fk_5nc52csjldidonqquope830e5 foreign key (education_level_id) references code.education_levels (id);
alter table cfg.prac_ability_credit_settings add constraint fk_dsjnwjx5y4t8dautxnmcvy5p5 foreign key (audit_depart_id) references base.departments (id);
alter table cfg.prac_ability_credit_settings add constraint fk_tgj49l6vv9e45bxc4l0c7jxfb foreign key (certificate_id) references code.certificates (id);
alter table cfg.prac_ability_credit_settings add constraint fk_80ramn6un2kwnkg8cg6p0gsj8 foreign key (config_id) references cfg.prac_ability_credit_configs (id);
alter table cfg.prac_ability_credit_settings_departs add constraint fk_8j1jy8s227hghchm381xi9kaq foreign key (ability_credit_setting_id) references cfg.prac_ability_credit_settings (id);
alter table cfg.prac_ability_credit_settings_departs add constraint fk_rccu5dxn6oot4d88c3y2fes8h foreign key (department_id) references base.departments (id);
alter table cfg.prac_ability_credit_settings_majors add constraint fk_2xoby5glg4f362igy1nb7m53s foreign key (ability_credit_setting_id) references cfg.prac_ability_credit_settings (id);
alter table cfg.prac_ability_credit_settings_majors add constraint fk_2t94hfdckgc022x658hrwto3b foreign key (major_id) references base.majors (id);
alter table code.std_practice_types add constraint fk_iq0202kvmg12e4g8in8uyq3wo foreign key (category_id) references code.std_practice_categories (id);
alter table prac.ability_credit_applies add constraint fk_32fokldnldp8y1koiqkyf2qpb foreign key (audit_depart_id) references base.departments (id);
alter table prac.ability_credit_applies add constraint fk_fkudkyhtsh2vhpv3xd63u3283 foreign key (std_id) references base.students (id);
alter table prac.ability_credit_applies add constraint fk_dllpy0vrhasggn9qfmj5scb78 foreign key (certificate_id) references code.certificates (id);
alter table prac.ability_credit_applies add constraint fk_573nmlny5j6v2dbuwr2gbysxn foreign key (semester_id) references base.semesters (id);
alter table prac.ability_credits add constraint fk_5g7yxjgu459avs4u0oi0q6wvy foreign key (std_id) references base.students (id);
alter table prac.prac_activities add constraint fk_c4bsxhdo0jtabw57h2ird9qqu foreign key (project_id) references base.projects (id);
alter table prac.prac_activities add constraint fk_4pg9jxejy64vv9mjrv1bsxesu foreign key (semester_id) references base.semesters (id);
alter table prac.prac_activities add constraint fk_ng2g7iqmxh7j49h3rnr1hh50p foreign key (department_id) references base.departments (id);
alter table prac.prac_activities add constraint fk_isv2ox2hb768emrxjrmlccu8u foreign key (activity_type_id) references code.prac_activity_types (id);
alter table prac.prac_activities_teachers add constraint fk_sbrmcefo1k7ge040byomyurxu foreign key (prac_activity_id) references prac.prac_activities (id);
alter table prac.prac_activities_teachers add constraint fk_r19n18fgti2v2kcax3vf397qf foreign key (user_id) references base.users (id);
alter table prac.prac_activity_schedules add constraint fk_ehfpf868ykup0ua4k2f3xk71h foreign key (activity_id) references prac.prac_activities (id);
alter table prac.prac_activity_schedules add constraint fk_mpvvellaugmncxcu7l906l73j foreign key (teaching_method_id) references code.teaching_methods (id);
alter table prac.prac_activity_schedules_teachers add constraint fk_et0ft6qvkgursin02xcd582do foreign key (prac_activity_schedule_id) references prac.prac_activity_schedules (id);
alter table prac.prac_activity_schedules_teachers add constraint fk_kn8meyebu2xv6ffc1alwpgi9m foreign key (user_id) references base.users (id);
alter table prac.prac_clazz_schedules add constraint fk_4trjfdfsffrmxjm5lsi5iso9l foreign key (activity_id) references prac.prac_clazzes (id);
alter table prac.prac_clazz_schedules add constraint fk_6828lk3f80ecupmsynewxnavo foreign key (teaching_method_id) references code.teaching_methods (id);
alter table prac.prac_clazz_schedules_teachers add constraint fk_c87nuj5ni77hscromf0ootkjw foreign key (prac_clazz_schedule_id) references prac.prac_clazz_schedules (id);
alter table prac.prac_clazz_schedules_teachers add constraint fk_8d5mu8w5tyhw8id11oxvuhkf7 foreign key (user_id) references base.users (id);
alter table prac.prac_clazzes add constraint fk_e90tpvxp8gm4htpvxvuqc05dd foreign key (course_type_id) references code.course_types (id);
alter table prac.prac_clazzes add constraint fk_tpe1gh13nmdxiqp6lhjf9eiw2 foreign key (project_id) references base.projects (id);
alter table prac.prac_clazzes add constraint fk_hrdcy3cqyo6jl7qmgfi0jk6rx foreign key (lang_type_id) references code.teach_lang_types (id);
alter table prac.prac_clazzes add constraint fk_80drhdh0bou9tno01wt3x4j9d foreign key (course_id) references base.courses (id);
alter table prac.prac_clazzes add constraint fk_42codv4qbqywkp9yyyfphgjwh foreign key (semester_id) references base.semesters (id);
alter table prac.prac_clazzes add constraint fk_kxsvh4eu5mxdm5o03fngyrn2o foreign key (department_id) references base.departments (id);
alter table prac.prac_clazzes_teachers add constraint fk_18sg1dqds95gb88scjlgeat11 foreign key (prac_clazz_id) references prac.prac_clazzes (id);
alter table prac.prac_clazzes_teachers add constraint fk_72gmihbglgkgu443pcf198dwx foreign key (user_id) references base.users (id);
alter table prac.std_practice_hours add constraint fk_j3hpd9ija7mcq8ibrgiwk7co3 foreign key (std_id) references base.students (id);
alter table prac.std_practice_hours add constraint fk_opk7pt9ku3etujtwalom6nove foreign key (category_id) references code.std_practice_categories (id);
alter table prac.std_practice_infoes add constraint fk_gcpstqnkqrl3st6f1liodc1t5 foreign key (std_id) references base.students (id);
alter table prac.std_practice_infoes add constraint fk_1y9bnfpxtfrev6ww154jorf9v foreign key (category_id) references code.std_practice_categories (id);
alter table prac.std_practice_infoes add constraint fk_r9n8db2kkmxyq7oe1xpuwmig9 foreign key (practice_type_id) references code.std_practice_types (id);
alter table code.prac_activity_types add constraint uk_7b25q1acdimewk5onml28197d unique (code);
alter table code.std_practice_categories add constraint uk_32haswsktuxdoii5wj30l2r8w unique (code);
alter table code.std_practice_types add constraint uk_nu2056990u2cwn9i884wcnr7m unique (code);
alter table prac.ability_credits add constraint uk_oe22t4o0pgpw8ig651ulvxs2v unique (std_id);
alter table prac.std_practice_hours add constraint uk_iy7h2lrkq2jd9misuc1ovhmir unique (std_id,category_id);
create index idx_cb33h6q49capyl5oofab3glxh on cfg.prac_ability_credit_configs_levels (ability_credit_config_id);
create index idx_9ewta2bpdnt3idfdodnbgm27v on cfg.prac_ability_credit_settings (config_id);
create index idx_rlsdjllgn11ynb2o6rybpfvad on cfg.prac_ability_credit_settings_departs (ability_credit_setting_id);
create index idx_2p44n79uehye5l8lpkn0xluiv on cfg.prac_ability_credit_settings_majors (ability_credit_setting_id);
create index idx_aj85q6ks4a0y457njlmqs09vm on prac.prac_activities_teachers (prac_activity_id);
create index idx_6xg4w1hyp21v6abxcgpoioql8 on prac.prac_activity_schedules (activity_id);
create index idx_48qfgq86msw43c7kfajdpc41v on prac.prac_activity_schedules_teachers (prac_activity_schedule_id);
create index idx_4gesjj1pmtwcuc3np7c5ldjsv on prac.prac_clazz_schedules (activity_id);
create index idx_o0dh84ppn5lx2r5ymdbgtgnt5 on prac.prac_clazz_schedules_teachers (prac_clazz_schedule_id);
create index idx_tji6brc59o4a8evrhgxx6gnil on prac.prac_clazzes_teachers (prac_clazz_id);


create table code.clazz_archive_docs (id integer not null, begin_on date not null, code varchar(20) not null, en_name varchar(300), end_on date, name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);
create table edu.clazz_archives (id bigint not null, archived_by_id bigint not null, clazz_id bigint not null, doc_id integer not null, doc_path varchar(400) not null, doc_size integer default 0 not null, updated_at timestamptz default current_timestamp not null);
alter table code.clazz_archive_docs add constraint pk_rdl7dyua7mq986uedf1tuo1sg primary key (id);
alter table edu.clazz_archives add constraint pk_svxcnumv1r9p24ymun0hsdg28 primary key (id);
alter table edu.clazz_archives add constraint fk_adclnnepw3n885amwtsltah8r foreign key (doc_id) references code.clazz_archive_docs (id);
alter table edu.clazz_archives add constraint fk_5rqvebyop21a4uouypp85qet9 foreign key (archived_by_id) references base.users (id);
alter table edu.clazz_archives add constraint fk_ebr6602e41pv8ybnuwwla44ol foreign key (clazz_id) references edu.clazzes (id);
alter table code.clazz_archive_docs add constraint uk_19qhsalo7xubo3jutx8o5htmh unique (code);
alter table edu.clazz_archives add constraint uk_caluyc9lm5rlj5628fenvr9a unique (clazz_id,doc_id);
comment on table code.clazz_archive_docs is '课程规章资料文档类型@edu';
comment on column code.clazz_archive_docs.id is '非业务主键:code';
comment on column code.clazz_archive_docs.begin_on is '生效日期';
comment on column code.clazz_archive_docs.code is '代码';
comment on column code.clazz_archive_docs.en_name is '英文名称';
comment on column code.clazz_archive_docs.end_on is '失效日期';
comment on column code.clazz_archive_docs.name is '名称';
comment on column code.clazz_archive_docs.remark is '备注';
comment on column code.clazz_archive_docs.updated_at is '修改时间';
comment on table edu.clazz_archives is '课程资料归档@course';
comment on column edu.clazz_archives.id is '非业务主键:datetime';
comment on column edu.clazz_archives.archived_by_id is '归档人ID';
comment on column edu.clazz_archives.clazz_id is '教学任务ID';
comment on column edu.clazz_archives.doc_id is '课程规章资料文档类型ID';
comment on column edu.clazz_archives.doc_path is '存储路径';
comment on column edu.clazz_archives.doc_size is '文件大小';
comment on column edu.clazz_archives.updated_at is '更新时间';


comment on table base.students is '学生';
comment on table cfg.prac_ability_credit_configs is '能力拓展配置@ability.config';
comment on column cfg.prac_ability_credit_configs.id is '非业务主键:datetime';
comment on column cfg.prac_ability_credit_configs.begin_at is '开始时间';
comment on column cfg.prac_ability_credit_configs.credits is '认定的学分';
comment on column cfg.prac_ability_credit_configs.edu_type_id is '培养类型ID';
comment on column cfg.prac_ability_credit_configs.end_at is '结束时间';
comment on column cfg.prac_ability_credit_configs.notice is '通知';
comment on column cfg.prac_ability_credit_configs.project_id is '项目ID';
comment on table cfg.prac_ability_credit_configs_levels is '能力拓展配置-培养类型@ability.config';
comment on column cfg.prac_ability_credit_configs_levels.ability_credit_config_id is '能力拓展配置ID';
comment on column cfg.prac_ability_credit_configs_levels.education_level_id is '培养层次ID';
comment on table cfg.prac_ability_credit_settings is '能力拓展学分证书配置@ability.config';
comment on column cfg.prac_ability_credit_settings.id is '非业务主键:datetime';
comment on column cfg.prac_ability_credit_settings.audit_depart_id is '审核学院ID';
comment on column cfg.prac_ability_credit_settings.certificate_id is '校外证书ID';
comment on column cfg.prac_ability_credit_settings.college_review_required is '是否需要学院审核';
comment on column cfg.prac_ability_credit_settings.config_id is '能力拓展配置ID';
comment on column cfg.prac_ability_credit_settings.credits is '学分数';
comment on column cfg.prac_ability_credit_settings.remark is '备注';
comment on column cfg.prac_ability_credit_settings.special is '学科专业特定还是全校通用';
comment on column cfg.prac_ability_credit_settings.valid_months is '有效期长度';
comment on table cfg.prac_ability_credit_settings_departs is '能力拓展学分证书配置-面向学院@ability.config';
comment on column cfg.prac_ability_credit_settings_departs.ability_credit_setting_id is '能力拓展学分证书配置ID';
comment on column cfg.prac_ability_credit_settings_departs.department_id is '部门组织机构信息ID';
comment on table cfg.prac_ability_credit_settings_majors is '能力拓展学分证书配置-面向专业@ability.config';
comment on column cfg.prac_ability_credit_settings_majors.ability_credit_setting_id is '能力拓展学分证书配置ID';
comment on column cfg.prac_ability_credit_settings_majors.major_id is '专业ID';
comment on table code.prac_activity_types is '实践课程类型@prac';
comment on column code.prac_activity_types.id is '非业务主键:auto_increment';
comment on column code.prac_activity_types.begin_on is '生效日期';
comment on column code.prac_activity_types.code is '代码';
comment on column code.prac_activity_types.en_name is '英文名称';
comment on column code.prac_activity_types.end_on is '失效日期';
comment on column code.prac_activity_types.name is '名称';
comment on column code.prac_activity_types.remark is '备注';
comment on column code.prac_activity_types.updated_at is '修改时间';
comment on table code.std_practice_categories is '学生实践大类@prac';
comment on column code.std_practice_categories.id is '非业务主键:auto_increment';
comment on column code.std_practice_categories.begin_on is '生效日期';
comment on column code.std_practice_categories.code is '代码';
comment on column code.std_practice_categories.en_name is '英文名称';
comment on column code.std_practice_categories.end_on is '失效日期';
comment on column code.std_practice_categories.name is '名称';
comment on column code.std_practice_categories.remark is '备注';
comment on column code.std_practice_categories.updated_at is '修改时间';
comment on table code.std_practice_types is '学生实践分类@prac';
comment on column code.std_practice_types.id is '非业务主键:auto_increment';
comment on column code.std_practice_types.begin_on is '生效日期';
comment on column code.std_practice_types.category_id is '学生实践大类ID';
comment on column code.std_practice_types.code is '代码';
comment on column code.std_practice_types.en_name is '英文名称';
comment on column code.std_practice_types.end_on is '失效日期';
comment on column code.std_practice_types.name is '名称';
comment on column code.std_practice_types.remark is '备注';
comment on column code.std_practice_types.updated_at is '修改时间';
comment on table prac.ability_credit_applies is '学生能力证书学分申请@ability';
comment on column prac.ability_credit_applies.id is '非业务主键:datetime';
comment on column prac.ability_credit_applies.acquired_on is '获得年月';
comment on column prac.ability_credit_applies.attachment_path is '成绩单附件路径';
comment on column prac.ability_credit_applies.audit_depart_id is '审核部门ID';
comment on column prac.ability_credit_applies.audit_opinion is '审核意见';
comment on column prac.ability_credit_applies.certificate_id is '校外证书ID';
comment on column prac.ability_credit_applies.certificate_no is '证书编号';
comment on column prac.ability_credit_applies.credits is '认定的学分数';
comment on column prac.ability_credit_applies.finished is '是否完成证书所有课程';
comment on column prac.ability_credit_applies.reasons is '申请理由';
comment on column prac.ability_credit_applies.semester_id is '学年学期ID';
comment on column prac.ability_credit_applies.status is '申请状态';
comment on column prac.ability_credit_applies.std_id is '学生ID';
comment on column prac.ability_credit_applies.subject_cnt is '通过门数';
comment on column prac.ability_credit_applies.subjects is '证书内课程';
comment on column prac.ability_credit_applies.updated_at is '更新时间';
comment on table prac.ability_credits is '学生能力素质能力认定学分@ability';
comment on column prac.ability_credits.id is '非业务主键:datetime';
comment on column prac.ability_credits.course_grade_id is '认定的成绩ID';
comment on column prac.ability_credits.credits is '学分';
comment on column prac.ability_credits.std_id is '学生ID';
comment on column prac.ability_credits.updated_at is '更新时间';
comment on table prac.prac_activities is '校外实践活动@activity';
comment on column prac.prac_activities.id is '非业务主键:datetime';
comment on column prac.prac_activities.activity_name is '活动名称';
comment on column prac.prac_activities.activity_type_id is '实践课程类型ID';
comment on column prac.prac_activities.credits is '学分';
comment on column prac.prac_activities.department_id is '部门组织机构信息ID';
comment on column prac.prac_activities.description is '活动介绍';
comment on column prac.prac_activities.extern_teacher is '外校教师';
comment on column prac.prac_activities.project_id is '项目ID';
comment on column prac.prac_activities.semester_id is '学年学期ID';
comment on column prac.prac_activities.std_count is '实际人数';
comment on table prac.prac_activities_teachers is '授课教师@activity';
comment on column prac.prac_activities_teachers.prac_activity_id is '校外实践活动ID';
comment on column prac.prac_activities_teachers.user_id is '通用人员信息ID';
comment on table prac.prac_activity_schedules is '校外实践活动安排@activity';
comment on column prac.prac_activity_schedules.id is '非业务主键:datetime';
comment on column prac.prac_activity_schedules.activity_id is '校外实践活动ID';
comment on column prac.prac_activity_schedules.begin_at is '开始时间';
comment on column prac.prac_activity_schedules.begin_on is '开始日期';
comment on column prac.prac_activity_schedules.end_at is '结束时间';
comment on column prac.prac_activity_schedules.end_on is '结束日期';
comment on column prac.prac_activity_schedules.extern_teacher is '外校教师';
comment on column prac.prac_activity_schedules.places is '地点';
comment on column prac.prac_activity_schedules.remark is '备注';
comment on column prac.prac_activity_schedules.start_on is '开始日期';
comment on column prac.prac_activity_schedules.teaching_method_id is '授课方式方法ID';
comment on column prac.prac_activity_schedules.times is '日期时间';
comment on column prac.prac_activity_schedules.topic is '教学主题';
comment on column prac.prac_activity_schedules.weekstate is '周状态';
comment on table prac.prac_activity_schedules_teachers is '授课教师@activity';
comment on column prac.prac_activity_schedules_teachers.prac_activity_schedule_id is '校外实践活动安排ID';
comment on column prac.prac_activity_schedules_teachers.user_id is '通用人员信息ID';
comment on table prac.prac_clazz_schedules is '校内实践课程安排@activity';
comment on column prac.prac_clazz_schedules.id is '非业务主键:datetime';
comment on column prac.prac_clazz_schedules.activity_id is '校内实践课程ID';
comment on column prac.prac_clazz_schedules.begin_at is '开始时间';
comment on column prac.prac_clazz_schedules.begin_on is '开始日期';
comment on column prac.prac_clazz_schedules.end_at is '结束时间';
comment on column prac.prac_clazz_schedules.end_on is '结束日期';
comment on column prac.prac_clazz_schedules.extern_teacher is '外校教师';
comment on column prac.prac_clazz_schedules.places is '地点';
comment on column prac.prac_clazz_schedules.remark is '备注';
comment on column prac.prac_clazz_schedules.start_on is '开始日期';
comment on column prac.prac_clazz_schedules.teaching_method_id is '授课方式方法ID';
comment on column prac.prac_clazz_schedules.times is '日期时间';
comment on column prac.prac_clazz_schedules.topic is '教学主题';
comment on column prac.prac_clazz_schedules.weekstate is '周状态';
comment on table prac.prac_clazz_schedules_teachers is '授课教师@activity';
comment on column prac.prac_clazz_schedules_teachers.prac_clazz_schedule_id is '校内实践课程安排ID';
comment on column prac.prac_clazz_schedules_teachers.user_id is '通用人员信息ID';
comment on table prac.prac_clazzes is '校内实践课程@activity';
comment on column prac.prac_clazzes.id is '非业务主键:datetime';
comment on column prac.prac_clazzes.clazz_name is '班级名称';
comment on column prac.prac_clazzes.course_id is '课程基本信息ID';
comment on column prac.prac_clazzes.course_name is '课程名称';
comment on column prac.prac_clazzes.course_type_id is '课程类别ID';
comment on column prac.prac_clazzes.credits is '学分';
comment on column prac.prac_clazzes.department_id is '部门组织机构信息ID';
comment on column prac.prac_clazzes.extern_teacher is '外校教师';
comment on column prac.prac_clazzes.lang_type_id is '授课语言类型ID';
comment on column prac.prac_clazzes.project_id is '项目ID';
comment on column prac.prac_clazzes.semester_id is '学年学期ID';
comment on column prac.prac_clazzes.std_count is '实际人数';
comment on table prac.prac_clazzes_teachers is '授课教师@activity';
comment on column prac.prac_clazzes_teachers.prac_clazz_id is '校内实践课程ID';
comment on column prac.prac_clazzes_teachers.user_id is '通用人员信息ID';
comment on table prac.std_practice_hours is '学生实践学时@activity';
comment on column prac.std_practice_hours.id is '非业务主键:datetime';
comment on column prac.std_practice_hours.category_id is '学生实践大类ID';
comment on column prac.std_practice_hours.hours is '学时';
comment on column prac.std_practice_hours.std_id is '学生ID';
comment on column prac.std_practice_hours.updated_at is '更新时间';
comment on table prac.std_practice_infoes is '学生实践活动@activity';
comment on column prac.std_practice_infoes.id is '非业务主键:datetime';
comment on column prac.std_practice_infoes.category_id is '学生实践大类ID';
comment on column prac.std_practice_infoes.datetime is '日期时间';
comment on column prac.std_practice_infoes.hours is '学时';
comment on column prac.std_practice_infoes.name is '名称';
comment on column prac.std_practice_infoes.place is '地点';
comment on column prac.std_practice_infoes.practice_type_id is '学生实践分类ID';
comment on column prac.std_practice_infoes.remark is '备注';
comment on column prac.std_practice_infoes.std_id is '学生ID';
comment on column prac.std_practice_infoes.updated_at is '更新时间';
comment on column prac.std_practice_hours.course_grade_id is '已转为课程成绩';
comment on column prac.std_practice_hours.required_hours is '学时要求';
