insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.44.2',now(),'增加小课教学');

--course directors
alter table edu.course_tasks add idx smallint default 1 not null;
alter table edu.course_tasks drop constraint uk_3frid92ox3grog2rfffm12lci cascade;
alter table edu.course_tasks add constraint uk_788vbx6k6ka93qc211s8sg70v unique (semester_id,course_id,idx);

--mini clazz
create table edu.mini_clazz_activities (id bigint not null, advisor1_id bigint, advisor2_id bigint, begin_at smallint not null, begin_unit smallint default 0 not null, end_at smallint not null, end_unit smallint default 0 not null, mini_clazz_id bigint not null, start_on date not null, teacher_id bigint, weekstate bigint not null);
create table edu.mini_clazzes (id bigint not null, course_id bigint not null, crn varchar(255) not null, project_id integer not null, remark varchar(255), teacher_id bigint, updated_at timestamptz default current_timestamp not null);
create table edu.mini_clazzes_stds (mini_clazz_id bigint not null, student_id bigint not null);

alter table edu.mini_clazz_activities add constraint pk_6km9ontbm925da7x6y1d0jxh6 primary key (id);
alter table edu.mini_clazzes add constraint pk_6b9ghfkpxs4254cqx2ta74dmx primary key (id);
alter table edu.mini_clazzes_stds add constraint pk_gf6ecj0anc2bodb5uq0f2jtx0 primary key (mini_clazz_id,student_id);
alter table edu.mini_clazz_activities add constraint fk_qgqcs7mn2kbspko6hinps92ts foreign key (teacher_id) references base.teachers (id);
alter table edu.mini_clazz_activities add constraint fk_ocss8b1rk38m8rupwrvyysv1g foreign key (advisor2_id) references base.users (id);
alter table edu.mini_clazz_activities add constraint fk_lyrbqkvrq062g0qkwffp8tnrv foreign key (mini_clazz_id) references edu.mini_clazzes (id);
alter table edu.mini_clazz_activities add constraint fk_2ud09ne4h8n8j2ds3hhajp5a9 foreign key (advisor1_id) references base.users (id);
alter table edu.mini_clazzes add constraint fk_g54qg0i9hm1nbmg2m6a7n839i foreign key (teacher_id) references base.teachers (id);
alter table edu.mini_clazzes add constraint fk_hnrgbw7etthlww63oc1ryxuhn foreign key (course_id) references base.courses (id);
alter table edu.mini_clazzes add constraint fk_fmhlll73mqj7xkjrk87vd263t foreign key (project_id) references base.projects (id);
alter table edu.mini_clazzes_stds add constraint fk_daujuml3i7o557awedjldp7wv foreign key (mini_clazz_id) references edu.mini_clazzes (id);
alter table edu.mini_clazzes_stds add constraint fk_j7dorxuc3wd616p5c69d12lsq foreign key (student_id) references base.students (id);
create index idx_q821i08k5uk8aef9ku718jnhw on edu.mini_clazz_activities (mini_clazz_id);
create index idx_jdfyocb0rx7cybidywsoje2ge on edu.mini_clazzes_stds (mini_clazz_id);
comment on table edu.mini_clazz_activities is '小课上课安排@clazz';
comment on column edu.mini_clazz_activities.id is '非业务主键:datetime';
comment on column edu.mini_clazz_activities.advisor1_id is '辅导老师1ID';
comment on column edu.mini_clazz_activities.advisor2_id is '辅导老师2ID';
comment on column edu.mini_clazz_activities.begin_at is '开始时间';
comment on column edu.mini_clazz_activities.begin_unit is '开始节次';
comment on column edu.mini_clazz_activities.end_at is '结束时间';
comment on column edu.mini_clazz_activities.end_unit is '结束节次';
comment on column edu.mini_clazz_activities.mini_clazz_id is '小课ID';
comment on column edu.mini_clazz_activities.start_on is '开始日期';
comment on column edu.mini_clazz_activities.teacher_id is '授课教师ID';
comment on column edu.mini_clazz_activities.weekstate is '周状态';
comment on table edu.mini_clazzes is '小课@clazz';
comment on column edu.mini_clazzes.id is '非业务主键:datetime';
comment on column edu.mini_clazzes.course_id is '课程基本信息ID';
comment on column edu.mini_clazzes.crn is '课程序号';
comment on column edu.mini_clazzes.project_id is '项目ID';
comment on column edu.mini_clazzes.remark is '备注';
comment on column edu.mini_clazzes.teacher_id is '授课教师ID';
comment on column edu.mini_clazzes.updated_at is '更新时间';
comment on table edu.mini_clazzes_stds is '小课学生名单@clazz';
comment on column edu.mini_clazzes_stds.mini_clazz_id is '小课ID';
comment on column edu.mini_clazzes_stds.student_id is '学生ID';
comment on column edu.course_tasks.idx is '顺序号';
