insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.41.10',now(),'增加规则支持');

create table cfg.std_audit_settings (grule_ids varchar(255) not null, id bigint not null, remark varchar(255), begin_on date not null, end_on date, name varchar(200) not null, drule_ids varchar(255), project_id integer not null);
create table cfg.std_audit_settings_levels (audit_setting_id bigint not null, education_level_id integer not null);
alter table cfg.std_audit_settings add constraint pk_1tmso8l97p7jm20tsc4ua4y3x primary key (id);
alter table cfg.std_audit_settings_levels add constraint pk_dy465bk4jkltlc3rxdeupjame primary key (audit_setting_id,education_level_id);
create index idx_3j4odlyj9di9w4pv7uxhcjxk4 on cfg.std_audit_settings_levels (audit_setting_id);
alter table cfg.std_audit_settings add constraint fk_7a1325mn6knpmwqawtlsa61vm foreign key (project_id) references base.projects (id);
alter table cfg.std_audit_settings_levels add constraint fk_9wmc3gpjfs94ip7a3g6ethpkf foreign key (audit_setting_id) references cfg.std_audit_settings (id);
alter table cfg.std_audit_settings_levels add constraint fk_phmalq0nr1btsvy9c6rwsrcyx foreign key (education_level_id) references code.education_levels (id);

comment on column cfg.std_audit_settings.begin_on is '生效日期';
comment on column cfg.std_audit_settings.drule_ids is '审核设置-学位规则IDs';
comment on column cfg.std_audit_settings.end_on is '失效日期';
comment on column cfg.std_audit_settings.grule_ids is '审核设置-毕业规则IDs';
comment on column cfg.std_audit_settings.id is '非业务主键:auto_increment';
comment on column cfg.std_audit_settings.name is '名称';
comment on column cfg.std_audit_settings.project_id is '项目ID';
comment on column cfg.std_audit_settings.remark is '备注';
comment on column cfg.std_audit_settings_levels.audit_setting_id is '毕业审核设置ID';
comment on column cfg.std_audit_settings_levels.education_level_id is '培养层次ID';
