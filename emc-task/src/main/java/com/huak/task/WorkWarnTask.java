package com.huak.task;

import com.huak.diacrisis.WorkWarnService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.task<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/7/11<BR>
 * Description:   工况报警  <BR>
 * Function List:  <BR>
 */
@Component("workWarnTask")
public class WorkWarnTask {
    @Resource
    private WorkWarnService workWarnService;


    public void workWarnInfo(){
       workWarnService.executeWarning();
    }
}
