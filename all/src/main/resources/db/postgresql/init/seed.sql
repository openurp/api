INSERT INTO code.academic_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (70, '70', '初级中学', NULL, '2017-03-04 00:00:00', NULL, '2015-04-21 09:57:33', NULL);
INSERT INTO code.academic_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (11, '11', '硕士研究生', NULL, '2017-03-04 00:00:00', NULL, '2014-10-14 15:09:27.238', NULL);
INSERT INTO code.academic_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (10, '10', '博士研究生', NULL, '2017-03-04 00:00:00', NULL, '2014-10-14 15:09:36.965', NULL);
INSERT INTO code.academic_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (40, '40', '中等职业', NULL, '2017-03-04 00:00:00', NULL, '2015-04-21 09:56:59', NULL);
INSERT INTO code.academic_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (80, '80', '小学', NULL, '2017-03-04 00:00:00', NULL, '2015-04-21 09:57:46', NULL);
INSERT INTO code.academic_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (30, '30', '大学专科', NULL, '2017-03-04 00:00:00', NULL, '2014-10-14 15:09:13.977', NULL);
INSERT INTO code.academic_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (60, '60', '普通高中', NULL, '2017-03-04 00:00:00', NULL, '2015-04-21 09:57:22', NULL);
INSERT INTO code.academic_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (20, '20', '大学本科', NULL, '2017-03-04 00:00:00', NULL, '2014-10-14 15:09:20.921', NULL);

INSERT INTO code.countries(id, code, name, en_name,short_name,begin_on,  updated_at,alpha2_code,alpha3_code) VALUES (156, '156','中华人民共和国', 'China','中国',current_date,now(),'CN','CHN');

INSERT INTO code.course_take_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '01', '正常', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.course_take_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (3, '02', '重修', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.course_take_types (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (5, '03', '免修', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);

INSERT INTO code.education_levels (id, code, name, en_name, begin_on, end_on, updated_at, from_level_id, to_level_id, remark) VALUES (1, '23', '高起本', NULL, '2005-07-24', NULL, '2019-10-22 23:37:43.727598', 60, 20, NULL);
INSERT INTO code.education_levels (id, code, name, en_name, begin_on, end_on, updated_at, from_level_id, to_level_id, remark) VALUES (3, '22', '专升本', NULL, '2005-07-24', NULL, '2019-10-22 23:37:56.352206', 30, 20, NULL);
INSERT INTO code.education_levels (id, code, name, en_name, begin_on, end_on, updated_at, from_level_id, to_level_id, remark) VALUES (2, '21', '高起专', NULL, '2005-07-24', NULL, '2019-10-22 23:38:10.792471', 60, 30, NULL);
INSERT INTO code.education_levels (id, code, name, en_name, begin_on, end_on, updated_at, from_level_id, to_level_id, remark) VALUES (4, '4', '高中', NULL, '2016-01-20', NULL, '2016-01-20 00:00:00', 70, 60, NULL);
INSERT INTO code.education_levels (id, code, name, en_name, begin_on, end_on, updated_at, from_level_id, to_level_id, remark) VALUES (5, '5', '大专', NULL, '2016-01-22', NULL, '2016-01-22 00:00:00', 60, 30, NULL);

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

INSERT INTO edu.room_occupy_apps (id, name, activity_url) VALUES (1, 'course', '--');
INSERT INTO edu.room_occupy_apps (id, name, activity_url) VALUES (2, 'exam', '--');
INSERT INTO edu.room_occupy_apps (id, name, activity_url) VALUES (3, 'apply', '--');

-----------如下代码需要插入后修改---------
insert into code.institutions(id,code,name,begin_on,updated_at)
values(00001,'00001','URP学院',current_date -10,now());

insert into base.schools(id,name,institution_id,logo_url,code,begin_on,short_name)
values(00001,'URP学院',11833,'http://someip/default/images/logo.png',11833,current_date -10,'URP');

insert into base.users(id,code,name,school_id,email,department_id,gender_id,begin_on,end_on,updated_at,category_id)
values(next_id('base.users'),'root','root',00001,'admin@school.com',1,1,current_date-365,current_date+10*365,now(),3);

