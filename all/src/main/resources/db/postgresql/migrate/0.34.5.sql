insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.34.5',now(),'扩充毕业信息');

alter table base.graduates add column certificate_seq_no varchar(50);
alter table base.graduates alter certificate_no type varchar(100);
alter table base.graduates alter diploma_no type varchar(100);

create table cfg.edu_cert_exempt_configs_levels (cert_exempt_config_id bigint not null, education_level_id integer not null);
insert into cfg.edu_cert_exempt_configs_levels(cert_exempt_config_id,education_level_id)
select id,level_id from cfg.edu_cert_exempt_configs;

alter table cfg.edu_cert_exempt_configs drop level_id cascade;
alter table cfg.edu_cert_exempt_configs drop semester_id cascade;
alter table edu.course_audit_results alter remark type varchar(100);
alter table edu.occupancies alter comments type varchar(400);
alter table edu.room_applies alter room_comment type varchar(1200);
alter table cfg.edu_cert_exempt_configs_levels add constraint pk_tf2xqvguag0l8xh1e8khnwgux primary key (cert_exempt_config_id,education_level_id);
alter table cfg.edu_cert_exempt_configs_levels add constraint fk_hskk72xjk64g1vyv98ncdpl5 foreign key (cert_exempt_config_id) references cfg.edu_cert_exempt_configs (id);
alter table cfg.edu_cert_exempt_configs_levels add constraint fk_9l4k70sid7r5y919hdv3mkr24 foreign key (education_level_id) references code.education_levels (id);
alter table cfg.edu_cert_exempt_configs drop constraint if exists fk_2q8vw5com0vfbgn5mnyf3j8ld cascade;
alter table cfg.edu_cert_exempt_configs drop constraint if exists fk_glgadjb064njhibmc3l8hjvl4 cascade;
create index idx_hcbg5wtcik11yo0iqe4do7w5u on cfg.edu_cert_exempt_configs_levels (cert_exempt_config_id);
comment on column base.graduates.certificate_seq_no is '毕业证书序列号';
comment on table cfg.edu_cert_exempt_configs_levels is '培养层次@exempt.config';
comment on column cfg.edu_cert_exempt_configs_levels.cert_exempt_config_id is '校外考试免修设置ID';
comment on column cfg.edu_cert_exempt_configs_levels.education_level_id is '培养层次ID';
