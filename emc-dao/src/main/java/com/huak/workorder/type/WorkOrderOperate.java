package com.huak.workorder.type;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.workorder.type<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017-10-25<BR>
 * Description:  操作翻译   <BR>
 * Function List:  <BR>
 */
public enum WorkOrderOperate {
    A_SAVE(11,"保存工单"),
    A_SEND(12,"派送工单给"),
    A_SAVE_SEND(13,"保存并派送工单"),
    A_RESET(14,"重新派送工单，流程结束"),
    A_CONFIRM(15,"确认工单，流程结束"),
    A_CLOSE(16,"关闭工单，流程结束"),

    B_TAKING(21,"接受工单"),
    B_BACK(22,"退回工单"),
    B_SEND(23,"派送工单"),
    B_FINISH(24,"完成工单"),

    C_TAKING(31,"接受工单"),
    C_BACK(32,"退回工单"),
    C_FINISH(33,"完成工单");

    private int key;
    private String value;

    WorkOrderOperate(int key, String value) {
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
