insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.49.11',now(),'增加导师顺序号');

alter table base.student_tutors add column idx integer default 0;
alter table base.student_tutors alter idx set not null;
comment on column base.student_tutors.idx is '顺序号';
