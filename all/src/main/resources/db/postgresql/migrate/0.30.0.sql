alter table base.teachers drop column teacher_type_id;
alter table base.teachers drop column status_id;
alter table base.teachers drop column title_id;
alter table base.teachers drop column education_degree_id;
alter table base.teachers drop column degree_id;
alter table base.teachers drop column formal_hr;

alter table edu.restriction_items rename column include_in to included;
alter table base.courses rename column credits to default_credits;

drop table base.courses_Levels;
alter table base.courses_edulevels rename to courses_levels;
