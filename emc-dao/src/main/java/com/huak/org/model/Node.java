package com.huak.org.model;

import java.io.Serializable;

public class Node implements Serializable{
    private String id;

    private String manageTypeId;

    private String provinceId;

    private String cityId;

    private String countyId;

    private String townId;

    private String villageId;

    private String addr;

    private Double lng;

    private Double lat;

    private Double publicArea;

    private Double dwellArea;
    private Byte status;



    public Node(String id, String manageTypeId, String provinceId, String cityId, String countyId, String townId, String villageId, String addr, Double lng, Double lat, Double publicArea, Double dwellArea,Byte status) {
        this.id = id;
        this.manageTypeId = manageTypeId;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.countyId = countyId;
        this.townId = townId;
        this.villageId = villageId;
        this.addr = addr;
        this.lng = lng;
        this.lat = lat;
        this.publicArea = publicArea;
        this.dwellArea = dwellArea;
        this.status = status;

    }

    public Node() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getManageTypeId() {
        return manageTypeId;
    }

    public void setManageTypeId(String manageTypeId) {
        this.manageTypeId = manageTypeId == null ? null : manageTypeId.trim();
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId == null ? null : provinceId.trim();
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId == null ? null : countyId.trim();
    }

    public String getTownId() {
        return townId;
    }

    public void setTownId(String townId) {
        this.townId = townId == null ? null : townId.trim();
    }

    public String getVillageId() {
        return villageId;
    }

    public void setVillageId(String villageId) {
        this.villageId = villageId == null ? null : villageId.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getPublicArea() {
        return publicArea;
    }

    public void setPublicArea(Double publicArea) {
        this.publicArea = publicArea;
    }

    public Double getDwellArea() {
        return dwellArea;
    }

    public void setDwellArea(Double dwellArea) {
        this.dwellArea = dwellArea;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}