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
