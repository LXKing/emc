package com.huak.mdc;

import com.huak.mdc.model.EnergyDataHis;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.mdc<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/28<BR>
 * Description:   能耗历史  <BR>
 * Function List:  <BR>
 */
public interface EnergyDataHisService {
    /**
     * 保存能耗数据
     * @param energyDataHis
     */
    public void saveEnergyData(EnergyDataHis energyDataHis);
}
