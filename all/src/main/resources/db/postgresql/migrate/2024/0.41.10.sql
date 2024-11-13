insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.41.10',now(),'增加规则支持');

create table cfg.businesses (id bigint not null, code varchar(50) not null, name varchar(100) not null);
create table cfg.rule_metas (id bigint not null, business_id bigint not null, description varchar(500) not null, name varchar(50) not null, title varchar(80) not null);
create table cfg.rule_param_metas (id bigint not null, data_type varchar(255) not null, description varchar(200) not null, name varchar(50) not null, rule_meta_id bigint not null, title varchar(80) not null);
create table cfg.rule_params (id bigint not null, contents varchar(500) not null, meta_id bigint not null, rule_id bigint not null);
create table cfg.rules (id bigint not null, enabled boolean default false not null, meta_id bigint not null);
create table cfg.std_audit_settings (id bigint not null, begin_on date not null, end_on date, project_id integer not null, remark varchar(255));
create table cfg.std_audit_settings_drules (audit_setting_id bigint not null, rule_id bigint not null);
create table cfg.std_audit_settings_grules (audit_setting_id bigint not null, rule_id bigint not null);
create table cfg.std_audit_settings_levels (audit_setting_id bigint not null, education_level_id integer not null);
alter table cfg.businesses add constraint pk_hwk1w6gci03htaw42gsh91oci primary key (id);
alter table cfg.rule_metas add constraint pk_oa0ynexmn1eqxisovviwxgv1w primary key (id);
alter table cfg.rule_param_metas add constraint pk_5qdluxes7lil2fquhfmyye9br primary key (id);
alter table cfg.rule_params add constraint pk_q0krx8koxest0cn2fsjoylsn7 primary key (id);
alter table cfg.rules add constraint pk_j73u8s0y7cjco3vmns7wsnu0j primary key (id);
alter table cfg.std_audit_settings add constraint pk_1tmso8l97p7jm20tsc4ua4y3x primary key (id);
alter table cfg.std_audit_settings_drules add constraint pk_rb4qw3e2v6cvoa2ixkdxaimuf primary key (audit_setting_id,rule_id);
alter table cfg.std_audit_settings_grules add constraint pk_o6trqq27d9c8lg9323pnw589b primary key (audit_setting_id,rule_id);
alter table cfg.std_audit_settings_levels add constraint pk_dy465bk4jkltlc3rxdeupjame primary key (audit_setting_id,education_level_id);
alter table cfg.rule_metas add constraint fk_ep7oi1qpmhehcruw1qw5jqnwh foreign key (business_id) references cfg.businesses (id);
alter table cfg.rule_param_metas add constraint fk_qjq55jc6py1k24ts1vddu9c4v foreign key (rule_meta_id) references cfg.rule_metas (id);
alter table cfg.rule_params add constraint fk_177xngtk7gkvaqmswvjlqouo4 foreign key (meta_id) references cfg.rule_param_metas (id);
alter table cfg.rule_params add constraint fk_kj0ksjtxdmuhi8k5oqo9dv936 foreign key (rule_id) references cfg.rules (id);
alter table cfg.rules add constraint fk_o3cv2wsi7kv42s5dripgw5jus foreign key (meta_id) references cfg.rule_metas (id);
alter table cfg.std_audit_settings add constraint fk_7a1325mn6knpmwqawtlsa61vm foreign key (project_id) references base.projects (id);
alter table cfg.std_audit_settings_drules add constraint fk_o6yptdfm0ihbl98rp0rcaoidl foreign key (audit_setting_id) references cfg.std_audit_settings (id);
alter table cfg.std_audit_settings_drules add constraint fk_ekad1lyggnjxhvvwhbnrlnc3n foreign key (rule_id) references cfg.rules (id);
alter table cfg.std_audit_settings_grules add constraint fk_qs4aii1nd2lgrss1jad767qao foreign key (audit_setting_id) references cfg.std_audit_settings (id);
alter table cfg.std_audit_settings_grules add constraint fk_fjp0w2ajp7a721jur25gte02v foreign key (rule_id) references cfg.rules (id);
alter table cfg.std_audit_settings_levels add constraint fk_9wmc3gpjfs94ip7a3g6ethpkf foreign key (audit_setting_id) references cfg.std_audit_settings (id);
alter table cfg.std_audit_settings_levels add constraint fk_phmalq0nr1btsvy9c6rwsrcyx foreign key (education_level_id) references code.education_levels (id);
create index idx_hxhjfg7x56i21jyaki0rkvg9l on cfg.rule_param_metas (rule_meta_id);
create index idx_hna20lgrudoep14oismm0m9uv on cfg.rule_params (rule_id);
create index idx_ef0xtrmh9123lo5x41dpfm07u on cfg.std_audit_settings_drules (audit_setting_id);
create index idx_r7qgh6ihyer4bnydmhanh0o3f on cfg.std_audit_settings_grules (audit_setting_id);
create index idx_3j4odlyj9di9w4pv7uxhcjxk4 on cfg.std_audit_settings_levels (audit_setting_id);

comment on table cfg.businesses is '业务类型@cfg';
comment on column cfg.businesses.id is '非业务主键:auto_increment';
comment on column cfg.businesses.code is '代码';
comment on column cfg.businesses.name is '名称';
comment on table cfg.rule_metas is '规则元数据@cfg';
comment on column cfg.rule_metas.id is '非业务主键:datetime';
comment on column cfg.rule_metas.business_id is '业务类型ID';
comment on column cfg.rule_metas.description is '描述';
comment on column cfg.rule_metas.name is '名称';
comment on column cfg.rule_metas.title is '标题';
comment on table cfg.rule_param_metas is '规则参数元数据@cfg';
comment on column cfg.rule_param_metas.id is '非业务主键:datetime';
comment on column cfg.rule_param_metas.data_type is '数据类型';
comment on column cfg.rule_param_metas.description is '描述';
comment on column cfg.rule_param_metas.name is '名称';
comment on column cfg.rule_param_metas.rule_meta_id is '规则元数据ID';
comment on column cfg.rule_param_metas.title is '标题';
comment on table cfg.rule_params is '规则参数@cfg';
comment on column cfg.rule_params.id is '非业务主键:datetime';
comment on column cfg.rule_params.contents is '值';
comment on column cfg.rule_params.meta_id is '规则参数元数据ID';
comment on column cfg.rule_params.rule_id is '规则ID';
comment on table cfg.rules is '规则@cfg';
comment on column cfg.rules.id is '非业务主键:datetime';
comment on column cfg.rules.enabled is '是否启用';
comment on column cfg.rules.meta_id is '规则元数据ID';
comment on table cfg.std_audit_settings is '毕业审核设置@graduation.config';
comment on column cfg.std_audit_settings.id is '非业务主键:auto_increment';
comment on column cfg.std_audit_settings.begin_on is '生效日期';
comment on column cfg.std_audit_settings.end_on is '失效日期';
comment on column cfg.std_audit_settings.project_id is '项目ID';
comment on column cfg.std_audit_settings.remark is '备注';
comment on table cfg.std_audit_settings_drules is '审核设置-学位规则@graduation.config';
comment on column cfg.std_audit_settings_drules.audit_setting_id is '毕业审核设置ID';
comment on column cfg.std_audit_settings_drules.rule_id is '规则ID';
comment on table cfg.std_audit_settings_grules is '审核设置-毕业规则@graduation.config';
comment on column cfg.std_audit_settings_grules.audit_setting_id is '毕业审核设置ID';
comment on column cfg.std_audit_settings_grules.rule_id is '规则ID';
comment on table cfg.std_audit_settings_levels is '审核设置-层次@graduation.config';
comment on column cfg.std_audit_settings_levels.audit_setting_id is '毕业审核设置ID';
comment on column cfg.std_audit_settings_levels.education_level_id is '培养层次ID';
