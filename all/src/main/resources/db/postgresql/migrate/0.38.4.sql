insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.38.4',now(),'优化授课计划');

alter table degree.thesis_reviews add course_grade_synced boolean default false;
alter table base.graduate_seasons add graduate_on date;
update base.graduate_seasons set graduate_on=to_date(graduate_year::varchar||'06','yyyyMM');
