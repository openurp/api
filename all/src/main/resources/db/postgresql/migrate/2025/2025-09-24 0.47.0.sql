insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'0.47.0',now(),'更改专业方向名称');

alter table base.directions rename to major_directions;
alter table base.direction_journals rename to major_direction_journals;

alter table base.tutor_majors add column grade_id bigint;
alter table base.tutor_majors add constraint fk_ahfteyvf8kfsph07oa5efi225 foreign key (grade_id) references base.grades (id);

-- mini clazz
alter table edu.mini_clazz_activities rename column advisor1_id to coach1_id;
alter table edu.mini_clazz_activities rename column advisor2_id to coach2_id;

alter table edu.mini_clazzes add column coach_hours integer default 0;
alter table edu.mini_clazzes alter coach_hours set not null;
alter table edu.mini_clazzes add column course_hours integer default 0;
alter table edu.mini_clazzes alter course_hours set not null;
alter table edu.mini_clazzes add column teach_depart_id integer;
alter table edu.mini_clazzes add constraint fk_sh3lhhuphg0obentx2h5ywqup foreign key (teach_depart_id) references base.departments (id);

-- lab task
alter table lab.lab_tasks add column nature_id integer;
alter table lab.lab_tasks alter nature_id set not null;
alter table lab.lab_tasks add column office_id bigint;
alter table lab.lab_tasks add column practice_hours integer default 0;
alter table lab.lab_tasks alter practice_hours set not null;
alter table lab.lab_tasks add column remark varchar(255);
alter table lab.lab_tasks add column theory_hours integer default 0;
alter table lab.lab_tasks alter theory_hours set not null;
alter table lab.lab_tasks add column validated boolean default false;
alter table lab.lab_tasks alter validated set not null;
alter table lab.lab_tasks alter required set default true;
alter table lab.lab_tasks alter required set not null;

-- comments
comment on table base.major_direction_journals is '专业方向建设过程@edu';
comment on column base.major_direction_journals.id is '非业务主键:datetime';
comment on column base.major_direction_journals.begin_on is '生效日期';
comment on column base.major_direction_journals.depart_id is '部门ID';
comment on column base.major_direction_journals.direction_id is '专业方向ID';
comment on column base.major_direction_journals.end_on is '失效日期';
comment on column base.major_direction_journals.level_id is '培养层次ID';
comment on column base.major_direction_journals.remark is '备注';
comment on table base.major_directions is '方向信息 专业领域@edu';
comment on column base.major_directions.id is '非业务主键:datetime';
comment on column base.major_directions.begin_on is '生效日期';
comment on column base.major_directions.code is '专业方向编码';
comment on column base.major_directions.en_name is '专业方向英文名';
comment on column base.major_directions.end_on is '失效日期';
comment on column base.major_directions.major_id is '所属专业ID';
comment on column base.major_directions.name is '专业方向名称';
comment on column base.major_directions.project_id is '项目ID';
comment on column base.major_directions.remark is '备注';
comment on column base.major_directions.updated_at is '更新时间';
comment on column base.tutor_majors.grade_id is '年级ID';
comment on column edu.mini_clazzes.coach_hours is '辅导课时';
comment on column edu.mini_clazzes.course_hours is '上课课时';
comment on column edu.mini_clazzes.teach_depart_id is '部门组织机构信息ID';
comment on column lab.lab_tasks.nature_id is '课程性质ID';
comment on column lab.lab_tasks.office_id is '教研室ID';
comment on column lab.lab_tasks.practice_hours is '实践学时';
comment on column lab.lab_tasks.remark is '备注';
comment on column lab.lab_tasks.theory_hours is '理论学时';
comment on column lab.lab_tasks.validated is '验证通过';

