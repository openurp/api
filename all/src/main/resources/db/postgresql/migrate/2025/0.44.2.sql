insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.44.2',now(),'增加小课教学');

--course directors
alter table edu.course_tasks add idx smallint default 1 not null;
alter table edu.course_tasks drop constraint uk_3frid92ox3grog2rfffm12lci cascade;
alter table edu.course_tasks add constraint uk_788vbx6k6ka93qc211s8sg70v unique (semester_id,course_id,idx);

