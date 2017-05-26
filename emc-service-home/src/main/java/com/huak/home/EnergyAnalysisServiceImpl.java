package com.huak.home;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.huak.energy.EnergyAnalysisService;
import org.springframework.stereotype.Service;

import com.huak.home.dao.EnergyAnalysisDao;

@Service
public class EnergyAnalysisServiceImpl implements EnergyAnalysisService {

	@Resource
	private EnergyAnalysisDao eaDao;

	@Override
	public Map<String, Object> groupEnergy2Day(Map<String, String> params) {
		Map<String,Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> curList = eaDao.groupEnergy2curyear(params);
		
		Date date = new Date();//获取当前时间    
		Calendar calendar = Calendar.getInstance();    
		calendar.setTime(date);    
		calendar.add(Calendar.YEAR, -1);//当前时间减去一年，即一年前的时间    
		date = calendar.getTime();//获取一年前的时间，或者一个月前的时间    
		params.put("yearDate", new SimpleDateFormat("yyyy-MM-dd").format(date));
		List<Map<String,Object>> lastList = eaDao.groupEnergy2lastyear(params);
		Map<String,List<String>> curMap = getItem(curList);
		Map<String,List<String>> lastMap = getItem(lastList);
		
		if(!curMap.isEmpty()){
			List<String> energyType = curMap.get("energyType");
			for(String type:energyType){
				Map<String,String> html = new HashMap<String,String>();
				String cur = "0",last = "0";
				if(!lastMap.isEmpty()){
					last = lastMap.get(type).get(0).toString();
				}
				if(!curMap.isEmpty()){
					cur = curMap.get(type).get(0).toString();
				}
				html.put("total", cur);
				Double scale = 0.00;
				if(Double.valueOf(cur)>=Double.valueOf(last)){
					html.put("up", "1");
					scale = Double.valueOf(last)==0?Double.valueOf(cur):Double.valueOf(cur)/Double.valueOf(last);
				}else{
					html.put("up", "0");
					scale = Double.valueOf(cur)==0?Double.valueOf(last):Double.valueOf(last)/Double.valueOf(cur);
				}
				html.put("scale", String.format("%.2f", scale));
				result.put(type, html);
			}
		}
		return result;
	}
	
	@Override
	public Map<String, Object> groupEnergy() {
		
		Map<String, Object> result = new HashMap<String,Object>();
		
		List<Map<String,Object>> curList = eaDao.groupEnergy2curyear(null);
		List<Map<String,Object>> lastList = eaDao.groupEnergy2lastyear(null);

		result = getChartsData(curList,lastList);
		
		return result;
	}

	private Map<String,Object> getChartsData(List<Map<String, Object>> curList,
			List<Map<String, Object>> lastList) {

		Map<String,List<String>> curMap = getItem(curList);
		Map<String,List<String>> lastMap = getItem(lastList);
		List<String> yearList = curMap.get("yearDate");
		List<String> energyType = curMap.get("energyType");
		Map<String,Object> result = new HashMap<String,Object>();
		for(String type:energyType){
			Map<String,Object> itemMap = new HashMap<String,Object>();
			List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
			Map<String,Object> map1 = new HashMap<String,Object>();
			Map<String,Object> map2 = new HashMap<String,Object>();
			map1.put("typeName", "今年");
			map2.put("typeName", "去年");
			map1.put("dataList",curMap.get(type));
			map2.put("dataList",lastMap.get(type));
			data.add(map1);
			data.add(map2);
			itemMap.put("yearDate", yearList);
			itemMap.put("data", data);
			result.put(type, itemMap);
		}
		
		return result;
	}

	private Map<String,List<String>> getItem(List<Map<String, Object>> list) {
		Map<String,List<String>> result = new HashMap<String,List<String>>();
		List<String> energyType = new ArrayList<String>();
		if(null!=list&&list.size()>0){
			Set<String> sets = list.get(0).keySet();
			for(String set:sets){
				if(!"yearDate".equals(set)) energyType.add(set);
				List<String> kvList = new ArrayList<String>();
				for(Map<String,Object> map : list){
					kvList.add(map.get(set).toString());
				}
				result.put(set, kvList);
			}
			result.put("energyType", energyType);
		}
		return result;
	}

}
