package com.huak.home;

import com.alibaba.fastjson.JSONObject;
import com.huak.base.dao.DateDao;
import com.huak.common.utils.DateUtils;
import com.huak.home.dao.SearchDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.home<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/6/8<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Resource
    private SearchDao searchDao;
    @Resource
    private DateDao dateDao;
    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> getOrgList(String id) {
        return searchDao.getOrgList(id);
    }

    @Override
    @Transactional(readOnly = true)
    public JSONObject getYearDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        JSONObject jsonObject = new JSONObject();
        String nowDate = dateDao.getDate();
        Integer year = Integer.valueOf(nowDate.substring(0, 4));
        Date first = DateUtils.getYearFirst(year);
        Date last = DateUtils.getYearLast(year);
        jsonObject.put("startDate",dateFormat.format(first));
        jsonObject.put("endDate",dateFormat.format(last));
        return jsonObject;
    }
}
