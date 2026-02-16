insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.49.6',now(),'归档下载日志增加手机号');

alter table log.std_archive_downloads add column mobile varchar(255);
alter table log.std_archive_downloads alter mobile set not null;
comment on column log.std_archive_downloads.mobile is '下载手机号';
