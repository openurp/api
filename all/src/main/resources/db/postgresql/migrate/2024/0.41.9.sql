insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.41.9',now(),'学籍增加用户ID');

alter table base.students add column user_id bigint;
update base.students std set user_id=(select u.id from base.users u where u.code=std.code);
alter table base.students alter column user_id set not null;
alter table base.students add constraint fk_dt1cjx5ve5bdabmuuf3ibrwaq foreign key (user_id) references base.users (id);

alter table edu.course_profiles set schema base;
alter table base.course_profiles add semester_id int4;
update base.course_profiles p set semester_id = (select max(s.id) from base.semesters s where p.updated_at between s.begin_on and s.end_on) where semester_id is null;
update base.course_profiles p set semester_id = (select max(s.id) from base.semesters s where p.updated_at between s.begin_on and s.end_on+'50 day'::interval) where semester_id is null;

alter table edu.course_profiles alter column semester_id set not null;

create index idx_eaxjc9jjy2ylssnue0eqcxrjp on base.students (person_id);
create index idx_g4fwvutq09fjdlb4bb0byp7t on base.students (user_id);

comment on column base.course_profiles.semester_id is '学年学期ID';
comment on column base.students.user_id is '账户信息ID';
