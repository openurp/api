insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.38.0',now(),'增加教学大纲和督导听课');

--audit results
alter table edu.course_audit_results rename to audit_course_results;
alter table edu.group_audit_results rename to audit_group_results;
alter table edu.plan_audit_results rename to audit_plan_results;

alter table edu.audit_course_results add taking bool default false;
alter table edu.audit_course_results add predicted bool default false;
alter table edu.audit_course_results add has_grade bool default false;
alter table edu.audit_course_results add passed_way int4;

update edu.audit_course_results set predicted=passed;
update edu.audit_course_results set scores='--' where scores is null;
alter table edu.audit_course_results alter column scores set not null;
update edu.audit_course_results set has_grade=true where length(scores)>0 and scores<>'--';
update edu.audit_course_results cr set has_grade=true where has_grade=false
and exists(select * from edu.audit_group_results gr,edu.audit_plan_results pr ,edu.course_grades cg
		  where cr.group_result_id=gr.id and gr.plan_result_id=pr.id and pr.std_id=cg.std_id and cr.course_id=cg.course_id);

alter table  edu.audit_plan_results drop column required_count;
alter table  edu.audit_plan_results drop column passed_count;
alter table  edu.audit_plan_results drop column converted_credits;
alter table  edu.audit_group_results drop column required_count;
alter table  edu.audit_group_results drop column passed_count;

alter table  edu.audit_plan_results add owed_credits float default 0;
alter table  edu.audit_plan_results add owed_credits2 float default 0;
alter table  edu.audit_plan_results add owed_credits3 float default 0;
alter table  edu.audit_group_results add owed_credits float default 0;
alter table  edu.audit_group_results add owed_credits2 float default 0;
alter table  edu.audit_group_results add owed_credits3 float default 0;

alter table edu.audit_plan_results add predicted bool default false;
update edu.audit_plan_results  set predicted=passed;
--program
alter table edu.share_plan_courses add compulsory bool default false;
update  edu.share_plan_courses set compulsory=false where compulsory is null;
alter table edu.share_plan_courses alter compulsory set not null;


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
--program
alter table edu.major_course_groups add allow_unplanned bool default false;
alter table edu.execution_course_groups add allow_unplanned bool default false;
alter table edu.std_course_groups add allow_unplanned bool default false;
create table edu.exempt_courses_stds (exempt_course_id bigint not null, student_id bigint not null);

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

--insert into base.calendar_stages(id,name,start_week,end_week,en_name,vacation) values(1,'上课周',1,16,'Long',false);
--insert into base.calendar_stages(id,name,start_week,end_week,en_name,vacation) values(2,'考试周',17,18,'Short',false);

--insert into code.course_modules(id,code,name,begin_on,updated_at) values(1,'1','通识课模块',current_date,now());
--insert into code.course_modules(id,code,name,begin_on,updated_at) values(2,'2','学科专业模块',current_date,now());
--insert into code.course_modules(id,code,name,begin_on,updated_at) values(2,'3','实践模块',current_date,now());

insert into code.syllabus_topic_labels(id,code,name,begin_on,updated_at) values(1,'01','课程思政',current_date,current_timestamp);
insert into code.syllabus_topic_labels(id,code,name,begin_on,updated_at) values(2,'02','本章重点',current_date,current_timestamp);
insert into code.syllabus_topic_labels(id,code,name,begin_on,updated_at) values(3,'03','本章难点',current_date,current_timestamp);
insert into code.syllabus_topic_labels(id,code,name,begin_on,updated_at) values(4,'04','学生学习成果',current_date,current_timestamp);


create table code.graduate_objectives (id integer not null, begin_on date not null, end_on date, code varchar(20) not null, en_name varchar(300), name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);

insert into code.graduate_objectives(id,code,name,begin_on,updated_at) values(1,'1','思想政治素质',current_date,current_timestamp);
insert into code.graduate_objectives(id,code,name,begin_on,updated_at) values(2,'2','诚信品质',current_date,current_timestamp);
insert into code.graduate_objectives(id,code,name,begin_on,updated_at) values(3,'3','身心健康',current_date,current_timestamp);
insert into code.graduate_objectives(id,code,name,begin_on,updated_at) values(4,'4','通识知识/学科专业基础知识',current_date,current_timestamp);
insert into code.graduate_objectives(id,code,name,begin_on,updated_at) values(5,'5','数据和信息素养',current_date,current_timestamp);
insert into code.graduate_objectives(id,code,name,begin_on,updated_at) values(6,'6','创新意识',current_date,current_timestamp);
insert into code.graduate_objectives(id,code,name,begin_on,updated_at) values(7,'7','实践能力',current_date,current_timestamp);
insert into code.graduate_objectives(id,code,name,begin_on,updated_at) values(8,'8','国际视野',current_date,current_timestamp);

create table code.course_ranks (id integer not null, begin_on date not null, end_on date, code varchar(20) not null, en_name varchar(300), name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);

insert into code.course_ranks(id,code,name,begin_on,updated_at) values(1,'1','必修',current_date,current_timestamp);
insert into code.course_ranks(id,code,name,begin_on,updated_at) values(2,'2','限选',current_date,current_timestamp);
insert into code.course_ranks(id,code,name,begin_on,updated_at) values(3,'3','任选',current_date,current_timestamp);

create table code.course_ability_subjects (id integer not null, begin_on date not null, end_on date, code varchar(20) not null, en_name varchar(300), name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);
insert into code.course_ability_subjects(id,code,name,begin_on,updated_at)
values(1,'1','英语分级',current_date,now());

alter table code.course_ability_rates add subject_id int4;
update code.course_ability_rates set subject_id=1;
