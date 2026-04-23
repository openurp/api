insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'1.1.0',now(),'重构能力素质拓展模型');

alter table prac.ability_credit_applies rename to prac_ability_credit_applies;
alter table prac.prac_ability_credit_applies set schema flow;

alter table prac.ability_credits drop constraint pk_94rnfwyukm6a9ch8lh4b24jtu;
alter table prac.ability_credits rename to ability_credit_stats;
alter table prac.ability_credit_stats drop constraint uk_oe22t4o0pgpw8ig651ulvxs2v;
alter table prac.ability_credit_stats add constraint pk_ab2qc33kuv8ifnav1xyx6r1dd primary key (id);
alter table prac.ability_credit_stats add constraint uk_nmh8kg4juku9egpj2nujcdqb4 unique (std_id);
alter table prac.ability_credit_stats add remark varchar(500);

create table prac.ability_credits (semester_id integer not null, certificate_no varchar(80) not null, subjects varchar(200) not null, credits float4 default 0 not null, acquired_in date not null, std_id bigint not null, id bigint not null, remark varchar(200), certificate_id integer not null, updated_at timestamptz default current_timestamp not null);
alter table prac.ability_credits add constraint pk_94rnfwyukm6a9ch8lh4b24jtu primary key (id);
create index idx_oe22t4o0pgpw8ig651ulvxs2v on prac.ability_credits (std_id);

insert into prac.ability_credits(id,semester_id,certificate_id,certificate_no,subjects,credits,acquired_in,std_id,updated_at)
select id,semester_id,certificate_id,case when certificate_no is null then '--' else certificate_no end,subjects,credits,
       acquired_in,std_id,updated_at from flow.prac_ability_credit_applies a
where credits is not null and status=100
and not exists(select * from prac.ability_credits ac where ac.std_id=a.std_id and ac.certificate_id =a.certificate_id
                                                     and ac.acquired_in=a.acquired_in and ac.subjects = a.subjects);

alter table prac.ability_credits add constraint uk_sc0b8eiegl0n7npyyaet4ja6y unique (std_id,certificate_id,certificate_no,acquired_in,subjects);

-- exam room and activities
alter table edu.exam_activities alter exam_week type smallint;
alter table edu.exam_rooms add column project_id integer;
update edu.exam_rooms er set project_id=(select distinct clz.project_id
                                         from edu.exam_activities_rooms ear ,edu.exam_activities ea,edu.clazzes clz where er.id=ear.exam_room_id
                                         and ear.exam_activity_id=ea.id and ea.clazz_id=clz.id);
alter table edu.exam_rooms alter project_id set not null;
alter table edu.exam_rooms add constraint fk_6a45hjatfkcu09bdf6yn6abhe foreign key (project_id) references base.projects (id);

-- final_makeup
alter table edu.final_makeup_courses add column begin_at smallint;
alter table edu.final_makeup_courses alter begin_at set not null;
alter table edu.final_makeup_courses add column campus_id integer;
alter table edu.final_makeup_courses alter campus_id set not null;
alter table edu.final_makeup_courses add column end_at smallint;
alter table edu.final_makeup_courses alter end_at set not null;
alter table edu.final_makeup_courses add column exam_on date;
alter table edu.final_makeup_courses add column invigilator1_id bigint;
alter table edu.final_makeup_courses add column invigilator2_id bigint;
alter table edu.final_makeup_courses add column room_id bigint;
alter table edu.final_makeup_takers add column course_id bigint;
alter table edu.final_makeup_takers alter makeup_course_id drop not null;
alter table edu.final_makeup_courses add constraint fk_3anyedw3pwhh4ix28jk902u6a foreign key (room_id) references base.classrooms (id);
alter table edu.final_makeup_courses add constraint fk_aggdu5hmu5hschvx2m3r1q815 foreign key (campus_id) references base.campuses (id);
alter table edu.final_makeup_courses add constraint fk_cgt3hnabhv1vhgncyecf11b2v foreign key (invigilator2_id) references base.users (id);
alter table edu.final_makeup_courses add constraint fk_eyo5prcy6jm980h5we98w045m foreign key (invigilator1_id) references base.users (id);
alter table edu.final_makeup_takers add constraint fk_lkd4ymif569369snadf60d53 foreign key (course_id) references base.courses (id);
update edu.final_makeup_takers mt set course_id=(select mc.course_id from edu.final_makeup_courses mc where mc.id=mt.makeup_course_id);
alter table edu.final_makeup_takers alter course_id set not null;

--occupancies
alter table edu.occupancies add column shared boolean default false;
alter table edu.occupancies alter shared set not null;

--comments
comment on column edu.exam_rooms.project_id is '项目ID';
comment on column edu.final_makeup_courses.begin_at is '考试开始时间';
comment on column edu.final_makeup_courses.campus_id is '校区信息ID';
comment on column edu.final_makeup_courses.end_at is '考试结束时间';
comment on column edu.final_makeup_courses.exam_on is '考试日期';
comment on column edu.final_makeup_courses.invigilator1_id is '监考人1ID';
comment on column edu.final_makeup_courses.invigilator2_id is '监考人2ID';
comment on column edu.final_makeup_courses.room_id is '考试教室ID';
comment on column edu.final_makeup_takers.course_id is '课程基本信息ID';
comment on column edu.occupancies.shared is '是否可以共享占用';
comment on table flow.prac_ability_credit_applies is '学生能力证书学分申请@ability.flow';
comment on column flow.prac_ability_credit_applies.id is '非业务主键:datetime';
comment on column flow.prac_ability_credit_applies.acquired_in is '获得年月';
comment on column flow.prac_ability_credit_applies.attachment_path is '成绩单附件路径';
comment on column flow.prac_ability_credit_applies.audit_depart_id is '审核部门ID';
comment on column flow.prac_ability_credit_applies.audit_opinion is '审核意见';
comment on column flow.prac_ability_credit_applies.auditor_id is '通用人员信息ID';
comment on column flow.prac_ability_credit_applies.certificate_id is '校外证书ID';
comment on column flow.prac_ability_credit_applies.certificate_no is '证书编号';
comment on column flow.prac_ability_credit_applies.credits is '认定的学分数';
comment on column flow.prac_ability_credit_applies.finished is '是否完成证书所有课程';
comment on column flow.prac_ability_credit_applies.reasons is '申请理由';
comment on column flow.prac_ability_credit_applies.semester_id is '学年学期ID';
comment on column flow.prac_ability_credit_applies.status is '申请状态';
comment on column flow.prac_ability_credit_applies.std_id is '学生ID';
comment on column flow.prac_ability_credit_applies.subject_cnt is '通过门数';
comment on column flow.prac_ability_credit_applies.subjects is '证书内课程';
comment on column flow.prac_ability_credit_applies.updated_at is '更新时间';
comment on table prac.ability_credit_stats is '学生能力素质能力认定学分汇总@ability';
comment on column prac.ability_credit_stats.id is '非业务主键:datetime';
comment on column prac.ability_credit_stats.course_grade_id is '认定的成绩ID';
comment on column prac.ability_credit_stats.credits is '学分';
comment on column prac.ability_credit_stats.remark is '备注';
comment on column prac.ability_credit_stats.std_id is '学生ID';
comment on column prac.ability_credit_stats.updated_at is '更新时间';
comment on table prac.ability_credits is '学生能力证书学分';
comment on column prac.ability_credits.acquired_in is '获得年月';
comment on column prac.ability_credits.certificate_id is '校外证书ID';
comment on column prac.ability_credits.certificate_no is '证书编号';
comment on column prac.ability_credits.remark is '备注';
comment on column prac.ability_credits.semester_id is '学年学期ID';
comment on column prac.ability_credits.subjects is '证书内课程';
