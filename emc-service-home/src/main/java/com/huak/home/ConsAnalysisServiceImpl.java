package com.huak.home;

import com.huak.home.dao.ConsAnalysisDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.home<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/6/5<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class ConsAnalysisServiceImpl implements ConsAnalysisService {
    @Resource
    private ConsAnalysisDao consAnalysisDao;
}
