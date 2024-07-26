create index idx_t7g19kn25v4jhn6darbfa2stp on base.classrooms_departs (classroom_id);
create index idx_3y1hwcx0xh0apfawnr099698f on base.classrooms_projects (classroom_id);
create index idx_cio9wl9gex7fo85575w4112c4 on base.course_hours (course_id);
create index idx_lm5drr3y1vinuxh8pqi0up3pw on base.course_journal_hours (journal_id);
create index idx_63qf0hjsyy394vrfl9ui51oe4 on base.course_journals (course_id);
create index idx_fa43felt9lxq3wse8lp20bdgg on base.course_journals_tags (course_journal_id);
create index idx_w33dd8gtyilc14ptwiwvx0w5 on base.course_levels (course_id);
create index idx_tjt1ybpj33nkgqek0j2fylp7g on base.course_profiles (course_id);
create index idx_enrjhpjmn8iterehufbi0m2f6 on base.course_units (setting_id);
create index idx_61og8rbqdd2y28rx2et5fdnxd on base.courses (code);
create index idx_gsyp40xj7elqlco0688yda587 on base.courses (cluster_id);
create index idx_fx0k2hychuov557bedalysbex on base.courses_categories (course_id);
create index idx_b33k2icegejva3ino103aa5eb on base.courses_tags (course_id);
create index idx_lloboi12ir2a1q541ntsr7kao on base.departments (parent_id);
create index idx_4tia0kw51tgg3ruidjnm1q02k on base.departments_campuses (department_id);
create index idx_ovshoc5u1nrmelxy454pix0gm on base.devices (room_id);
create index idx_1r68feb85enylfu5hhehp92vj on base.direction_journals (direction_id);
create index idx_7389oy88qypl4v9gudyudoi07 on base.directions (major_id);
create index idx_3d8mbewqjbirn81bfhk47iwf5 on base.major_disciplines (major_id);
create index idx_9tpxhrx7soog576asswjsm796 on base.major_journals (major_id);
create index idx_ppbhk85s88rolvwv4tm8d2kmv on base.mentors_projects (mentor_id);
create index idx_ajflqx9dbuh26q7nfapkr0rkh on base.people (code);
create index idx_ars8ki40prfrp0dh90qqcsrxu on base.projects_campuses (project_id);
create index idx_sy5ms6fqa1if8ox26dljlhk0v on base.projects_departments (project_id);
create index idx_59q82b4p5aojkd254d4wy0u4f on base.projects_edu_types (project_id);
create index idx_4ipswvqq8c1i50hh9cr762l61 on base.projects_levels (project_id);
create index idx_9xea4f7jxyx6oo4ghwyr4ygfp on base.projects_std_labels (project_id);
create index idx_pj8pjvjbvsbx8mbrg5enx6c31 on base.projects_std_types (project_id);
create index idx_a0nnh2q7pmjkwgdafxxn3xyhx on base.school_lengths (major_id);
create index idx_4751fgfwuvlgoag1als5q4hm2 on base.secretaries_projects (secretary_id);
create index idx_oaj7k1ifx24c5uno3e8xhi4c7 on base.semester_stages (semester_id);
create index idx_o7lu6fw6qehpr050je6d9rsa4 on base.semesters (calendar_id);
create index idx_qv6ypfolegfolbywfx7d0gtlm on base.squads (code);
create index idx_5i9qeuhvlwhad4vd9bqi64gqg on base.student_states (squad_id);
create index idx_cefnmy5upxnnsata9x559sf6a on base.student_states (department_id);
create index idx_lcxttegmywvdx7t18fp94onnp on base.student_states (major_id);
create index idx_qwnmnr5yx5gquk3kon03wmevm on base.student_states (std_id);
create index idx_7fh2oh8g5uif40be11jf9ohfm on base.students (project_id);
create index idx_eqa1d4jiyg5m5rnuja7ifgw73 on base.students (code);
create index idx_ov10ar0rv4dr3yywq2uct6kgj on base.students (state_id);
create index idx_6p2ajoj9er8ayk42gcgammih3 on base.students_labels (student_id);
create index idx_dryob4n9h2g16emfu7mhc2b7w on base.teachers_campuses (teacher_id);
create index idx_swgo4qm8hl9fiixhbkynf4kmp on base.teachers_projects (teacher_id);
create index idx_xwp64q9jqda0yiypgwq9mw5n on base.tutor_majors_directions (tutor_major_id);
create index idx_o068gea4qj0vju9tm7h09mqbv on base.user_group_members (user_id);
create index idx_psg4y38f0pvgmc8v0axsp9ac0 on base.user_groups (parent_id);
create index idx_hcbg5wtcik11yo0iqe4do7w5u on cfg.edu_cert_exempt_configs_levels (cert_exempt_config_id);
create index idx_8trplfnh70abytghtf6a1nuk2 on cfg.edu_cert_exempt_courses (cert_exempt_setting_id);
create index idx_4w8nuo8e80kd8hdgticua1pik on cfg.edu_cert_exempt_settings (config_id);
create index idx_2umtts876een1sjj83imo3yrs on cfg.edu_cert_signup_exclusives (cert_signup_setting_id);
create index idx_wushq0sduboxx4itrnvjbnvh on cfg.edu_cert_signup_scopes (setting_id);
create index idx_rkfwkuxhdb0ex6yxqy8784q9k on cfg.edu_cert_signup_settings (config_id);
create index idx_5kyvw5or7adkyrs9x80q5pijc on cfg.edu_factor_segments (factor_id);
create index idx_53afh6m93slftsy0gkjfg6acr on cfg.edu_grade_input_switches_types (grade_input_switch_id);
create index idx_77lbljk2g828tygwxkxed8bkb on cfg.edu_grade_rate_items (config_id);
create index idx_s8lp615fkmfph46u6cdovk0p4 on cfg.edu_room_apply_depart_rooms (room_apply_depart_scope_id);
create index idx_khgja88dtey7iiamxbn00qcwq on cfg.edu_schedule_setting_departs (setting_id);
create index idx_35f6ka7arp3j75onw142dn7cr on cfg.std_transfer_options (scheme_id);
create index idx_84i8icrfu33dut8an8tn9qrgu on cfg.std_transfer_scopes (scheme_id);
create index idx_eijut5ihl52n15i4j2akr6cfe on cfg.std_transfer_scopes_grades (transfer_scope_id);
create index idx_fy1xn4m2wnf6qb26am53aoa3n on cfg.std_transfer_scopes_majors (transfer_scope_id);
create index idx_o0yv8bij0wlo1dbukqmgjfd6p on code.course_categories (parent_id);
create index idx_rjl6jeb6fw4xp9247gv68vw64 on code.department_categories (parent_id);
create index idx_6n5shiehsmeu47y6hblapon9j on code.divisions (parent_id);
create index idx_pkni877r20i1vkg4n2jk2lb31 on code.staff_source_types (parent_id);
create index idx_bqnq6ihum5omb2520bavufwbs on code.staff_types (parent_id);
create index idx_9k59y288i18bb8p47o66u1s0d on degree.advisors_departs (advisor_id);
create index idx_cjkawkssayfjof7fys1g3ygmy on degree.deadlines (writer_id);
create index idx_92mpovwqhxbb96nvwtrkgpug3 on degree.defense_members (group_id);
create index idx_ne698xa5anj44drr9vbx8o26d on degree.defense_notices (group_id);
create index idx_j7e8nufnixcsdgy5qippugbs5 on degree.defense_writers (group_id);
create index idx_hhd23uu4g31k3qwk0dkqi508f on degree.depart_plans (thesis_plan_id);
create index idx_aljt9k57y1h5hjipycrjvxvdd on degree.depart_plans_times (depart_plan_id);
create index idx_o1tr5322a778ojj610im4awwf on degree.guidances (writer_id);
create index idx_367fgdh8tq59609e5635nl8t1 on degree.midterm_check_details (check_id);
create index idx_kuygvcpnq78g1uha15vxgmk47 on degree.paper_submissions (writer_id);
create index idx_9pvh5lmgs2d11b304e8jmqt8q on degree.subject_applies (last_id);
create index idx_6ybpxgvqceyalb2dv0r8khryx on degree.subjects_majors (subject_id);
create index idx_5ed3idyn3wloeml8w2hipr22i on degree.thesis_plans_times (thesis_plan_id);
create index idx_1msow45s5r22cpswq4uby1gyo on degree.writers (season_id);
create index idx_8re320acxkwwypf6jedlbkkm6 on degree.writers (std_id);
create index idx_7f4fl8ra3f9rfwu0dn89o4mcu on edu.audit_course_results (group_result_id);
create index idx_1fjw4dws9p4gekfgb0n7864wq on edu.audit_group_results (plan_result_id);
create index idx_acjvk6wb7g4n0uokihrrqn060 on edu.audit_group_results (parent_id);
create index idx_5miknelheaykqp8o0l44o9em2 on edu.certificate_grades (semester_id);
create index idx_7arggk33h5fogvdhxs390tc4c on edu.certificate_grades_exempts (certificate_grade_id);
create index idx_sjlgr9e0wh41aqb4wbj7tyqaj on edu.clazz_activities (clazz_id);
create index idx_lp1ho1db4ui0l4lffulgfn0yk on edu.clazz_activities_rooms (activity_id);
create index idx_15kq4qjipsyjesv30panl7d28 on edu.clazz_activities_teachers (activity_id);
create index idx_bte7jeq8ko72gnghvj3wxrln1 on edu.clazz_docs (clazz_id);
create index idx_ytymsw5lymtmg2d521wkl05d on edu.clazz_materials_books (clazz_material_id);
create index idx_ifw02k8m49nnnsixxeic5irmd on edu.clazz_notice_files (notice_id);
create index idx_etjnc5p0edykl4yjenl8f5aqo on edu.clazz_notices (clazz_id);
create index idx_tarm0ae4yoy7g95d7dlndehcj on edu.clazz_restriction_items (restriction_id);
create index idx_eu8m90l4434qlu64236gaxwd4 on edu.clazz_restrictions (parent_id);
create index idx_qgp3bg29kpkhk1sclrxxklhos on edu.clazz_restrictions (clazz_id);
create index idx_2nu751wwcosaoh3kd2a36ycdj on edu.clazzes (group_id);
create index idx_9bwygbyci888eug6sjs5ihbqq on edu.clazzes (project_id, semester_id, teach_depart_id);
create index idx_dtwnr7ysxuukruvulscomb6on on edu.clazzes_ability_rates (clazz_id);
create index idx_8w82phrf16wpnbxdoqo3otrtk on edu.clazzes_tags (clazz_id);
create index idx_ljvsyyihis2oe1m3gt3rffg2m on edu.clazzes_teachers (clazz_id);
create index idx_14ecxgagb3kumjkrb1bxv86tw on edu.course_grade_states (clazz_id);
create index idx_4sj9rxcj7k0w4n33ijoi43yfw on edu.course_grades (std_id);
create index idx_slt78xg1vj372c8mh8a9chs4l on edu.course_grades (clazz_id);
create index idx_temwvr6v0fwgm0p56bik0h1wh on edu.course_grades (project_id);
create index idx_9l1h3gh5tajelrpwlja4rvoeu on edu.course_takers (semester_id);
create index idx_cg1338egu7o03diaf8h2ebjvp on edu.course_takers (clazz_id);
create index idx_m6bxmljp0ixb3vabhn06n3gqh on edu.course_tasks_teachers (course_task_id);
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
create index idx_ia1rnujj64tq7s6bw31qxvvbs on edu.executive_course_groups (parent_id);
create index idx_ktdcfjdpse8b04tpfwisrj4w6 on edu.executive_course_groups (plan_id);
create index idx_maqy74ejiwi3u11ldf8hn8ji5 on edu.executive_plan_courses (group_id);
create index idx_2p1f05r4qitxkli21t8ie7v3i on edu.executive_plans (program_id);
create index idx_3l1lv5lhap575efwfuyfklwa3 on edu.exempt_courses_std_types (exempt_course_id);
create index idx_d3bxyon5uawiu9el72altkowk on edu.extern_grades_exempts (extern_grade_id);
create index idx_kn96mnwxflrwspmtwlq970vhr on edu.final_makeup_courses_squads (final_makeup_course_id);
create index idx_h87h8nwy1vu833fi9ddal46nd on edu.final_makeup_takers (makeup_course_id);
create index idx_tmijebsy2v1l6otedsmxu6q4q on edu.final_makeup_takers (std_id);
create index idx_1lirrcdr4lcwbe5iju3f82jsb on edu.ga_grade_states (grade_state_id);
create index idx_2h8ijcy7d6yg7t44byw2rn1i7 on edu.ga_grades (course_grade_id);
create index idx_hxngncdxsqws8idke1p2m3ood on edu.invigilation_quota_details (quota_id);
create index idx_t403dhky0rxdpfyt54q7rq0kq on edu.invigilation_quotas_excludes (invigilation_quota_id);
create index idx_2awmtgmlcsgq6pncrafck1dbh on edu.invigilations (exam_room_id);
create index idx_m85fsqiyr7ldosv7uwt9atr7x on edu.lessons (plan_id);
create index idx_hnm0j0sfpkc5gqn2s07ln80th on edu.major_alt_courses (project_id);
create index idx_afx0rbkspks71gaexr83lu1oo on edu.major_alt_courses_news (major_alt_course_id);
create index idx_l2bh1c8bo4hsur07k9yo62lu8 on edu.major_alt_courses_olds (major_alt_course_id);
create index idx_h0ysdj9584dxm8l728cffxrrl on edu.major_course_groups (plan_id);
create index idx_k721uxxb3t08ojvw2gw6s4nt5 on edu.major_course_groups (parent_id);
create index idx_lew5g9v57qxwekv4aherpblii on edu.major_plan_courses (group_id);
create index idx_5kjqjfeqltk9lomhga2doccnh on edu.major_plans (program_id);
create index idx_5c8y9mjfcmi8qfg5ktnfmhq8e on edu.occupancies (room_id);
create index idx_cibqwhtpc8x3gdpclaxfh296j on edu.occupancies (activity_id);
create index idx_ddxl05y02vs65yn5vwqhnn5rk on edu.occupancies (start_on);
create index idx_gfyumr969ca1ykkkgat7biy2t on edu.program_course_labels (program_id);
create index idx_tkx5sw4r528dewo84cr4kl2w2 on edu.program_course_outcomes (doc_id);
create index idx_d69hctw0adiwc375iducsqovm on edu.program_doc_metas (template_id);
create index idx_jfn7je4lfhja46qtvdeka1dut on edu.program_doc_sections (parent_id);
create index idx_lkchplwh47vmakls9riujfgrw on edu.program_doc_sections (doc_id);
create index idx_q0vt9i2ql8fjenu8ldm8fm5wu on edu.program_doc_templates_types (program_doc_template_id);
create index idx_2nskwcp75nhywhfvwra2o5tvr on edu.program_objectives (doc_id);
create index idx_e735gkuaellofhjbxsxixwlj7 on edu.program_outcomes (doc_id);
create index idx_i4rlfwsjdqfqvi0cep3ke92nj on edu.program_prerequisites (program_id);
create index idx_6d6uejbklk3j5me187qk8nmc3 on edu.program_tables (doc_id);
create index idx_sjusvc5k3oltaoggtr9xb02jo on edu.program_texts (doc_id);
create index idx_7w0yqeo8hxaggvl60x9vedi3d on edu.programs_degree_certificates (program_id);
create index idx_g6aj9g4hfn2pbwy6l2pkyh2mh on edu.programs_degree_courses (program_id);
create index idx_5u4gknostdmf16gyfurc2u6eq on edu.programs_std_types (program_id);
create index idx_qcchbnp54bci79i8mdudt2y63 on edu.regular_grade_states_percents (regular_grade_state_id);
create index idx_79jpq20ahqs7fp9yhfb1k0eac on edu.regular_grades (clazz_id);
create index idx_fmwrwh59gqwgfcs1jevsbahxw on edu.regular_grades (std_id);
create index idx_4uha48ytsvm0oy0dn16v694u3 on edu.regular_test_grades (regular_grade_id);
create index idx_2ofh2k9b4pj51ftjaf0e9oj3s on edu.room_applies_rooms (room_apply_id);
create index idx_oaii80w561p28ji3r86pv8djo on edu.room_applies_times (room_apply_id);
create index idx_nw4fpd24j0jdnkjmyucxb8gkp on edu.schedule_suggest_activities (suggest_id);
create index idx_127cshdpus15xol454uaojjfe on edu.share_course_groups (parent_id);
create index idx_dsgghmsmfesuqdn7ct736a6re on edu.share_course_groups (plan_id);
create index idx_4kls4p6klclkgscfvak8pqsdf on edu.share_plan_courses (group_id);
create index idx_qayu145ivc7x931n23vnaxskr on edu.std_alt_courses (std_id);
create index idx_oqnutavt0ni5equ6cxmx15yv9 on edu.std_alt_courses_news (std_alt_course_id);
create index idx_limcsyiq30whrglyd56hbsv1a on edu.std_alt_courses_olds (std_alt_course_id);
create index idx_sou84ebcj7jqrhl8670mw37xd on edu.std_course_abilities (std_id);
create index idx_e7b2h1mmriptticlepxklqk0c on edu.std_course_groups (parent_id);
create index idx_fu51ynnn9xdnujcuopnl6nu7k on edu.std_course_groups (plan_id);
create index idx_56fbv4yt2d1wshrjys9mjfarf on edu.std_exempt_courses (std_id);
create index idx_c642h40ncdah4eq1q71d1jat1 on edu.std_leave_files (leave_id);
create index idx_b7n6dqvs1vm7wjyc5mcwern37 on edu.std_leave_lessons (clazz_id);
create index idx_fyc4q8xtjps60xf5gfovi398s on edu.std_plan_courses (group_id);
create index idx_oi064e8gd1lg81fdcrvgp4p2a on edu.std_semester_gpas (std_gpa_id);
create index idx_k2it4d6hlqi69f22ht73l2ogb on edu.std_year_gpas (std_gpa_id);
create index idx_4kc8i1iwg3dcxgp93s9lcfui9 on edu.subclazzes (clazz_id);
create index idx_6wbdmn5a9toyucjxt4pqyxjh5 on edu.syllabus_assessments (syllabus_id);
create index idx_hqrxviradweba2ve2ptwofbql on edu.syllabus_cases (syllabus_id);
create index idx_8sl5w9jfgibrpr6amljeme2g4 on edu.syllabus_credit_hours (syllabus_id);
create index idx_8qx3072jupsnmwta4pr2am1wq on edu.syllabus_docs (course_id);
create index idx_khcn79d0h9o2224wnk5sul1f6 on edu.syllabus_experiments (syllabus_id);
create index idx_dfywiu9r8v1lje8xa5ewsykt on edu.syllabus_method_designs (syllabus_id);
create index idx_boimgnna7omgr47k2ekt5gqjh on edu.syllabus_objectives (syllabus_id);
create index idx_g2stn7s4196pb6su9bhdhqr1a on edu.syllabus_outcomes (syllabus_id);
create index idx_7qdhoj27l6atb3a2lky1m0pyf on edu.syllabus_texts (syllabus_id);
create index idx_jf6pjysudnyysiitqpeh2j1yt on edu.syllabus_texts (parent_id);
create index idx_f09xp32cvvyffk0fikf3432yf on edu.syllabus_topic_elements (topic_id);
create index idx_bvfnvma9c5es1u33pagskn816 on edu.syllabus_topic_hours (topic_id);
create index idx_rpwl1ty7otjsq11olf9r0npqt on edu.syllabus_topics (syllabus_id);
create index idx_qex7k49m7ujh56q2m44ge0l2n on edu.syllabuses_levels (syllabus_id);
create index idx_5j67g3q269502ouxrogyd887w on edu.syllabuses_majors (syllabus_id);
create index idx_gff3qkdgoqcp85i7f5xk3hy93 on edu.syllabuses_textbooks (syllabus_id);
create index idx_d5xdfpjitx7jv41r4gk8u0qrb on edu.teaching_plan_sections (plan_id);
create index idx_ks5c08r0ewjy6tqj2bdktvcnr on edu.term_campuses (program_id);
create index idx_llkqxeps662bpfy07txdda2x9 on flow.edu_cert_exempt_applies_courses (cert_exempt_apply_id);
create index idx_6h3erf71w3qv90ufpoh8egp2s on flow.edu_course_alt_applies (std_id);
create index idx_khemcapxwkjxmq4cy33adk9lq on flow.edu_course_alt_applies_news (course_alt_apply_id);
create index idx_dvtao95i0inafotdooks5gcnp on flow.edu_course_alt_applies_olds (course_alt_apply_id);
create index idx_dekf0w4tvano8kx1nyx9414it on flow.edu_course_type_change_applies (std_id);
create index idx_2mk8rl7id68acmo8sk7h8gs4b on flow.edu_new_course_applies_tags (new_course_apply_id);
create index idx_pfxpk2do32b5txki4hkw2jnxn on flow.edu_new_course_apply_hours (course_apply_id);
create index idx_8f6pd4rrn6otdrimouy7sb65i on flow.process_logs (flow_type_id, entity_id);
create index idx_4qtd3v5e09e2i9bck3oakp4yr on his.edu_course_grades (clazz_id);
create index idx_5jetp4mnl4e0wv0xf65xpolo7 on his.edu_course_grades (std_id);
create index idx_mhoqx05hw4fqqupyfa3wf6sb2 on his.edu_course_grades (project_id);
create index idx_3pvbkxqc36l8ngt03321vf1hy on his.edu_course_takers (clazz_id);
create index idx_senti97jh80q5xhh9q5rkvknd on his.edu_course_takers (semester_id);
create index idx_6qs3494fnm7ifknq96a1b8r89 on his.edu_exam_grades (course_grade_id);
create index idx_bqer3r9thk2y3grmr8xalkp9u on his.edu_exam_takers (exam_room_id);
create index idx_peop4o043mp7t9vik78bkwnnc on his.edu_exam_takers (clazz_id);
create index idx_7jjbv89v7xsd97ul68dg75q48 on his.edu_ga_grades (course_grade_id);
create index idx_ge26c8oiqt7qus9se6lhkm1lm on his.edu_regular_grades (clazz_id);
create index idx_qveyhipxc0xk5wlfghb0ypuoe on his.edu_regular_grades (std_id);
create index idx_cgqdfncp4cyc44didyefmiem2 on his.edu_regular_test_grades (regular_grade_id);
create index idx_685qcltuowforqswdtamn9twj on prac.inv_closure_review_details (review_id);
create index idx_m2t7iuwlel6emr4vx22n4qehv on prac.inv_closure_review_groups_experts (closure_review_group_id);
create index idx_jt69c02pxw1fluu7c5bcjoj36 on prac.inv_init_review_details (review_id);
create index idx_2iin7gkfnols861113p379k4r on prac.inv_init_review_groups_experts (init_review_group_id);
create index idx_buocfkkkiaei916q2ycuatltx on prac.inv_level_jounals (project_id);
create index idx_4wi0894n7irbfriincwlb1v49 on prac.inv_materials (project_id);
create index idx_jgmkdgcx6b45gje06wsrddoov on prac.inv_members (project_id);
create index idx_frrxd4jd7ukujwlkkhfixewsk on prac.inv_projects_instructors (project_id);
create index idx_b7p23pjlvtc8tseu6kp1vw66h on prac.inv_promotion_defense_members (group_id);
create index idx_48xavf7sfkprspggdrro5we82 on prac.inv_stage_types (parent_id);
create index idx_ntjvah3rh2kfhrn2my4c2ye37 on prac.inv_stages (batch_id);
create index idx_g3sayvuiofpvq3mn3ng2e1yvy on qos.assess_grades (criteria_id);
create index idx_mi7ww5l00nl6fs4iutwvt7rvx on qos.category_stat_grades (stat_id);
create index idx_r7swvtnb56g4gghc15grhhu0h on qos.category_stat_ranges (stat_id);
create index idx_qg7n56vxh2knmj2tarp3k3mmp on qos.course_indicator_stats (stat_id);
create index idx_ge1xy7c75yebe89psnbqqnmmf on qos.course_option_stats (question_stat_id);
create index idx_gll1inanpvdrco57654f2t0kr on qos.course_question_stats (stat_id);
create index idx_4xgi7yqagty3jwp7tvywu7jpm on qos.feedbacks (semester_id, teacher_id);
create index idx_l8aq1ny8y75hpngyciaa5s7a6 on qos.feedbacks (semester_id, teach_depart_id);
create index idx_bdmhluql4kminsm39jio58lhb on qos.options (group_id);
create index idx_8bhmm2vgbinad1w0aylpxt4n7 on qos.question_results (result_id);
create index idx_1mhhdaxdan0vekgoofvqin7hk on qos.questionnaires_questions (questionnaire_id);
create index idx_ntyfknpfh0x0vn49nm18bbh2i on std.bills (std_id);
create index idx_o6kjrquuoijirhvpeytmy2fxx on std.debts (std_id);
create index idx_h6txa38ww6xmjierqkvw1u4d7 on std.degree_results (std_id);
create index idx_66bod6jp4lk8p1u4c3cf6pix4 on std.exchange_programs_schools (exchange_program_id);
create index idx_i1rmelienjou7tluo440dlom1 on std.person_check_items (check_id);
create index idx_tu5uubg7w074d5n7aoudd13r on std.social_relations (std_id);
create index idx_4ajhq7ycatuntfts147dl6sx6 on std.std_alteration_items (alteration_id);
create index idx_sure8ht5dxo4cbr29cq0bbq50 on trd.rd_achievement_awards (achievement_id);
create index idx_tf2rabxu2vp1vl422toaq0y0t on trd.rd_achievement_members (achievement_id);
create index idx_2k68gfloim9504k22uiuvfauk on trd.rd_project_members (project_id);
create index idx_9i3d8et45ck4qwhsgrvnvdqrc on trd.rd_projects_leaders (rd_project_id);
create index idx_9loptf43av6ac4srdn8nuq58p on trd.teaching_team_members (team_id);
create index idx_iksg21jj8jtd80126xrj83fp6 on trd.teaching_teams_leaders (teaching_team_id);
create index idx_pynj8wtc3cfhryhr6988ymw1h on trd.textbook_awards (achievement_id);
create index idx_nkkgske0o3mh9cxc2s5hrpbvc on trd.textbook_editors (achievement_id);
