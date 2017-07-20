package com.huak.common.enums;

/**
 * Created by MR-BIN on 2017/7/11.
 */
public enum WeatherEnum {
    clear(1,0),cloudy(2,1),shade(3,2),shower(4,3),thundershower(5,4),thundershowerhail(6,5),
    sleet(7,6),sprinkle(8,7),moderaterain(9,8),soaker(10,9),rainstorm(11,10),downpour(12,11),
    rainstorms(13,12),snowshower(14,13),flurry(15,14),snow(16,15),heavysnow(17,16),blizzard(18,17),
    fog(19,18),icerain(20,19),sandstorm(21,20),lightrainmoderaterain(22,21),moderaterainheavyrain(23,22),
    downpourrainstorm(24,23),rainstormdownpour(25,24),downpourrainstorms(26,25),flurrysnow(27,26),snowheavysnow(28,27),
    heavysnowblizzard(29,28),dust(30,29),jansa(31,30),heavyduststorm(32,31),haze(33,53);
    // 成员变量
    private int weatherid;
    private int weatheridIcon;
    // 构造方法
    private WeatherEnum(int weatherid, int weatheridIcon) {
        this.weatherid = weatherid;
        this.weatheridIcon = weatheridIcon;
    }
    // 普通方法
    public static String getName(int weatherid) {
        for (WeatherEnum c : WeatherEnum.values()) {
            if (c.getWeatherid() == weatherid) {
                return c.weatheridIcon+"";
            }
        }
        return null;
    }
    public int getWeatherid() {
        return weatherid;
    }

    public int getWeatheridIcon() {
        return weatheridIcon;
    }
}
