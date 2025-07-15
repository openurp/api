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


CREATE OR REPLACE FUNCTION public.age_year(
	date)
RETURNS bigint
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE
AS $BODY$
declare rs int4;
begin
  select extract(year from age($1)) into rs ;
  return rs;
end;
$BODY$;


CREATE OR REPLACE FUNCTION public.plus_days(date,int4)
    RETURNS date
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
declare rs date;
begin
  select $1 + $2 into rs ;
  return rs;
end;
$BODY$;

CREATE OR REPLACE FUNCTION public.plus_days(timestamptz,int4)
    RETURNS timestamptz
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
declare rs timestamptz;
begin
  select $1 + ($2||' day')::interval into rs ;
  return rs;
end;
$BODY$;

CREATE OR REPLACE FUNCTION public.day_diff(date,date)
    RETURNS int4
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
declare rs int4;
begin
  select $2 - $1 into rs ;
  return rs;
end;
$BODY$;
