package com.huak.org.model.vo;

import java.io.Serializable;

public class Secondnet implements Serializable{
    private String id;

    private String netTypeId;

    private Double length;

    private Integer cellNum;

    private Integer partNum;

    private String medium;

    public Secondnet(String id, String netTypeId, Double length, Integer cellNum, Integer partNum, String medium) {
        this.id = id;
        this.netTypeId = netTypeId;
        this.length = length;
        this.cellNum = cellNum;
        this.partNum = partNum;
        this.medium = medium;
    }

    public Secondnet() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNetTypeId() {
        return netTypeId;
    }

    public void setNetTypeId(String netTypeId) {
        this.netTypeId = netTypeId == null ? null : netTypeId.trim();
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
        this.medium = medium == null ? null : medium.trim();
    }
}