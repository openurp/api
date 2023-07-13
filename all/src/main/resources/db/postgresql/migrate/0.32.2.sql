insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.32.2',now(),'更改排课表名');

alter table edu.sessions rename to clazz_activities;
alter table edu.sessions_rooms rename to clazz_activities_rooms;
alter table edu.sessions_teachers rename to clazz_activities_teachers;

alter table edu.clazz_activities_rooms rename session_id to activity_id;
alter table edu.clazz_activities_teachers rename session_id to activity_id;

alter table edu.clazz_activities add begin_unit smallint;
alter table edu.clazz_activities add end_unit smallint;


CREATE OR REPLACE FUNCTION public.minutes(
	integer)
    RETURNS integer
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE PARALLEL UNSAFE
AS $BODY$
 select ($1/100)*60 + $1%100 end;
$BODY$;


update edu.clazz_activities ca set
begin_unit=(select min(cu.indexno) from base.course_units cu where abs(minutes(ca.begin_at )- minutes(cu.begin_at))<=15)
where ca.begin_unit is null;

update edu.clazz_activities ca set
end_unit=(select min(cu.indexno) from base.course_units cu where abs(minutes(ca.end_at) - minutes(cu.end_at))<=15)
where ca.end_unit is null;
