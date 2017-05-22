package com.huak.org.model;

public class Company {
    private String id;

    private String cname;

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