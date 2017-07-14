package com.huak.temp;

import com.huak.task.dao.TemperatureDao;
import com.huak.task.model.Temperature;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/7/13<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class TempServiceImpl implements com.huak.temp.TempService{

    @Resource
    TemperatureDao tempDao;

    @Override
    public List<Temperature> isExsistTemp(Map<String, Object> map) {
        return tempDao.selectAllByMap(map);
    }

    @Override
    public Map<String, Object> insertTemp(Temperature temp) {
        Map<String, Object> map = new HashMap<String,Object>();
         try {
             tempDao.insertSelective(temp);
             map.put("flag",true);
             map.put("msg","导入成功");
         }catch (Exception e){
             e.printStackTrace();
             map.put("flag",false);
             map.put("msg","导入失败");
         }
        return map;
    }
}
