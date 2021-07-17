insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.23.1',now(),'更新外校交流成绩');

--minor
create table base.minor_majors (id bigint not null, begin_on date not null, category_id integer not null, code varchar(255) not null, en_name varchar(255), end_on date, institution_id integer not null, major_id bigint, name varchar(255) not null);
comment on table base.minor_majors is '辅修专业@std';
comment on column base.minor_majors.id is '非业务主键:auto_increment';
comment on column base.minor_majors.begin_on is '生效日期';
comment on column base.minor_majors.category_id is '学科门类ID';
comment on column base.minor_majors.code is '代码';
comment on column base.minor_majors.en_name is '英文名';
comment on column base.minor_majors.end_on is '失效日期';
comment on column base.minor_majors.institution_id is '科研机构ID';
comment on column base.minor_majors.major_id is '对应本校的专业ID';
comment on column base.minor_majors.name is '名称';
alter table base.minor_majors add constraint pk_puyx3wltdpl9vv77rj77l9txc primary key (id);
alter table base.minor_majors add constraint fk_ke4umnne27kyiri86a5xh8cao foreign key (institution_id) references code.institutions (id);
alter table base.minor_majors add constraint fk_dpl4nixoo7au7l7cn8trnyov1 foreign key (major_id) references base.majors (id);
alter table base.minor_majors add constraint fk_scxbu2r3m4h8enodix43iqufy foreign key (category_id) references code.discipline_categories (id);
alter table std.minors rename to major_students;

--exchange
alter table edu.exchange_schools set schema base;
alter table base.exchange_schools rename to extern_schools;
alter table edu.exchange_students rename to extern_students;
alter table edu.extern_students set schema base;
alter table edu.exemption_credits set schema std;
alter table edu.exchange_grades set schema std;
alter table edu.exchange_grades_courses set schema std;
--??create edu.extern_grades.
--create table edu.extern_grades (id bigint not null, acquired_on date not null, course_name varchar(400) not null, credits float4 not null, extern_student_id bigint not null, remark varchar(255), score_text varchar(20) not null, updated_at timestamp not null);


--exemption_applies
create table std.exemption_applies (id bigint not null, audit_opinion varchar(255), audit_state integer not null, credits float4 not null, exemption_credits float4 not null, extern_student_id bigint not null, transcript_path varchar(255), updated_at timestamp not null);
comment on table std.exemption_applies is '免修申请@exchange';
comment on column std.exemption_applies.id is '非业务主键:datetime';
comment on column std.exemption_applies.audit_opinion is '审核意见';
comment on column std.exemption_applies.audit_state is '审核状态';
comment on column std.exemption_applies.credits is '申请冲抵的外校课程学分总计';
comment on column std.exemption_applies.exemption_credits is '冲抵本校课程的学分总计';
comment on column std.exemption_applies.extern_student_id is '外校学习经历ID';
comment on column std.exemption_applies.transcript_path is '成绩单附件路径';
comment on column std.exemption_applies.updated_at is '更新时间';
alter table std.exemption_applies add constraint pk_qvjs9t4aqm88842v6gyn46bgv primary key (id);
alter table std.exemption_applies add constraint uk_by51f9dfo4ln2rvtqab3q5mty unique (extern_student_id);
alter table std.exemption_applies add constraint fk_qo9k4wr3fykdx7exe1q1b4vxf foreign key (extern_student_id) references base.extern_students (id);

--exchange_programs
--??create exchange programs
alter table std.exchange_programs add constraint pk_md99sroe2nv8btxk3cr7tp2an primary key (id);
alter table std.exchange_programs add constraint fk_cwq1p3629513kh6yj165ciav4 foreign key (project_id) references base.projects (id);
create table std.exchange_programs_schools (exchange_program_id bigint not null, extern_school_id integer not null);
comment on table std.exchange_programs_schools is '交换生计划对应学校@exchange';
comment on column std.exchange_programs_schools.exchange_program_id is '交换生计划ID';
comment on column std.exchange_programs_schools.extern_school_id is '校外教育机构ID';
alter table std.exchange_programs_schools add constraint pk_da8usceea51yrhkys93r6wcgw primary key (exchange_program_id,extern_school_id);
create index idx_66bod6jp4lk8p1u4c3cf6pix4 on std.exchange_programs_schools (exchange_program_id);
alter table std.exchange_programs_schools add constraint fk_5t7s1x3fvpnlo91ugo0qcp4u3 foreign key (exchange_program_id) references std.exchange_programs (id);
alter table std.exchange_programs_schools add constraint fk_fc6hj8pr1rmq0oyfuaocumded foreign key (extern_school_id) references base.extern_schools (id);

--process grade

create table edu.process_grades (id bigint not null, clazz_id bigint not null, score float4 not null, status integer not null, std_id bigint not null, updated_at timestamp not null);
comment on table edu.process_grades is '平时过程总评成绩@grade.process';
comment on column edu.process_grades.id is '非业务主键:datetime';
comment on column edu.process_grades.clazz_id is '教学任务ID';
comment on column edu.process_grades.score is '分数';
comment on column edu.process_grades.status is '状态';
comment on column edu.process_grades.std_id is '学籍信息实现ID';
comment on column edu.process_grades.updated_at is '更新时间';
alter table edu.process_grades add constraint pk_ertalftcv64vnbi8sya1kebvh primary key (id);
alter table edu.process_grades add constraint uk_tpg5ssh3c0nc4bgte7770lsop unique (std_id,clazz_id);
create index idx_1oeikyxe9m04d1os8e6soi5go on edu.process_grades (std_id);
create index idx_py3s5ykk1fw0gk21lxl5ctl7v on edu.process_grades (clazz_id);
alter table edu.process_grades add constraint fk_j6m8dvr6hg60eloemmsss2qnv foreign key (std_id) references base.students (id);
alter table edu.process_grades add constraint fk_tjck1xif57r4tf9b99xvdmgmp foreign key (clazz_id) references edu.clazzes (id);
create table edu.process_test_grades (id bigint not null, process_grade_id bigint not null, score integer not null, score_percent integer not null, test_type_id integer not null);
comment on table edu.process_test_grades is '平时测试成绩@grade.process';
comment on column edu.process_test_grades.id is '非业务主键:datetime';
comment on column edu.process_test_grades.process_grade_id is '平时过程总评成绩ID';
comment on column edu.process_test_grades.score is '分数';
comment on column edu.process_test_grades.score_percent is '百分比';
comment on column edu.process_test_grades.test_type_id is '平时测试类型ID';
alter table edu.process_test_grades add constraint pk_m2ij29ekdu9mjwwh999l76iph primary key (id);
alter table edu.process_test_grades add constraint uk_23w8j9ifx282bxgi9f12gjwh3 unique (process_grade_id,test_type_id);
create index idx_qoyl32k2m10m7r8g1if8fwdg5 on edu.process_test_grades (process_grade_id);
alter table edu.process_test_grades add constraint fk_ea2db94ig58wr4i6tt4d2cky9 foreign key (process_grade_id) references edu.process_grades (id);
alter table edu.process_test_grades add constraint fk_4rpt9v447pkihwaoy6tklddx9 foreign key (test_type_id) references edu.process_test_types (id);
create table edu.process_grade_states_percents (process_grade_state_id bigint not null, process_test_type_id integer not null, value_ integer not null);
comment on table edu.process_grade_states_percents is '各类测试百分比@grade.process';
comment on column edu.process_grade_states_percents.process_grade_state_id is '平时总评成绩状态ID';
comment on column edu.process_grade_states_percents.process_test_type_id is '平时测试类型ID';
comment on column edu.process_grade_states_percents.value_ is '各类测试百分比';
alter table edu.process_grade_states_percents add constraint pk_2oh70wf3brffvjdb2j5m3wn0x primary key (process_grade_state_id,value_,process_test_type_id);
create index idx_pqls9iau3y0aiari9j1i8pjbh on edu.process_grade_states_percents (process_grade_state_id);
alter table edu.process_grade_states_percents add constraint fk_jfb6nv8xt9ul5gqjvpey2o8x7 foreign key (process_grade_state_id) references edu.process_grade_states (id);
create table edu.process_grade_states (id bigint not null, clazz_id bigint not null, excellent_rate float4 not null, excellent_rate_limit float4 not null, status integer not null, updated_at timestamp not null);
comment on table edu.process_grade_states is '平时总评成绩状态@grade.process';
comment on column edu.process_grade_states.id is '非业务主键:datetime';
comment on column edu.process_grade_states.clazz_id is '教学任务ID';
comment on column edu.process_grade_states.excellent_rate is '优秀率';
comment on column edu.process_grade_states.excellent_rate_limit is '优秀率上限';
comment on column edu.process_grade_states.status is '状态';
comment on column edu.process_grade_states.updated_at is '更新时间';
alter table edu.process_grade_states add constraint pk_3iw2sbrv3pfmqb6lm168lfvpf primary key (id);
alter table edu.process_grade_states add constraint uk_efki1v9qto7s90pa2081ajov unique (clazz_id);
alter table edu.process_grade_states add constraint fk_4jrss25fyhfrryx1h43989hmt foreign key (clazz_id) references edu.clazzes (id);
create table edu.process_test_types (id integer not null, name varchar(255) not null);
comment on table edu.process_test_types is '平时测试类型@grade.process';
comment on column edu.process_test_types.id is '非业务主键:auto_increment';
comment on column edu.process_test_types.name is '名称';
alter table edu.process_test_types add constraint pk_knphm7mh7voy3442lcxysesn7 primary key (id);
alter table edu.process_test_types add constraint uk_oxllj3oaiitkn2np5b1bviv3h unique (name);

--stdinfo
create table std.punishments (id bigint not null, depart_id integer not null, doc_seq varchar(255) not null, issue_on date not null, name varchar(255) not null, publishment_type_id integer not null, reason varchar(255), remark varchar(255), semester_id integer not null, std_id bigint not null, withdraw_on date);
comment on table std.punishments is '处分@award';
comment on column std.punishments.id is '非业务主键:datetime';
comment on column std.punishments.depart_id is '部门组织机构信息ID';
comment on column std.punishments.doc_seq is '处分文号';
comment on column std.punishments.issue_on is '日期';
comment on column std.punishments.name is '名称';
comment on column std.punishments.publishment_type_id is '学生处分类型ID';
comment on column std.punishments.reason is '处分原因';
comment on column std.punishments.remark is '备注';
comment on column std.punishments.semester_id is '学年学期ID';
comment on column std.punishments.std_id is '学籍信息实现ID';
comment on column std.punishments.withdraw_on is '撤销日期';
alter table std.punishments add constraint pk_n9h4mdiiwcc2512hqfwdu3w3 primary key (id);
alter table std.punishments add constraint fk_mtkduknxkugka6i94ij538d70 foreign key (std_id) references base.students (id);
alter table std.punishments add constraint fk_hidl8et9oewgb463jg2hg9ar7 foreign key (publishment_type_id) references code.std_punishment_types (id);
alter table std.punishments add constraint fk_8nk8fjmdj5ha71jlp1auqk5j8 foreign key (semester_id) references base.semesters (id);
alter table std.punishments add constraint fk_itujqw0pkp4j6qlcqpanlf9a foreign key (depart_id) references base.departments (id);
alter table std.contacts rename column mail to email;
alter table std.contacts add constraint uk_qopxi21m45bqtuemmwurk7dkl unique (std_id);
alter table std.degree_results drop batch cascade;
alter table std.social_relations drop relation cascade;
alter table std.social_relations add column duty varchar(400);
alter table std.social_relations add column idcard varchar(255);
alter table std.social_relations add column phone varchar(255);
alter table std.social_relations add column id_type_id integer;
alter table std.social_relations add column relationship_id integer;
alter table std.social_relations alter relationship_id set not null;
alter table std.social_relations add constraint fk_4y95e8ow9ijrr7443wy3inkjj foreign key (id_type_id) references code.id_types (id);
alter table std.social_relations add constraint fk_bkj2udhihdk4dx9ixwcrb0gh2 foreign key (relationship_id) references code.family_relationships (id);
create index idx_tu5uubg7w074d5n7aoudd13r on std.social_relations (std_id);
alter table std.graduate_results rename column batch to batch_no;
alter table std.homes add constraint uk_t42cafsv2e1s4k9l1rxdixf2x unique (std_id);
alter table std.graduations add column batch_no integer;
alter table std.graduations alter batch_no set not null;
alter table std.graduations alter certificate_no drop not null;
alter table std.graduations rename column code to certificate_no;
alter table code.std_punishment_types add column grade integer;
alter table code.std_punishment_types alter grade set not null;

alter table edu.programs alter end_on set not null;
comment on column edu.programs.end_on is '结束日期';
alter table base.students alter person_id set not null;

