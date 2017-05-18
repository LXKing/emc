package com.huak.common;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:eccp<BR>
 * File name:  com.huak.common<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/26<BR>
 * Description:    常量类 <BR>
 * Function List:  <BR>
 */
public class Constants {
    /* 分页常量 */
    public final static String OFFSET = "offset";
    public final static String LIMIT = "limit";
    public final static String PAGE = "page";
    public final static String DATA = "data";
    public final static String PAGENO = "pageNo";
    /* 分页返回名称 */
    public final static String LIST = "list";

    /* JSON返回boolean常量名称 */
    public final static String FLAG = "flag";
    /* JSON返回消息常量名称 */
    public final static String MSG = "msg";

    /*修改页面返回实体对象*/
    public final static String OBJECT = "object";

    /*授权菜单功能*/
    public final static String GRANT_MENU_AFTER = "grantMenuAfter";
    /*授权菜单功能*/
    public final static String GRANT_MENU_BEFORE = "grantMenuBefore";
    /*子菜单*/
    public final static String CHILDREN_MENUS = "children";
    /*功能*/
    public final static String FUNCTIONS = "functions";
    /*新增或者保存 0 SAVE 1 UPDATE*/
    public final static String SAVE_OR_UPDATE = "saveOrUpdate";

    /*字典*/
    public final static String SYS_DIC = "sysDic";
    /*后台系统*/
    public final static String PLATFORM = "platform";
    /*前台系统*/
    public final static String WEB = "web";
    /*字典 MAP<类型标识，字典列表>*/
    public final static String DIC = "dicList";
    /*字典 MAP<类型主键，字典列表>*/
    public final static String DIC_LIST = "diclist";
    /*字典标识MAP<标识,说明>*/
    public final static String DIC_DES = "dicDes";
    /*字典类型MAP<类型主键,说明>*/
    public final static String DIC_MAP = "dicType";
    /*字典类型MAP<类型标识,类型主键>*/
    public final static String DIC_TYPE_MAP = "dicTypeMap";
    /*字典类型MAP<类型标识,类型主键>*/
    public final static String DIC_DES_MAP = "dicDesMap";

    /*session user key*/
    public final static String SESSION_KEY = "_user";

    /*session timeout status*/
    public final static String SESSION_STATUS = "sessionStatus";

    /* session auth key*/
    public final static String SESSION_AUTH_KEY = "_auth";

    /* session role key*/
    public final static String SESSION_ROLE_KEY = "_role";

    /* session menu key*/
    public final static String SESSION_MENU_KEY = "_menu";

    /* session cell key*/
    public final static String SESSION_CELL_KEY = "_cell";

    /* session part key*/
    public final static String SESSION_PART_KEY = "_part";

    /* 管理员账号*/
    public final static String ADMIN_LOGIN = "admin";
}
