insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'1.4.0',now(),'重构毕业模型');

alter table std.graduate_batches add apply_begin_at timestamptz;
alter table std.graduate_batches add apply_end_at timestamptz;
alter table std.graduate_batches add column settings jsonb default '{}' not null;

alter table std.graduations alter column has_degree drop not null;
alter table std.graduations add degree_id int4;
alter table std.graduations add email varchar(100);
alter table std.graduations add mobile varchar(15);
alter table std.graduations add mobile_verified boolean default false;
alter table std.graduations add plan_passed boolean default false;
alter table std.graduations add last_result_id int4;
alter table std.graduations add gpa float8 default 0;
alter table std.graduations add wms float8 default 0;
alter table std.graduations drop has_degree cascade;
alter table std.graduations add grad_passed boolean;
alter table std.graduations add degree_passed boolean;

alter table std.degree_applies add college_passed boolean;
alter table std.degree_applies add datas jsonb;
alter table std.degree_applies add email varchar(100);
alter table std.degree_applies add mobile varchar(15);
alter table std.degree_applies add wms float8 ;
alter table std.degree_results alter gpa type float8;
alter table std.degree_applies rename to grad_degree_applies;
alter table std.grad_degree_applies add college_opinion varchar(200);
alter table std.grad_degree_applies add std_sign_url varchar(200);
alter table std.grad_degree_applies add reason varchar(200);
alter table std.grad_degree_applies drop constraint uk_7pjmosme1tvk266r1b5ca6kds;
alter table std.grad_degree_applies add constraint uk_4llojq70wvy25bx41ygo8ixxy unique (batch_id,std_id);

create table std.grad_complete_applies (passed boolean, std_id bigint not null, college_passed boolean, std_sign_url varchar(255), datas jsonb not null, college_opinion varchar(255), mobile varchar(15), reason varchar(255), batch_id bigint not null, id bigint not null, updated_at timestamptz default current_timestamp not null);
alter table std.grad_complete_applies add constraint uk_561k8ths3keh8qcnotbnr4wd8 unique (batch_id,std_id);
alter table std.grad_complete_applies add constraint pk_kds0hake3or6uo1ycrrd79jqj primary key (id);

create table std.grad_defer_applies (id bigint not null, batch_id bigint not null, college_opinion varchar(255), college_passed boolean, datas jsonb not null, defer_grad_date date not null, mobile varchar(15), passed boolean, plan_grad_date date not null, reason varchar(255), std_id bigint not null, std_sign_url varchar(255), updated_at timestamptz default current_timestamp not null);
alter table std.grad_defer_applies add constraint fk_8wi7t2h73a79c4kfnsyivtauh foreign key (std_id) references base.students (id);
alter table std.grad_defer_applies add constraint fk_gqbbwftrt9rseuahgpja5adef foreign key (batch_id) references std.graduate_batches (id);
alter table std.grad_defer_applies add constraint pk_218brrfqpcem52uuwj2mxhn54 primary key (id);

create table base.depart_transitions (effective_on date not null, to_id integer not null, id bigint not null, remark varchar(100), from_id integer not null, updated_at timestamptz default current_timestamp not null);
alter table base.depart_transitions add constraint pk_mmw501gva47u49d1v46b9eikx primary key (id);
alter table base.depart_transitions add constraint fk_k8vw90xvcnbjsle99sxd3xmfj foreign key (to_id) references base.departments (id);
alter table base.depart_transitions add constraint fk_thh402c04iutg10u78f360t6r foreign key (from_id) references base.departments (id);

create table prac.mandarin_grades (id bigint not null, acquired_in date not null, certificate_no varchar(255), course_grade_id bigint, passed boolean default false not null, score float4 default 0 not null, semester_id integer not null, std_id bigint not null, updated_at timestamptz default current_timestamp not null);
create index idx_5gweufhuqx3myklfoetf5wqo5 on prac.mandarin_grades (std_id);
alter table prac.mandarin_grades add constraint pk_q202q46nm3jj3f7smndxpu6x4 primary key (id);
alter table prac.mandarin_grades add constraint fk_f3vpwb63rsw9xwbac0dg01n82 foreign key (std_id) references base.students (id);
alter table prac.mandarin_grades add constraint fk_7w5bd1mhpxj8gvayyae0uq6x2 foreign key (semester_id) references base.semesters (id);

alter table std.bachelor2nd_applies rename to grad_bachelor2nd_applies;
alter table std.plan_result_checks rename to grad_plan_result_checks;

alter table std.grad_bachelor2nd_applies rename to std_grad_bachelor2nd_applies;
alter table std.std_grad_bachelor2nd_applies set schema flow;

alter table std.grad_complete_applies rename to std_grad_complete_applies;
alter table std.std_grad_complete_applies set schema flow;

alter table std.grad_defer_applies rename to std_grad_defer_applies;
alter table std.std_grad_defer_applies set schema flow;

alter table std.grad_degree_applies rename to std_grad_degree_applies;
alter table std.std_grad_degree_applies set schema flow;

alter table std.honor_categories rename column discription to description;
alter table std.honor_levels rename column discription to description;
alter table std.scholarship_categories rename column discription to description;
alter table std.scholarship_levels rename column discription to description;
alter table std.stipend_categories rename column discription to description;
alter table std.stipend_levels rename column discription to description;
alter table std.subsidy_categories rename column discription to description;
alter table std.subsidy_levels rename column discription to description;

insert into code.graduate_types(id,code,name,begin_on,updated_at)
values(1,'1','应届',current_date,now());
insert into code.graduate_types(id,code,name,begin_on,updated_at)
values(2,'2','延期',current_date,now());

insert into std.graduations(id,std_id,batch_id,plan_passed,gpa,wms,graduate_type_id,mobile_verified)
select gr.id,gr.std_id,gr.batch_id,gr.passed,0,0,case when std.graduation_deferred =true then 2 else 1 end,false
    from std.graduate_results gr,base.students std
    where std.id=gr.std_id and not exists(select * from std.graduations g where g.std_id=gr.std_id and g.batch_id=gr.batch_id);

comment on table base.depart_transitions is '部门变迁记录@common';
comment on column base.depart_transitions.id is '非业务主键:datetime';
comment on column base.depart_transitions.effective_on is '生效日期';
comment on column base.depart_transitions.from_id is '原部门ID';
comment on column base.depart_transitions.remark is '备注';
comment on column base.depart_transitions.to_id is '变更后部门ID';
comment on column base.depart_transitions.updated_at is '更新时间';
comment on table flow.std_grad_bachelor2nd_applies is '毕业学生二学位申请@graduation.flow';
comment on column flow.std_grad_bachelor2nd_applies.id is '非业务主键:datetime';
comment on column flow.std_grad_bachelor2nd_applies.batch_id is '毕业批次ID';
comment on column flow.std_grad_bachelor2nd_applies.gpa is '平均绩点';
comment on column flow.std_grad_bachelor2nd_applies.grade_detail is '成绩明细';
comment on column flow.std_grad_bachelor2nd_applies.std_id is '学生ID';
comment on column flow.std_grad_bachelor2nd_applies.updated_at is '更新时间';
comment on table flow.std_grad_complete_applies is '结业申请@graduation.flow';
comment on column flow.std_grad_complete_applies.id is '非业务主键:datetime';
comment on column flow.std_grad_complete_applies.batch_id is '毕业批次ID';
comment on column flow.std_grad_complete_applies.college_opinion is '院系意见';
comment on column flow.std_grad_complete_applies.college_passed is '院系是否通过';
comment on column flow.std_grad_complete_applies.datas is '其他申请数据';
comment on column flow.std_grad_complete_applies.mobile is '联系手机';
comment on column flow.std_grad_complete_applies.passed is '是否通过';
comment on column flow.std_grad_complete_applies.reason is '申请理由';
comment on column flow.std_grad_complete_applies.std_id is '学生ID';
comment on column flow.std_grad_complete_applies.std_sign_url is '个人签名url';
comment on column flow.std_grad_complete_applies.updated_at is '更新时间';
comment on table flow.std_grad_defer_applies is '延期申请@graduation.flow';
comment on column flow.std_grad_defer_applies.id is '非业务主键:datetime';
comment on column flow.std_grad_defer_applies.batch_id is '毕业批次ID';
comment on column flow.std_grad_defer_applies.college_opinion is '院系意见';
comment on column flow.std_grad_defer_applies.college_passed is '院系是否通过';
comment on column flow.std_grad_defer_applies.datas is '其他申请数据';
comment on column flow.std_grad_defer_applies.defer_grad_date is '申请延期到';
comment on column flow.std_grad_defer_applies.mobile is '联系手机';
comment on column flow.std_grad_defer_applies.passed is '是否通过';
comment on column flow.std_grad_defer_applies.plan_grad_date is '计划毕业日期';
comment on column flow.std_grad_defer_applies.reason is '申请理由';
comment on column flow.std_grad_defer_applies.std_id is '学生ID';
comment on column flow.std_grad_defer_applies.std_sign_url is '个人签名url';
comment on column flow.std_grad_defer_applies.updated_at is '更新时间';
comment on table flow.std_grad_degree_applies is '学位申请@graduation.flow';
comment on column flow.std_grad_degree_applies.id is '非业务主键:datetime';
comment on column flow.std_grad_degree_applies.batch_id is '毕业批次ID';
comment on column flow.std_grad_degree_applies.college_opinion is '院系意见';
comment on column flow.std_grad_degree_applies.college_passed is '院系是否通过';
comment on column flow.std_grad_degree_applies.datas is '其他申请数据';
comment on column flow.std_grad_degree_applies.degree_id is '学位ID';
comment on column flow.std_grad_degree_applies.email is 'Email';
comment on column flow.std_grad_degree_applies.gpa is 'GPA';
comment on column flow.std_grad_degree_applies.mobile is '联系手机';
comment on column flow.std_grad_degree_applies.passed is '是否通过';
comment on column flow.std_grad_degree_applies.reason is '申请理由';
comment on column flow.std_grad_degree_applies.std_id is '学生ID';
comment on column flow.std_grad_degree_applies.std_sign_url is '个人签名url';
comment on column flow.std_grad_degree_applies.updated_at is '更新时间';
comment on column flow.std_grad_degree_applies.wms is '加权平均分';
comment on table prac.mandarin_grades is '普通话水平测试成绩@mandarin';
comment on column prac.mandarin_grades.id is '非业务主键:datetime';
comment on column prac.mandarin_grades.acquired_in is '获得年月';
comment on column prac.mandarin_grades.certificate_no is '证书号';
comment on column prac.mandarin_grades.course_grade_id is '课程成绩ID';
comment on column prac.mandarin_grades.passed is '是否通过';
comment on column prac.mandarin_grades.score is '分数';
comment on column prac.mandarin_grades.semester_id is '学年学期ID';
comment on column prac.mandarin_grades.std_id is '学生ID';
comment on column prac.mandarin_grades.updated_at is '更新时间';
comment on table std.grad_plan_result_checks is '计划完成情况确认@graduation';
comment on column std.grad_plan_result_checks.id is '非业务主键:datetime';
comment on column std.grad_plan_result_checks.contents is '各个类别完成情况';
comment on column std.grad_plan_result_checks.owed_credits is '欠学分';
comment on column std.grad_plan_result_checks.owed_credits2 is '预计通过后所欠学分';
comment on column std.grad_plan_result_checks.owed_credits3 is '在读通过后所欠学分';
comment on column std.grad_plan_result_checks.passed_credits is '通过学分';
comment on column std.grad_plan_result_checks.required_credits is '要求学分';
comment on column std.grad_plan_result_checks.std_id is '学生ID';
comment on column std.grad_plan_result_checks.updated_at is '更新时间';
comment on column std.graduate_batches.apply_begin_at is '申请起始时间';
comment on column std.graduate_batches.apply_end_at is '申请结束时间';
comment on column std.graduate_batches.settings is '其他设置';
comment on column std.graduations.degree_id is '学位ID';
comment on column std.graduations.degree_passed is '学位审核是否通过';
comment on column std.graduations.email is 'Email';
comment on column std.graduations.gpa is '平均绩点';
comment on column std.graduations.grad_passed is '毕业审核是否通过';
comment on column std.graduations.mobile is '联系手机';
comment on column std.graduations.mobile_verified is '手机号码是否已经验证';
comment on column std.graduations.plan_passed is '是否能完成学业';
comment on column std.graduations.wms is '平均分';

