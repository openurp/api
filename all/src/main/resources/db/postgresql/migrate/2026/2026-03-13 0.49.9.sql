insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.49.9',now(),'增加子课程支持');

alter table base.courses add column sub_course_id bigint;
alter table base.courses add column terms integer default 0;
alter table base.courses alter terms set not null;
alter table base.courses add constraint fk_1vt6w3pyso2t60vtyw49y0fuo foreign key (sub_course_id) references base.courses (id);
comment on column base.courses.sub_course_id is '子课程ID';
comment on column base.courses.terms is '开课学期数';
