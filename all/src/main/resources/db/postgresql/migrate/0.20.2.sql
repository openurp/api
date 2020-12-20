alter table edu.user_apps rename to room_occupy_apps;
alter table edu.clazzes alter exam_form_id drop not null;
alter table edu.exam_activities alter exam_on drop not null;
alter table edu.programs alter offset_type_id drop not null;
