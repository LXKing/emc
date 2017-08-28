package com.huak.mdc.model;

import java.io.Serializable;
/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.mdc.model<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/28<BR>
 * Description: 采集元数据    <BR>
 * Function List:  <BR>
 */
public class MetaData  implements Serializable {
    private static final long serialVersionUID = -4795427870423268586L;
    private String tag;

    private String collectTime;

    private String collectType;

    private Double collectValue;

    public MetaData(String tag, String collectTime, String collectType, Double collectValue) {
        this.tag = tag;
        this.collectTime = collectTime;
        this.collectType = collectType;
        this.collectValue = collectValue;
    }

    public MetaData() {
        super();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(String collectTime) {
        this.collectTime = collectTime;
    }

    public String getCollectType() {
        return collectType;
    }

    public void setCollectType(String collectType) {
        this.collectType = collectType == null ? null : collectType.trim();
    }

    public Double getCollectValue() {
        return collectValue;
    }

    public void setCollectValue(Double collectValue) {
        this.collectValue = collectValue;
    }
}