insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'1.2.0',now(),'增加毕业补考结构');

alter table edu.final_makeup_takers add semester_id int4;
alter table edu.final_makeup_takers add course_id bigint;
alter table edu.final_makeup_takers alter column makeup_course_id drop not null;

alter table edu.final_makeup_courses add campus_id int4;
alter table edu.final_makeup_courses add begin_at smallint default 0;
alter table edu.final_makeup_courses add end_at smallint default 0;
alter table edu.final_makeup_courses add exam_on date;
alter table edu.final_makeup_courses add room_id bigint;
alter table edu.final_makeup_courses add invigilator1_id bigint;
alter table edu.final_makeup_courses add invigilator2_id bigint;

update edu.final_makeup_takers mt set semester_id=(select mc.semester_id from edu.final_makeup_courses mc
                                                                         where mc.id=mt.makeup_course_id)
where semester_id is null and makeup_course_id is not null;

update edu.final_makeup_takers mt set course_id=(select mc.course_id from edu.final_makeup_courses mc
                                                   where mc.id=mt.makeup_course_id)
where course_id is null and makeup_course_id is not null;

alter table edu.final_makeup_takers add teacher_id bigint;

alter table edu.std_gpas add pending_credits float8 default 0;
alter table edu.std_gpas add taken_credits float8 default 0;
alter table edu.std_gpas rename grade_count to total_count;

alter table edu.std_semester_gpas add pending_credits float8 default 0;
alter table edu.std_semester_gpas add taken_credits float8 default 0;
alter table edu.std_semester_gpas rename grade_count to total_count;

alter table edu.std_year_gpas add pending_credits float8 default 0;
alter table edu.std_year_gpas add taken_credits float8 default 0;
alter table edu.std_year_gpas rename grade_count to total_count;

comment on column edu.final_makeup_takers.semester_id is '学年学期ID';
comment on column edu.final_makeup_takers.teacher_id is '教师信息ID';
comment on column edu.std_gpas.pending_credits is '在修学分';
comment on column edu.std_gpas.taken_credits is '实修学分';
comment on column edu.std_semester_gpas.pending_credits is '在修学分';
comment on column edu.std_semester_gpas.taken_credits is '实修学分';
comment on column edu.std_year_gpas.pending_credits is '在修学分';
comment on column edu.std_year_gpas.taken_credits is '实修学分';
