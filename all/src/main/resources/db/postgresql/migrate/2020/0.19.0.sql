
create schema edu_lesson;
alter table edu_clazz.lessons set schema edu_lesson;
alter table edu_clazz.lessons_teachers set schema edu_lesson;
alter table edu_clazz.lessons_rooms set schema edu_lesson;

alter  table edu_clazz.materials set schema edu_textbook;
alter  table edu_clazz.materials_books set schema edu_textbook;

alter table code_gb.education_degrees rename column conclusion to result_id;

create schema hr_profile;
alter table edu_course.teacher_blogs set schema hr_profile;
--drop table edu_course.syllabus_section_titles cascade;
--drop table edu_course.course_blogs_teachers cascade;
--drop table edu_course.syllabus_sections cascade;
--drop table edu_course.lecture_plans cascade;
alter table edu_course.course_blogs rename to course_profiles;
create table edu_course.syllabus_files (id bigint not null, doc_locale varchar(255) not null, file_path varchar(255) not null, file_size integer not null, mime_type varchar(255) not null, syllabus_id bigint not null);
comment on table edu_course.syllabus_files is '教学大纲附件';
comment on column edu_course.syllabus_files.id is '非业务主键:datetime';
comment on column edu_course.syllabus_files.doc_locale is '语言';
comment on column edu_course.syllabus_files.file_path is '附件关键字';
comment on column edu_course.syllabus_files.file_size is '大小';
comment on column edu_course.syllabus_files.mime_type is '文件类型';
comment on column edu_course.syllabus_files.syllabus_id is '课程教学大纲ID';
alter table edu_course.syllabus_files add constraint pk_45v91qs7o9qg0dvr6dxtp27yc primary key (id);
create index idx_mj7o7fx2dl4p7omsdqdln9122 on edu_course.syllabus_files (syllabus_id);
alter table edu_course.syllabus_files add constraint fk_4rixrpk485v21t4manv83f2qu foreign key (syllabus_id) references edu_course.syllabuses (id);
comment on table edu_course.syllabuses is '课程教学大纲';
alter table edu_course.syllabuses drop name cascade;
alter table edu_course.syllabuses drop file_size cascade;
alter table edu_course.syllabuses drop passed cascade;
alter table edu_course.syllabuses drop mime_type cascade;
alter table edu_course.syllabuses drop file_path cascade;
alter table edu_course.syllabuses drop doc_locale cascade;
alter table edu_course.syllabuses add column department_id integer;
alter table edu_course.syllabuses alter department_id set not null;
alter table edu_course.syllabuses add column status integer;
alter table edu_course.syllabuses alter status set not null;
alter table edu_course.syllabuses add column teaching_group_id bigint;
alter table edu_course.syllabuses add constraint fk_bcjr4d78cmwxtof1akent1apw foreign key (department_id) references base.departments (id);
alter table edu_course.syllabuses add constraint fk_c8f0kk3qb1apqjpjk5g2o2g6l foreign key (teaching_group_id) references edu_base.teaching_groups (id);

alter table code_hb.student_punishment_types rename to std_punishment_types;
drop table code_hb.student_alter_types cascade;
drop table code_hb.student_alter_reasons cascade;

comment on table code_hb.fee_origins is 'FeeOrigin?';
comment on table code_hb.uee_subject_types is 'UeeSubjectType?';
comment on table code_hb.std_alter_reasons is 'StdAlterReason?';
comment on table code_hb.std_alter_types is 'StdAlterType?';
comment on table code_hb.student_statuses is 'StudentStatus?';

create table edu_base.teachers_projects (project_id integer not null, teacher_id bigint not null);
comment on table edu_base.teachers_projects is '所在项目';
comment on column edu_base.teachers_projects.project_id is '项目ID';
comment on column edu_base.teachers_projects.teacher_id is '教师信息ID';
alter table edu_base.teachers_projects add constraint pk_ksmq83maw97gk85mth4s2g99n primary key (teacher_id,project_id);
create index idx_swgo4qm8hl9fiixhbkynf4kmp on edu_base.teachers_projects (teacher_id);
alter table edu_base.teachers_projects add constraint fk_7yto5qcqlg6uvluupiche0ub9 foreign key (teacher_id) references edu_base.teachers (id);
alter table edu_base.teachers_projects add constraint fk_sxnuy9llj90w0sxmvtcnl0ll4 foreign key (project_id) references edu_base.projects (id);
insert into edu_base.teachers_projects (teacher_id,project_id)select id ,project_id from edu_base.teachers;

--alter table edu_base.teachers drop project_id cascade;
alter table edu_base.teachers add column education_degree_id integer;
alter table edu_base.teachers add column school_id integer;
alter table edu_base.teachers add column degree_id integer;
update edu_base.teachers set school_id=(select s.id from base.schools s);
alter table edu_base.teachers alter school_id set not null;
alter table edu_base.teachers drop constraint fk_1onjcmor1ox39txw1dk62ht35 cascade;
alter table edu_base.teachers add constraint fk_cny3qap1t5kigpnr697g3cixx foreign key (degree_id) references code_gb.degrees (id);
alter table edu_base.teachers add constraint fk_nuqy5yvj4u3uw5r7d4u78x9tl foreign key (education_degree_id) references code_gb.education_degrees (id);
alter table edu_base.teachers add constraint fk_25tvrvw3ww2p7mbt62abrbwev foreign key (school_id) references base.schools (id);
alter table edu_base.teachers drop constraint uk_oa2tjed13a1wvudv8ce1b71fg cascade;
alter table edu_base.teachers add constraint uk_cd1k6xwg9jqtiwx9ybnxpmoh9 unique (user_id);

alter table edu_base.teaching_groups add column project_id integer;
alter table edu_base.teaching_groups alter project_id set not null;
alter table edu_base.teaching_groups add constraint fk_kkdwqyjk5wby463hfv6kb8yw9 foreign key (project_id) references edu_base.projects (id);
alter table edu_base.teaching_groups add constraint uk_wtvrq9aup4j185u5y60wpx04 unique (project_id,code);

alter table edu_clazz.clazzes drop material_id cascade;
alter table edu_clazz.clazzes drop constraint fk_rn2odkel0yngmb9ec4i4l60hy cascade;
