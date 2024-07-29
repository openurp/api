insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.26.0',now(),'重新映射代码、配置、模型和日志的命名规则');

alter table base.book_award_types rename to c_book_award_types;
alter table base.book_types rename to c_book_types;
alter table base.course_ability_rates rename to c_course_ability_rates;
alter table base.course_categories rename to c_course_categories;
alter table base.course_types rename to c_course_types;
alter table std.fee_types set schema base;
alter table base.fee_types rename to c_fee_types;
alter table base.plan_course_labels rename to c_plan_course_labels;
alter table base.std_label_types rename to c_std_label_types;
alter table base.std_labels rename to c_std_labels;
alter table base.std_types rename to c_std_types;
alter table base.teacher_types rename to c_teacher_types;
alter table base.user_categories set schema code;
alter table base.department_categories set schema code;

alter table edu.course_arrange_switches rename to cfg_schedule_settings;
alter table edu.cfg_schedule_settings rename  published to time_published;
alter table edu.cfg_schedule_settings add place_published boolean default false;
update edu.cfg_schedule_settings set place_published=time_published;
alter table edu.cfg_schedule_settings add clazz_editable boolean default false;
alter table edu.cfg_schedule_settings add begin_at timestamp;
alter table edu.cfg_schedule_settings add end_at timestamp;
update edu.cfg_schedule_settings  css set (begin_at,end_at)=(select s.begin_on,s.end_on from base.semesters s
where s.id=css.semester_id);

alter table edu.clazzes add depart_arranged bool default true;
alter table edu.clazz_tags rename to c_clazz_tags;
alter table edu.certificate_categories rename to c_certificate_categories;
alter table edu.certificate_subjects rename to c_certificate_subjects;
alter table app.report_templates rename to cfg_transcript_templates;
alter table app.cfg_transcript_templates set schema edu;

alter table app.grade_input_switches set schema edu;
alter table app.grade_input_switches_types set schema edu;
alter table edu.grade_input_switches_types rename to cfg_grade_input_switches_types;
alter table edu.grade_input_switches rename to cfg_grade_input_switches;


alter table edu.grade_rate_items rename to cfg_grade_rate_items;
alter table edu.grade_rate_configs rename to cfg_grade_rate_configs;
alter table edu.exam_alloc_settings rename to cfg_exam_alloc_settings;

alter table app.exam_notices set schema edu;
create schema degree;
create schema log;

alter table hr.capacity_factors rename to cfg_capacity_factors;
alter table hr.factor_segments rename to cfg_factor_segments;

alter table trd.rd_award_grades rename to c_award_grades;
alter table trd.rd_levels rename to c_rd_levels;
alter table trd.rd_achievement_types rename to c_rd_achievement_types;
alter table trd.rd_project_statuses rename to c_rd_project_statuses;
alter table trd.rd_project_categories rename to c_rd_project_categories;

alter table std.transfer_options rename to cfg_transfer_options;
alter table std.transfer_schemes rename to cfg_transfer_schemes;
alter table std.transfer_scopes rename to cfg_transfer_scopes;
alter table std.transfer_scopes_majors rename to cfg_transfer_scopes_majors;
alter table std.transfer_scopes_grades rename to cfg_transfer_scopes_grades;

alter table std.tuition_configs rename to cfg_tuition_configs;

--spa
alter table spa.coupons rename to cfg_coupons;
alter table spa.doc_types rename to cfg_doc_types;
alter table spa.print_configs  rename to cfg_print_configs;
alter table spa.download_logs rename to spa_download_logs;
alter table spa.print_logs rename to spa_print_logs;
alter table spa.spa_download_logs set schema log;
alter table spa.spa_print_logs set schema log;
--qos
alter table qos.assess_grades  rename to cfg_assess_grades;
alter table qos.questionnaires_questions  rename to cfg_questionnaires_questions;
alter table qos.questions  rename to cfg_questions;
alter table qos.option_groups  rename to cfg_option_groups;
alter table qos.options  rename to cfg_options;
alter table qos.indicators  rename to cfg_indicators;
alter table qos.assess_criterias  rename to cfg_assess_criterias;
alter table qos.questionnaires  rename to cfg_questionnaires;
