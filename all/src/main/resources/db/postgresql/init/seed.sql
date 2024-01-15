INSERT INTO code.academic_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (70, '70', '初级中学', NULL, '2017-03-04 00:00:00', NULL, '2015-04-21 09:57:33', NULL);
INSERT INTO code.academic_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (11, '11', '硕士研究生', NULL, '2017-03-04 00:00:00', NULL, '2014-10-14 15:09:27.238', NULL);
INSERT INTO code.academic_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (10, '10', '博士研究生', NULL, '2017-03-04 00:00:00', NULL, '2014-10-14 15:09:36.965', NULL);
INSERT INTO code.academic_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (40, '40', '中等职业', NULL, '2017-03-04 00:00:00', NULL, '2015-04-21 09:56:59', NULL);
INSERT INTO code.academic_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (80, '80', '小学', NULL, '2017-03-04 00:00:00', NULL, '2015-04-21 09:57:46', NULL);
INSERT INTO code.academic_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (30, '30', '大学专科', NULL, '2017-03-04 00:00:00', NULL, '2014-10-14 15:09:13.977', NULL);
INSERT INTO code.academic_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (60, '60', '普通高中', NULL, '2017-03-04 00:00:00', NULL, '2015-04-21 09:57:22', NULL);
INSERT INTO code.academic_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (20, '20', '大学本科', NULL, '2017-03-04 00:00:00', NULL, '2014-10-14 15:09:20.921', NULL);

INSERT INTO code.education_results (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '01', '毕业', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.education_results (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (3, '03', '结业', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.education_results (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (8, '08', '肄业', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);

INSERT INTO code.education_degrees (id, code, name,level_id,result_id, en_name, begin_on, end_on, updated_at, remark) VALUES (11, '11', '博士研究生毕业', 10,1,NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.education_degrees (id, code, name,level_id,result_id, en_name, begin_on, end_on, updated_at, remark) VALUES (14, '14', '硕士研究生毕业', 11,1,NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.education_degrees (id, code, name,level_id,result_id, en_name, begin_on, end_on, updated_at, remark) VALUES (21, '21', '大学本科毕业', 20,1,NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);

INSERT INTO code.degree_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '1', '学士学位', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.degree_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (2, '2', '硕士学位', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.degree_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (3, '3', '博士学位', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);

INSERT INTO code.countries(id, code, name, en_name,short_name,begin_on,  updated_at,alpha2_code,alpha3_code) VALUES (156, '156','中华人民共和国', 'China','中国',current_date,now(),'CN','CHN');

INSERT INTO code.course_take_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '01', '正常', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.course_take_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (3, '02', '重修', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.course_take_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (5, '03', '免修', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);

INSERT INTO code.education_levels (id, code, name, en_name, begin_on, end_on, updated_at, from_level_id, to_level_id, remark) VALUES (1, '23', '高起本', NULL, '2005-07-24', NULL, '2019-10-22 23:37:43.727598', 60, 20, NULL);
INSERT INTO code.education_levels (id, code, name, en_name, begin_on, end_on, updated_at, from_level_id, to_level_id, remark) VALUES (3, '22', '专升本', NULL, '2005-07-24', NULL, '2019-10-22 23:37:56.352206', 30, 20, NULL);
INSERT INTO code.education_levels (id, code, name, en_name, begin_on, end_on, updated_at, from_level_id, to_level_id, remark) VALUES (2, '21', '高起专', NULL, '2005-07-24', NULL, '2019-10-22 23:38:10.792471', 60, 30, NULL);
INSERT INTO code.education_levels (id, code, name, en_name, begin_on, end_on, updated_at, from_level_id, to_level_id, remark) VALUES (4, '4', '高中', NULL, '2016-01-20', NULL, '2016-01-20 00:00:00', 70, 60, NULL);
INSERT INTO code.education_levels (id, code, name, en_name, begin_on, end_on, updated_at, from_level_id, to_level_id, remark) VALUES (5, '5', '专科', NULL, '2016-01-22', NULL, '2016-01-22 00:00:00', 60, 30, NULL);

INSERT INTO code.education_levels (id, code, name, en_name, begin_on, end_on, updated_at, from_level_id, to_level_id, remark) VALUES (31, '31', '硕士', NULL, '2016-01-20', NULL, '2016-01-20 00:00:00', 20,11, NULL);
INSERT INTO code.education_levels (id, code, name, en_name, begin_on, end_on, updated_at, from_level_id, to_level_id, remark) VALUES (32, '32', '博士', NULL, '2016-01-22', NULL, '2016-01-22 00:00:00', 11, 10, NULL);

INSERT INTO code.exam_modes (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (2, '1', '考查', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.exam_modes (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '0', '考试', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);

INSERT INTO code.grading_modes (id, code, name, en_name, begin_on, end_on, numerical, updated_at, remark) VALUES (4, '4', '标准分', NULL, '2007-05-26', NULL, true, '2017-05-23 15:54:50.522236', NULL);
INSERT INTO code.grading_modes (id, code, name, en_name, begin_on, end_on, numerical, updated_at, remark) VALUES (3, '3', '五分等级制', NULL, '2007-05-26', NULL, false, '2017-05-23 15:55:09.497529', NULL);
INSERT INTO code.grading_modes (id, code, name, en_name, begin_on, end_on, numerical, updated_at, remark) VALUES (5, '5', '两级制', NULL, '2007-05-26', NULL, false, '2017-05-23 15:56:22.831842', NULL);
INSERT INTO code.grading_modes (id, code, name, en_name, begin_on, end_on, numerical, updated_at, remark) VALUES (1, '1', '百分制', NULL, '2015-07-01', NULL, true, '2015-07-01 00:00:00', NULL);

INSERT INTO code.study_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '01', '全日制', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.study_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (3, '03', '夜大学', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.study_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (8, '08', '研修班', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);

INSERT INTO code.day_parts (id, begin_on, code, en_name, end_on, name, updated_at, color, remark) VALUES (1, current_date, '1', NULL, NULL, '上午', '2016-06-04 00:00:00', '#eeff00', NULL);
INSERT INTO code.day_parts (id, begin_on, code, en_name, end_on, name, updated_at, color, remark) VALUES (2, current_date, '2', NULL, NULL, '下午', '2016-06-04 00:00:00', '#33bb00', NULL);
INSERT INTO code.day_parts (id, begin_on, code, en_name, end_on, name, updated_at, color, remark) VALUES (3, current_date, '3', NULL, NULL, '晚上', '2016-06-04 00:00:00', 'pink', NULL);

INSERT INTO code.classroom_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '01', '多媒体教室', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);

INSERT INTO code.teach_lang_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '01', '中文', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);

INSERT INTO code.exam_forms (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '01', '笔试', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.exam_forms (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (2, '02', '口试', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.exam_forms (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (3, '03', '考查', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);

INSERT INTO code.election_modes (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '1', '指定', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.election_modes (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (2, '02', '自选', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.election_modes (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (3, '03', '代理', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);

INSERT INTO code.exam_types (id, begin_on, code, en_name, end_on, name, updated_at, for_deferred) VALUES (1, '2016-05-04', '01', NULL, NULL, '期末', '2016-05-04 00:00:00', false);
INSERT INTO code.exam_types (id, begin_on, code, en_name, end_on, name, updated_at, for_deferred) VALUES (3, '2016-05-04', '03', NULL, NULL, '补考', '2016-05-04 00:00:00', false);
INSERT INTO code.exam_types (id, begin_on, code, en_name, end_on, name, updated_at, for_deferred) VALUES (4, '2016-05-04', '04', NULL, NULL, '缓考', '2016-05-04 00:00:00', true);
INSERT INTO code.exam_types (id, begin_on, code, en_name, end_on, name, updated_at, for_deferred) VALUES (2, '2016-05-04', '02', NULL, NULL, '补缓考', '2016-05-04 00:00:00', false);

INSERT INTO code.exam_statuses (id, code, name, en_name, begin_on, end_on, updated_at, attended, has_deferred, remark,  cheating) VALUES (1, '1', '正常', NULL, '2015-07-01', NULL, '2015-07-01 00:00:00', true, false, NULL,   false);
INSERT INTO code.exam_statuses (id, code, name, en_name, begin_on, end_on, updated_at, attended, has_deferred, remark,  cheating) VALUES (2, '2', '缓考', NULL, '2015-07-01', NULL, '2015-07-01 00:00:00', false, true, NULL, false);
INSERT INTO code.exam_statuses (id, code, name, en_name, begin_on, end_on, updated_at, attended, has_deferred, remark,  cheating) VALUES (10, '10', '作弊', NULL, '2017-03-30', NULL, '2017-03-30 13:49:04.346837', true, false, NULL,  true);
INSERT INTO code.exam_statuses (id, code, name, en_name, begin_on, end_on, updated_at, attended, has_deferred, remark,  cheating) VALUES (3, '3', '缺考', NULL, '2015-07-01', NULL, '2015-07-01 00:00:00', false, false, NULL,  false);

INSERT INTO code.grade_types (id, begin_on, code, en_name, end_on, name, updated_at, exam_type_id, remark) VALUES (0, '2007-04-30', '0001', NULL, NULL, '最终成绩', '2007-04-30 00:00:00', NULL, NULL);
INSERT INTO code.grade_types (id, begin_on, code, en_name, end_on, name, updated_at, exam_type_id, remark) VALUES (8, '2014-12-11', '0018', NULL, NULL, '缓考总评', '2014-12-11 00:00:00', NULL, NULL);
INSERT INTO code.grade_types (id, begin_on, code, en_name, end_on, name, updated_at, exam_type_id, remark) VALUES (7, '2006-10-29', '0007', NULL, NULL, '期末总评', '2006-10-29 14:56:09', NULL, NULL);
INSERT INTO code.grade_types (id, begin_on, code, en_name, end_on, name, updated_at, exam_type_id, remark) VALUES (9, '2014-12-11', '0019', NULL, NULL, '补考总评', '2014-12-11 00:00:00', NULL, NULL);
INSERT INTO code.grade_types (id, begin_on, code, en_name, end_on, name, updated_at, exam_type_id, remark) VALUES (4, '2006-10-29', '0004', NULL, NULL, '补考成绩', '2006-10-29 14:56:09', 3, NULL);
INSERT INTO code.grade_types (id, begin_on, code, en_name, end_on, name, updated_at, exam_type_id, remark) VALUES (6, '2006-10-29', '0006', NULL, NULL, '缓考成绩', '2011-06-03 13:41:02', 4, NULL);
INSERT INTO code.grade_types (id, begin_on, code, en_name, end_on, name, updated_at, exam_type_id, remark) VALUES (3, '2006-10-29', '0002', 'Component Score', NULL, '平时成绩', '2007-12-20 00:00:00', NULL, NULL);
INSERT INTO code.grade_types (id, begin_on, code, en_name, end_on, name, updated_at, exam_type_id, remark) VALUES (2, '2006-10-29', '0003', 'Final Exam Score', NULL, '期末成绩', '2007-03-09 00:00:00', 1, NULL);
INSERT INTO code.grade_types (id, begin_on, code, en_name, end_on, name, updated_at, exam_type_id, remark) VALUES (1, '2006-10-29', '0008', NULL, '2016-05-03', '期中成绩', '2007-04-30 00:00:00', NULL, NULL);

INSERT INTO code.genders (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '01', '男', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.genders (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (2, '02', '女', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);

INSERT INTO code.professional_grades (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '1', '高级', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.professional_grades (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (2, '2', '中级', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.professional_grades (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (3, '3', '初级', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);

INSERT INTO code.professional_titles (id, code, name, grade_id,en_name, begin_on, end_on, updated_at, remark) VALUES (11, '011', '教授', 1,NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.professional_titles (id, code, name, grade_id,en_name, begin_on, end_on, updated_at, remark) VALUES (12, '012', '副教授',1, NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.professional_titles (id, code, name, grade_id,en_name, begin_on, end_on, updated_at, remark) VALUES (13, '013', '讲师', 2,NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.professional_titles (id, code, name, grade_id,en_name, begin_on, end_on, updated_at, remark) VALUES (14, '014', '助教',3, NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);

INSERT INTO code.professional_titles (id, code, name, grade_id,en_name, begin_on, end_on, updated_at, remark) VALUES (621, '621', '研究员（社会科学）', 1,NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.professional_titles (id, code, name, grade_id,en_name, begin_on, end_on, updated_at, remark) VALUES (622, '622', '副研究员（社会科学）',1, NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.professional_titles (id, code, name, grade_id,en_name, begin_on, end_on, updated_at, remark) VALUES (623, '623', '助理研究员（社会科学）',2, NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);

INSERT INTO code.professional_titles (id, code, name, grade_id,en_name, begin_on, end_on, updated_at, remark) VALUES (611, '611', '研究员（自然科学）', 1,NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.professional_titles (id, code, name, grade_id,en_name, begin_on, end_on, updated_at, remark) VALUES (612, '612', '副研究员（自然科学）',1, NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.professional_titles (id, code, name, grade_id,en_name, begin_on, end_on, updated_at, remark) VALUES (613, '613', '助理研究员（自然科学）',2, NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);


INSERT INTO code.work_statuses (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '01', '退休', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.work_statuses (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (2, '02', '离休', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.work_statuses (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (4, '04', '返聘', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.work_statuses (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (6, '06', '辞职', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.work_statuses (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (7, '07', '离职', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.work_statuses (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (11, '11', '在职', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.work_statuses (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (12, '12', '延聘', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.work_statuses (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (14, '14', '长病假', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.work_statuses (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (15, '15', '因公出国', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.work_statuses (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (16, '16', '停薪留职', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);

INSERT INTO code.staff_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (11, '11', '专任教师', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL );
INSERT INTO code.staff_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (12, '12', '教辅教师', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL );
INSERT INTO code.staff_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (13, '13', '兼职教师', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL );

INSERT INTO code.id_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '1', '居民身份证', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL );
INSERT INTO code.id_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (2, '2', '军官证', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL );
INSERT INTO code.id_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (3, '3', '士兵证', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL );
INSERT INTO code.id_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (4, '4', '文职干部证', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL );
INSERT INTO code.id_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (5, '5', '部队离退休证', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL );
INSERT INTO code.id_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (6, '6', '香港特区护照/身份证明', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL );
INSERT INTO code.id_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (7, '7', '澳门特区护照/身份证明', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL );
INSERT INTO code.id_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (8, '8', '台湾居民来往大陆通行证', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL );
INSERT INTO code.id_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (9, '9', '境外永久居住证', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL );
INSERT INTO code.id_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (10, 'A', '护照', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL );
INSERT INTO code.id_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (11, 'B', '户口薄', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL );
INSERT INTO code.id_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (12, 'Z', '其他', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL );

insert into code.teaching_natures(id,code,name,category,begin_on,updated_at)
select 1,'1','理论',1,current_date-1,now() where not exists(select * from code.teaching_natures tn where tn.id=1);
insert into code.teaching_natures(id,code,name,category,begin_on,updated_at)
select 2,'2','实验',2,current_date-1,now() where not exists(select * from code.teaching_natures tn where tn.id=2);
insert into code.teaching_natures(id,code,name,category,begin_on,updated_at)
select 9,'9','实践',9,current_date-1,now() where not exists(select * from code.teaching_natures tn where tn.id=9);

insert into code.teaching_methods(id,code,name,begin_on,updated_at) values(1,'1','线下授课',current_date-1,now());
insert into code.teaching_methods(id,code,name,begin_on,updated_at) values(2,'2','线上直播',current_date-1,now());
insert into code.teaching_methods(id,code,name,begin_on,updated_at) values(3,'3','线上录播',current_date-1,now());
insert into code.teaching_methods(id,code,name,begin_on,updated_at) values(4,'4','线上线下同步',current_date-1,now());

insert into code.course_natures(id,code,name,practical,begin_on,updated_at) values(1,'1','理论课',false,current_date-1,now());
insert into code.course_natures(id,code,name,practical,begin_on,updated_at) values(2,'2','术科课',false,current_date-1,now());
insert into code.course_natures(id,code,name,practical,begin_on,updated_at) values(3,'3','单独设立实验课',false,current_date-1,now());
insert into code.course_natures(id,code,name,practical,begin_on,updated_at) values(4,'4','实践课',true,current_date-1,now());

insert into code.edu_categories(id,code,name,begin_on,updated_at) values(30,'30','成人高等教育',current_date -10,now());
insert into code.edu_categories(id,code,name,begin_on,updated_at) values(31,'31','普通高等教育',current_date -10,now());
insert into code.edu_categories(id,code,name,begin_on,updated_at) values(32,'32','网络教育',current_date -10,now());

INSERT INTO code.user_categories (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '01', '教师', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.user_categories (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (2, '02', '学生', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.user_categories (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (3, '03', '管理人员', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.user_categories (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (4, '04', '其他', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);

INSERT INTO code.tutor_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '01', '博士生导师', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.tutor_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (2, '02', '硕士生导师', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.tutor_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (3, '03', '博士硕士生导师', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);

INSERT INTO code.activity_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '01', '排课', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.activity_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (2, '02', '排考', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);

INSERT INTO code.student_statuses (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '01', '在读', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.student_statuses (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (25, '25', '毕业', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.student_statuses (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (2, '02', '休学', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.student_statuses (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (3, '03', '退学', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.student_statuses (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (4, '04', '延期', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.student_statuses (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (5, '05', '未入学', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);

INSERT INTO edu.room_occupy_apps (id, name, activity_url) VALUES (1, 'course', '--');
INSERT INTO edu.room_occupy_apps (id, name, activity_url) VALUES (2, 'exam', '--');
INSERT INTO edu.room_occupy_apps (id, name, activity_url) VALUES (3, 'apply', '--');

INSERT INTO code.nations (id, code, name,alpha_code, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '01', '汉族', 'HA',NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.nations (id, code, name,alpha_code, en_name, begin_on, end_on, updated_at, remark) VALUES (02, '02', '蒙古族','MG', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.nations (id, code, name,alpha_code, en_name, begin_on, end_on, updated_at, remark) VALUES (03, '03', '回族', 'HU',NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.nations (id, code, name,alpha_code, en_name, begin_on, end_on, updated_at, remark) VALUES (04, '04', '藏族','ZA', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.nations (id, code, name,alpha_code, en_name, begin_on, end_on, updated_at, remark) VALUES (05, '05', '维吾尔族','UG', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.nations (id, code, name,alpha_code, en_name, begin_on, end_on, updated_at, remark) VALUES (06, '06', '苗族','MH', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.nations (id, code, name,alpha_code, en_name, begin_on, end_on, updated_at, remark) VALUES (07, '07', '彝族','YI', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.nations (id, code, name,alpha_code, en_name, begin_on, end_on, updated_at, remark) VALUES (08, '08', '壮族', 'ZH',NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.nations (id, code, name,alpha_code, en_name, begin_on, end_on, updated_at, remark) VALUES (09, '09', '布依族', 'BY',NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.nations (id, code, name,alpha_code, en_name, begin_on, end_on, updated_at, remark) VALUES (10, '10', '朝鲜族', 'CS',NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.nations (id, code, name,alpha_code, en_name, begin_on, end_on, updated_at, remark) VALUES (11, '11', '满族', 'MA',NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.nations (id, code, name,alpha_code, en_name, begin_on, end_on, updated_at, remark) VALUES (12, '12', '侗族', 'DO',NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.nations (id, code, name,alpha_code, en_name, begin_on, end_on, updated_at, remark) VALUES (14, '14', '白族', 'MA',NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.nations (id, code, name,alpha_code, en_name, begin_on, end_on, updated_at, remark) VALUES (15, '15', '土家族','TJ', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.nations (id, code, name,alpha_code, en_name, begin_on, end_on, updated_at, remark) VALUES (17, '17', '哈萨克族','KZ', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.nations (id, code, name,alpha_code, en_name, begin_on, end_on, updated_at, remark) VALUES (22, '22', '畲族','SH', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.nations (id, code, name,alpha_code, en_name, begin_on, end_on, updated_at, remark) VALUES (23, '23', '高山族','GS', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.nations (id, code, name,alpha_code, en_name, begin_on, end_on, updated_at, remark) VALUES (33, '33', '羌族', 'QI',NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.nations (id, code, name,alpha_code, en_name, begin_on, end_on, updated_at, remark) VALUES (37, '37', '仡佬族', 'GL',NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.nations (id, code, name,alpha_code, en_name, begin_on, end_on, updated_at, remark) VALUES (38, '38', '锡伯族', 'XB',NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.nations (id, code, name,alpha_code, en_name, begin_on, end_on, updated_at, remark) VALUES (97, '97', '其他', 'QT',NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);

insert into base.c_education_types(id,code,name,begin_on,updated_at) values(1,'1','普通本科',current_date -10,now());
insert into base.c_std_types(id,code,name,begin_on,updated_at) values(1,'1','普通',current_date -10,now());

INSERT INTO code.std_alter_types (id, begin_on, code, en_name, end_on, name, updated_at) VALUES (1, '2017-04-14', '1', NULL, NULL, '休学', '2017-04-14 14:40:00.813518');
INSERT INTO code.std_alter_types (id, begin_on, code, en_name, end_on, name, updated_at) VALUES (2, '2017-04-14', '2', NULL, NULL, '退学', '2017-04-14 14:40:24.358813');
INSERT INTO code.std_alter_types (id, begin_on, code, en_name, end_on, name, updated_at) VALUES (3, '2017-04-14', '3', NULL, NULL, '复学', '2017-04-14 14:41:06.502864');
INSERT INTO code.std_alter_types (id, begin_on, code, en_name, end_on, name, updated_at) VALUES (4, '2017-04-14', '4', NULL, NULL, '放弃学籍', '2017-04-14 14:41:36.211759');
INSERT INTO code.std_alter_types (id, begin_on, code, en_name, end_on, name, updated_at) VALUES (5, '2017-04-14', '5', NULL, NULL, '转专业', '2017-04-14 14:42:10.183278');
INSERT INTO code.std_alter_types (id, begin_on, code, en_name, end_on, name, updated_at) VALUES (6, '2017-04-26', '6', NULL, NULL, '恢复学籍', '2017-04-26 22:13:33.004115');
INSERT INTO code.std_alter_types (id, begin_on, code, en_name, end_on, name, updated_at) VALUES (7, '2017-04-26', '7', NULL, NULL, '延期毕业', '2017-04-26 22:13:33.004115');
INSERT INTO code.std_alter_types (id, begin_on, code, en_name, end_on, name, updated_at) VALUES (11, '2019-03-31', '11', NULL, NULL, '保留学籍', '2019-04-02 16:12:20.816552');

INSERT INTO code.std_alter_reasons(id, begin_on, code, en_name, end_on, name, updated_at) VALUES (11, '2019-03-31', '11', NULL, NULL, '个人原因', '2019-04-02 16:12:20.816552');

insert into code.discipline_categories(id,code,name,begin_on,updated_at) values(1,'01','哲学',current_date -10*365,now());
insert into code.discipline_categories(id,code,name,begin_on,updated_at) values(2,'02','经济学',current_date -10*365,now());
insert into code.discipline_categories(id,code,name,begin_on,updated_at) values(3,'03','法学',current_date -10*365,now());
insert into code.discipline_categories(id,code,name,begin_on,updated_at) values(4,'04','教育学',current_date -10*365,now());
insert into code.discipline_categories(id,code,name,begin_on,updated_at) values(5,'05','文学',current_date -10*365,now());
insert into code.discipline_categories(id,code,name,begin_on,updated_at) values(6,'06','历史学',current_date -10*365,now());
insert into code.discipline_categories(id,code,name,begin_on,updated_at) values(7,'07','理学',current_date -10*365,now());
insert into code.discipline_categories(id,code,name,begin_on,updated_at) values(8,'08','工学',current_date -10*365,now());
insert into code.discipline_categories(id,code,name,begin_on,updated_at) values(9,'09','农学',current_date -10*365,now());
insert into code.discipline_categories(id,code,name,begin_on,updated_at) values(10,'10','医学',current_date -10*365,now());
insert into code.discipline_categories(id,code,name,begin_on,updated_at) values(11,'11','军事学',current_date -10*365,now());
insert into code.discipline_categories(id,code,name,begin_on,updated_at) values(12,'12','管理学',current_date -10*365,now());
insert into code.discipline_categories(id,code,name,begin_on,updated_at) values(13,'13','艺术学',current_date -10*365,now());
insert into code.discipline_categories(id,code,name,begin_on,updated_at) values(14,'14','交叉学科',current_date -10*365,now());
--学校性质类别
insert into code.institution_categories(id,code,name,begin_on,updated_at) values(1,'01','综合大学',current_date -10,now());
insert into code.institution_categories(id,code,name,begin_on,updated_at) values(2,'02','理工院校',current_date -10,now());
insert into code.institution_categories(id,code,name,begin_on,updated_at) values(3,'03','农林院校',current_date -10,now());
insert into code.institution_categories(id,code,name,begin_on,updated_at) values(4,'04','林业院校',current_date -10,now());
insert into code.institution_categories(id,code,name,begin_on,updated_at) values(5,'05','医药院校',current_date -10,now());
insert into code.institution_categories(id,code,name,begin_on,updated_at) values(6,'06','师范院校',current_date -10,now());
insert into code.institution_categories(id,code,name,begin_on,updated_at) values(7,'07','语文院校',current_date -10,now());
insert into code.institution_categories(id,code,name,begin_on,updated_at) values(8,'08','财经院校',current_date -10,now());
insert into code.institution_categories(id,code,name,begin_on,updated_at) values(9,'09','政法院校',current_date -10,now());
insert into code.institution_categories(id,code,name,begin_on,updated_at) values(10,'10','体育院校',current_date -10,now());
insert into code.institution_categories(id,code,name,begin_on,updated_at) values(11,'11','艺术院校',current_date -10,now());
insert into code.institution_categories(id,code,name,begin_on,updated_at) values(12,'12','民族院校',current_date -10,now());

--建筑物用途
INSERT INTO code.building_types (id, begin_on, code, en_name, end_on, name, updated_at) VALUES (11, '2019-03-31', '11', NULL, NULL, '教学楼', '2019-04-02 16:12:20.816552');
INSERT INTO code.building_types (id, begin_on, code, en_name, end_on, name, updated_at) VALUES (12, '2019-03-31', '12', NULL, NULL, '综合楼', '2019-04-02 16:12:20.816552');
INSERT INTO code.building_types (id, begin_on, code, en_name, end_on, name, updated_at) VALUES (13, '2019-03-31', '13', NULL, NULL, '实验楼', '2019-04-02 16:12:20.816552');
INSERT INTO code.building_types (id, begin_on, code, en_name, end_on, name, updated_at) VALUES (14, '2019-03-31', '14', NULL, NULL, '图书馆(室)', '2019-04-02 16:12:20.816552');
INSERT INTO code.building_types (id, begin_on, code, en_name, end_on, name, updated_at) VALUES (15, '2019-03-31', '15', NULL, NULL, '体育活动室', '2019-04-02 16:12:20.816552');
INSERT INTO code.building_types (id, begin_on, code, en_name, end_on, name, updated_at) VALUES (21, '2019-03-31', '21', NULL, NULL, '宿舍', '2019-04-02 16:12:20.816552');
INSERT INTO code.building_types (id, begin_on, code, en_name, end_on, name, updated_at) VALUES (31, '2019-03-31', '31', NULL, NULL, '办公楼', '2019-04-02 16:12:20.816552');
INSERT INTO code.building_types (id, begin_on, code, en_name, end_on, name, updated_at) VALUES (41, '2019-03-31', '41', NULL, NULL, '其他用房', '2019-04-02 16:12:20.816552');

--部门类别
insert into code.department_categories(id,code,name,indexno,begin_on,updated_at) values(10,'10','党政机构','10',current_date-1,now());
insert into code.department_categories(id,code,name,indexno,begin_on,updated_at) values(20,'20','教学科研单位','20',current_date-1,now());
insert into code.department_categories(id,code,name,indexno,begin_on,updated_at) values(21,'21','科研机构','21',current_date-1,now());
insert into code.department_categories(id,code,name,indexno,begin_on,updated_at) values(30,'30','直属单位','30',current_date-1,now());

-----------如下代码需要插入后修改---------
insert into code.institutions(id,code,name,begin_on,updated_at)
values(10000,'10000','URP学院',current_date -10,now());

insert into base.schools(id,name,institution_id,logo_url,code,begin_on,short_name)
values(10000,'URP学院',10000,'http://openurp.net/logo.png',10000,current_date -10,'URP');

insert into base.departments(id,code,name,research,teaching,school_id,begin_on,updated_at,indexno)
values(1,'01','软件学院',true,true,10000,current_date -365,now(),'01');

insert into base.users(id,code,name,school_id,email,department_id,gender_id,begin_on,end_on,updated_at,category_id)
values(next_id('base.users'),'root','root',10000,'admin@school.com',1,1,current_date-365,current_date+10*365,now(),3);

