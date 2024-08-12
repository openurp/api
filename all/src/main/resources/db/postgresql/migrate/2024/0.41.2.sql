insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.41.2',now(),'删除课程类别的多余属性');

insert into base.course_journals(id,course_id,name,en_name,credit_hours,department_id,
exam_mode_id,week_hours,weeks,begin_on,end_on,updated_at)
select datetime_id(),id,name,en_name,credit_hours,department_id,
exam_mode_id,week_hours,weeks,begin_on,end_on,updated_at from base.courses c
where not exists(select * from base.course_journals cj where cj.course_id=c.id);
