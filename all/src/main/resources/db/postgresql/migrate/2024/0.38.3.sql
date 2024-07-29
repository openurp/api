insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.38.3',now(),'优化任务的教材信息');

alter table edu.clazz_materials rename to clazz_docs;
create table edu.clazz_materials (submit_at timestamptz, passed boolean, adoption integer not null, id bigint not null, remark varchar(200), bibliography varchar(255), clazz_id bigint not null, materials varchar(255), ordered boolean default false not null);
create table edu.clazz_materials_books (clazz_material_id bigint not null, textbook_id bigint not null);
alter table edu.clazz_materials add constraint pk_nrnf4gf44n88mvd5efhg9ror2 primary key (id);
alter table edu.clazz_materials add constraint uk_ftsvqtl0wf5lx885wwiw514hp unique (clazz_id);
alter table edu.clazz_materials_books add constraint pk_19ptw67po3yu7qi332nbrrh0h primary key (clazz_material_id,textbook_id);

alter table base.major_disciplines add discipline_name varchar(100);
update base.major_disciplines md set discipline_name=(select m.name from base.majors m where m.id=md.major_id);

