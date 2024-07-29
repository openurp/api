alter table edu.user_apps rename to room_occupy_apps;
alter table edu.clazzes alter exam_form_id drop not null;
alter table edu.exam_activities alter exam_on drop not null;
alter table edu.programs alter offset_type_id drop not null;

drop schema code_hb;
drop schema code_gb;
drop schema edu_base;
drop schema edu_extern;
drop schema edu_grade;
drop schema edu_workload;
drop schema edu_evaluation;
drop schema edu_exam;
drop schema edu_graduation;
drop schema edu_room;

drop schema hr_base;
drop schema hr_contact;
drop schema hr_profile;

drop schema sin_harvest;
drop schema std_fee;
drop schema std_info;
drop schema std_register;
drop schema std_transfer;
drop schema std_alter;

drop schema stu_job;
drop schema stu_award;

drop schema prac_innovation;

alter schema app_clazz rename to app_edu;
alter table app_grade.audit_settings set schema app_edu;
alter table app_grade.audit_settings_convertables set schema app_edu;
alter table app_grade.audit_settings_departments set schema app_edu;
alter table app_grade.audit_settings_directions set schema app_edu;
alter table app_grade.audit_settings_levels set schema app_edu;
alter table app_grade.audit_settings_majors set schema app_edu;
alter table app_grade.audit_settings_excludes set schema app_edu;
alter table app_grade.audit_settings_std_types set schema app_edu;

alter table app_grade.grade_input_switches set schema app_edu;
alter table app_grade.grade_input_switches_types set schema app_edu;
alter table app_grade.grade_modify_applies set schema app_edu;
alter table app_grade.report_templates set schema app_edu;

drop schema app_grade;


alter table app_graduation.ga_standards_degree_rules set schema app_edu;
alter table app_graduation.ga_standards_departments set schema app_edu;
alter table app_graduation.ga_standards_directions set schema app_edu;
alter table app_graduation.ga_standards_educations set schema app_edu;
alter table app_graduation.ga_standards_majors set schema app_edu;
alter table app_graduation.ga_standards_rules set schema app_edu;
alter table app_graduation.ga_standards_std_types set schema app_edu;
alter table app_graduation.graduate_audit_logs set schema app_edu;
alter table app_graduation.graduate_audit_standards set schema app_edu;

drop schema app_graduation;

alter schema app_edu rename to app;
alter table eams.sys_business_log_details set schema app;
alter table eams.sys_business_logs set schema app;
alter table eams.sys_code_metas set schema app;
alter table eams.sys_code_categories set schema app;
alter table eams.sys_code_scripts set schema app;
alter table eams.sys_custom_queries set schema app;
alter table eams.sys_entity_metas set schema app;
alter table eams.sys_property_config_items set schema app;
alter table eams.sys_property_metas set schema app;
alter table eams.sys_rule_config_params set schema app;
alter table eams.sys_rule_configs set schema app;
alter table eams.sys_rule_parameters set schema app;
alter table eams.sys_rules set schema app;
drop schema eams;

alter table app_std.ga_standards_degree_rules set schema app;
alter table app_std.ga_standards_departments set schema app;
alter table app_std.ga_standards_directions set schema app;
alter table app_std.ga_standards_educations set schema app;
alter table app_std.ga_standards_majors set schema app;
alter table app_std.ga_standards_rules set schema app;
alter table app_std.ga_standards_std_types set schema app;
alter table app_std.graduate_audit_logs set schema app;
alter table app_std.graduate_audit_standards set schema app;

drop schema app_std;

alter table app_student.register_user_groups set schema app;
alter table app_student.register_user_groups_users set schema app;
alter table app_student.report_login_switches set schema app;
alter table app_student.std_alter_configs set schema app;
alter table app_student.std_edit_restrictions set schema app;
alter table app_student.std_edit_restrictions set schema app;
alter table app_student.std_apply_edit_noteses set schema app;
alter table app_student.std_edit_restrictions_metas set schema app;
alter table app_student.std_property_editors set schema app;
alter table app_student.student_apply_edits set schema app;
alter table app_student.student_logs set schema app;
alter table app_student.std_edit_restrictions_users  set schema app;
drop schema app_student;

alter table edu_clazz.restriction_metas set schema edu;
alter table edu_clazz.clazz_plan_relations set schema edu;
alter table edu_clazz.course_arrange_switches set schema edu;
alter table edu_clazz.arrange_suggests set schema edu;
alter table edu_clazz.arrange_suggests_rooms set schema edu;
alter table edu_clazz.lessons set schema edu;
alter table edu_clazz.lessons_rooms set schema edu;
alter table edu_clazz.lessons_teachers set schema edu;
alter table edu_clazz.materials set schema edu;
alter table edu_clazz.materials_books set schema edu;

drop schema edu_clazz;
drop schema edu_lesson;

alter table edu_textbook.clazz_book_stats set schema edu;
alter table edu_textbook.std_book_orders set schema edu;

drop schema edu_textbook;

alter table edu_program.execution_plan_comments set schema edu;
drop schema edu_program;

alter table edu_course.course_blogs set schema edu;
alter table edu_course.course_blogs_teachers set schema edu;
alter table edu_course.lecture_plans set schema edu;
drop table edu_course.syllabus_sections;
drop table edu_course.syllabus_section_titles;
drop schema edu_course;

alter table edu.materials rename column reference_books  to materials;
