insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.42.0',now(),'增加学籍异动申请');

alter table cfg.std_std_alter_configs rename to std_alter_configs;
create table std.std_alter_applies (passed boolean, assignees varchar(255), id bigint not null, alter_from date not null, remark varchar(255), status varchar(255) not null, alter_type_id integer not null, form_data_json varchar(500) not null, process_id varchar(255), alter_to date, apply_at timestamptz not null, std_id bigint not null, alter_data_json varchar(500) not null, reason varchar(255) not null);
create table std.std_alter_apply_steps (name varchar(255) not null, assignee_id bigint, alter_apply_id bigint not null, audit_at timestamptz, passed boolean, id bigint not null, idx integer default 0 not null, comments varchar(255));

CREATE OR REPLACE FUNCTION public.age_year(
	date)
RETURNS bigint
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE
AS $BODY$
declare rs int4;
begin
  select extract(year from age($1)) into rs ;
  return rs;
end;
$BODY$;

update base.students set max_end_on =  case when end_on > graduate_on then end_on else graduate_on end
where case when end_on > graduate_on then end_on else graduate_on end > max_end_on;

alter table base.students add graduateddd_on date;
update base.students std set graduateddd_on=(select g.graduate_on from base.graduates g where g.std_id=std.id);

update base.student_states ss set end_on = (select std.graduateddd_on from base.students std where ss.id=std.state_id)
where exists(select * from base.students std where std.state_id=ss.id and graduateddd_on is not null and graduateddd_on <> graduate_on);

update base.students std set graduate_on = graduateddd_on,end_on=graduateddd_on where std.id in(select id from base.students where graduateddd_on is not null and graduateddd_on <> graduate_on);

--process only 1 graduate states
alter table base.students add ss_count int4;
update base.students std set ss_count=(select count(*) from base.student_states ss where ss.std_id=std.id);

create table tmp_ss as
select ss.* from base.student_states ss where exists(select * from base.students std where ss.id=std.state_id and std.ss_count=1 and std.graduateddd_on is not null);

create table tmp_ss2 as select * from tmp_ss;
update tmp_ss set begin_on = end_on;
update tmp_ss2 set id=datetime_id(),end_on = end_on - 1,inschool=true,status_id=1;

update base.student_states ss set begin_on= end_on where ss.id in(select id from tmp_ss);
insert into base.student_states  select * from tmp_ss2;

drop table tmp_ss2;
drop table tmp_ss;

--danger 2(endOn = graduate_on)
update base.student_states ss set end_on=(select std.graduate_on from base.students std where std.state_id=ss.id)
where exists(select * from base.students std where std.graduateddd_on is null and std.end_on > std.graduate_on and ss.id=std.state_id);
update base.students std set end_on = graduate_on where graduateddd_on is null and end_on > graduate_on;

--update states end_on = student end_on
update base.student_states ss set end_on=(select std.end_on from base.students std where std.state_id=ss.id)
where exists(select * from base.students std where std.state_id=ss.id and std.end_on <> ss.end_on);

alter table base.students drop column ss_count;
alter table base.students drop column graduateddd_on;

--clazz archive doc
create table code.clazz_archive_docs (id integer not null, begin_on date not null, code varchar(20) not null, en_name varchar(300), end_on date, name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);
create table edu.clazz_archives (id bigint not null, archived_by_id bigint not null, clazz_id bigint not null, doc_id integer not null, doc_path varchar(400) not null, doc_size integer default 0 not null, updated_at timestamptz default current_timestamp not null);
create table code.foreign_book_types (id integer not null, begin_on date not null, code varchar(20) not null, en_name varchar(300), end_on date, name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);
create table code.textbook_forms (id integer not null, begin_on date not null, code varchar(20) not null, en_name varchar(300), end_on date, name varchar(100) not null, remark varchar(200), updated_at timestamptz default current_timestamp not null);

--textbook
alter table base.textbooks add column book_form_id integer;
alter table base.textbooks add column discipline_category_id integer;
alter table base.textbooks add column domestic boolean default true;
alter table base.textbooks alter domestic set not null;
alter table base.textbooks add column foreign_book_type_id integer;


--practice
alter table edu.lesson_designs alter homework type varchar(1000);
alter table prac.std_practice_hours add column course_grade_id bigint;
alter table prac.std_practice_hours add column required_hours integer default 0;
alter table prac.std_practice_hours alter required_hours set not null;

alter table code.clazz_archive_docs add constraint pk_rdl7dyua7mq986uedf1tuo1sg primary key (id);
alter table code.foreign_book_types add constraint pk_27dwcqmg6miv7f4freg62bb4b primary key (id);
alter table code.textbook_forms add constraint pk_51hrkbnu1nvo3wum11rlmfg41 primary key (id);
alter table edu.clazz_archives add constraint pk_svxcnumv1r9p24ymun0hsdg28 primary key (id);
alter table std.std_alter_applies add constraint pk_pl84xb1wr2s9338d4f4kk7o92 primary key (id);
alter table std.std_alter_apply_steps add constraint pk_guolqow8ldtemrqwb2v6rxeyh primary key (id);
alter table base.textbooks add constraint fk_4mqbsq2ipe8go0rxhpgvjpgqc foreign key (book_form_id) references code.textbook_forms (id);
alter table base.textbooks add constraint fk_h33rngxx01ex7ea8tb4xysiiy foreign key (foreign_book_type_id) references code.foreign_book_types (id);
alter table base.textbooks add constraint fk_kcen97hvqclls3daxbs8gwd03 foreign key (discipline_category_id) references code.discipline_categories (id);
alter table cfg.std_alter_configs add constraint fk_j42kib8a5iqlwlpradf2t8k9w foreign key (alter_type_id) references code.std_alter_types (id);
alter table cfg.std_alter_configs add constraint fk_psdba96tb131tqc8lpievfadj foreign key (project_id) references base.projects (id);
alter table cfg.std_alter_configs add constraint fk_j8grxvj752xly6qc8c2amx0pp foreign key (status_id) references code.student_statuses (id);
alter table edu.clazz_archives add constraint fk_adclnnepw3n885amwtsltah8r foreign key (doc_id) references code.clazz_archive_docs (id);
alter table edu.clazz_archives add constraint fk_5rqvebyop21a4uouypp85qet9 foreign key (archived_by_id) references base.users (id);
alter table edu.clazz_archives add constraint fk_ebr6602e41pv8ybnuwwla44ol foreign key (clazz_id) references edu.clazzes (id);
alter table std.std_alter_applies add constraint fk_p13ofvyw587ihey8v0ppagk7o foreign key (std_id) references base.students (id);
alter table std.std_alter_applies add constraint fk_hhvysmtw20mqk9riv29u4peh9 foreign key (alter_type_id) references code.std_alter_types (id);
alter table std.std_alter_apply_steps add constraint fk_ps6ewc9g5p2jjkqrst5llmrv6 foreign key (alter_apply_id) references std.std_alter_applies (id);
alter table std.std_alter_apply_steps add constraint fk_djav4w1k1xi29jwmksgjml797 foreign key (assignee_id) references base.users (id);
alter table code.clazz_archive_docs add constraint uk_19qhsalo7xubo3jutx8o5htmh unique (code);
alter table code.foreign_book_types add constraint uk_sxtrrji37pli31kc5qsbqrqhy unique (code);
alter table code.textbook_forms add constraint uk_dmmvqtr0phm6cuy5r3bladqfk unique (code);
alter table edu.clazz_archives add constraint uk_caluyc9lm5rlj5628fenvr9a unique (clazz_id,doc_id);
create index idx_hl7uwua6rl6ruchg64kc9kput on std.std_alter_apply_steps (alter_apply_id);
comment on column base.textbooks.book_form_id is '教材形态ID';
comment on column base.textbooks.discipline_category_id is '学科门类ID';
comment on column base.textbooks.domestic is '是否是境内教材';
comment on column base.textbooks.foreign_book_type_id is '境外教材类别ID';
comment on table cfg.std_alter_configs is '学籍异动配置@alter.config';
comment on column cfg.std_alter_configs.id is '非业务主键:datetime';
comment on column cfg.std_alter_configs.alter_end_on is '异动预计离校日期';
comment on column cfg.std_alter_configs.alter_graduate_on is '异动预计毕业日期';
comment on column cfg.std_alter_configs.alter_type_id is '学籍异动类别ID';
comment on column cfg.std_alter_configs.attributes is '异动影响属性';
comment on column cfg.std_alter_configs.inschool is '异动后是否在校';
comment on column cfg.std_alter_configs.project_id is '项目ID';
comment on column cfg.std_alter_configs.status_id is '学生学籍状态ID';
comment on table code.clazz_archive_docs is '课程规章资料文档类型@edu';
comment on column code.clazz_archive_docs.id is '非业务主键:code';
comment on column code.clazz_archive_docs.begin_on is '生效日期';
comment on column code.clazz_archive_docs.code is '代码';
comment on column code.clazz_archive_docs.en_name is '英文名称';
comment on column code.clazz_archive_docs.end_on is '失效日期';
comment on column code.clazz_archive_docs.name is '名称';
comment on column code.clazz_archive_docs.remark is '备注';
comment on column code.clazz_archive_docs.updated_at is '修改时间';
comment on table code.foreign_book_types is '境外教材类别@sin';
comment on column code.foreign_book_types.id is '非业务主键:code';
comment on column code.foreign_book_types.begin_on is '生效日期';
comment on column code.foreign_book_types.code is '代码';
comment on column code.foreign_book_types.en_name is '英文名称';
comment on column code.foreign_book_types.end_on is '失效日期';
comment on column code.foreign_book_types.name is '名称';
comment on column code.foreign_book_types.remark is '备注';
comment on column code.foreign_book_types.updated_at is '修改时间';
comment on table code.textbook_forms is '教材形态@sin';
comment on column code.textbook_forms.id is '非业务主键:code';
comment on column code.textbook_forms.begin_on is '生效日期';
comment on column code.textbook_forms.code is '代码';
comment on column code.textbook_forms.en_name is '英文名称';
comment on column code.textbook_forms.end_on is '失效日期';
comment on column code.textbook_forms.name is '名称';
comment on column code.textbook_forms.remark is '备注';
comment on column code.textbook_forms.updated_at is '修改时间';
comment on table edu.clazz_archives is '课程资料归档@course';
comment on column edu.clazz_archives.id is '非业务主键:datetime';
comment on column edu.clazz_archives.archived_by_id is '归档人ID';
comment on column edu.clazz_archives.clazz_id is '教学任务ID';
comment on column edu.clazz_archives.doc_id is '课程规章资料文档类型ID';
comment on column edu.clazz_archives.doc_path is '存储路径';
comment on column edu.clazz_archives.doc_size is '文件大小';
comment on column edu.clazz_archives.updated_at is '更新时间';
comment on column prac.std_practice_hours.course_grade_id is '已转为课程成绩';
comment on column prac.std_practice_hours.required_hours is '学时要求';
comment on table std.std_alter_applies is '学籍异动申请@alter';
comment on column std.std_alter_applies.id is '非业务主键:datetime';
comment on column std.std_alter_applies.alter_data_json is '提交需要变更的数据';
comment on column std.std_alter_applies.alter_from is '变动开始日期';
comment on column std.std_alter_applies.alter_to is '变动结束日期';
comment on column std.std_alter_applies.alter_type_id is '学籍异动类别ID';
comment on column std.std_alter_applies.apply_at is '申请时间';
comment on column std.std_alter_applies.assignees is '受理人账户';
comment on column std.std_alter_applies.form_data_json is '提交表单';
comment on column std.std_alter_applies.passed is '是否通过';
comment on column std.std_alter_applies.process_id is '流程Id';
comment on column std.std_alter_applies.reason is '申请理由';
comment on column std.std_alter_applies.remark is '备注';
comment on column std.std_alter_applies.status is '状态';
comment on column std.std_alter_applies.std_id is '学生ID';
comment on table std.std_alter_apply_steps is '异动申请审核步骤@alter';
comment on column std.std_alter_apply_steps.id is '非业务主键:datetime';
comment on column std.std_alter_apply_steps.alter_apply_id is '学籍异动申请ID';
comment on column std.std_alter_apply_steps.assignee_id is '受理人ID';
comment on column std.std_alter_apply_steps.audit_at is '审核时间';
comment on column std.std_alter_apply_steps.comments is '审核意见';
comment on column std.std_alter_apply_steps.idx is '审核顺序';
comment on column std.std_alter_apply_steps.name is '名称';
comment on column std.std_alter_apply_steps.passed is '审核结果';
