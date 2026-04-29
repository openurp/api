insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'1.3.0',now(),'扩展教室占用结构');

create table his.edu_occupancies (id bigint not null, activity_id bigint default 0 not null, activity_type_id integer not null, app_id bigint not null, begin_at smallint not null, depart_id integer not null, end_at smallint not null, room_id bigint not null, school_year integer not null, shared boolean default false not null, start_on date not null, std_count integer default 0 not null, subject varchar(500) not null, updated_at timestamptz default current_timestamp not null, weekstate bigint not null);
alter table his.edu_occupancies add constraint pk_7xyb65adr1lhgohe9729eeo1h primary key (id,school_year);
create index idx_4m1o28u47wl0uxr6r929a12tt on his.edu_occupancies (activity_id);
create index idx_nrrdkdb47amqyor3ia51aaqbq on his.edu_occupancies (room_id);
create index idx_pr55fhe4vls9geopr8vtqnnv5 on his.edu_occupancies (start_on);
alter table his.edu_occupancies add constraint fk_ta3v5y0rlw5p2mjii6998wmxw foreign key (app_id) references edu.room_occupy_apps (id);
alter table his.edu_occupancies add constraint fk_ipk4ah12sab6uwiqdk9og90kg foreign key (room_id) references base.classrooms (id);
alter table his.edu_occupancies add constraint fk_3tkyhkrhaof56x5h3josgbpoe foreign key (depart_id) references base.departments (id);
alter table his.edu_occupancies add constraint fk_ehgk4rrncqpqe5lt62wm9ha7j foreign key (activity_type_id) references code.activity_types (id);

alter table edu.occupancies rename column comments to subject;
alter table edu.occupancies add column depart_id integer;
alter table edu.occupancies add column std_count integer default 0;
alter table edu.occupancies add constraint fk_2fgymnnikfutk9trnrswdq104 foreign key (depart_id) references base.departments (id);

update edu.occupancies o set (depart_id,std_count)=(select teach_depart_id,std_count from edu.clazzes clz where clz.id=o.activity_id)
where o.activity_type_id=1 and app_id=1 and depart_id is null;

update edu.occupancies o set (depart_id,std_count)=(select teach_depart_id,std_count from edu.exam_rooms er where er.id=o.activity_id)
where o.activity_type_id=2 and app_id=2 and depart_id is null;

update edu.occupancies o set (depart_id,std_count)=(select u.department_id,er.attendance_num from edu.room_applies er,base.users u where er.id=o.activity_id and er.applicant_id=u.id)
where  app_id=3 and depart_id is null;

alter table edu.occupancies alter depart_id set not null;
alter table edu.occupancies alter std_count set not null;

INSERT INTO his.edu_occupancies(
    id, activity_id, activity_type_id, app_id, begin_at, depart_id, end_at, room_id, shared, start_on, std_count, subject, updated_at, weekstate,school_year)
select id, activity_id, activity_type_id, app_id, begin_at, depart_id, end_at, room_id, shared, start_on, std_count, subject, updated_at, weekstate ,
       extract(year from start_on)
from edu.occupancies o where
    exists(select * from base.school_years where archived=true and start_year=extract(year from start_on) )
                         and o.depart_id is not null;
delete from edu.occupancies a where exists(select * from his.edu_occupancies ho where ho.id=a.id);

comment on column edu.occupancies.depart_id is '占用院系ID';
comment on column edu.occupancies.std_count is '学生人数';
comment on column edu.occupancies.subject is '活动主题';
comment on table his.edu_occupancies is '房间占用情况@his';
comment on column his.edu_occupancies.id is '非业务主键:assigned';
comment on column his.edu_occupancies.activity_id is '活动Id';
comment on column his.edu_occupancies.activity_type_id is '活动类型ID';
comment on column his.edu_occupancies.app_id is '用户系统ID';
comment on column his.edu_occupancies.begin_at is '开始时间';
comment on column his.edu_occupancies.depart_id is '占用院系ID';
comment on column his.edu_occupancies.end_at is '结束时间';
comment on column his.edu_occupancies.room_id is '房间ID';
comment on column his.edu_occupancies.school_year is '学年度';
comment on column his.edu_occupancies.shared is '是否可以共享占用';
comment on column his.edu_occupancies.start_on is '开始日期';
comment on column his.edu_occupancies.std_count is '学生人数';
comment on column his.edu_occupancies.subject is '活动主题';
comment on column his.edu_occupancies.updated_at is '更新时间';
comment on column his.edu_occupancies.weekstate is '周状态';
