package com.huak.org.model;

import java.io.Serializable;

public class Room implements Serializable {
    private String id;

    private Double dwellArea;

    public Room(String id, Double dwellArea) {
        this.id = id;
        this.dwellArea = dwellArea;
    }

    public Room() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Double getDwellArea() {
        return dwellArea;
    }

    public void setDwellArea(Double dwellArea) {
        this.dwellArea = dwellArea;
    }
}