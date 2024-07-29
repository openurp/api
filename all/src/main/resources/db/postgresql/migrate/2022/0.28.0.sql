insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.28.0',now(),'更好的支持项目属性');

alter table base.projects_properties rename to project_properties;
create view base.projects_properties as select * from base.project_properties;

alter table base.project_properties add type_name varchar(100);
alter table base.project_properties add description varchar(200);
alter table base.project_properties add id bigint;
update base.project_properties set id=datetime_id();

update base.project_properties set name='edu.grade.setting' ,type_name='json',description='成绩配置' where name='course.grade.setting';

insert into base.project_properties(id,project_id,name,value_,type_name,description)
values(datetime_id(),(select max(id) from base.projects),'edu.course.hours_per_credit','16','integer','每学分对应学时数');

insert into base.project_properties(id,project_id,name,value_,type_name,description)
values(datetime_id(),(select max(id) from base.projects),'edu.program.link_course_info_enabled','false','boolean','是否在计划预览界面链接课程简介');

insert into base.project_properties(id,project_id,name,value_,type_name,description)
values(datetime_id(),(select max(id) from base.projects),'edu.program.degree_gpa_supported','false','boolean','是否支持培养计划中的学位绩点');

insert into base.project_properties(id,project_id,name,value_,type_name,description)
values(datetime_id(),(select max(id) from base.projects),'edu.program.degree_course_supported','false','boolean','是否支持培养计划中的学位课程');

alter table base.project_properties alter column type_name set not null;
alter table base.project_properties alter column description set not null;

alter table base.project_properties drop constraint pk_k5gcer7xqcwvko6ou7lc985m8;
alter table base.project_properties add constraint pk_rfrpbcb39anra9nb0a5x1g6dd primary key (id);
alter table base.project_properties add constraint uk_1kgy8wfyw9wg1e28k8seg64se unique (project_id,name);
