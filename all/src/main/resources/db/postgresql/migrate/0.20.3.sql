alter table edu.exam_activities add centralized boolean;
alter table edu.exam_tasks add centralized boolean;

update edu.exam_activities set centralized = false where depart_arranged=true;
update edu.exam_activities set centralized = true where depart_arranged=false;

update edu.exam_tasks set centralized = false where depart_arranged=true;
update edu.exam_tasks set centralized = true where depart_arranged=false;

alter table edu.exam_activities drop column depart_arranged;
alter table edu.exam_tasks drop column depart_arranged;

create table base.versions (id integer not null, description varchar(200) not null, updated_at timestamp not null, version varchar(20) not null);
comment on table base.versions is '版本迁移日志';
comment on column base.versions.id is '非业务主键:auto_increment';
comment on column base.versions.description is '内容';
comment on column base.versions.updated_at is '更新时间';
comment on column base.versions.version is '版本';
alter table base.versions add constraint pk_kn37tnh6h9broruk4q2gsyyip primary key (id);

insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.20.3',now(),'将考务中的depart_arranged重命名为centralized');

