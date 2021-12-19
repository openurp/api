CREATE OR REPLACE FUNCTION public.bitand(
	bigint,
	bigint)
RETURNS bigint
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE
AS $BODY$
declare rs bigint;
begin
  select $1 & $2 into rs ;
  return rs;
end;
$BODY$;


CREATE OR REPLACE FUNCTION public.instr(
	character varying,
	character varying)
RETURNS integer
    LANGUAGE 'plpgsql'
    COST 100
    IMMUTABLE STRICT
AS $BODY$
DECLARE
    pos integer;
BEGIN
    pos:= instr($1, $2, 1);
    RETURN pos;
END;
$BODY$;


CREATE OR REPLACE FUNCTION public.instr(
	string character varying,
	string_to_search character varying,
	beg_index integer)
RETURNS integer
    LANGUAGE 'plpgsql'
    COST 100
    IMMUTABLE STRICT
AS $BODY$
DECLARE
    pos integer NOT NULL DEFAULT 0;
    temp_str varchar;
    beg integer;
    length integer;
    ss_length integer;
BEGIN
    IF beg_index > 0 THEN
        temp_str := substring(string FROM beg_index);
        pos := position(string_to_search IN temp_str);
        IF pos = 0 THEN
            RETURN 0;
        ELSE
            RETURN pos + beg_index - 1;
        END IF;
    ELSE
        ss_length := char_length(string_to_search);
        length := char_length(string);
        beg := length + beg_index - ss_length+2;
        WHILE beg > 0 LOOP
            temp_str := substring(string FROM beg FOR ss_length);
            pos := position(string_to_search IN temp_str);
            IF pos > 0 THEN
                RETURN beg;
            END IF;
            beg := beg - 1;
        END LOOP;
        RETURN 0;
    END IF;
END;
$BODY$;


CREATE OR REPLACE FUNCTION public.instr(
	string character varying,
	string_to_search character varying,
	beg_index integer,
	occur_index integer)
RETURNS integer
    LANGUAGE 'plpgsql'
    COST 100
    IMMUTABLE STRICT
AS $BODY$
DECLARE
    pos integer NOT NULL DEFAULT 0;
    occur_number integer NOT NULL DEFAULT 0;
    temp_str varchar;
    beg integer;
    i integer;
    length integer;
    ss_length integer;
BEGIN
    IF beg_index > 0 THEN
        beg := beg_index;
        temp_str := substring(string FROM beg_index);
        FOR i IN 1..occur_index LOOP
            pos := position(string_to_search IN temp_str);
            IF i = 1 THEN
                beg := beg+pos - 1;
            ELSE
                beg := beg+pos;
            END IF;
            temp_str := substring(string FROM beg + 1);
        END LOOP;
        IF pos = 0 THEN
            RETURN 0;
        ELSE
            RETURN beg;
        END IF;
    ELSE
        ss_length := char_length(string_to_search);
        length := char_length(string);
        beg := length+beg_index - ss_length+2;
        WHILE beg > 0 LOOP
            temp_str := substring(string FROM beg FOR ss_length);
            pos := position(string_to_search IN temp_str);
            IF pos > 0 THEN
                occur_number := occur_number+1;
                IF occur_number = occur_index THEN
                    RETURN beg;
                END IF;
            END IF;
            beg := beg - 1;
        END LOOP;
        RETURN 0;
    END IF;
END;
$BODY$;

CREATE OR REPLACE FUNCTION public.add_seconds(
  timestamp without time zone,
  integer)
RETURNS timestamp without time zone
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE
AS $BODY$
begin
  return $1 +  $2 * (interval '1' second);
end;
$BODY$;
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
    for rec in EXECUTE 'select (max(id)+1)::bigint as maxid  from ' || $1 loop
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

