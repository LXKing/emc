package com.huak.workorder.model;

import java.io.Serializable;
import java.util.Date;

public class WorkOrderInfo implements Serializable{
    private static final long serialVersionUID = -1758831625704939057L;
    private String id;

    private String code;

    private Byte type;

    private String name;

    private String content;

    private Date startTime;

    private Date finishTime;

    private Date createTime;

    private String creator;

    private Byte status;

    private String finish;

    private Date actualFinishTime;

    private String comid;

    private String reason;

    public WorkOrderInfo(String id, String code, Byte type, String name, String content, Date startTime, Date finishTime, Date createTime, String creator, Byte status, String finish, Date actualFinishTime, String comid, String reason) {
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
        this.reason = reason;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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

    public Date getActualFinishTime() {
        return actualFinishTime;
    }

    public void setActualFinishTime(Date actualFinishTime) {
        this.actualFinishTime = actualFinishTime;
    }

    public String getComid() {
        return comid;
    }

    public void setComid(String comid) {
        this.comid = comid == null ? null : comid.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }
}