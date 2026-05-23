insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'1.4.1',now(),'增加毕业状态');

create table code.graduation_statuses (id integer not null, begin_on date not null, code varchar(20) not null, en_name varchar(300), end_on date, graduated boolean default false not null, name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);
alter table std.graduations add column apply_status_id integer;
alter table std.graduations add column degree_applied boolean;
alter table std.graduations add column status_id integer;
alter table code.graduation_statuses add constraint pk_oqxaqc8aho71r25xm6jyjq6aq primary key (id);
alter table std.graduations add constraint fk_67l8l1qabwxmn9uuji8771280 foreign key (apply_status_id) references code.graduation_statuses (id);
alter table std.graduations add constraint fk_c2m50yq32rniyfar3mfypowjq foreign key (status_id) references code.graduation_statuses (id);
alter table code.graduation_statuses add constraint uk_1i9o25vr5905g7qt3f90tme0d unique (code);

insert into code.graduation_statuses(id,code,name,graduated,begin_on,updated_at)
    values
        (1,'1','毕业',true,'2000-01-01',now()),
        (2,'2','结业',false,'2000-01-01',now()),
        (3,'3','肄业',false,'2000-01-01',now()),
        (4,'4','延期',false,'2000-01-01',now());

comment on table code.graduation_statuses is '毕业状态@std';
comment on column code.graduation_statuses.id is '非业务主键:code';
comment on column code.graduation_statuses.begin_on is '生效日期';
comment on column code.graduation_statuses.code is '代码';
comment on column code.graduation_statuses.en_name is '英文名称';
comment on column code.graduation_statuses.end_on is '失效日期';
comment on column code.graduation_statuses.graduated is '是否毕业';
comment on column code.graduation_statuses.name is '名称';
comment on column code.graduation_statuses.remark is '备注';
comment on column code.graduation_statuses.updated_at is '修改时间';
comment on column std.graduations.apply_status_id is '毕业状态ID';
comment on column std.graduations.degree_applied is '是否已申请学位';
comment on column std.graduations.status_id is '毕业状态ID';
