insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.49.10',now(),'增强小课名单模型');

alter table edu.mini_clazzes_stds rename to mini_clazz_takers;
alter table edu.mini_clazz_takers add id bigint;
alter table edu.mini_clazz_takers add course_id bigint;
alter table edu.mini_clazz_takers add semester_id integer;
alter table edu.mini_clazz_takers rename student_id to std_id;
alter table edu.mini_clazz_takers add score_text varchar(20);
alter table edu.mini_clazz_takers add updated_at timestamptz;

update edu.mini_clazz_takers set id = datetime_id() where id is null;
ALTER TABLE IF EXISTS edu.mini_clazz_takers DROP CONSTRAINT IF EXISTS pk_gf6ecj0anc2bodb5uq0f2jtx0;
alter table edu.mini_clazz_takers add constraint pk_7l9n185stj7vx6ybr57qjj6dw primary key (id);

update edu.mini_clazz_takers a set(course_id,semester_id)=(select clz.course_id,clz.semester_id from edu.mini_clazzes clz where clz.id=a.mini_clazz_id)
where a.course_id is null;
alter table edu.mini_clazz_takers alter course_id set not null;
alter table edu.mini_clazz_takers alter semester_id set not null;

update edu.mini_clazz_takers ct set updated_at =(select clz.updated_at from edu.mini_clazzes clz where clz.id=ct.mini_clazz_id)
where updated_at is null;
alter table edu.mini_clazz_takers alter updated_at set not null;

alter table edu.mini_clazz_takers add constraint fk_px9bk9ar5gvebvew89pgdv1nd foreign key (course_id) references base.courses (id);
alter table edu.mini_clazz_takers add constraint fk_4uu492i2h0qo3v18onvmcne0a foreign key (semester_id) references base.semesters (id);
alter table edu.mini_clazz_takers add constraint uk_4pvs6v9h4cx4lrh0y3x25b0rt unique (std_id,semester_id,course_id);

alter table edu.mini_clazzes add column grade_entered boolean default false;
alter table edu.mini_clazzes alter grade_entered set not null;


comment on table edu.mini_clazz_takers is '小课学生名单@miniclazz';
comment on column edu.mini_clazz_takers.id is '非业务主键:datetime';
comment on column edu.mini_clazz_takers.course_id is '课程基本信息ID';
comment on column edu.mini_clazz_takers.mini_clazz_id is '专业小课ID';
comment on column edu.mini_clazz_takers.score_text is '成绩';
comment on column edu.mini_clazz_takers.semester_id is '学年学期ID';
comment on column edu.mini_clazz_takers.std_id is '学生ID';
comment on column edu.mini_clazz_takers.updated_at is '更新时间';
comment on table edu.mini_clazzes is '专业小课';
comment on column edu.mini_clazzes.grade_entered is '成绩是否登记';
