insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.37.1',now(),'重构自助打印和毕业生文档');

alter table code.std_doc_archive_types rename to std_doc_types;
alter table std.std_doc_archives rename to archive_docs;

alter table cfg.spa_doc_types rename to spa_doc_settings;

insert into code.std_doc_types(id,code,name,begin_on,updated_at)
select id,code,name,current_date,now() from cfg.spa_doc_settings;

create table cfg.std_archive_doc_settings (page_size integer not null, url varchar(200) not null, project_id integer not null, enabled boolean default false not null, id integer not null, orientation integer not null, doc_type_id integer not null);
alter table cfg.std_archive_doc_settings add constraint pk_euy7kdgak01xqyafjoxogbjo2 primary key (id);

alter table cfg.spa_coupons drop constraint if exists fk_kqys2g369af3kdo2wahdghqmg cascade;
alter table cfg.spa_coupons add constraint fk_t9owg4l2wre000cg415nc45s7 foreign key (doc_type_id) references code.std_doc_types (id);
alter table cfg.spa_print_configs drop constraint if exists fk_980vypmo5ogrida8elus811o5 cascade;
alter table cfg.spa_print_configs add constraint fk_fs3lfyubvb38fxv4uj9m2ns26 foreign key (doc_type_id) references cfg.spa_doc_settings (id);
alter table log.spa_download_logs drop constraint if exists fk_avr0jfq4pxl6dk16i89v8u82v cascade;
alter table log.spa_download_logs add constraint fk_fknen94qm9dgfvn814vt3kggk foreign key (doc_type_id) references code.std_doc_types (id);
alter table log.spa_print_logs drop constraint if exists fk_3d8hwkwr4xo3xw1fi17cyv1kv cascade;
alter table log.spa_print_logs add constraint fk_3jju80t0ypjam2l5vkq59okn0 foreign key (doc_type_id) references code.std_doc_types (id);
alter table spa.print_quotas drop constraint if exists fk_81tnr7u2aq88bdoqobwagjjlu cascade;
alter table spa.print_quotas add constraint fk_2xpeitkjoh0dc42e0qiskemg8 foreign key (doc_type_id) references code.std_doc_types (id);

alter table cfg.spa_doc_settings add doc_type_id int4;
alter table cfg.spa_doc_settings add project_id int4;
alter table cfg.spa_doc_settings add printable bool default true;
update cfg.spa_doc_settings set doc_type_id = id;

alter table cfg.spa_print_configs add project_id int4;
alter table cfg.spa_coupons add project_id int4;
update cfg.spa_print_configs set project_id = 5;
update cfg.spa_coupons set project_id = 5;
update cfg.spa_doc_settings set project_id = 5;

insert into cfg.std_archive_doc_settings(id,enabled,orientation,page_size,doc_type_id,url,project_id)
select id,enabled,orientation,page_size,doc_type_id,admin_url,project_id from cfg.spa_doc_settings where admin_url is not null;

comment on table cfg.std_archive_doc_settings is '学生归档文档设置@archive.config';
comment on column cfg.std_archive_doc_settings.id is '非业务主键:auto_increment';
comment on column cfg.std_archive_doc_settings.enabled is '是否启用';
comment on column cfg.std_archive_doc_settings.orientation is '打印方向';
comment on column cfg.std_archive_doc_settings.page_size is '纸张大小';
comment on column cfg.std_archive_doc_settings.url is '文档的访问地址';

alter table cfg.spa_doc_settings drop column admin_url;
alter table cfg.spa_doc_settings drop column code;
alter table cfg.spa_doc_settings drop column name;
