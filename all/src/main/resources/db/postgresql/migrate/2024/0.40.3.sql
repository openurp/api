insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.40.3',now(),'改进唯一约束命名方式');

alter table code.professional_titles alter grade_id drop not null;
alter table edu.audit_course_results alter remark type varchar(200);
alter table edu.audit_plan_results alter remark type varchar(200);
alter table edu.room_occupy_apps alter activity_url drop not null;
alter table base.projects drop constraint if exists projects_code_key cascade;
alter table base.projects add constraint uk_clujw4wu21d33ssgde022aymk unique (code);
alter table base.schools drop constraint if exists schools_code_key cascade;
alter table base.schools add constraint uk_m5x8j64nhdcprk9ghc6622swx unique (code);
alter table base.user_groups drop constraint if exists idx_group cascade;
alter table base.user_groups add constraint uk_n3w9nvii9wui5ldmpdvtyooe1 unique (school_id,code);
alter table code.academic_levels drop constraint if exists academic_levels_code_key cascade;
alter table code.academic_levels add constraint uk_h35t9eht6ihmhsi6ttx0xu5u0 unique (code);
alter table code.activity_types drop constraint if exists activity_types_code_key cascade;
alter table code.activity_types add constraint uk_40dp7vraisnxtj04mahsegm5f unique (code);
alter table code.admission_types drop constraint if exists admission_types_code_key cascade;
alter table code.admission_types add constraint uk_s7tnd3ykp7egjddmrhm6hgkdv unique (code);
alter table code.blood_types drop constraint if exists blood_types_code_key cascade;
alter table code.blood_types add constraint uk_cw185pu60enlogx4catcnx4x4 unique (code);
alter table code.book_award_types drop constraint if exists book_award_types_code_key cascade;
alter table code.book_award_types add constraint uk_bmuou3h9n3eql52ix5srm5cm0 unique (code);
alter table code.book_categories drop constraint if exists book_categories_code_key cascade;
alter table code.book_categories add constraint uk_kvn03vqif4d5kvwwdm002an9j unique (code);
alter table code.book_types drop constraint if exists book_types_code_key cascade;
alter table code.book_types add constraint uk_bu6h6nn9hx6ns9ujc99of3rmb unique (code);
alter table code.building_types drop constraint if exists building_types_code_key cascade;
alter table code.building_types add constraint uk_68npuqi7xnxjvprta4p402413 unique (code);
alter table code.certificate_categories drop constraint if exists certificate_categories_code_key cascade;
alter table code.certificate_categories add constraint uk_oh5vjbhf5wqjqj56dk0gb4h0p unique (code);
alter table code.certificates drop constraint if exists certificates_code_key cascade;
alter table code.certificates add constraint uk_mkx2966u5d4brh13s2rh5qubj unique (code);
alter table code.classroom_types drop constraint if exists classroom_types_code_key cascade;
alter table code.classroom_types add constraint uk_jnbxj0dxvs5hlql3nfr0x87b3 unique (code);
alter table code.clazz_tags drop constraint if exists clazz_tags_code_key cascade;
alter table code.clazz_tags add constraint uk_9de4r9wq9x1xubugtm1nhicos unique (code);
alter table code.compatriot_types drop constraint if exists compatriot_types_code_key cascade;
alter table code.compatriot_types add constraint uk_cvym46ixfc8otn8b8ijmn1g12 unique (code);
alter table code.countries drop constraint if exists countries_code_key cascade;
alter table code.countries add constraint uk_5dhgnik9p8t72kaktdb8kd8dt unique (code);
alter table code.course_ability_rates drop constraint if exists course_ability_rates_code_key cascade;
alter table code.course_ability_rates add constraint uk_ihcloqdo5h0okf4jchujnfilv unique (code);
alter table code.course_ability_subjects drop constraint if exists course_ability_subjects_code_key cascade;
alter table code.course_ability_subjects add constraint uk_fu4x4wggce13plvehho1gtlpi unique (code);
alter table code.course_award_categories drop constraint if exists course_award_categories_code_key cascade;
alter table code.course_award_categories add constraint uk_2b1aggre0kouucl9x8rc54p57 unique (code);
alter table code.course_award_types drop constraint if exists course_award_types_code_key cascade;
alter table code.course_award_types add constraint uk_brsg073sqnh28tt27cfoke6jy unique (code);
alter table code.course_categories drop constraint if exists course_categories_code_key cascade;
alter table code.course_categories add constraint uk_2f852vsgkix9h35aukbasxq0x unique (code);
alter table code.course_category_dimensions drop constraint if exists course_category_dimensions_code_key cascade;
alter table code.course_category_dimensions add constraint uk_itjwhqnqcserqfrssyuemcj3w unique (code);
alter table code.course_modules drop constraint if exists course_modules_code_key cascade;
alter table code.course_modules add constraint uk_fg6kojr9d970f6o6v5rx9bod unique (code);
alter table code.course_natures drop constraint if exists course_natures_code_key cascade;
alter table code.course_natures add constraint uk_rw1hkv2df9qb30k705iwphq58 unique (code);
alter table code.course_ranks drop constraint if exists course_ranks_code_key cascade;
alter table code.course_ranks add constraint uk_ogoy8l02n66ogmy9avptu6hl8 unique (code);
alter table code.course_tags drop constraint if exists course_tags_code_key cascade;
alter table code.course_tags add constraint uk_kcx6hro48hdaiu9hukowtnkuo unique (code);
alter table code.course_take_types drop constraint if exists course_take_types_code_key cascade;
alter table code.course_take_types add constraint uk_fgwbxogl105wdygubia4x0ak6 unique (code);
alter table code.course_types drop constraint if exists course_types_code_key cascade;
alter table code.course_types add constraint uk_fngi9siu21ndq00kc435oj2go unique (code);
alter table code.day_parts drop constraint if exists day_parts_code_key cascade;
alter table code.day_parts add constraint uk_n758xomdiif4e0txke6wsnh96 unique (code);
alter table code.degree_levels drop constraint if exists degree_levels_code_key cascade;
alter table code.degree_levels add constraint uk_paatawgj9c6hs54qs3gbglvj6 unique (code);
alter table code.degrees drop constraint if exists degrees_code_key cascade;
alter table code.degrees add constraint uk_3wc34egir46fjb9eu1acggkj5 unique (code);
alter table code.department_categories drop constraint if exists department_categories_code_key cascade;
alter table code.department_categories add constraint uk_1267ypqnna5uxvk1p0wl2fuh8 unique (code);
alter table code.device_types drop constraint if exists device_types_code_key cascade;
alter table code.device_types add constraint uk_pp5sfw4fjv6fyc6wglgok63ap unique (code);
alter table code.difficulty_causes drop constraint if exists difficulty_causes_code_key cascade;
alter table code.difficulty_causes add constraint uk_buabmyat6a2sviii2q5liikbl unique (code);
alter table code.difficulty_degrees drop constraint if exists difficulty_degrees_code_key cascade;
alter table code.difficulty_degrees add constraint uk_5fox8obj9j7c0qvwojfdoqqks unique (code);
alter table code.discipline_catalogs drop constraint if exists discipline_catalogs_code_key cascade;
alter table code.discipline_catalogs add constraint uk_1279dmr4pp7umw94eavqlr4il unique (code);
alter table code.discipline_categories drop constraint if exists discipline_categories_code_key cascade;
alter table code.discipline_categories add constraint uk_smxy3kfhfpgwy542xeyt24rym unique (code);
alter table code.disciplines drop constraint if exists disciplines_code_key cascade;
alter table code.disciplines add constraint uk_osah0lag5rsel21i6uu1gqixb unique (code);
alter table code.divisions drop constraint if exists divisions_code_key cascade;
alter table code.divisions add constraint uk_hccml8hpwk506p2l66y8tg1ki unique (code);
alter table code.duty_grades drop constraint if exists duty_grades_code_key cascade;
alter table code.duty_grades add constraint uk_faqn3nsn6cpyhtgwljnq6f4mo unique (code);
alter table code.duty_types drop constraint if exists duty_types_code_key cascade;
alter table code.duty_types add constraint uk_mkju5mr9eblx404xkv3dhockf unique (code);
alter table code.edu_categories drop constraint if exists edu_categories_code_key cascade;
alter table code.edu_categories add constraint uk_39n65oi32jiuycaqsvesys3ek unique (code);
alter table code.education_degrees drop constraint if exists education_degrees_code_key cascade;
alter table code.education_degrees add constraint uk_shx6ottwax2nmo1rw58u78bkl unique (code);
alter table code.education_levels drop constraint if exists education_levels_code_key cascade;
alter table code.education_levels add constraint uk_7g26adhgvuu97atuy1j8x79um unique (code);
alter table code.education_modes drop constraint if exists education_modes_code_key cascade;
alter table code.education_modes add constraint uk_sefk02fm6l1jly10imshq5p9i unique (code);
alter table code.education_results drop constraint if exists education_results_code_key cascade;
alter table code.education_results add constraint uk_42ltpdxra5laem73s06676wol unique (code);
alter table code.education_types drop constraint if exists education_types_code_key cascade;
alter table code.education_types add constraint uk_fyos3e22eyhws7nvx2eq17mgw unique (code);
alter table code.election_modes drop constraint if exists election_modes_code_key cascade;
alter table code.election_modes add constraint uk_e1svykmcg99gw7si27h9xrc7p unique (code);
alter table code.employ_types drop constraint if exists employ_types_code_key cascade;
alter table code.employ_types add constraint uk_q0rd5okmcshdcors7iu8b46tp unique (code);
alter table code.enroll_modes drop constraint if exists enroll_modes_code_key cascade;
alter table code.enroll_modes add constraint uk_j3e78icm5cskq84oufww3hnyk unique (code);
alter table code.exam_defer_reasons drop constraint if exists exam_defer_reasons_code_key cascade;
alter table code.exam_defer_reasons add constraint uk_a163soi3n0flqmw3idgohok2g unique (code);
alter table code.exam_forms drop constraint if exists exam_forms_code_key cascade;
alter table code.exam_forms add constraint uk_q7mk62ugd1gdajtvbxt2py6b0 unique (code);
alter table code.exam_modes drop constraint if exists exam_modes_code_key cascade;
alter table code.exam_modes add constraint uk_r6joowy1dni4gljtoe92v2uhs unique (code);
alter table code.exam_statuses drop constraint if exists exam_statuses_code_key cascade;
alter table code.exam_statuses add constraint uk_jkwl9gx5i72i9xkycc76x68so unique (code);
alter table code.exam_types drop constraint if exists exam_types_code_key cascade;
alter table code.exam_types add constraint uk_b45j7kamdkrhm9qd2ufgbiyfl unique (code);
alter table code.experiment_types drop constraint if exists experiment_types_code_key cascade;
alter table code.experiment_types add constraint uk_ojnotfulo4sssvhe583atg34r unique (code);
alter table code.family_categories drop constraint if exists family_categories_code_key cascade;
alter table code.family_categories add constraint uk_1t23bsp894wnybtqv3mf5he4m unique (code);
alter table code.family_relationships drop constraint if exists family_relationships_code_key cascade;
alter table code.family_relationships add constraint uk_1x471cgit9jgmgjrnno32l3cn unique (code);
alter table code.fee_origins drop constraint if exists fee_origins_code_key cascade;
alter table code.fee_origins add constraint uk_e7o1bghwdfjri8pp4awwosra3 unique (code);
alter table code.fee_types drop constraint if exists fee_types_code_key cascade;
alter table code.fee_types add constraint uk_qwg36jxftoi87236ypjx0jcw8 unique (code);
alter table code.genders drop constraint if exists genders_code_key cascade;
alter table code.genders add constraint uk_6uwkx8yhh4w794lagv7uvnntx unique (code);
alter table code.grade_types drop constraint if exists grade_types_code_key cascade;
alter table code.grade_types add constraint uk_ssv6qvkbkbx6455gsmptkc54e unique (code);
alter table code.grading_modes drop constraint if exists grading_modes_code_key cascade;
alter table code.grading_modes add constraint uk_b97msvfxl2du5qxj3gaav8vkd unique (code);
alter table code.graduate_objectives drop constraint if exists graduate_objectives_code_key cascade;
alter table code.graduate_objectives add constraint uk_ipru1n0o561cuwyc6o0ogd3p6 unique (code);
alter table code.graduate_types drop constraint if exists graduate_types_code_key cascade;
alter table code.graduate_types add constraint uk_9die6co9800of7rw2vh6a63cr unique (code);
alter table code.health_statuses drop constraint if exists health_statuses_code_key cascade;
alter table code.health_statuses add constraint uk_styg2klnvn0k1w4s864ifol19 unique (code);
alter table code.household_types drop constraint if exists household_types_code_key cascade;
alter table code.household_types add constraint uk_cnav0pni6ce5cyyxef7tqtp12 unique (code);
alter table code.hsk_levels drop constraint if exists hsk_levels_code_key cascade;
alter table code.hsk_levels add constraint uk_gtvnlv6mjjmcdwgh17ltrqqlt unique (code);
alter table code.id_types drop constraint if exists id_types_code_key cascade;
alter table code.id_types add constraint uk_beex3aqbbv2nqkicpjadtdvdv unique (code);
alter table code.institution_categories drop constraint if exists institution_categories_code_key cascade;
alter table code.institution_categories add constraint uk_k5ar1dxviaioiewl76ct7jlga unique (code);
alter table code.institutions drop constraint if exists institutions_code_key cascade;
alter table code.institutions add constraint uk_90wcwrx6ap068pspum25xhtyu unique (code);
alter table code.job_statuses drop constraint if exists job_statuses_code_key cascade;
alter table code.job_statuses add constraint uk_k02k1s3sjqc9v9dvqrarjgc1c unique (code);
alter table code.language_abilities drop constraint if exists language_abilities_code_key cascade;
alter table code.language_abilities add constraint uk_6p3nor27f0i5e8ryfh5ppa9un unique (code);
alter table code.languages drop constraint if exists languages_code_key cascade;
alter table code.languages add constraint uk_myc139vxcejowe4q8qm3ca5jn unique (code);
alter table code.marital_statuses drop constraint if exists marital_statuses_code_key cascade;
alter table code.marital_statuses add constraint uk_8p2hd9frpqs2fbulxhe8mtskj unique (code);
alter table code.nations drop constraint if exists nations_code_key cascade;
alter table code.nations add constraint uk_791yx3rdfmeevupxwptmm30o3 unique (code);
alter table code.passport_types drop constraint if exists passport_types_code_key cascade;
alter table code.passport_types add constraint uk_10c0292fifdx77x100dtk2ho2 unique (code);
alter table code.political_statuses drop constraint if exists political_statuses_code_key cascade;
alter table code.political_statuses add constraint uk_2giyhgqrhixando5e6pkr48of unique (code);
alter table code.post_grades drop constraint if exists post_grades_code_key cascade;
alter table code.post_grades add constraint uk_6mcnlu55dfti3itsiig9s36p unique (code);
alter table code.post_types drop constraint if exists post_types_code_key cascade;
alter table code.post_types add constraint uk_5ll10mqocole6bnix353h3w2b unique (code);
alter table code.press_grades drop constraint if exists press_grades_code_key cascade;
alter table code.press_grades add constraint uk_tetfawlge4670eycic2edwetv unique (code);
alter table code.presses drop constraint if exists presses_code_key cascade;
alter table code.presses add constraint uk_8idhuopnq9vabab63pyqfqir6 unique (code);
alter table code.professional_grades drop constraint if exists professional_grades_code_key cascade;
alter table code.professional_grades add constraint uk_j783ewxhftk3q9ixbyavljn5j unique (code);
alter table code.professional_titles drop constraint if exists professional_titles_code_key cascade;
alter table code.professional_titles add constraint uk_bncxdgi5xdsp50jnrwvyii5yg unique (code);
alter table code.program_course_tags drop constraint if exists program_course_tags_code_key cascade;
alter table code.program_course_tags add constraint uk_ieqp9ajuh1804wlmmjhwjentb unique (code);
alter table code.publication_grades drop constraint if exists publication_grades_code_key cascade;
alter table code.publication_grades add constraint uk_5cwt30wjytp7kqqxxg9mgciql unique (code);
alter table code.publications drop constraint if exists publications_code_key cascade;
alter table code.publications add constraint uk_ojr6l8tqoupm6dq3els21esn unique (code);
alter table code.railway_stations drop constraint if exists railway_stations_code_key cascade;
alter table code.railway_stations add constraint uk_liaskyn9dcenlbxc2p3sjlr6h unique (code);
alter table code.rd_award_grades drop constraint if exists rd_award_grades_code_key cascade;
alter table code.rd_award_grades add constraint uk_6i8kdj7snafxjqhed4wm4nwn7 unique (code);
alter table code.rd_levels drop constraint if exists rd_levels_code_key cascade;
alter table code.rd_levels add constraint uk_5bw4yswj1h9pyo86b0wrm1f09 unique (code);
alter table code.religions drop constraint if exists religions_code_key cascade;
alter table code.religions add constraint uk_rmojysk9alhr0ih800fyw2af unique (code);
alter table code.room_types drop constraint if exists room_types_code_key cascade;
alter table code.room_types add constraint uk_pwg4oqr4ylc28g701vfys5hc4 unique (code);
alter table code.social_unit_types drop constraint if exists social_unit_types_code_key cascade;
alter table code.social_unit_types add constraint uk_bc5712vn0ag3o2gbjb8jx7hbu unique (code);
alter table code.staff_source_types drop constraint if exists staff_source_types_code_key cascade;
alter table code.staff_source_types add constraint uk_jy2k3tq0rnovx9qni4hbo3ear unique (code);
alter table code.staff_types drop constraint if exists staff_types_code_key cascade;
alter table code.staff_types add constraint uk_gwihxgl4bspt7wg34tfsln42t unique (code);
alter table code.std_alter_reasons drop constraint if exists std_alter_reasons_code_key cascade;
alter table code.std_alter_reasons add constraint uk_d61fcrrbis8g296gvq0aoswdw unique (code);
alter table code.std_alter_types drop constraint if exists std_alter_types_code_key cascade;
alter table code.std_alter_types add constraint uk_d0hmv5idntvp604d8rv5ffwn1 unique (code);
alter table code.std_doc_types drop constraint if exists std_doc_types_code_key cascade;
alter table code.std_doc_types add constraint uk_88xe3fjb0lxyfmaywg9ov25gk unique (code);
alter table code.std_label_types drop constraint if exists std_label_types_code_key cascade;
alter table code.std_label_types add constraint uk_hryqvm8sj4a4w3fwfsg1tsjl2 unique (code);
alter table code.std_labels drop constraint if exists std_labels_code_key cascade;
alter table code.std_labels add constraint uk_jviknlfon7h8c5sgvtbxwfpxa unique (code);
alter table code.std_punishment_types drop constraint if exists std_punishment_types_code_key cascade;
alter table code.std_punishment_types add constraint uk_mw835oirmjqh1xkdbfkfsrfg1 unique (code);
alter table code.std_types drop constraint if exists std_types_code_key cascade;
alter table code.std_types add constraint uk_jm6qwf4cy0gayqcsrpddyjbey unique (code);
alter table code.student_statuses drop constraint if exists student_statuses_code_key cascade;
alter table code.student_statuses add constraint uk_58ik22jbawb1i8ht83lk11o87 unique (code);
alter table code.study_types drop constraint if exists study_types_code_key cascade;
alter table code.study_types add constraint uk_rp1j8oeth9sda21pk71dqp8m8 unique (code);
alter table code.syllabus_topic_labels drop constraint if exists syllabus_topic_labels_code_key cascade;
alter table code.syllabus_topic_labels add constraint uk_olq7lmxw1if38fl9gsmauhn0a unique (code);
alter table code.teach_lang_types drop constraint if exists teach_lang_types_code_key cascade;
alter table code.teach_lang_types add constraint uk_8whak4jl313vch6unmqvsguuc unique (code);
alter table code.teaching_forms drop constraint if exists teaching_forms_code_key cascade;
alter table code.teaching_forms add constraint uk_e6dw7nu15cnb29jw939qy7o4a unique (code);
alter table code.teaching_methods drop constraint if exists teaching_methods_code_key cascade;
alter table code.teaching_methods add constraint uk_2oid1oma03fwmia8yeepjtaxy unique (code);
alter table code.teaching_natures drop constraint if exists teaching_natures_code_key cascade;
alter table code.teaching_natures add constraint uk_ft2l2vqf6ue19y570m8n2jb1i unique (code);
alter table code.teaching_sections drop constraint if exists teaching_sections_code_key cascade;
alter table code.teaching_sections add constraint uk_pl7lusqa27g9jxm2rlvot4qmm unique (code);
alter table code.thesis_topic_sources drop constraint if exists thesis_topic_sources_code_key cascade;
alter table code.thesis_topic_sources add constraint uk_h0vhsaoskfugync7c9g7l9wsk unique (code);
alter table code.thesis_types drop constraint if exists thesis_types_code_key cascade;
alter table code.thesis_types add constraint uk_rvlh56ev01vw0s1m2727ulta3 unique (code);
alter table code.tutor_types drop constraint if exists tutor_types_code_key cascade;
alter table code.tutor_types add constraint uk_mvnftvqyie0v05l9t7khdnxp2 unique (code);
alter table code.uee_subject_types drop constraint if exists uee_subject_types_code_key cascade;
alter table code.uee_subject_types add constraint uk_9ohkri73dgrruvg1noi5cjqvm unique (code);
alter table code.uncheckin_reasons drop constraint if exists uncheckin_reasons_code_key cascade;
alter table code.uncheckin_reasons add constraint uk_erpbu68elyqcrwgi8w0suoqou unique (code);
alter table code.unregistered_reasons drop constraint if exists unregistered_reasons_code_key cascade;
alter table code.unregistered_reasons add constraint uk_t1sd0t3npnyfes867y9rlipbc unique (code);
alter table code.user_categories drop constraint if exists user_categories_code_key cascade;
alter table code.user_categories add constraint uk_j3ynn4qkfqo4imt05sbforud3 unique (code);
alter table code.visa_types drop constraint if exists visa_types_code_key cascade;
alter table code.visa_types add constraint uk_3yagdiemtpdg1ohnf12cofgfs unique (code);
alter table code.whereto_goes drop constraint if exists whereto_goes_code_key cascade;
alter table code.whereto_goes add constraint uk_kgwluf8uj4fa2bgtefeq1ta0y unique (code);
alter table code.work_statuses drop constraint if exists work_statuses_code_key cascade;
alter table code.work_statuses add constraint uk_8iau4k90qik38mmw3v600qll6 unique (code);
alter table edu.clazz_final_exams drop constraint if exists clazz_final_exams_clazz_id_key cascade;
alter table edu.clazz_final_exams add constraint uk_j4vo8w3lb8v157ssmw709sga8 unique (clazz_id);
alter table edu.exam_tasks drop constraint if exists uk_hblf5miprku52a8qklxm4lvpi cascade;
alter table flow.edu_new_course_categories drop constraint if exists edu_new_course_categories_code_key cascade;
alter table flow.edu_new_course_categories add constraint uk_9uibg29bcb9318kfgrx82ce0l unique (code);
alter table std.orders drop constraint if exists orders_code_key cascade;
alter table std.orders add constraint uk_gt3o4a5bqj59e9y6wakgk926t unique (code);
alter table trd.rd_achievement_types drop constraint if exists rd_achievement_types_code_key cascade;
alter table trd.rd_achievement_types add constraint uk_sg5cv23t8wxl2pdm7hs44oovv unique (code);
alter table trd.rd_project_categories drop constraint if exists rd_project_categories_code_key cascade;
alter table trd.rd_project_categories add constraint uk_lm068o3s4253ai1s8dkm17r77 unique (code);
alter table trd.rd_project_statuses drop constraint if exists rd_project_statuses_code_key cascade;
alter table trd.rd_project_statuses add constraint uk_64ybhe0mvvwxkaw2woo44aycp unique (code);

alter table base.courses alter default_credits set default 0;
alter table base.school_lengths alter maximum set default 0;
alter table base.school_lengths alter minimum set default 0;
alter table base.school_lengths alter normal set default 0;
alter table base.students alter duration set default 0;
alter table cfg.edu_exam_alloc_settings alter min_occupy_ratio set default 0;
alter table cfg.edu_grade_rate_configs alter pass_score set default 0;
alter table cfg.edu_grade_rate_items alter max_score set default 0;
alter table cfg.edu_grade_rate_items alter min_score set default 0;
alter table cfg.std_tuition_configs alter duration set default 0;

alter table edu.audit_group_results alter converted_credits set default 0;
alter table edu.audit_group_results alter owed_credits set default 0;
alter table edu.audit_group_results alter owed_credits2 set default 0;
alter table edu.audit_group_results alter owed_credits3 set default 0;
alter table edu.audit_group_results alter passed_credits set default 0;
alter table edu.audit_group_results alter required_credits set default 0;
alter table edu.audit_plan_results alter owed_credits set default 0;
alter table edu.audit_plan_results alter owed_credits2 set default 0;
alter table edu.audit_plan_results alter owed_credits3 set default 0;
alter table edu.audit_plan_results alter passed_credits set default 0;
alter table edu.audit_plan_results alter remark type varchar(200);
alter table edu.audit_plan_results alter required_credits set default 0;
alter table edu.exam_groups alter max_course_conflict_ratio set default 0;
alter table edu.executive_course_groups alter credits set default 0;
alter table edu.executive_plans alter credits set default 0;
alter table edu.extern_exempt_credits alter exempted set default 0;
alter table edu.extern_exempt_credits alter max_value set default 0;
alter table edu.extern_grades alter credits set default 0;
alter table edu.invigilation_clazz_quotas alter amount set default 0;
alter table edu.invigilation_clazz_quotas alter credit_hours set default 0;
alter table edu.invigilation_clazz_quotas alter ratio set default 0;
alter table edu.invigilation_quota_details alter amount set default 0;
alter table edu.lessons alter learning_hours set default 0;
alter table edu.major_course_groups alter credits set default 0;
alter table edu.major_plans alter credits set default 0;
alter table edu.programs alter credits set default 0;
alter table edu.programs alter duration set default 0;
alter table edu.regular_grade_states alter excellent_rate set default 0;
alter table edu.regular_grade_states alter excellent_rate_limit set default 0;
alter table edu.regular_grades alter score set default 0;
alter table edu.std_course_groups alter credits set default 0;
alter table edu.std_credit_stats alter max_credits set default 0;
alter table edu.std_credit_stats alter total_credits set default 0;
alter table edu.std_gpas alter credits set default 0;
alter table edu.std_gpas alter ga set default 0;
alter table edu.std_gpas alter gpa set default 0;
alter table edu.std_gpas alter total_credits set default 0;
alter table edu.std_plans alter credits set default 0;
alter table edu.std_semester_gpas alter credits set default 0;
alter table edu.std_semester_gpas alter ga set default 0;
alter table edu.std_semester_gpas alter gpa set default 0;
alter table edu.std_semester_gpas alter total_credits set default 0;
alter table edu.std_year_gpas alter credits set default 0;
alter table edu.std_year_gpas alter ga set default 0;
alter table edu.std_year_gpas alter gpa set default 0;
alter table edu.std_year_gpas alter total_credits set default 0;
alter table edu.syllabus_credit_hours alter credit_hours set default 0;
alter table edu.syllabus_exam_hours alter credit_hours set default 0;
alter table edu.syllabus_topic_hours alter credit_hours set default 0;
alter table edu.syllabus_topics alter learning_hours set default 0;
alter table edu.syllabuses alter learning_hours set default 0;
alter table edu.teaching_loads alter factor set default 0;
alter table edu.teaching_loads alter load_hours set default 0;
alter table flow.edu_extern_exempt_applies alter credits set default 0;
alter table flow.edu_extern_exempt_applies alter exemption_credits set default 0;
alter table flow.edu_new_course_applies alter default_credits set default 0;
alter table his.edu_regular_grades alter score set default 0;
alter table qos.assess_grades alter max_score set default 0;
alter table qos.assess_grades alter min_score set default 0;
alter table qos.category_stat_grades alter avg_score set default 0;
alter table qos.category_stat_grades alter max_score set default 0;
alter table qos.category_stat_grades alter min_score set default 0;
alter table qos.category_stat_ranges alter from_score set default 0;
alter table qos.category_stat_ranges alter to_score set default 0;
alter table qos.course_eval_stats alter score set default 0;
alter table qos.course_indicator_stats alter score set default 0;
alter table qos.course_question_stats alter score set default 0;
alter table qos.depart_eval_stats alter avg_score set default 0;
alter table qos.evaluate_results alter score set default 0;
alter table qos.final_teacher_scores alter depart_score set default 0;
alter table qos.final_teacher_scores alter score set default 0;
alter table qos.final_teacher_scores alter std_score set default 0;
alter table qos.final_teacher_scores alter supvi_score set default 0;
alter table qos.options alter proportion set default 0;
alter table qos.question_results alter score set default 0;
alter table qos.questions alter score set default 0;
alter table qos.school_eval_stats alter avg_score set default 0;
alter table std.bachelor2nd_applies alter gpa set default 0;
alter table std.degree_applies alter gpa set default 0;
alter table std.freshmen alter duration set default 0;
alter table std.plan_result_checks alter owed_credits set default 0;
alter table std.plan_result_checks alter owed_credits2 set default 0;
alter table std.plan_result_checks alter owed_credits3 set default 0;
alter table std.plan_result_checks alter passed_credits set default 0;
alter table std.plan_result_checks alter required_credits set default 0;
alter table std.transfer_applies alter gpa set default 0;
alter table std.transfer_applies alter major_gpa set default 0;
alter table std.transfer_applies alter other_gpa set default 0;
alter table std.transfer_applies alter transfer_gpa set default 0;

insert into code.course_modules(id,code,name,begin_on,updated_at) values(1,'1','通识课模块',current_date,now());
insert into code.course_modules(id,code,name,begin_on,updated_at) values(2,'2','学科专业模块',current_date,now());
insert into code.course_modules(id,code,name,begin_on,updated_at) values(3,'3','实践模块',current_date,now());

alter table code.course_modules add column major boolean default false;
alter table code.course_modules add column practical boolean default false;

update code.course_modules set major=true where id=2;
update code.course_modules set practical=true where id=3;

alter table code.course_modules alter major set not null;
alter table code.course_modules alter practical set not null;

alter table code.course_types add column module_id integer;
alter table code.course_types add column rank_id integer;
update code.course_types set module_id=2 where major = true and module_id is null;
update code.course_types set module_id=3 where practical = true and module_id is null;
update code.course_types set module_id=1 where module_id is null;

update code.course_types set rank_id=2 where optional=true and rank_id is null;
update code.course_types set rank_id=3 where optional=true and name like '%限制%';
update code.course_types set rank_id=1 where optional=false and rank_id is null;

alter table edu.audit_group_results add column rank_id integer;
update edu.audit_group_results gr set rank_id=2 where exists(select * from code.course_types ct where ct.id=gr.course_type_id and ct.optional=true);
update edu.audit_group_results gr set rank_id=1
where exists(select * from code.course_types ct where ct.id=gr.course_type_id and ct.optional=false)
      and exists(select * from edu.audit_course_results cr where cr.group_result_id=gr.id);
update edu.audit_group_results gr set rank_id=1 where rank_id is null;

alter table code.course_types add constraint fk_5ufodafafis1r7h88r1mva11l foreign key (rank_id) references code.course_ranks (id);
alter table code.course_types add constraint fk_buyplucphy8v3df4dxorj0lj1 foreign key (module_id) references code.course_modules (id);
alter table edu.audit_group_results add constraint fk_d3cyoyo0nubvnqix4i5b6wetq foreign key (rank_id) references code.course_ranks (id);
comment on column code.course_modules.major is '是否专业课';
comment on column code.course_modules.practical is '是否实践课';
comment on column code.course_types.module_id is '课程模块ID';
comment on column code.course_types.rank_id is '课程属性ID';
comment on column edu.audit_group_results.rank_id is '课程属性ID';
