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
    public final static Navigation SECOND_ENERGY = new Navigation(HOME,"能耗分析","/energy/monitor/tsec");
    public final static Navigation SECOND_CONS = new Navigation(HOME,"单耗分析","/cons/analysis/tsec");
    public final static Navigation THIRD_ENERGY_WATER = new Navigation(SECOND_ENERGY,"水能耗分析","/third/energy/page/1");
    public final static Navigation THIRD_ENERGY_ELECTRIC = new Navigation(SECOND_ENERGY,"电能耗分析","/third/energy/page/2");
    public final static Navigation THIRD_ENERGY_GAS = new Navigation(SECOND_ENERGY,"气能耗分析","/third/energy/page/3");
    public final static Navigation THIRD_ENERGY_HEAT = new Navigation(SECOND_ENERGY,"热能耗分析","/third/energy/page/4");
    public final static Navigation THIRD_ENERGY_COAL = new Navigation(SECOND_ENERGY,"煤能耗分析","/third/energy/page/5");
    public final static Navigation THIRD_ENERGY_OIL = new Navigation(SECOND_ENERGY,"油能耗分析","/third/energy/page/6");
    public final static Navigation THIRD_CONS_WATER = new Navigation(SECOND_CONS,"水单耗分析","/third/analysis/page/1");
    public final static Navigation THIRD_CONS_ELECTRIC = new Navigation(SECOND_CONS,"电单耗分析","/third/analysis/page/2");
    public final static Navigation THIRD_CONS_GAS = new Navigation(SECOND_CONS,"气单耗分析","/third/analysis/page/3");
    public final static Navigation THIRD_CONS_HEAT = new Navigation(SECOND_CONS,"热单耗分析","/third/analysis/page/4");
    public final static Navigation THIRD_CONS_COAL = new Navigation(SECOND_CONS,"煤单耗分析","/third/analysis/page/5");
    public final static Navigation THIRD_CONS_OIL = new Navigation(SECOND_CONS,"油单耗分析","/third/analysis/page/6");

    /*能耗分析*/


    /*成本管控*/

    /*碳排管理*/

    /*报警管理*/

    /*项目后评估*/

    /*后台*/

    /*全部*/
    public final static List<Navigation> NAVIGATIONS = new ArrayList<>();

    static{
        NAVIGATIONS.add(HOME);
        NAVIGATIONS.add(SECOND_ENERGY);
        NAVIGATIONS.add(SECOND_CONS);
        NAVIGATIONS.add(THIRD_ENERGY_WATER);
        NAVIGATIONS.add(THIRD_ENERGY_ELECTRIC);
        NAVIGATIONS.add(THIRD_ENERGY_GAS);
        NAVIGATIONS.add(THIRD_ENERGY_HEAT);
        NAVIGATIONS.add(THIRD_ENERGY_COAL);
        NAVIGATIONS.add(THIRD_ENERGY_OIL);
        NAVIGATIONS.add(THIRD_CONS_WATER);
        NAVIGATIONS.add(THIRD_CONS_ELECTRIC);
        NAVIGATIONS.add(THIRD_CONS_GAS);
        NAVIGATIONS.add(THIRD_CONS_HEAT);
        NAVIGATIONS.add(THIRD_CONS_COAL);
        NAVIGATIONS.add(THIRD_CONS_OIL);
    }

}
