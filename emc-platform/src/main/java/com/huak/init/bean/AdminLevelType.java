package com.huak.init.bean;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:eccp<BR>
 * File name:  com.huak.sys.bean<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/10/28<BR>
 * Description: 行政区划等级枚举   <BR>
 * Function List:  <BR>
 */
public enum AdminLevelType {
    PROVINCE(1,"省级/直辖市"),
    CITY(2,"市级/区"),
    COUNTY(3,"县级"),
    TOWN(4,"镇"),
    VILLAGE(5,"村");

    private int key;
    private String value;

    AdminLevelType(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
