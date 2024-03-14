-- 用户接口调用次数表
create table if not exists user_api_info
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `userId` bigint not null  comment '用户id' ,
    `apiId` bigint not null comment '接口id' ,
    `totalNum` int default 0 not null comment '总共调用次数',
    `leftNum` int default 0 not null comment '剩余调用次数',
    `status` int default 0 not null comment '0 允许调用 1 不允许调用',
    `update_time` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `create_time` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `is_deleted` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '用户接口调用次数表';