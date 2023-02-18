insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.22.2',now(),'更名教室借用审核表');

alter table edu.available_times rename to room_available_times;
create table edu.room_applies_rooms (classroom_id bigint not null, room_apply_id bigint not null);
comment on table edu.room_applies_rooms is '已分配教室';
comment on column edu.room_applies_rooms.classroom_id is '教室ID';
comment on column edu.room_applies_rooms.room_apply_id is '教室借用ID';
alter table edu.room_applies_rooms add constraint pk_f3isa2ggycb2cyjdu9cg3ow5q primary key (room_apply_id,classroom_id);
create index idx_2ofh2k9b4pj51ftjaf0e9oj3s on edu.room_applies_rooms (room_apply_id);
alter table edu.room_applies_rooms add constraint fk_nu4r5htixx20aou9bo8ep23bb foreign key (room_apply_id) references edu.room_applies (id);
alter table edu.room_applies_rooms add constraint fk_6ocigqxbksqi0cyosbbo2h91s foreign key (classroom_id) references base.classrooms (id);

alter table edu.apply_depart_checks rename to room_apply_depart_checks;
alter table edu.apply_final_checks rename to room_apply_final_checks;

create index idx_9bwygbyci888eug6sjs5ihbqq on edu.clazzes (project_id, semester_id, teach_depart_id);

alter table base.course_categories rename to course_assess_categories;
alter table base.course_types add column parent_id integer;
alter table base.course_types add constraint fk_smb9je2ssiv6o3gshdjvc5b42 foreign key (parent_id) references base.course_types (id);
