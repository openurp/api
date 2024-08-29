insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.40.0',now(),'改进课程和培养方案');

create table base.courses_tags (course_id bigint not null, course_tag_id integer not null);
create table base.course_journal_hours (id bigint not null, credit_hours integer default 0 not null, journal_id bigint not null, nature_id integer not null, weeks integer default 0 not null);
create table base.course_journals (id bigint not null, course_id bigint not null, course_type_id integer not null, credit_hours integer default 0 not null, department_id integer not null, exam_mode_id integer not null, grade_id bigint not null, updated_at timestamptz default current_timestamp not null, week_hours integer default 0 not null, weeks integer default 0 not null);
create table base.theses (id bigint not null, advisor varchar(255), comments varchar(500), keywords varchar(200), language_id integer, research_field varchar(200), score float4, score_text varchar(20), source_id integer, std_id bigint not null, thesis_type_id integer, title varchar(500) not null);
create table base.course_clusters (id bigint not null, en_name varchar(255), name varchar(255) not null, project_id integer not null);
create table code.course_tags (id integer not null, begin_on date not null, code varchar(20) not null, en_name varchar(300), end_on date, name varchar(100) not null, remark varchar(200), short_name varchar(255), sign varchar(255), updated_at timestamptz default current_timestamp not null);
create table code.program_course_tags (id integer not null, begin_on date not null, code varchar(20) not null, en_name varchar(300), end_on date, name varchar(100) not null, remark varchar(200), short_name varchar(255), sign varchar(255), updated_at timestamptz default current_timestamp not null);
create table code.thesis_topic_sources (id integer not null, begin_on date not null, code varchar(20) not null, en_name varchar(300), end_on date, name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);
create table code.thesis_types (id integer not null, begin_on date not null, code varchar(20) not null, en_name varchar(300), end_on date, name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);
create table edu.program_course_labels (id bigint not null, course_id bigint not null, program_id bigint not null, tag_id integer not null);

--course_categories idex??
alter table code.course_categories add color varchar(10);
alter table code.course_categories add indexno varchar(10);
alter table code.course_categories add parent_id int4;
alter table edu.teaching_plans drop column doc_locale;
alter table edu.syllabuses rename column locale to doc_locale;
alter table edu.teaching_plans add column opinions varchar(300);
alter table edu.syllabuses add column opinions varchar(300);
alter table edu.teaching_plans add column lesson_hours int4 default 0;
alter table edu.teaching_plans add column exam_hours int4 default 0;
alter table base.courses add cluster_id bigint;
alter table base.courses add module_id bigint;
alter table base.courses add rank_id bigint;

alter table edu.course_tasks add syllabus_required boolean default true;

create table code.course_category_dimensions (id integer not null, begin_on date not null, end_on date, code varchar(20) not null, en_name varchar(300), name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);
alter table code.course_category_dimensions add constraint course_category_dimensions_code_key unique (code);
alter table code.course_category_dimensions add constraint pk_qx35190x8nbbv5p2u2x4obn8j primary key (id);
alter table code.course_categories add dimension_id int4;

alter table base.course_clusters add constraint pk_6gjj4kp1e06ob51efr5det0ot primary key (id);
alter table base.courses_tags add constraint pk_46nuykr92j7qtyny8dwsa0dbl primary key (course_id,course_tag_id);
alter table base.course_journal_hours add constraint pk_s8496i9wb7uknu72vs44i8dx7 primary key (id);
alter table base.course_journals add constraint pk_do83gjyf4jjoo7724iwkd5thn primary key (id);
alter table base.theses add constraint pk_pm8dei1ncn39xbda439mpqao7 primary key (id);
alter table code.course_tags add constraint pk_satpi3byt6c5baol3b5m090dh primary key (id);
alter table code.program_course_tags add constraint pk_mxsf2utw2s5dh63wx9s44pf4n primary key (id);
alter table code.thesis_topic_sources add constraint pk_3lderniukaq5dpqfvlh0qejob primary key (id);
alter table code.thesis_types add constraint pk_4tcotln8xe9p0p18p0fjqeul2 primary key (id);
alter table edu.program_course_labels add constraint pk_aejk5w39nv0dg8818jwpg67bq primary key (id);

alter table base.courses add constraint fk_b3oqgd1pw427cuyrtoi5c3794 foreign key (cluster_id) references base.course_clusters (id);
alter table base.course_clusters add constraint fk_s3wlxtejuii2qiubn7r49ichl foreign key (project_id) references base.projects (id);
alter table base.courses_tags add constraint fk_65r8l9b82e7ervbu7wijui68c foreign key (course_id) references base.courses (id);
alter table base.courses_tags add constraint fk_goebdflj73egcv00l2y3cio1d foreign key (course_tag_id) references code.course_tags (id);
alter table base.course_journal_hours add constraint fk_3g22ci8gemkcmatixwx8b55v4 foreign key (journal_id) references base.course_journals (id);
alter table base.course_journal_hours add constraint fk_sw2xt1t4sfo7csdpnpoqink66 foreign key (nature_id) references code.teaching_natures (id);
alter table base.course_journals add constraint fk_op7rb7kl5lt5s55ucsqgcb2ec foreign key (course_type_id) references code.course_types (id);
alter table base.course_journals add constraint fk_wl82w2eiwtrqt6hu3n1531l9 foreign key (exam_mode_id) references code.exam_modes (id);
alter table base.course_journals add constraint fk_74w9jwmn8xx548vnbu8d6o9yf foreign key (grade_id) references base.grades (id);
alter table base.course_journals add constraint fk_ifs9452ityx8mgd2ex4dghhuv foreign key (course_id) references base.courses (id);
alter table base.course_journals add constraint fk_7wbxgtnbiskdo1yudf1u0m22w foreign key (department_id) references base.departments (id);
alter table base.theses add constraint fk_j7kxlr162akb05faq2yrai6hg foreign key (std_id) references base.students (id);
alter table base.theses add constraint fk_45ed0que67j7gybjurqr8qku8 foreign key (thesis_type_id) references code.thesis_types (id);
alter table base.theses add constraint fk_oafq4q0wewve8islr8w6sk3r9 foreign key (language_id) references code.languages (id);
alter table base.theses add constraint fk_9nyn3ylmfc0reag5iuy2nyqtm foreign key (source_id) references code.thesis_topic_sources (id);
alter table edu.program_course_labels add constraint fk_lh6g9pye62c4655juhxng74yu foreign key (course_id) references base.courses (id);
alter table edu.program_course_labels add constraint fk_8enehe9sa6p3va1drnjtia6ke foreign key (program_id) references edu.programs (id);
alter table edu.program_course_labels add constraint fk_1g3koth47oni9h0coergbns76 foreign key (tag_id) references code.program_course_tags (id);
alter table edu.executive_course_groups add constraint fk_6mh987hal1axohw2uqqpb6n6b foreign key (stage_id) references base.calendar_stages (id);
alter table edu.executive_course_groups add constraint fk_qws3d63si4v6xhjnqrkjq7dq7 foreign key (rank_id) references code.course_ranks (id);
alter table edu.executive_plan_courses drop constraint if exists fk_18f6shjjvisy7v08ajv64f86d cascade;
alter table edu.executive_plan_courses drop constraint if exists fk_al1gnursmvf6djxjru6n7ic90 cascade;
alter table edu.executive_plan_courses drop constraint if exists fk_n0kl5cs7n9ud4keyann63bdt6 cascade;
alter table edu.major_course_groups add constraint fk_436wsxxoka11ym0dysdeeg4ip foreign key (stage_id) references base.calendar_stages (id);
alter table edu.major_course_groups add constraint fk_er4c5awycvn4b3so4q9mfx52k foreign key (rank_id) references code.course_ranks (id);
alter table edu.major_plan_courses drop constraint if exists fk_cpur8aumdrrxdxdcxosq9i8e9 cascade;
alter table edu.major_plan_courses drop constraint if exists fk_gqrlejyl7apbt5n37c4eto8qb cascade;
alter table edu.major_plan_courses drop constraint if exists fk_j38xjy3f1lmbr5qmylh1kklnh cascade;
alter table edu.share_plan_courses drop constraint if exists fk_7w6p0gedk311maqiuoh96lmwt cascade;
alter table edu.share_plan_courses drop constraint if exists fk_b0ekb19c7goh9fp1ql5hcb8cx cascade;
alter table edu.share_plan_courses drop constraint if exists fk_to7u0blska65ya55myb46qt1f cascade;
alter table edu.std_course_groups add constraint fk_k4mjbn5rvj3e23uvore7mh4kx foreign key (rank_id) references code.course_ranks (id);
alter table edu.std_course_groups add constraint fk_nnc7tsf6jo9l2lcl6qhcwetvu foreign key (stage_id) references base.calendar_stages (id);
alter table base.course_journals add constraint uk_dut9rpum7lpeogqb1gg8mxm4 unique (grade_id,course_id);
alter table base.theses add constraint uk_2bybvwu388u4f0mo9cw49nkwe unique (std_id);
alter table code.course_tags add constraint course_tags_code_key unique (code);
alter table code.program_course_tags add constraint program_course_tags_code_key unique (code);
alter table code.thesis_topic_sources add constraint thesis_topic_sources_code_key unique (code);
alter table code.thesis_types add constraint thesis_types_code_key unique (code);
create index idx_lm5drr3y1vinuxh8pqi0up3pw on base.course_journal_hours (journal_id);
create index idx_63qf0hjsyy394vrfl9ui51oe4 on base.course_journals (course_id);
create index idx_gfyumr969ca1ykkkgat7biy2t on edu.program_course_labels (program_id);
create index idx_b33k2icegejva3ino103aa5eb on base.courses_tags (course_id);

alter table edu.syllabuses alter materials type varchar(2000);
alter table edu.syllabuses alter methods type varchar(500);

alter table edu.major_course_groups add departments varchar(100);
alter table edu.executive_plans drop begin_on cascade;
alter table edu.executive_plans drop end_on cascade;
alter table edu.major_plans drop begin_on cascade;
alter table edu.major_plans drop end_on cascade;
alter table edu.std_plans drop begin_on cascade;
alter table edu.std_plans drop end_on cascade;
alter table edu.executive_course_groups add column stage_id integer;
alter table edu.major_course_groups add column stage_id integer;
alter table edu.std_course_groups add column stage_id integer;
alter table edu.std_plan_courses drop remark cascade;

alter table edu.share_plan_courses drop department_id cascade;
alter table edu.share_plan_courses drop exam_mode_id cascade;
alter table edu.share_plan_courses drop stage_id cascade;
alter table edu.share_plan_courses drop weekstate cascade;

alter table edu.major_plan_courses add column term_text varchar(10);
alter table edu.executive_plan_courses add column term_text varchar(10);
alter table edu.executive_course_groups add column rank_id integer;
alter table edu.major_course_groups add column rank_id integer;
alter table edu.std_course_groups add column rank_id integer;
alter table edu.executive_plan_courses add column idx smallint default 0;
alter table edu.major_plan_courses add column idx smallint default 0;
alter table edu.std_plan_courses add column idx smallint default 0;
alter table edu.executive_course_groups add column weeks integer;
alter table edu.major_course_groups add column weeks integer;

update edu.executive_plan_courses pc set term_text=(select s.name from base.calendar_stages s where s.id=pc.stage_id);
update edu.major_plan_courses pc set term_text=(select s.name from base.calendar_stages s where s.id=pc.stage_id);

update edu.executive_course_groups g set rank_id=1 where auto_addup=true and exists(select * from edu.executive_plan_courses pc where pc.group_id=g.id);
update edu.executive_course_groups g set rank_id=3 where rank_id is null and exists(select * from code.course_types ct where ct.id=g.course_type_id and ct.name like '%限制%');
update edu.executive_course_groups set rank_id=4 where rank_id is null;

update edu.major_course_groups g set rank_id=1 where auto_addup=true and exists(select * from edu.executive_plan_courses pc where pc.group_id=g.id);
update edu.major_course_groups g set rank_id=3 where rank_id is null and exists(select * from code.course_types ct where ct.id=g.course_type_id and ct.name like '%限制%');
update edu.major_course_groups set rank_id=4 where rank_id is null;

alter table edu.executive_plan_courses drop department_id cascade;
alter table edu.executive_plan_courses drop exam_mode_id cascade;
alter table edu.executive_plan_courses drop suggest_terms cascade;
alter table edu.major_plan_courses drop department_id cascade;
alter table edu.major_plan_courses drop exam_mode_id cascade;

alter table edu.major_course_groups drop allow_unplanned cascade;
alter table edu.major_course_groups drop auto_addup cascade;
alter table edu.std_course_groups drop allow_unplanned cascade;
alter table edu.std_course_groups drop auto_addup cascade;
alter table edu.executive_course_groups drop allow_unplanned cascade;
alter table edu.executive_course_groups drop auto_addup cascade;

alter table edu.major_plan_courses alter idx set not null;
alter table edu.executive_plan_courses alter idx set not null;
alter table edu.std_plan_courses alter idx set not null;

drop table base.course_names cascade;

create table flow.flow_types (id integer not null, name varchar(255) not null);
create table flow.process_logs (id bigint not null, comments varchar(2000) not null, entity_id bigint default 0 not null, flow_type_id integer not null, from_status varchar(100) not null, operator_id bigint not null, to_status varchar(100) not null, updated_at timestamptz default current_timestamp not null);
create table flow.edu_new_course_applies (module_id integer, applicant_id bigint not null, project_id integer not null, rank_id integer, credit_hours integer default 0 not null, exam_mode_id integer not null, code varchar(255), department_id integer not null, grading_mode_id integer not null, end_on date, default_credits float4 not null, name varchar(255) not null, en_name varchar(255), id bigint not null, begin_on date not null, status integer not null, nature_id integer not null, week_hours integer default 0 not null, category_id integer not null, updated_at timestamptz default current_timestamp not null);
create table flow.edu_new_course_applies_tags (new_course_apply_id bigint not null, course_tag_id integer not null);
create table flow.edu_new_course_apply_hours (weeks integer default 0 not null, credit_hours integer default 0 not null, id bigint not null, course_apply_id bigint not null, nature_id integer not null);
create table flow.edu_new_course_categories (id integer not null, begin_on date not null, end_on date, code varchar(20) not null, en_name varchar(300), name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);
create table flow.edu_new_course_departs (school_id integer not null, code varchar(255) not null, id bigint not null, depart_id integer not null);
alter table flow.edu_new_course_applies add constraint pk_c2hfstwqwcmyo86xfc4v4llu3 primary key (id);
alter table flow.edu_new_course_applies_tags add constraint pk_6n9cq37up5f509auhglv3lqqk primary key (new_course_apply_id,course_tag_id);
alter table flow.edu_new_course_apply_hours add constraint pk_9o8bhp7vs4pxwkcyeluduysai primary key (id);
alter table flow.edu_new_course_categories add constraint edu_new_course_categories_code_key unique (code);
alter table flow.edu_new_course_categories add constraint pk_ooyymrpkh76y78k7y935mt7l4 primary key (id);
alter table flow.edu_new_course_departs add constraint pk_jbaiwlql5545t7atoyyltjl6p primary key (id);

alter table flow.flow_types add constraint pk_ehgs3p5kwrerow9mydbi4j3tc primary key (id);
alter table flow.process_logs add constraint pk_pg4kxtmg5fqls4l221xt6b9cn primary key (id);
alter table flow.process_logs add constraint fk_ig1v0881ecbatm73ftrr83mbg foreign key (operator_id) references base.users (id);
alter table flow.process_logs add constraint fk_k72l3ohawu5uhm4i09xdnr320 foreign key (flow_type_id) references flow.flow_types (id);
alter table flow.edu_new_course_applies add constraint fk_16m7eofbd6bcstu4riw5ni7yv foreign key (exam_mode_id) references code.exam_modes (id);
alter table flow.edu_new_course_applies add constraint fk_21at3bdoub5eqiyc2eula4d05 foreign key (applicant_id) references base.users (id);
alter table flow.edu_new_course_applies add constraint fk_27hoy1o9c07bt4tn6i0e5995 foreign key (project_id) references base.projects (id);
alter table flow.edu_new_course_applies add constraint fk_387g9ljivru3jxdgxsocc0tcy foreign key (category_id) references flow.edu_new_course_categories (id);
alter table flow.edu_new_course_applies add constraint fk_eac9ye2h4tti5u6rrp4b3y2au foreign key (rank_id) references code.course_ranks (id);
alter table flow.edu_new_course_applies add constraint fk_eapmxr3lhv7973w7opidip8qk foreign key (department_id) references base.departments (id);
alter table flow.edu_new_course_applies add constraint fk_em5mh2clty1avltli9m0hfins foreign key (nature_id) references code.course_natures (id);
alter table flow.edu_new_course_applies add constraint fk_o8g0dsa3lxr5xf3q13dska24y foreign key (grading_mode_id) references code.grading_modes (id);
alter table flow.edu_new_course_applies add constraint fk_tj7ujf1r03hg4y6crjtw1dyir foreign key (module_id) references code.course_modules (id);
alter table flow.edu_new_course_applies_tags add constraint fk_e337xxmu3xn8dytgblifdwpjx foreign key (course_tag_id) references code.course_tags (id);
alter table flow.edu_new_course_applies_tags add constraint fk_eomhr7dsfvsxkl31jkr8xohxb foreign key (new_course_apply_id) references flow.edu_new_course_applies (id);
alter table flow.edu_new_course_apply_hours add constraint fk_l7wcmtqtes34hk7qe97j2fco6 foreign key (course_apply_id) references flow.edu_new_course_applies (id);
alter table flow.edu_new_course_apply_hours add constraint fk_tovmuehvajioljjit4b4673fa foreign key (nature_id) references code.teaching_natures (id);
alter table flow.edu_new_course_departs add constraint fk_60iccrk2txx4k767h41bfseao foreign key (school_id) references base.schools (id);
alter table flow.edu_new_course_departs add constraint fk_gls870o0jcts93hkbx7g4da8s foreign key (depart_id) references base.departments (id);

create index idx_8f6pd4rrn6otdrimouy7sb65i on flow.process_logs (flow_type_id, entity_id);
create index idx_2mk8rl7id68acmo8sk7h8gs4b on flow.edu_new_course_applies_tags (new_course_apply_id);
create index idx_pfxpk2do32b5txki4hkw2jnxn on flow.edu_new_course_apply_hours (course_apply_id);

comment on table base.course_clusters is '课程群组@edu';
comment on column base.course_clusters.id is '非业务主键:datetime';
comment on column base.course_clusters.en_name is '英文名';
comment on column base.course_clusters.name is '名称';
comment on column base.course_clusters.project_id is '项目ID';
comment on table base.course_journal_hours is '课程记录-学时@edu';
comment on column base.course_journal_hours.id is '非业务主键:datetime';
comment on column base.course_journal_hours.credit_hours is '学时';
comment on column base.course_journal_hours.journal_id is '课程记录ID';
comment on column base.course_journal_hours.nature_id is '授课性质ID';
comment on column base.course_journal_hours.weeks is '周数';
comment on table base.course_journals is '课程记录@edu';
comment on column base.course_journals.id is '非业务主键:datetime';
comment on column base.course_journals.course_id is '课程基本信息ID';
comment on column base.course_journals.course_type_id is '课程类别ID';
comment on column base.course_journals.credit_hours is '学时';
comment on column base.course_journals.department_id is '部门组织机构信息ID';
comment on column base.course_journals.exam_mode_id is '考核方式ID';
comment on column base.course_journals.grade_id is '年级ID';
comment on column base.course_journals.updated_at is '更新时间';
comment on column base.course_journals.week_hours is '周课时';
comment on column base.course_journals.weeks is '周数';
comment on table base.courses_tags is '课程标签@edu';
comment on column base.courses_tags.course_id is '课程基本信息ID';
comment on column base.courses_tags.course_tag_id is '课程标签ID';
comment on table base.theses is '学生论文信息@std';
comment on column base.theses.id is '非业务主键:datetime';
comment on column base.theses.advisor is '指导老师';
comment on column base.theses.comments is '评语';
comment on column base.theses.keywords is '论文关键词';
comment on column base.theses.language_id is '撰写语种ID';
comment on column base.theses.research_field is '研究领域';
comment on column base.theses.score is '分数';
comment on column base.theses.score_text is '成绩';
comment on column base.theses.source_id is '选题来源ID';
comment on column base.theses.std_id is '学籍信息实现ID';
comment on column base.theses.thesis_type_id is '论文类型ID';
comment on column base.theses.title is '论文题目';
comment on table code.course_tags is '课程标签@edu';
comment on column code.course_tags.id is '非业务主键:code';
comment on column code.course_tags.begin_on is '生效日期';
comment on column code.course_tags.code is '代码';
comment on column code.course_tags.en_name is '英文名称';
comment on column code.course_tags.end_on is '失效日期';
comment on column code.course_tags.name is '名称';
comment on column code.course_tags.remark is '备注';
comment on column code.course_tags.short_name is '简称';
comment on column code.course_tags.sign is '注记符号';
comment on column code.course_tags.updated_at is '修改时间';
comment on table code.program_course_tags is '专业课程标签@edu';
comment on column code.program_course_tags.id is '非业务主键:code';
comment on column code.program_course_tags.begin_on is '生效日期';
comment on column code.program_course_tags.code is '代码';
comment on column code.program_course_tags.en_name is '英文名称';
comment on column code.program_course_tags.end_on is '失效日期';
comment on column code.program_course_tags.name is '名称';
comment on column code.program_course_tags.remark is '备注';
comment on column code.program_course_tags.short_name is '简称';
comment on column code.program_course_tags.sign is '注记符号';
comment on column code.program_course_tags.updated_at is '修改时间';
comment on table code.thesis_topic_sources is '选题来源@edu';
comment on column code.thesis_topic_sources.id is '非业务主键:code';
comment on column code.thesis_topic_sources.begin_on is '生效日期';
comment on column code.thesis_topic_sources.code is '代码';
comment on column code.thesis_topic_sources.en_name is '英文名称';
comment on column code.thesis_topic_sources.end_on is '失效日期';
comment on column code.thesis_topic_sources.name is '名称';
comment on column code.thesis_topic_sources.remark is '备注';
comment on column code.thesis_topic_sources.updated_at is '修改时间';
comment on table code.thesis_types is '论文类型@edu';
comment on column code.thesis_types.id is '非业务主键:code';
comment on column code.thesis_types.begin_on is '生效日期';
comment on column code.thesis_types.code is '代码';
comment on column code.thesis_types.en_name is '英文名称';
comment on column code.thesis_types.end_on is '失效日期';
comment on column code.thesis_types.name is '名称';
comment on column code.thesis_types.remark is '备注';
comment on column code.thesis_types.updated_at is '修改时间';
comment on table edu.program_course_labels is '培养方案方案课程标签@program';
comment on column edu.program_course_labels.id is '非业务主键:datetime';
comment on column edu.program_course_labels.course_id is '课程基本信息ID';
comment on column edu.program_course_labels.program_id is '专业培养方案ID';
comment on column edu.program_course_labels.tag_id is '专业课程标签ID';
comment on column edu.executive_course_groups.rank_id is '课程属性ID';
comment on column edu.executive_course_groups.stage_id is '日历阶段ID';
comment on column edu.executive_plan_courses.idx is '序号';
comment on column edu.executive_plan_courses.term_text is '开课学期文本';
comment on column edu.major_course_groups.rank_id is '课程属性ID';
comment on column edu.major_course_groups.stage_id is '日历阶段ID';
comment on column edu.major_plan_courses.idx is '序号';
comment on column edu.major_plan_courses.term_text is '开课学期文本';
comment on column edu.std_course_groups.rank_id is '课程属性ID';
comment on column edu.std_course_groups.stage_id is '日历阶段ID';
comment on column edu.std_plan_courses.idx is '序号';
comment on table flow.flow_types is '业务类型@resource';
comment on column flow.flow_types.id is '非业务主键:auto_increment';
comment on column flow.flow_types.name is '名称';
comment on table flow.process_logs is '审核日志@resource';
comment on column flow.process_logs.id is '非业务主键:datetime';
comment on column flow.process_logs.comments is '说明';
comment on column flow.process_logs.entity_id is '实体';
comment on column flow.process_logs.flow_type_id is '业务类型ID';
comment on column flow.process_logs.from_status is '起始状态';
comment on column flow.process_logs.operator_id is '操作人ID';
comment on column flow.process_logs.to_status is '目标状态';
comment on column flow.process_logs.updated_at is '更新时间';

create table base.course_textbooks (course_id bigint not null, id bigint not null, begin_on date not null, textbook_id bigint not null, end_on date, recommended boolean default false not null, required boolean default false not null);
alter table base.course_textbooks add constraint pk_g7q36h5ul3cxttlc4n44ko4yy primary key (id);
alter table base.course_textbooks add constraint fk_4stnq1rke2gsqq6ltx8k0n5yl foreign key (textbook_id) references base.textbooks (id);
alter table base.course_textbooks add constraint fk_qh2cf5dfbnaqunjflbooxg5e foreign key (course_id) references base.courses (id);
comment on column base.course_textbooks.begin_on is '生效日期';
comment on column base.course_textbooks.course_id is '课程基本信息ID';
comment on column base.course_textbooks.end_on is '失效日期';
comment on column base.course_textbooks.id is '非业务主键:datetime';
comment on column base.course_textbooks.recommended is '是否推荐教材';
comment on column base.course_textbooks.required is '是否必选教材';
comment on column base.course_textbooks.textbook_id is '教材ID';
