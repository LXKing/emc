package com.huak.workorder.model;

public class WorkOrderRecord {
    private String id;

    private String code;

    private Byte beforStatus;

    private String operateTime;

    private String opertor;

    private String sendee;

    private Byte afterStatus;

    private String des;

    public WorkOrderRecord(String id, String code, Byte beforStatus, String operateTime, String opertor, String sendee, Byte afterStatus, String des) {
        this.id = id;
        this.code = code;
        this.beforStatus = beforStatus;
        this.operateTime = operateTime;
        this.opertor = opertor;
        this.sendee = sendee;
        this.afterStatus = afterStatus;
        this.des = des;
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

    public Byte getBeforStatus() {
        return beforStatus;
    }

    public void setBeforStatus(Byte beforStatus) {
        this.beforStatus = beforStatus;
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

    public Byte getAfterStatus() {
        return afterStatus;
    }

    public void setAfterStatus(Byte afterStatus) {
        this.afterStatus = afterStatus;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }
}