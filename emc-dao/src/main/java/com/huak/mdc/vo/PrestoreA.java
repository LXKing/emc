package com.huak.mdc.vo;

import java.io.Serializable;
import java.util.Date;

public class PrestoreA implements Serializable{

    private static final long serialVersionUID = 7055745417281150056L;
    private String id;

    private String collectId;

    private Date prestoreTime;

    private Double usedNum;

    private Double prestoreNum;

    private Date createTime;

    private String crestor;

    private String unitName;
    public PrestoreA(String id, String collectId, Date prestoreTime, Double usedNum, Double prestoreNum, Date createTime, String crestor,String unitName) {
        this.id = id;
        this.collectId = collectId;
        this.prestoreTime = prestoreTime;
        this.usedNum = usedNum;
        this.prestoreNum = prestoreNum;
        this.createTime = createTime;
        this.crestor = crestor;
        this.unitName=unitName;
    }

    public PrestoreA() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId == null ? null : collectId.trim();
    }

    public Date getPrestoreTime() {
        return prestoreTime;
    }

    public void setPrestoreTime(Date prestoreTime) {
        this.prestoreTime = prestoreTime;
    }

    public Double getUsedNum() {
        return usedNum;
    }

    public void setUsedNum(Double usedNum) {
        this.usedNum = usedNum;
    }

    public Double getPrestoreNum() {
        return prestoreNum;
    }

    public void setPrestoreNum(Double prestoreNum) {
        this.prestoreNum = prestoreNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCrestor() {
        return crestor;
    }

    public void setCrestor(String crestor) {
        this.crestor = crestor == null ? null : crestor.trim();
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}