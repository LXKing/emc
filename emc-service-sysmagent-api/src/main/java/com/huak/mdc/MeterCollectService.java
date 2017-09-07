package com.huak.mdc;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.mdc.model.MeterCollect;
import com.huak.mdc.vo.MeterCollectA;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.mdc<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/28<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

public interface MeterCollectService {

    /**
     * 计量器具删除信息
     */
    public int deleteByPrimaryKey(String id);
    /**
     * 计量器具新增信息
     */
    public int insertSelective(MeterCollect record);
    /**
     * 计量器具新增信息
     */
    public int insert(MeterCollect record);
    /**
     * 计量器具根据id查询
     */
    public MeterCollect selectByPrimaryKey(String id);
    /**
     * 计量器具修改信息
     */
    public int updateByPrimaryKeySelective(MeterCollect record);

    /**
     * 计量器具导入
     */
    public Map<String, Object> excelUpload(String s, String path) throws IOException;
    /**
     * 计量器具管理查询
     */
    public PageResult<MeterCollectA> queryByPage(Map<String,Object> paramsMap, Page page);

    /**
     * 计量器具-导出
     * @param paramsMap
     * @return
     * @throws IOException
     */
    List<Map<String, Object>> exportExcel(Map<String, Object> paramsMap) throws IOException;

    /**
     * 校验计量代码
     */
    public boolean checkName(Map<String,Object> paramsMap);
    /**
     * 校验计量代码
     */
    public boolean checkCode(Map<String,Object> paramsMap);
    /**
     * 校验计量代码
     */
    public boolean checkNo(String serialNo);

    /**
     * 获取相关类型的用能单位
     * @param unitType
     */
    public List<Map<String,Object>> getUnitInfo(Map<String, Object> unitType);

    /**
     * 填报数据查询
     * @param params
     * @return
     */
    List<Map<String,Object>> getMeterDatas(Map<String, Object> params);

    /**
     * 安全与后台-数据填报
     * @param jo
     * @return
     */
    boolean fillData(JSONObject jo);
}
