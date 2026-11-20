insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'1.6.6',now(),'无考试资格学院复核信息设置默认值');

alter table edu.regular_assess_stats alter column college_review set default '{}'::jsonb;
