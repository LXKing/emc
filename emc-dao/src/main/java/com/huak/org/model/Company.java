package com.huak.org.model;

import java.io.Serializable;

public class Company implements Serializable{
    private String id;

    private String cname;

    private String nextDes;

    public String getNextDes() {
        return nextDes;
    }

    public void setNextDes(String nextDes) {
        this.nextDes = nextDes;
    }

    public Company(String id, String cname) {
        this.id = id;
        this.cname = cname;
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