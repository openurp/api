alter table edu_base.squads add column en_name varchar(255);

alter schema std_job rename to stu_job;
alter schema edu_award rename to stu_award;
alter schema edu_innovation rename to prac_innovation;
alter schema edu_fee rename to std_fee;

create schema std_register;
create schema std_alter;
create schema std_transfer;
create schema std_info;

alter table edu_student.registers set schema std_register;

alter table edu_student.transfer_applies set schema std_transfer;
alter table edu_student.transfer_options set schema std_transfer;
alter table edu_student.transfer_schemes set schema std_transfer;
alter table edu_student.transfer_scopes set schema std_transfer;
alter table edu_student.transfer_scopes_grades set schema std_transfer;
alter table edu_student.transfer_scopes_majors set schema std_transfer;

alter table edu_student.admissions set schema std_info;
alter table edu_student.contacts set schema std_info;
alter table edu_student.graduations set schema std_info;
alter table edu_student.education_records set schema std_info;
alter table edu_student.examinees set schema std_info;
alter table edu_student.examinees_scores set schema std_info;
alter table edu_student.homes set schema std_info;
alter table edu_student.social_relations set schema std_info;

alter table edu_student.std_alteration_items set schema std_alter;
alter table edu_student.std_alterations set schema std_alter;

--make sure edu_student schema is empty,then drop it.
--DROP schema edu_student cascade;
