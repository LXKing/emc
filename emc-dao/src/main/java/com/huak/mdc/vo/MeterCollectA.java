package com.huak.mdc.vo;

import com.huak.mdc.model.MeterCollect;

import java.io.Serializable;

public class MeterCollectA extends MeterCollect implements Serializable{


    private String unitname;

    @Override
    public String getUnitname() {
        return unitname;
    }

    @Override
    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }
}