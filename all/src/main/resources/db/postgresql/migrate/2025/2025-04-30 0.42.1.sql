insert into base.versions(id,version,updated_at,description) values(next_id('base.versions'),'0.42.1',now(),'完善教材选用');

create table base.course_profiles_books (course_profile_id bigint not null, textbook_id bigint not null);
create table edu.clazzes_books (clazz_id bigint not null, textbook_id bigint not null);
alter table base.course_profiles add column book_adoption integer default 0;
alter table base.course_profiles alter book_adoption set not null;
alter table base.course_profiles add column submit_at timestamptz;
alter table edu.clazzes add column course_profile_id bigint;
alter table edu.syllabuses add column course_profile_id bigint;

alter table base.course_profiles_books add constraint pk_mi2bbaqlh7saryv4xvnugl2du primary key (course_profile_id,textbook_id);
alter table edu.clazzes_books add constraint pk_blgbfvis5pagursyudqrjkkaf primary key (clazz_id,textbook_id);
alter table base.course_profiles_books add constraint fk_ec8h93pedbvuk37imgo1q4r02 foreign key (course_profile_id) references base.course_profiles (id);
alter table base.course_profiles_books add constraint fk_cj7moo3jd8hjihwqaw8p3nfdi foreign key (textbook_id) references base.textbooks (id);
alter table edu.clazzes_books add constraint fk_aa7corpgdoxtfdquwlxdc73n foreign key (clazz_id) references edu.clazzes (id);
alter table edu.clazzes_books add constraint fk_iqaoim3gfr6vl3ripj8p3mjjp foreign key (textbook_id) references base.textbooks (id);
create index idx_h55h1s4mtw979tqx7jqam6p4h on base.course_profiles_books (course_profile_id);
create index idx_k0yuoagy6nvrf7ofks8j0dyg5 on edu.clazzes_books (clazz_id);

comment on table base.course_profiles_books is '课程简介-教材列表@edu';
comment on column base.course_profiles_books.course_profile_id is '课程简介ID';
comment on column base.course_profiles_books.textbook_id is '教材ID';
comment on column base.course_profiles.book_adoption is '教材选用类型';
comment on column base.course_profiles.submit_at is '提及时间';
comment on table edu.clazzes_books is '教学任务-对应教材@clazz';
comment on column edu.clazzes_books.clazz_id is '教学任务ID';
comment on column edu.clazzes_books.textbook_id is '教材ID';
comment on column edu.clazzes.course_profile_id is '课程介绍ID';
comment on column edu.syllabuses.course_profile_id is '课程介绍ID';
