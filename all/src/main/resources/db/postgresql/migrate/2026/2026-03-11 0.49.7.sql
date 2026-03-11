insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.49.7',now(),'增加业务设置模型');

create table cfg.business_settings (id bigint not null, business varchar(255) not null, profile_id varchar(255) not null, project_id integer not null, settings jsonb not null, updated_at timestamptz default current_timestamp not null);
alter table cfg.business_settings add constraint pk_72fx0qbym3hwpnuubjt6id68f primary key (id);
alter table cfg.business_settings add constraint fk_3m0pwxtvp16jv1n47mhgtrsxs foreign key (project_id) references base.projects (id);
comment on table cfg.business_settings is '业务设置@config';
comment on column cfg.business_settings.id is '非业务主键:datetime';
comment on column cfg.business_settings.business is '业务类型';
comment on column cfg.business_settings.profile_id is '业务设置ID';
comment on column cfg.business_settings.project_id is '项目ID';
comment on column cfg.business_settings.settings is '设置JSON';
comment on column cfg.business_settings.updated_at is '更新时间';
