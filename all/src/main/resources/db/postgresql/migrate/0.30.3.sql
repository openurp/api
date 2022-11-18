insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.30.3',now(),'指导老师增加联系方式');

alter table degree.advisors add mobile varchar(20);
alter table degree.advisors add email varchar(100);
update degree.advisors a set (email,mobile) = (select u.email,u.mobile from base.users u where u.id=a.user_id);

alter table degree.subject_applies add current_round int4 default 0;
alter table degree.advisors drop column subdecanal;

update degree.subject_applies sa set  current_round = 1 where sa.first_id is not null and sa.last_id is not null and sa.last_id = sa.first_id;
update degree.subject_applies sa set  current_round = 2 where sa.first_id is not null and sa.last_id is not null and sa.last_id <> sa.first_id;
update degree.subject_applies sa set  current_round = 99 where sa.second_id is not null and sa.last_id is not null and sa.last_id <> sa.second_id;

