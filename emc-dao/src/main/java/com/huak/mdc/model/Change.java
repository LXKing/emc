package com.huak.mdc.model;

import java.util.Date;

public class Change {
    private String id;

    private String collectId;

    private Date changeTime;

    private Double usedNum;

    private Double newNum;

    private Double usedCoef;

    private Double newCoef;

    private Date createTime;

    private String crestor;

    public Change(String id, String collectId, Date changeTime, Double usedNum, Double newNum, Double usedCoef, Double newCoef, Date createTime, String crestor) {
        this.id = id;
        this.collectId = collectId;
        this.changeTime = changeTime;
        this.usedNum = usedNum;
        this.newNum = newNum;
        this.usedCoef = usedCoef;
        this.newCoef = newCoef;
        this.createTime = createTime;
        this.crestor = crestor;
    }

    public Change() {
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

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public Double getUsedNum() {
        return usedNum;
    }

    public void setUsedNum(Double usedNum) {
        this.usedNum = usedNum;
    }

    public Double getNewNum() {
        return newNum;
    }

    public void setNewNum(Double newNum) {
        this.newNum = newNum;
    }

    public Double getUsedCoef() {
        return usedCoef;
    }

    public void setUsedCoef(Double usedCoef) {
        this.usedCoef = usedCoef;
    }

    public Double getNewCoef() {
        return newCoef;
    }

    public void setNewCoef(Double newCoef) {
        this.newCoef = newCoef;
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
}