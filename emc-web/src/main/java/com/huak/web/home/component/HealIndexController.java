package com.huak.web.home.component;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.Constants;
import com.huak.common.utils.DoubleUtils;
import com.huak.health.type.PercentType;
import com.huak.home.component.ComponentService;
import com.huak.home.type.ToolVO;
import com.huak.web.home.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MR-BIN on 2017/9/26.
 */
@Controller
@RequestMapping("/healthcheck")
public class HealIndexController   extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ComponentService componentService;
    /**
     * 组件-健康指数检测
     * @param request
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public String healthcheck(ToolVO toolVO,HttpServletRequest request) {
        logger.info("组件-健康指数检测加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
             /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);
            Map<String,Object> jjdata = new HashMap<>();
            Map<String,Object> datemp  = componentService.getAlarms(params);

            if((boolean)datemp.get("flag")){
                Map<String,Object> temp = (Map<String, Object>) datemp.get("data");

                int jjSerious = null == temp.get("serious") ?0:Integer.valueOf(temp.get("serious").toString());
                int jjModerate = null == temp.get("moderate") ?0:Integer.valueOf(temp.get("moderate").toString());
                int jjMild = null == temp.get("mild") ?0:Integer.valueOf(temp.get("mild").toString());
                jjdata.put("serious",jjSerious);
                jjdata.put("moderate",jjModerate);
                jjdata.put("mild",jjMild);
                if(jjSerious+jjModerate+jjMild>0){
                    jjdata.put("css","m");
                }else{
                    jjdata.put("css","a");
                }

            }else{
                jjdata.put("serious",0);
                jjdata.put("moderate",0);
                jjdata.put("mild",0);
                jjdata.put("css","a");
            }
            Map<String,Object> data = new HashMap<>();
            Map<String,Object> workData = componentService.getWorkAlarms(params);
            int workLevel1 = null == workData.get("level1") ?0:Integer.valueOf(workData.get("level1").toString());
            int workLevel2 = null == workData.get("level2") ?0:Integer.valueOf(workData.get("level2").toString());
            int workLevel3 = null == workData.get("level3") ?0:Integer.valueOf(workData.get("level3").toString());
            int workLevel4 = null == workData.get("level4") ?0:Integer.valueOf(workData.get("level4").toString());
            workData.put("level1",workLevel1);
            workData.put("level2",workLevel2);
            workData.put("level3",workLevel3);
            workData.put("level4",workLevel4);
            if(workLevel1+workLevel2+workLevel3+workLevel4>0){
                workData.put("css","m");
            }else{
                workData.put("css","a");
            }

            Map<String,Object> fwdata = new HashMap<>();
            fwdata.put("serious",0);
            fwdata.put("moderate",0);
            fwdata.put("mild",0);
            fwdata.put("css","a");

            Map<String,Object> tempData = componentService.getTempAlarms(params);
            int tempMin = null == tempData.get("min") ?0:Integer.valueOf(tempData.get("min").toString());
            int tempMax = null == tempData.get("max") ?0:Integer.valueOf(tempData.get("max").toString());
            tempData.put("min",tempMin);
            tempData.put("max",tempMax);
            if(tempMax+tempMin>0){
                tempData.put("css","m");
            }else{
                tempData.put("css","a");
            }
            data.put("gkyx",workData);
            data.put("jjyx",jjdata);
            data.put("fwqk",fwdata);
            data.put("zygl",tempData);
            Double score = calculatedFraction(workData,jjdata,fwdata,tempData);
            data.put("score",score);
            if (data!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, data);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }
        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("组件-健康指数检测加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     * 健康指数分数
     * 初始化分数100分
     * 四个模块分为1/4*100
     * 工况运行 一级4/10 二级3/10 三级2/10 四级1/10
     * 经济运行 行标3/6 地标2/6 企标1/6
     * 服务情况 1/1
     * 室温报警 低温1/2 高温1/2
     * @param workData 工况运行
     * @param jjdata 经济运行
     * @param fwdata 服务情况
     * @param tempData 室温报警
     * @return 分数
     */
    public Double calculatedFraction(Map<String,Object> workData,Map<String,Object> jjdata,Map<String,Object> fwdata,Map<String,Object> tempData){
        Double score = 100d;

        Double work = 0d;
        int workLevel1 = null == workData.get("level1") ?0:Integer.valueOf(workData.get("level1").toString());
        int workLevel2 = null == workData.get("level2") ?0:Integer.valueOf(workData.get("level2").toString());
        int workLevel3 = null == workData.get("level3") ?0:Integer.valueOf(workData.get("level3").toString());
        int workLevel4 = null == workData.get("level4") ?0:Integer.valueOf(workData.get("level4").toString());
        if(workLevel1>0){
            work = DoubleUtils.add(work, PercentType.WORK_ONE.getMolecule());
        }
        if(workLevel2>0){
            work = DoubleUtils.add(work, PercentType.WORK_TWO.getMolecule());
        }
        if(workLevel3>0){
            work = DoubleUtils.add(work, PercentType.WORK_THREE.getMolecule());
        }
        if(workLevel4>0){
            work = DoubleUtils.add(work, PercentType.WORK_FOUR.getMolecule());
        }
        work = DoubleUtils.div(work,PercentType.WORK_ONE.getDenominator());

        Double temp = 0d;
        int tempMin = null == tempData.get("min") ?0:Integer.valueOf(tempData.get("min").toString());
        int tempMax = null == tempData.get("max") ?0:Integer.valueOf(tempData.get("max").toString());
        if(tempMin>0){
            temp = DoubleUtils.add(temp, PercentType.TEMP_MIN.getMolecule());
        }
        if(tempMax>0){
            temp = DoubleUtils.add(temp, PercentType.TEMP_MAX.getMolecule());
        }
        temp = DoubleUtils.div(temp,PercentType.TEMP_MIN.getDenominator());

        Double jj = 0d;
        int jjSerious = null == jjdata.get("serious") ?0:Integer.valueOf(jjdata.get("serious").toString());
        int jjModerate = null == jjdata.get("moderate") ?0:Integer.valueOf(jjdata.get("moderate").toString());
        int jjMild = null == jjdata.get("mild") ?0:Integer.valueOf(jjdata.get("mild").toString());
        if(jjSerious>0){
            jj = DoubleUtils.add(jj, PercentType.JJ_ROWER.getMolecule());
        }
        if(jjModerate>0){
            jj = DoubleUtils.add(jj, PercentType.JJ_LANDMARK.getMolecule());
        }
        if(jjMild>0){
            jj = DoubleUtils.add(jj, PercentType.JJ_ENTER.getMolecule());
        }
        jj = DoubleUtils.div(jj,PercentType.JJ_ROWER.getDenominator());

        work = DoubleUtils.div(DoubleUtils.mul(work,PercentType.FOUR_ONE.getMolecule()),PercentType.FOUR_ONE.getDenominator());
        temp = DoubleUtils.div(DoubleUtils.mul(temp,PercentType.FOUR_ONE.getMolecule()),PercentType.FOUR_ONE.getDenominator());
        jj = DoubleUtils.div(DoubleUtils.mul(jj,PercentType.FOUR_ONE.getMolecule()),PercentType.FOUR_ONE.getDenominator());
        Double service = 0d;

        Double reduce = DoubleUtils.add(work,DoubleUtils.add(temp,DoubleUtils.add(jj,service)));
        score = DoubleUtils.mul(score,DoubleUtils.sub(1d,reduce));
        return DoubleUtils.round(score,0);
    }

}
