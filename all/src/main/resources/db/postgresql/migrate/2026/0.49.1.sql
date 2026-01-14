insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.49.1',now(),'能力素质拓展课增加审核人');

alter table prac.ability_credit_applies add auditor_id bigint;
