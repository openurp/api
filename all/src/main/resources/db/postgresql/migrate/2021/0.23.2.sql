insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.23.2',now(),'毕业批次支持开始-结束日期');

alter table std.orders add invoice_path varchar(200);
update std.orders o set invoice_path=(select b.invoice_code from std.bills b where b.id=o.bill_id);
alter table std.bills drop column invoice_code;

alter table std.graduate_sessions rename graduate_on to begin_on;
alter table std.graduate_sessions add column end_on date;
update std.graduate_sessions set end_on = begin_on;

alter table edu.room_apply_final_checks rename column apply_id to room_apply_id;
alter table edu.room_apply_final_checks drop constraint fk_eb7l2qgiubakaqheycv4gkwck cascade;
alter table edu.room_apply_final_checks add constraint fk_4athjgnyrbex8davkcl5rdjo4 foreign key (room_apply_id) references edu.room_applies (id);
alter table edu.room_apply_depart_checks rename column apply_id to room_apply_id;
alter table edu.room_apply_depart_checks drop constraint fk_n1agt68977pli9i9lfb2lxyhr cascade;
alter table edu.room_apply_depart_checks add constraint fk_86o2677abn3whr0cn6hcfmf73 foreign key (room_apply_id) references edu.room_applies (id);

