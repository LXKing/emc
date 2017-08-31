package com.huak.mdc.model;

import java.io.Serializable;
import java.util.Date;

public class Prestore implements Serializable{

    private static final long serialVersionUID = -2545246791139673571L;
    private String id;

    private String collectId;

    private Date prestoreTime;

    private Double usedNum;

    private Double prestoreNum;

    private Date createTime;

    private String crestor;

    public Prestore(String id, String collectId, Date prestoreTime, Double usedNum, Double prestoreNum, Date createTime, String crestor) {
        this.id = id;
        this.collectId = collectId;
        this.prestoreTime = prestoreTime;
        this.usedNum = usedNum;
        this.prestoreNum = prestoreNum;
        this.createTime = createTime;
        this.crestor = crestor;
    }

    public Prestore() {
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
}