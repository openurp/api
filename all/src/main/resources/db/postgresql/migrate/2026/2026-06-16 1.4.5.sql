insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'1.4.5',now(),'扩充缓考申请模型');

alter table flow.edu_exam_defer_applies add attachments jsonb default '[]';
alter table flow.edu_exam_defer_applies add column college_approved boolean;
alter table flow.edu_exam_defer_applies add column teacher_reviewed boolean;
alter table flow.edu_exam_defer_applies add column created_at timestamptz;
alter table flow.edu_exam_defer_applies add column opinions jsonb;
alter table flow.edu_exam_defer_applies add column std_sign_url varchar(255);
alter table flow.edu_exam_defer_applies alter reason_id set not null;
alter table std.grad_plan_result_checks drop owed_credits3 cascade;

update flow.edu_exam_defer_applies set created_at =updated_at;
alter table flow.edu_exam_defer_applies alter created_at set not null;
update flow.edu_exam_defer_applies  set reason_id =3 where reason_id is null;
alter table flow.edu_exam_defer_applies alter reason_id set not null;

create table code.attendance_modes (id integer not null, begin_on date not null, code varchar(20) not null, en_name varchar(300), end_on date, name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);
create table code.exam_banned_reasons (id integer not null, begin_on date not null, code varchar(20) not null, en_name varchar(300), end_on date, name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);
create table edu.regular_assess_stats (id bigint not null, absence_exceed_count integer default 0 not null, assignment_exceed_count integer default 0 not null, attendance_mode_id integer not null, clazz_id bigint not null, college_reviewed boolean, disqualified_count integer default 0 not null, updated_at timestamptz default current_timestamp not null);
create table edu.regular_assess_stds (id bigint not null, absence_exceeded boolean default false not null, assignment_exceeded boolean default false not null, clazz_id bigint not null, disqualified boolean default false not null, remark varchar(255), std_id bigint not null);
create table std.delayed_graduates (id bigint not null, acknowledged boolean, acknowledged_at timestamptz, inform_details jsonb not null, informed boolean, season_id bigint not null, std_id bigint not null, std_sign_url varchar(200));

insert into code.attendance_modes(id,code,name,begin_on,updated_at) values(1,'1','全程考勤',current_date,now());
insert into code.attendance_modes(id,code,name,begin_on,updated_at) values(2,'2','抽查考勤',current_date,now());

alter table std.degree_results add wms float8;

alter table code.attendance_modes add constraint pk_64nurmvo67lu5htvfq2m3b128 primary key (id);
alter table code.exam_banned_reasons add constraint pk_g76v7aot0bddd4fsvhs20ejwc primary key (id);
alter table code.attendance_modes add constraint uk_nodotkyudsruswoxpa5ct8v5r unique (code);
alter table code.exam_banned_reasons add constraint uk_9o3d4m39i98k450o59qreotau unique (code);
alter table edu.regular_assess_stats add constraint pk_1297903897vmi7sciuapsk5kq primary key (id);
alter table edu.regular_assess_stds add constraint pk_9cnbfaakbs1he5yw8wbca9b16 primary key (id);
alter table edu.regular_assess_stats add constraint fk_92f56pnmdm38oo3m6ouen503j foreign key (attendance_mode_id) references code.attendance_modes (id);
alter table edu.regular_assess_stats add constraint fk_73hxn1c8o20p1bst8do1uv3ej foreign key (clazz_id) references edu.clazzes (id);
alter table edu.regular_assess_stds add constraint fk_3lk7uinrjqg7tm860946k53ra foreign key (std_id) references base.students (id);
alter table edu.regular_assess_stats add constraint uk_e72495vd64k80w187ca91mbk6 unique (clazz_id);
alter table edu.regular_assess_stds add constraint uk_14x085pv4gbtvsty0c2ol51vi unique (clazz_id,std_id);
alter table edu.regular_assess_stds add constraint fk_4kk71jbud6t831kcr2pj5t3k8 foreign key (clazz_id) references edu.clazzes (id);
alter table std.delayed_graduates add constraint pk_ducjfgf86a8sowv3t2pl91lw4 primary key (id);
alter table std.delayed_graduates add constraint fk_3q5sdbsrb0rf9b4i8itt1nf6q foreign key (std_id) references base.students (id);
alter table std.delayed_graduates add constraint fk_cq7cgor6j4pgrl05qgadebxbn foreign key (season_id) references base.graduate_seasons (id);


comment on table code.attendance_modes is '考勤方式@edu';
comment on column code.attendance_modes.id is '非业务主键:code';
comment on column code.attendance_modes.begin_on is '生效日期';
comment on column code.attendance_modes.code is '代码';
comment on column code.attendance_modes.en_name is '英文名称';
comment on column code.attendance_modes.end_on is '失效日期';
comment on column code.attendance_modes.name is '名称';
comment on column code.attendance_modes.remark is '备注';
comment on column code.attendance_modes.updated_at is '修改时间';
comment on table code.exam_banned_reasons is '取消考试资格原因@edu';
comment on column code.exam_banned_reasons.id is '非业务主键:code';
comment on column code.exam_banned_reasons.begin_on is '生效日期';
comment on column code.exam_banned_reasons.code is '代码';
comment on column code.exam_banned_reasons.en_name is '英文名称';
comment on column code.exam_banned_reasons.end_on is '失效日期';
comment on column code.exam_banned_reasons.name is '名称';
comment on column code.exam_banned_reasons.remark is '备注';
comment on column code.exam_banned_reasons.updated_at is '修改时间';
comment on table edu.regular_assess_stats is '日常考核评估统计@exam';
comment on column edu.regular_assess_stats.id is '非业务主键:datetime';
comment on column edu.regular_assess_stats.absence_exceed_count is '缺勤超标人数';
comment on column edu.regular_assess_stats.assignment_exceed_count is '作业超标人数';
comment on column edu.regular_assess_stats.attendance_mode_id is '考勤方式ID';
comment on column edu.regular_assess_stats.clazz_id is '教学任务ID';
comment on column edu.regular_assess_stats.college_reviewed is '学院是否审核';
comment on column edu.regular_assess_stats.disqualified_count is '取消考试资格人数';
comment on column edu.regular_assess_stats.updated_at is '更新时间';
comment on table edu.regular_assess_stds is '日常考勤/作业学生情况记录@exam';
comment on column edu.regular_assess_stds.id is '非业务主键:datetime';
comment on column edu.regular_assess_stds.absence_exceeded is '缺勤是否超标';
comment on column edu.regular_assess_stds.assignment_exceeded is '作业是否超标';
comment on column edu.regular_assess_stds.clazz_id is '教学任务ID';
comment on column edu.regular_assess_stds.disqualified is '是否取消考试资格';
comment on column edu.regular_assess_stds.remark is '备注';
comment on column edu.regular_assess_stds.std_id is '学生ID';
comment on table flow.edu_exam_defer_applies is '考试缓考申请';
comment on column flow.edu_exam_defer_applies.attachments is '附件';
comment on column flow.edu_exam_defer_applies.college_approved is '院系是否通过';
comment on column flow.edu_exam_defer_applies.created_at is '创建时间';
comment on column flow.edu_exam_defer_applies.opinions is '意见';
comment on column flow.edu_exam_defer_applies.std_sign_url is '个人签名url';
comment on column flow.edu_exam_defer_applies.teacher_reviewed is '教师是否审核';
comment on table std.delayed_graduates is '不能毕业的应届毕业生@graduation';
comment on column std.delayed_graduates.id is '非业务主键:datetime';
comment on column std.delayed_graduates.acknowledged is '是否知晓';
comment on column std.delayed_graduates.acknowledged_at is '知晓时间';
comment on column std.delayed_graduates.inform_details is '告知详情';
comment on column std.delayed_graduates.informed is '是否告知';
comment on column std.delayed_graduates.season_id is '毕业界别ID';
comment on column std.delayed_graduates.std_id is '学生ID';
comment on column std.delayed_graduates.std_sign_url is '个人签名url';
