insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.38.0',now(),'增加教学大纲和督导听课');

--course
alter table base.courses drop column cluster_id;
alter table base.courses add column curriculum_id bigint;
alter table base.courses add column grading_mode_id int4;
update base.courses c set grading_mode_id=(select min(cg.grading_mode_id) from base.courses_grading_modes cg where cg.course_id=c.id);
update base.courses c set grading_mode_id=1 where grading_mode_id is null;

alter table base.course_hours rename column teaching_nature_id to nature_id;
drop table base.courses_grading_modes;
drop table base.course_clusters;
drop table base.courses_prerequisites;

--syllabus_files
alter table edu.syllabus_files rename to syllabus_docs;

alter table edu.syllabus_docs add  column  writer_id bigint;
alter table edu.syllabus_docs add  column  auditor_id bigint;
alter table edu.syllabus_docs add  column  audit_at timestamptz;
alter table edu.syllabus_docs add  column  semester_id int4;
alter table edu.syllabus_docs add  column  department_id int4;
alter table edu.syllabus_docs add  column  course_id bigint;
alter table edu.syllabus_docs add  column  office_id bigint;
alter table edu.syllabus_docs add  column  begin_on date;
alter table edu.syllabus_docs add  column  end_on date;
alter table edu.syllabus_docs add  column  status int4;
alter table edu.syllabus_docs add  column  updated_at timestamptz;

update edu.syllabus_docs d set (writer_id,auditor_id,audit_at,semester_id,department_id,course_id,office_id,begin_on,end_on,status,updated_at)
=(select s.author_id,s.auditor_id,s.audit_at,s.semester_id,s.department_id,s.course_id,s.teaching_office_id,begin_on,end_on,status,updated_at from edu.syllabuses s
 where s.id=d.syllabus_id);

alter table edu.syllabus_docs rename file_size to doc_size;
alter table edu.syllabus_docs rename file_path to doc_path;

----curriculum ??
create table base.curriculums (project_id integer not null, en_name varchar(255), office_id bigint, course_count integer default 0 not null, department_id integer not null, id bigint not null, remark varchar(255), begin_on date not null, director_id bigint, end_on date, name varchar(100) not null, code varchar(255) not null, updated_at timestamptz default current_timestamp not null);
create table base.curriculums_teachers (curriculum_id bigint not null, teacher_id bigint not null);

create table code.course_modules (id integer not null, begin_on date not null, end_on date, code varchar(20) not null, en_name varchar(300), name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);

create table edu.syllabus_assess_percents (id bigint not null, objectives varchar(255), "percent" integer default 0 not null, syllabus_id bigint not null, component varchar(255), grade_type_id integer not null);
create table edu.syllabus_credit_hours (weeks integer default 0 not null, credit_hours integer default 0 not null, id bigint not null, nature_id integer not null, syllabus_id bigint not null);
create table edu.syllabus_lesson_hours (weeks integer default 0 not null, credit_hours integer default 0 not null, lesson_id bigint not null, id bigint not null, nature_id integer not null, learning integer default 0 not null);
create table edu.syllabus_lessons (id bigint not null, topic varchar(255) not null, syllabus_id bigint not null);
create table edu.syllabus_objectives (name varchar(255) not null, outcome_codes varchar(255), id bigint not null, syllabus_id bigint not null, code varchar(255) not null, contents varchar(255) not null);
create table edu.syllabus_outcomes (name varchar(255) not null, id bigint not null, syllabus_id bigint not null, code varchar(255) not null, contents varchar(255) not null);
create table edu.syllabus_texts (name varchar(255) not null, contents varchar(255) not null, id bigint not null, indexno varchar(255) not null, syllabus_id bigint not null, parent_id bigint);
create table edu.syllabus_topics (name varchar(255) not null, contents varchar(255) not null, id bigint not null, objectives varchar(255), syllabus_id bigint not null);
create table edu.syllabus_topics_methods (syllabus_topic_id bigint not null, teaching_method_id integer not null);
create table edu.syllabuses_corequisites (syllabus_id bigint not null, curriculum_id bigint not null);
create table edu.syllabuses_levels (syllabus_id bigint not null, education_level_id integer not null);
create table edu.syllabuses_majors (syllabus_id bigint not null, major_id bigint not null);
create table edu.syllabuses_methods (syllabus_id bigint not null, teaching_method_id integer not null);
create table edu.syllabuses_prerequisites (syllabus_id bigint not null, curriculum_id bigint not null);
create table edu.syllabuses_subsequents (syllabus_id bigint not null, curriculum_id bigint not null);
create table edu.syllabuses_textbooks (syllabus_id bigint not null, textbook_id bigint not null);

--calendar
alter table base.calendar_stages add start_week int4 default 1;
alter table base.calendar_stages add end_week int4 default 16;
alter table base.calendar_stages add en_name varchar(40);

teachers
office_id
