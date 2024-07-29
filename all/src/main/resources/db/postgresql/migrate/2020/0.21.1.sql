insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.21.1',now(),'调整辅修、增加教师任教院系');

drop table std.minor_schools cascade;
alter table std.minors rename column major_catalog_id to major_category_id;
alter table std.minors drop constraint fk_muu7y8ts33ny1fsshso4pkkrc cascade;
alter table std.minors drop constraint fk_5vsmx94glpctw9pby3bqeb9bd cascade;
alter table std.minors add constraint fk_8xpnw1gcc5ha7wne9fknnwq6r foreign key (school_id) references code.institutions (id);
alter table std.minors add constraint fk_gmmjfmmugool9fya0o2r9ba0h foreign key (major_category_id) references code.discipline_categories (id);
alter table base.teachers add column department_id integer;
alter table base.teachers add constraint fk_66bgaf2haj7wrne27r25ywhe3 foreign key (department_id) references base.departments (id);
update base.teachers t set department_id=(select u.department_id from base.users u where u.id=t.user_id);
alter table base.teachers alter department_id set not null;
