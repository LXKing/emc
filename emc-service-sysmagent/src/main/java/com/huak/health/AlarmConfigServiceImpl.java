package com.huak.health;

import com.huak.health.model.AlarmConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.health<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/9/19<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class AlarmConfigServiceImpl implements AlarmConfigService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(String id) {
        logger.info("删除报警配置");
        return 0;
    }

    @Override
    @Transactional(readOnly = false)
    public int insert(AlarmConfig record) {
        logger.info("新增报警配置");
        return 0;
    }

    @Override
    @Transactional(readOnly = false)
    public int insertSelective(AlarmConfig record) {
        logger.info("新增报警配置");
        return 0;
    }

    @Override
    @Transactional(readOnly = true)
    public AlarmConfig selectByPrimaryKey(String id) {
        logger.info("查询报警配置");
        return null;
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(AlarmConfig record) {
        logger.info("更新报警配置");
        return 0;
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKey(AlarmConfig record) {
        logger.info("更新报警配置");
        return 0;
    }
}
