/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/1/13 9:27:48                            */
/*==============================================================*/


drop table if exists sys_datasource;

drop table if exists sys_dept;

drop table if exists sys_job;

drop table if exists sys_log;

drop table if exists sys_menu;

drop table if exists sys_role;

drop table if exists sys_role_menu;

drop table if exists sys_user;

drop table if exists 基本信息表;

drop table if exists 基本信息表2;

/*==============================================================*/
/* Table: sys_datasource                                        */
/*==============================================================*/
create table sys_datasource
(
   id                   varchar(32) not null comment 'id',
   name                 varchar(100) comment '名称',
   db_type              varchar(100) comment '数据库类型',
   host                 varchar(100) comment '服务器地址',
   port                 varchar(10) comment '端口号',
   username             varchar(100) comment '用户名',
   password             varchar(200) comment '密码',
   db_name              varchar(100) comment '数据库名称',
   create_id            varchar(32) comment '创建人ID',
   create_name          varchar(64) comment '创建人名称',
   create_time          timestamp comment '创建时间',
   update_id            varchar(32) comment '修改人ID',
   update_name          varchar(64) comment '修改人名称',
   update_time          timestamp comment '修改时间',
   deleted_flag         tinyint comment '删除标记: 0未删除 1已删除',
   primary key (id)
);

alter table sys_datasource comment '数据源表';

/*==============================================================*/
/* Table: sys_dept                                              */
/*==============================================================*/
create table sys_dept
(
   id                   varchar(32) not null comment '部门ID',
   name                 varchar(50) comment '部门名称',
   parent_id            varchar(32) comment '上级部门ID',
   sort                 int comment '排序',
   remark               varchar(200) comment '备注',
   create_id            varchar(32) comment '创建人ID',
   create_name          varchar(64) comment '创建人名称',
   create_time          datetime comment '创建时间',
   update_id            varchar(32) comment '修改人ID',
   update_name          varchar(64) comment '修改人名称',
   update_time          datetime comment '修改时间',
   deleted_flag         tinyint comment '删除标记: 0-未删除 1-已删除',
   primary key (id)
);

alter table sys_dept comment '部门表';

/*==============================================================*/
/* Table: sys_job                                               */
/*==============================================================*/
create table sys_job
(
   id                   varchar(32) not null comment '岗位ID',
   name                 varchar(50) comment '部门名称',
   dept_id              varchar(32) comment '部门ID',
   sort                 int comment '排序',
   remark               varchar(200) comment '备注',
   create_id            varchar(32) comment '创建人ID',
   create_name          varchar(64) comment '创建人名称',
   create_time          datetime comment '创建时间',
   update_id            varchar(32) comment '修改人ID',
   update_name          varchar(64) comment '修改人名称',
   update_time          datetime comment '修改时间',
   deleted_flag         tinyint comment '删除标记: 0-未删除 1-已删除',
   primary key (id)
);

alter table sys_job comment '岗位表';

/*==============================================================*/
/* Table: sys_log                                               */
/*==============================================================*/
create table sys_log
(
   id                   varchar(32) not null comment 'ID',
   request_ip           varchar(100) comment '操作IP',
   ip_location          varchar(200) comment 'IP归属地',
   type                 tinyint comment '数据类型：1 操作记录 2 异常记录',
   user_id              varchar(32) comment '操作人ID',
   username             varchar(100) comment '操作人名称',
   description          varchar(500) comment '操作描述',
   action_method        varchar(100) comment '请求目标方法',
   action_url           varchar(200) comment '请求url',
   params               varchar(1000) comment '请求参数',
   browser              varchar(100) comment '浏览器',
   operating_system     varchar(100) comment '操作系统',
   class_path           varchar(100) comment '类路径',
   request_method       varchar(100) comment '请求方法',
   operate_type         varchar(50) comment '操作类型：1 查询/获取 2 添加 3 修改 4 删除',
   start_time           datetime comment '开始时间',
   finish_time          datetime comment '完成时间',
   consuming_time       bigint comment '消耗时间',
   ex_detail            text comment '异常详情信息 堆栈信息',
   ex_desc              varchar(200) comment '异常描述',
   primary key (id)
);

alter table sys_log comment '操作日志记录表';

/*==============================================================*/
/* Table: sys_menu                                              */
/*==============================================================*/
create table sys_menu
(
   id                   varchar(32) not null comment 'ID',
   name                 varchar(100) comment '名称',
   parent_id            varchar(32) comment '父级ID',
   iframe               tinyint comment '是否外链：0=否 1=是',
   sort                 int comment '排序',
   icon                 varchar(200) comment '图标',
   path                 varchar(255) comment '链接地址',
   cache                tinyint comment '是否缓存：0=否 1=是',
   hidden               tinyint comment '是否隐藏：0=否 1=是',
   component_name       varchar(100) comment '组件名称',
   component_path       varchar(255) comment '组件路径',
   type                 tinyint comment '类型：1=目录 2=菜单 3=按钮',
   permission           varchar(200) comment '权限标识',
   create_id            varchar(32) comment '创建人ID',
   create_name          varchar(64) comment '创建人名称',
   create_time          datetime comment '创建时间',
   update_id            varchar(32) comment '修改人ID',
   update_name          varchar(64) comment '修改人名称',
   update_time          datetime comment '修改时间',
   deleted_flag         tinyint comment '删除标记： 0=未删除 1=已删除',
   primary key (id)
);

alter table sys_menu comment '菜单表';

/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
   id                   varchar(32) not null comment '岗位ID',
   name                 varchar(50) comment '角色名称',
   code                 varchar(100) comment '角色标识',
   sort                 int comment '排序',
   remark               varchar(200) comment '备注',
   create_id            varchar(32) comment '创建人ID',
   create_name          varchar(64) comment '创建人名称',
   create_time          datetime comment '创建时间',
   update_id            varchar(32) comment '修改人ID',
   update_name          varchar(64) comment '修改人名称',
   update_time          datetime comment '修改时间',
   deleted_flag         tinyint comment '删除标记: 0-未删除 1-已删除',
   primary key (id)
);

alter table sys_role comment '角色表';

/*==============================================================*/
/* Table: sys_role_menu                                         */
/*==============================================================*/
create table sys_role_menu
(
   id                   varchar(32) not null comment 'ID',
   role_id              varchar(32) comment '角色ID',
   menu_id              varchar(32) comment '菜单ID',
   primary key (id)
);

alter table sys_role_menu comment '角色菜单表';

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   id                   varchar(32) not null comment '用户ID',
   username             varchar(50) comment '用户名',
   password             varchar(200) comment '密码',
   dept_id              varchar(32) comment '部门ID',
   job_id               varchar(32) comment '岗位ID',
   email                varchar(255) comment '邮箱',
   phone                varchar(20) comment '手机号',
   avatar               varchar(255) comment '头像',
   status               char(10) comment '状态：0-正常 1-锁定',
   create_id            varchar(32) comment '创建人ID',
   create_name          varchar(64) comment '创建人名称',
   create_time          datetime comment '创建时间',
   update_id            varchar(32) comment '修改人ID',
   update_name          varchar(64) comment '修改人名称',
   update_time          datetime comment '修改时间',
   deleted_flag         tinyint comment '删除标记: 0-未删除 1-已删除',
   primary key (id)
);

alter table sys_user comment '用户表';

/*==============================================================*/
/* Table: 基本信息表                                                 */
/*==============================================================*/
create table 基本信息表
(
   create_id            varchar(32) comment '创建人ID',
   create_name          varchar(64) comment '创建人名称',
   create_time          datetime comment '创建时间',
   update_id            varchar(32) comment '修改人ID',
   update_name          varchar(64) comment '修改人名称',
   update_time          datetime comment '修改时间',
   deleted_flag         tinyint comment '删除标记：0=未删除 1=已删除'
);

/*==============================================================*/
/* Table: 基本信息表2                                                */
/*==============================================================*/
create table 基本信息表2
(
   create_id            varchar(32) comment '创建人ID',
   create_name          varchar(64) comment '创建人名称',
   create_time          datetime comment '创建时间',
   update_id            varchar(32) comment '修改人ID',
   update_name          varchar(64) comment '修改人名称',
   update_time          datetime comment '修改时间',
   deleted_flag         tinyint comment '删除标记: 0未删除 1已删除'
);

