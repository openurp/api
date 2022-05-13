insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.25.1',now(),'增加年级');

alter table std.graduate_sessions rename gaduate_grade_id to graduate_grade_id;
alter table std.graduate_sessions drop constraint fk_1kuglq2yqj5gipmr7s1s0i1h6 cascade;
alter table std.graduate_sessions add constraint fk_qtky36f0j9qk75hc1jcn9pa3x foreign key (graduate_grade_id) references base.graduate_grades (id);

--grades--
create table base.grades (id integer not null, code varchar(255) not null unique, name varchar(255) not null);
comment on table base.grades is '年级@std';
comment on column base.grades.id is '非业务主键:auto_increment';
comment on column base.grades.code is '代码';
comment on column base.grades.name is '名称';
alter table base.grades add constraint pk_tfax6erblyil71kws4l93yhnv primary key (id);

alter table std.transfer_schemes add column grade_id integer;
alter table std.transfer_schemes alter grade_id set not null;
alter table std.transfer_schemes alter audit_end_at drop not null;
alter table std.transfer_schemes alter audit_begin_at drop not null;
alter table std.transfer_schemes add constraint fk_tk18vblwpr3r37hj6rfqacbr9 foreign key (grade_id) references base.grades (id);
