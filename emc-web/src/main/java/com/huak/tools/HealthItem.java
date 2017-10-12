package com.huak.tools;

import java.util.*;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.tools<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/10/9<BR>
 * Description:   健康指数检测项  <BR>
 * Function List:  <BR>
 */
public class HealthItem {

   static class Item{
        private String title;
        private String img;
        private String parentName;
        private String parentTitle;

       public String getTitle() {
           return title;
       }

       public void setTitle(String title) {
           this.title = title;
       }

       public String getImg() {
           return img;
       }

       public void setImg(String img) {
           this.img = img;
       }

       public String getParentName() {
           return parentName;
       }

       public void setParentName(String parentName) {
           this.parentName = parentName;
       }

       public String getParentTitle() {
           return parentTitle;
       }

       public void setParentTitle(String parentTitle) {
           this.parentTitle = parentTitle;
       }

       Item(String title, String img, String parentName, String parentTitle) {
            this.title = title;
            this.img = img;
            this.parentName = parentName;
            this.parentTitle = parentTitle;
        }
    }
    public static List<Item> HEALTH_ITEM = new ArrayList<>();

    public static Map<String,Object> map = new HashMap<>();



    static {
        //工况运行
        HEALTH_ITEM.add(new Item("一次供温","test-01-08","GKYX","工况运行"));
        HEALTH_ITEM.add(new Item("一次回温","test-01-10","GKYX","工况运行"));
        HEALTH_ITEM.add(new Item("二次供温","test-01-12","GKYX","工况运行"));
        HEALTH_ITEM.add(new Item("二次回温","test-01-14","GKYX","工况运行"));
        HEALTH_ITEM.add(new Item("一次供水","test-01-07","GKYX","工况运行"));
        HEALTH_ITEM.add(new Item("一次回水","test-01-09","GKYX","工况运行"));
        HEALTH_ITEM.add(new Item("二次供水","test-01-11","GKYX","工况运行"));
        HEALTH_ITEM.add(new Item("二次回水","test-01-13","GKYX","工况运行"));
        //经济运行
        HEALTH_ITEM.add(new Item("水单耗","test-02-03","JJYX","经济运行"));
        HEALTH_ITEM.add(new Item("水效率","test-02-03","JJYX","经济运行"));
        HEALTH_ITEM.add(new Item("电单耗","test-02-05","JJYX","经济运行"));
        HEALTH_ITEM.add(new Item("电效率","test-02-05","JJYX","经济运行"));
        HEALTH_ITEM.add(new Item("气单耗","test-02-04","JJYX","经济运行"));
        HEALTH_ITEM.add(new Item("气效率","test-02-04","JJYX","经济运行"));
        HEALTH_ITEM.add(new Item("煤单耗","test-02-07","JJYX","经济运行"));
        HEALTH_ITEM.add(new Item("煤效率","test-02-07","JJYX","经济运行"));
        HEALTH_ITEM.add(new Item("油单耗","test-02-01","JJYX","经济运行"));
        HEALTH_ITEM.add(new Item("油效率","test-02-01","JJYX","经济运行"));
        HEALTH_ITEM.add(new Item("热单耗","test-02-02","JJYX","经济运行"));
        //服务情况
        HEALTH_ITEM.add(new Item("万平米工单","test-04-03","FWQK","服务情况"));
        //室温报警
        HEALTH_ITEM.add(new Item("室温报警","test-01-03","SWBJ","室温报警"));
        map.put("一次供温","GKYX");
        map.put("一次回温","GKYX");
        map.put("二次供温","GKYX");
        map.put("二次回温","GKYX");
        map.put("一次供水","GKYX");
        map.put("一次回水","GKYX");
        map.put("二次供水","GKYX");
        map.put("二次回水","GKYX");
        map.put("水单耗","JJYX");
        map.put("水效率","JJYX");
        map.put("电单耗","JJYX");
        map.put("电效率","JJYX");
        map.put("气单耗","JJYX");
        map.put("气效率","JJYX");
        map.put("煤单耗","JJYX");
        map.put("煤效率","JJYX");
        map.put("油单耗","JJYX");
        map.put("油效率","JJYX");
        map.put("热单耗","JJYX");
        map.put("万平米工单","FWQK");
        map.put("室温报警","SWBJ");
    }
}
