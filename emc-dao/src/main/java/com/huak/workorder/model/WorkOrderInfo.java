package com.huak.workorder.model;

import java.io.Serializable;

public class WorkOrderInfo implements Serializable{
    private static final long serialVersionUID = -8609021829001947047L;
    private String id;

    private String code;

    private Byte type;

    private String name;

    private String content;

    private String startTime;

    private String finishTime;

    private String createTime;

    private String creator;

    private Byte status;

    private String finish;

    private String actualFinishTime;

    private String comid;

    public WorkOrderInfo(String id, String code, Byte type, String name, String content, String startTime, String finishTime, String createTime, String creator, Byte status, String finish, String actualFinishTime, String comid) {
        this.id = id;
        this.code = code;
        this.type = type;
        this.name = name;
        this.content = content;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.createTime = createTime;
        this.creator = creator;
        this.status = status;
        this.finish = finish;
        this.actualFinishTime = actualFinishTime;
        this.comid = comid;
    }

    public WorkOrderInfo() {
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

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish == null ? null : finish.trim();
    }

    public String getActualFinishTime() {
        return actualFinishTime;
    }

    public void setActualFinishTime(String actualFinishTime) {
        this.actualFinishTime = actualFinishTime;
    }

    public String getComid() {
        return comid;
    }

    public void setComid(String comid) {
        this.comid = comid == null ? null : comid.trim();
    }
}