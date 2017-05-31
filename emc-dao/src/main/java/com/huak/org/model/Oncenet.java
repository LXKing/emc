package com.huak.org.model;

import java.io.Serializable;

public class Oncenet implements Serializable{
    private Long id;

    private String netTypeId;

    private Double length;

    private Integer cellNum;

    private Integer partNum;

    private String medium;

    public Oncenet() {
        super();
    }

    public Oncenet(Long id, String netTypeId, Double length, Integer cellNum, Integer partNum, String medium) {
        this.id = id;
        this.netTypeId = netTypeId;
        this.length = length;
        this.cellNum = cellNum;
        this.partNum = partNum;
        this.medium = medium;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNetTypeId() {
        return netTypeId;
    }

    public void setNetTypeId(String netTypeId) {
        this.netTypeId = netTypeId;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Integer getCellNum() {
        return cellNum;
    }

    public void setCellNum(Integer cellNum) {
        this.cellNum = cellNum;
    }

    public Integer getPartNum() {
        return partNum;
    }

    public void setPartNum(Integer partNum) {
        this.partNum = partNum;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}