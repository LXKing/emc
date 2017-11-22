package com.huak.data.vo;

import java.io.Serializable;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.data.vo<BR>
 * Author:  liuhe  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/11/21<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public class Weather implements Serializable{
    private static final long serialVersionUID = 2975507596792376748L;
    private String dateTime;
    private String highTemp;
    private String lowTemp;
    private String avgTemp;

    @Override
    public String toString() {
        return "Weather{" +
                "dateTime='" + dateTime + '\'' +
                ", highTemp='" + highTemp + '\'' +
                ", lowTemp='" + lowTemp + '\'' +
                ", avgTemp='" + avgTemp + '\'' +
                '}';
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(String highTemp) {
        this.highTemp = highTemp;
    }

    public String getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(String lowTemp) {
        this.lowTemp = lowTemp;
    }

    public String getAvgTemp() {
        return avgTemp;
    }

    public void setAvgTemp(String avgTemp) {
        this.avgTemp = avgTemp;
    }

    public Weather(String dateTime, String highTemp, String lowTemp, String avgTemp) {

        this.dateTime = dateTime;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
        this.avgTemp = avgTemp;
    }

    public Weather() {

    }
}
