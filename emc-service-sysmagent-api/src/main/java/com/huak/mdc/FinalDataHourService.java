package com.huak.mdc;

import com.huak.mdc.model.EnergyDataHis;
import com.huak.mdc.model.MeterCollect;
import com.huak.org.model.Company;

import java.util.List;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.mdc<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/29<BR>
 * Description:  最终能耗历史数据   <BR>
 * Function List:  <BR>
 */
public interface FinalDataHourService {
    /**
     * 时间段能耗更新
     * @return
     */
    public boolean saveDataHour(EnergyDataHis energyDataHis,EnergyDataHis data,Company company) throws Exception;

    public boolean saveVirtualDataHour(MeterCollect meterCollect, List<String> dateTimes, List<String> codes, Company company) throws Exception;
}
