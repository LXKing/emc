package com.huak.sys;

import com.huak.base.BaseTest;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.sys<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/17<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public class SysDicServiceTest  extends BaseTest {
    @Resource
    private SysDicService sysDicService;

//    @Test
//    @Rollback
//    public void testSelectDemo(){
//       Map<String, Object> map = new HashMap();
//        map.put("typeZh","能源经济类型");
//        map.put("typeUs","energyEcoType");
//        Long num = sysDicService.checkTypeUs(map);
//        sysDicService.checkTypeZh(map);
//
//    }

    @Test
    @Rollback
    public void testSelectDemo(){
        String A = "A[0-9]{5}";
        Pattern pattern = Pattern.compile(A);
        Matcher matcher = pattern.matcher("A00012+A33203-(A00323+A00303)");
       while (matcher.find()){
           System.err.println(matcher.group());
       }

    }
}
