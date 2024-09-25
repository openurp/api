insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.40.1',now(),'改进课程和培养方案2');

--code
alter table code.course_categories add column color varchar(255);
alter table code.course_categories add column dimension_id integer;
alter table code.course_categories alter dimension_id set not null;
alter table code.course_categories add column indexno varchar(255);
alter table code.course_categories alter indexno set not null;
alter table code.course_categories add column parent_id integer;
alter table code.course_category_dimensions add constraint pk_qx35190x8nbbv5p2u2x4obn8j primary key (id);
alter table code.course_categories add constraint fk_c5k6uw6byn2wdwqo5in0bi5rf foreign key (parent_id) references code.course_categories (id);
alter table code.course_categories add constraint fk_dcguulpdpunq4d9j3eofi6i2w foreign key (dimension_id) references code.course_category_dimensions (id);
alter table code.course_category_dimensions add constraint course_category_dimensions_code_key unique (code);
create index idx_o0yv8bij0wlo1dbukqmgjfd6p on code.course_categories (parent_id);
--base
create table base.course_journals_tags (course_journal_id bigint not null, course_tag_id integer not null);
create table base.courses_categories (course_category_id integer not null, course_id bigint not null);
create table code.course_category_dimensions (id integer not null, begin_on date not null, code varchar(20) not null, en_name varchar(300), end_on date, name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);

alter table base.course_hours drop weeks cascade;
alter table base.course_journal_hours drop weeks cascade;
alter table base.courses add column module_id integer;
alter table base.courses add column rank_id integer;
alter table base.courses alter course_type_id drop not null;
alter table base.course_profiles add column category_id integer;

alter table base.course_journals_tags add constraint pk_oc31bgxn1cvhvmk0qm0ggi2xx primary key (course_journal_id,course_tag_id);
alter table base.courses_categories add constraint pk_6yk45p39vdpsewkckbdykwte0 primary key (course_id,course_category_id);
alter table base.course_journals_tags add constraint fk_r2phyx0i8jsm70swiqwdkqp76 foreign key (course_journal_id) references base.course_journals (id);
alter table base.course_journals_tags add constraint fk_6f95s2i987nm7hqwj2nqdwxih foreign key (course_tag_id) references code.course_tags (id);
alter table base.courses_categories add constraint fk_3b0bydvq4epcc03soryfs3ny2 foreign key (course_id) references base.courses (id);
alter table base.courses_categories add constraint fk_4424ctb42s6tah056jxicxjte foreign key (course_category_id) references code.course_categories (id);
alter table base.course_profiles drop constraint if exists fk_pl9metcyvlatw5fosu0i4wnjq cascade;
alter table base.course_profiles add constraint fk_635e263hw0c3erxkus9fe4ndr foreign key (category_id) references code.course_categories (id);
alter table base.courses drop constraint if exists fk_cicn2r7gplymu7hbcf1n0fo9 cascade;
alter table base.courses drop constraint if exists fk_lrqjut8xtbtgs6h7uosddyut7 cascade;
alter table base.courses add constraint fk_fc5thepipnuxwrkelcyiteyr foreign key (rank_id) references code.course_ranks (id);
alter table base.courses add constraint fk_gk09g3jc7vtceqa8tsav91s2o foreign key (module_id) references code.course_modules (id);
alter table base.major_directors add constraint fk_lcjjujsk43xp8ah7qht7sd1dh foreign key (level_id) references code.education_levels (id);

create index idx_fa43felt9lxq3wse8lp20bdgg on base.course_journals_tags (course_journal_id);
create index idx_fx0k2hychuov557bedalysbex on base.courses_categories (course_id);
create index idx_lm5drr3y1vinuxh8pqi0up3pw on base.course_journal_hours (journal_id);
alter table base.major_directors add level_id int4;
alter table base.major_directors alter column level_id set not null;
alter table his.edu_course_profiles drop general_type_id cascade;
alter table his.edu_course_profiles add column category_id integer;

insert into base.courses_categories(course_id,course_category_id)
select id,category_id from base.courses where category_id is not null;

--danger....
alter table base.course_profiles drop general_type_id cascade;
alter table base.courses drop general_type_id cascade;
alter table base.courses drop category_id cascade;
drop table code.course_general_types cascade;

--edu.program
create table edu.program_prerequisites (id bigint not null, course_id bigint not null, prerequisite_id bigint not null, program_id bigint not null);
alter table edu.program_prerequisites add constraint pk_ejooikitt3cj7onfpr7mep1tg primary key (id);
create index idx_i4rlfwsjdqfqvi0cep3ke92nj on edu.program_prerequisites (program_id);
alter table edu.program_prerequisites add constraint fk_8c8deivak1he7o0ptisx0rlog foreign key (prerequisite_id) references base.courses (id);
alter table edu.program_prerequisites add constraint fk_no54i5e4i3fkbed635lpif5s3 foreign key (course_id) references base.courses (id);
alter table edu.program_prerequisites add constraint fk_6v54f49o1j1uyx3scjcrlnlco foreign key (program_id) references edu.programs (id);

alter table edu.programs add opinions varchar(200);

alter table edu.major_plans add credit_hours int4 default 0;
alter table edu.major_plans alter credit_hours set not null;
alter table edu.major_plans add column hour_ratios varchar(50);
update edu.major_plans set hour_ratios=' ' where hour_ratios is null;
alter table edu.major_plans alter hour_ratios set not null;

alter table edu.major_course_groups add column required boolean default true;
alter table edu.major_course_groups alter required set not null;
update edu.major_course_groups set hour_ratios=' ' where hour_ratios is null;
alter table edu.major_course_groups alter hour_ratios set not null;
alter table edu.major_course_groups alter rank_id drop not null;
alter table edu.major_course_groups add column departments varchar(100);

alter table edu.executive_plans add column credit_hours integer default 0;
alter table edu.executive_plans alter credit_hours set not null;
alter table edu.executive_plans add column hour_ratios varchar(50);
update edu.executive_plans set hour_ratios=' ' where hour_ratios is null;
alter table edu.executive_plans alter hour_ratios set not null;

alter table edu.executive_course_groups add column required boolean default true;
alter table edu.executive_course_groups alter required set not null;
update edu.executive_course_groups set hour_ratios=' ' where hour_ratios is null;
alter table edu.executive_course_groups alter hour_ratios set not null;
alter table edu.executive_course_groups alter rank_id drop not null;

alter table edu.std_plans add credit_hours int4 default 0;
alter table edu.std_plans alter credit_hours set not null;
alter table edu.std_plans add column hour_ratios varchar(50);
update edu.std_plans set hour_ratios=' ' where hour_ratios is null;
alter table edu.std_plans alter hour_ratios set not null;

alter table edu.std_course_groups add column required boolean default true;
alter table edu.std_course_groups alter required set not null;
update edu.std_course_groups set hour_ratios=' ' where hour_ratios is null;
alter table edu.std_course_groups alter hour_ratios set not null;
alter table edu.std_course_groups alter rank_id drop not null;

--edu.grade
alter table edu.exam_grades alter column score_text type varchar(10);
alter table edu.ga_grades alter column score_text type varchar(10);
alter table edu.course_grades alter column score_text type varchar(10);

--edu.course
alter table edu.lessons rename column hours to credit_hours;
alter table edu.lessons alter learning_hours type float4;
alter table edu.lessons alter learning_hours drop default;

alter table edu.teaching_plans drop doc_locale cascade;
alter table edu.teaching_plans add column exam_hours integer default 0;
alter table edu.teaching_plans alter exam_hours set not null;
alter table edu.teaching_plans add column lesson_hours integer default 0;
alter table edu.teaching_plans alter lesson_hours set not null;
alter table edu.teaching_plans add column opinions varchar(300);
alter table edu.teaching_plans alter writer_id set not null;

alter table edu.syllabus_topic_hours drop weeks cascade;
alter table edu.syllabuses add column opinions varchar(300);
alter table edu.syllabuses rename column locale to doc_locale;
alter table edu.syllabuses add constraint uk_53qtlhikj7jd9epqcxw2cqlm unique (semester_id,course_id,writer_id,doc_locale);
alter table edu.teaching_plans add constraint uk_gn499yuabis8sdu4iq6dbkt35 unique (clazz_id);
alter table edu.course_tasks add column renew_syllabus boolean;
alter table edu.course_tasks add column syllabus_required boolean default true;
alter table edu.course_tasks alter syllabus_required set not null;

--flow
create table flow.edu_new_course_applies (id bigint not null, applicant_id bigint not null, begin_on date not null, category_id integer not null, code varchar(255), credit_hours integer default 0 not null, default_credits float4 not null, department_id integer not null, en_name varchar(255), end_on date, exam_mode_id integer not null, grading_mode_id integer not null, module_id integer, name varchar(255) not null, nature_id integer not null, opinions varchar(200), project_id integer not null, rank_id integer, status integer not null, updated_at timestamptz default current_timestamp not null, week_hours integer default 0 not null);
create table flow.edu_new_course_applies_tags (course_tag_id integer not null, new_course_apply_id bigint not null);
create table flow.edu_new_course_apply_hours (id bigint not null, course_apply_id bigint not null, credit_hours integer default 0 not null, nature_id integer not null, weeks integer default 0 not null);
create table flow.edu_new_course_categories (id integer not null, begin_on date not null, code varchar(20) not null, en_name varchar(300), end_on date, name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);
create table flow.edu_new_course_departs (id bigint not null, code varchar(255) not null, depart_id integer not null, school_id integer not null);

alter table flow.edu_new_course_applies add constraint pk_c2hfstwqwcmyo86xfc4v4llu3 primary key (id);
alter table flow.edu_new_course_applies_tags add constraint pk_6n9cq37up5f509auhglv3lqqk primary key (new_course_apply_id,course_tag_id);
alter table flow.edu_new_course_apply_hours add constraint pk_9o8bhp7vs4pxwkcyeluduysai primary key (id);
alter table flow.edu_new_course_categories add constraint pk_ooyymrpkh76y78k7y935mt7l4 primary key (id);
alter table flow.edu_new_course_departs add constraint pk_jbaiwlql5545t7atoyyltjl6p primary key (id);

alter table flow.edu_new_course_applies add constraint fk_27hoy1o9c07bt4tn6i0e5995 foreign key (project_id) references base.projects (id);
alter table flow.edu_new_course_applies add constraint fk_16m7eofbd6bcstu4riw5ni7yv foreign key (exam_mode_id) references code.exam_modes (id);
alter table flow.edu_new_course_applies add constraint fk_o8g0dsa3lxr5xf3q13dska24y foreign key (grading_mode_id) references code.grading_modes (id);
alter table flow.edu_new_course_applies add constraint fk_eac9ye2h4tti5u6rrp4b3y2au foreign key (rank_id) references code.course_ranks (id);
alter table flow.edu_new_course_applies add constraint fk_eapmxr3lhv7973w7opidip8qk foreign key (department_id) references base.departments (id);
alter table flow.edu_new_course_applies add constraint fk_em5mh2clty1avltli9m0hfins foreign key (nature_id) references code.course_natures (id);
alter table flow.edu_new_course_applies add constraint fk_tj7ujf1r03hg4y6crjtw1dyir foreign key (module_id) references code.course_modules (id);
alter table flow.edu_new_course_applies add constraint fk_21at3bdoub5eqiyc2eula4d05 foreign key (applicant_id) references base.users (id);
alter table flow.edu_new_course_applies add constraint fk_387g9ljivru3jxdgxsocc0tcy foreign key (category_id) references flow.edu_new_course_categories (id);
alter table flow.edu_new_course_applies_tags add constraint fk_eomhr7dsfvsxkl31jkr8xohxb foreign key (new_course_apply_id) references flow.edu_new_course_applies (id);
alter table flow.edu_new_course_applies_tags add constraint fk_e337xxmu3xn8dytgblifdwpjx foreign key (course_tag_id) references code.course_tags (id);
alter table flow.edu_new_course_apply_hours add constraint fk_tovmuehvajioljjit4b4673fa foreign key (nature_id) references code.teaching_natures (id);
alter table flow.edu_new_course_apply_hours add constraint fk_l7wcmtqtes34hk7qe97j2fco6 foreign key (course_apply_id) references flow.edu_new_course_applies (id);
alter table flow.edu_new_course_departs add constraint fk_60iccrk2txx4k767h41bfseao foreign key (school_id) references base.schools (id);
alter table flow.edu_new_course_departs add constraint fk_gls870o0jcts93hkbx7g4da8s foreign key (depart_id) references base.departments (id);

alter table flow.edu_new_course_categories add constraint edu_new_course_categories_code_key unique (code);
create index idx_2mk8rl7id68acmo8sk7h8gs4b on flow.edu_new_course_applies_tags (new_course_apply_id);
create index idx_pfxpk2do32b5txki4hkw2jnxn on flow.edu_new_course_apply_hours (course_apply_id);

--comments
comment on table base.course_journals_tags is '课程标签@edu';
comment on column base.course_journals_tags.course_journal_id is '课程记录ID';
comment on column base.course_journals_tags.course_tag_id is '课程标签ID';
comment on table base.courses_categories is '课程分类@edu';
comment on column base.courses_categories.course_category_id is '课程分类ID';
comment on column base.courses_categories.course_id is '课程基本信息ID';
comment on column base.course_profiles.category_id is '课程分类ID';
comment on column base.courses.module_id is '课程模块ID';
comment on column base.courses.rank_id is '课程属性ID';
comment on column base.major_directors.level_id is '培养层次ID';
comment on table code.course_category_dimensions is '课程分类维度@edu';
comment on column code.course_category_dimensions.id is '非业务主键:code';
comment on column code.course_category_dimensions.begin_on is '生效日期';
comment on column code.course_category_dimensions.code is '代码';
comment on column code.course_category_dimensions.en_name is '英文名称';
comment on column code.course_category_dimensions.end_on is '失效日期';
comment on column code.course_category_dimensions.name is '名称';
comment on column code.course_category_dimensions.remark is '备注';
comment on column code.course_category_dimensions.updated_at is '修改时间';
comment on table code.course_categories is '课程分类';
comment on column code.course_categories.color is '分类颜色';
comment on column code.course_categories.dimension_id is '课程分类维度ID';
comment on column code.course_categories.indexno is '顺序号';
comment on column code.course_categories.parent_id is '课程分类ID';
comment on table edu.program_prerequisites is '培养方案先修课程@program';
comment on column edu.program_prerequisites.id is '非业务主键:datetime';
comment on column edu.program_prerequisites.course_id is '课程ID';
comment on column edu.program_prerequisites.prerequisite_id is '先修课程ID';
comment on column edu.program_prerequisites.program_id is '专业培养方案ID';
comment on column edu.course_tasks.renew_syllabus is '是否需要修订大纲';
comment on column edu.course_tasks.syllabus_required is '是否需要大纲';
comment on column edu.executive_course_groups.required is '是否必须完成';
comment on column edu.executive_plans.credit_hours is '要求学时';
comment on column edu.executive_plans.hour_ratios is '学时比例';
comment on column edu.major_course_groups.departments is '开课院系说明';
comment on column edu.major_course_groups.required is '是否必须完成';
comment on column edu.major_plans.credit_hours is '要求学时';
comment on column edu.major_plans.hour_ratios is '学时比例';
comment on column edu.programs.opinions is '审核意见';
comment on column edu.std_course_groups.required is '是否必须完成';
comment on column edu.std_plans.credit_hours is '要求学时';
comment on column edu.std_plans.hour_ratios is '学时比例';
comment on column edu.syllabuses.opinions is '驳回意见';
comment on column edu.teaching_plans.exam_hours is '考核课时';
comment on column edu.teaching_plans.lesson_hours is '课堂课时';
comment on column edu.teaching_plans.opinions is '驳回意见';
comment on table flow.edu_new_course_applies is '新开课程申请@course.flow';
comment on column flow.edu_new_course_applies.id is '非业务主键:datetime';
comment on column flow.edu_new_course_applies.applicant_id is '通用人员信息ID';
comment on column flow.edu_new_course_applies.begin_on is '设立日期';
comment on column flow.edu_new_course_applies.category_id is '新开课程分类ID';
comment on column flow.edu_new_course_applies.code is '课程代码';
comment on column flow.edu_new_course_applies.credit_hours is '学时';
comment on column flow.edu_new_course_applies.default_credits is '默认学分';
comment on column flow.edu_new_course_applies.department_id is '院系ID';
comment on column flow.edu_new_course_applies.en_name is '课程英文名';
comment on column flow.edu_new_course_applies.end_on is '失效日期';
comment on column flow.edu_new_course_applies.exam_mode_id is '考核方式ID';
comment on column flow.edu_new_course_applies.grading_mode_id is '成绩记录方式ID';
comment on column flow.edu_new_course_applies.module_id is '课程模块ID';
comment on column flow.edu_new_course_applies.name is '课程名称';
comment on column flow.edu_new_course_applies.nature_id is '课程性质ID';
comment on column flow.edu_new_course_applies.opinions is '审核意见';
comment on column flow.edu_new_course_applies.project_id is '项目ID';
comment on column flow.edu_new_course_applies.rank_id is '课程属性ID';
comment on column flow.edu_new_course_applies.status is '状态';
comment on column flow.edu_new_course_applies.updated_at is '更新时间';
comment on column flow.edu_new_course_applies.week_hours is '周课时';
comment on table flow.edu_new_course_applies_tags is '标签@course.flow';
comment on column flow.edu_new_course_applies_tags.course_tag_id is '课程标签ID';
comment on column flow.edu_new_course_applies_tags.new_course_apply_id is '新开课程申请ID';
comment on table flow.edu_new_course_apply_hours is '新开课程课时@course.flow';
comment on column flow.edu_new_course_apply_hours.id is '非业务主键:datetime';
comment on column flow.edu_new_course_apply_hours.course_apply_id is '新开课程申请ID';
comment on column flow.edu_new_course_apply_hours.credit_hours is '学时';
comment on column flow.edu_new_course_apply_hours.nature_id is '授课性质ID';
comment on column flow.edu_new_course_apply_hours.weeks is '周数';
comment on table flow.edu_new_course_categories is '新开课程分类@course.flow';
comment on column flow.edu_new_course_categories.id is '非业务主键:auto_increment';
comment on column flow.edu_new_course_categories.begin_on is '生效日期';
comment on column flow.edu_new_course_categories.code is '代码';
comment on column flow.edu_new_course_categories.en_name is '英文名称';
comment on column flow.edu_new_course_categories.end_on is '失效日期';
comment on column flow.edu_new_course_categories.name is '名称';
comment on column flow.edu_new_course_categories.remark is '备注';
comment on column flow.edu_new_course_categories.updated_at is '修改时间';
comment on table flow.edu_new_course_departs is '新开课程院系编码@course.flow';
comment on column flow.edu_new_course_departs.id is '非业务主键:datetime';
comment on column flow.edu_new_course_departs.code is '代码';
comment on column flow.edu_new_course_departs.depart_id is '部门组织机构信息ID';
comment on column flow.edu_new_course_departs.school_id is '学校信息ID';
comment on column his.edu_course_profiles.category_id is '课程分类ID';
