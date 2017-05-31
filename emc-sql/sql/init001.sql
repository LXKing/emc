/*
初始化数据库
2017年5月3日13:49:38
*/

/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50712
Source Host           : 127.0.0.1:3306
Source Database       : emc

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2017-05-05 08:31:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_emc_carbon_check
-- ----------------------------
DROP TABLE IF EXISTS t_emc_carbon_check;
CREATE TABLE t_emc_carbon_check (
  ID varchar(32) NOT NULL COMMENT '主键',
  COMID varchar(32) NOT NULL COMMENT '公司',
  UNITID varchar(32) NOT NULL COMMENT '用能单位',
  CARBON_INDEX double(6,2) NOT NULL COMMENT '碳排放强度',
  SDATE date NOT NULL COMMENT '开始日期(%Y-%m-%d)',
  EDATE date NOT NULL COMMENT '结束日期(%Y-%m-%d)',
  CID varchar(32) NOT NULL COMMENT '创建人',
  CDID varchar(32) NOT NULL COMMENT '创建人部门',
  CTIME datetime NOT NULL COMMENT '创建时间(%Y-%m-%d %H:%i:%s)',
  ISDELETED tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除(0-否 1-删除)',
  UID varchar(32) NOT NULL COMMENT '修改人',
  UDID varchar(32) NOT NULL COMMENT '修改人部门',
  UTIME datetime NOT NULL COMMENT '修改时间(%Y-%m-%d %H:%i:%s)',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='碳排放对标表';

-- ----------------------------
-- Table structure for t_emc_carbon_formula
-- ----------------------------
DROP TABLE IF EXISTS t_emc_carbon_formula;
CREATE TABLE t_emc_carbon_formula (
  ID varchar(32) NOT NULL COMMENT '主键',
  COMID varchar(32) NOT NULL COMMENT '公司',
  TYPEID varchar(32) NOT NULL COMMENT '能源类型',
  CAMOUNT double(6,2) NOT NULL COMMENT '单位热值含碳量（吨 碳/TJ）',
  CRATE double(6,2) NOT NULL COMMENT '碳氧化率',
  SDATE date NOT NULL COMMENT '开始日期',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='碳排放公式表';

-- ----------------------------
-- Table structure for t_emc_coal_coef
-- ----------------------------
DROP TABLE IF EXISTS t_emc_coal_coef;
CREATE TABLE t_emc_coal_coef (
  ID varchar(32) NOT NULL COMMENT '主键',
  COMID varchar(32) NOT NULL COMMENT '公司',
  TYPEID varchar(32) NOT NULL COMMENT '能源类型',
  COEF double(10,6) NOT NULL COMMENT '系数',
  SDATE date NOT NULL COMMENT '开始日期(%Y-%m-%d)',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='折标煤系数表';

-- ----------------------------
-- Table structure for t_emc_com_model
-- ----------------------------
DROP TABLE IF EXISTS t_emc_com_model;
CREATE TABLE t_emc_com_model (
  MID varchar(32) NOT NULL COMMENT '模块主键',
  CID varchar(32) NOT NULL COMMENT '公司主键',
  PRIMARY KEY (MID,CID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司模块关系表';

-- ----------------------------
-- Table structure for t_emc_cost_manual
-- ----------------------------
DROP TABLE IF EXISTS t_emc_cost_manual;
CREATE TABLE t_emc_cost_manual (
  ID varchar(32) NOT NULL COMMENT '主键',
  COMID varchar(32) NOT NULL COMMENT '公司',
  UNITID varchar(32) NOT NULL COMMENT '用能单位',
  TYPEID varchar(32) NOT NULL COMMENT '成本类型',
  COST decimal(10,2) NOT NULL COMMENT '成本',
  COST_DATE date NOT NULL COMMENT '日期',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='成本填报表';

-- ----------------------------
-- Table structure for t_emc_cost_type
-- ----------------------------
DROP TABLE IF EXISTS t_emc_cost_type;
CREATE TABLE t_emc_cost_type (
  ID varchar(32) NOT NULL COMMENT '主键',
  NAME_ZH varchar(16) NOT NULL COMMENT '类型名称(中文)',
  NAME_EN varchar(16) NOT NULL COMMENT '类型名称(英文)',
  DOSAGE_UNIT varchar(16) NOT NULL COMMENT '用量单位',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='成本类型表';

-- ----------------------------
-- Table structure for t_emc_energy_com
-- ----------------------------
DROP TABLE IF EXISTS t_emc_energy_com;
CREATE TABLE t_emc_energy_com (
  ID varchar(32) NOT NULL COMMENT '主键',
  COMID varchar(32) NOT NULL COMMENT '公司',
  TYPEID varchar(32) NOT NULL COMMENT '能源类型',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司能源类型关系表';

-- ----------------------------
-- Table structure for t_emc_energy_price
-- ----------------------------
DROP TABLE IF EXISTS t_emc_energy_price;
CREATE TABLE t_emc_energy_price (
  ID varchar(32) NOT NULL COMMENT '主键',
  COMID varchar(32) NOT NULL COMMENT '公司',
  TYPEID varchar(32) NOT NULL COMMENT '能源类型',
  PRICE decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '每用量单位单价(元)',
  SDATE date NOT NULL COMMENT '开始日期(%Y-%m-%d)',
  STIME time NOT NULL COMMENT '开始时间(%H:00:00)(包含)',
  ETIME time NOT NULL COMMENT '结束时间(%H:00:00)(不包含)',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='能源单价表';

-- ----------------------------
-- Table structure for t_emc_energy_type
-- ----------------------------
DROP TABLE IF EXISTS t_emc_energy_type;
CREATE TABLE t_emc_energy_type (
  ID varchar(32) NOT NULL COMMENT '主键',
  NAME_ZH varchar(16) NOT NULL COMMENT '类型名称(中文)',
  NAME_EN varchar(16) NOT NULL COMMENT '类型名称(英文)',
  DOSAGE_UNIT varchar(16) NOT NULL COMMENT '用量单位',
  PRICE decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '每用量单位默认单价(元)',
  COEF double(10,6) NOT NULL DEFAULT '1.000000' COMMENT '每用量单位默认标煤系数',
  ECO_TYPE tinyint(4) NOT NULL COMMENT '经济类型(0-即是产品也是成本1-成本2-产品)',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='能源类型表（包括热力站热源）';

-- ----------------------------
-- Table structure for t_emc_expend_check
-- ----------------------------
DROP TABLE IF EXISTS t_emc_expend_check;
CREATE TABLE t_emc_expend_check (
  ID varchar(32) NOT NULL COMMENT '主键',
  COMID varchar(32) NOT NULL COMMENT '公司',
  UNITID varchar(32) NOT NULL COMMENT '用能单位',
  TYPEID varchar(32) NOT NULL COMMENT '能源类型',
  STANDARD double(6,2) NOT NULL COMMENT '达标线',
  EXCELLENT double(6,2) NOT NULL COMMENT '优秀线',
  SDATE date NOT NULL COMMENT '开始日期(%Y-%m-%d)',
  EDATE date NOT NULL COMMENT '结束日期(%Y-%m-%d)',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='单耗考核对标表';

-- ----------------------------
-- Table structure for t_emc_final_cost_day
-- ----------------------------
DROP TABLE IF EXISTS t_emc_final_cost_day;
CREATE TABLE t_emc_final_cost_day (
  ID varchar(32) NOT NULL COMMENT '主键',
  COMID varchar(32) NOT NULL COMMENT '公司',
  UNITID varchar(32) NOT NULL COMMENT '用能单位',
  TYPEID varchar(32) NOT NULL COMMENT '成本类型',
  COST decimal(10,2) NOT NULL COMMENT '成本',
  COST_DATE date NOT NULL COMMENT '日期',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='最终每天成本表';

-- ----------------------------
-- Table structure for t_emc_final_data_hour
-- ----------------------------
DROP TABLE IF EXISTS t_emc_final_data_hour;
CREATE TABLE t_emc_final_data_hour (
  ID varchar(32) NOT NULL COMMENT '主键',
  COMID varchar(32) NOT NULL COMMENT '公司',
  UNITID varchar(32) NOT NULL COMMENT '用能单位',
  NODEID varchar(32) NOT NULL COMMENT '用能节点',
  TYPEID varchar(32) NOT NULL COMMENT '能源类型',
  DOSAGE_TIME datetime NOT NULL COMMENT '时间(%Y-%m-%d %H:00:00)',
  DOSAGE double(10,2) NOT NULL COMMENT '能源用量',
  AREA double(12,2) NOT NULL COMMENT '能源面积(㎡)',
  PRICE decimal(10,2) NOT NULL COMMENT '能源单价(元)',
  WTEMP double(6,2) NOT NULL COMMENT '天气温度(℃)',
  CWTEMP double(6,2) NOT NULL COMMENT '折算天气温度(℃)',
  COAL_COEF double(10,6) NOT NULL COMMENT '标煤系数',
  C_COEF double(10,6) NOT NULL COMMENT '碳排放系数',
  ITEMP double(6,2) DEFAULT NULL COMMENT '平均室内温度(℃)',
  CITEMP double(6,2) DEFAULT NULL COMMENT '折算室内温度(℃)',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='最终每小时能源用量表(一个用能单位的一个能源类型在每小时只能有一条记录)';

-- ----------------------------
-- Table structure for t_emc_heat_season
-- ----------------------------
DROP TABLE IF EXISTS t_emc_heat_season;
CREATE TABLE t_emc_heat_season (
  ID varchar(32) NOT NULL COMMENT '主键',
  COMID varchar(32) NOT NULL COMMENT '公司',
  NAME varchar(16) NOT NULL COMMENT '名称',
  SDATE date NOT NULL COMMENT '开始日期',
  EDATE date NOT NULL COMMENT '结束日期',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采暖季度表';

-- ----------------------------
-- Table structure for t_emc_home_model
-- ----------------------------
DROP TABLE IF EXISTS t_emc_home_model;
CREATE TABLE t_emc_home_model (
  ID varchar(32) NOT NULL COMMENT '主键',
  NAME_ZH varchar(16) NOT NULL COMMENT '名称(中文)',
  NAME_EN varchar(16) NOT NULL COMMENT '名称(英文)',
  TYPE tinyint(4) NOT NULL COMMENT '类型(0-领导 1-员工)',
  SORTNO tinyint(4) NOT NULL COMMENT '排序',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='首页模块表';

-- ----------------------------
-- Table structure for t_emc_log
-- ----------------------------
DROP TABLE IF EXISTS t_emc_log;
CREATE TABLE t_emc_log (
  ID varchar(32) NOT NULL COMMENT '主键',
  NAME_ZH varchar(16) NOT NULL COMMENT '名称(中文)',
  NAME_EN varchar(16) NOT NULL COMMENT '名称(英文)',
  TYPE tinyint(4) NOT NULL COMMENT '类型(0-领导 1-员工)',
  SORTNO tinyint(4) NOT NULL COMMENT '排序',
  CID varchar(32) NOT NULL COMMENT '创建人',
  CDID varchar(32) NOT NULL COMMENT '创建人部门',
  CTIME datetime NOT NULL COMMENT '创建时间(%Y-%m-%d %H:%i:%s)',
  ISDELETED tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除(0-否 1-删除)',
  UID varchar(32) NOT NULL COMMENT '修改人',
  UDID varchar(32) NOT NULL COMMENT '修改人部门',
  UTIME datetime NOT NULL COMMENT '修改时间(%Y-%m-%d %H:%i:%s)',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表';

-- ----------------------------
-- Table structure for t_emc_unit_area
-- ----------------------------
DROP TABLE IF EXISTS t_emc_unit_area;
CREATE TABLE t_emc_unit_area (
  ID varchar(32) NOT NULL COMMENT '主键',
  COMID varchar(32) NOT NULL COMMENT '公司',
  NODEID varchar(32) NOT NULL COMMENT '用能节点',
  UNITID varchar(32) NOT NULL COMMENT '用能单位',
  AREA double(12,2) NOT NULL COMMENT '能源面积(㎡)',
  STIME datetime NOT NULL COMMENT '开始时间(%Y-%m-%d %H:00:00)',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='单位面积表';

-- ----------------------------
-- Table structure for t_emc_user_model
-- ----------------------------
DROP TABLE IF EXISTS t_emc_user_model;
CREATE TABLE t_emc_user_model (
  ID varchar(32) NOT NULL COMMENT '主键',
  MID varchar(32) NOT NULL COMMENT '模块主键',
  STATUS tinyint(4) NOT NULL COMMENT '状态(0显示1不显示)',
  SORTNO tinyint(4) NOT NULL COMMENT '排序(升序)',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户模块表';
drop table if exists t_emc_role;


/**
删除组织机构历史表
 */
 drop table if exists t_emc_org;
drop table if exists t_emc_org_feed;
drop table if exists t_emc_org_node;
drop table if exists t_emc_org_ban;
drop table if exists t_emc_org_employee;
drop table if exists t_emc_org_oncenet;
drop table if exists t_emc_org_room;
drop table if exists t_emc_org_secondnet;
drop table if exists t_emc_org_unit;




/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/5/31 10:11:04                           */
/*==============================================================*/


drop table if exists t_emc_org;


drop table if exists t_emc_unit_ban;


drop table if exists t_emc_unit_cell;


drop table if exists t_emc_unit_community;


drop table if exists t_emc_unit_feed;


drop table if exists t_emc_unit_line;


drop table if exists t_emc_unit_net;


drop table if exists t_emc_unit_room;


drop table if exists t_emc_unit_station;

drop table if exists t_emc_unit_type_rel;

/*==============================================================*/
/* Table: t_emc_org                                             */
/*==============================================================*/
create table t_emc_org
(
   ID                   bigint not null auto_increment comment '机构主键',
   ORG_CODE             varchar(16) not null comment '机构代码',
   ORG_NAME             varchar(64) not null comment '机构名称',
   SHORT_NAME           varchar(32) comment '简称',
   P_ORG_ID             bigint not null comment '上级组织机构主键',
   TYPE_ID              bigint not null comment '类型',
   CREATOR              bigint not null comment '创建者',
   CREATE_TIME          datetime not null comment '创建时间',
   MEMO                 varchar(255) comment '备注',
   SEQ                  int not null comment '排序',
   COM_ID               varchar(32) not null comment '公司',
primary key (id)
);

alter table t_emc_org comment '组织机构表';


/*==============================================================*/
/* Table: t_emc_unit_ban                                        */
/*==============================================================*/
create table t_emc_unit_ban
(
   ID                   varchar(32) not null comment 'ID',
   BAN_NAME             varchar(64) not null comment '名称',
   PROVINCE_ID          varchar(12) not null comment '省主键 关联行政区划表T_ECC_SYS_ADMINISTRATIVE',
   CITY_ID              varchar(12) not null comment '市主键',
   COUNTY_ID            varchar(12) not null comment '县主键',
   TOWN_ID              varchar(12) comment '乡主键',
   VILLAGE_ID           varchar(12) comment '村主键',
   ADDR                 varchar(128) not null comment '详细地址',
   COMMUNITY_ID         varchar(32) comment '小区',
   ORG_ID               bigint not null comment '所属机构',
   COM_ID               varchar(32) not null comment '公司',
primary key (id)
);

alter table t_emc_unit_ban comment '楼栋基本信息表';


/*==============================================================*/
/* Table: t_emc_unit_cell                                       */
/*==============================================================*/
create table t_emc_unit_cell
(
   ID                   varchar(32) not null comment 'ID',
   NAME                 varchar(64) not null comment '名称',
   BAN_ID               varchar(32) not null comment '楼栋',
   ORG_ID               bigint not null comment '所属机构',
   COM_ID               varchar(32) not null comment '公司',
primary key (id)
);

alter table t_emc_unit_cell comment '单元基本信息表';


/*==============================================================*/
/* Table: t_emc_unit_community                                  */
/*==============================================================*/
create table t_emc_unit_community
(
   ID                   varchar(32) not null comment 'ID',
   COMMUNITY_NAME       varchar(64) not null comment '名称',
   ORG_ID               bigint not null comment '所属机构',
   COM_ID               varchar(32) not null comment '公司',
primary key (id)
);

alter table t_emc_unit_community comment '小区基本信息表';


/*==============================================================*/
/* Table: t_emc_unit_feed                                       */
/*==============================================================*/
create table t_emc_unit_feed
(
   ID                   varchar(32) not null comment '主键',
   FEED_NAME            varchar(64) not null comment '热源名称',
   FEED_CODE            varchar(32) comment '热源编码',
   FEED_TYPE            tinyint not null comment '热源性质 来源字典表 1:热电 2:区域锅炉房 3:核电 4:工业余热 ',
   HEAT_TYPE            tinyint not null comment '供热类型 来源字典表  区域供热 集中供热  尖峰供热',
   INSTALL_CAPACITY     double comment '装机容量(MW)',
   HEAT_CAPACITY        double default 0 comment '供热能力(㎡)',
   BOILER_NUM           int default 0 comment '锅炉数量',
   STEAMTURBINE_NUM     int default 0 comment '汽机数量',
   PROVINCE_ID          varchar(12) not null comment '省主键 关联行政区划表T_ECC_SYS_ADMINISTRATIVE',
   CITY_ID              varchar(12) not null comment '市主键',
   COUNTY_ID            varchar(12) not null comment '县主键',
   TOWN_ID              varchar(12) comment '乡主键',
   VILLAGE_ID           varchar(12) comment '村主键',
   ADDR                 varchar(128) not null comment '详细地址',
   LNG                  double(10,6) comment '经度',
   LAT                  double(10,6) comment '纬度',
   HEAT_AREA            double not null default 0 comment '供热面积',
   ORG_ID               bigint not null comment '所属机构',
   NET_ID               varchar(32) comment '所属管网',
   LINE_ID              varchar(32) comment '所属管线',
   COM_ID               varchar(32) not null comment '公司',
primary key (id)
);

alter table t_emc_unit_feed comment '用能单位-热源基本信息表';


/*==============================================================*/
/* Table: t_emc_unit_line                                       */
/*==============================================================*/
create table t_emc_unit_line
(
   ID                   varchar(32) not null comment '主键',
   LINE_NAME            varchar(64) not null comment '名称',
   LINE_CODE            varchar(32) not null comment '代码',
   NET_TYPE_ID          tinyint not null comment '管线类型 来源字典表 干线、支线、连通线、户线',
   LENGTH               double not null default 0 comment '管线长度',
   CELL_NUM             int default 0 comment '小室数量',
   PART_NUM             int default 0 comment '管段数量',
   MEDIUM               varchar(32) comment '输送介质',
   ORG_ID               bigint not null comment '所属机构',
   COM_ID               varchar(32) not null comment '公司',
primary key (id)
);

alter table t_emc_unit_line comment '用能单位-二次线基本信息表';


/*==============================================================*/
/* Table: t_emc_unit_net                                        */
/*==============================================================*/
create table t_emc_unit_net
(
   ID                   varchar(32) not null comment '主键',
   NET_NAME             varchar(64) not null comment '名称',
   NET_CODE             varchar(32) not null comment '代码',
   NET_TYPE_ID          tinyint not null comment '管线类型 来源字典表 干线、支线、连通线、户线',
   LENGTH               double not null default 0 comment '管线长度',
   CELL_NUM             int default 0 comment '小室数量',
   PART_NUM             int default 0 comment '管段数量',
   MEDIUM               varchar(32) comment '输送介质',
   ORG_ID               bigint not null comment '所属机构',
   COM_ID               varchar(32) not null comment '公司',
primary key (id)
);

alter table t_emc_unit_net comment '用能单位-一次网基本信息表';


/*==============================================================*/
/* Table: t_emc_unit_room                                       */
/*==============================================================*/
create table t_emc_unit_room
(
   ID                   varchar(32) not null comment 'ID',
   ROOM_NAME            varchar(64) not null comment '名称',
   ROOM_CODE            varchar(32) not null comment '代码',
   HEAT_AREA            double not null default 0 comment '供热面积',
   ORG_ID               bigint not null comment '所属机构',
   LINE_ID              varchar(32) not null comment '所属管线',
   COMMUNITY_ID         varchar(32) comment '小区',
   BAN_ID               varchar(32) comment '楼栋',
   CELL_ID              varchar(32) comment '单元',
   COM_ID               varchar(32) not null comment '公司',
primary key (id)
);

alter table t_emc_unit_room comment '用能单位-户基本信息表';


/*==============================================================*/
/* Table: t_emc_unit_station                                    */
/*==============================================================*/
create table t_emc_unit_station
(
   ID                   varchar(32) not null comment '主键',
   MANAGE_TYPE_ID       tinyint not null comment '管理类型 来源于字典表 0-统管 1-自管  2-代管',
   PROVINCE_ID          varchar(12) not null comment '省主键 关联行政区划表T_ECC_SYS_ADMINISTRATIVE',
   CITY_ID              varchar(12) not null comment '市主键',
   COUNTY_ID            varchar(12) not null comment '县主键',
   TOWN_ID              varchar(12) comment '乡主键',
   VILLAGE_ID           varchar(12) comment '村主键',
   ADDR                 varchar(128) not null comment '详细地址',
   LNG                  double(10,6) comment '经度',
   LAT                  double(10,6) comment '纬度',
   STATION_NAME         varchar(64) not null comment '名称',
   STATION_CODE         varchar(32) not null comment '代码',
   HEAT_AREA            double not null default 0 comment '供热面积',
   ORG_ID               bigint not null comment '所属机构',
   NET_ID               varchar(32) comment '所属管网',
   FEED_ID              varchar(32) comment '所属热源',
   LINE_ID              varchar(32) not null comment '所属管线',
   COM_ID               varchar(32) not null comment '公司',
primary key (id)
);

alter table t_emc_unit_station comment '用能单位-热力站基本信息表';


/*==============================================================*/
/* Table: t_emc_unit_type_rel                                   */
/*==============================================================*/
create table t_emc_unit_type_rel
(
   TYPE_ID              varchar(32) not null comment '类型',
   COM_ID               varchar(32) not null comment '公司'
);

alter table t_emc_unit_type_rel comment '公司类型关系表';

