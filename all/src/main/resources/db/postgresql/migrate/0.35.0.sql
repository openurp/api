insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.35.0',now(),'重构校外考试,增加考勤信息');

alter table edu.c_certificate_subjects rename to c_certificates;
alter table cfg.edu_cert_signup_settings rename column subject_id to certificate_id;
alter table cfg.edu_cert_exempt_settings rename column subject_id to certificate_id;
alter table cfg.edu_cert_signup_configs drop column opened;
alter table cfg.edu_cert_signup_configs drop column code;
alter table cfg.edu_cert_signup_configs drop column category_id;
alter table cfg.edu_cert_signup_configs rename column max_subject to max_options;

alter table edu.cert_exempt_applies rename column subject_id to certificate_id;
alter table edu.cert_signups rename column subject_id to certificate_id;
alter table edu.certificate_grades rename column subject_id to certificate_id;
alter table edu.certificate_grades add column subject varchar(40);
alter table edu.certificate_grades rename column certificate to certificate_no;
alter table edu.certificate_grades add column semester_id int4;

update  edu.certificate_grades  g set semester_id=
(select s.id from base.semesters s where g.acquired_on between s.begin_on-20 and s.end_on+20) where semester_id is null;

alter table edu.certificate_grades alter column semester_id set not null;

select * from edu.certificate_grades where semester_id is null limit 2;

alter table edu.std_dayoffs rename to std_leaves;
alter table edu.std_leaves rename column dayoff_type to leave_type;
