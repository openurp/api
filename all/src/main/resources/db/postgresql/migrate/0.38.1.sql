insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.38.1',now(),'免修课程调整');

--course_types
alter table code.course_types add short_name varchar(20);

--projects
alter table base.projects add administration varchar(80) default '教务处';
alter table base.projects add administration2nd varchar(80);

alter table std.graduate_batches add enable_progress_confirm bool default false;

--flow
create schema flow;
alter table app.alternative_applies set schema flow;
alter table app.alternative_applies_olds set schema flow;
alter table app.alternative_applies_news set schema flow;

alter table flow.alternative_applies rename to edu_course_alt_applies;
alter table flow.alternative_applies_olds rename to edu_course_alt_applies_olds;
alter table flow.alternative_applies_news rename to edu_course_alt_applies_news;
alter table flow.edu_course_alt_applies_olds rename alternative_apply_id to course_alt_apply_id;
alter table flow.edu_course_alt_applies_news rename alternative_apply_id to course_alt_apply_id;

alter table app.course_type_change_applies set schema flow;
alter table flow.course_type_change_applies rename to edu_course_type_change_applies;
--exempt
alter table edu.exempt_courses add course_type_id int4;
update edu.exempt_courses ec set course_type_id = (select c.course_type_id from base.courses c where c.id=ec.course_id);
alter table edu.exempt_courses alter column course_type_id set not null;

create table edu.std_exempt_courses (course_id bigint not null, std_id bigint not null, id bigint not null, remark varchar(255), updated_at timestamptz default current_timestamp not null);
alter table edu.std_exempt_courses add constraint pk_pmf5t4ig9yr2d7l6cha1r2b53 primary key (id);
create index idx_56fbv4yt2d1wshrjys9mjfarf on edu.std_exempt_courses (std_id);

--std
alter table base.students add graduation_deferred bool default false;

--thesis
create table degree.thesis_archives (writer_id bigint not null, confirm_at timestamptz, confirmed_by_id bigint, confirmed boolean, feedback varchar(400), id bigint not null, updated_at timestamptz default current_timestamp not null);
alter table degree.thesis_archives add constraint pk_3w21a253lbmr3pu2cqxayq301 primary key (id);
alter table degree.thesis_archives add constraint uk_6ppgmacal52hjo8831um7qon8 unique (writer_id);
