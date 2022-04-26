alter table edu.clazzes add column  audit_status int4;
update edu.clazzes set audit_status =
case when audit_state=2 then 100  when audit_state=3 then 99  else audit_state end;
--alter table edu.clazzes drop column audit_state;

---plans--------------
alter table edu.execution_plans add column  audit_status int4;
update edu.execution_plans set audit_status =
case when audit_state=2 then 100  when audit_state=3 then 99  else audit_state end;
--alter table edu.execution_plans drop column audit_state;

alter table edu.major_plans add column  audit_status int4;
update edu.major_plans set audit_status =
case when audit_state=2 then 100  when audit_state=3 then 99  else audit_state end;
--alter table edu.major_plans drop column audit_state;

alter table edu.std_plans add column  audit_status int4;
update edu.std_plans set audit_status =
case when audit_state=2 then 100  when audit_state=3 then 99  else audit_state end;
--alter table edu.std_plans drop column audit_state;

alter table edu.programs add column  audit_status int4;
update edu.programs set audit_status =
case when audit_state=2 then 100  when audit_state=3 then 99  else audit_state end;
--alter table edu.programs drop column audit_state;

--exchange-----------
alter table std.exchange_grades add column  audit_status int4;
update std.exchange_grades set audit_status =
case when audit_state=2 then 50  when audit_state=3 then 51  when audit_state=4 then 100  when audit_state=5 then 99 else audit_state end;
--alter table std.exchange_grades drop column audit_state;

alter table std.exemption_applies add column  audit_status int4;
update std.exemption_applies set audit_status =
case when audit_state=2 then 50  when audit_state=3 then 51  when audit_state=4 then 100  when audit_state=5 then 99 else audit_state end;
--alter table std.exemption_applies drop column audit_state;

alter table std.transfer_applies add column  audit_status int4;
update std.transfer_applies set audit_status =
case when audit_state=2 then 50  when audit_state=3 then 51  when audit_state=4 then 100  when audit_state=5 then 99 else audit_state end;
--alter table std.transfer_applies drop column audit_state;
