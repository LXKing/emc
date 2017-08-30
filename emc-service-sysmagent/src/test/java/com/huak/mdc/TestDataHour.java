package com.huak.mdc;

import com.huak.base.BaseTest;
import com.huak.common.UUIDGenerator;
import com.huak.mdc.dao.FinalDataHourDao;
import com.huak.mdc.model.FinalDataHour;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.mdc<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/28<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public class TestDataHour extends BaseTest {

    @Resource
    private FinalDataHourDao finalDataHourDao;

    @Test
    @Rollback
    public void testInsert() {
        FinalDataHour dataHour = new FinalDataHour();
        String id = UUIDGenerator.getUUID();
        dataHour.setId(id);
        dataHour.setTableName("t_emc_final_data_hour");
        dataHour.setNodeid("1");
        dataHour.setComid("1");
        dataHour.setUnitid("1");
        dataHour.setTypeid("1");
        dataHour.setDosageTime("2017-01-01 10:10:10");
        dataHour.setDosage(2d);
        dataHour.setArea(11d);
        dataHour.setPrice(BigDecimal.valueOf(4.3));
        dataHour.setWtemp(12d);
        dataHour.setCwtemp(23d);
        dataHour.setCoalCoef(12.2d);
        dataHour.setcCoef(22.1d);
        int i = finalDataHourDao.insertSelective(dataHour);
        System.err.println("-------------[i = " + i + ",id = " + id + "]----------");
    }

    @Test
    @Rollback
    public void testSelect() {
        Map<String, Object> params = new HashMap<>();
        String id = "1d7fa3c4ce2848f9bc874ca7eb93d202";
        params.put("id",id);
        params.put("tableName","t_emc_final_data_hour");

        FinalDataHour finalDataHour = finalDataHourDao.selectByPrimaryKey(params);
        System.err.println("-------------[id = " + finalDataHour.getId() + ",id = " + id + "]----------");
    }

}
