insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.24.0',now(),'简化评教结构');

alter table qos.evaluation_criterias rename to assess_criterias;
alter table qos.assess_criterias add begin_on date;
alter table qos.assess_criterias add end_on date;
update qos.assess_criterias set begin_on = current_date -1;

alter table qos.evaluation_criteria_items rename to assess_grades;
alter table qos.assess_grades add grade integer ;
alter table qos.assess_grades add description varchar(255);
update qos.assess_grades set grade = id,description=name where grade is null;
alter table qos.assess_criterias drop column depart_id;
--indicators
alter table qos.question_types rename to indicators;
alter table qos.questions rename column question_type_id to indicator_id;
alter table qos.indicators add code varchar(20);
update qos.indicators set code=priority;
alter table qos.indicators drop column priority;

--options
alter table qos.options rename column option_group_id to group_id;

--stat result
alter table qos.question_results rename column question_type_id to indicator_id;

--evalute result
alter table qos.evaluate_results rename column evaluate_at to updated_at;

--feedback
alter table  qos.text_evaluations rename to feedbacks;
alter table qos.feedbacks add semester_id int4;
alter table qos.feedbacks add teach_depart_id int4;
alter table qos.feedbacks add course_id int8;
alter table qos.feedbacks rename student_id to std_id;
alter table qos.feedbacks add crn varchar(20);
alter table qos.feedbacks add grade varchar(50);
alter table qos.feedbacks rename evaluate_at to updated_at;
update qos.feedbacks  f set (semester_id,crn,teach_depart_id,course_id)=(select c.semester_id,c.crn,c.teach_depart_id,course_id from edu.clazzes c where c.id=f.clazz_id);

--finalcomment
create table qos.final_comments (semester_id integer not null, updated_at timestamp not null, course_id bigint not null,
								 teacher_id bigint not null, std_id bigint not null, contents varchar(500) not null,
								 id bigint not null, crn varchar(255) not null, grade varchar(255) not null, teach_depart_id integer not null);
insert into qos.final_comments(semester_id,updated_at,course_id,teacher_id,std_id,contents,id,crn,grade,teach_depart_id)
select c.semester_id,r.updated_at,c.course_id,r.teacher_id,r.student_id,r.remark,r.id,c.crn,
'--' grade,c.teach_depart_id from qos.evaluate_results r,edu.clazzes c where c.id=r.clazz_id
and r.remark is not null;

drop table qos.course_question_type_stats cascade;
drop table qos.course_eval_stats cascade;
drop table qos.course_option_stats cascade;
drop table qos.course_question_stats cascade;

alter table qos.clazz_eval_stats rename to course_eval_stats;
alter table qos.clazz_question_type_stats rename to course_indicator_stats;
alter table qos.course_eval_stats add crn varchar(20);
alter table qos.course_eval_stats add course_id bigint;
alter table qos.course_eval_stats add teacher_depart_id int4;
alter table qos.course_eval_stats add teach_depart_id int4;
alter table qos.course_eval_stats add project_id int4;
alter table qos.course_eval_stats rename valid_tickets to tickets;

update qos.course_eval_stats a set (crn,project_id)=(select b.crn,b.project_id from edu.clazzes b where b.id=a.clazz_id);
update qos.course_eval_stats a set course_id=(select b.course_id from edu.clazzes b where b.id=a.clazz_id);
update qos.course_eval_stats a set teach_depart_id=(select b.teach_depart_id from edu.clazzes b where b.id=a.clazz_id);
update qos.course_eval_stats a set teacher_depart_id=(select b.department_id from base.teachers b where b.id=a.teacher_id);

alter table qos.course_eval_stats drop column total_tickets;
alter table qos.course_eval_stats drop column clazz_id;
alter table qos.course_eval_stats drop column total_score;
alter table qos.course_eval_stats drop column add_score;
alter table qos.course_eval_stats rename column avg_score to score;
alter table qos.course_eval_stats add category_rank int4 default 0;
alter table qos.course_eval_stats add grade_id int4 ;
alter table qos.course_eval_stats add category_id int4 ;
alter table qos.course_eval_stats add publish_status int4 default 2 ;
alter table qos.course_eval_stats rename column stat_at to updated_at;

alter table qos.clazz_question_stats rename to course_question_stats;
alter table qos.course_question_stats rename column eval_stat_id to stat_id;
alter table qos.course_question_stats rename column avg_score to score;
alter table qos.clazz_option_stats rename to course_option_stats;

alter table qos.course_indicator_stats rename column eval_stat_id to stat_id;
alter table qos.course_indicator_stats rename column question_type_id to indicator_id;

drop table qos.teacher_option_stats cascade;
drop table qos.teacher_question_stats cascade;
drop table qos.teacher_question_type_stats cascade;
drop table qos.teacher_eval_stats cascade;
drop table qos.school_option_stats cascade;
drop table qos.school_question_stats cascade;
drop table qos.school_question_type_stats cascade;

alter table  qos.school_eval_stats drop column total_tickets;
alter table  qos.school_eval_stats drop column tickets;
alter table  qos.school_eval_stats drop column add_score;
alter table  qos.school_eval_stats drop column total_score;
alter table  qos.school_eval_stats drop column questionnaire_id;
alter table  qos.school_eval_stats drop column published;
alter table  qos.school_eval_stats add column course_count int4 default 0;
alter table  qos.school_eval_stats rename column stat_at to updated_at;
alter table  qos.school_eval_stats add column project_id int4;
update qos.school_eval_stats set project_id=(select min(id) from base.projects);
update qos.school_eval_stats a set course_count=(select count(id) from qos.course_eval_stats c where  a.semester_id=c.semester_id);

drop table qos.depart_option_stats cascade;
drop table qos.depart_question_stats cascade;
drop table qos.depart_question_type_stats cascade;
alter table  qos.depart_eval_stats drop column total_tickets;
alter table  qos.depart_eval_stats drop column tickets;
alter table  qos.depart_eval_stats drop column add_score;
alter table  qos.depart_eval_stats drop column total_score;
alter table  qos.depart_eval_stats add column course_count int4 default 0;
alter table  qos.depart_eval_stats rename column stat_at to updated_at;
alter table  qos.depart_eval_stats add column project_id int4;
update qos.depart_eval_stats set project_id=(select min(id) from base.projects);
update qos.depart_eval_stats a set course_count=(select count(id) from qos.course_eval_stats c where c.teach_depart_id=a.department_id and a.semester_id=c.semester_id);


drop table qos.teacher_remessages_students;
drop table qos.teacher_remessages;


---plan course label
create table base.plan_course_labels (symbol varchar(10) not null, id integer not null, begin_on date not null, end_on date, updated_at timestamp not null, code varchar(20) not null unique, en_name varchar(300), name varchar(100) not null, remark varchar(200));
alter table base.plan_course_labels add constraint pk_f50ugm4kd9odv9vc6q8d0qs3b primary key (id);

create table edu.major_plan_courses_labels (major_plan_course_id bigint not null, plan_course_label_id integer not null);
alter table edu.major_plan_courses_labels add constraint pk_h5pie0pd7m9vcg0y55du0m3vf primary key (major_plan_course_id,plan_course_label_id);

create table edu.execution_plan_courses_labels (execution_plan_course_id bigint not null, plan_course_label_id integer not null);
alter table edu.execution_plan_courses_labels add constraint pk_ioyg353p4y54dour5lo2nfff0 primary key (execution_plan_course_id,plan_course_label_id);

create table edu.std_plan_courses_labels (std_plan_course_id bigint not null, plan_course_label_id integer not null);
alter table edu.std_plan_courses_labels add constraint pk_1pt84bi85mv6hfqqp0evj82a3 primary key (std_plan_course_id,plan_course_label_id);

--course assess category
alter table base.course_assess_categories rename to course_categories;
