package com.huak.tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.tools<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/25<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public class NavigationConstant {


    /*首页*/
    public final static Navigation HOME = new Navigation(null,"首页","/index");

    /*能耗分析*/
    public final static Navigation ENERGY = new Navigation(HOME,"能耗分析","/energy/analysis/index");

    /*成本管控*/

    /*碳排管理*/

    /*报警管理*/

    /*项目后评估*/

    /*后台*/

    /*全部*/
    public final static List<Navigation> NAVIGATIONS = new ArrayList<>();

    static{
        NAVIGATIONS.add(HOME);
        NAVIGATIONS.add(ENERGY);
    }

}
