alter table edu.exam_activities add centralized boolean;
alter table edu.exam_tasks add centralized boolean;

update edu.exam_activities set centralized = false where depart_arranged=true;
update edu.exam_activities set centralized = true where depart_arranged=false;

update edu.exam_tasks set centralized = false where depart_arranged=true;
update edu.exam_tasks set centralized = true where depart_arranged=false;

alter table edu.exam_activities drop column depart_arranged;
alter table edu.exam_tasks drop column depart_arranged;
