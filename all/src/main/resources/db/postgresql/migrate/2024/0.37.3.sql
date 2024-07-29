insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.37.3',now(),'增加考试试卷分析表');

create table edu.exam_analyses (id bigint not null, clazz_id bigint not null, contents varchar(3500) not null, updated_at timestamptz default current_timestamp not null);
alter table edu.exam_analyses add constraint pk_950ruvj5m4hf04gmkfwwyj9da primary key (id);
alter table edu.exam_analyses add constraint fk_d687atvkk0qjymkbstnv1pss9 foreign key (clazz_id) references edu.clazzes (id);
alter table edu.exam_analyses add constraint uk_5plw503n3jbjkxyi8ti9df4my unique (clazz_id);

create table prac.inv_promotion_defense_groups (id bigint not null, batch_id integer not null, begin_at smallint not null, capacity integer default 0 not null, defense_on date not null, end_at smallint not null, location varchar(255) not null, name varchar(255) not null);
create table prac.inv_promotion_defense_members (id bigint not null, group_id bigint not null, idx integer default 0 not null, project_id bigint not null, updated_at timestamptz default current_timestamp not null);
alter table prac.inv_promotion_defense_groups add constraint pk_34o2p0mvvg6mfy9tv5mm8hupe primary key (id);
alter table prac.inv_promotion_defense_members add constraint pk_b4nhltmsnowel3c1ogfvdnt7d primary key (id);
alter table prac.inv_promotion_defense_groups add constraint fk_dlofenq6ue5er1402n64fuf6e foreign key (batch_id) references prac.inv_batches (id);
alter table prac.inv_promotion_defense_members add constraint fk_sh7gkxgum9ean06dmamngquan foreign key (project_id) references prac.inv_projects (id);
alter table prac.inv_promotion_defense_members add constraint fk_i0264dwyp2s8j2sjnw8r0duwi foreign key (group_id) references prac.inv_promotion_defense_groups (id);
alter table prac.inv_promotion_defense_members add constraint uk_gouy23rxmvq3qpcbyfdphdthj unique (group_id,idx);
create index idx_b7p23pjlvtc8tseu6kp1vw66h on prac.inv_promotion_defense_members (group_id);
comment on table edu.exam_analyses is '考试试卷分析@grade.course';
comment on column edu.exam_analyses.id is '非业务主键:datetime';
comment on column edu.exam_analyses.clazz_id is '教学任务ID';
comment on column edu.exam_analyses.contents is '分析内容';
comment on column edu.exam_analyses.updated_at is '更新时间';
comment on table prac.inv_promotion_defense_groups is '推优答辩组@innovation';
comment on column prac.inv_promotion_defense_groups.id is '非业务主键:datetime';
comment on column prac.inv_promotion_defense_groups.batch_id is '创新批次ID';
comment on column prac.inv_promotion_defense_groups.begin_at is '答辩开始时间';
comment on column prac.inv_promotion_defense_groups.capacity is '计划人数';
comment on column prac.inv_promotion_defense_groups.defense_on is '答辩日期';
comment on column prac.inv_promotion_defense_groups.end_at is '答辩结束时间';
comment on column prac.inv_promotion_defense_groups.location is '答辩地点';
comment on column prac.inv_promotion_defense_groups.name is '名称';
comment on table prac.inv_promotion_defense_members is '推优答辩项目@innovation';
comment on column prac.inv_promotion_defense_members.id is '非业务主键:datetime';
comment on column prac.inv_promotion_defense_members.group_id is '推优答辩组ID';
comment on column prac.inv_promotion_defense_members.idx is '答辩次序';
comment on column prac.inv_promotion_defense_members.project_id is '项目ID';
comment on column prac.inv_promotion_defense_members.updated_at is '更新时间';
