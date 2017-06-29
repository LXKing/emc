package com.huak.home;

import com.huak.base.dao.DateDao;
import com.huak.home.dao.EnergyMonitorDao;
import com.huak.home.dao.EnergySecondDao;
import com.huak.home.model.EnergyMonitor;
import com.huak.home.model.EnergySecond;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
    public List<Map<String, Object>> fgsEnergyRatio(Map<String, Object> params) {
        return energySecondDao.fgsEnergyRatio(params);
    }

    /**
     * 分公司能耗趋势对比图
     *
     * @param params
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> fgsEnergyTrend(Map<String, Object> params) {
        return energySecondDao.fgsEnergyTrend(params);
    }

    /**
     * 分公司能耗同比
     *
     * @param params
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> fgsEnergyAn(Map<String, Object> params) {
        return energySecondDao.fgsEnergyAn(params);
    }

    /**
     * 分公司能耗排名
     *
     * @param params
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> fgsEnergyRanking(Map<String, Object> params) {
        return energySecondDao.fgsEnergyRanking(params);
    }

    /**
     * 导出分公司列表
     *
     * @param params
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<EnergySecond> exportAssessmentIndicators(Map<String, Object> params) {
        return energySecondDao.exportAssessmentIndicators(params);
    }

    /**
	 * 获取折线数据
	 */
	@Override
	@Transactional
	public Map<String, Object> groupEnergyLine(Map<String, String> params) throws Exception{
		Map<String, Object> result = new HashMap<String,Object>();
		//所有的能源类型
		String[] energyTypes = {"groupEnergy","waterEnergy","electricEnergy","gasEnergy","hotEnergy","coalEnergy"};
		String sDate = params.get("toolStartDate");//查询条件的开始时间
		String eDate = params.get("toolEndDate");//查询条件的结束时间
		sDate = (null==sDate||"".equals(sDate))?getYearDate(null,Calendar.DATE, -5):sDate;//如果查询条件的开始时间为空，设置默认的开始时间
		eDate = (null==eDate||"".equals(eDate))?getYearDate(null,Calendar.DATE, 0):eDate;//如果查询条件的结束时间为空，设置默认的结束时间
		String lsDate = getYearDate(sDate,Calendar.YEAR, -1);
		String leDate = getYearDate(eDate,Calendar.YEAR, -1);
		//查询时间list
		List<String> clyearList = new ArrayList<String>();
		List<String> lyearList = new ArrayList<String>();
		List<String> yearList = new ArrayList<String>();
		while(!sDate.equals(eDate)){
			yearList.add(sDate);
			sDate = getYearDate(sDate,Calendar.DATE,1);
		}
		yearList.add(eDate);
		while(!lsDate.equals(leDate)){
			lyearList.add(lsDate);
			lsDate = getYearDate(lsDate,Calendar.DATE,1);
		}
		lyearList.add(leDate);
		clyearList.addAll(lyearList);
		clyearList.addAll(yearList);
		//根据查询条件，查询相应的数据
		List<Map<String,Object>> listMap = eaDao.groupEnergy(params);
		//组装chartJson格式开始
		boolean lmEmpty = null!=listMap && listMap.size()>0;
		for(String type:energyTypes){
			List<String> curList = new ArrayList<String>();//每个能源类型今年某一时间段（查询条件中的时间段）的值，存储为list形式
			List<String> lastList = new ArrayList<String>();//每个能源类型去年某一时间段（查询条件中的时间段）的值，存储为list形式
			Double curYearTotal = 0.0,lastYearTotal = 0.0;//今天和去年今天的能耗数据
			if(lmEmpty){//如果查询结果存在，需要设置上面所定义的变量，方便下面封装chartJson
				for(String yd:clyearList){
					boolean isHas = false;
					for(Map<String,Object> map : listMap){
						String curyear = map.get("curyear").toString();
						String yeardate = map.get("yeardate").toString();
						if(!yeardate.equals(yd))continue;
						isHas = true;
						String value = String.valueOf(map.get(type));
						if("0".equals(curyear)){
							lastList.add(value);//添加
							lastYearTotal += Double.parseDouble(value);//拿到去年今天的能耗
						}else if("1".equals(curyear)){
							curList.add(value);//添加
							curYearTotal += Double.parseDouble(value);//拿到今天的能耗
						}
					}
					if(!isHas){
						if(lyearList.contains(yd)){
							lastList.add("0");
						}else{
							curList.add("0");
						}
					}
				}
			}
			//json中的能源数据
			Map<String,Object> energyMap = new HashMap<String,Object>();
			energyMap.put("yearDate", yearList);
			List<Object> data = new ArrayList<Object>();
			//此list作用：当查询数据不存在时，需按照查询条件中时间段来设置那天的数据为0，方便chart的显示
			List<String> dataList = new ArrayList<String>();
			for(String d : yearList){
				dataList.add("0.0");//不存在数据时，默认赋值为0
			}
			//封装今年数据的map
			Map<String,Object> curYearData = new HashMap<String,Object>();
			curYearData.put("typeName", "今年");
			curYearData.put("dataList", lmEmpty&&curList.size()>0?curList:dataList);
			data.add(curYearData);
			//封装去年数据的map
			Map<String,Object> lastYearData = new HashMap<String,Object>();
			lastYearData.put("typeName", "去年");
			lastYearData.put("dataList", lmEmpty&&lastList.size()>0?lastList:dataList);
			data.add(lastYearData);
			energyMap.put("data", data);
			result.put(type, energyMap);
			//json中的统计数据
			Map<String,Object> rateMap = new HashMap<String,Object>();
			//封装今天能耗数据的map
			Map<String,Object> energy = new HashMap<String,Object>();
			curYearTotal = (double)Math.round(curYearTotal*100)/100;
			energy.put("value", lmEmpty?curYearTotal:"0");
			energy.put("type", "0");
			rateMap.put("energy", energy);
			//封装同比数据的map
			Map<String,Object> rate = new HashMap<String,Object>();
			Double scale = Double.valueOf(lastYearTotal)==0.0?0.0:
				(double)Math.round((Double.valueOf(curYearTotal)-Double.valueOf(lastYearTotal))/Double.valueOf(lastYearTotal)*10000)/100;//计算同比值
			String rateType = "0";
			if(lastYearTotal==0) rateType="-1";
			if(lastYearTotal>curYearTotal)  rateType = "1";//设置同比数据后面的上下箭头
			rate.put("rate", lmEmpty?scale:"0");
			rate.put("type", lmEmpty?rateType:"0");
			rateMap.put("changeRate", rate);
			result.put(type.replace("Energy", "Total"), rateMap);
		}
		//组装chartJson格式结束
		return result;
	}

	/**
	 * 返回想要的日期
	 * 例如：getYearDate（2017-01-01，Calendar.YEAR，-1），返回值为 2016-01-01
	 *     getYearDate（2017-01-11，Calendar.DATE，-1），返回值为 2017-01-10
	 * @param curDate 元数据，在curDate的基础上获取想要的具体日期str
	 * @param type 类型，Calendar.YEAR,Calendar.MONTH...
	 * @param num 操作数
	 * @return
	 */
	private String getYearDate(String curDate,int type,int num) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		curDate = (curDate==null||"".equals(curDate))?dateDao.getDate():curDate;
		Date date = sdf.parse(curDate);
		Calendar calendar = Calendar.getInstance();    
		calendar.setTime(date);    
		calendar.add(type, num);
		date = calendar.getTime();
		return sdf.format(date);
	}
	
	/**
	 * 查询能源流明细
	 */
	@Override
	public List<Map<String, Object>> energyFlowTable(Map<String, String> params) {
		List<Map<String, Object>> eftListMap = eaDao.energyFlowTable(params);
		return eftListMap;
	}

	/**
	 * 查询能源流占比分布图
	 */
	@Override
	public List<Map<String, Object>> energyFlowPie(Map<String, String> params) {
		List<Map<String, Object>> efpListMap = eaDao.energyFlowPie(params);
		return efpListMap;
	}

	/**
	 * 查询能源流趋势对比图
	 */
	@Override
	public Map<String,Object> energyFlowLine(Map<String, String> params) throws Exception{
		Map<String,Object> result = new HashMap<String,Object>();
		String sDate = params.get("toolStartDate");//查询条件的开始时间
		String eDate = params.get("toolEndDate");//查询条件的结束时间
		sDate = (null==sDate||"".equals(sDate))?getYearDate(null,Calendar.DATE, -5):sDate;//如果查询条件的开始时间为空，设置默认的开始时间
		eDate = (null==eDate||"".equals(eDate))?getYearDate(null,Calendar.DATE, 0):eDate;//如果查询条件的结束时间为空，设置默认的结束时间
		//查询时间list
		List<String> yearList = new ArrayList<String>();
		//无查询结果时，设为默认值0
		List<String> defaultValue = new ArrayList<String>();
		while(!sDate.equals(eDate)){
			yearList.add(sDate);
			sDate = getYearDate(sDate,Calendar.DATE,1);
			defaultValue.add("0");
		}
		yearList.add(eDate);
		defaultValue.add("0");
		//处理查询结果，为折线图做准备
		List<Map<String, Object>> eflListMap = eaDao.energyFlowLine(params);
		String[] unitType = null;
		int toolorgtype = Integer.valueOf("".equals((params.get("toolOrgType")+""))?"-1":(params.get("toolOrgType")+""));
		switch(toolorgtype){
			case 1:unitType = new String[]{"供热源"};break;
			case 2:unitType = new String[]{"一次网"};break;
			case 3:unitType = new String[]{"换热站"};break;
			case 4:unitType = new String[]{"二次线"};break;
			case 5:unitType = new String[]{"民户"};break;
			default:unitType = new String[]{"供热源","一次网","换热站","二次线","民户"};
		}
		List<Map<String,Object>> series = new ArrayList<Map<String,Object>>();
		for(String type:unitType){
			Map<String, Object> serie = new HashMap<String,Object>();
			serie.put("name",type);
			serie.put("type","line");
			serie.put("symbol","circle");
			serie.put("smooth",false);
			List<String> lineValue = new ArrayList<String>();
			if(null!=eflListMap&&eflListMap.size()>0){
				for(String d:yearList){
					boolean isHas = false;
					for(Map<String, Object> map:eflListMap){
						if(type.equals(map.get("unittype")+"")&&d.equals(map.get("yeardate")+"")){
							lineValue.add(map.get("total")+"");
							isHas = true;
						}
					}
					if(!isHas){
						lineValue.add("0");
					}
				}
				serie.put("data",lineValue);
			}else{
				serie.put("data",defaultValue);
			}
			series.add(serie);
		}
		result.put("xDate", yearList);
		result.put("series", series);
		result.put("legends", unitType);
		return result;
	}

	/**
	 * 查询能源流同比
	 */
	@Override
	public Map<String, Object> energyFlowBar(Map<String, String> params) {
		Map<String,Object> result = new HashMap<String,Object>();
		List<String> last = new ArrayList<String>();
		List<String> cur = new ArrayList<String>();
		List<Map<String, Object>> efbListMap = eaDao.energyFlowBar(params);
		for(int i=1;i<=5;i++){
			if(null!=efbListMap&&efbListMap.size()>0){
				for(Map<String, Object> map:efbListMap){
					if((i+"").equals(map.get("unittype")+"")){
						last.add(map.get("last")+"");
						cur.add(map.get("cur")+"");
					}
				}
			}else{
				last.add("0");
				cur.add("0");
			}
		}
		result.put("last", last);
		result.put("cur", cur);
		return result;
	}

	/**
	 * 导出能源流明细
	 */
	@Override
	public List<Map<String, Object>> exportEnergyFlowDetail(
			Map<String, String> params) {
		return eaDao.exportEnergyFlowDetail(params);
	}

}
