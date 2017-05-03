package com.huak.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:eccp<BR>
 * File name:  com.huak.common.utils<BR>
 * Author:  Bin  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/12/28<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public class DateUtils {
    public static List<String> getDates(String year, String month) throws ParseException {
        List<String> dates = new ArrayList<>();
        SimpleDateFormat tempdate = new SimpleDateFormat("YYYY-MM");
        Calendar cal = Calendar.getInstance();
        cal.setTime(tempdate.parse(year + "-" + month));//month 为指定月份任意日期
        int tempYear = Integer.parseInt(year);
        int m = cal.get(Calendar.MONTH) + 1;
        int dayNumOfMonth = DateUtils.getDaysByYearMonth(tempYear, m);
        cal.set(Calendar.DAY_OF_MONTH, 1);// 从一号开始
        for (int i = 0; i < dayNumOfMonth; i++, cal.add(Calendar.DATE, 1)) {
            Date d = cal.getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String df = simpleDateFormat.format(d);
            dates.add(df);
        }

        return dates;
    }

    private static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
}
