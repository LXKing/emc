package com.huak.task;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.task<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/7/11<BR>
 * Description:   定时任务测试  <BR>
 * Function List:  <BR>
 */
@Component
public class TestTask {
    public void execute1(){
        System.out.printf("Task: %s, Current time: %s\n", 1, new Date());
    }
}
