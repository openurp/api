insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.49.1',now(),'能力素质拓展课增加审核人');

alter table prac.ability_credit_applies add auditor_id bigint;
comment on column prac.ability_credit_applies.auditor_id is '通用人员信息ID';
