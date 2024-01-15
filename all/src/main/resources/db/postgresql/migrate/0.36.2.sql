insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.36.2',now(),'增加校长信息');

create table base.presidents (id bigint not null, begin_on date not null, en_name varchar(100) not null, end_on date, name varchar(50) not null, school_id integer not null);
alter table base.graduate_seasons drop president cascade;
alter table base.graduates add column finish_on date;
alter table base.graduates alter graduate_on drop not null;
alter table base.squads alter en_name type varchar(250);
alter table base.presidents add constraint pk_cbv4hxj91u02s00no22yukttx primary key (id);
alter table base.presidents add constraint fk_cuu0xfncdxdq3peluf04grdax foreign key (school_id) references base.schools (id);
comment on table base.presidents is '校长@hr';
comment on column base.presidents.id is '非业务主键:datetime';
comment on column base.presidents.begin_on is '生效日期';
comment on column base.presidents.en_name is '英文名';
comment on column base.presidents.end_on is '失效日期';
comment on column base.presidents.name is '名称';
comment on column base.presidents.school_id is '学校信息ID';
comment on column base.graduates.finish_on is '结业日期';
comment on column base.graduates.graduate_on is '毕业日期';
