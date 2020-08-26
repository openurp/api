CREATE SEQUENCE public.seq_date
    CYCLE
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 99999999999;

CREATE SEQUENCE public.seq_datetime
    CYCLE
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 99999;

CREATE or replace FUNCTION public.date_id()
    RETURNS bigint
    LANGUAGE 'plpgsql'
AS $BODY$
declare nextid bigint;
begin
  select (to_char(current_date,'yyyyMMdd')||lpad(nextval('seq_date')::varchar,11,'0')) ::bigint into nextid;
  return nextid;
end;
$BODY$;

CREATE or replace FUNCTION public.datetime_id()
    RETURNS bigint
    LANGUAGE 'plpgsql'
AS $BODY$
declare nextid bigint;
begin
  select (to_char(current_timestamp,'yyyyMMddHH24MISS') ||lpad(nextval('seq_datetime')::varchar,5,'0')) ::bigint into nextid;
  return nextid;
end;
$BODY$;

CREATE TABLE public.table_sequences
(
    table_name character varying(60)  NOT NULL,
    currval bigint NOT NULL,
    CONSTRAINT table_sequences_pkey PRIMARY KEY (table_name)
);

CREATE OR REPLACE FUNCTION public.next_id(text)
RETURNS bigint
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE
AS $BODY$

declare nextid bigint;
declare rec record;
begin

  update table_sequences a set currval = currval+1 where a.table_name=$1 returning currval into nextid;

  if nextid is null then
    for rec in EXECUTE 'select max(id)+1 as maxid  from ' || $1 loop
      nextid = rec.maxid;
    end loop;
    if nextid is null then
      nextid=1;
    end if;
    insert into table_sequences(table_name,currval) values($1,nextid);
  end if;

  return nextid;
end;

$BODY$;

