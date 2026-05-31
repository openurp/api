
alter table flow.edu_exam_defer_applies add attachments jsonb default '[]';

alter table flow.edu_exam_defer_applies rename status to status_text;
update flow.edu_exam_defer_applies set status=1 where status_text='已提交';

update flow.edu_exam_defer_applies  set status=99 where status_text ='审核不通过';
update flow.edu_exam_defer_applies  set status=100 where status_text ='审核通过';

select * from flow.edu_exam_defer_applies  where status is null;

alter table flow.edu_exam_defer_applies  drop column status_text;
