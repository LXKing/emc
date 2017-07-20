package com.huak.home.component;


import com.huak.base.dao.DateDao;
import com.huak.common.MathsUtil;
import com.huak.common.utils.DateUtils;
import com.huak.home.dao.SearchDao;
import com.huak.home.dao.component.ComponentDao;
import com.huak.task.dao.TemperatureDao;
import com.huak.task.model.Temperature;
import com.huak.weather.dao.WeatherAqiDao;
import com.huak.weather.dao.WeatherDao;
import com.huak.weather.dao.WeekforcastDao;
import com.huak.weather.model.Weather;
import com.huak.weather.model.WeatherAQI;
import com.huak.weather.model.Weekforcast;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by MR-BIN on 2017/6/8.
 */
@Service
public class ComponentServiceImpl implements ComponentService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ComponentDao componentDao;

    @Autowired
    private DateDao dateDao;

    @Autowired
    private SearchDao searchDao;

    @Autowired
    private WeatherAqiDao weatherAqiDao;

    @Autowired
    private WeekforcastDao weekforcastDao;

    @Autowired
    private WeatherDao weatherDao;

    @Autowired
    private TemperatureDao temperatureDao;

    /**
     * 组件 能耗明细查询
     * @param params
     * @return
     */
    public Map<String,Object> energyDetail(Map<String,Object> params){
        Map<String,Object> currentplan =new HashMap<>();
        Map<String,Object> previousplan = new HashMap<>();
        String sdate = "";
        String edate = "";
        String psdate = "";
        String pedate = "";
        Map<String,Object> season = this.getSeason(params);
        int planDays= 0;
        int currentDays = 0;
        Map<String,Object> currentSeason = (Map<String, Object>) season.get("currentSeason");
        Map<String,Object> preSeason = (Map<String, Object>) season.get("preSeason");
        if(currentSeason != null){
            sdate =currentSeason.get("SDATE").toString();
            edate = currentSeason.get("EDATE").toString();
            params.put("startTime",sdate);
            params.put("endTime",edate);
            try {
                if(StringUtils.isNotBlank(sdate) && StringUtils.isNotBlank(edate)){
                    planDays = DateUtils.daysBetween(sdate,edate)+1;
                    String nowDate = dateDao.getDate();
                    currentDays = DateUtils.daysBetween(sdate, nowDate)+1;
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
            currentplan = componentDao.getplan(params);
        }
        if(preSeason != null){
            psdate =preSeason.get("SDATE").toString();
            pedate = preSeason.get("EDATE").toString();
            params.put("startTime",psdate);
            params.put("endTime",pedate);
            previousplan = componentDao.getplan(params);
        }

        if(StringUtils.isNotBlank(sdate)){
            params.put("startTime",sdate+" 00:00:00");
        }else {
            params.put("startTime",sdate);
        }
        if(StringUtils.isNotBlank(edate)){
            params.put("endTime",edate+" 23:59:59");
        }else{
            params.put("endTime",edate);
        }
        if(StringUtils.isNotBlank(psdate)){
            params.put("pstartTime",psdate+" 00:00:00");
        }else {
            params.put("pstartTime",psdate);
        }
        if(StringUtils.isNotBlank(pedate)){
            params.put("pendTime",pedate+" 23:59:59");
        }else{
            params.put("pendTime",pedate);
        }
        Map<String,Object> data =  componentDao.energyDetail(params);
        if(currentplan != null){
            data.put("currentPlan",currentplan.get("plan")==null?0:currentplan.get("plan"));
        }else{
            data.put("currentPlan",0);
        }
        if(previousplan != null){
            data.put("previousPlan",previousplan.get("plan")==null?0:previousplan.get("plan"));
        }else{
            data.put("previousPlan",0);
        }
        data.put("planDays",planDays);
        data.put("currentDays",currentDays);
        data.put("isInCurrentSeason",season.get("isInCurrentSeason"));
        return this.digitData(data);

    }

    /**
     * 组件 成本明细
     * @param params
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> costDetail(Map<String, Object> params) {
        String starttime =  params.get("startTime").toString()+" 00:00:00";
        String endTime =  params.get("endTime").toString()+" 23:59:59";
        String pstartTime = this.getPreviousDates(starttime,"yyyy-MM-dd HH:mm:ss");
        String pendTime = this.getPreviousDates(endTime,"yyyy-MM-dd HH:mm:ss");
        params.put("pstartTime",pstartTime);
        params.put("pendTime",pendTime);
        return componentDao.costDetail(params);
    }

    /**
     * 组件 天气-空气质量
     * @param params
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> weatherForcast(Map<String, Object> params) {
        Map<String,Object> data = new HashMap<>();
        String times = dateDao.getTime().substring(0, 13)+":21:00";
        String date = dateDao.getDate();
        params.put("reportDate",date);
        List<Weekforcast> weekforcasts = weekforcastDao.selectByComponent(params);
        params.put("reportDate",times);
        List<Weather> weathers = weatherDao.getLatestWeathers(params);
        Weather  weather = weatherDao.selectByPrimaryKey(params);
        WeatherAQI weatherAQI = weatherAqiDao.selectById(params);
        Map<String,Object> datas = new HashMap<>();
        List<String> hours = new ArrayList<>();
        List<String> tem = new ArrayList<>();
        if(weathers!=null && weathers.size()>0){
            for(int i = weathers.size()-1; i>=0;i--){
                String hour =  weathers.get(i).getreportDate().substring(6,13);
                hours.add(hour);
                tem.add(weathers.get(i).getTemperatureCurr().replace("℃",""));
            }
            datas.put("hour",hours);
            datas.put("temp",tem);
        }
        data.put("weekForcast",weekforcasts);
        data.put("currentWeather",weather);
        data.put("aqi",weatherAQI);
        data.put("weathers",datas);
        return data;
    }

    /**
     * 组件 室温散点查询
     * @param params
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> roomTemperature(Map<String, Object> params) {
        Map<String,Object> result = new HashMap<>();
        List<Map<String,Object>> datas = temperatureDao.selectByMap(params);
        List<String> times = new ArrayList<>();
        List<Map<String,Object>> minlist= new ArrayList<>();//最小临界值的数据
        List<Map<String,Object>> maxlist= new ArrayList<>();//最大临界值的数据
        List<Map<String,Object>> rangelist= new ArrayList<>();//范围数据
        List<Map<String,Object>> barlist = new ArrayList<>();//bar组件的数据
        Double min = Double.parseDouble(params.get("min").toString());
        Double max = Double.parseDouble( params.get("max").toString());
        Double minpercent = 0.0;
        Double maxpercent = 0.0;
        Double rangepercent = 0.0;
        for(Map data:datas){
            times.add(data.get("times").toString());
            Double temps = Double.parseDouble(data.get("temps").toString());
            if(temps> max){
                maxlist.add(data);
            }
            if(temps< min){
                minlist.add(data);
            }
            if(temps>= min && temps<= max){
                rangelist.add(data);
            }
        }
        /*line-bar占比*/
        if(datas.size()>0){
            minpercent =  MathsUtil.round(MathsUtil.div(minlist.size(),datas.size()),2)*100;
            maxpercent =  MathsUtil.round(MathsUtil.div(maxlist.size(),datas.size()),2)*100;
            rangepercent =  MathsUtil.round(MathsUtil.div(rangelist.size(),datas.size()),2)*100;
        }
        Map<String,Object> minmap= new HashMap<>();
        minmap.put("value",minpercent);
        minmap.put("color","#32bbb6");
        minmap.put("text",min+"℃以下");
        Map<String,Object> maxmap= new HashMap<>();
        maxmap.put("value",maxpercent);
        maxmap.put("color","#32bbb6");
        maxmap.put("text",max+"℃以上");
        Map<String,Object> rangemap= new HashMap<>();
        rangemap.put("value",rangepercent);
        rangemap.put("color","#32bbb6");
        rangemap.put("text",min+"~"+max+"℃");
        barlist.add(minmap);
        barlist.add(rangemap);
        barlist.add(maxmap);
        result.put("bar",barlist);
        result.put("datas",datas);
        result.put("times",times);
        return result;
    }

    /**
     * 组件-近期单耗详情
     * @param paramsMap
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> selectrecentDetail(Map<String, Object> paramsMap) {

        try {
            String date = dateDao.getDate();
            String yesterday = DateUtils.getDay(date,-1);
            String towdayago = DateUtils.getDay(date,-2);
            String treedayago = DateUtils.getDay(date,-3);
            paramsMap.put("today",date);
            paramsMap.put("yesterday",yesterday);
            paramsMap.put("towdayago",towdayago);
            paramsMap.put("treedayago",treedayago);
            List<Map<String,Object>> result = componentDao.selectrecentDetail(paramsMap);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 组件 单耗趋势
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> energycomparison(Map<String, Object> params) {
        String starttime =  params.get("startTime").toString()+" 00:00:00";
        String endTime =  params.get("endTime").toString()+" 23:59:59";
        String pstartTime = this.getPreviousDates(starttime,"yyyy-MM-dd HH:mm:ss");
        String pendTime = this.getPreviousDates(endTime,"yyyy-MM-dd HH:mm:ss");
        params.put("startTime",starttime);
        params.put("endTime",endTime);
        List<Map<String, Object>> current = componentDao.getenergycomparision(params);
        params.put("startTime",pstartTime);
        params.put("endTime",pendTime);
        List<Map<String, Object>> previous = componentDao.getenergycomparision(params);
        Map<String,Object> previousValue = new HashMap<>();
        for(Map pr : previous){
            previousValue.put(pr.get("dates").toString(),pr.get("num"));
        }
        List<String> days = new ArrayList<>();//时间列表
        List<Object> values = new ArrayList<>();//今年对应时间列表的值
        List<Object> previousValues = new ArrayList<>();//去年对应时间列表的值
        List<Object> avg = new ArrayList<>();//今年的平均单耗
        List<Object> excellent = new ArrayList<>();//今年的上线
        List<Object> standard = new ArrayList<>();//今年的下线
        double total = 0.0;
        double avgs = 0.0;
        for (Map da :current){
            days.add(da.get("dates").toString());//时间列表
            excellent.add(da.get("excellent"));//上线
            standard.add(da.get("standard"));//下线
            values.add(da.get("num").toString());//今年时间值
            String pretime = this.getPreviousDates(da.get("dates").toString(),"yyyy-MM-dd");
            if(previousValue.containsKey(pretime)){
                previousValues.add(previousValue.get(pretime));
            }else{
                previousValues.add(0);
            }
            total += Double.valueOf(da.get("num").toString());
        }
        if(days.size()>0){
            avgs = total/days.size();
            for(int i=0 ;i< days.size();i++){
                DecimalFormat df = new DecimalFormat("0.00");
                avg.add(df.format(avgs));
            }
        }
        List<Map<String,Object>> results = new ArrayList<>();
        Map<String,Object> currentYear = new HashMap<>();
        currentYear.put("typeName","今年");
        currentYear.put("dataList",values);
        results.add(currentYear);
        Map<String,Object> preYear = new HashMap<>();
        preYear.put("typeName","去年");
        preYear.put("dataList",previousValues);
        results.add(preYear);

        //上线、下线、平均值
        Map<String,Object> other = new HashMap<>();
        Map<String,Object> upper = new HashMap<>();
        upper.put("typeName","上限");
        upper.put("data",excellent);

        Map<String,Object> standa = new HashMap<>();
        standa.put("typeName","下限");
        standa.put("data",standard);

        Map<String,Object> avgMap = new HashMap<>();
        avgMap.put("typeName","平均值");
        avgMap.put("data",avg);

        other.put("upperLimit",upper);
        other.put("lowerLimit",standa);
        other.put("average",avgMap);

        Map<String,Object> result = new HashMap<>();
        result.put("yearDate",days);
        result.put("data",results);
        result.put("other",other);
        return result;
    }



    /**
     * 工具-获取上年的日期并格式化
     * @param starttime
     * @param format
     * @return
     */
    private String getPreviousDates(String starttime,String format) {
        String ptime = "";
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(new SimpleDateFormat(format).parse(starttime));
            calendar.add(Calendar.YEAR, -1);
            ptime = new SimpleDateFormat(format).format(calendar.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ptime;
    }

    /**
     * 工具 计算同比环比
     * @param data
     * @return
     */
    private Map<String, Object> digitData(Map<String, Object> data) {

            if(data != null){
                /*标煤同比增长*/
                Double jn_total = (double) data.get("bm_total");
                Double qn_total = (double) data.get("qn_bm_total");
                if(jn_total>qn_total){
                    data.put("total_flag",true);
                }
                if(jn_total == qn_total){
                    data.put("total_flag",null);
                }
                if(jn_total < qn_total){
                    data.put("total_flag",false);
                }
                if(qn_total!= 0){
                    DecimalFormat df = new DecimalFormat("0.00");
                    String total_tb =String.valueOf(df.format(Math.abs(jn_total-qn_total)/qn_total*100));
                    data.put("total_tb",total_tb+"%");
                }else{
                    data.put("total_tb","#%");
                }

                /*水*/

                double jn_whater = (double) data.get("whater");
                double qn_whater = (double) data.get("qn_whater");
                if(jn_whater>qn_whater){
                    data.put("whater_flag",true);
                }
                if(jn_whater == qn_whater){
                    data.put("whater_flag",null);
                }
                if(jn_whater < qn_whater){
                    data.put("whater_flag",false);
                }
                if(qn_whater!= 0){
                    DecimalFormat df = new DecimalFormat("0.00");
                    String total_tb =String.valueOf(df.format(Math.abs(jn_whater-qn_whater)/qn_whater*100));
                    data.put("whater_tb",total_tb+"%");
                }else{
                    data.put("whater_tb","#%");
                }

                /*电*/

                double jn_electric = (double) data.get("electric");
                double qn_electric = (double) data.get("qn_electric");
                if(jn_electric>qn_electric){
                    data.put("electric_flag",true);
                }
                if(jn_electric == qn_electric){
                    data.put("electric_flag",null);
                }
                if(jn_electric < qn_electric){
                    data.put("electric_flag",false);
                }
                if(qn_electric!= 0){
                    DecimalFormat df = new DecimalFormat("0.00");
                    String electric_tb =String.valueOf(df.format(Math.abs(jn_electric-qn_electric)/qn_electric*100));
                    data.put("electric_tb",electric_tb+"%");
                }else{
                    data.put("electric_tb","#%");
                }
                /*气*/

                double jn_gas = (double) data.get("gas");
                double qn_gas = (double) data.get("qn_gas");
                if(jn_gas>qn_gas){
                    data.put("gas_flag",true);
                }
                if(jn_gas == qn_gas){
                    data.put("gas_flag",null);
                }
                if(jn_gas < qn_gas){
                    data.put("gas_flag",false);
                }
                if(qn_gas!= 0){
                    DecimalFormat df = new DecimalFormat("0.00");
                    String gas_tb =String.valueOf(df.format(Math.abs(jn_gas-qn_gas)/qn_gas*100));
                    data.put("gas_tb",gas_tb+"%");
                }else{
                    data.put("gas_tb","#%");
                }
                /*热*/
                double jn_heat = (double) data.get("heat");
                double qn_heat = (double) data.get("qn_heat");
                if(jn_heat>qn_heat){
                    data.put("heat_flag",true);
                }
                if(jn_heat == qn_heat){
                    data.put("heat_flag",null);
                }
                if(jn_heat < qn_heat){
                    data.put("heat_flag",false);
                }
                if(qn_heat!= 0){
                    DecimalFormat df = new DecimalFormat("0.00");
                    String heat_tb =String.valueOf(df.format(Math.abs(jn_heat-qn_heat)/qn_heat*100));
                    data.put("heat_tb",heat_tb+"%");
                }else{
                    data.put("heat_tb","#%");
                }


                /*煤*/
                double jn_coal = (double) data.get("coal");
                double qn_coal = (double) data.get("qn_coal");
                if(jn_coal>qn_coal){
                    data.put("coal_flag",true);
                }
                if(jn_coal == qn_coal){
                    data.put("coal_flag",null);
                }
                if(jn_coal < qn_coal){
                    data.put("coal_flag",false);
                }
                if(qn_coal!= 0){
                    DecimalFormat df = new DecimalFormat("0.00");
                    String coal_tb =String.valueOf(df.format(Math.abs(jn_coal-qn_coal)/qn_coal*100));
                    data.put("coal_tb",coal_tb+"%");
                }else{
                    data.put("coal_tb","#%");
                }

                double pcd = 0.0;
                double kedu1 = 0.0;
                double pcdz = 0.0;
                boolean fla = (boolean) data.get("isInCurrentSeason");
                int currentDays = (int) data.get("currentDays");
                int planDays = (int) data.get("planDays");
                double currentPlan = Double.parseDouble(data.get("currentPlan").toString());
                if(fla){
                    kedu1 =  MathsUtil.round(MathsUtil.mul(MathsUtil.div(currentDays,planDays),0.75),2);
                    if(planDays == 0 ){
                        if(currentPlan == 0){
                            pcd = 0 ;
                        }else{
                            pcd =  MathsUtil.round(MathsUtil.mul(MathsUtil.div(jn_total,currentPlan),100),4);
                        }
                        pcdz = jn_total;
                    }else{
                        if(currentPlan != 0){
                            pcd = MathsUtil.round(MathsUtil.div(MathsUtil.sub(jn_total,MathsUtil.mul(MathsUtil.div(currentDays,planDays),currentPlan)),currentPlan)*100,4);
                        }else{
                            pcd = 0;
                        }
                        pcdz =MathsUtil.round(MathsUtil.sub(jn_total,MathsUtil.mul(MathsUtil.div(currentDays,planDays),currentPlan)),2);

                    }
                }else{
                    kedu1 = 0.75;
                    if(planDays == 0 ){
                        if(currentPlan == 0){
                            pcd = 0 ;
                        }else{
                            pcd = MathsUtil.round(MathsUtil.div(jn_total,currentPlan)*100,2);

                        }
                        pcdz = jn_total;
                    }else{
                        if(currentPlan != 0){
                            pcd = MathsUtil.round(MathsUtil.div((jn_total -currentPlan),currentPlan)*100,2);
                        }else{
                            pcd = 0;
                        }

                        pcdz =  MathsUtil.sub(jn_coal,currentPlan);

                    }

                }
                data.put("jn_coal",jn_coal);
                data.put("currentPlan",currentPlan);
                data.put("pcd",pcd);
                data.put("pcdz",pcdz);
                data.put("kedu1",kedu1);
            }

        return data;
    }

    /**
     * 工具 获取采暖季
     * @return
     */
    private Map<String, Object> getSeason(Map<String,Object> params) {
        String nowDate = dateDao.getDate();
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("comId",params.get("comId"));
        paramsMap.put("sdate",nowDate);
        paramsMap.put("edate",nowDate);
        Map<String,Object> data = new HashMap<>();
        Map<String,Object> season = componentDao.getCurrentSeason(paramsMap);
        //当前时间不在采暖季之间，取去年的采暖季
        if(season == null){//不在采暖季里
            data.put("isInCurrentSeason",false);
            List<Map<String, Object>> seasons = componentDao.getPreSeason(paramsMap);
            if(seasons.size()==0){//查询上个采暖季为空
                data.put("currentSeason",null);
                data.put("preSeason",null);
            }else {//查询结果不为空
                season = seasons.get(0);//查询上个采暖季的结果不为空
                Integer day = null;
                try {
                    day = DateUtils.daysBetween(season.get("EDATE").toString(), nowDate) +1;
                    if(day>365){//计算上个采暖季记录中的结束时间距当前系统时间超过1年
                         data.put("currentSeason",null);//则本采暖季的开始和结束日期不能用上个采暖季的开始结束日期
                         if(day <=730){//如果获取到的采暖季结束日期距当前系统日期小于2年则为上上个采暖季
                             data.put("preSeason",season);
                         }else{
                             data.put("preSeason",null);
                         }

                    }else{//计算上个采暖季记录中的结束时间距当前系统时间没超过1年，则为刚过去采暖季
                        data.put("currentSeason",season);//将刚过去的采暖季日期作为本采暖季显示
                        if(seasons.size()>1){//查询上个采暖季的结果集大于2
                            season = seasons.get(1);//上上个采暖季
                            day = DateUtils.daysBetween(season.get("EDATE").toString(), nowDate)+1;
                            if(day >730){//如果上上个采暖季距系统日期大于2年则说明上上个采暖季距上个采暖季大于1年，上上个采暖季为空
                                data.put("preSeason",null);
                            }else{//上上个采暖季和上个采暖季都可用
                                data.put("preSeason",season);
                            }
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }else {//在采暖季
            data.put("isInCurrentSeason",true);
            data.put("currentSeason",season);
            List<Map<String, Object>> seasons = componentDao.getPreSeason(paramsMap);
            if(seasons.size()==0){//查询上个采暖季为空
                data.put("preSeason",null);
            }else {//查询结果不为空
                season = seasons.get(0);
                Integer day = null;
                try {
                    day = DateUtils.daysBetween(season.get("EDATE").toString(), nowDate)+1;
                    if(day>365) {//如果查询最大采暖季结束时间大于365
                            data.put("preSeason", null);
                    }else{
                        data.put("preSeason",season);
                    }
                }catch (Exception e){
                  e.printStackTrace();
                }
            }
        }
        return data;
    }

}
