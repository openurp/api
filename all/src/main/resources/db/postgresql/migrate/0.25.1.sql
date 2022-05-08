insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.25.1',now(),'修复毕业界别命名错误');

alter table std.graduate_sessions rename gaduate_grade_id to graduate_grade_id;
