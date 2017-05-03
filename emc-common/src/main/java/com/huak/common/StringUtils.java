package com.huak.common;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:eccp<BR>
 * File name:  com.huak.common<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/12/7<BR>
 * Description:  字符串操作   <BR>
 * Function List:  <BR>
 */
public class StringUtils {

    /**
     * 获取指定位数的随机字符串(包含小写字母、大写字母、数字,0<length)
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        //随机字符串的随机字符库
        String KeyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer sb = new StringBuffer();
        int len = KeyString.length();
        for (int i = 0; i < length; i++) {
            sb.append(KeyString.charAt((int) Math.round(Math.random() * (len - 1))));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getRandomString(3));
    }
}
