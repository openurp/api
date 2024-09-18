insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.41.7',now(),'增加教案的日期和节次');

alter table edu.lesson_designs add lesson_on date;
alter table edu.lesson_designs add units int4 default 0;
alter table edu.clazz_programs add lesson_count smallint default 0;
alter table edu.clazz_programs add design_count smallint default 0;

CREATE OR REPLACE FUNCTION public.pair_1(int4)
    RETURNS smallint
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
declare rs smallint;
begin
  select $1 >> 16 into rs ;
  return rs;
end;
$BODY$;


CREATE OR REPLACE FUNCTION public.pair_2(int4)
    RETURNS smallint
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
declare rs smallint;
begin
  select $1 & 65535 into rs ;
  return rs;
end;
$BODY$;

alter table edu.syllabus_objectives add constraint uk_qcf83a87e0bfew53ny7n8wyrt unique (syllabus_id,code);
