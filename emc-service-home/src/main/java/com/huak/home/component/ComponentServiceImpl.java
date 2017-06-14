package com.huak.home.component;


import com.huak.base.dao.DateDao;
import com.huak.common.utils.DateUtils;
import com.huak.home.dao.SearchDao;
import com.huak.home.dao.component.ComponentDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by MR-BIN on 2017/6/8.
 */
@Service
public class ComponentServiceImpl implements ComponentService{

    @Autowired
    private ComponentDao componentDao;

    @Autowired
    private DateDao dateDao;

    @Autowired
    private SearchDao searchDao;

    /**
     * 组件能耗明细查询
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
                    planDays = DateUtils.daysBetween(sdate,edate);
                    String nowDate = dateDao.getDate();
                    currentDays = DateUtils.daysBetween(sdate, nowDate);
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

        params.put("startTime",sdate);
        params.put("endTime",edate);
        params.put("pstartTime",psdate);
        params.put("pendTime",pedate);
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
     * 成本明细
     * @param params
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> costDetail(Map<String, Object> params) {
        String starttime =  params.get("startTime").toString();
        String endTime =  params.get("startTime").toString();
        String pstartTime = "";
        String pendTime = "";
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(starttime));
            calendar.add(Calendar.YEAR, -1);
            pstartTime = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(endTime));
            calendar.add(Calendar.YEAR, -1);
            pendTime = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        params.put("pstartTime",pstartTime);
        params.put("pendTime",pendTime);
        return componentDao.costDetail(params);
    }

    /**
     * 计算同比环比
     * @param data
     * @return
     */
    private Map<String, Object> digitData(Map<String, Object> data) {

            if(data != null){
                /*标煤同比增长*/
                double jn_total = (double) data.get("bm_total");
                double qn_total = (double) data.get("qn_bm_total");
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
            }

        return data;
    }

    /**
     * 获取采暖季
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
                season = seasons.get(0);
                Integer day = null;
                try {
                    day = DateUtils.daysBetween(season.get("EDATE").toString(), nowDate);
                    if(day>365){//如果查询最大采暖季结束时间大于365
                         data.put("currentSeason",null);//获取本采暖季的时间
                         if(day <=730){//获取到上个采暖季
                             data.put("preSeason",season);
                         }else{
                             data.put("preSeason",null);
                         }

                    }else{
                        data.put("currentSeason",season);//获取本采暖季的时间
                        if(seasons.size()>1){
                            season = seasons.get(1);
                            day = DateUtils.daysBetween(season.get("EDATE").toString(), nowDate);
                            if(day >730){
                                data.put("preSeason",null);
                            }else{
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
                    day = DateUtils.daysBetween(season.get("EDATE").toString(), nowDate);
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
