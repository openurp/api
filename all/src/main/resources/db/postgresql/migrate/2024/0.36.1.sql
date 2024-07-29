insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.36.1',now(),'简化学籍异动');

alter table base.graduate_seasons add column president varchar(20);

alter table base.students add column max_end_on date;
update base.students set max_end_on = end_on;
alter table base.students alter max_end_on set not null;

alter table std.std_alterations rename begin_on to alter_on;
alter table std.std_alterations drop code cascade;
alter table std.std_alterations drop end_on cascade;
alter table std.std_alterations add column doc_num varchar(255);

alter table base.classrooms drop constraint if exists uk_3rf701tm9q4qyua4q0ydybv95 cascade;
alter table base.classrooms add constraint uk_t23e7c95vph4sm7nva099kab0 unique (school_id,code);

alter table cfg.edu_cert_exempt_settings add column college_review_required boolean default false;
alter table cfg.edu_cert_exempt_settings alter college_review_required set not null;

alter table edu.course_grade_states add column excellent_ratio float4;
alter table edu.course_grade_states add column excellent_ratio_limit float4;
alter table edu.exam_grade_states add column excellent_ratio float4;
alter table edu.exam_grade_states add column excellent_ratio_limit float4;
alter table edu.ga_grade_states add column excellent_ratio float4;
alter table edu.ga_grade_states add column excellent_ratio_limit float4;

alter table edu.extern_exempt_applies add column semester_id integer;
update edu.extern_exempt_applies a set semester_id=(select s.id from base.semesters s where a.updated_at between s.begin_on and s.end_on);
alter table edu.extern_exempt_applies add constraint fk_101nfu3hyftmpl5xx0stuf1a4 foreign key (semester_id) references base.semesters (id);
alter table edu.extern_exempt_applies alter semester_id set not null;

comment on column base.graduate_seasons.president is '校长';
comment on column base.students.max_end_on is '学籍最长截止日期';
comment on column cfg.edu_cert_exempt_settings.college_review_required is '是否需要学院审核';
comment on column edu.course_grade_states.excellent_ratio is '优秀比例';
comment on column edu.course_grade_states.excellent_ratio_limit is '优秀比例限制';
comment on column edu.exam_grade_states.excellent_ratio is '优秀比例';
comment on column edu.exam_grade_states.excellent_ratio_limit is '优秀比例限制';
comment on column edu.extern_exempt_applies.semester_id is '学年学期ID';
comment on column edu.ga_grade_states.excellent_ratio is '优秀比例';
comment on column edu.ga_grade_states.excellent_ratio_limit is '优秀比例限制';
comment on column std.std_alterations.alter_on is '变动日期';
comment on column std.std_alterations.doc_num is '批准文号';
