insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.31.1',now(),'学位答辩结构调整');

--degree
alter table degree.defense_groups add season_id bigint;
alter table degree.defense_groups add published bool default false;
update degree.defense_groups set season_id=20230701;
alter table degree.defense_groups alter column season_id set not null;
alter table degree.defense_groups alter column published set not null;
alter table degree.thesis_reviews add advisor_review_at timestamptz;
alter table degree.thesis_reviews add cross_review_at timestamptz;
alter table degree.thesis_reviews rename column defense_permited to defense_permitted;
alter table degree.defense_infoes add defense_on date;

--base
create table base.courses_prerequisites (course_id bigint not null, prerequisite_id bigint not null);
comment on table base.courses_prerequisites is '先修课程@edu';
comment on column base.courses_prerequisites.course_id is '课程基本信息ID';
comment on column base.courses_prerequisites.prerequisite_id is '课程基本信息ID';
alter table base.courses_prerequisites add constraint pk_oshk6vwdtq9ycqsmj1jh6nxki primary key (course_id,prerequisite_id);
create index idx_5uwkw1b4k9hep46wquup9fhus on base.courses_prerequisites (course_id);
alter table base.courses_prerequisites add constraint fk_7omn0oam1nqls0al47n54pgdp foreign key (course_id) references base.courses (id);
alter table base.courses_prerequisites add constraint fk_irl9cdt3f9i90i9rumci8ncyb foreign key (prerequisite_id) references base.courses (id);

alter table base.course_hours add constraint uk_pr9ombr8rdoadj4w1xbu80gk7 unique (course_id,teaching_nature_id);

alter table base.students add column advisor_id bigint;
alter table base.students add constraint fk_f9btqdhvos27xdp2qk113lovf foreign key (advisor_id) references base.teachers (id);
alter table base.graduates alter season_id type bigint;

alter table base.teaching_offices drop constraint if exists fk_7u1invwk3nkvxts9bfbadd760 cascade;
alter table base.teaching_offices add constraint fk_j6lbhujiysv22jsqq6yyw3tny foreign key (director_id) references base.teachers (id);
drop table base.teaching_offices_members cascade;
alter table base.teachers add column office_id bigint;


alter table base.teachers add column user_id bigint;
update base.teachers t set user_id=(select u.id from base.users u,base.staffs staff where u.code=staff.code and staff.id=t.staff_id);
alter table base.teachers alter user_id set not null;
alter table base.teachers add constraint fk_b8dct7w2j1vl1r2bpstw5isc0 foreign key (user_id) references base.users (id);
alter table base.teachers add constraint fk_jwai2tgrgosk1qdcni108ypjb foreign key (office_id) references base.teaching_offices (id);
alter table base.teachers add constraint uk_cd1k6xwg9jqtiwx9ybnxpmoh9 unique (user_id);

alter table base.extern_students add column exchange boolean;
alter table base.extern_students alter exchange set not null;

alter table base.graduate_seasons alter id type bigint;
--std
alter table std.transfer_applies add column transfer_gpa float4;

--updated_at

alter table base.users alter updated_at type timestamptz;
alter table base.c_book_types alter updated_at type timestamptz;
alter table base.c_education_types alter updated_at type timestamptz;
alter table base.campuses alter updated_at type timestamptz;
alter table base.rooms alter updated_at type timestamptz;
alter table base.c_book_award_types alter updated_at type timestamptz;
alter table base.c_std_types alter updated_at type timestamptz;
alter table base.c_std_labels alter updated_at type timestamptz;
alter table base.staffs alter updated_at type timestamptz;
alter table base.buildings alter updated_at type timestamptz;
alter table base.versions alter updated_at type timestamptz;
alter table base.c_fee_types alter updated_at type timestamptz;
alter table base.calendars alter updated_at type timestamptz;
alter table base.students alter updated_at type timestamptz;
alter table base.people alter updated_at type timestamptz;
alter table base.majors alter updated_at type timestamptz;
alter table base.c_course_types alter updated_at type timestamptz;
alter table base.graduates alter updated_at type timestamptz;
alter table base.c_course_categories alter updated_at type timestamptz;
alter table base.departments alter updated_at type timestamptz;
alter table base.c_std_label_types alter updated_at type timestamptz;
alter table base.c_course_ability_rates alter updated_at type timestamptz;
alter table base.teaching_offices alter updated_at type timestamptz;
alter table edu.std_plans alter updated_at type timestamptz;
alter table edu.room_applies alter apply_at type timestamptz;
alter table edu.c_certificate_subjects alter updated_at type timestamptz;
alter table edu.course_takers alter updated_at type timestamptz;
alter table edu.c_clazz_tags alter updated_at type timestamptz;
alter table edu.clazzes alter updated_at type timestamptz;
alter table edu.std_gpas alter updated_at type timestamptz;
alter table edu.program_docs alter updated_at type timestamptz;
alter table edu.clazz_book_stats alter stat_at type timestamptz;
alter table edu.exam_groups alter updated_at type timestamptz;
alter table edu.cfg_grade_input_switches alter begin_at type timestamptz;
alter table edu.cfg_grade_input_switches alter end_at type timestamptz;
alter table edu.teaching_plans alter audit_at type timestamptz;
alter table edu.teaching_plans alter updated_at type timestamptz;
alter table edu.major_alt_courses alter updated_at type timestamptz;
alter table edu.course_grades alter created_at type timestamptz;
alter table edu.course_grades alter updated_at type timestamptz;
alter table edu.clazz_materials alter updated_at type timestamptz;
alter table edu.occupancies alter updated_at type timestamptz;
alter table edu.exam_room_groups alter updated_at type timestamptz;
alter table edu.exam_grades alter created_at type timestamptz;
alter table edu.exam_grades alter updated_at type timestamptz;
alter table edu. alter updated_at type timestamptz;
alter table edu.cfg_cert_exempt_configs alter begin_at type timestamptz;
alter table edu.cfg_cert_exempt_configs alter end_at type timestamptz;
alter table edu.materials alter submit_at type timestamptz;
alter table edu.c_certificate_categories alter updated_at type timestamptz;
alter table edu.cfg_cert_signup_configs alter begin_at type timestamptz;
alter table edu.cfg_cert_signup_configs alter end_at type timestamptz;
alter table edu.exam_grade_states alter updated_at type timestamptz;
alter table edu.extern_grades add column status integer;
alter table edu.extern_grades alter status set not null;
alter table edu.extern_grades alter updated_at type timestamptz;
alter table edu.ga_grades alter created_at type timestamptz;
alter table edu.ga_grades alter updated_at type timestamptz;
alter table edu.regular_grades alter updated_at type timestamptz;
alter table edu.cert_exempt_applies alter updated_at type timestamptz;
alter table edu.std_course_abilities alter updated_at type timestamptz;
alter table edu.certificate_grades alter updated_at type timestamptz;
alter table edu.programs alter updated_at type timestamptz;
alter table edu.cert_signups alter updated_at type timestamptz;
alter table edu.execution_plans alter updated_at type timestamptz;
alter table edu.ga_grade_states alter updated_at type timestamptz;
alter table edu.program_doc_templates alter updated_at type timestamptz;
alter table edu.major_plans alter updated_at type timestamptz;
alter table edu.regular_grade_states alter updated_at type timestamptz;
alter table edu.course_profiles alter updated_at type timestamptz;
alter table edu.clazz_notices alter updated_at type timestamptz;
alter table edu.clazz_notice_files alter updated_at type timestamptz;
alter table edu.invigilations alter updated_at type timestamptz;
alter table edu.room_apply_final_checks alter checked_at type timestamptz;
alter table edu.syllabuses alter audit_at type timestamptz;
alter table edu.syllabuses alter updated_at type timestamptz;
alter table edu.std_alt_courses alter updated_at type timestamptz;
alter table edu.course_grade_states alter updated_at type timestamptz;
alter table edu.std_book_orders alter withdraw_at type timestamptz;
alter table edu.std_book_orders alter created_at type timestamptz;
alter table edu.plan_audit_results alter updated_at type timestamptz;
alter table edu.cfg_schedule_setting_departs alter begin_at type timestamptz;
alter table edu.cfg_schedule_setting_departs alter end_at type timestamptz;
alter table edu.final_makeup_courses alter input_at type timestamptz;
alter table edu.cfg_transcript_templates alter updated_at type timestamptz;
alter table edu.share_plans alter updated_at type timestamptz;
alter table edu.room_apply_depart_checks alter checked_at type timestamptz;
alter table edu.cfg_schedule_settings alter begin_at type timestamptz;
alter table edu.cfg_schedule_settings alter end_at type timestamptz;
alter table edu.final_makeup_takers alter updated_at type timestamptz;
alter table log.std_transfer_apply_logs alter operate_at type timestamptz;
alter table log.spa_download_logs alter updated_at type timestamptz;
alter table log.spa_print_logs alter updated_at type timestamptz;
alter table prac.inv_stages alter begin_at type timestamptz;
alter table prac.inv_stages alter end_at type timestamptz;
alter table prac.inv_level_jounals alter updated_at type timestamptz;
alter table prac.inv_closures alter updated_at type timestamptz;
alter table prac.inv_materials alter updated_at type timestamptz;
alter table prac.inv_init_review_details alter updated_at type timestamptz;
alter table prac.inv_closure_review_details alter updated_at type timestamptz;
alter table std.homes alter updated_at type timestamptz;
alter table std.std_alterations alter updated_at type timestamptz;
alter table std.bills alter pay_at type timestamptz;
alter table std.bills alter created_at type timestamptz;
alter table std.bills alter updated_at type timestamptz;
alter table std.transfer_applies alter updated_at type timestamptz;
alter table std.degree_results alter updated_at type timestamptz;
alter table std.cfg_transfer_schemes alter apply_end_at type timestamptz;
alter table std.cfg_transfer_schemes alter apply_begin_at type timestamptz;
alter table std.cfg_transfer_schemes alter edit_begin_at type timestamptz;
alter table std.cfg_transfer_schemes alter audit_end_at type timestamptz;
alter table std.cfg_transfer_schemes alter edit_end_at type timestamptz;
alter table std.cfg_transfer_schemes alter audit_begin_at type timestamptz;
alter table std.cfg_transfer_schemes alter updated_at type timestamptz;
alter table std.graduate_batches alter updated_at type timestamptz;
alter table std.graduate_batches alter season_id type bigint;
alter table std.registers alter register_at type timestamptz;
alter table std.orders alter pay_at type timestamptz;
alter table std.orders alter expired_at type timestamptz;
alter table std.orders alter created_at type timestamptz;
alter table std.graduate_results alter updated_at type timestamptz;
alter table std.examinees alter updated_at type timestamptz;
alter table std.contacts alter updated_at type timestamptz;
alter table std.major_students alter updated_at type timestamptz;
alter table std.degree_applies alter updated_at type timestamptz;
alter table base.extern_students alter updated_at type timestamptz;
alter table base.squads alter updated_at type timestamptz;
alter table base.courses alter updated_at type timestamptz;
alter table base.extern_schools alter updated_at type timestamptz;
alter table base.directions alter updated_at type timestamptz;
alter table base.classrooms alter updated_at type timestamptz;
alter table base.projects alter updated_at type timestamptz;
alter table code.degrees alter updated_at type timestamptz;
alter table code.household_types alter updated_at type timestamptz;
alter table code.languages alter updated_at type timestamptz;
alter table code.activity_types alter updated_at type timestamptz;
alter table code.student_statuses alter updated_at type timestamptz;
alter table code.political_statuses alter updated_at type timestamptz;
alter table code.marital_statuses alter updated_at type timestamptz;
alter table code.edu_categories alter updated_at type timestamptz;
alter table code.passport_types alter updated_at type timestamptz;
alter table code.fee_origins alter updated_at type timestamptz;
alter table code.grade_types alter updated_at type timestamptz;
alter table code.professional_grades alter updated_at type timestamptz;
alter table code.discipline_catalogs alter updated_at type timestamptz;
alter table code.health_statuses alter updated_at type timestamptz;
alter table code.language_abilities alter updated_at type timestamptz;
alter table code.compatriot_types alter updated_at type timestamptz;
alter table code.duty_types alter updated_at type timestamptz;
alter table code.education_modes alter updated_at type timestamptz;
alter table code.staff_types alter updated_at type timestamptz;
alter table code.education_levels alter updated_at type timestamptz;
alter table code.teaching_natures alter updated_at type timestamptz;
alter table code.teach_lang_types alter updated_at type timestamptz;
alter table code.professional_titles alter updated_at type timestamptz;
alter table code.whereto_goes alter updated_at type timestamptz;
alter table code.grading_modes alter updated_at type timestamptz;
alter table code.press_grades alter updated_at type timestamptz;
alter table code.family_categories alter updated_at type timestamptz;
alter table code.academic_levels alter updated_at type timestamptz;
alter table code.user_categories alter updated_at type timestamptz;
alter table code.institutions alter updated_at type timestamptz;
alter table code.study_types alter updated_at type timestamptz;
alter table code.railway_stations alter updated_at type timestamptz;
alter table code.post_grades alter updated_at type timestamptz;
alter table code.room_types alter updated_at type timestamptz;
alter table code.difficulty_causes alter updated_at type timestamptz;
alter table code.exam_modes alter updated_at type timestamptz;
alter table code.difficulty_degrees alter updated_at type timestamptz;
alter table code.building_types alter updated_at type timestamptz;
alter table code.blood_types alter updated_at type timestamptz;
alter table code.employ_types alter updated_at type timestamptz;
alter table code.duty_grades alter updated_at type timestamptz;
alter table code.id_types alter updated_at type timestamptz;
alter table code.tutor_types alter updated_at type timestamptz;
alter table code.education_degrees alter updated_at type timestamptz;
alter table code.degree_levels alter updated_at type timestamptz;
alter table code.admission_types alter updated_at type timestamptz;
alter table code.uee_subject_types alter updated_at type timestamptz;
alter table code.course_take_types alter updated_at type timestamptz;
alter table code.publications alter updated_at type timestamptz;
alter table code.countries alter updated_at type timestamptz;
alter table code.exam_statuses alter updated_at type timestamptz;
alter table code.visa_types alter updated_at type timestamptz;
alter table code.exam_forms alter updated_at type timestamptz;
alter table code.std_punishment_types alter updated_at type timestamptz;
alter table code.social_unit_types alter updated_at type timestamptz;
alter table code.std_alter_reasons alter updated_at type timestamptz;
alter table code.exam_types alter updated_at type timestamptz;
alter table code.post_types alter updated_at type timestamptz;
alter table code.classroom_types alter updated_at type timestamptz;
alter table code.job_statuses alter updated_at type timestamptz;
alter table code.family_relationships alter updated_at type timestamptz;
alter table code.unregistered_reasons alter updated_at type timestamptz;
alter table code.day_parts alter updated_at type timestamptz;
alter table code.divisions alter updated_at type timestamptz;
alter table code.discipline_categories alter updated_at type timestamptz;
alter table code.uncheckin_reasons alter updated_at type timestamptz;
alter table code.staff_source_types alter updated_at type timestamptz;
alter table code.nations alter updated_at type timestamptz;
alter table code.genders alter updated_at type timestamptz;
alter table code.publication_grades alter updated_at type timestamptz;
alter table code.teaching_methods alter updated_at type timestamptz;
alter table code.election_modes alter updated_at type timestamptz;
alter table code.std_alter_types alter updated_at type timestamptz;
alter table code.presses alter updated_at type timestamptz;
alter table code.course_natures alter updated_at type timestamptz;
alter table code.book_categories alter updated_at type timestamptz;
alter table code.religions alter updated_at type timestamptz;
alter table code.education_results alter updated_at type timestamptz;
alter table code.enroll_modes alter updated_at type timestamptz;
alter table code.hsk_levels alter updated_at type timestamptz;
alter table code.department_categories alter updated_at type timestamptz;
alter table code.disciplines alter updated_at type timestamptz;
alter table code.work_statuses alter updated_at type timestamptz;
alter table spa.cfg_print_configs alter updated_at type timestamptz;
alter table spa.cfg_coupons alter updated_at type timestamptz;
alter table spa.print_quotas alter last_print_at type timestamptz;
alter table qos.depart_eval_stats alter updated_at type timestamptz;
alter table qos.cfg_questions alter updated_at type timestamptz;
alter table qos.school_eval_stats alter updated_at type timestamptz;
alter table qos.final_comments alter updated_at type timestamptz;
alter table qos.feedbacks alter updated_at type timestamptz;
alter table qos.course_eval_stats alter updated_at type timestamptz;
alter table qos.cfg_questionnaires alter updated_at type timestamptz;
alter table qos.category_eval_stats alter updated_at type timestamptz;
alter table qos.evaluate_results alter updated_at type timestamptz;
alter table qos.cfg_indicators alter updated_at type timestamptz;
alter table trd.c_rd_award_grades alter updated_at type timestamptz;
alter table trd.teaching_teams alter updated_at type timestamptz;
alter table trd.rd_achievements alter updated_at type timestamptz;
alter table trd.c_rd_achievement_types alter updated_at type timestamptz;
alter table trd.c_rd_levels alter updated_at type timestamptz;
alter table trd.textbook_achievements alter updated_at type timestamptz;
alter table trd.c_rd_project_statuses alter updated_at type timestamptz;
alter table trd.c_rd_project_categories alter updated_at type timestamptz;
alter table trd.rd_projects alter updated_at type timestamptz;
alter table hr.teacher_profiles alter updated_at type timestamptz;
