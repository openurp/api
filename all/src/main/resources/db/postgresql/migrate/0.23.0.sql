insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.23.0',now(),'更名考务教室分组和分配表');

alter table edu.room_groups rename to exam_room_groups;
alter table edu.room_groups_rooms rename to exam_room_groups_rooms;
alter table edu.exam_room_groups_rooms rename column room_group_id to exam_room_group_id;
alter table edu.room_alloc_settings rename to exam_alloc_settings;
