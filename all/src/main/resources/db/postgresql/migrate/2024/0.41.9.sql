insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.41.9',now(),'学籍增加用户ID');

alter table base.students add column user_id bigint;
update base.students std set user_id=(select u.id from base.users u where u.code=std.code);
alter table base.students alter column user_id set not null;
alter table base.students add constraint fk_dt1cjx5ve5bdabmuuf3ibrwaq foreign key (user_id) references base.users (id);
create index idx_g4fwvutq09fjdlb4bb0byp7t on base.students (user_id);

alter table edu.course_profiles add semester_id int4;
update edu.course_profiles p set semester_id = (select s.id from base.semesters s where p.updated_at between s.begin_on and s.end_on);
alter table edu.course_profiles alter column semester_id set not null;
