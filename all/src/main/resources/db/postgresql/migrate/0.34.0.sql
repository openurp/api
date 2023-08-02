create schema cfg;

alter table edu.clazzes rename column name to clazz_name;
alter table edu.clazzes rename column locked to capacity_locked;
alter table edu.clazzes rename column reserved to reserved_count;
alter table edu.clazzes rename column actual to std_count;

alter table edu.clazzes add week_hours int4 ;
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
