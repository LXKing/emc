package com.huak.org.model;

import java.io.Serializable;

public class Company implements Serializable{
    private String id;

    private String cname;

    private String nextDes;

    private String wcode;

    private String wname;

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

    public Company(String id, String cname,String nextDes,String wcode,String wname) {
        this.id = id;
        this.cname = cname;
        this.nextDes = nextDes;
        this.wcode = wcode;
        this.wname = wname;
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