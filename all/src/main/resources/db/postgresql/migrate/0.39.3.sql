insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.39.3',now(),'改进培养方案模型');

alter table edu.syllabuses alter column bibliography type varchar(2000);
alter table edu.syllabuses alter learning_hours type float4;
alter table edu.syllabuses alter column materials type varchar(500);

alter table edu.syllabus_topics alter learning_hours type float4;
alter table edu.syllabus_topics add column exam boolean default false;
alter table edu.syllabus_topics alter contents type varchar(4000);

alter table edu.syllabus_topic_elements alter contents type varchar(3000);
alter table edu.syllabus_credit_hours alter credit_hours type float4;
alter table edu.syllabus_topic_hours alter credit_hours type float4;
alter table edu.syllabus_exam_hours alter credit_hours type float4;

update edu.syllabus_outcomes set idx=replace(code,'R','')::int4 where code like 'R%';
alter table edu.syllabus_outcomes drop column code;
alter table edu.syllabus_outcomes add title varchar(100);
alter table edu.syllabus_outcomes add idx integer default 0;
alter table edu.syllabus_outcomes alter column title set not null;
alter table edu.syllabus_outcomes drop constraint if exists fk_tejpgbfo0a3mdwswqbvcbpujc cascade;

alter table edu.lessons add column exam boolean default false;
alter table edu.lessons alter exam set not null;
alter table edu.lessons add column hours integer default 0;
alter table edu.lessons alter contents type varchar(2000);

alter table edu.programs add start_term smallint default 0;
alter table edu.programs add end_term smallint default 0;
alter table edu.programs add credits float default 0;
update edu.programs p set (start_term,end_term,credits)=(select mp.start_term,mp.end_term,mp.credits from edu.major_plans mp
where mp.program_id=p.id);

create table edu.program_course_outcomes (id bigint not null, course_id bigint, course_name varchar(255) not null, doc_id bigint not null, group_name varchar(255) not null, idx integer default 0 not null, outcomes varchar(50) not null);
create table edu.program_objectives (id bigint not null, code varchar(255) not null, contents varchar(100) not null, doc_id bigint not null, outcomes varchar(40) not null);
create table edu.program_outcomes (id bigint not null, contents varchar(4000) not null, doc_id bigint not null, idx integer default 0 not null, title varchar(255) not null);
create table edu.program_outlines (id integer not null, code varchar(255) not null, name varchar(255) not null);
create table edu.program_tables (id bigint not null, caption varchar(255) not null, contents varchar(10000) not null, doc_id bigint not null, name varchar(255) not null);
create table edu.program_texts (id bigint not null, contents varchar(4000) not null, doc_id bigint not null, link_table varchar(255), name varchar(255) not null, title varchar(255) not null);
alter table edu.program_course_outcomes add constraint pk_nvau4y9rvun2kbgatx4y0qhat primary key (id);
alter table edu.program_objectives add constraint pk_ejx91beni3lkiv6q1jo7gb1qn primary key (id);
alter table edu.program_outcomes add constraint pk_ad3rhlvff93jocwu172v2ua7j primary key (id);
alter table edu.program_outcomes alter column contents type varchar(4000);
alter table edu.program_outlines add constraint pk_rl1nqqsx91kr0k5to4csk0hxq primary key (id);
alter table edu.program_tables add constraint pk_ljn79y0w3bm6lr3nctrchnqv primary key (id);
alter table edu.program_texts add constraint pk_55uvopdrvnwndti8ayyrhpsc0 primary key (id);
alter table edu.program_course_outcomes add constraint fk_30oo9u3w8oosxj1u361qvmgou foreign key (course_id) references base.courses (id);
alter table edu.program_course_outcomes add constraint fk_ktrxakwxdaibvxdkyjatb2u8l foreign key (doc_id) references edu.program_docs (id);
alter table edu.program_objectives add constraint fk_6i92kd04faqrep3d09n2hrel2 foreign key (doc_id) references edu.program_docs (id);
alter table edu.program_outcomes add constraint fk_q4jxukvs7tkhwxmxe0c1o14ph foreign key (doc_id) references edu.program_docs (id);
alter table edu.program_tables add constraint fk_pie7l0pqb0xptybh3j5c2kb47 foreign key (doc_id) references edu.program_docs (id);
alter table edu.program_texts add constraint fk_9m9aw8lcs9ve08cs1wbvb48yv foreign key (doc_id) references edu.program_docs (id);
create index idx_tkx5sw4r528dewo84cr4kl2w2 on edu.program_course_outcomes (doc_id);
create index idx_2nskwcp75nhywhfvwra2o5tvr on edu.program_objectives (doc_id);
create index idx_e735gkuaellofhjbxsxixwlj7 on edu.program_outcomes (doc_id);
create index idx_6d6uejbklk3j5me187qk8nmc3 on edu.program_tables (doc_id);
create index idx_sjusvc5k3oltaoggtr9xb02jo on edu.program_texts (doc_id);

alter table base.minor_majors add column project_id integer;
alter table base.minor_majors alter project_id set not null;
alter table base.minor_majors add constraint fk_7nykr3h6yq9l7larsbsil1lbi foreign key (project_id) references base.projects (id);

alter table std.degree_results rename column degree_comments to remark;
alter table std.degree_results add column failed_items varchar(500);
alter table std.degree_results add column passed_items varchar(500);
alter table std.degree_results alter ga drop not null;
alter table std.degree_results alter gpa drop not null;
alter table std.degree_results alter passed drop default;
alter table std.degree_results alter passed drop not null;
alter table std.graduate_results rename column graduate_comments to remark;
alter table std.graduate_results add column failed_items varchar(500);
alter table std.graduate_results add column passed_items varchar(500);

create index idx_h6txa38ww6xmjierqkvw1u4d7 on std.degree_results (std_id);
create index idx_b4r23ej9bc55e8rkjosbder4w on std.graduate_results (std_id);

alter table edu.executive_plans drop end_term cascade;
alter table edu.executive_plans drop remark cascade;
alter table edu.executive_plans drop start_term cascade;
alter table edu.executive_plans drop status cascade;
alter table edu.major_plans drop end_term cascade;
alter table edu.major_plans drop remark cascade;
alter table edu.major_plans drop start_term cascade;
alter table edu.major_plans drop status cascade;
alter table edu.std_plans drop end_term cascade;
alter table edu.std_plans drop remark cascade;
alter table edu.std_plans drop start_term cascade;
alter table edu.std_plans drop status cascade;

comment on column base.minor_majors.project_id is '项目ID';
comment on table edu.program_course_outcomes is '课程对应课表目标@program';
comment on column edu.program_course_outcomes.id is '非业务主键:datetime';
comment on column edu.program_course_outcomes.course_id is '课程基本信息ID';
comment on column edu.program_course_outcomes.course_name is '课程名';
comment on column edu.program_course_outcomes.doc_id is '培养方案文档ID';
comment on column edu.program_course_outcomes.group_name is '分组';
comment on column edu.program_course_outcomes.idx is '顺序号';
comment on column edu.program_course_outcomes.outcomes is '对应毕业要求';
comment on table edu.program_objectives is '培养方案-培养目标@program';
comment on column edu.program_objectives.id is '非业务主键:datetime';
comment on column edu.program_objectives.code is '代码';
comment on column edu.program_objectives.contents is '内容';
comment on column edu.program_objectives.doc_id is '培养方案文档ID';
comment on column edu.program_objectives.outcomes is '对应毕业要求';
comment on table edu.program_outcomes is '培养方案-毕业要求@program';
comment on column edu.program_outcomes.id is '非业务主键:datetime';
comment on column edu.program_outcomes.contents is '毕业要求';
comment on column edu.program_outcomes.doc_id is '培养方案文档ID';
comment on column edu.program_outcomes.idx is '顺序号';
comment on column edu.program_outcomes.title is '标题';
comment on table edu.program_outlines is '培养方案大纲@program';
comment on column edu.program_outlines.id is '非业务主键:auto_increment';
comment on column edu.program_outlines.code is '代码';
comment on column edu.program_outlines.name is '名称';
comment on table edu.program_tables is '培养方案-表格@program';
comment on column edu.program_tables.id is '非业务主键:datetime';
comment on column edu.program_tables.caption is '标题';
comment on column edu.program_tables.contents is '内容';
comment on column edu.program_tables.doc_id is '培养方案文档ID';
comment on column edu.program_tables.name is '名称';
comment on table edu.program_texts is '培养方案-文本@program';
comment on column edu.program_texts.id is '非业务主键:datetime';
comment on column edu.program_texts.contents is '内容';
comment on column edu.program_texts.doc_id is '培养方案文档ID';
comment on column edu.program_texts.link_table is '链接表格的名称';
comment on column edu.program_texts.name is '名称';
comment on column edu.program_texts.title is '标题';
comment on column edu.lessons.exam is '是否考试';
comment on column edu.lessons.hours is '课时';
comment on column edu.programs.credits is '学分';
comment on column edu.programs.end_term is '结束学期';
comment on column edu.programs.start_term is '起始学期';
comment on column edu.syllabus_outcomes.idx is '顺序号';
comment on column edu.syllabus_outcomes.title is '标题';
comment on column edu.syllabus_topics.exam is '是否考试环节';
comment on column edu.syllabuses.credit_hours is '学时';
comment on column std.degree_results.failed_items is '不通过的项目';
comment on column std.degree_results.passed_items is '通过的项目';
comment on column std.degree_results.remark is '备注';
comment on column std.graduate_results.failed_items is '不通过的项目';
comment on column std.graduate_results.passed_items is '通过的项目';
comment on column std.graduate_results.remark is '备注';
