insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.40.4',now(),'删除大纲的期末考核学时分布');

drop table edu.syllabus_exam_hours cascade;
alter table edu.syllabus_outcomes alter title type varchar(150);
alter table edu.syllabuses add column complete boolean default false;
alter table edu.syllabuses alter complete set not null;
comment on column edu.syllabuses.complete is '检查是否完整';
