insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.44.1',now(),'重构实验教学');

--course directors
alter table base.course_directors add begin_on date;
alter table base.course_directors add end_on date;
update base.course_directors cd set begin_on = (select min(s.begin_on) from base.semesters s,edu.course_tasks ct
where ct.semester_id=s.id and cd.course_id=ct.course_id and cd.director_id=ct.director_id);
delete from base.course_directors where director_id is null or begin_on is null;
alter table base.course_directors alter begin_on set not null;
alter table base.course_directors alter director_id set not null;

--laboratories
alter table base.laboratories drop building_id cascade;
alter table base.laboratories drop campus_id cascade;
alter table base.laboratories drop room_no cascade;
alter table base.laboratories add column room_id bigint;
alter table base.laboratories add constraint fk_ngw65r8tuth5tpr6ox5r0m3kk foreign key (room_id) references base.classrooms (id);

--experiments
drop table lab.experiment_activities cascade;
drop table lab.experiment_activities_labs cascade;
drop table lab.experiments cascade;

create table base.experiments (id bigint not null, begin_on date not null, category_id integer, code varchar(20) not null, course_id bigint not null, credit_hours float4 default 0 not null, discipline_id integer, en_name varchar(400), end_on date, experiment_type_id integer not null, group_std_count integer default 0 not null, name varchar(300) not null, online boolean default false not null, remark varchar(255), updated_at timestamptz default current_timestamp not null);

alter table base.experiments add constraint pk_9bocam72pq84nbf7q7r1jn5py primary key (id);
alter table base.experiments add constraint fk_syicnxr0u0po3ou1cf9l1n1so foreign key (discipline_id) references code.level1_disciplines (id);
alter table base.experiments add constraint fk_bsj5luxworu1ale3fq4bv5m7h foreign key (experiment_type_id) references code.experiment_types (id);
alter table base.experiments add constraint fk_cnbo41vj9grh5xljkjie3jiw5 foreign key (course_id) references base.courses (id);
alter table base.experiments add constraint fk_qdjbtewk6kkhmrrlcxkrr6h6 foreign key (category_id) references code.experiment_categories (id);

create table public.tmp_experiments as
select s.course_id,se.name,se.experiment_type_id,se.online,se.idx,se.credit_hours,min(sm.begin_on) begin_on,max(s.updated_at) updated_at from edu.syllabus_experiments se,edu.syllabuses s,base.semesters sm
where s.id=se.syllabus_id and s.semester_id=sm.id
group by s.course_id,se.name,se.experiment_type_id,se.online,se.idx,se.credit_hours;

insert into base.experiments(id,name,experiment_type_id,online,credit_hours,course_id,begin_on,updated_at,group_std_count,code)
select datetime_id(),name,experiment_type_id,online,credit_hours,course_id,begin_on,updated_at,0,idx::varchar from public.tmp_experiments;

drop table public.tmp_experiments;

delete from base.experiments a
where a.credit_hours =0 and
exists(select * from base.experiments b where a.name=b.name and a.course_id=b.course_id and (a.credit_hours <> b.credit_hours or a.experiment_type_id<>b.experiment_type_id
or a.online <> b.online));

delete from base.experiments a
where  exists(select * from base.experiments b where a.name=b.name and a.course_id=b.course_id and a.credit_hours = b.credit_hours
and a.experiment_type_id=b.experiment_type_id and a.online = b.online and a.id <> b.id and a.code::int4>b.code::int4);

select a.course_id,a.name,a.credit_hours,a.experiment_type_id,a.online,a.begin_on from base.experiments a
where exists(select * from base.experiments b where a.name=b.name and a.course_id=b.course_id and (a.credit_hours <> b.credit_hours or a.experiment_type_id<>b.experiment_type_id
or a.online <> b.online)) order by a.course_id,a.name;

create table public.tmp_expr_num as
SELECT
    ROW_NUMBER() OVER w1 AS rn,
    e.id,e.course_id,e.name,e.code
FROM
    base.experiments e
WINDOW
w1 AS (PARTITION BY course_id ORDER BY code ASC);

update base.experiments e set code=(select case when n.rn<10 then '0'||n.rn::varchar else n.rn::varchar end
from public.tmp_expr_num n where n.id=e.id);

update  base.experiments set code= case when code::int <9 then '0'||(code::int4+1)::varchar else (code::int4+1)::varchar end;
alter table base.experiments add constraint uk_4t6s3rhpyuivur62lwcyy7ul5 unique (course_id,code);
drop table public.tmp_expr_num;

--syllabus experiment
alter table edu.syllabus_experiments add column experiment_id bigint;

update edu.syllabus_experiments se set experiment_id=(select e.id from base.experiments e,edu.syllabuses s
where e.course_id=s.course_id and se.syllabus_id=s.id and se.name=e.name and se.credit_hours=e.credit_hours
and se.experiment_type_id=e.experiment_type_id and se.online=e.online)
where experiment_id is null and (select count(*) from base.experiments e,edu.syllabuses s
      where e.course_id=s.course_id and se.syllabus_id=s.id and se.name=e.name and se.credit_hours=e.credit_hours
      and se.experiment_type_id=e.experiment_type_id and se.online=e.online)=1;

--ignore credit hours/experiment_type/online
update edu.syllabus_experiments se set experiment_id=(select e.id from base.experiments e,edu.syllabuses s
where e.course_id=s.course_id and se.syllabus_id=s.id and se.name=e.name)
where experiment_id is null and (select count(*) from base.experiments e,edu.syllabuses s
      where e.course_id=s.course_id and se.syllabus_id=s.id and se.name=e.name)=1;

alter table edu.syllabus_experiments alter experiment_id set not null;
alter table edu.syllabus_experiments alter credit_hours drop not null;
alter table edu.syllabus_experiments alter experiment_type_id  drop not null;
alter table edu.syllabus_experiments alter name  drop not null;
alter table edu.syllabus_experiments alter online  drop not null;
alter table edu.syllabus_experiments drop constraint if exists fk_grw66d0pyjiqxydbhaicjkg0y cascade;
alter table edu.syllabus_experiments add constraint fk_8d9d58buigjgts7ix2fjdmaqe foreign key (experiment_id) references base.experiments (id);

--course tasks
alter table edu.course_tasks drop renew_syllabus cascade;

--lab experiment
create table lab.lab_experiments (id bigint not null, experiment_id bigint not null, idx integer default 0 not null, task_id bigint not null);
create table lab.lab_tasks (id bigint not null, clazz_count integer default 0 not null, course_id bigint not null, department_id integer not null, director_id bigint, exp_count integer default 0 not null, rank_id integer not null, required boolean, semester_id integer not null, std_count integer default 0 not null);
create table lab.lab_tasks_labs (lab_task_id bigint not null, laboratory_id bigint not null);
alter table lab.lab_experiments add constraint pk_cluasve2mn5lsm6jvfxqs77v8 primary key (id);
alter table lab.lab_tasks add constraint pk_pobmn5lqgf0ngijre9cvc3id9 primary key (id);
alter table lab.lab_tasks_labs add constraint pk_m055on3ewsrwn869g19w0fixo primary key (lab_task_id,laboratory_id);

alter table lab.lab_experiments add constraint fk_i0vuapwcokj3be9h2p3lli057 foreign key (task_id) references lab.lab_tasks (id);
alter table lab.lab_experiments add constraint fk_llngpoxmckvmntm8dp5jo2h9n foreign key (experiment_id) references base.experiments (id);
alter table lab.lab_tasks add constraint fk_rtm2nghx8jx980pvos7l3qoic foreign key (director_id) references base.teachers (id);
alter table lab.lab_tasks add constraint fk_o2mk6oai9b2e3naujyiik6o2w foreign key (course_id) references base.courses (id);
alter table lab.lab_tasks add constraint fk_nlctcagnfkpkgvms7e4y3j6vf foreign key (rank_id) references code.course_ranks (id);
alter table lab.lab_tasks add constraint fk_fo8who9que0f2ihy9o155emae foreign key (semester_id) references base.semesters (id);
alter table lab.lab_tasks add constraint fk_ap3i990156cnq9891r4nxked5 foreign key (department_id) references base.departments (id);
alter table lab.lab_tasks_labs add constraint fk_ngp72ymfqmd11qfh2wmqk49k1 foreign key (lab_task_id) references lab.lab_tasks (id);
alter table lab.lab_tasks_labs add constraint fk_7c3oy6rnq7vh9wkpt23yhh084 foreign key (laboratory_id) references base.laboratories (id);

alter table lab.lab_experiments add constraint uk_eopmlqm6bmh71p4qw6wdxrfoo unique (task_id,idx);
create index idx_5pdh9qryix36eaw5nrncfwlji on lab.lab_experiments (task_id);
create index idx_375h8err64n0ull44fwcu8pw9 on lab.lab_tasks_labs (lab_task_id);

--comments

comment on table base.experiments is '实验项目@edu';
comment on column base.experiments.id is '非业务主键:datetime';
comment on column base.experiments.begin_on is '生效日期';
comment on column base.experiments.category_id is '实验类别ID';
comment on column base.experiments.code is '代码';
comment on column base.experiments.course_id is '课程基本信息ID';
comment on column base.experiments.credit_hours is '学时';
comment on column base.experiments.discipline_id is '一级学科ID';
comment on column base.experiments.en_name is '英文名';
comment on column base.experiments.end_on is '失效日期';
comment on column base.experiments.experiment_type_id is '实验类型ID';
comment on column base.experiments.group_std_count is '每组人数';
comment on column base.experiments.name is '名称';
comment on column base.experiments.online is '是否在线实验';
comment on column base.experiments.remark is '备注';
comment on column base.experiments.updated_at is '更新时间';
comment on column base.course_directors.begin_on is '生效日期';
comment on column base.course_directors.end_on is '失效日期';
comment on column base.laboratories.room_id is '教室ID';
comment on column edu.syllabus_experiments.experiment_id is '实验项目ID';
comment on table lab.lab_experiments is '每学期实验任务@lab';
comment on column lab.lab_experiments.id is '非业务主键:datetime';
comment on column lab.lab_experiments.experiment_id is '实验项目ID';
comment on column lab.lab_experiments.idx is '顺序号';
comment on column lab.lab_experiments.task_id is '实验任务ID';
comment on table lab.lab_tasks is '实验任务@lab';
comment on column lab.lab_tasks.id is '非业务主键:datetime';
comment on column lab.lab_tasks.clazz_count is '班级数';
comment on column lab.lab_tasks.course_id is '课程基本信息ID';
comment on column lab.lab_tasks.department_id is '部门组织机构信息ID';
comment on column lab.lab_tasks.director_id is '教师信息ID';
comment on column lab.lab_tasks.exp_count is '实验数';
comment on column lab.lab_tasks.rank_id is '课程属性ID';
comment on column lab.lab_tasks.required is '是否要求填写实验';
comment on column lab.lab_tasks.semester_id is '学年学期ID';
comment on column lab.lab_tasks.std_count is '实验人数';
comment on table lab.lab_tasks_labs is '实验项目占用实验室@lab';
comment on column lab.lab_tasks_labs.lab_task_id is '实验任务ID';
comment on column lab.lab_tasks_labs.laboratory_id is '实验室ID';

