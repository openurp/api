insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.40.5',now(),'移动课程大纲中实践周');

alter table edu.syllabus_credit_hours rename to syllabus_hours;
alter table edu.syllabuses add weeks int4;
update edu.syllabuses s set weeks=(select sum(h.weeks) from edu.syllabus_hours h where h.syllabus_id=s.id);
update edu.syllabuses set weeks =null where weeks=0;
alter table  edu.syllabus_hours drop column weeks;

comment on column edu.syllabuses.weeks is '总实践周';
