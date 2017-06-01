package com.huak.org.model;

import java.io.Serializable;

public class Org implements Serializable{
    private Long id;

    private String orgCode;

    private String orgName;

    private String shortName;

    private Long pOrgId;

    private Byte typeId;

    private String creator;

    private String  createTime;

    private String memo;

    private Integer seq;

    private String comId;


    public Org() {
        super();
    }

    public Org(Long id, String orgCode, String orgName, String shortName, Long pOrgId, Byte typeId, String creator, String createTime, String memo, Integer seq, String comId) {
        this.id = id;
        this.orgCode = orgCode;
        this.orgName = orgName;
        this.shortName = shortName;
        this.pOrgId = pOrgId;
        this.typeId = typeId;
        this.creator = creator;
        this.createTime = createTime;
        this.memo = memo;
        this.seq = seq;
        this.comId = comId;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Long getpOrgId() {
        return pOrgId;
    }

    public void setpOrgId(Long pOrgId) {
        this.pOrgId = pOrgId;
    }

    public Byte getTypeId() {
        return typeId;
    }

    public void setTypeId(Byte typeId) {
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
        this.memo = memo;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }
}