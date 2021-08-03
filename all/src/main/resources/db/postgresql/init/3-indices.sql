create index idx_t7g19kn25v4jhn6darbfa2stp on base.classrooms_departs (classroom_id);
create index idx_3y1hwcx0xh0apfawnr099698f on base.classrooms_projects (classroom_id);
create index idx_cio9wl9gex7fo85575w4112c4 on base.course_hours (course_id);
create index idx_enrjhpjmn8iterehufbi0m2f6 on base.course_units (setting_id);
create index idx_61og8rbqdd2y28rx2et5fdnxd on base.courses (code);
create index idx_1frqftcod45ghtxsmgci3x40s on base.courses_ability_rates (course_id);
create index idx_stfe4bsgtttmfke9q01mcn86k on base.courses_grading_modes (course_id);
create index idx_a28ih2a6htcdlct7gyr69oulb on base.courses_levels (course_id);
create index idx_nrw3kp2mrqymirhyhhbciyjhe on base.courses_majors (course_id);
create index idx_9p2rpmgl437gq3r5n1cwsjyo9 on base.courses_teachers (course_id);
create index idx_cwi8j5mcog4kvgv6keicpliwn on base.courses_textbooks (course_id);
create index idx_2cpvhk7d040sh5temf5afvht8 on base.courses_xmajors (course_id);
create index idx_rjl6jeb6fw4xp9247gv68vw64 on base.department_categories (parent_id);
create index idx_lloboi12ir2a1q541ntsr7kao on base.departments (parent_id);
create index idx_4tia0kw51tgg3ruidjnm1q02k on base.departments_campuses (department_id);
create index idx_1r68feb85enylfu5hhehp92vj on base.direction_journals (direction_id);
create index idx_7389oy88qypl4v9gudyudoi07 on base.directions (major_id);
create index idx_3d8mbewqjbirn81bfhk47iwf5 on base.major_disciplines (major_id);
create index idx_9tpxhrx7soog576asswjsm796 on base.major_journals (major_id);
create index idx_p5a5db2pkgadejhaaf505vryn on base.projects_calendars (project_id);
create index idx_ars8ki40prfrp0dh90qqcsrxu on base.projects_campuses (project_id);
create index idx_sy5ms6fqa1if8ox26dljlhk0v on base.projects_departments (project_id);
create index idx_4ipswvqq8c1i50hh9cr762l61 on base.projects_levels (project_id);
create index idx_jx3emdr0cnf0yfq5111tyd1c5 on base.projects_properties (project_id);
create index idx_9xea4f7jxyx6oo4ghwyr4ygfp on base.projects_std_labels (project_id);
create index idx_pj8pjvjbvsbx8mbrg5enx6c31 on base.projects_std_types (project_id);
create index idx_a0nnh2q7pmjkwgdafxxn3xyhx on base.school_lengths (major_id);
create index idx_oaj7k1ifx24c5uno3e8xhi4c7 on base.semester_stages (semester_id);
create index idx_o7lu6fw6qehpr050je6d9rsa4 on base.semesters (calendar_id);
create index idx_qv6ypfolegfolbywfx7d0gtlm on base.squads (code);
create index idx_5i9qeuhvlwhad4vd9bqi64gqg on base.student_states (squad_id);
create index idx_cefnmy5upxnnsata9x559sf6a on base.student_states (department_id);
create index idx_lcxttegmywvdx7t18fp94onnp on base.student_states (major_id);
create index idx_qwnmnr5yx5gquk3kon03wmevm on base.student_states (std_id);
create index idx_7fh2oh8g5uif40be11jf9ohfm on base.students (project_id);
create index idx_g4fwvutq09fjdlb4bb0byp7t on base.students (user_id);
create index idx_ov10ar0rv4dr3yywq2uct6kgj on base.students (state_id);
create index idx_6p2ajoj9er8ayk42gcgammih3 on base.students_labels (student_id);
create index idx_swgo4qm8hl9fiixhbkynf4kmp on base.teachers_projects (teacher_id);
create index idx_kpw6oy9d7vq7egphmp4jfnwm2 on base.teaching_groups_members (teaching_group_id);
create index idx_6n5shiehsmeu47y6hblapon9j on code.divisions (parent_id);
create index idx_pkni877r20i1vkg4n2jk2lb31 on code.staff_source_types (parent_id);
create index idx_bqnq6ihum5omb2520bavufwbs on code.staff_types (parent_id);
create index idx_9rggpenqtj9bpoeeiw6anqwu9 on edu.certificate_grades_courses (certificate_grade_id);
create index idx_2nu751wwcosaoh3kd2a36ycdj on edu.clazzes (group_id);
create index idx_9bwygbyci888eug6sjs5ihbqq on edu.clazzes (project_id, semester_id, teach_depart_id);
create index idx_dtwnr7ysxuukruvulscomb6on on edu.clazzes_ability_rates (clazz_id);
create index idx_8w82phrf16wpnbxdoqo3otrtk on edu.clazzes_tags (clazz_id);
create index idx_ljvsyyihis2oe1m3gt3rffg2m on edu.clazzes_teachers (clazz_id);
create index idx_n27g9imaanyl2kggxag56rvhs on edu.course_audit_results (group_result_id);
create index idx_14ecxgagb3kumjkrb1bxv86tw on edu.course_grade_states (clazz_id);
create index idx_4sj9rxcj7k0w4n33ijoi43yfw on edu.course_grades (std_id);
create index idx_slt78xg1vj372c8mh8a9chs4l on edu.course_grades (clazz_id);
create index idx_temwvr6v0fwgm0p56bik0h1wh on edu.course_grades (project_id);
create index idx_cg1338egu7o03diaf8h2ebjvp on edu.course_takers (clazz_id);
create index idx_2tuc5f1o0pkk22qb86p2pawup on edu.exam_activities (semester_id);
create index idx_3c0fuy04m4yleiot9mvjwnud6 on edu.exam_activities (task_id);
create index idx_9eb357ua5ig65i2eufrufd9y on edu.exam_activities (clazz_id, exam_type_id);
create index idx_pjwqn6d874ljreci8v9elrxac on edu.exam_activities_rooms (exam_room_id);
create index idx_qbiu7ds54rmfpxmjwnvwo8dal on edu.exam_activities_rooms (exam_activity_id);
create index idx_k9c9mutaa6jeye5w02bmskklp on edu.exam_grade_states (grade_state_id);
create index idx_otlslgc3imibs7quv0d0dqjb3 on edu.exam_grades (course_grade_id);
create index idx_3fn9e5bana3uc5991o48ldw1x on edu.exam_groups_rooms (exam_group_id);
create index idx_okvnxy33bnpg2tpeexe0h3fvy on edu.exam_room_groups_rooms (exam_room_group_id);
create index idx_1hhyq3u8al0sqnt26y580k722 on edu.exam_takers (clazz_id);
create index idx_l5wab7yp0sb0g5emubh6fp2iy on edu.exam_takers (exam_room_id);
create index idx_rlo27xd1egx2ff1e2311u4wqq on edu.exam_takers (activity_id);
create index idx_i2jdex5p4klvkav3llp032ie3 on edu.exam_tasks (group_id);
create index idx_t7hobv2c1smv3okkui80qnent on edu.exam_tasks (semester_id);
create index idx_imbpgw9lfiugawpojujikpuon on edu.exam_tasks_rooms (exam_task_id);
create index idx_sxe04un5lqoyaj8phyx3yrn5g on edu.exam_turns (group_id);
create index idx_eiv4rp5l2gk4ememhrha2qq5j on edu.execution_course_groups (parent_id);
create index idx_kh1p3dlllkx7ldn2j23x1y2wk on edu.execution_course_groups (plan_id);
create index idx_3ryteeplnbge7ri2uj5j5tsl7 on edu.execution_plan_courses (group_id);
create index idx_qgckajdcovnvbh5emipeorwqi on edu.execution_plans (program_id);
create index idx_3ism2f5vgvkn1vc0jlm1nuygm on edu.extern_grades_courses (extern_grade_id);
create index idx_kn96mnwxflrwspmtwlq970vhr on edu.final_makeup_courses_squads (final_makeup_course_id);
create index idx_h87h8nwy1vu833fi9ddal46nd on edu.final_makeup_takers (makeup_course_id);
create index idx_tmijebsy2v1l6otedsmxu6q4q on edu.final_makeup_takers (std_id);
create index idx_1lirrcdr4lcwbe5iju3f82jsb on edu.ga_grade_states (grade_state_id);
create index idx_2h8ijcy7d6yg7t44byw2rn1i7 on edu.ga_grades (course_grade_id);
create index idx_t24fq5hvjtdmsv2i9ivvk1vbt on edu.grade_rate_items (config_id);
create index idx_9xfmxfqyoevwocv474u0tq2m3 on edu.group_audit_results (plan_result_id);
create index idx_a2dxk9q7074dj9m1085a82me7 on edu.group_audit_results (parent_id);
create index idx_hxngncdxsqws8idke1p2m3ood on edu.invigilation_quota_details (quota_id);
create index idx_t403dhky0rxdpfyt54q7rq0kq on edu.invigilation_quotas_excludes (invigilation_quota_id);
create index idx_2awmtgmlcsgq6pncrafck1dbh on edu.invigilations (exam_room_id);
create index idx_m85fsqiyr7ldosv7uwt9atr7x on edu.lessons (plan_id);
create index idx_inlritlkt48apx8ejj1oa4xsp on edu.lessons_rooms (lesson_id);
create index idx_6dfrp3fwx1fst4j0686ofjsuc on edu.lessons_teachers (lesson_id);
create index idx_8s5d0wiirdi101qp238i6ovru on edu.major_alt_courses (project_id);
create index idx_4hvjp5b9sm4mb3gn32sdn86ch on edu.major_alt_courses_news (major_alternative_course_id);
create index idx_2v1a5cnldp9ct2bepe4510lry on edu.major_alt_courses_olds (major_alternative_course_id);
create index idx_h0ysdj9584dxm8l728cffxrrl on edu.major_course_groups (plan_id);
create index idx_k721uxxb3t08ojvw2gw6s4nt5 on edu.major_course_groups (parent_id);
create index idx_lew5g9v57qxwekv4aherpblii on edu.major_plan_courses (group_id);
create index idx_5kjqjfeqltk9lomhga2doccnh on edu.major_plans (program_id);
create index idx_oihxedgq4vkrng9e7qom6e89f on edu.materials_books (material_id);
create index idx_5c8y9mjfcmi8qfg5ktnfmhq8e on edu.occupancies (room_id);
create index idx_cibqwhtpc8x3gdpclaxfh296j on edu.occupancies (activity_id);
create index idx_ddxl05y02vs65yn5vwqhnn5rk on edu.occupancies (start_on);
create index idx_pqls9iau3y0aiari9j1i8pjbh on edu.process_grade_states_percents (process_grade_state_id);
create index idx_1oeikyxe9m04d1os8e6soi5go on edu.process_grades (std_id);
create index idx_py3s5ykk1fw0gk21lxl5ctl7v on edu.process_grades (clazz_id);
create index idx_qoyl32k2m10m7r8g1if8fwdg5 on edu.process_test_grades (process_grade_id);
create index idx_d69hctw0adiwc375iducsqovm on edu.program_doc_metas (template_id);
create index idx_jfn7je4lfhja46qtvdeka1dut on edu.program_doc_sections (parent_id);
create index idx_lkchplwh47vmakls9riujfgrw on edu.program_doc_sections (doc_id);
create index idx_q0vt9i2ql8fjenu8ldm8fm5wu on edu.program_doc_templates_types (program_doc_template_id);
create index idx_dax3f658vva8uq89kdgg48v4n on edu.restriction_items (restriction_id);
create index idx_c2pllu9g7p0poy59op11nkab5 on edu.restrictions (clazz_id);
create index idx_i8g6bl3jpwldie38iiqgw4kes on edu.restrictions (parent_id);
create index idx_2ofh2k9b4pj51ftjaf0e9oj3s on edu.room_applies_rooms (room_apply_id);
create index idx_oaii80w561p28ji3r86pv8djo on edu.room_applies_times (room_apply_id);
create index idx_epu4ne61rru6f5udqshomhdwx on edu.sessions (clazz_id);
create index idx_8x2cffwmuo8bbpdljfnv5sxna on edu.sessions_rooms (session_id);
create index idx_m8omy8lyt0n0jerbb2fk6he47 on edu.sessions_teachers (session_id);
create index idx_127cshdpus15xol454uaojjfe on edu.share_course_groups (parent_id);
create index idx_dsgghmsmfesuqdn7ct736a6re on edu.share_course_groups (plan_id);
create index idx_4kls4p6klclkgscfvak8pqsdf on edu.share_plan_courses (group_id);
create index idx_mstilk5giawk7vh78kv73x7tr on edu.std_alt_courses (std_id);
create index idx_1swj196xyqjha21237i35824w on edu.std_alt_courses_news (std_alternative_course_id);
create index idx_xtxet4oto3m3lt6fkyq6l9vj on edu.std_alt_courses_olds (std_alternative_course_id);
create index idx_e7b2h1mmriptticlepxklqk0c on edu.std_course_groups (parent_id);
create index idx_fu51ynnn9xdnujcuopnl6nu7k on edu.std_course_groups (plan_id);
create index idx_fyc4q8xtjps60xf5gfovi398s on edu.std_plan_courses (group_id);
create index idx_oi064e8gd1lg81fdcrvgp4p2a on edu.std_semester_gpas (std_gpa_id);
create index idx_k2it4d6hlqi69f22ht73l2ogb on edu.std_year_gpas (std_gpa_id);
create index idx_4kc8i1iwg3dcxgp93s9lcfui9 on edu.subclazzes (clazz_id);
create index idx_mj7o7fx2dl4p7omsdqdln9122 on edu.syllabus_files (syllabus_id);
create index idx_ks5c08r0ewjy6tqj2bdktvcnr on edu.term_campuses (program_id);
create index idx_mejcmpe6sjv8ms3xcx67b8fvd on hr.factor_segments (factor_id);
create index idx_n9haglj7eo6bwl5w0uxkhu7vp on hr.staff_states (staff_id);
create index idx_ha5q227lgqjvx64hds5ftehaw on prac.closure_review_details (review_id);
create index idx_5mw1phdrwafw25td21t17lwje on prac.closure_review_groups_experts (closure_review_group_id);
create index idx_647uatuoilm5dj2g4pmq8xt4v on prac.init_review_details (review_id);
create index idx_ssouu798lwioqaal1qhfjf7ci on prac.init_review_groups_experts (init_review_group_id);
create index idx_qddipto76r59psyuaj555e3tb on prac.innov_projects_instructors (innov_project_id);
create index idx_4ssdre2d81tm5otc6dixkq3lq on prac.level_jounals (project_id);
create index idx_oef7t0mg0u77ybvb22awjxdnw on prac.materials (project_id);
create index idx_1nqrsr3dk6my0v93wa5e2f4cw on prac.members (project_id);
create index idx_fd5q80lq1turqbh8ih82l4usm on prac.stage_types (parent_id);
create index idx_f2v825ixwo1fjsmcve92y6ads on prac.stages (batch_id);
create index idx_okhf1aowv2bfnakj4bilsgvhn on qos.clazz_option_stats (question_stat_id);
create index idx_r0vg6mq0l0isrk8taocrdqqx2 on qos.clazz_question_stats (eval_stat_id);
create index idx_ojrkhn0h6br5f6n6sj1dbpfk6 on qos.clazz_question_type_stats (eval_stat_id);
create index idx_ge1xy7c75yebe89psnbqqnmmf on qos.course_option_stats (question_stat_id);
create index idx_nam6mb615mbrtiesoyruaqxom on qos.course_question_stats (eval_stat_id);
create index idx_ro9jt76eu2mm9o6595iaenuaw on qos.course_question_type_stats (eval_stat_id);
create index idx_oglqawgw140uo18apeo7854pu on qos.depart_option_stats (question_stat_id);
create index idx_obashn99dymi92mf91b4nmvmg on qos.depart_question_stats (eval_stat_id);
create index idx_4mcmjt66f8q52ctg2c4p0as89 on qos.depart_question_type_stats (eval_stat_id);
create index idx_5nnh9us7gnoognmp5q2xg60p4 on qos.evaluation_criteria_items (criteria_id);
create index idx_f5kj6dxaq8tcasfuc6a1r4nv on qos.options (option_group_id);
create index idx_8bhmm2vgbinad1w0aylpxt4n7 on qos.question_results (result_id);
create index idx_1mhhdaxdan0vekgoofvqin7hk on qos.questionnaires_questions (questionnaire_id);
create index idx_by5qirmgdrj5x19jrufrm1ve9 on qos.school_option_stats (question_stat_id);
create index idx_f3igcqaja6fvjkdel9xeqk0ai on qos.school_question_stats (eval_stat_id);
create index idx_pt0fyb4lahxfp9j5or2ecqj0q on qos.school_question_type_stats (eval_stat_id);
create index idx_ojgk4b0hl2ks2hgw2l11ktdr9 on qos.teacher_option_stats (question_stat_id);
create index idx_7lykycmfk7vttwxwqw2hpr565 on qos.teacher_question_stats (eval_stat_id);
create index idx_9d7epkerdh0q4rrvvjrn7epth on qos.teacher_question_type_stats (eval_stat_id);
create index idx_8s9ak6mlyl39u6hp8j4ysbkel on qos.teacher_remessages (text_evaluation_id);
create index idx_gavh43lg9nulgrwrywxh9o76u on qos.teacher_remessages_students (teacher_remessage_id);
create index idx_sure8ht5dxo4cbr29cq0bbq50 on rd.rd_achievement_awards (achievement_id);
create index idx_tf2rabxu2vp1vl422toaq0y0t on rd.rd_achievement_members (achievement_id);
create index idx_2k68gfloim9504k22uiuvfauk on rd.rd_project_members (project_id);
create index idx_9i3d8et45ck4qwhsgrvnvdqrc on rd.rd_projects_leaders (rd_project_id);
create index idx_9loptf43av6ac4srdn8nuq58p on rd.teaching_team_members (team_id);
create index idx_iksg21jj8jtd80126xrj83fp6 on rd.teaching_teams_leaders (teaching_team_id);
create index idx_pynj8wtc3cfhryhr6988ymw1h on rd.textbook_awards (achievement_id);
create index idx_nkkgske0o3mh9cxc2s5hrpbvc on rd.textbook_editors (achievement_id);
create index idx_bill_std on std.bills (std_id);
create index idx_6mgvfxpime2q3p8vuknbtghbo on std.degree_audit_items (result_id);
create index idx_jby4o302r5oxdg1qfapaoxj45 on std.examinees_scores (examinee_id);
create index idx_56xdm5a4qc9hqkj6rtm7ota1l on std.exchange_grades_courses (exchange_grade_id);
create index idx_66bod6jp4lk8p1u4c3cf6pix4 on std.exchange_programs_schools (exchange_program_id);
create index idx_2pucgaon5u33sjehbtj1u9nmj on std.graduate_audit_items (result_id);
create index idx_tu5uubg7w074d5n7aoudd13r on std.social_relations (std_id);
create index idx_4ajhq7ycatuntfts147dl6sx6 on std.std_alteration_items (alteration_id);
create index idx_pv31fi3fkfu1vs1s0forsx0fk on std.transfer_options (scheme_id);
create index idx_i8ly5hbcvk7jigvo8lk7byghv on std.transfer_scopes (scheme_id);
create index idx_jtbvyq3f1ct6o8cavnk1yp514 on std.transfer_scopes_grades (transfer_scope_id);
create index idx_mhtbekv1er9p7w7nu640ye9en on std.transfer_scopes_majors (transfer_scope_id);
