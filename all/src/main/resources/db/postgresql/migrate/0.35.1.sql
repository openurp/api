insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.35.1',now(),'完善考勤记录');

alter table edu.std_leaves add days int;
create table edu.std_leave_files (leave_id bigint not null, file_path varchar(255) not null, id bigint not null);
create table edu.std_leave_lessons (semester_id integer not null, std_id bigint not null, id bigint not null, lesson_on date not null, leave_type integer not null, leave_id bigint, clazz_id bigint not null, lesson_time varchar(11) not null);
alter table edu.std_leave_files add constraint pk_dr8s0l3a2j9jhm17kyp5no1n1 primary key (id);
alter table edu.std_leave_lessons add constraint pk_fvos8v6qystkps2poftah2g92 primary key (id);
create index idx_c642h40ncdah4eq1q71d1jat1 on edu.std_leave_files (leave_id);
create index idx_b7n6dqvs1vm7wjyc5mcwern37 on edu.std_leave_lessons (clazz_id);
