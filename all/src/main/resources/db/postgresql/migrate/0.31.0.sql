alter table base.graduate_grades rename to graduate_seasons;
alter table std.graduate_sessions rename to graduate_batches;

alter table degree.writers rename grade_id to season_id;
alter table degree.thesis_plans rename grade_id to season_id;
alter table degree.subjects rename grade_id to season_id;
alter table base.graduates rename grade_id to season_id;
alter table std.graduate_batches rename grade_id to season_id;
alter table std.graduate_batches rename graduate_grade_id to season_id;

alter table std.degree_applies rename session_id to batch_id;
alter table std.degree_results rename session_id to batch_id;
alter table std.graduate_results rename session_id to batch_id;

create table edu.clazz_bulletins (contents varchar(1000), id bigint not null, contact_channel varchar(150), clazz_id bigint not null, contact_qrcode_path varchar(300));
alter table edu.clazz_bulletins add constraint pk_qr18drp7kdfhmd4be5fgqvs2o primary key (id);
alter table edu.clazz_bulletins add constraint uk_bw61pgrg2iw0irmhs8hof0q0g unique (clazz_id);

--course cluster
create table base.course_clusters (name varchar(100) not null, project_id integer not null, code varchar(20) not null,
 id bigint not null, target_id bigint, component boolean not null, edu_type_id integer not null, level_id integer not null);
alter table base.course_clusters add constraint pk_6gjj4kp1e06ob51efr5det0ot primary key (id);
alter table base.course_clusters add constraint uk_jvovhm8hp7ph6s50s7fvq90wt unique (project_id,code);
alter table base.courses add constraint fk_b3oqgd1pw427cuyrtoi5c3794 foreign key (cluster_id) references base.course_clusters (id);
alter table base.course_clusters add constraint fk_dpwqno5uhcsw2c03pjel3kf9c foreign key (target_id) references base.courses (id);
alter table base.course_clusters add constraint fk_nwdxeyujck948jylsxhumiblc foreign key (level_id) references code.education_levels (id);
alter table base.course_clusters add constraint fk_s3wlxtejuii2qiubn7r49ichl foreign key (project_id) references base.projects (id);
alter table base.course_clusters add constraint fk_sweltcp74ihdx1pajo1re38st foreign key (edu_type_id) references base.c_education_types (id);
