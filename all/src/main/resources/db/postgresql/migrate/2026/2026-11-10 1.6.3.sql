insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'1.6.3',now(),'增加无考试资格和缓考审核教师签名路径');

alter table edu.regular_assess_stats add column teacher_signature_path varchar(400);
alter table edu.regular_assess_stats add column teacher_submitted_at timestamptz;
alter table flow.edu_exam_defer_applies add column teacher_sign_url varchar(400);

comment on column edu.regular_assess_stats.teacher_signature_path is '教师签名存储路径';
comment on column edu.regular_assess_stats.teacher_submitted_at is '教师提交时间';
comment on column flow.edu_exam_defer_applies.teacher_sign_url is '任课教师审核签名url';
