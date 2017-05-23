package com.huak.org.model;

import java.io.Serializable;
import java.util.Date;

public class Org implements Serializable{
    private String id;

    private String comId;

    private String orgCode;

    private String orgName;

    private String shortName;

    private String pOrgId;

    private String typeId;

    private String creator;

    private String createTime;

    private String memo;

    private Integer seq;

    private Double area;

    public Org(String id, String comId, String orgCode, String orgName, String shortName, String pOrgId, String typeId, String creator, String createTime, String memo, Integer seq, Double area) {
        this.id = id;
        this.comId = comId;
        this.orgCode = orgCode;
        this.orgName = orgName;
        this.shortName = shortName;
        this.pOrgId = pOrgId;
        this.typeId = typeId;
        this.creator = creator;
        this.createTime = createTime;
        this.memo = memo;
        this.seq = seq;
        this.area = area;
    }

    public Org() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId == null ? null : comId.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public String getpOrgId() {
        return pOrgId;
    }

    public void setpOrgId(String pOrgId) {
        this.pOrgId = pOrgId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }
}