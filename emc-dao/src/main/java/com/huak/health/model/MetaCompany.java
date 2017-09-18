package com.huak.health.model;

import java.io.Serializable;

public class MetaCompany implements Serializable{
    private static final long serialVersionUID = 5285237199107532997L;
    private String tag;

    private String comId;

    private String unitId;

    private Byte unitType;

    public MetaCompany(String tag, String comId, String unitId, Byte unitType) {
        this.tag = tag;
        this.comId = comId;
        this.unitId = unitId;
        this.unitType = unitType;
    }

    public MetaCompany() {
        super();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId == null ? null : comId.trim();
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId == null ? null : unitId.trim();
    }

    public Byte getUnitType() {
        return unitType;
    }

    public void setUnitType(Byte unitType) {
        this.unitType = unitType;
    }
}