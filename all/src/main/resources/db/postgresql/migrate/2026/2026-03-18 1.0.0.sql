insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'1.0.0',now(),'重构干部任职信息');

--新表
create table code.cadre_post_ranks (id integer not null, begin_on date not null, code varchar(20) not null, en_name varchar(300), end_on date, name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);
alter table code.cadre_post_ranks add constraint pk_nmwkwestutkos99bg4ty0l3y9 primary key (id);
alter table code.cadre_post_ranks add constraint uk_jp9686016eo4drvgciol91150 unique (code);
insert into code.cadre_post_ranks(id,code,name,begin_on,updated_at) values(112,'112','副部级',current_date-1,now());
insert into code.cadre_post_ranks(id,code,name,begin_on,updated_at) values(121,'121','正局级',current_date-1,now());
insert into code.cadre_post_ranks(id,code,name,begin_on,updated_at) values(122,'122','副局级',current_date-1,now());
insert into code.cadre_post_ranks(id,code,name,begin_on,updated_at) values(131,'131','正处级',current_date-1,now());
insert into code.cadre_post_ranks(id,code,name,begin_on,updated_at) values(132,'132','副处级',current_date-1,now());
insert into code.cadre_post_ranks(id,code,name,begin_on,updated_at) values(211,'211','相当于正处级',current_date-1,now());
insert into code.cadre_post_ranks(id,code,name,begin_on,updated_at) values(222,'222','相当于副处级',current_date-1,now());
insert into code.cadre_post_ranks(id,code,name,begin_on,updated_at) values(141,'141','正科级',current_date-1,now());
insert into code.cadre_post_ranks(id,code,name,begin_on,updated_at) values(142,'142','副科级',current_date-1,now());

alter table base.officials rename to cadre_assignments;
alter table base.cadre_assignments add column rank_id integer;
alter table base.cadre_assignments rename column parttime to concurrent;
alter table base.cadre_assignments rename column duty to post;
alter table base.cadre_assignments add principal boolean default false;
alter table base.cadre_assignments add constraint fk_gjs08li01300sjc99tpj2nwfc foreign key (rank_id) references code.cadre_post_ranks (id);

comment on table base.cadre_assignments is '领导干部@hr';
comment on column base.cadre_assignments.id is '非业务主键:datetime';
comment on column base.cadre_assignments.begin_on is '生效日期';
comment on column base.cadre_assignments.concurrent is '是否兼职';
comment on column base.cadre_assignments.department_id is '部门组织机构信息ID';
comment on column base.cadre_assignments.end_on is '失效日期';
comment on column base.cadre_assignments.post is '行政职务';
comment on column base.cadre_assignments.principal is '是否正职';
comment on column base.cadre_assignments.rank_id is '领导干部职级ID';
comment on column base.cadre_assignments.staff_id is '教职工信息ID';
comment on table code.cadre_post_ranks is '领导干部职级@hr';
comment on column code.cadre_post_ranks.id is '非业务主键:code';
comment on column code.cadre_post_ranks.begin_on is '生效日期';
comment on column code.cadre_post_ranks.code is '代码';
comment on column code.cadre_post_ranks.en_name is '英文名称';
comment on column code.cadre_post_ranks.end_on is '失效日期';
comment on column code.cadre_post_ranks.name is '名称';
comment on column code.cadre_post_ranks.remark is '备注';
comment on column code.cadre_post_ranks.updated_at is '修改时间';
