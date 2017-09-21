package com.huak.health.vo;

import java.io.Serializable;

public class MetaCompanyA implements Serializable{

    private String tag;

    private String comId;

    private String unitId;

    private Byte unitType;

    private String unitName;

    private String cname;

    public MetaCompanyA(String tag, String comId, String unitId, Byte unitType, String unitName, String cname) {
        this.tag = tag;
        this.comId = comId;
        this.unitId = unitId;
        this.unitType = unitType;
        this.unitName = unitName;
        this.cname = cname;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public Byte getUnitType() {
        return unitType;
    }

    public void setUnitType(Byte unitType) {
        this.unitType = unitType;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}