insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'1.4.10',now(),'增加肄业申请');

create table flow.std_grad_dropout_applies (id bigint not null, batch_id bigint not null, college_opinion varchar(255), college_passed boolean, datas jsonb not null, mobile varchar(15), passed boolean, reason varchar(255), std_id bigint not null, std_sign_url varchar(255), updated_at timestamptz default current_timestamp not null);
alter table std.delayed_graduates add column status_id integer;
alter table flow.std_grad_dropout_applies add constraint pk_2frjotu1n08jskusup2xaf5tf primary key (id);
alter table flow.std_grad_dropout_applies add constraint fk_qxy394pmhwl201bx04s0skbx4 foreign key (std_id) references base.students (id);
alter table flow.std_grad_dropout_applies add constraint fk_kwe9csr1p17bodsf159rpirqs foreign key (batch_id) references std.graduate_batches (id);
alter table std.delayed_graduates add constraint fk_q9bcyd2thhetfcbm7v61k15u8 foreign key (status_id) references code.graduation_statuses (id);
alter table flow.std_grad_dropout_applies add constraint uk_q4aasvdekb3r0i1r4sofhwedo unique (batch_id,std_id);
alter table std.delayed_graduates add constraint uk_2v11mxy80kn0u3k8bnqwd1ji5 unique (std_id,season_id);

alter table std.spa_print_quotas alter last_print_at drop not null;

comment on table flow.std_grad_dropout_applies is '肄业申请@graduation.flow';
comment on column flow.std_grad_dropout_applies.id is '非业务主键:datetime';
comment on column flow.std_grad_dropout_applies.batch_id is '毕业批次ID';
comment on column flow.std_grad_dropout_applies.college_opinion is '院系意见';
comment on column flow.std_grad_dropout_applies.college_passed is '院系是否通过';
comment on column flow.std_grad_dropout_applies.datas is '其他申请数据';
comment on column flow.std_grad_dropout_applies.mobile is '联系手机';
comment on column flow.std_grad_dropout_applies.passed is '是否通过';
comment on column flow.std_grad_dropout_applies.reason is '申请理由';
comment on column flow.std_grad_dropout_applies.std_id is '学生ID';
comment on column flow.std_grad_dropout_applies.std_sign_url is '个人签名url';
comment on column flow.std_grad_dropout_applies.updated_at is '更新时间';
comment on table std.delayed_graduates is '不予正常毕业的毕业生';
comment on column std.delayed_graduates.status_id is '毕业状态ID';
