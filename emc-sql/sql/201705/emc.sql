/*2017年5月3日*/

/*菜单
  2017年5月5日15:10:10
  sunbinbin
*/
drop table if exists t_emc_auth_menu;

/*==============================================================*/
/* Table: t_emc_menu                                            */
/*==============================================================*/
create table t_emc_auth_menu
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
alter table t_emc_auth_menu comment '权限菜单';
alter table t_emc_auth_menu add SEQ int not Null;
alter table t_emc_auth_menu add MENU_ICON varchar(64) ;
alter table t_emc_auth_menu change P_MENU_AF_ID TYPE varchar(128) NOT NULL;
alter table t_emc_auth_menu change    TYPE   TYPE   TINYINT;
/*用户表
  2017年5月5日15:10:10
  sunbinbin
*/
drop table if exists t_emc_auth_user;
/*==============================================================*/
/* Table: t_emc_user                                            */
/*==============================================================*/
create table t_emc_auth_user
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
alter table t_emc_auth_user comment '用户表';

/*员工表
  2017年5月5日15:10:10
  sunbinbin
*/
drop table if exists t_emc_org_employee;
/*==============================================================*/
/* Table: t_emc_org_employ                                      */
/*==============================================================*/
create table t_emc_org_employee
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
alter table t_emc_org_employee comment '员工表';

/*角色-用户关系表
  2017年5月5日15:10:10
  sunbinbin
*/
drop table if exists t_emc_auth_role_user_rel;
/*==============================================================*/
/* Table: t_emc_role_user_rel                                   */
/*==============================================================*/
create table t_emc_auth_role_user_rel
(
  USER_ID              varchar(32) not null comment '用户主键',
  ROLE_ID              varchar(32) not null comment '角色主键'
);
alter table t_emc_auth_role_user_rel comment '角色用户关系表';

/*菜单功能关系表
  2017年5月5日15:10:10
  sunbinbin
*/
drop table if exists t_emc_auth_menu_func_rel;
/*==============================================================*/
/* Table: t_emc_menu_func_rel                                   */
/*==============================================================*/
create table t_emc_auth_menu_func_rel
(
  ROLE_ID              varbinary(64) not null comment '功能id',
  MENU_ID              varbinary(64) not null comment '菜单id'
);
alter table t_emc_auth_menu_func_rel comment '菜单功能关系表';


/*组织机构   功能2017年5月9日 12:59:12*/


drop table if exists t_emc_org;

/*==============================================================*/
/* Table: t_emc_org                                             */
/*==============================================================*/
create table t_emc_org
(
   ID                   varchar(32) not null comment '机构主键',
   COM_ID               varchar(32) comment '公司',
   ORG_CODE             varchar(16) not null comment '机构代码',
   ORG_NAME             varchar(64) not null comment '机构名称',
   SHORT_NAME           varchar(32) comment '简称',
   P_ORG_ID             varchar(32) not null comment '上级组织机构主键',
   TYPE_ID              bigint not null comment '类型',
   CREATOR              bigint not null comment '创建者',
   CREATE_TIME          datetime not null comment '创建时间',
   MEMO                 varchar(255) comment '备注',
   SEQ                  int not null comment '排序',
   AREA                 double comment '面积',
   primary key (ID)
);

alter table t_emc_org comment '公司组织机构父表';


drop table if exists t_emc_org_feed;

/*==============================================================*/
/* Table: t_emc_org_feed                                        */
/*==============================================================*/
create table t_emc_org_feed
(
   ID                   varchar(32) not null comment '机构主键',
   FEED_TYPE            tinyint not null comment '热源性质 来源字典表 1:热电 2:区域锅炉房 3:核电 4:工业余热 ',
   HEAT_TYPE            tinyint not null comment '供热类型 来源字典表 区域供热 集中供热 尖峰供热',
   INSTALL_CAPACITY     double not null comment '装机容量(MW)',
   HEAT_CAPACITY        double not null default 0 comment '供热能力(㎡)',
   BOILER_NUM           int not null default 0 comment '锅炉数量(自动计算)',
   STEAMTURBINE_NUM     int not null default 0 comment '汽机数量(自动计算)',
   PROVINCE_ID          varchar(12) not null comment '省主键 关联行政区划表T_ECC_SYS_ADMINISTRATIVE',
   CITY_ID              varchar(12) not null comment '市主键',
   COUNTY_ID            varchar(12) not null comment '县主键',
   TOWN_ID              varchar(12) comment '乡主键',
   VILLAGE_ID           varchar(12) comment '村主键',
   ADDR                 varchar(64) not null comment '详细地址',
   LNG                  double(10,6) comment '经度',
   LAT                  double(10,6) comment '纬度',
   PUBLIC_AREA          double not null default 0 comment '公建面积',
   DWELL_AREA           double not null default 0 comment '居民面积',
   primary key (ID)
);

alter table t_emc_org_feed comment '热源基本信息表';


drop table if exists t_emc_org_node;

/*==============================================================*/
/* Table: t_emc_org_node                                        */
/*==============================================================*/
create table t_emc_org_node
(
   ID                   varchar(32) not null comment '机构主键',
   MANAGE_TYPE_ID       varchar(32) not null comment '管理类型 来源于字典表 0-统管 1-自管 2-代管',
   PROVINCE_ID          varchar(12) not null comment '省主键 关联行政区划表T_ECC_SYS_ADMINISTRATIVE',
   CITY_ID              varchar(12) not null comment '市主键',
   COUNTY_ID            varchar(12) not null comment '县主键',
   TOWN_ID              varchar(12) comment '乡主键',
   VILLAGE_ID           varchar(12) comment '村主键',
   ADDR                 varchar(64) not null comment '详细地址',
   LNG                  double(10,6) comment '经度',
   LAT                  double(10,6) comment '纬度',
   PUBLIC_AREA          double not null default 0 comment '公建面积',
   DWELL_AREA           double not null default 0 comment '居民面积',
   primary key (ID)
);
alter table t_emc_org_node comment '热力站基本信息表';
ALTER TABLE t_emc_org_node DROP PRIMARY KEY ;
alter table t_emc_org_node add STATUS TYPEINT not Null;

drop table if exists t_emc_org_room;

/*==============================================================*/
/* Table: t_emc_org_room                                        */
/*==============================================================*/
create table t_emc_org_room
(
   ORG_ID               varchar(32) not null comment '机构主键',
   DWELL_AREA           double not null comment '居民面积',
   primary key (ORG_ID)
);

alter table t_emc_org_room comment '房间基本信息表';


drop table if exists t_emc_org_ban;

/*==============================================================*/
/* Table: t_emc_org_ban                                         */
/*==============================================================*/
create table t_emc_org_ban
(
   ORG_ID               varchar(32) not null comment '机构主键',
   PROVINCE_ID          varchar(12) not null comment '省主键 关联行政区划表T_ECC_SYS_ADMINISTRATIVE',
   CITY_ID              varchar(12) not null comment '市主键',
   COUNTY_ID            varchar(12) not null comment '县主键',
   TOWN_ID              varchar(12) comment '乡主键',
   VILLAGE_ID           varchar(12) comment '村主键',
   ADDR                 varchar(64) not null comment '详细地址',
   LNG                  double(10,6) comment '经度',
   LAT                  double(10,6) comment '纬度',
   PUBLIC_AREA          double not null default 0 comment '公建面积',
   DWELL_AREA           double not null default 0 comment '居民面积',
   primary key (ORG_ID)
);

alter table t_emc_org_ban comment '建筑基本信息表';


drop table if exists t_emc_org_unit;

/*==============================================================*/
/* Table: t_emc_org_unit                                        */
/*==============================================================*/
create table t_emc_org_unit
(
   ID                   bigint not null comment '机构主键',
   PROVINCE_ID          varchar(12) not null comment '省主键 关联行政区划表T_ECC_SYS_ADMINISTRATIVE',
   CITY_ID              varchar(12) not null comment '市主键',
   COUNTY_ID            varchar(12) not null comment '县主键',
   TOWN_ID              varchar(12) comment '乡主键',
   VILLAGE_ID           varchar(12) comment '村主键',
   ADDR                 varchar(64) not null comment '详细地址',
   LNG                  double(10,6) comment '经度',
   LAT                  double(10,6) comment '纬度',
   PUBLIC_AREA          double not null default 0 comment '公建面积',
   DWELL_AREA           double not null default 0 comment '居民面积',
   primary key (ID)
);

alter table t_emc_org_unit comment '单元基本信息表';


drop table if exists t_emc_org_secondne;

/*==============================================================*/
/* Table: t_emc_org_secondne                                    */
/*==============================================================*/
create table t_emc_org_secondne
(
   ID                   varchar(32) not null comment '机构主键',
   NET_TYPE_ID          varchar(32) not null comment '管线类型 来源字典表 干线、支线、连通线、户线',
   LENGTH               double not null default 0 comment '管线长度(管段长度生成)',
   CELL_NUM             int not null default 0 comment '小室数量',
   PART_NUM             int not null default 0 comment '管段数量',
   MEDIUM               varchar(32) not null comment '输送介质',
   primary key (ID)
);

alter table t_emc_org_secondne comment '二次管线基本信息表';


drop table if exists t_emc_org_oncenet;

/*==============================================================*/
/* Table: t_emc_org_oncenet                                     */
/*==============================================================*/
create table t_emc_org_oncenet
(
   ID                   varchar(32) not null comment '机构主键',
   NET_TYPE_ID          varchar(32) not null comment '管线类型 来源字典表  干线、支线、连通线、户线',
   LENGTH               double not null default 0 comment '管线长度(管段长度生成)',
   CELL_NUM             int not null default 0 comment '小室数量',
   PART_NUM             int not null default 0 comment '管段数量',
   MEDIUM               varchar(32) not null comment '输送介质',
   primary key (ID)
);

alter table t_emc_org_oncenet comment '一次管网基本信息表';

/**
  角色表
  sunbinbin
  2017/5/9 14:03
 */
drop table if exists t_emc_auth_role;
/*==============================================================*/
/* Table: t_emc_role                                            */
/*==============================================================*/
create table t_emc_auth_role
(
  ID                   varchar(32) not null comment '角色主键',
  ROLE_NAME            varchar(16) not null comment '角色名称',
  ROLE_DES             varchar(32) not null comment '角色描述',
  MEMO                 varchar(255) comment '备注',
  CREATOR              varchar(64) not null comment '创建者',
  CREATE_TIME          datetime not null comment '创建时间',
  primary key (ID)
);

alter table t_emc_auth_role comment '角色基本信息表';

/*
时间2017年5月10日08:13:59
*/
alter table t_emc_auth_role modify column CREATOR varchar(32);

/**
2017年5月12日14:35:55
 */
 drop table if exists t_emc_auth_func;

/*==============================================================*/
/* Table: T_ECC_AUTH_FUNC                                       */
/*==============================================================*/
create table t_emc_auth_func
(
   ID              varchar(32) not null comment '功能主键',
   MENU_ID              varchar(32) not null comment '菜单主键',
   UNAME                varchar(32) not null comment '唯一名称 功能唯一标识',
   FUNC_NAME            varchar(32) not null comment '功能名称',
   ISSEARCH              tinyint not null comment '是否查询 0-是 1-否',
   SEQ                  int not null comment '排序',
   primary key (ID)
);

alter table t_emc_auth_func comment '功能基本信息表';

/**
2017年5月16日14:51:27
 */
drop table if exists t_emc_sys_dic;

/*==============================================================*/
/* Table: t_emc_sys_dic                                         */
/*==============================================================*/
create table t_emc_sys_dic
(
   ID                   varchar(32) not null comment '字典主键',
   DES                  varchar(16) not null comment '字典名称',
   TYPE_US              varchar(32) not null comment '字典类型',
   TYPE_ZH              varchar(32) not null comment '字典类型名称',
   SEQ                  int not null comment '排序',
   primary key (ID)
);

alter table t_emc_sys_dic comment '系统字典表';

/**
2017年5月17日13:03:29
 */
 drop table if exists t_emc_role_func_rel;

/*==============================================================*/
/* Table: t_emc_role_func_rel                                   */
/*==============================================================*/
create table t_emc_role_func_rel
(
   ROLE_ID              varchar(32) not null comment '角色主键',
   FUNC_ID              varchar(32) not null comment '功能主键'
);

alter table t_emc_role_func_rel comment '角色权限功能表';

drop table if exists t_emc_company;

/*==============================================================*/
/* Table: t_emc_company                                         */
/*==============================================================*/
create table t_emc_company
(
   ID                   varchar(32) not null comment '公司主键',
   CNAME                varchar(64) comment '公司名称',
   primary key (id)
);

alter table t_emc_company comment '公司信息表';


/**
2017年5月22日09:05:51
 */
 ALTER TABLE t_emc_role_func_rel RENAME t_emc_auth_role_func_rel;

 DROP TABLE t_emc_auth_menu_func_rel;

/**
  2017年5月23日 10:26:22 修改org表中字段类型
 */
 alter table t_emc_org modify column TYPE_ID VARCHAR(32);
 alter table t_emc_org modify column CREATOR VARCHAR(32);
