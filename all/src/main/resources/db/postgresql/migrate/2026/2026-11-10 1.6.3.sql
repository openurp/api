insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'1.6.3',now(),'增加无考试资格复核、取消日期及教师签名信息');

alter table edu.regular_assess_stats add column teacher_signature_path varchar(400);
alter table edu.regular_assess_stats add column teacher_submitted_at timestamptz;
alter table flow.edu_exam_defer_applies add column teacher_sign_url varchar(400);

comment on column edu.regular_assess_stats.teacher_signature_path is '教师签名存储路径';
comment on column edu.regular_assess_stats.teacher_submitted_at is '教师提交时间';
comment on column flow.edu_exam_defer_applies.teacher_sign_url is '任课教师审核签名url';

alter table edu.regular_assess_stds add column updated_at timestamptz default current_timestamp not null;

update edu.regular_assess_stds s
set updated_at = coalesce(st.teacher_submitted_at, st.updated_at)
from edu.regular_assess_stats st
where st.clazz_id = s.clazz_id;

comment on column edu.regular_assess_stds.updated_at is '取消日期';

alter table edu.regular_assess_stats add column college_review jsonb default '{}'::jsonb not null;

comment on column edu.regular_assess_stats.college_review is '学院复核信息';
