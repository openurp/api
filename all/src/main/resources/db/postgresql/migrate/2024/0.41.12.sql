insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.41.11',now(),'论文作者增加研究方向');

alter table code.political_statuses add column short_name varchar(255);
alter table degree.thesis_checks alter research_field type varchar(100);
alter table degree.thesis_checks alter title type varchar(300);
alter table degree.thesis_papers alter research_field type varchar(100);
alter table degree.thesis_papers alter title type varchar(300);
alter table degree.writers alter thesis_title type varchar(300);
comment on column code.political_statuses.short_name is '简称';
comment on column degree.writers.research_field is '研究方向';

alter table degree.writers add column research_field varchar(100);
update degree.writers w set research_field=(select p.research_field from degree.thesis_papers p where p.writer_id=w.id);

update degree.writers w set research_field=
	(select s.research_field from degree.subject_applies p,degree.subjects s where p.writer_id=w.id and p.last_id=s.id)
where research_field is null;


