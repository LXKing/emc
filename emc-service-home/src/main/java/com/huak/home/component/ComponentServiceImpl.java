package com.huak.home.component;


import com.huak.home.dao.component.ComponentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MR-BIN on 2017/6/8.
 */
@Service
public class ComponentServiceImpl implements ComponentService{

    @Autowired
    private ComponentDao componentDao;

    /**
     * 组件能耗明细查询
     * @param params
     * @return
     */
    public Map<String,Object> energyDetail(Map<String,Object> params){
        Map<String,Object> data =  componentDao.energymonitorDetail(params);
        Map<String,Object> currentplan = componentDao.getplan(params);
        String sdate = params.get("startTime") == null ? "": params.get("startTime").toString();
        String edate = params.get("endTime") == null ? "": params.get("endTime").toString();
        Map<String,Object> previousplan = new HashMap<>();
        if(org.apache.commons.lang.StringUtils.isNotBlank(sdate) && org.apache.commons.lang.StringUtils.isNotBlank(edate) ){
            try {
                Calendar c = Calendar.getInstance();
                c.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(sdate));
                c.add(Calendar.YEAR, -1);
                Date psdate = c.getTime();
                sdate = new SimpleDateFormat("yyyy-MM-dd").format(psdate);
                c.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(edate));
                c.add(Calendar.YEAR, -1);
                Date pedate = c.getTime();
                edate =  new SimpleDateFormat("yyyy-MM-dd").format(pedate);
                params.put("startTime",sdate);
                params.put("endTime",edate);
                previousplan = componentDao.getplan(params);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        data.put("currentPlan",currentplan.get("plan"));
        data.put("previousPlan",previousplan.get("plan"));
        return data;

    }

}
