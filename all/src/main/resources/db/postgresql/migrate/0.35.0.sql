insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.35.0',now(),'重构校外考试,增加考勤信息');

alter table edu.c_certificate_subjects rename to c_certificates;
alter table cfg.edu_cert_signup_settings rename column subject_id to certificate_id;
alter table cfg.edu_cert_signup_exclusives rename column certificate_subject_id to certificate_id;
alter table cfg.edu_cert_signup_exclusives drop constraint if exists pk_t5s02uj9t8c9q7kxg7itbqam5 cascade;
alter table cfg.edu_cert_signup_exclusives add constraint pk_t5s02uj9t8c9q7kxg7itbqam5 primary key (cert_signup_setting_id,certificate_id);

alter table cfg.edu_cert_signup_configs drop column opened;
alter table cfg.edu_cert_signup_configs drop column code;
alter table cfg.edu_cert_signup_configs drop column category_id;
alter table cfg.edu_cert_signup_configs rename column max_subject to max_options;

alter table cfg.edu_cert_exempt_settings rename column subject_id to certificate_id;
alter table edu.cert_exempt_applies rename column subject_id to certificate_id;

alter table edu.cert_signups rename column subject_id to certificate_id;
alter table edu.certificate_grades rename column subject_id to certificate_id;
alter table edu.certificate_grades add column subject varchar(80);
alter table edu.certificate_grades rename column certificate to certificate_no;
alter table edu.certificate_grades alter certificate_no drop not null;
alter table edu.certificate_grades alter score_text type varchar(10);
alter table edu.certificate_grades add column semester_id int4;

update  edu.certificate_grades  g set semester_id=
(select s.id from base.semesters s where g.acquired_on between s.begin_on-20 and s.end_on+20) where semester_id is null;
alter table edu.certificate_grades alter column semester_id set not null;

select * from edu.certificate_grades where semester_id is null limit 2;

alter table edu.certificate_grades add constraint fk_i1gm7pa9f1701d8osdolmo478 foreign key (semester_id) references base.semesters (id);
create index idx_5miknelheaykqp8o0l44o9em2 on edu.certificate_grades (semester_id);
--std leave
alter table edu.std_dayoffs rename to std_leaves;
alter table edu.std_leaves rename column dayoff_type to leave_type;

--room apply reserved
create table cfg.edu_room_apply_reserved_times (id bigint not null, begin_on date not null, building_id integer, campus_id integer not null, end_on date not null, remark varchar(300), school_id integer not null);
alter table cfg.edu_room_apply_reserved_times add constraint pk_3u92nu8ycsxxvd4f6vb7u35e1 primary key (id);
alter table cfg.edu_room_apply_reserved_times add constraint fk_nlyr6vk2utul3vhq75di8n6yo foreign key (school_id) references base.schools (id);
alter table cfg.edu_room_apply_reserved_times add constraint fk_knplsa6y632k1d5wlu36sa6nf foreign key (campus_id) references base.campuses (id);
alter table cfg.edu_room_apply_reserved_times add constraint fk_4tw45x3o0c942156gw454t5l6 foreign key (building_id) references base.buildings (id);

--graduates
alter table base.graduates alter certificate_no drop not null;

--course audit results
alter table edu.course_audit_results alter remark type varchar(150);

--comments
comment on table cfg.edu_room_apply_reserved_times is '借用保留时间@room.config';
comment on column cfg.edu_room_apply_reserved_times.id is '非业务主键:datetime';
comment on column cfg.edu_room_apply_reserved_times.begin_on is '开始日期';
comment on column cfg.edu_room_apply_reserved_times.building_id is '楼房建筑物ID';
comment on column cfg.edu_room_apply_reserved_times.campus_id is '校区信息ID';
comment on column cfg.edu_room_apply_reserved_times.end_on is '结束日期';
comment on column cfg.edu_room_apply_reserved_times.remark is '说明';
comment on column cfg.edu_room_apply_reserved_times.school_id is '学校信息ID';
comment on column cfg.edu_cert_exempt_settings.certificate_id is '校外证书ID';
comment on column cfg.edu_cert_signup_exclusives.certificate_id is '校外证书ID';
comment on column cfg.edu_cert_signup_settings.certificate_id is '校外证书ID';
comment on table edu.c_certificates is '校外证书@extern.code';
comment on column edu.c_certificates.id is '非业务主键:code';
comment on column edu.c_certificates.begin_on is '生效日期';
comment on column edu.c_certificates.category_id is '证书类型ID';
comment on column edu.c_certificates.code is '代码';
comment on column edu.c_certificates.en_name is '英文名称';
comment on column edu.c_certificates.end_on is '失效日期';
comment on column edu.c_certificates.institution_code is '教育机构代码';
comment on column edu.c_certificates.institution_name is '教育机构名称';
comment on column edu.c_certificates.name is '名称';
comment on column edu.c_certificates.remark is '备注';
comment on column edu.c_certificates.updated_at is '修改时间';
comment on table edu.std_leaves is '学生请假@attendance';
comment on column edu.std_leaves.id is '非业务主键:datetime';
comment on column edu.std_leaves.begin_at is '开始时间';
comment on column edu.std_leaves.end_at is '结束时间';
comment on column edu.std_leaves.leave_type is '请假类型';
comment on column edu.std_leaves.reason is '请假事由';
comment on column edu.std_leaves.semester_id is '学年学期ID';
comment on column edu.std_leaves.std_id is '学籍信息实现ID';
comment on table edu.attendances is '学生出勤统计';
comment on column edu.attendances.absent is '缺勤次数';
comment on column edu.attendances.clazz_id is '教学任务ID';
comment on column edu.attendances.late is '迟到早退次数';
comment on column edu.attendances.present is '出勤次数';
comment on column edu.attendances.leave is '请假次数';
comment on table edu.c_certificate_categories is '校外证书类型';
comment on column edu.cert_exempt_applies.certificate_id is '校外证书ID';
comment on column edu.cert_signups.certificate_id is '校外证书ID';
comment on column edu.certificate_grades.certificate_id is '校外证书ID';
comment on column edu.certificate_grades.semester_id is '学年学期ID';
comment on column edu.certificate_grades.subject is '科目';
