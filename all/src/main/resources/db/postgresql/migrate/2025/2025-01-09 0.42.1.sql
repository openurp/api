insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.42.1',now(),'增加学籍异动申请');

alter table cfg.std_std_alter_configs rename to std_alter_configs;

create table std.std_alter_applies (passed boolean, assignees varchar(255), id bigint not null, alter_from date not null, remark varchar(255), status varchar(255) not null, alter_type_id integer not null, form_data_json varchar(500) not null, process_id varchar(255), alter_to date, apply_at timestamptz not null, std_id bigint not null, alter_data_json varchar(500) not null, reason varchar(255) not null);
create table std.std_alter_apply_steps (name varchar(255) not null, assignee_id bigint, alter_apply_id bigint not null, audit_at timestamptz, passed boolean, id bigint not null, idx integer default 0 not null, comments varchar(255));
