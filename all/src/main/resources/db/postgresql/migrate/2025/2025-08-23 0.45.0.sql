insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.45.0',now(),'增加学年度，重构平时成绩');

alter table base.course_journal_hours add constraint uk_ke2wt12wi4g02ypp3i7med47s unique (journal_id,nature_id);

--add school years
create table base.school_years (id integer not null, archived boolean default false not null, calendar_id integer not null, name varchar(10) not null, start_year integer default 0 not null);
insert into base.school_years(id,calendar_id,start_year,name)
select ROW_NUMBER () over(order by calendar_id,school_year) i,calendar_id,substr(school_year,1,4)::int4,school_year from
(select distinct  school_year,calendar_id from base.semesters  order by calendar_id,school_year)a
where not exists(select * from base.school_years s where s.calendar_id = a.calendar_id and s.name =a.school_year);

alter table base.semesters add year_id int4;
update base.semesters s set year_id = (select y.id from base.school_years y where y.calendar_id=s.calendar_id and y.name=s.school_year);
alter table base.semesters alter column year_id set not null;

alter table base.school_years add constraint pk_r2s3qxwae2iuu16m6foo51mci primary key (id);
alter table base.school_years add constraint fk_985wpou25j9ln7eqhx78pkkxr foreign key (calendar_id) references base.calendars (id);
alter table base.semesters add constraint fk_rctbi42phvs26hbnar00a3d02 foreign key (year_id) references base.school_years (id);

alter table base.semesters drop archived cascade;
alter table base.semesters drop school_year cascade;

--regular grade state
alter table edu.regular_grade_states add column components_json jsonb;

update edu.regular_grade_states s set components_json =
(select json_agg(json_build_object('name',rc.name,'percent',item.score_percent))
from edu.regular_components rc,edu.regular_grade_state_items item
where item.state_id=s.id and item.component_id=rc.id)
where exists(select * from edu.regular_grade_state_items  item where item.state_id=s.id);

update edu.regular_grade_states s set components_json = '[]'
where not exists(select * from edu.regular_grade_state_items  item where item.state_id=s.id);
alter table edu.regular_grade_states alter components_json set not null;

--regular grade
alter table edu.regular_grades add column tests_json jsonb;

update edu.regular_grades s set tests_json =
(select json_agg(json_build_object('name',rc.name,'percent',item.score_percent,'score',item.score))
from edu.regular_components rc,edu.regular_grade_items item
where item.grade_id=s.id and item.component_id=rc.id)
where exists(select * from edu.regular_grade_items item where item.grade_id=s.id);

update edu.regular_grades s set tests_json ='[]'
where not exists(select * from edu.regular_grade_items item where item.grade_id=s.id);

alter table edu.regular_grades alter tests_json set not null;

--his regular grade
alter table his.edu_regular_grades drop status cascade;
alter table his.edu_regular_grades add column tests_json jsonb;

update his.edu_regular_grades s set tests_json =
(select json_agg(json_build_object('name',rc.name,'percent',item.score_percent,'score',item.score))
from edu.regular_components rc,his.edu_regular_grade_items item
where item.grade_id=s.id and item.component_id=rc.id)
where exists(select * from his.edu_regular_grade_items item where item.grade_id=s.id);

update his.edu_regular_grades s set tests_json ='[]'
where not exists(select * from his.edu_regular_grade_items item where item.grade_id=s.id);

alter table his.edu_regular_grades alter tests_json set not null;

--DROP table
drop table edu.regular_components cascade;
drop table edu.regular_grade_items cascade;
drop table edu.regular_grade_state_items cascade;
drop table his.edu_regular_test_grades cascade;

--comments
comment on table base.school_years is '学年度@common';
comment on column base.school_years.id is '非业务主键:auto_increment';
comment on column base.school_years.archived is '是否归档';
comment on column base.school_years.calendar_id is '日历方案ID';
comment on column base.school_years.name is '名称';
comment on column base.school_years.start_year is '起始年份';
comment on column base.semesters.year_id is '学年度ID';
comment on column edu.regular_grade_states.components_json is '百分比设置';
comment on column edu.regular_grades.tests_json is '测试分数详情';
comment on column his.edu_regular_grades.tests_json is '测试分数详情';
