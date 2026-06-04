insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'1.4.5',now(),'扩充缓考申请模型');

alter table flow.edu_exam_defer_applies add attachments jsonb default '[]';
alter table flow.edu_exam_defer_applies add opinions jsonb default '{}';
alter table flow.edu_exam_defer_applies add teacher_reviewed bool;
alter table flow.edu_exam_defer_applies add college_approved bool;
alter table flow.edu_exam_defer_applies add created_at timestamptz;
alter table flow.edu_exam_defer_applies add std_sign_url varchar(200);

update flow.edu_exam_defer_applies set created_at =updated_at;
alter table flow.edu_exam_defer_applies alter created_at set not null;
update flow.edu_exam_defer_applies  set reason_id =3 where reason_id is null;
alter table flow.edu_exam_defer_applies alter reason_id set not null;


create table code.attendance_modes (id integer not null, begin_on date not null, code varchar(20) not null, en_name varchar(300), end_on date, name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);
create table code.exam_banned_reasons (id integer not null, begin_on date not null, code varchar(20) not null, en_name varchar(300), end_on date, name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);
create table edu.regular_assess_stats (id bigint not null, absence_exceed_count integer default 0 not null, assignment_exceed_count integer default 0 not null, attendance_mode_id integer not null, clazz_id bigint not null, college_reviewed boolean, disqualified_count integer default 0 not null, updated_at timestamptz default current_timestamp not null);
create table edu.regular_assess_stds (id bigint not null, absence_exceeded boolean default false not null, assignment_exceeded boolean default false not null, clazz_id bigint not null, disqualified boolean default false not null, remark varchar(255), std_id bigint not null);

insert into code.attendance_modes(id,code,name,begin_on,updated_at) values(1,'1','全程考勤',current_date,now());
insert into code.attendance_modes(id,code,name,begin_on,updated_at) values(2,'2','抽查考勤',current_date,now());
