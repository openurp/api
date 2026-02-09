insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.49.4',now(),'归档文件增加扩展名');

alter table std.archive_docs add column file_ext varchar(10);
alter table std.archive_docs alter file_ext set not null;
alter table prac.ability_credit_applies add constraint fk_pttvj13irpux8tbfvsnrgh6bb foreign key (auditor_id) references base.users (id);

comment on column std.archive_docs.file_ext is '文件扩展名';
