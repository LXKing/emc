package com.huak.common.enums;

/**
 * Created by MR-BIN on 2017/7/11.
 */
public enum WeatherEnum {
    clear("晴",0),cloudy("多云",1),shade("阴",2),shower("阵雨",3),thundershower("雷阵雨",4),thundershowerhail("雷阵雨有冰雹",5),
    sleet("雨夹雪",6),sprinkle("小雨",7),moderaterain("中雨",8),soaker("大雨",9),rainstorm("暴雨",10),downpour("大暴雨",11),
    rainstorms("特大暴雨",12),snowshower("阵雪",13),flurry("小雪",14),snow("中雪",15),heavysnow("大雪",16),blizzard("暴雪",17),
    fog("雾",18),icerain("冻雨",19),sandstorm("沙尘暴",20),lightrainmoderaterain("小雨-中雨",21),moderaterainheavyrain("中雨-大雨",22),
    downpourrainstorm("大雨-暴雨",23),rainstormdownpour("暴雨-大暴雨",24),downpourrainstorms("大暴雨-特大暴雨",25),flurrysnow("小雪-中雪",26),snowheavysnow("中雪-大雪",27),
    heavysnowblizzard("大雪-暴雪",28),dust("浮尘",29),jansa("扬沙",30),heavyduststorm("强沙尘暴",31),haze("霾",53);
    // 成员变量
    private String weatherid;
    private int weatheridIcon;
    // 构造方法
    private WeatherEnum(String weatherid, int weatheridIcon) {
        this.weatherid = weatherid;
        this.weatheridIcon = weatheridIcon;
    }
    // 普通方法
    public static String getName(String weatherid) {
        for (WeatherEnum c : WeatherEnum.values()) {
            if (c.getWeatherid() == weatherid) {
                return c.weatheridIcon+"";
            }
        }
        return null;
    }
    public String getWeatherid() {
        return weatherid;
    }

    public int getWeatheridIcon() {
        return weatheridIcon;
    }
}
