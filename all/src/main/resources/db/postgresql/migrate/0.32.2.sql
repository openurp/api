insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.31.1',now(),'更改排课表名');

alter table edu.sessions rename to clazz_activities;
alter table edu.sessions_rooms rename to clazz_activities_rooms;
alter table edu.sessions_teachers rename to clazz_activities_teachers;

alter table edu.clazz_activities_rooms rename session_id to activity_id;
alter table edu.clazz_activities_teachers rename session_id to activity_id;

