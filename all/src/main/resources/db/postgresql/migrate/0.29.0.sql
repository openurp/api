--使用这个查询出来的值，一般是18
select * from base.project_properties where name='edu.course.hours_per_credit';

alter table edu.major_course_groups add credit_hours int4;
alter table edu.major_course_groups add hour_ratios varchar(20);
update edu.major_course_groups set credit_hours= credits*18;

alter table edu.execution_course_groups add credit_hours int4;
alter table edu.execution_course_groups add hour_ratios varchar(20);
update edu.execution_course_groups set credit_hours= credits*18;

alter table edu.std_course_groups add credit_hours int4;
alter table edu.std_course_groups add hour_ratios varchar(20);
update edu.std_course_groups set credit_hours= credits*18;

alter table hr.teacher_profiles add department_id int4;
alter table hr.teacher_profiles add gender_id int4;
alter table hr.teacher_profiles add teacher_type_id int4;
alter table hr.teacher_profiles add status_id int4;
alter table hr.teacher_profiles add id_type_id int4;
alter table hr.teacher_profiles add id_number varchar(20);
alter table hr.teacher_profiles add birthday date;
alter table hr.teacher_profiles add nation_id int4;
alter table hr.teacher_profiles add political_status_id int4;
alter table hr.teacher_profiles add title_id int4;
alter table hr.teacher_profiles add education_degree_id int4;
alter table hr.teacher_profiles add degree_level int4;
alter table hr.teacher_profiles add degree_id int4;
alter table hr.teacher_profiles add degree_award_by varchar(255);
alter table hr.teacher_profiles add formal_hr bool default true;
alter table hr.teacher_profiles add has_tqc bool default true;
alter table hr.teacher_profiles add tqc_number varchar(20);
alter table hr.teacher_profiles add oqc varchar(200);
alter table hr.teacher_profiles add homepage varchar(200);
alter table hr.teacher_profiles add organization varchar(200);

update hr.teacher_profiles tp set department_id=(select u.department_id from base.users u,base.teachers t where t.id=tp.teacher_id and t.user_id=u.id);
update hr.teacher_profiles tp set gender_id=(select u.gender_id from base.users u,base.teachers t where t.id=tp.teacher_id and t.user_id=u.id);
update hr.teacher_profiles tp set teacher_type_id=(select t.teacher_type_id from  base.teachers t where t.id=tp.teacher_id);
update hr.teacher_profiles tp set status_id=(select t.status_id from  base.teachers t where t.id=tp.teacher_id);
update hr.teacher_profiles tp set id_number=(select p.code from base.people p,base.teachers t where t.id=tp.teacher_id and t.person_id=p.id);
update hr.teacher_profiles tp set birthday=(select p.birthday from base.people p,base.teachers t where t.id=tp.teacher_id and t.person_id=p.id);
update hr.teacher_profiles tp set nation_id=(select p.nation_id from base.people p,base.teachers t where t.id=tp.teacher_id and t.person_id=p.id);
update hr.teacher_profiles tp set political_status_id=(select p.political_status_id from base.people p,base.teachers t where t.id=tp.teacher_id and t.person_id=p.id);
update hr.teacher_profiles tp set title_id=(select t.title_id from  base.teachers t where t.id=tp.teacher_id);
update hr.teacher_profiles tp set education_degree_id=(select t.education_degree_id from  base.teachers t where t.id=tp.teacher_id);
update hr.teacher_profiles tp set degree_id=(select t.degree_id from  base.teachers t where t.id=tp.teacher_id);
update hr.teacher_profiles tp set formal_hr=(select t.formal_hr from  base.teachers t where t.id=tp.teacher_id);

INSERT INTO code.degree_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (1, '1', '学士学位', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.degree_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (2, '2', '硕士学位', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);
INSERT INTO code.degree_levels (id, code, name, en_name, begin_on, end_on, updated_at, remark) VALUES (3, '3', '博士学位', NULL, '2015-06-23', NULL, '2015-06-23 00:00:00', NULL);

alter table base.teachers add code varchar(20);
alter table base.teachers add name varchar(100);
alter table base.teachers add gender_id int4;

update base.teachers t set (code,name,gender_id)=(select u.code,u.name,u.gender_id from base.users u where u.id=t.user_id);
alter table base.teachers drop constraint uk_cd1k6xwg9jqtiwx9ybnxpmoh9;
alter table base.teachers alter column user_id drop not null;

alter table base.teachers add constraint fk_jon7fgny0jq3cmx8p1hhnkm48 foreign key (gender_id) references code.genders (id);
alter table hr.teacher_profiles add constraint fk_4j4l7pt61dsk9y36r7q77bt83 foreign key (political_status_id) references code.political_statuses (id);
alter table hr.teacher_profiles add constraint fk_6q43qwvbj11y88887clckx00b foreign key (status_id) references code.work_statuses (id);
alter table hr.teacher_profiles add constraint fk_72ddm4j70d4l6cuu547kse5mr foreign key (id_type_id) references code.id_types (id);
alter table hr.teacher_profiles add constraint fk_9k0u12l9wf6io948ll27borf0 foreign key (department_id) references base.departments (id);
alter table hr.teacher_profiles add constraint fk_a7mw7rrwq4ikp543v4a98lmwl foreign key (gender_id) references code.genders (id);
alter table hr.teacher_profiles add constraint fk_c3cjf6p1mmqtm5obrnjncv26m foreign key (teacher_type_id) references base.c_teacher_types (id);
alter table hr.teacher_profiles add constraint fk_epr5jcirvvhtmfck0miremiyg foreign key (nation_id) references code.nations (id);
alter table hr.teacher_profiles add constraint fk_kju81hx73juocdbum6bse9616 foreign key (degree_id) references code.degrees (id);
alter table hr.teacher_profiles add constraint fk_ms8kw00tamwnxdns4blv0m34f foreign key (education_degree_id) references code.education_degrees (id);
alter table hr.teacher_profiles add constraint fk_toxox3o9xlmdxtxfhukydutqd foreign key (title_id) references code.professional_titles (id);

alter table base.teachers drop column teacher_type_id;
alter table base.teachers drop column status_id;
alter table base.teachers drop column title_id;
alter table base.teachers drop column education_degree_id;
alter table base.teachers drop column degree_id;
alter table base.teachers drop column formal_hr;

alter table edu.major_alt_courses alter column level_id drop not null;
alter table edu.major_alt_courses drop column level_id;

create table edu.clazzes_levels (clazz_id bigint not null, education_level_id integer not null);
alter table edu.clazzes_levels add constraint pk_i68q454w5kfdeuq347y3g80b5 primary key (clazz_id,education_level_id);

create table base.courses_level_credits (course_id bigint not null, value_ float4 not null, education_level_id integer not null);
alter table base.courses_level_credits add constraint pk_rp78tpdutg1g4rg1d9uf4wgr9 primary key (course_id,education_level_id);

create table base.courses_levels2 (course_id bigint not null, education_level_id integer not null);
insert into base.courses_levels2
select cl.course_id,l.id from base.courses_levels cl,code.education_levels l where cl.academic_level_id=l.to_level_id;

drop table base.courses_Levels;
alter table base.courses_levels2 rename to courses_levels;

alter table edu.restriction_items rename column include_in to included;
alter table base.courses rename column credits to default_credits;
