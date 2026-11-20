insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'1.6.5',now(),'增加无考试资格学院复核信息');

alter table edu.regular_assess_stats add column college_review jsonb default '{}'::jsonb;
update edu.regular_assess_stats set college_review = '{}'::jsonb where college_review is null;
alter table edu.regular_assess_stats alter college_review set not null;

comment on column edu.regular_assess_stats.college_review is '学院复核信息';
