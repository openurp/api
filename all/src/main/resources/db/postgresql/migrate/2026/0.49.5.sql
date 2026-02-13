insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.49.5',now(),'增加归档下载日志');

create table log.std_archive_downloads (id bigint not null, doc_type_id integer not null, ip varchar(255) not null, updated_at timestamptz default current_timestamp not null, user_id bigint not null);
alter table std.archive_docs add column signed_at timestamptz;
alter table std.archive_docs add column signed_file_path varchar(255);
alter table log.std_archive_downloads add constraint pk_f3ws4of2immxg2ugrlnjbrq9j primary key (id);
alter table log.std_archive_downloads add constraint fk_pjbi78smvfbmim4mjr257o8t9 foreign key (doc_type_id) references code.std_doc_types (id);
alter table log.std_archive_downloads add constraint fk_fleorml2xhh7w493rvwp0ptoj foreign key (user_id) references base.users (id);
comment on table log.std_archive_downloads is '学生归档文件下载日志@archive.log';
comment on column log.std_archive_downloads.id is '非业务主键:datetime';
comment on column log.std_archive_downloads.doc_type_id is '学生文档类型ID';
comment on column log.std_archive_downloads.ip is '下载IP';
comment on column log.std_archive_downloads.updated_at is '更新时间';
comment on column log.std_archive_downloads.user_id is '通用人员信息ID';
comment on column std.archive_docs.signed_at is '签名时间';
comment on column std.archive_docs.signed_file_path is '签名过的文件路径';
