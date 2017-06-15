package com.huak.home;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huak.base.dao.DateDao;
import com.huak.home.dao.EnergyMonitorDao;
import com.huak.home.dao.EnergySecondDao;
import com.huak.home.model.EnergyMonitor;
import com.huak.home.model.EnergySecond;

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
		//查询时间list
		List<String> yearList = new ArrayList<String>();
		while(!sDate.equals(eDate)){
			yearList.add(sDate);
			sDate = getYearDate(sDate,Calendar.DATE,1);
		}
		yearList.add(eDate);
		//根据查询条件，查询相应的数据
		List<Map<String,Object>> listMap = eaDao.groupEnergy(params);
		//组装chartJson格式开始
		boolean lmEmpty = null!=listMap && listMap.size()>0;
		for(String type:energyTypes){
			List<String> curList = new ArrayList<String>();//每个能源类型今年某一时间段（查询条件中的时间段）的值，存储为list形式
			List<String> lastList = new ArrayList<String>();//每个能源类型去年某一时间段（查询条件中的时间段）的值，存储为list形式
			Double today = 0.0,lastYearToday = 0.0;//今天和去年今天的能耗数据
			String cdate = getYearDate(null,Calendar.DATE, 0);//今天日期
			String ldate = getYearDate(cdate,Calendar.YEAR, -1);//去年今天日期
			if(lmEmpty){//如果查询结果存在，需要设置上面所定义的变量，方便下面封装chartJson
				for(Map<String,Object> map : listMap){
					String curyear = map.get("curyear").toString();
					String yeardate = map.get("yeardate").toString();
					String value = String.valueOf(map.get(type));
					if("0".equals(curyear)){
						lastList.add(value);//添加
					}else if("1".equals(curyear)){
						curList.add(value);//添加
					}
					if(cdate.equals(yeardate)){
						today = Double.parseDouble(value);//拿到今天的能耗
					}
					if(ldate.equals(yeardate)){
						lastYearToday = Double.parseDouble(value);//拿到去年今天的能耗
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
			energy.put("value", lmEmpty?today:"0");
			energy.put("type", "0");
			rateMap.put("energy", energy);
			//封装同比数据的map
			Map<String,Object> rate = new HashMap<String,Object>();
			Double scale = Double.valueOf(lastYearToday)==0.0?0.0:
				(double)Math.round((Double.valueOf(today)-Double.valueOf(lastYearToday))/Double.valueOf(lastYearToday)*10000)/100;//计算同比值
			String rateType = "0";
			if(lastYearToday>today)  rateType = "1";//设置同比数据后面的上下箭头
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

}
