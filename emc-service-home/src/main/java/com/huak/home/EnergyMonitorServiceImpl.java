package com.huak.home;

import com.huak.base.dao.DateDao;
import com.huak.home.dao.EnergyMonitorDao;
import com.huak.home.dao.EnergySecondDao;
import com.huak.home.model.EnergyMonitor;
import com.huak.home.model.EnergySecond;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EnergyMonitorServiceImpl implements EnergyMonitorService {

	@Resource
	private DateDao dateDao;
	@Resource
	private EnergyMonitorDao eaDao;
    @Resource
    private EnergySecondDao energySecondDao;

	/**
	 * 跳转到此页面前，查询↑和↓那块的数据
	 */
	@Override
	public Map<String, Object> groupEnergy2Day() {
		Map<String,Object> result = new HashMap<String,Object>();
		//设置查询参数
		Map<String,String> params = new HashMap<String,String>();
		params.put("yearDate", getYearDate(null, Calendar.DATE, 0));
		//查询今天的数据
		List<Map<String,Object>> curList = eaDao.groupEnergy(params);
		params.put("yearDate", getYearDate(null, Calendar.YEAR, -1));
		//查询去年今天的数据
		List<Map<String,Object>> lastList = eaDao.groupEnergy(params);
		//转换格式
		Map<String,List<String>> curMap = getItem(curList);
		Map<String,List<String>> lastMap = getItem(lastList);
		//组装返回格式
		List<String> energyType = new ArrayList<String>();
		if(!curMap.isEmpty()&&null!=curMap.get("energyType")){
			energyType = curMap.get("energyType");
		}else if(!lastMap.isEmpty()&&null!=lastMap.get("energyType")){
			energyType = lastMap.get("energyType");
		}
		if(energyType.size()>0){//查询出数据
			for(String type:energyType){
				//每一项结果Map
				Map<String,String> html = new HashMap<String,String>();
				//今天的数据
				String cur = curMap.get(type)==null||curMap.get(type).size()==0?"0":curMap.get(type).get(0).toString();
				//去年今天的数据
				String last = lastMap.get(type)==null||lastMap.get(type).size()==0?"0":lastMap.get(type).get(0).toString();
				html.put("total", cur);//能耗数
				Double scale = 0.00;
				if(Double.valueOf(cur)>=Double.valueOf(last)){
					html.put("up", "1");//为1，↑
					scale = Double.valueOf(last)==0?Double.valueOf(cur):Double.valueOf(cur)/Double.valueOf(last);
				}else{
					html.put("up", "0");//为0，↓
					scale = Double.valueOf(cur)==0?Double.valueOf(last):Double.valueOf(last)/Double.valueOf(cur);
				}
				html.put("scale", String.format("%.2f", scale));//同比 %
				result.put(type, html);
			}
		}
		return result;
	}

    @Override
    @Transactional(readOnly = false)
    public void insertByPrimaryKeySelective(EnergyMonitor energyMonitor) {
        eaDao.insertByPrimaryKeySelective(energyMonitor);
    }

    /**
     * 查询分公司列表
     *
     * @param params
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<EnergySecond> findAssessmentIndicators(Map<String, Object> params) {
        return energySecondDao.findAssessmentIndicators(params);
    }

    /**
     * 分公司能耗占比分布图
     *
     * @param params
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<EnergySecond> fgsEnergyRatio(Map<String, Object> params) {
        return energySecondDao.fgsEnergyRatio(params);
    }

    /**
	 * 获取折线数据
	 */
	@Override
	public Map<String, Object> groupEnergyLine(Map<String, String> params) {
		Map<String, Object> result = new HashMap<String,Object>();
		//如果查询条件开始时间和结束时间没有，设置默认的开始和结束时间。开始时间：当前日期前五天，结束时间：当前日期
		if(params.get("cyearDate")==null){
			params.put("cyearDate", getYearDate(null,Calendar.DATE, -5));
		}
		if(params.get("eyearDate")==null){
			params.put("eyearDate", getYearDate(null,Calendar.DATE, 0));
		}
		//查询今年的数据
		List<Map<String,Object>> curList = eaDao.groupEnergy(params);
		//将查询条件中的开始和结束时间，设置为去年（同比）
		params.put("cyearDate", getYearDate(params.get("cyearDate").toString(),Calendar.YEAR, -1));
		params.put("eyearDate", getYearDate(params.get("eyearDate").toString(),Calendar.YEAR, -1));
		//查询去年的数据
		List<Map<String,Object>> lastList = eaDao.groupEnergy(params);
		//组装数据
		result = getChartsData(curList,lastList);
		return result;
	}

	/**
	 * 组装集团总能耗的折线数据
	 * @param curList 今年的数据
	 * @param lastList 去年（同比）的数据
	 * @return 前段js显示所需的格式
	 *  {"data":{"item1":{"yearDate":[],"data":[{今年的数据},{去年的数据}]},"item2":...}}
	 */
	private Map<String,Object> getChartsData(List<Map<String, Object>> curList,
			List<Map<String, Object>> lastList) {
		//格式转换
		Map<String,List<String>> curMap = getItem(curList);
		Map<String,List<String>> lastMap = getItem(lastList);
		//每一项中都需要的yearDate【】
		List<String> yearList = curMap.get("yeardate");
		//有多少项，每项名称
		List<String> energyType = curMap.get("energyType");
		Map<String,Object> result = new HashMap<String,Object>();
		//按照项遍历，组装想要的格式
		for(String type:energyType){
			//组装格式，参考方法注释中的格式
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

	/**
	 * [{total:,gas:,},{...},...] --> {total:[1,2,3,...],gas:[1,2,3]...}
	 * 就是遍历查询出的list<map>，将map中的key作为返回结果map的key，相应的value为list
	 * @param list 要转换的list
	 * @return map<String,list>
	 */
	private Map<String,List<String>> getItem(List<Map<String, Object>> list) {
		Map<String,List<String>> result = new HashMap<String,List<String>>();
		List<String> energyType = new ArrayList<String>();//能耗类型，total：总；gas：气；water：水；elec：电；hot：热；coal：煤
		if(null!=list&&list.size()>0){
			Set<String> sets = list.get(0).keySet();
			for(String set:sets){
				if(!"yeardate".equals(set)) energyType.add(set);//返回结果中有每一个list的日期，过滤掉
				List<String> kvList = new ArrayList<String>();
				//查询出list中每一个项的值组成新的list
				for(Map<String,Object> map : list){
					kvList.add(map.get(set).toString());
				}
				result.put(set, kvList);//结果，string：value；每一项：list【】
			}
			result.put("energyType", energyType);
		}
		return result;
	}
	
	/**
	 * 返回想要的日期
	 * @param curDate 元数据，在curDate的基础上获取想要的具体日期str
	 * @param type 类型，Calendar.YEAR,Calendar.MONTH...
	 * @param num 操作数
	 * @return
	 */
	private String getYearDate(String curDate,int type,int num) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(dateDao.getDate());
			Calendar calendar = Calendar.getInstance();    
			calendar.setTime(date);    
			calendar.add(type, num);
			date = calendar.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sdf.format(date);
	}

}
