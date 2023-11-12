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

create table base.courses_directions (course_id bigint not null, direction_id bigint not null);
create table edu.attendances (id bigint not null, course_id bigint not null, leave bigint default 0 not null, semester_id integer not null, status bigint default 0 not null, std_id bigint not null);
create table edu.std_dayoffs (id bigint not null, begin_at timestamptz not null, dayoff_type integer not null, end_at timestamptz not null, reason varchar(200) not null, semester_id integer not null, std_id bigint not null);
alter table base.courses_directions add constraint pk_136dwy0fmose8fj6v9aak3pey primary key (course_id,direction_id);
alter table edu.attendances add constraint pk_2t9o47wqtt7xk1qlypcetbjw8 primary key (id);
alter table edu.std_dayoffs add constraint pk_2qd9rpbufn0f9b49fw2mxetdn primary key (id);
alter table base.courses_directions add constraint fk_tonpu8hysqc3uwnthcu2jyhhd foreign key (course_id) references base.courses (id);
alter table base.courses_directions add constraint fk_mqh5wxs205vxjdn03nxd4xqwx foreign key (direction_id) references base.directions (id);
alter table edu.attendances add constraint fk_ejex8x13p9qoo55i6oqrmpvnd foreign key (std_id) references base.students (id);
alter table edu.attendances add constraint fk_g8lphmp6k3q3l6gj7vl359tss foreign key (course_id) references base.courses (id);
alter table edu.attendances add constraint fk_3rkwlet3ptnhlhlrkhjy0o2u7 foreign key (semester_id) references base.semesters (id);
alter table edu.std_dayoffs add constraint fk_2ka4wcnq73cywuhkuxstg0r35 foreign key (std_id) references base.students (id);
alter table edu.std_dayoffs add constraint fk_kowipw86myhy0cmwskdjchv36 foreign key (semester_id) references base.semesters (id);
alter table edu.attendances add constraint uk_cmxo2cohpt1qv4qsirn68cr3x unique (semester_id,course_id,std_id);
create index idx_nsr0sov8n153ey2nvko5wgxei on base.courses_directions (course_id);
create index idx_9pvh5lmgs2d11b304e8jmqt8q on degree.subject_applies (last_id);

comment on table base.courses_directions is '面向专业方向@edu';
comment on column base.courses_directions.course_id is '课程基本信息ID';
comment on column base.courses_directions.direction_id is '方向信息 专业领域ID';
comment on table base.courses_majors is '面向专业';
comment on table edu.attendances is '课程考勤@attendance';
comment on column edu.attendances.id is '非业务主键:datetime';
comment on column edu.attendances.course_id is '课程基本信息ID';
comment on column edu.attendances.leave is '请假状态';
comment on column edu.attendances.semester_id is '学年学期ID';
comment on column edu.attendances.status is '出勤状态';
comment on column edu.attendances.std_id is '学籍信息实现ID';
comment on table edu.std_dayoffs is '学生请假@attendance';
comment on column edu.std_dayoffs.id is '非业务主键:datetime';
comment on column edu.std_dayoffs.begin_at is '开始时间';
comment on column edu.std_dayoffs.dayoff_type is '请假类型';
comment on column edu.std_dayoffs.end_at is '结束时间';
comment on column edu.std_dayoffs.reason is '请假事由';
comment on column edu.std_dayoffs.semester_id is '学年学期ID';
comment on column edu.std_dayoffs.std_id is '学籍信息实现ID';
