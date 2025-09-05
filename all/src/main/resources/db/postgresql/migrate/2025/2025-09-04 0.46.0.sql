insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.46.0',now(),'增加双导师支持');

--score_percent to weight
alter table edu.exam_grade_states rename score_percent to weight;
alter table edu.exam_grades rename score_percent to weight;
alter table his.edu_exam_grades rename score_percent to weight;
alter table edu.syllabus_assessments rename score_percent to weight;

update edu.regular_grades
SET tests_json =
 (
  SELECT jsonb_agg(
    -- 对数组中的每个对象进行处理
    jsonb_build_object(
      'name', elem->'name',
      'score', elem->'score',
      'weight', elem->'percent'  -- 将percent的值赋给weight
    )
  )
  FROM jsonb_array_elements(tests_json) AS elem  -- 展开数组
);

update edu.regular_grade_states
SET components_json =
 (
  SELECT jsonb_agg(
    -- 对数组中的每个对象进行处理
    jsonb_build_object(
      'name', elem->'name',
      'weight', elem->'percent'  -- 将percent的值赋给weight
    )
  )
  FROM jsonb_array_elements(components_json) AS elem  -- 展开数组
);

--mini clazz
alter table edu.mini_clazzes add semester_id int4;
alter table edu.mini_clazz_activities add places varchar(200);

--student tutor
create table base.student_tutors (std_id bigint not null, id bigint not null, tutorship integer not null, tutor_id bigint not null);
insert into base.student_tutors(id,std_id,tutor_id,tutorship)
select datetime_id(),std.id,std.tutor_id,1 from base.students std where std.tutor_id is not null;

insert into base.student_tutors(id,std_id,tutor_id,tutorship)
select datetime_id(),std.id,std.advisor_id,2 from base.students std where std.advisor_id is not null;

alter table base.student_tutors add constraint pk_660n5gg3o56ge0nyioh2nu33i primary key (id);
create index idx_csl0e7vpvsg0kscrmohr9iidu on base.student_tutors (std_id);
alter table base.student_tutors add constraint fk_4i4sx6r8cxr1rmk7iy3v3b2ma foreign key (std_id) references base.students (id);
alter table base.student_tutors add constraint fk_9ghft00b7yskfgjjqqsdwathl foreign key (tutor_id) references base.teachers (id);

--misc
alter table edu.lesson_designs alter homework type varchar(2000);

comment on table base.student_tutors is '学生导师@std';
comment on column base.student_tutors.id is '非业务主键:datetime';
comment on column base.student_tutors.std_id is '学生ID';
comment on column base.student_tutors.tutor_id is '教师信息ID';
comment on column base.student_tutors.tutorship is '指导关系';

--danger
alter table base.students drop advisor_id cascade;
alter table base.students drop tutor_id cascade;
