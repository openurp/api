insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.41.8',now(),'增加教案明细和优秀毕业论文支持');

alter table base.courses drop extra_hours cascade;
alter table degree.subjects add column research_field varchar(255);
alter table degree.thesis_checks add column cover_doc_id bigint;
alter table degree.thesis_checks add column examinee_code varchar(255);
alter table degree.thesis_papers add column excellent boolean default false;
alter table degree.thesis_papers alter excellent set not null;
alter table edu.clazz_plans add column extra_hours integer default 0;
alter table edu.clazz_plans alter extra_hours set not null;
alter table edu.clazz_programs add column design_count smallint default 0;
alter table edu.clazz_programs alter design_count set not null;
alter table edu.clazz_programs add column lesson_count smallint default 0;
alter table edu.clazz_programs alter lesson_count set not null;
alter table edu.course_tasks add column confirmed boolean default false;
alter table edu.course_tasks alter confirmed set not null;
alter table edu.course_tasks add column extra_hours integer;
alter table edu.lesson_design_sections alter details type varchar(50000);
alter table edu.lesson_design_sections alter summary type varchar(50000);
alter table edu.lesson_designs add column lesson_on date;
alter table edu.lesson_designs alter lesson_on set not null;
alter table edu.lesson_designs add column units integer;
alter table edu.lesson_designs alter units set not null;
alter table edu.lesson_designs alter homework type varchar(500);
alter table edu.lesson_designs alter subject type varchar(300);
alter table degree.thesis_checks drop constraint if exists fk_qrfjf2f7wb8irycu5c7cjf70l cascade;
alter table degree.thesis_checks add constraint fk_15nlot5p3f4frhgl13rpt3e5m foreign key (cover_doc_id) references degree.thesis_docs (id);
alter table degree.thesis_checks add constraint fk_jl08bb9bx4xtmmqaq7iyapwjp foreign key (paper_doc_id) references degree.thesis_docs (id);
alter table degree.thesis_checks drop constraint if exists uk_ogbi6lgf5f4fvxmghph3h9irs cascade;
alter table degree.thesis_checks add constraint uk_8vgla3u0x7jq1whmak4w9in13 unique (season_id,writer_id,degree_major_code);
alter table edu.syllabus_objectives add constraint uk_qcf83a87e0bfew53ny7n8wyrt unique (syllabus_id,code);
comment on column degree.subjects.research_field is '研究方向';
comment on column degree.thesis_checks.cover_doc_id is '学位论文相关文档ID';
comment on column degree.thesis_checks.examinee_code is '考生号';
comment on column degree.thesis_papers.excellent is '是否优秀毕业论文';
comment on column edu.clazz_plans.extra_hours is '课外学时';
comment on column edu.clazz_programs.design_count is '已填写次数';
comment on column edu.clazz_programs.lesson_count is '上课次数';
comment on column edu.course_tasks.confirmed is '是否确认';
comment on column edu.course_tasks.extra_hours is '课外学时';
comment on column edu.lesson_designs.lesson_on is '上课日期';
comment on column edu.lesson_designs.units is '节次';