insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.34.0',now(),'增加cfg');

create schema cfg;

alter table edu.clazzes rename column name to clazz_name;
alter table edu.clazzes rename column locked to capacity_locked;
alter table edu.clazzes rename column reserved to reserved_count;
alter table edu.clazzes rename column actual to std_count;

alter table edu.clazzes add week_hours int4;
update edu.clazzes set week_hours=(select c.week_hours::int4 from base.courses c where c.id=course_id);
alter table edu.clazzes alter week_hours set not null;

alter table edu.cfg_schedule_settings set schema cfg;
alter table cfg.cfg_schedule_settings rename to edu_schedule_settings;
alter table edu.cfg_grade_input_switches set schema cfg;
alter table cfg.cfg_grade_input_switches rename to edu_grade_input_switches;
alter table edu.cfg_grade_input_switches_types set schema cfg;
alter table cfg.cfg_grade_input_switches_types rename to edu_grade_input_switches_types;
alter table edu.cfg_grade_rate_configs set schema cfg;
alter table cfg.cfg_grade_rate_configs rename to edu_grade_rate_configs;
alter table edu.cfg_grade_rate_items set schema cfg;
alter table cfg.cfg_grade_rate_items rename to edu_grade_rate_items;
alter table edu.cfg_schedule_setting_departs set schema cfg;
alter table cfg.cfg_schedule_setting_departs rename to edu_schedule_setting_departs;
alter table edu.cfg_transcript_templates set schema cfg;
alter table cfg.cfg_transcript_templates rename to edu_transcript_templates;

alter table spa.cfg_doc_types set schema cfg;
alter table cfg.cfg_doc_types rename to spa_doc_types;
alter table spa.cfg_print_configs set schema cfg;
alter table cfg.cfg_print_configs rename to spa_print_configs;

alter table edu.cfg_cert_exempt_configs set schema cfg;
alter table cfg.cfg_cert_exempt_configs rename to edu_cert_exempt_configs;
alter table edu.cfg_cert_exempt_settings set schema cfg;
alter table cfg.cfg_cert_exempt_settings rename to edu_cert_exempt_settings;
alter table edu.cfg_cert_exempt_courses set schema cfg;
alter table cfg.cfg_cert_exempt_courses rename to edu_cert_exempt_courses;
alter table edu.certificate_grades_courses rename to certificate_grades_exempts;

alter table edu.cfg_cert_signup_configs set schema cfg;
alter table cfg.cfg_cert_signup_configs rename to edu_cert_signup_configs;
alter table edu.cfg_cert_signup_exclusives set schema cfg;
alter table cfg.cfg_cert_signup_exclusives rename to edu_cert_signup_exclusives;
alter table edu.cfg_cert_signup_scopes set schema cfg;
alter table cfg.cfg_cert_signup_scopes rename to edu_cert_signup_scopes;
alter table edu.cfg_cert_signup_settings set schema cfg;
alter table cfg.cfg_cert_signup_settings rename to edu_cert_signup_settings;

alter table edu.cfg_exam_alloc_settings set schema cfg;
alter table cfg.cfg_exam_alloc_settings rename to edu_exam_alloc_settings;

alter table std.cfg_transfer_options rename to std_transfer_options;
alter table std.std_transfer_options set schema cfg;
alter table std.cfg_transfer_schemes rename to std_transfer_schemes;
alter table std.std_transfer_schemes set schema cfg;
alter table std.cfg_transfer_scopes rename to std_transfer_scopes;
alter table std.std_transfer_scopes set schema cfg;
alter table std.cfg_transfer_scopes_grades rename to std_transfer_scopes_grades;
alter table std.std_transfer_scopes_grades set schema cfg;
alter table std.cfg_transfer_scopes_majors rename to std_transfer_scopes_majors;
alter table std.std_transfer_scopes_majors set schema cfg;

alter table std.cfg_tuition_configs rename to std_tuition_configs;
alter table std.std_tuition_configs set schema cfg;

alter table edu.arrange_suggests rename to schedule_suggests;

alter table std.exemption_credits rename to exch_exempt_credits;

update cfg.edu_schedule_settings ss set begin_at=(select s.begin_on from base.semesters s where s.id=ss.semester_id) where begin_at is null;
update cfg.edu_schedule_settings ss set end_at=(select s.end_on from base.semesters s where s.id=ss.semester_id) where end_at is null;

alter table edu.clazzes add plan_id bigint;
update edu.clazzes clz set plan_id=(select min(r.plan_id) from edu.clazz_plan_relations r where r.clazz_id=clz.id);
drop table edu.clazz_plan_relations;

alter table app.std_alter_configs rename to std_std_alter_configs;
alter table app.std_std_alter_configs set schema cfg;

alter table edu.extern_grades_courses rename to extern_grades_exempts;
