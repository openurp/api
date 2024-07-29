insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.34.1',now(),'重构计划');

create table edu.programs_std_types (program_id bigint not null, std_type_id integer not null);
alter table edu.programs_std_types add constraint pk_9ehq0f2rhqjce1anhhw5bcf4b primary key (program_id,std_type_id);
insert into edu.programs_std_types(program_id,std_type_id) select id,std_type_id from edu.programs;
alter table edu.programs drop column std_type_id;
