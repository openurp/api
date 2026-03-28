insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'1.1.0',now(),'重构能力素质拓展模型');

alter table prac.ability_credit_applies rename to prac_ability_credit_applies;
alter table prac.prac_ability_credit_applies set schema flow;

alter table prac.ability_credits drop constraint pk_94rnfwyukm6a9ch8lh4b24jtu;
alter table prac.ability_credits rename to ability_credit_stats;
alter table prac.ability_credit_stats drop constraint uk_oe22t4o0pgpw8ig651ulvxs2v;
alter table prac.ability_credit_stats add constraint pk_ab2qc33kuv8ifnav1xyx6r1dd primary key (id);
alter table prac.ability_credit_stats add constraint uk_nmh8kg4juku9egpj2nujcdqb4 unique (std_id);
alter table prac.ability_credit_stats add remark varchar(500);

create table prac.ability_credits (semester_id integer not null, certificate_no varchar(80) not null, subjects varchar(200) not null, credits float4 default 0 not null, acquired_in date not null, std_id bigint not null, id bigint not null, remark varchar(200), certificate_id integer not null, updated_at timestamptz default current_timestamp not null);
alter table prac.ability_credits add constraint pk_94rnfwyukm6a9ch8lh4b24jtu primary key (id);
create index idx_oe22t4o0pgpw8ig651ulvxs2v on prac.ability_credits (std_id);

insert into prac.ability_credits(id,semester_id,certificate_id,certificate_no,subjects,credits,acquired_in,std_id,updated_at)
select id,semester_id,certificate_id,case when certificate_no is null then '--' else certificate_no end,subjects,credits,
       acquired_in,std_id,updated_at from flow.prac_ability_credit_applies
where credits is not null and status=100;

alter table prac.ability_credits add constraint uk_sc0b8eiegl0n7npyyaet4ja6y unique (std_id,certificate_id,certificate_no,acquired_in,subjects);
