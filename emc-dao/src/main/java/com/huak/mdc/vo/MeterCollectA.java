package com.huak.mdc.vo;

import com.huak.mdc.model.MeterCollect;

import java.io.Serializable;

public class MeterCollectA extends MeterCollect implements Serializable{


    private static final long serialVersionUID = 9090351763739071516L;

    public MeterCollectA(String id, String code, String name, String serialNo, String energyTypeId, Byte isreal, Byte istotal, String unitId, Byte unitType, Byte isauto, String depict, String tag, Byte coef, String formula, Byte isprestore, Byte isdelete, String comId, String unitname) {
        super(id, code, name, serialNo, energyTypeId, isreal, istotal, unitId, unitType, isauto, depict, tag, coef, formula, isprestore, isdelete, comId);
        this.unitname = unitname;
    }

    private String unitname;

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }
}