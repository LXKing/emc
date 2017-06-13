package com.huak.home.model;

import java.io.Serializable;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.home.model<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/6/9<BR>
 * Description: 单耗分析    <BR>
 * Function List:  <BR>
 */
public class ConsSecond implements Serializable{
    /* 主键 */
    private String typeId;
    /* 主键 */
    private String typeName;
    /* 总单耗（tce/㎡） */
    private Double totalNum;
    /* 总单耗（tce/㎡）同比 */
    private Double totalAn;
    /* 总单耗（tce/㎡）环比 */
    private Double totalMom;
    /* 水单耗（T） */
    private Double waterNum;
    /* 水单耗（T）同比 */
    private Double waterAn;
    /* 水单耗（T）环比 */
    private Double waterMom;
    /* 电单耗(Kw/h) */
    private Double electricNum;
    /* 电单耗(Kw/h)同比 */
    private Double electricAn;
    /* 电单耗(Kw/h)环比 */
    private Double electricMom;
    /* 气单耗（M²） */
    private Double gasNum;
    /* 气单耗（M²）同比 */
    private Double gasAn;
    /* 气单耗（M²）环比 */
    private Double gasMom;
    /* 热单耗（GJ） */
    private Double heatNum;
    /* 热单耗（GJ）同比 */
    private Double heatAn;
    /* 热单耗（GJ）环比 */
    private Double heatMom;
    /* 煤单耗（GJ） */
    private Double coalNum;
    /* 煤单耗（GJ）同比 */
    private Double coalAn;
    /* 煤单耗（GJ）环比 */
    private Double coalMom;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Double getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Double totalNum) {
        this.totalNum = totalNum;
    }

    public Double getTotalAn() {
        return totalAn;
    }

    public void setTotalAn(Double totalAn) {
        this.totalAn = totalAn;
    }

    public Double getTotalMom() {
        return totalMom;
    }

    public void setTotalMom(Double totalMom) {
        this.totalMom = totalMom;
    }

    public Double getWaterNum() {
        return waterNum;
    }

    public void setWaterNum(Double waterNum) {
        this.waterNum = waterNum;
    }

    public Double getWaterAn() {
        return waterAn;
    }

    public void setWaterAn(Double waterAn) {
        this.waterAn = waterAn;
    }

    public Double getWaterMom() {
        return waterMom;
    }

    public void setWaterMom(Double waterMom) {
        this.waterMom = waterMom;
    }

    public Double getElectricNum() {
        return electricNum;
    }

    public void setElectricNum(Double electricNum) {
        this.electricNum = electricNum;
    }

    public Double getElectricAn() {
        return electricAn;
    }

    public void setElectricAn(Double electricAn) {
        this.electricAn = electricAn;
    }

    public Double getElectricMom() {
        return electricMom;
    }

    public void setElectricMom(Double electricMom) {
        this.electricMom = electricMom;
    }

    public Double getGasNum() {
        return gasNum;
    }

    public void setGasNum(Double gasNum) {
        this.gasNum = gasNum;
    }

    public Double getGasAn() {
        return gasAn;
    }

    public void setGasAn(Double gasAn) {
        this.gasAn = gasAn;
    }

    public Double getGasMom() {
        return gasMom;
    }

    public void setGasMom(Double gasMom) {
        this.gasMom = gasMom;
    }

    public Double getHeatNum() {
        return heatNum;
    }

    public void setHeatNum(Double heatNum) {
        this.heatNum = heatNum;
    }

    public Double getHeatAn() {
        return heatAn;
    }

    public void setHeatAn(Double heatAn) {
        this.heatAn = heatAn;
    }

    public Double getHeatMom() {
        return heatMom;
    }

    public void setHeatMom(Double heatMom) {
        this.heatMom = heatMom;
    }

    public Double getCoalNum() {
        return coalNum;
    }

    public void setCoalNum(Double coalNum) {
        this.coalNum = coalNum;
    }

    public Double getCoalAn() {
        return coalAn;
    }

    public void setCoalAn(Double coalAn) {
        this.coalAn = coalAn;
    }

    public Double getCoalMom() {
        return coalMom;
    }

    public void setCoalMom(Double coalMom) {
        this.coalMom = coalMom;
    }
}
