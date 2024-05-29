alter table edu.syllabus_topics alter contents type varchar(4000);
alter table edu.syllabuses alter column materials type varchar(500);
alter table edu.syllabus_topic_elements alter contents type varchar(3000);

alter table edu.programs add start_term smallint default 0;
alter table edu.programs add end_term smallint default 0;
alter table edu.programs add credits float default 0;

update edu.programs p set (start_term,end_term,credits)=(select mp.start_term,mp.end_term,mp.credits from edu.major_plans mp
where mp.program_id=p.id);

alter table edu.syllabus_outcomes add code varchar(20);
alter table edu.syllabus_outcomes add title varchar(100);

update code.graduate_objectives set code = replace(code,'SR','R');
alter table edu.syllabus_outcomes alter column code set not null;
alter table edu.syllabus_outcomes alter column title set not null;

alter table edu.syllabuses alter column bibliography type varchar(2000);
