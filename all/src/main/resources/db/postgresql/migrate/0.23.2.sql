alter table std.orders add invoice_path varchar(200);
update std.orders o set invoice_path=(select b.invoice_code from std.bills b where b.id=o.bill_id);
alter table std.bills drop column invoice_code;
