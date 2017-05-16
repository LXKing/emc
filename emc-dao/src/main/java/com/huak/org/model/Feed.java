package com.huak.org.model;

import java.io.Serializable;

public class Feed implements Serializable {
    private String id;

    private Byte feedType;

    private Byte heatType;

    private Double installCapacity;

    private Double heatCapacity;

    private Integer boilerNum;

    private Integer steamturbineNum;

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

    public Feed(String id, Byte feedType, Byte heatType, Double installCapacity, Double heatCapacity, Integer boilerNum, Integer steamturbineNum, String provinceId, String cityId, String countyId, String townId, String villageId, String addr, Double lng, Double lat, Double publicArea, Double dwellArea) {
        this.id = id;
        this.feedType = feedType;
        this.heatType = heatType;
        this.installCapacity = installCapacity;
        this.heatCapacity = heatCapacity;
        this.boilerNum = boilerNum;
        this.steamturbineNum = steamturbineNum;
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
    }

    public Feed() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Byte getFeedType() {
        return feedType;
    }

    public void setFeedType(Byte feedType) {
        this.feedType = feedType;
    }

    public Byte getHeatType() {
        return heatType;
    }

    public void setHeatType(Byte heatType) {
        this.heatType = heatType;
    }

    public Double getInstallCapacity() {
        return installCapacity;
    }

    public void setInstallCapacity(Double installCapacity) {
        this.installCapacity = installCapacity;
    }

    public Double getHeatCapacity() {
        return heatCapacity;
    }

    public void setHeatCapacity(Double heatCapacity) {
        this.heatCapacity = heatCapacity;
    }

    public Integer getBoilerNum() {
        return boilerNum;
    }

    public void setBoilerNum(Integer boilerNum) {
        this.boilerNum = boilerNum;
    }

    public Integer getSteamturbineNum() {
        return steamturbineNum;
    }

    public void setSteamturbineNum(Integer steamturbineNum) {
        this.steamturbineNum = steamturbineNum;
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
}