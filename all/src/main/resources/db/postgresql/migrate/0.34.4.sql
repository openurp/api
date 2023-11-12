insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.34.4',now(),'增加高校信息');

create table code.institution_categories (id integer not null, begin_on date not null, code varchar(20) not null, en_name varchar(300), end_on date, name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);
alter table code.institution_categories add constraint pk_fd9fbuxbvexuhcw9jfsvrvdtp primary key (id);
alter table code.institution_categories add constraint institution_categories_code_key unique (code);
comment on table code.institution_categories is '高校性质类别@edu';
comment on column code.institution_categories.id is '非业务主键:code';
comment on column code.institution_categories.begin_on is '生效日期';
comment on column code.institution_categories.code is '代码';
comment on column code.institution_categories.en_name is '英文名称';
comment on column code.institution_categories.end_on is '失效日期';
comment on column code.institution_categories.name is '名称';
comment on column code.institution_categories.remark is '备注';
comment on column code.institution_categories.updated_at is '修改时间';

insert into code.institution_categories(id,code,name,begin_on,updated_at) values(1,'01','综合大学',current_date -10,now());
insert into code.institution_categories(id,code,name,begin_on,updated_at) values(2,'02','理工院校',current_date -10,now());
insert into code.institution_categories(id,code,name,begin_on,updated_at) values(3,'03','农林院校',current_date -10,now());
insert into code.institution_categories(id,code,name,begin_on,updated_at) values(4,'04','林业院校',current_date -10,now());
insert into code.institution_categories(id,code,name,begin_on,updated_at) values(5,'05','医药院校',current_date -10,now());
insert into code.institution_categories(id,code,name,begin_on,updated_at) values(6,'06','师范院校',current_date -10,now());
insert into code.institution_categories(id,code,name,begin_on,updated_at) values(7,'07','语文院校',current_date -10,now());
insert into code.institution_categories(id,code,name,begin_on,updated_at) values(8,'08','财经院校',current_date -10,now());
insert into code.institution_categories(id,code,name,begin_on,updated_at) values(9,'09','政法院校',current_date -10,now());
insert into code.institution_categories(id,code,name,begin_on,updated_at) values(10,'10','体育院校',current_date -10,now());
insert into code.institution_categories(id,code,name,begin_on,updated_at) values(11,'11','艺术院校',current_date -10,now());
insert into code.institution_categories(id,code,name,begin_on,updated_at) values(12,'12','民族院校',current_date -10,now());


drop table base.dayoffs cascade;
alter table base.schools add column category_id integer;
alter table base.schools add column division_id integer;
alter table base.schools add column identifier varchar(10);
alter table base.schools add column superior_org varchar(50);
alter table base.schools add column uscc varchar(18);
alter table base.schools add constraint fk_i0amwk9vd4lhgjtb2c943edtf foreign key (division_id) references code.divisions (id);

comment on column base.schools.identifier is '标识码(10位)';
comment on column base.schools.institution_id is '研究机构ID';
comment on column base.schools.superior_org is '主管部门';
comment on column base.schools.uscc is '统一信用代码';

insert into code.divisions (id,code,name,begin_on,updated_at) values(310000,'310000','上海',current_date-1,now());

update base.schools set category_id=9,division_id=310000 where name='华东政法大学';
update base.schools set category_id=8,division_id=310000 where name='上海财经大学';
update base.schools set category_id=8,division_id=310000 where name='上海立信会计金融学院';
update base.schools set category_id=2,division_id=310000 where name='上海理工大学';
update base.schools set category_id=11,division_id=310000 where name='上海音乐学院';
update base.schools set category_id=2,division_id=310000 where name='上海工程技术大学';

alter table base.schools alter category_id set not null;
alter table base.schools alter division_id set not null;
alter table base.schools add constraint fk_ewpo25xxpkxlls6vv52k62vi8 foreign key (category_id) references code.institution_categories (id);
