insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.49.0',now(),'增加实验中心');

create table base.lab_centers (id bigint not null, begin_on date not null, end_on date, name varchar(200) not null, school_id integer not null, short_name varchar(255));
create table base.lab_centers_departs (department_id integer not null, lab_center_id bigint not null);

alter table base.lab_centers add constraint pk_nayypwvu5gso83w6ayoe7evdh primary key (id);
alter table base.lab_centers_departs add constraint pk_h83hwmcma7e7r5uvxrj1jq9te primary key (lab_center_id,department_id);
alter table base.lab_centers add constraint fk_4gehni8ckjboarc3jb5drcnsr foreign key (school_id) references base.schools (id);
alter table base.lab_centers_departs add constraint fk_1lxpxbd5dlhuv0jy2jj52tlpa foreign key (lab_center_id) references base.lab_centers (id);
alter table base.lab_centers_departs add constraint fk_mdfbmfmy1kb83j3j83e6cqbse foreign key (department_id) references base.departments (id);
create index idx_a0kvh8ahjb7bcpl1r0bm04x0w on base.lab_centers_departs (lab_center_id);

--his course profiles
create index idx_xaiterx8ciy41f5pv3pg53c4 on his.edu_course_profiles (course_id);
alter table base.course_profiles alter en_description type varchar(2000);
alter table his.edu_course_profiles alter bibliography type varchar(2000);
alter table his.edu_course_profiles alter description type varchar(800);
alter table his.edu_course_profiles alter en_description type varchar(2000);
alter table his.edu_course_profiles alter majors type varchar(200);
alter table his.edu_course_profiles alter materials type varchar(1000);
alter table his.edu_course_profiles alter textbooks type varchar(500);

comment on table base.lab_centers is '实验中心@resource';
comment on column base.lab_centers.id is '非业务主键:datetime';
comment on column base.lab_centers.begin_on is '生效日期';
comment on column base.lab_centers.end_on is '失效日期';
comment on column base.lab_centers.name is '名称';
comment on column base.lab_centers.school_id is '学校信息ID';
comment on column base.lab_centers.short_name is '简称';
comment on table base.lab_centers_departs is '实验中心-关联学院@resource';
comment on column base.lab_centers_departs.department_id is '部门组织机构信息ID';
comment on column base.lab_centers_departs.lab_center_id is '实验中心ID';
