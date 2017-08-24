package com.huak.common;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.common<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/6/13<BR>
 * Description:   集合工具类  <BR>
 * Function List:  <BR>
 */
public class CollectionUtil {

    /**
     * 删除ArrayList中重复元素，保持顺序
     *
     * @param list
     * @return
     */
    public static List removeDuplicateWithOrder(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (set.add(element)) {
                newList.add(element);
            }
        }
        list.clear();
        list.addAll(newList);
        return list;
    }

    /**
     * 对象转换map key为大写
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> Obj2Map(Object obj) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            //过滤序列化字段
            if("serialVersionUID".equals(field.getName())){
                continue;
            }
            field.setAccessible(true);
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), obj.getClass());
            Method getMethod = pd.getReadMethod();
            map.put(field.getName().toUpperCase(), getMethod.invoke(obj));
        }
        return map;
    }

    /**
     * 封装数据方法
     * 由于emc系统有大量的本期和同期的折线图，故有此公共方法
     * 限制：横坐标每天时间、本期折线、同期折线
     * @param start 开始时间
     * @param end 结束时间
     * @param thisList 本期数据List<Map<String,Object>> map中一定存在key{dayDate,dayValue}
     * @param sameList 同期数据List<Map<String,Object>> map中一定存在key{dayDate,dayValue}
     * @return Map<String,Object>
     *     key = xdatas value = List<String>{}   横坐标
     *     key = aline value = List<String>{}    第一条线，本期
     *     key = bline value = List<String>{}    第二条线，同期
     */
    public static Map<String,Object> packageDataLine(String start,String end,List<Map<String,Object>> thisList,List<Map<String,Object>> sameList){
        Map<String, Object> map = new HashMap<String, Object>();

        return map;
    }

}
