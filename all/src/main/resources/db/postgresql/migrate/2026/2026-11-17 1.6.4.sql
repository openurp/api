insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'1.6.4',now(),'增加取消考试资格日期');

alter table edu.regular_assess_stds add column updated_at timestamptz default current_timestamp not null;

update edu.regular_assess_stds s
set updated_at = coalesce(st.teacher_submitted_at, st.updated_at)
from edu.regular_assess_stats st
where st.clazz_id = s.clazz_id;

comment on column edu.regular_assess_stds.updated_at is '取消日期';
