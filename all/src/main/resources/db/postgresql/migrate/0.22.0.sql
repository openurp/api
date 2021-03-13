create unique index idx_versions_v on base.versions (version);
insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.22.0',now(),'支持分组排课');

------------course----------------
create table code.course_natures (id integer not null, begin_on date not null, code varchar(20) not null unique, en_name varchar(300), end_on date, name varchar(100) not null, practical boolean not null, remark varchar(200), updated_at timestamp not null);
comment on table code.course_natures is '课程性质';
comment on column code.course_natures.id is '非业务主键:auto_increment';
comment on column code.course_natures.begin_on is '生效日期';
comment on column code.course_natures.code is '代码';
comment on column code.course_natures.en_name is '英文名称';
comment on column code.course_natures.end_on is '失效日期';
comment on column code.course_natures.name is '名称';
comment on column code.course_natures.practical is '是否实践课';
comment on column code.course_natures.remark is '备注';
comment on column code.course_natures.updated_at is '修改时间';
alter table code.course_natures add constraint pk_qmyas7mib2hjnqnj6ogbi2n7p primary key (id);

insert into code.course_natures(id,code,name,practical,begin_on,updated_at) values(1,'1','理论课',false,current_date-1,now());
insert into code.course_natures(id,code,name,practical,begin_on,updated_at) values(2,'2','术科课',false,current_date-1,now());
insert into code.course_natures(id,code,name,practical,begin_on,updated_at) values(3,'3','单独设立实验课',false,current_date-1,now());
insert into code.course_natures(id,code,name,practical,begin_on,updated_at) values(4,'4','实践课',true,current_date-1,now());

alter table base.courses add column nature_id integer;
update base.courses set nature_id=4 where practical = true;
update base.courses set nature_id=1 where practical = false;
--alter table base.courses drop practical cascade;
alter table base.courses alter nature_id set not null;
alter table base.courses add constraint fk_6iin27c8tr78rl3ea1ybw0fai foreign key (nature_id) references code.course_natures (id);
alter table base.course_hours rename hour_type_id to teaching_nature_id;
-------------teaching nature ------------------------
alter table base.course_hour_types set schema code;
alter table code.course_hour_types rename to teaching_natures;

insert into code.teaching_natures(id,code,name,category,begin_on,updated_at)
select 1,'1','理论',1,current_date-1,now() where not exists(select * from code.teaching_natures tn where tn.id=1);

insert into code.teaching_natures(id,code,name,category,begin_on,updated_at)
select 2,'2','实验',2,current_date-1,now() where not exists(select * from code.teaching_natures tn where tn.id=2);

insert into code.teaching_natures(id,code,name,category,begin_on,updated_at)
select 9,'9','实践',9,current_date-1,now() where not exists(select * from code.teaching_natures tn where tn.id=9);

update code.teaching_natures set category = id where id in(1,2,9) and id <> category;

-----teaching method---------------------
create table code.teaching_methods (id integer not null, begin_on date not null, code varchar(20) not null unique, en_name varchar(300), end_on date, name varchar(100) not null, remark varchar(200), updated_at timestamp not null);
comment on table code.teaching_methods is '授课方式';
comment on column code.teaching_methods.id is '非业务主键:auto_increment';
comment on column code.teaching_methods.begin_on is '生效日期';
comment on column code.teaching_methods.code is '代码';
comment on column code.teaching_methods.en_name is '英文名称';
comment on column code.teaching_methods.end_on is '失效日期';
comment on column code.teaching_methods.name is '名称';
comment on column code.teaching_methods.remark is '备注';
comment on column code.teaching_methods.updated_at is '修改时间';
alter table code.teaching_methods add constraint pk_s2ob7rrxiu3hwv3dgtwvik47a primary key (id);

insert into code.teaching_methods(id,code,name,begin_on,updated_at) values(1,'1','线下授课',current_date-1,now());
insert into code.teaching_methods(id,code,name,begin_on,updated_at) values(2,'2','线上直播',current_date-1,now());
insert into code.teaching_methods(id,code,name,begin_on,updated_at) values(3,'3','线上录播',current_date-1,now());
insert into code.teaching_methods(id,code,name,begin_on,updated_at) values(4,'4','线上线下同步',current_date-1,now());
--------------course-taker-groups------------------------
create table edu.course_taker_groups (id bigint not null, clazz_id bigint not null, name varchar(100) not null);
comment on table edu.course_taker_groups is '上课名单分组';
comment on column edu.course_taker_groups.id is '非业务主键:datetime';
comment on column edu.course_taker_groups.clazz_id is '教学任务ID';
comment on column edu.course_taker_groups.name is '名称';
alter table edu.course_taker_groups add constraint pk_6ndrt74wireh1asherw4tgy5 primary key (id);
create index idx_99wftqrkh28rar8l5jid83gc7 on edu.course_taker_groups (clazz_id);
alter table edu.course_taker_groups add constraint fk_b5ohlt4r8w00aaf3ht6cc09oj foreign key (clazz_id) references edu.clazzes (id);

alter table edu.course_takers drop restriction_id cascade;
alter table edu.course_takers add column group_id bigint;
alter table edu.course_takers drop constraint fk_qqaj89m3thotceid7iwvyqobq cascade;
alter table edu.course_takers add constraint fk_8buo8j0jj9opvy322g3wbvefc foreign key (group_id) references edu.course_taker_groups (id);
alter table edu.sessions add column taker_group_id bigint;
alter table edu.sessions add constraint fk_kkmo14jtfsweo6i2ylnwpisme foreign key (taker_group_id) references edu.course_taker_groups (id);

-------lesson--------------
drop table edu.lessons_teachers cascade;
drop table edu.lessons_rooms cascade;
comment on table edu.lessons is '授课内容';
alter table edu.lessons drop begin_at cascade;
alter table edu.lessons drop open_on cascade;
alter table edu.lessons drop end_at cascade;
alter table edu.lessons add column group_id bigint;
alter table edu.lessons add column idx integer;
alter table edu.lessons alter idx set not null;
alter table edu.lessons add constraint fk_7lc8cx5b6xlyp7gnyocjp1fp1 foreign key (group_id) references edu.course_taker_groups (id);

-----------session-----------------
alter table edu.sessions rename  remark to places;
alter table edu.sessions add column teaching_nature_id integer default 1;
alter table edu.sessions alter teaching_nature_id set not null;
alter table edu.sessions add column teaching_method_id integer default 1;
alter table edu.sessions alter teaching_method_id set not null;
alter table edu.sessions add constraint fk_indcesiu4vvyo0hl7s7cjairm foreign key (teaching_nature_id) references code.teaching_natures (id);
alter table edu.sessions add constraint fk_cp0hb230utgrxs71b1w1e9aag foreign key (teaching_method_id) references code.teaching_methods (id);

------------teachers-------------
alter table base.teachers drop parttime cascade;
alter table base.teachers drop retired cascade;
alter table base.teacher_types add column retired boolean default false;
alter table base.teacher_types alter retired set not null;