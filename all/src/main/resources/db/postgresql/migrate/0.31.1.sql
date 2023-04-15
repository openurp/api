alter table degree.defense_groups add season_id bigint;
alter table degree.defense_groups add published bool default false;
update degree.defense_groups set season_id=20230701;
alter table degree.defense_groups alter column season_id set not null;
alter table degree.defense_groups alter column published set not null;
