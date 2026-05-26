insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'1.4.3',now(),'重构毕业缺学分');

alter table edu.audit_group_results drop owed_credits2 cascade;
alter table edu.audit_plan_results drop owed_credits2 cascade;

alter table edu.audit_group_results rename column owed_credits3 to owed_credits2;
alter table edu.audit_plan_results rename column owed_credits3 to owed_credits2;

alter table std.graduations alter degree_applied set default false;
alter table std.graduations alter degree_applied set not null;
