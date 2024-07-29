insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.37.2',now(),'绩点采用双精度浮点数');

alter table edu.programs alter remark type varchar(500);
alter table edu.restriction_items alter contents type varchar(1000);
alter table edu.std_gpas alter ga type float8;
alter table edu.std_gpas alter gpa type float8;
alter table edu.std_semester_gpas alter ga type float8;
alter table edu.std_semester_gpas alter gpa type float8;
alter table edu.std_year_gpas alter ga type float8;
alter table edu.std_year_gpas alter gpa type float8;
