insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.43.0',now(),'完善英文名');

drop table edu.clazz_materials cascade;
drop table edu.clazz_materials_books cascade;

alter table base.people drop family_name cascade;
alter table base.people drop given_name cascade;
alter table base.people drop middle_name cascade;
alter table base.people rename column formatted_name to name;
alter table base.staffs add column en_name varchar(255);
alter table base.students drop en_name cascade;
alter table base.users drop en_name cascade;
comment on column base.staffs.en_name is '英文名';
