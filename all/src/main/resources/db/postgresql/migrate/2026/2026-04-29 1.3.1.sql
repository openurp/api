insert into base.versions(id,version,updated_at,description)
values(next_id('base.versions'),'1.3.1',now(),'毕业补考增加分数');

alter table edu.final_makeup_takers rename scores to fail_scores;
alter table edu.final_makeup_takers add score float4;

comment on column edu.final_makeup_takers.fail_scores is '之前的成绩';
comment on column edu.final_makeup_takers.score is '本次补考分数';
