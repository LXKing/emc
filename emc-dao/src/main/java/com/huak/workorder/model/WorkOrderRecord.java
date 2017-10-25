package com.huak.workorder.model;

import java.io.Serializable;

public class WorkOrderRecord implements Serializable{
    private static final long serialVersionUID = 7503704037012448772L;
    private String id;

    private String code;

    private Byte status;

    private String operateTime;

    private String opertor;

    private String sendee;

    public WorkOrderRecord(String id, String code, Byte status, String operateTime, String opertor, String sendee) {
        this.id = id;
        this.code = code;
        this.status = status;
        this.operateTime = operateTime;
        this.opertor = opertor;
        this.sendee = sendee;
    }

    public WorkOrderRecord() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getOpertor() {
        return opertor;
    }

    public void setOpertor(String opertor) {
        this.opertor = opertor == null ? null : opertor.trim();
    }

    public String getSendee() {
        return sendee;
    }

    public void setSendee(String sendee) {
        this.sendee = sendee == null ? null : sendee.trim();
    }
}