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

create table edu.regular_grades (id bigint not null, clazz_id bigint not null, score float4 not null, status integer not null, std_id bigint not null, updated_at timestamp not null);
comment on table edu.regular_grades is '平时过程总评成绩@grade.process';
comment on column edu.regular_grades.id is '非业务主键:datetime';
comment on column edu.regular_grades.clazz_id is '教学任务ID';
comment on column edu.regular_grades.score is '分数';
comment on column edu.regular_grades.status is '状态';
comment on column edu.regular_grades.std_id is '学籍信息实现ID';
comment on column edu.regular_grades.updated_at is '更新时间';
alter table edu.regular_grades add constraint pk_ertalftcv64vnbi8sya1kebvh primary key (id);
alter table edu.regular_grades add constraint uk_tpg5ssh3c0nc4bgte7770lsop unique (std_id,clazz_id);
create index idx_1oeikyxe9m04d1os8e6soi5go on edu.regular_grades (std_id);
create index idx_py3s5ykk1fw0gk21lxl5ctl7v on edu.regular_grades (clazz_id);
alter table edu.regular_grades add constraint fk_j6m8dvr6hg60eloemmsss2qnv foreign key (std_id) references base.students (id);
alter table edu.regular_grades add constraint fk_tjck1xif57r4tf9b99xvdmgmp foreign key (clazz_id) references edu.clazzes (id);
create table edu.regular_test_grades (id bigint not null, process_grade_id bigint not null, score integer not null, score_percent integer not null, test_type_id integer not null);
comment on table edu.regular_test_grades is '平时测试成绩@grade.process';
comment on column edu.regular_test_grades.id is '非业务主键:datetime';
comment on column edu.regular_test_grades.process_grade_id is '平时过程总评成绩ID';
comment on column edu.regular_test_grades.score is '分数';
comment on column edu.regular_test_grades.score_percent is '百分比';
comment on column edu.regular_test_grades.test_type_id is '平时测试类型ID';
alter table edu.regular_test_grades add constraint pk_m2ij29ekdu9mjwwh999l76iph primary key (id);
alter table edu.regular_test_grades add constraint uk_23w8j9ifx282bxgi9f12gjwh3 unique (process_grade_id,test_type_id);
create index idx_qoyl32k2m10m7r8g1if8fwdg5 on edu.regular_test_grades (process_grade_id);
alter table edu.regular_test_grades add constraint fk_ea2db94ig58wr4i6tt4d2cky9 foreign key (process_grade_id) references edu.regular_grades (id);
alter table edu.regular_test_grades add constraint fk_4rpt9v447pkihwaoy6tklddx9 foreign key (test_type_id) references edu.regular_test_types (id);
create table edu.regular_grade_states_percents (process_grade_state_id bigint not null, process_test_type_id integer not null, value_ integer not null);
comment on table edu.regular_grade_states_percents is '各类测试百分比@grade.process';
comment on column edu.regular_grade_states_percents.process_grade_state_id is '平时总评成绩状态ID';
comment on column edu.regular_grade_states_percents.process_test_type_id is '平时测试类型ID';
comment on column edu.regular_grade_states_percents.value_ is '各类测试百分比';
alter table edu.regular_grade_states_percents add constraint pk_2oh70wf3brffvjdb2j5m3wn0x primary key (process_grade_state_id,value_,process_test_type_id);
create index idx_pqls9iau3y0aiari9j1i8pjbh on edu.regular_grade_states_percents (process_grade_state_id);
alter table edu.regular_grade_states_percents add constraint fk_jfb6nv8xt9ul5gqjvpey2o8x7 foreign key (process_grade_state_id) references edu.regular_grade_states (id);
create table edu.regular_grade_states (id bigint not null, clazz_id bigint not null, excellent_rate float4 not null, excellent_rate_limit float4 not null, status integer not null, updated_at timestamp not null);
comment on table edu.regular_grade_states is '平时总评成绩状态@grade.process';
comment on column edu.regular_grade_states.id is '非业务主键:datetime';
comment on column edu.regular_grade_states.clazz_id is '教学任务ID';
comment on column edu.regular_grade_states.excellent_rate is '优秀率';
comment on column edu.regular_grade_states.excellent_rate_limit is '优秀率上限';
comment on column edu.regular_grade_states.status is '状态';
comment on column edu.regular_grade_states.updated_at is '更新时间';
alter table edu.regular_grade_states add constraint pk_3iw2sbrv3pfmqb6lm168lfvpf primary key (id);
alter table edu.regular_grade_states add constraint uk_efki1v9qto7s90pa2081ajov unique (clazz_id);
alter table edu.regular_grade_states add constraint fk_4jrss25fyhfrryx1h43989hmt foreign key (clazz_id) references edu.clazzes (id);
create table edu.regular_test_types (id integer not null, name varchar(255) not null);
comment on table edu.regular_test_types is '平时测试类型@grade.process';
comment on column edu.regular_test_types.id is '非业务主键:auto_increment';
comment on column edu.regular_test_types.name is '名称';
alter table edu.regular_test_types add constraint pk_knphm7mh7voy3442lcxysesn7 primary key (id);
alter table edu.regular_test_types add constraint uk_oxllj3oaiitkn2np5b1bviv3h unique (name);

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
alter table std.graduations add column batch_no integer default 1;
alter table std.graduations alter batch_no set not null;
alter table std.graduations alter certificate_no drop not null;
alter table std.graduations rename column code to certificate_no;
alter table std.graduations add whereto_go_id int4;
alter table code.std_punishment_types add column grade integer;
alter table code.std_punishment_types alter grade set not null;

alter table edu.programs alter end_on set not null;
comment on column edu.programs.end_on is '结束日期';
alter table base.students alter person_id set not null;

---增加教学研究部分
create schema rd;
create table rd.rd_achievement_awards (name varchar(255) not null, award_on date, id bigint not null, grade_id integer not null, award_year integer not null, achievement_id bigint not null, level_id integer not null, award_by varchar(255) not null);
create table rd.rd_achievement_members (name varchar(255) not null, id bigint not null, idx integer not null, user_id bigint, achievement_id bigint not null);
create table rd.rd_achievement_types (id integer not null, begin_on date not null, end_on date, updated_at timestamp not null, code varchar(20) not null unique, en_name varchar(300), name varchar(100) not null, remark varchar(200));
create table rd.rd_achievements (org_name varchar(255) not null, website varchar(255), code varchar(255) not null, id bigint not null, achievement_type_id integer not null, begin_on date not null, end_on date not null, category_code varchar(255), name varchar(255) not null, updated_at timestamp not null);
create table rd.rd_award_grades (id integer not null, begin_on date not null, end_on date, updated_at timestamp not null, code varchar(20) not null unique, en_name varchar(300), name varchar(100) not null, remark varchar(200));
create table rd.rd_levels (id integer not null, begin_on date not null, end_on date, updated_at timestamp not null, code varchar(20) not null unique, en_name varchar(300), name varchar(100) not null, remark varchar(200));
create table rd.rd_project_categories (id integer not null, begin_on date not null, for_course boolean not null, end_on date, updated_at timestamp not null, code varchar(20) not null unique, en_name varchar(300), name varchar(100) not null, remark varchar(200));
create table rd.rd_project_members (name varchar(255) not null, project_id bigint not null, id bigint not null, idx integer not null, user_id bigint);
create table rd.rd_project_statuses (id integer not null, begin_on date not null, end_on date, updated_at timestamp not null, code varchar(20) not null unique, en_name varchar(300), name varchar(100) not null, remark varchar(200));
create table rd.rd_projects (code varchar(255) not null, department_id integer not null, id bigint not null, finished_on date, category_id integer not null, for_course boolean not null, end_on date not null, level_id integer not null, name varchar(255) not null, updated_at timestamp not null, remark varchar(255), begin_on date not null, status_id integer not null, funds integer not null);
create table rd.rd_projects_leaders (rd_project_id bigint not null, user_id bigint not null);
create table rd.teaching_team_members (id bigint not null, team_id bigint not null, idx integer not null, user_id bigint not null);
create table rd.teaching_teams (award_title varchar(255), code varchar(255) not null, department_id integer not null, id bigint not null, begin_on date not null, level_id integer not null, name varchar(255) not null, updated_at timestamp not null, remark varchar(255));
create table rd.teaching_teams_leaders (teaching_team_id bigint not null, user_id bigint not null);
create table rd.textbook_achievements (updated_at timestamp not null, textbook_id bigint not null, id bigint not null);
create table rd.textbook_awards (name varchar(255) not null, award_on date, id bigint not null, grade_id integer, level_id integer not null, achievement_id bigint not null);
create table rd.textbook_editors (chief boolean not null, name varchar(255) not null, id bigint not null, idx integer not null, user_id bigint, achievement_id bigint not null);

alter table rd.rd_achievement_awards add constraint pk_jrurc91k5s98dupbvbiucs605 primary key (id);
alter table rd.rd_achievement_members add constraint pk_13aseiekqtn3gmtqw4u0ortdo primary key (id);
alter table rd.rd_achievement_types add constraint pk_ta42fnnl0b3oljvweg6mflnwd primary key (id);
alter table rd.rd_achievements add constraint pk_kcqw8m7fdba9xh17q6sm4kxtd primary key (id);
alter table rd.rd_award_grades add constraint pk_ij1rtygc537ykj7n9n0fonlm primary key (id);
alter table rd.rd_levels add constraint pk_oblsjsi7q9bdeq4kxmf4j3qc9 primary key (id);
alter table rd.rd_project_categories add constraint pk_8ad75c7otnko05ptv7cs56eos primary key (id);
alter table rd.rd_project_members add constraint pk_fx6ud9yt7chj5wgi8jsjxe1vd primary key (id);
alter table rd.rd_project_statuses add constraint pk_2l8sy0rmcghk1vv78aur11wmw primary key (id);
alter table rd.rd_projects add constraint pk_sn8pn1wp0f8awmjy42hcmnmoc primary key (id);
alter table rd.rd_projects_leaders add constraint pk_te6ajmxgr9o0402uvfenplpvh primary key (rd_project_id,user_id);
alter table rd.teaching_team_members add constraint pk_b0hsatym04m5isj6oxjjmmfbb primary key (id);
alter table rd.teaching_teams add constraint pk_k1qfb3g4prtlvhoqsw3cq0ska primary key (id);
alter table rd.teaching_teams_leaders add constraint pk_aqg74musb0j1qtonp6uy9vupg primary key (teaching_team_id,user_id);
alter table rd.textbook_achievements add constraint pk_301jawapmi8866dn1wo04dr62 primary key (id);
alter table rd.textbook_awards add constraint pk_mf7o4jj1qfw8k0d3akw2g95s7 primary key (id);
alter table rd.textbook_editors add constraint pk_6l1cf8pefv3kvorgvxyk7qrm8 primary key (id);

create index idx_sure8ht5dxo4cbr29cq0bbq50 on rd.rd_achievement_awards (achievement_id);
create index idx_tf2rabxu2vp1vl422toaq0y0t on rd.rd_achievement_members (achievement_id);
create index idx_2k68gfloim9504k22uiuvfauk on rd.rd_project_members (project_id);
create index idx_9i3d8et45ck4qwhsgrvnvdqrc on rd.rd_projects_leaders (rd_project_id);
create index idx_9loptf43av6ac4srdn8nuq58p on rd.teaching_team_members (team_id);
create index idx_iksg21jj8jtd80126xrj83fp6 on rd.teaching_teams_leaders (teaching_team_id);
create index idx_pynj8wtc3cfhryhr6988ymw1h on rd.textbook_awards (achievement_id);
create index idx_nkkgske0o3mh9cxc2s5hrpbvc on rd.textbook_editors (achievement_id);


alter table rd.rd_achievement_awards add constraint fk_44j8kfjd2ja3u8qlj56j5d180 foreign key (level_id) references rd.rd_levels (id);
alter table rd.rd_achievement_awards add constraint fk_ddndd7lhul3j63b3f98v49r8d foreign key (grade_id) references rd.rd_award_grades (id);
alter table rd.rd_achievement_awards add constraint fk_oxp7bebss19larnn3t0nb14gp foreign key (achievement_id) references rd.rd_achievements (id);
alter table rd.rd_achievement_members add constraint fk_jsxdglbdv51m9kbj295otnt3v foreign key (user_id) references base.users (id);
alter table rd.rd_achievement_members add constraint fk_tr48v1ys65adxli6vukmd9l66 foreign key (achievement_id) references rd.rd_achievements (id);
alter table rd.rd_achievements add constraint fk_sgffqts7wdj8k2vrtrpvlq0d1 foreign key (achievement_type_id) references rd.rd_achievement_types (id);
alter table rd.rd_project_members add constraint fk_b1y6ss7h1kn4xp7wtgnl3ihj9 foreign key (project_id) references rd.rd_projects (id);
alter table rd.rd_project_members add constraint fk_dqj4rjwrly2grgaonlanwxyvm foreign key (user_id) references base.users (id);
alter table rd.rd_projects add constraint fk_3n41csujj883di3pustjmihnd foreign key (level_id) references rd.rd_levels (id);
alter table rd.rd_projects add constraint fk_ekyb4syr9shdr4mvio1h07pf6 foreign key (department_id) references base.departments (id);
alter table rd.rd_projects add constraint fk_k2vcs9vy3431k0tuf1nhn0ubu foreign key (category_id) references rd.rd_project_categories (id);
alter table rd.rd_projects add constraint fk_oicmoe4lx8mjag53ba812vs2s foreign key (status_id) references rd.rd_project_statuses (id);
alter table rd.rd_projects_leaders add constraint fk_fxa8grfxgh285tcrphy86vtky foreign key (rd_project_id) references rd.rd_projects (id);
alter table rd.rd_projects_leaders add constraint fk_lme51xpar41nsygthi5ti6kti foreign key (user_id) references base.users (id);
alter table rd.teaching_team_members add constraint fk_fspuaxe1a4elhn8xulnovu2dp foreign key (team_id) references rd.teaching_teams (id);
alter table rd.teaching_team_members add constraint fk_m78errt234ys34olo2fih3ky6 foreign key (user_id) references base.users (id);
alter table rd.teaching_teams add constraint fk_3o51s6k56umn5thxfoombqp19 foreign key (department_id) references base.departments (id);
alter table rd.teaching_teams add constraint fk_rk53xvi8yj1jey0tr1q3qs71w foreign key (level_id) references rd.rd_levels (id);
alter table rd.teaching_teams_leaders add constraint fk_arns2gdyoytsqvrp4wld7jbln foreign key (user_id) references base.users (id);
alter table rd.teaching_teams_leaders add constraint fk_erqumximio9whgmyo69wokoch foreign key (teaching_team_id) references rd.teaching_teams (id);
alter table rd.textbook_achievements add constraint fk_qpm0bumhlc6bxyxqqk5a61cnq foreign key (textbook_id) references base.textbooks (id);
alter table rd.textbook_awards add constraint fk_bqljtra85ba4yhal05n5o58b6 foreign key (grade_id) references rd.rd_award_grades (id);
alter table rd.textbook_awards add constraint fk_h46np6gm06l99bci0qv2ntath foreign key (achievement_id) references rd.textbook_achievements (id);
alter table rd.textbook_awards add constraint fk_isow2pp9x9uso60iam7mn7npj foreign key (level_id) references rd.rd_levels (id);
alter table rd.textbook_editors add constraint fk_flsg7iul201ccuoxp8c1l6bt4 foreign key (achievement_id) references rd.textbook_achievements (id);
alter table rd.textbook_editors add constraint fk_h1v8t8wt85nke81m3roa78437 foreign key (user_id) references base.users (id);

alter table std.tuition_configs add direction_id bigint;
alter table std.tuition_configs add duration float;
--fixme
update std.tuition_configs set duration=4 where level_id in(select id from code.education_levels l where l.name='本科');
update std.tuition_configs set duration=5 where level_id in(select id from code.education_levels l where l.name='高起本');
update std.tuition_configs set duration=3 where level_id in(select id from code.education_levels l where l.name in('专升本','高起专'));

