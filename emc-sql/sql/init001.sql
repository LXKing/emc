/*角色表
  2017年5月5日15:10:10
  sunbinbin
*/
drop table if exists t_emc_role;
/*==============================================================*/
/* Table: t_emc_role                                            */
/*==============================================================*/
create table t_emc_role
(
  ID                   varchar(32) not null comment '角色主键',
  ROLE_NAME            varchar(16) not null comment '角色名称',
  ROLE_DES             varchar(32) not null comment '角色描述',
  MEMO                 varchar(255) comment '备注',
  CREATOR              varchar(64) not null comment '创建者',
  CREATE_TIME          datetime not null comment '创建时间',
  primary key (ID)
);
alter table t_emc_role comment '角色基本信息表';


/*菜单
  2017年5月5日15:10:10
  sunbinbin
*/
drop table if exists t_emc_menu;

/*==============================================================*/
/* Table: t_emc_menu                                            */
/*==============================================================*/
create table t_emc_menu
(
  ID                   varchar(32) not null comment '菜单ID',
  MENU_NAME            varchar(32) not null comment '菜单名称',
  MENU_URL             varchar(128) not null comment '菜单路径',
  P_MENU_ID            varchar(128) not null comment '前台直接上级菜单',
  CREATOR              varchar(64) comment '创建人',
  CREAT_TIME           datetime comment '创建时间',
  MENU_TYPE            tinyint not null comment '前后台类型0-通用 1 前台 2 后台 ',
  P_MENU_AF_ID         varchar(128) not null comment '后台直接上级菜单',
  primary key (ID)
);
alter table t_emc_menu comment '权限菜单';

/*用户表
  2017年5月5日15:10:10
  sunbinbin
*/
drop table if exists t_emc_user;
/*==============================================================*/
/* Table: t_emc_user                                            */
/*==============================================================*/
create table t_emc_user
(
  ID                   varchar(32) not null comment '用户主键',
  LOGIN                varchar(64) not null comment '登录账号可以是手机号、工号、电子邮箱和自定义',
  PASSWORD             varchar(32) not null comment '密码32位加密报文',
  LOGIN_TIME           datetime comment '登录时间',
  LATS_LOGIN_TIME      datetime comment '上次登录时间',
  LOGIN_NUM            int not null default 0 comment '登录次数',
  USE_STATUS           tinyint not null default 0 comment '使用状态 0-启用1-禁用',
  MEMO                 varchar(255) comment '备注',
  CREATOR              varchar(64) not null comment '创建者',
  CREATE_TIME          datetime not null comment '创建时间',
  primary key (ID)
);
alter table t_emc_user comment '用户表';

/*员工表
  2017年5月5日15:10:10
  sunbinbin
*/
drop table if exists t_emc_org_employ;
/*==============================================================*/
/* Table: t_emc_org_employ                                      */
/*==============================================================*/
create table t_emc_org_employ
(
  ID                   varchar(32) not null comment '员工ID',
  EMP_NAME             varchar(16) not null comment '员工名称',
  EMP_CODE             varchar(32) not null comment '员工编号',
  EMP_TEL              varchar(16) comment '联系电话',
  ADDR                 varchar(128) comment '家庭住址',
  ORG_ID               varchar(64) not null comment '上级组织机构ID',
  EMP_AGE              int comment '员工年龄',
  EMP_GENDER           int comment '员工性别',
  EMP_BIRTHDAY         date comment '员工出生日期',
  EMAIL                varchar(64) comment '员工邮箱',
  EMP_MOBILEPHONE      varchar(11) comment '手机号',
  user_id              varchar(32) not null comment '用户主键',
  primary key (ID)
);
alter table t_emc_org_employ comment '员工表';

/*角色-用户关系表
  2017年5月5日15:10:10
  sunbinbin
*/
drop table if exists t_emc_role_user_rel;
/*==============================================================*/
/* Table: t_emc_role_user_rel                                   */
/*==============================================================*/
create table t_emc_role_user_rel
(
  USER_ID              varchar(32) not null comment '用户主键',
  ROLE_ID              varchar(32) not null comment '角色主键'
);
alter table t_emc_role_user_rel comment '角色用户关系表';

/*菜单功能关系表
  2017年5月5日15:10:10
  sunbinbin
*/
drop table if exists t_emc_menu_func_rel;
/*==============================================================*/
/* Table: t_emc_menu_func_rel                                   */
/*==============================================================*/
create table t_emc_menu_func_rel
(
  ROLE_ID              varbinary(64) not null comment '功能id',
  MENU_ID              varbinary(64) not null comment '菜单id'
);
alter table t_emc_menu_func_rel comment '菜单功能关系表';