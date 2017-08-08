package com.huak.org.model;

import java.io.Serializable;

public class Company implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;

    private String cname;

    private String nextDes;

    private String wcode;

    private String wname;

    private String tableName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    public String getWcode() {
        return wcode;
    }

    public void setWcode(String wcode) {
        this.wcode = wcode;
    }

    public String getNextDes() {
        return nextDes;
    }

    public void setNextDes(String nextDes) {
        this.nextDes = nextDes;
    }

    public Company(String id, String cname,String nextDes,String wcode,String wname,String tableName) {
        this.id = id;
        this.cname = cname;
        this.nextDes = nextDes;
        this.wcode = wcode;
        this.wname = wname;
        this.tableName = tableName;
    }
    public Company() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }
}