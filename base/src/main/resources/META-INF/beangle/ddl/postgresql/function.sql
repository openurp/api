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

CREATE OR REPLACE FUNCTION public.hourminute_duration(bigint,bigint)
RETURNS bigint
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE
AS $BODY$
declare rs bigint;
begin
  select abs(hourminute_value($1)-hourminute_value($2)) into rs ;
  return rs;
end;
$BODY$;

CREATE OR REPLACE FUNCTION public.hourminute_value(bigint)
RETURNS bigint
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE
AS $BODY$
declare rs bigint;
begin
  select ($1 - mod($1,100))*6/10 into rs ;
  return rs;
end;
$BODY$;
