insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'1.4.6',now(),'将GPA浮点数改成固定精度的数据类型');

alter table edu.programs alter degree_gpa type numeric(10,5);
alter table edu.std_gpas alter ams type numeric(10,5);
alter table edu.std_gpas alter gpa type numeric(10,5);
alter table edu.std_gpas alter wms type numeric(10,5);
alter table edu.std_semester_gpas alter ams type numeric(10,5);
alter table edu.std_semester_gpas alter gpa type numeric(10,5);
alter table edu.std_semester_gpas alter wms type numeric(10,5);
alter table edu.std_year_gpas alter ams type numeric(10,5);
alter table edu.std_year_gpas alter gpa type numeric(10,5);
alter table edu.std_year_gpas alter wms type numeric(10,5);
alter table flow.std_grad_bachelor2nd_applies alter gpa type numeric(10,5);
alter table flow.std_grad_degree_applies alter gpa type numeric(10,5);
alter table std.degree_results alter gpa type numeric(10,5);
alter table std.graduations alter gpa type numeric(10,5);
alter table std.minor_signup_stds alter gpa type numeric(10,5);
alter table std.transfer_applies alter gpa type numeric(10,5);
alter table std.transfer_applies alter major_gpa type numeric(10,5);
alter table std.transfer_applies alter other_gpa type numeric(10,5);
alter table std.transfer_applies alter transfer_gpa type numeric(10,5);
