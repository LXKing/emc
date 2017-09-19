package com.huak.health;

import com.huak.health.model.MetaCompany;
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
public class MetaCompanyServiceImpl implements MetaCompanyService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(String tag) {
        logger.info("删除点表公司关系");
        return 0;
    }

    @Override
    @Transactional(readOnly = false)
    public int insert(MetaCompany record) {
        logger.info("新增点表公司关系");
        return 0;
    }

    @Override
    @Transactional(readOnly = false)
    public int insertSelective(MetaCompany record) {
        logger.info("新增点表公司关系");
        return 0;
    }

    @Override
    @Transactional(readOnly = true)
    public MetaCompany selectByPrimaryKey(String tag) {
        logger.info("查询点表公司关系");
        return null;
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(MetaCompany record) {
        logger.info("更新点表公司关系");
        return 0;
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKey(MetaCompany record) {
        logger.info("更新点表公司关系");
        return 0;
    }
}
