insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.41.6',now(),'支持课外学时');

alter table edu.course_tasks add column extra_hours integer;
comment on column edu.course_tasks.extra_hours is '课外学时';
alter table edu.clazz_plans add column extra_hours integer default 0 not null;
comment on column edu.clazz_plans.extra_hours is '课外学时';

alter table base.courses drop column extra_hours;
