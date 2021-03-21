
create table edu_program.program_doc_sections (id bigint not null, contents varchar(10000) not null, doc_id bigint not null, indexno varchar(255) not null, name varchar(255) not null, parent_id bigint);
comment on table edu_program.program_doc_sections is '培养方案文档章节';
comment on column edu_program.program_doc_sections.id is '非业务主键:datetime';
comment on column edu_program.program_doc_sections.contents is '内容';
comment on column edu_program.program_doc_sections.doc_id is '培养方案文档ID';
comment on column edu_program.program_doc_sections.indexno is '顺序号';
comment on column edu_program.program_doc_sections.name is '名称';
comment on column edu_program.program_doc_sections.parent_id is '培养方案文档章节ID';

alter table edu_program.program_doc_sections add constraint pk_7f7k9q32r86m55a0r93h2uflm primary key (id);
create index idx_jfn7je4lfhja46qtvdeka1dut on edu_program.program_doc_sections (parent_id);
create index idx_lkchplwh47vmakls9riujfgrw on edu_program.program_doc_sections (doc_id);
alter table edu_program.program_doc_sections add constraint fk_gg4dh1idchc0dpmdlftk6wd8t foreign key (parent_id) references edu_program.program_doc_sections (id);
alter table edu_program.program_doc_sections add constraint fk_46gbs7xngpyw938o4916n9soy foreign key (doc_id) references edu_program.program_docs (id);
create table edu_program.program_doc_metas (id integer not null, indexno varchar(255) not null, maxlength integer not null, name varchar(255) not null, template_id integer not null);
comment on table edu_program.program_doc_metas is '培养方案章节定义';
comment on column edu_program.program_doc_metas.id is '非业务主键:auto_increment';
comment on column edu_program.program_doc_metas.indexno is '章节编号';
comment on column edu_program.program_doc_metas.maxlength is '最大长度';
comment on column edu_program.program_doc_metas.name is '名称';
comment on column edu_program.program_doc_metas.template_id is '培养方案文档模板ID';

alter table edu_program.program_doc_metas add constraint pk_rt39x9dho1u8xtaov9jms7p94 primary key (id);
create index idx_d69hctw0adiwc375iducsqovm on edu_program.program_doc_metas (template_id);
alter table edu_program.program_doc_metas add constraint fk_m1vrqjy2yfp0tncdk6y0wdi5m foreign key (template_id) references edu_program.program_doc_templates (id);


create table edu_base.classrooms_projects (classroom_id bigint not null, project_id integer not null);
comment on table edu_base.classrooms_projects is '使用项目';
comment on column edu_base.classrooms_projects.classroom_id is '教室ID';
comment on column edu_base.classrooms_projects.project_id is '项目ID';
alter table edu_base.classrooms_projects add constraint pk_cjgojafxa077u3fb2vicargx primary key (classroom_id,project_id);
create index idx_3y1hwcx0xh0apfawnr099698f on edu_base.classrooms_projects (classroom_id);
alter table edu_base.classrooms_projects add constraint fk_fgiktidt9t5160vtodc3olj5j foreign key (classroom_id) references edu_base.classrooms (id);
alter table edu_base.classrooms_projects add constraint fk_i4kfwi4ifex3emo0xsda5iuk1 foreign key (project_id) references edu_base.projects (id);

insert into edu_base.classrooms_projects(classroom_id,project_id)
select r.id,r.project_id from edu_base.classrooms r;

--check data--------
alter table edu_base.squads alter end_on set not null;
comment on column edu_base.squads.end_on is '结束日期';

alter table edu_base.classrooms add column room_no varchar(20);
alter table edu_base.classrooms add column school_id integer;
alter table edu_base.classrooms add column capacity integer;
alter table edu_base.classrooms add column building_id integer;
alter table edu_base.classrooms add column floor_no integer;
update edu_base.classrooms c set room_no=(select r.code from base.rooms r where r.id=c.room_id);
update edu_base.classrooms c set capacity=(select r.capacity from base.rooms r where r.id=c.room_id);
update edu_base.classrooms set capacity=0 where capacity is null;
update edu_base.classrooms set floor_no=0 where floor_no is null;

update edu_base.classrooms c set building_id=(select r.building_id from base.rooms r where r.id=c.room_id);
update edu_base.classrooms c set floor_no=(select r.floor_no from base.rooms r where r.id=c.room_id);
update edu_base.classrooms c set school_id=(select r.school_id from base.rooms r where r.id=c.room_id);


alter table base.rooms drop capacity cascade;
alter table edu_base.classrooms drop project_id cascade;
alter table edu_base.classrooms alter school_id set not null;
alter table edu_base.classrooms alter floor_no set not null;
alter table edu_base.classrooms alter capacity set not null;
alter table edu_base.classrooms drop constraint fk_imfvwrw46p6vc698n7vl2305l cascade;
alter table edu_base.classrooms drop constraint fk_4msji5x9bfek4i65cmeqjpjgc cascade;
alter table edu_base.classrooms add constraint fk_p169e84csib3wbj8ipuh3omkg foreign key (building_id) references base.buildings (id);
alter table edu_base.classrooms add constraint fk_stm3c7u1s7l0t42gywsldaani foreign key (school_id) references base.schools (id);
alter table edu_base.classrooms drop constraint uk_odyfmsy18wrf9vl0xvhejepy8 cascade;

create schema edu_room;
create table edu_room.apply_depart_checks (id bigint not null, apply_id bigint not null, approved boolean not null, checked_at timestamp not null, checked_by_id bigint not null, opinions varchar(100));
comment on table edu_room.apply_depart_checks is '归口审核';
comment on column edu_room.apply_depart_checks.id is '非业务主键:datetime';
comment on column edu_room.apply_depart_checks.apply_id is '教室借用ID';
comment on column edu_room.apply_depart_checks.approved is '是否审核通过';
comment on column edu_room.apply_depart_checks.checked_at is '审核时间';
comment on column edu_room.apply_depart_checks.checked_by_id is '审核人ID';
comment on column edu_room.apply_depart_checks.opinions is '具体意见';
create table edu_room.room_applies (id bigint not null, activity_name varchar(255) not null, activity_type_id integer not null, applicant varchar(255) not null, apply_at timestamp not null, apply_by_id bigint not null, approved boolean, attendance varchar(255) not null, attendance_num integer not null, begin_on date not null, campus_id integer not null, depart_check_id bigint, department_id integer not null, email varchar(255), end_on date not null, final_check_id bigint, minutes integer not null, mobile varchar(255) not null, require_multimedia boolean not null, room_comment varchar(255), school_id integer not null, speaker varchar(255) not null, time_comment varchar(255), unit_attendance integer not null);

alter table edu_room.apply_depart_checks add constraint pk_6w1n3ng89llvco6c5gwu8js1d primary key (id);
alter table edu_room.apply_depart_checks add constraint fk_5rqqkq7gslufl9irv3flcdomg foreign key (apply_id) references edu_room.room_applies (id);
alter table edu_room.apply_depart_checks add constraint fk_6kai721b7f77w1syw0l85d1w6 foreign key (checked_by_id) references base.users (id);
comment on table edu_room.room_applies is '教室借用';
comment on column edu_room.room_applies.id is '非业务主键:datetime';
comment on column edu_room.room_applies.activity_name is '活动名称';
comment on column edu_room.room_applies.activity_type_id is '活动类型ID';
comment on column edu_room.room_applies.applicant is '借用人';
comment on column edu_room.room_applies.apply_at is '申请时间';
comment on column edu_room.room_applies.apply_by_id is '通用人员信息ID';
comment on column edu_room.room_applies.approved is '是否审核通过';
comment on column edu_room.room_applies.attendance is '出席对象';
comment on column edu_room.room_applies.attendance_num is '出席人数';
comment on column edu_room.room_applies.begin_on is '开始日期';
comment on column edu_room.room_applies.campus_id is '校区信息ID';
comment on column edu_room.room_applies.depart_check_id is '归口审核ID';
comment on column edu_room.room_applies.department_id is '借用人部门ID';
comment on column edu_room.room_applies.email is '电子邮件';
comment on column edu_room.room_applies.end_on is '结束日期';
comment on column edu_room.room_applies.final_check_id is '最终审核ID';
comment on column edu_room.room_applies.minutes is '借用时长(分钟)';
comment on column edu_room.room_applies.mobile is '移动电话';
comment on column edu_room.room_applies.require_multimedia is '是否使用多媒体设备';
comment on column edu_room.room_applies.room_comment is '借用场所要求';
comment on column edu_room.room_applies.school_id is '学校信息ID';
comment on column edu_room.room_applies.speaker is '主讲人';
comment on column edu_room.room_applies.time_comment is '借用时间要求';
comment on column edu_room.room_applies.unit_attendance is '每个教室单元需要的座位数';

alter table edu_room.room_applies add constraint pk_bc80rnoxs22g3i49cprrotgaq primary key (id);
alter table edu_room.room_applies add constraint fk_5c4e72iuk8knlqt9b2bnp49ur foreign key (activity_type_id) references code_hb.activity_types (id);
alter table edu_room.room_applies add constraint fk_bder5hiniytppyemmjpxbe26n foreign key (school_id) references base.schools (id);
alter table edu_room.room_applies add constraint fk_62jrt2d5fqdd1pemvaox7r5jq foreign key (department_id) references base.departments (id);
alter table edu_room.room_applies add constraint fk_f2bae216b0ksd7i7yubivtl0j foreign key (final_check_id) references edu_room.apply_final_checks (id);
alter table edu_room.room_applies add constraint fk_20id4cb1d1x6200p623rlmfvk foreign key (apply_by_id) references base.users (id);
alter table edu_room.room_applies add constraint fk_3aeoyormiqlanhahh275e24af foreign key (campus_id) references base.campuses (id);
alter table edu_room.room_applies add constraint fk_hrdbttbgm820xfqg28vby4m4c foreign key (depart_check_id) references edu_room.apply_depart_checks (id);


create table edu_room.available_times (id bigint not null, begin_at smallint not null, end_at smallint not null, project_id integer not null, room_id bigint not null, start_on date not null, updated_at timestamp not null, weekstate bigint not null);
comment on table edu_room.available_times is '教室可用时间';
comment on column edu_room.available_times.id is '非业务主键:datetime';
comment on column edu_room.available_times.begin_at is '开始时间';
comment on column edu_room.available_times.end_at is '结束时间';
comment on column edu_room.available_times.project_id is '项目ID';
comment on column edu_room.available_times.room_id is '教室ID';
comment on column edu_room.available_times.start_on is '开始日期';
comment on column edu_room.available_times.updated_at is '更新时间';
comment on column edu_room.available_times.weekstate is '周状态';
alter table edu_room.available_times add constraint pk_8110ur4lpu6jgidotr5o22x0b primary key (id);
alter table edu_room.available_times add constraint fk_nfxet639psyta7k9c4s6c7lgs foreign key (project_id) references edu_base.projects (id);
alter table edu_room.available_times add constraint fk_5dx5on9etwpt5ih0whvyjqow4 foreign key (room_id) references edu_base.classrooms (id);
create table edu_room.apply_final_checks (id bigint not null, apply_id bigint not null, approved boolean not null, checked_at timestamp not null, checked_by_id bigint not null, opinions varchar(100));
comment on table edu_room.apply_final_checks is '最终审核';
comment on column edu_room.apply_final_checks.id is '非业务主键:datetime';
comment on column edu_room.apply_final_checks.apply_id is '教室借用ID';
comment on column edu_room.apply_final_checks.approved is '是否审核通过';
comment on column edu_room.apply_final_checks.checked_at is '审核时间';
comment on column edu_room.apply_final_checks.checked_by_id is '审核人ID';
comment on column edu_room.apply_final_checks.opinions is '具体意见';
alter table edu_room.apply_final_checks add constraint pk_j60mj9ihq0fjm4jo49xfw9x2v primary key (id);
alter table edu_room.apply_final_checks add constraint fk_91digo316rq3atlerysskmecx foreign key (apply_id) references edu_room.room_applies (id);
alter table edu_room.apply_final_checks add constraint fk_2b8hsw3njvdb4advr7t5ad82b foreign key (checked_by_id) references base.users (id);
create table edu_room.room_applies_times (begin_at smallint not null, end_at smallint not null, room_apply_id bigint not null, start_on date not null, weekstate bigint not null);
comment on table edu_room.room_applies_times is '申请借用时间';
comment on column edu_room.room_applies_times.begin_at is '开始时间';
comment on column edu_room.room_applies_times.end_at is '结束时间';
comment on column edu_room.room_applies_times.room_apply_id is '教室借用ID';
comment on column edu_room.room_applies_times.start_on is '开始日期';
comment on column edu_room.room_applies_times.weekstate is '周状态';
alter table edu_room.room_applies_times add constraint pk_gut4id17joy6x8d5psmvc1pd2 primary key (room_apply_id,weekstate,begin_at,start_on,end_at);
create index idx_oaii80w561p28ji3r86pv8djo on edu_room.room_applies_times (room_apply_id);

alter table edu_room.room_applies_times add constraint fk_qpapnbbq74r8lwfljrwobal9j foreign key (room_apply_id) references edu_room.room_applies (id);

--occupancies-----------
alter table lg_room.occupancies set schema edu_room;
alter table edu_room.occupancies alter room_id type bigint;
update edu_room.occupancies o set room_id=(select r.id from edu_base.classrooms r where r.room_id=o.room_id);
alter table edu_base.classrooms drop room_id cascade;


select * from edu_room.occupancies o where not exists(select r.id from edu_base.classrooms r where r.room_id=o.room_id);

alter table edu_room.occupancies drop constraint fk_5tyfmv9xpwuh3qmtskpy62ah cascade;
alter table edu_room.occupancies add constraint fk_cxcnxdgl2tss3ved9eqe00oq9 foreign key (room_id) references edu_base.classrooms (id);

create table edu_clazz.std_course_abilities (id bigint not null, rate_id integer not null, score float4, std_id bigint not null, updated_at timestamp not null);
comment on table edu_clazz.std_course_abilities is '学生课程能力';
comment on column edu_clazz.std_course_abilities.id is '非业务主键:datetime';
comment on column edu_clazz.std_course_abilities.rate_id is '课程能力等级ID';
comment on column edu_clazz.std_course_abilities.score is '分数';
comment on column edu_clazz.std_course_abilities.std_id is '学籍信息实现ID';
comment on column edu_clazz.std_course_abilities.updated_at is '更新时间';
alter table edu_clazz.std_course_abilities add constraint pk_ctriq5lvqq54p8st9q7qkkg20 primary key (id);
alter table edu_clazz.std_course_abilities add constraint fk_7q7mrns33elrcpminu6waryj7 foreign key (std_id) references edu_base.students (id);
alter table edu_clazz.std_course_abilities add constraint fk_c7ddhnmuoymrsl7rj8dce3nmg foreign key (rate_id) references edu_base.course_ability_rates (id);
