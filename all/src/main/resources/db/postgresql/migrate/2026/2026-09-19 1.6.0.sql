insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'1.6.0',now(),'课程审核结果改用未通过后续途径');

alter table edu.audit_course_results add column pending_way integer;
--原 taking 同时表示在读和补缓考, 用备注区分出补缓考
update edu.audit_course_results set pending_way=1 where taking=true and remark not like '未出补缓考成绩%';
update edu.audit_course_results set pending_way=2 where taking=true and remark like '未出补缓考成绩%';
--原 predicted 为预计能通过(在读或毕业学年课程), taking=false 的即为毕业学年课程
update edu.audit_course_results set pending_way=3 where taking=false and predicted=true;
alter table edu.audit_course_results drop taking cascade;
alter table edu.audit_course_results drop predicted cascade;
comment on column edu.audit_course_results.pending_way is '未通过后续途径';
