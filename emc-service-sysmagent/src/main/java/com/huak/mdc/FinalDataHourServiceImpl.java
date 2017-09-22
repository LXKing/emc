package com.huak.mdc;

import com.huak.common.UUIDGenerator;
import com.huak.common.utils.DateUtils;
import com.huak.common.utils.DoubleUtils;
import com.huak.mdc.dao.FinalDataHourDao;
import com.huak.mdc.dao.MeterCollectDao;
import com.huak.mdc.model.EnergyDataHis;
import com.huak.mdc.model.FinalDataHour;
import com.huak.mdc.model.MeterCollect;
import com.huak.org.model.Company;
import com.huak.sys.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.mdc<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/29<BR>
 * Description: 最终能耗数据    <BR>
 * Function List:  <BR>
 */
@Service
public class FinalDataHourServiceImpl implements FinalDataHourService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private FinalDataHourDao finalDataHourDao;
    @Resource
    private MeterCollectDao meterCollectDao;
    //标煤系数
    @Resource
    private CoalCoefService coalCoefService;
    //碳排放
    @Resource
    private CarbonFormulaService carbonFormulaService;
    //能源单价
    @Resource
    private EnergyPriceService energyPriceService;
    //单位面积
    @Resource
    private UnitAreaService unitAreaService;
    //天气
    @Resource
    private WeatherService weatherService;

    /**
     * 时间段能耗更新
     *
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public boolean saveDataHour(EnergyDataHis energyDataHis, EnergyDataHis data, Company company) throws Exception {
        String start = "";
        String end = "";
        if (energyDataHis.getCollectTime().compareTo(data.getCollectTime()) > 0) {
            start = data.getCollectTime();
            end = energyDataHis.getCollectTime();
        } else {
            end = data.getCollectTime();
            start = energyDataHis.getCollectTime();
        }
        //时间段每小时集合
        List<String> dateTimes = DateUtils.getDateTimes(start, end);

        //查询计量采集表
        MeterCollect meterCollect = meterCollectDao.selectByPrimaryKey(energyDataHis.getCollectId());

        // 判断特殊的换表和预存
        Double dosage;
        if (energyDataHis.getCollectTime().compareTo(data.getCollectTime()) > 0) {//判断前期是否换表预存
            Double dosageTotal;
            if (1 == data.getIschange() && 1 == data.getIsprestore()) {//换表且预存，新表表底+预存值
                Double num = DoubleUtils.add(data.getChangeNum(), data.getPrestoreNum());
                dosageTotal = DoubleUtils.sub(energyDataHis.getCollectNum(), num);
            } else if (1 == data.getIschange() && 0 == data.getIsprestore()) {//只换表，新表表底
                dosageTotal = DoubleUtils.sub(energyDataHis.getCollectNum(), data.getChangeNum());
            } else if (0 == data.getIschange() && 1 == data.getIsprestore()) {//只预存，旧表表底+预存值
                Double num = DoubleUtils.add(data.getCollectNum(), data.getPrestoreNum());
                dosageTotal = DoubleUtils.sub(energyDataHis.getCollectNum(), num);
            } else {
                dosageTotal = DoubleUtils.sub(data.getCollectNum(), energyDataHis.getCollectNum());
            }
            dosageTotal = Math.abs(dosageTotal);//绝对值，忽略预存负值
            dosage = DoubleUtils.div(dosageTotal, dateTimes.size(), 2);//每小时平均能耗
        } else {//判断本期是否换表预存
            Double dosageTotal;
            if (1 == energyDataHis.getIschange() && 1 == energyDataHis.getIsprestore()) {//换表且预存，新表表底+预存值
                Double num = DoubleUtils.add(energyDataHis.getChangeNum(), energyDataHis.getPrestoreNum());
                dosageTotal = DoubleUtils.sub(data.getCollectNum(), num);
            } else if (1 == energyDataHis.getIschange() && 0 == energyDataHis.getIsprestore()) {//只换表，新表表底
                dosageTotal = DoubleUtils.sub(data.getCollectNum(), energyDataHis.getChangeNum());
            } else if (0 == energyDataHis.getIschange() && 1 == energyDataHis.getIsprestore()) {//只预存，旧表表底+预存值
                Double num = DoubleUtils.add(energyDataHis.getCollectNum(), energyDataHis.getPrestoreNum());
                dosageTotal = DoubleUtils.sub(data.getCollectNum(), num);
            } else {
                dosageTotal = DoubleUtils.sub(data.getCollectNum(), energyDataHis.getCollectNum());
            }
            dosageTotal = Math.abs(dosageTotal);//绝对值，忽略预存负值
            dosage = DoubleUtils.div(dosageTotal, dateTimes.size(), 2);//每小时平均能耗
        }

        //time %Y-%m-%d %H:00:00
        for (String time : dateTimes) {
            //查询该时间的标煤系数
            Double coalCoef = coalCoefService.getCoalCoefByTime(company.getId(), meterCollect.getEnergyTypeId(), time);
            //查询该时间的碳排放系数
            Double carbonFormula = carbonFormulaService.getCarbonFormulaByTime(company.getId(), meterCollect.getEnergyTypeId(), time);
            //查询该时间的单位面积
            Double unitArea = unitAreaService.getUnitAreaByTime(company.getId(), meterCollect.getUnitId(), meterCollect.getUnitType(), time);
            //查询该时间的能源单价
            BigDecimal energyPrice = energyPriceService.getEnergyPriceByTime(company.getId(), meterCollect.getEnergyTypeId(), time);
            //查询该时间的天气温度
            Double weather = weatherService.getWeatherByTime(company.getWcode(), time);
            //todo 计算折算天气温度
            //todo 查询该时间段的室内温度
            Double cWeather = weather;
            //todo 计算折算室内温度
            FinalDataHour dataHour = new FinalDataHour();
            String id = UUIDGenerator.getUUID();
            dataHour.setId(id);//自动生成id
            dataHour.setTableName(company.getTableName());//动态表
            //dataHour.setTableName("t_emc_final_data_hour");
            dataHour.setNodeid(energyDataHis.getCollectId());
            dataHour.setComid(company.getId());
            dataHour.setUnitid(meterCollect.getUnitId());
            dataHour.setTypeid(meterCollect.getEnergyTypeId());
            dataHour.setDosageTime(time);
            dataHour.setDosage(dosage);
            dataHour.setArea(unitArea);
            dataHour.setPrice(energyPrice);
            dataHour.setWtemp(weather);
            dataHour.setCwtemp(cWeather);
            dataHour.setCoalCoef(coalCoef);
            dataHour.setcCoef(carbonFormula);
            int i = finalDataHourDao.insertOrUpdate(dataHour);
            if (1 != i) {
                return false;
            }
        }
        return true;
    }

    /**
     * 虚表时间段能耗更新
     *
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public boolean saveVirtualDataHour(MeterCollect meterCollect, List<String> dateTimes, List<String> codes, Company company) throws Exception {
        //time %Y-%m-%d %H:00:00
        for (String time : dateTimes) {
            //查询该时间的标煤系数
            Double coalCoef = coalCoefService.getCoalCoefByTime(company.getId(), meterCollect.getEnergyTypeId(), time);
            //查询该时间的碳排放系数
            Double carbonFormula = carbonFormulaService.getCarbonFormulaByTime(company.getId(), meterCollect.getEnergyTypeId(), time);
            //查询该时间的单位面积
            Double unitArea = unitAreaService.getUnitAreaByTime(company.getId(), meterCollect.getUnitId(), meterCollect.getUnitType(), time);
            //查询该时间的能源单价
            BigDecimal energyPrice = energyPriceService.getEnergyPriceByTime(company.getId(), meterCollect.getEnergyTypeId(), time);
            //查询该时间的天气温度
            Double weather = weatherService.getWeatherByTime(company.getWcode(), time);
            //todo 计算折算天气温度
            //todo 查询该时间段的室内温度
            Double cWeather = weather;
            //todo 计算折算室内温度

            //根据公式计算能耗
            Map<String, Object> codeValue = getCodeValues(codes, company, time);
            Double dosage = getVirtualDosage(codeValue, meterCollect.getFormula(), codes);

            FinalDataHour dataHour = new FinalDataHour();
            String id = UUIDGenerator.getUUID();
            dataHour.setId(id);//自动生成id
            dataHour.setTableName(company.getTableName());//动态表
            //dataHour.setTableName("t_emc_final_data_hour");
            dataHour.setNodeid(meterCollect.getId());
            dataHour.setComid(company.getId());
            dataHour.setUnitid(meterCollect.getUnitId());
            dataHour.setTypeid(meterCollect.getEnergyTypeId());
            dataHour.setDosageTime(time);
            dataHour.setDosage(dosage);
            dataHour.setArea(unitArea);
            dataHour.setPrice(energyPrice);
            dataHour.setWtemp(weather);
            dataHour.setCwtemp(cWeather);
            dataHour.setCoalCoef(coalCoef);
            dataHour.setcCoef(carbonFormula);
            int i = finalDataHourDao.insertOrUpdate(dataHour);
            if (1 != i) {
                return false;
            }
        }
        return true;
    }

    /**
     * 根据公式返回值
     *
     * @param codeValue
     * @param formula
     * @param codes
     * @return
     * @throws ScriptException
     */
    private Double getVirtualDosage(Map<String, Object> codeValue, String formula, List<String> codes) throws ScriptException {
        for (String code : codes) {
            formula = formula.replaceAll(code, codeValue.get(code).toString());
        }
        formula = formula.replaceAll("/+", "+").replaceAll("-", "-").replaceAll("×", "*").replaceAll("÷", "/");
        ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
        Double num = Double.valueOf(jse.eval(formula).toString());
        DecimalFormat df = new DecimalFormat("######0.000");

        return Double.valueOf(df.format(num));
    }

    /**
     * 根据编码和公司及时间取用量
     *
     * @param codes
     * @param company
     * @param time
     * @return
     */
    public Map<String, Object> getCodeValues(List<String> codes, Company company, String time) {
        Map<String, Object> codeValues = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        params.put("comId", company.getId());
        params.put("codes", codes);
        params.put("time", time);
        //params.put("tableName",company.getTableName());
        params.put("tableName", "t_emc_final_data_hour");
        List<Map<String, Object>> list = finalDataHourDao.selectCodeValue(params);
        for (Map<String, Object> map : list) {
            codeValues.put(map.get("CODE").toString(), map.get("DOSAGE"));
        }
        return codeValues;
    }
}
