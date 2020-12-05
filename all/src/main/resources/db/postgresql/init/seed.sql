INSERT INTO code_gb.academic_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (70, '70', '初级中学', NULL, '2017-03-04 00:00:00', NULL, '2015-04-21 09:57:33', NULL);
INSERT INTO code_gb.academic_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (11, '11', '硕士研究生', NULL, '2017-03-04 00:00:00', NULL, '2014-10-14 15:09:27.238', NULL);
INSERT INTO code_gb.academic_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (10, '10', '博士研究生', NULL, '2017-03-04 00:00:00', NULL, '2014-10-14 15:09:36.965', NULL);
INSERT INTO code_gb.academic_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (40, '40', '中等职业', NULL, '2017-03-04 00:00:00', NULL, '2015-04-21 09:56:59', NULL);
INSERT INTO code_gb.academic_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (80, '80', '小学', NULL, '2017-03-04 00:00:00', NULL, '2015-04-21 09:57:46', NULL);
INSERT INTO code_gb.academic_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (30, '30', '大学专科', NULL, '2017-03-04 00:00:00', NULL, '2014-10-14 15:09:13.977', NULL);
INSERT INTO code_gb.academic_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (60, '60', '普通高中', NULL, '2017-03-04 00:00:00', NULL, '2015-04-21 09:57:22', NULL);
INSERT INTO code_gb.academic_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (20, '20', '大学本科', NULL, '2017-03-04 00:00:00', NULL, '2014-10-14 15:09:20.921', NULL);


INSERT INTO code_hb.education_levels (id, code, name, en_name, begin_on, end_on, updated_at, from_level_id, to_level_id, remark) VALUES (1, '23', '高起本', NULL, '2005-07-24', NULL, '2019-10-22 23:37:43.727598', 60, 20, NULL);
INSERT INTO code_hb.education_levels (id, code, name, en_name, begin_on, end_on, updated_at, from_level_id, to_level_id, remark) VALUES (3, '22', '专升本', NULL, '2005-07-24', NULL, '2019-10-22 23:37:56.352206', 30, 20, NULL);
INSERT INTO code_hb.education_levels (id, code, name, en_name, begin_on, end_on, updated_at, from_level_id, to_level_id, remark) VALUES (2, '21', '高起专', NULL, '2005-07-24', NULL, '2019-10-22 23:38:10.792471', 60, 30, NULL);
INSERT INTO code_hb.education_levels (id, code, name, en_name, begin_on, end_on, updated_at, from_level_id, to_level_id, remark) VALUES (4, '4', '高中', NULL, '2016-01-20', NULL, '2016-01-20 00:00:00', 70, 60, NULL);
INSERT INTO code_hb.education_levels (id, code, name, en_name, begin_on, end_on, updated_at, from_level_id, to_level_id, remark) VALUES (5, '5', '大专', NULL, '2016-01-22', NULL, '2016-01-22 00:00:00', 60, 30, NULL);


INSERT INTO code_hb.exam_modes (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (2, '1', '考查', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code_hb.exam_modes (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '0', '考试', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);


INSERT INTO code_hb.grading_modes (id, code, name, en_name, begin_on, end_on, numerical, updated_at, remark) VALUES (4, '4', '标准分', NULL, '2007-05-26', NULL, true, '2017-05-23 15:54:50.522236', NULL);
INSERT INTO code_hb.grading_modes (id, code, name, en_name, begin_on, end_on, numerical, updated_at, remark) VALUES (3, '3', '五分等级制', NULL, '2007-05-26', NULL, false, '2017-05-23 15:55:09.497529', NULL);
INSERT INTO code_hb.grading_modes (id, code, name, en_name, begin_on, end_on, numerical, updated_at, remark) VALUES (5, '5', '两级制', NULL, '2007-05-26', NULL, false, '2017-05-23 15:56:22.831842', NULL);
INSERT INTO code_hb.grading_modes (id, code, name, en_name, begin_on, end_on, numerical, updated_at, remark) VALUES (1, '1', '百分制', NULL, '2015-07-01', NULL, true, '2015-07-01 00:00:00', NULL);


INSERT INTO code_gb.study_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '01', '全日制', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);

INSERT INTO code_hb.day_parts (id, begin_on, code, en_name, end_on, name, updated_at, color, remark) VALUES (1, current_date, '1', NULL, NULL, '上午', '2016-06-04 00:00:00', '#eeff00', NULL);
INSERT INTO code_hb.day_parts (id, begin_on, code, en_name, end_on, name, updated_at, color, remark) VALUES (2, current_date, '2', NULL, NULL, '下午', '2016-06-04 00:00:00', '#33bb00', NULL);
INSERT INTO code_hb.day_parts (id, begin_on, code, en_name, end_on, name, updated_at, color, remark) VALUES (3, current_date, '3', NULL, NULL, '晚上', '2016-06-04 00:00:00', 'pink', NULL);


INSERT INTO code_hb.classroom_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '01', '多媒体教室', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);

INSERT INTO code_hb.teach_lang_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '01', '中文', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);

INSERT INTO code_hb.exam_forms (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '01', '笔试', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code_hb.exam_forms (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (2, '02', '口试', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code_hb.exam_forms (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (3, '03', '考查', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);


INSERT INTO code_hb.exam_forms (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '01', '笔试', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code_hb.exam_forms (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (2, '02', '口试', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code_hb.exam_forms (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (3, '03', '考查', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);

INSERT INTO code_hb.election_modes (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '1', '指定', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code_hb.election_modes (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (2, '02', '自选', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code_hb.election_modes (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (3, '03', '代理', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);



INSERT INTO code_hb.exam_types (id, begin_on, code, en_name, end_on, name, updated_at, for_deferred) VALUES (1, '2016-05-04', '01', NULL, NULL, '期末', '2016-05-04 00:00:00', false);
INSERT INTO code_hb.exam_types (id, begin_on, code, en_name, end_on, name, updated_at, for_deferred) VALUES (3, '2016-05-04', '03', NULL, NULL, '补考', '2016-05-04 00:00:00', false);
INSERT INTO code_hb.exam_types (id, begin_on, code, en_name, end_on, name, updated_at, for_deferred) VALUES (4, '2016-05-04', '04', NULL, NULL, '缓考', '2016-05-04 00:00:00', true);
INSERT INTO code_hb.exam_types (id, begin_on, code, en_name, end_on, name, updated_at, for_deferred) VALUES (2, '2016-05-04', '02', NULL, NULL, '补缓考', '2016-05-04 00:00:00', false);

INSERT INTO code_hb.exam_statuses (id, code, name, en_name, begin_on, end_on, updated_at, attended, has_deferred, remark,  cheating) VALUES (1, '1', '正常', NULL, '2015-07-01', NULL, '2015-07-01 00:00:00', true, false, NULL,   false);
INSERT INTO code_hb.exam_statuses (id, code, name, en_name, begin_on, end_on, updated_at, attended, has_deferred, remark,  cheating) VALUES (2, '2', '缓考', NULL, '2015-07-01', NULL, '2015-07-01 00:00:00', false, true, NULL, false);
INSERT INTO code_hb.exam_statuses (id, code, name, en_name, begin_on, end_on, updated_at, attended, has_deferred, remark,  cheating) VALUES (10, '10', '作弊', NULL, '2017-03-30', NULL, '2017-03-30 13:49:04.346837', true, false, NULL,  true);
INSERT INTO code_hb.exam_statuses (id, code, name, en_name, begin_on, end_on, updated_at, attended, has_deferred, remark,  cheating) VALUES (3, '3', '缺考', NULL, '2015-07-01', NULL, '2015-07-01 00:00:00', false, false, NULL,  false);

INSERT INTO code_hb.grade_types (id, begin_on, code, en_name, end_on, name, updated_at, exam_type_id, remark) VALUES (0, '2007-04-30', '0001', NULL, NULL, '最终成绩', '2007-04-30 00:00:00', NULL, NULL);
INSERT INTO code_hb.grade_types (id, begin_on, code, en_name, end_on, name, updated_at, exam_type_id, remark) VALUES (8, '2014-12-11', '0018', NULL, NULL, '缓考总评', '2014-12-11 00:00:00', NULL, NULL);
INSERT INTO code_hb.grade_types (id, begin_on, code, en_name, end_on, name, updated_at, exam_type_id, remark) VALUES (7, '2006-10-29', '0007', NULL, NULL, '期末总评', '2006-10-29 14:56:09', NULL, NULL);
INSERT INTO code_hb.grade_types (id, begin_on, code, en_name, end_on, name, updated_at, exam_type_id, remark) VALUES (9, '2014-12-11', '0019', NULL, NULL, '补考总评', '2014-12-11 00:00:00', NULL, NULL);
INSERT INTO code_hb.grade_types (id, begin_on, code, en_name, end_on, name, updated_at, exam_type_id, remark) VALUES (4, '2006-10-29', '0004', NULL, NULL, '补考成绩', '2006-10-29 14:56:09', 3, NULL);
INSERT INTO code_hb.grade_types (id, begin_on, code, en_name, end_on, name, updated_at, exam_type_id, remark) VALUES (6, '2006-10-29', '0006', NULL, NULL, '缓考成绩', '2011-06-03 13:41:02', 4, NULL);
INSERT INTO code_hb.grade_types (id, begin_on, code, en_name, end_on, name, updated_at, exam_type_id, remark) VALUES (3, '2006-10-29', '0002', 'Component Score', NULL, '平时成绩', '2007-12-20 00:00:00', NULL, NULL);
INSERT INTO code_hb.grade_types (id, begin_on, code, en_name, end_on, name, updated_at, exam_type_id, remark) VALUES (2, '2006-10-29', '0003', 'Final Exam Score', NULL, '期末成绩', '2007-03-09 00:00:00', 1, NULL);
INSERT INTO code_hb.grade_types (id, begin_on, code, en_name, end_on, name, updated_at, exam_type_id, remark) VALUES (1, '2006-10-29', '0008', NULL, '2016-05-03', '期中成绩', '2007-04-30 00:00:00', NULL, NULL);


INSERT INTO edu_clazz.restriction_metas (id, name, remark) VALUES (1, 'GRADE', ' ');
INSERT INTO edu_clazz.restriction_metas (id, name, remark) VALUES (2, 'STDTYPE', ' ');
INSERT INTO edu_clazz.restriction_metas (id, name, remark) VALUES (3, 'GENDER', ' ');
INSERT INTO edu_clazz.restriction_metas (id, name, remark) VALUES (4, 'DEPARTMENT', ' ');
INSERT INTO edu_clazz.restriction_metas (id, name, remark) VALUES (5, 'MAJOR', ' ');
INSERT INTO edu_clazz.restriction_metas (id, name, remark) VALUES (6, 'DIRECTION', ' ');
INSERT INTO edu_clazz.restriction_metas (id, name, remark) VALUES (8, 'EDUCATION', NULL);
INSERT INTO edu_clazz.restriction_metas (id, name, remark) VALUES (7, 'SQUAD', ' ');


INSERT INTO edu_room.user_apps (id, name, activity_url) VALUES (1, 'course', '--');
INSERT INTO edu_room.user_apps (id, name, activity_url) VALUES (2, 'exam', '--');
INSERT INTO edu_room.user_apps (id, name, activity_url) VALUES (3, 'apply', '--');
