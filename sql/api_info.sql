-- api信息表
create table if not exists api_db.`api_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `apiName` varchar(256) not null comment 'api名',
    `description` varchar(256) not null comment '描述',
    `url` varchar(512) not null comment 'api地址',
    `requestHeader` text null comment '请求头',
    `responseHeader` text null comment '响应头',
    `status` int default 1 not null comment '0 关闭 1 开启',
    `method` varchar(256) not null comment '请求类型',
    `userId` bigint not null comment '创建人id',
    `update_time` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `create_time` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `is_deleted` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment 'api信息表';

insert into api_db.`api_info` (`apiName`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('01YX', 'LmE', 'www.merissa-borer.info', 'ktSl', 'OH', 1, 'get', 942263);
insert into api_db.`api_info` (`apiName`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('ODo', 'PsOt', 'www.latisha-gaylord.net', 'p6', 'v7gF', 1, 'get', 55112);
insert into api_db.`api_info` (`apiName`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('kox', '81gvx', 'www.tyson-maggio.biz', 'c7', 'bHCsf', 1, 'get', 562266);
insert into api_db.`api_info` (`apiName`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('UFNK', 'C4', 'www.jorge-heathcote.com', 'le', '7I', 1, 'get', 640);
insert into api_db.`api_info` (`apiName`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('j3wS', 'Up', 'www.landon-schmidt.name', 'LeFXA', 'Suu', 1, 'get', 467229463);
insert into api_db.`api_info` (`apiName`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('W9Sx4', '4XmIF', 'www.rashad-ruecker.org', '433', 'jQrgd', 1, 'get', 62);
insert into api_db.`api_info` (`apiName`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('qFrX', 'FWai', 'www.elvis-kuhlman.name', 'C7hlZ', 'kD', 1, 'get', 214199310);
insert into api_db.`api_info` (`apiName`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('MZo', '2oIiN', 'www.donald-kertzmann.io', 'XR', 'xL', 1, 'get', 2167460);
insert into api_db.`api_info` (`apiName`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('MMRh', 'HmYkC', 'www.dee-wiegand.name', 'tfSy', 'Dbt', 1, 'get', 84);
insert into api_db.`api_info` (`apiName`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('ovw', 'R6', 'www.retta-hettinger.com', 'T34w', 'ki4id', 1, 'get', 44289185);
insert into api_db.`api_info` (`apiName`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('FZgMH', 'cc', 'www.deloras-hettinger.org', 'LYnKb', 'bAAs', 1, 'get', 8365804686);
insert into api_db.`api_info` (`apiName`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('u7Sh6', 'YTG', 'www.hedy-renner.org', 'V48Qr', 'EK3hH', 1, 'get', 2);
insert into api_db.`api_info` (`apiName`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('Hm', '9sB', 'www.tonita-gottlieb.name', 'CIgr', 'aZjLg', 1, 'get', 740);
insert into api_db.`api_info` (`apiName`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('dC', '5o', 'www.renae-torphy.io', 'LrUg', 'LIR', 1, 'get', 6787);
insert into api_db.`api_info` (`apiName`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('pQbc', 'wE79m', 'www.antonio-king.io', 'fgvdL', 'JKj', 1, 'get', 642);
insert into api_db.`api_info` (`apiName`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('RLoK', 'zKE', 'www.jess-johns.io', '2gEI', 's5Oex', 1, 'get', 2049113);
insert into api_db.`api_info` (`apiName`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('eqeD', 'RHN3', 'www.cary-daugherty.info', 'XVRh', 'O4qr1', 1, 'get', 3177016589);
insert into api_db.`api_info` (`apiName`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('mz5r', 'kf6B', 'www.dennis-feil.info', 'at3J', 'Vq', 1, 'get', 32959);
insert into api_db.`api_info` (`apiName`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('iM', 'vqb', 'www.armandina-tremblay.org', 'ot', 'Ik7', 1, 'get', 9160634);
insert into api_db.`api_info` (`apiName`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('NZAfj', 'XOc', 'www.caleb-gulgowski.name', 'O7fD', 'kK', 1, 'get', 71);