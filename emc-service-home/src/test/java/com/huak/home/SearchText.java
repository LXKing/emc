package com.huak.home;

import com.huak.base.BaseTest;
import com.huak.common.UUIDGenerator;
import com.huak.home.model.EnergyMonitor;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.home<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/6/8<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public class SearchText extends BaseTest{
    @Resource
    private SearchService searchService;
    @Resource
    private EnergyMonitorService energyMonitorService;
    //@Test
    public void findOrgs(){
        String comId = "40a6bfd44863406e8356bbcfe879fd70";
        List<Map<String, Object>> orgs = searchService.getOrgList(comId);
        System.out.print("");
    }

    //@Test
    public void findSeason(){
        String comId = "40a6bfd44863406e8356bbcfe879fd70";
        searchService.getSeason(comId);
    }

    /**
     * 生成测试数据
     */
    @Test
    //@Rollback(true)
    public void createData(){
        Random random = new Random();
        String[] coms = {"74ee3b6752254435b724b6672f9fde8d","74ee3b6752254435b724b6672f9fde8d",
                "74ee3b6752254435b724b6672f9fde8d","74ee3b6752254435b724b6672f9fde8d",
                "74ee3b6752254435b724b6672f9fde8d","74ee3b6752254435b724b6672f9fde8d"};
        String[] units = {"a5d49d21e0494719b25c4b9b66c6f77f","255201429c364657bfb3aa283e66a26c",
                "9a1f16bdf8d74701a85439412d50ffec","33b1b97a228149739b3a78b06c6979d0",
                "f282ac1dd36d4b73b6a9bd21cf374f9f","c992f97161e443e19b11da1e0bc207ff",
                "fb63a52f3a9945609e64cf10466491cb","3675a3b8683a4428aa45e1357241a977"};
        String[] types = {"1","2","3","4","5","6","7"};
        Double[] dosages = {8d,10d,12d,14d,16d,18d,20d};
        Double[] areas = {15d,10d,8d,16d};
        Double[] coals = {1d,0.9d,0.5d,1.2d};
        BigDecimal[] prices = {new BigDecimal(0.23d),new BigDecimal(0.94d),new BigDecimal(1.8d),new BigDecimal(3d)};
        Double[] wtemps = {-6d,-1d,0d,2d,6d,16d,20d};
        Double[] cwtemps = {0d,1d,2d,6d,8d,16d,18d};
        Double[] ccoefs = {0.223d,0.45d,0.9d,1.7d,2.1d,1d,1.3d};
        Double[] itemps = {22d,24d,26d,28d};
        Double[] citemps = {22d,24d,26d,28d};

        for(int i = 0;i<11680;i=i+7){//时间循环
            String time = getTime(i);
            for(String unitId:units){//用能单位循环
                Double area = areas[random.nextInt(3)];
                for(int j=0;j<7;j++){//能源类型循环
                    EnergyMonitor em = new EnergyMonitor();
                    em.setId(UUIDGenerator.getUUID());
                    em.setComid(coms[random.nextInt(6)]);
                    em.setUnitid(unitId);
                    em.setNodeid("1");
                    em.setDosageTime(time);
                    em.setTypeid(types[j]);
                    em.setArea(area);
                    em.setDosage(dosages[random.nextInt(6)]);
                    em.setCoalCoef(coals[random.nextInt(3)]);
                    em.setPrice(prices[random.nextInt(3)]);
                    em.setWtemp(wtemps[random.nextInt(6)]);
                    em.setCwtemp(cwtemps[random.nextInt(6)]);
                    em.setcCoef(ccoefs[random.nextInt(6)]);
                    em.setItemp(itemps[random.nextInt(3)]);
                    em.setCitemp(citemps[random.nextInt(3)]);
                    energyMonitorService.insertByPrimaryKeySelective(em);
                }
            }


        }

    }

    private static String getTime(int i){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
        Calendar c = Calendar.getInstance();
        c.set(2015,11,1);
        c.set(Calendar.HOUR,i);
        return  format.format(c.getTime());
    }

   // @Test
    public void testTime(){
        for (int i = 0;i<40;i++){
            System.err.println(getTime(i));
        }
    }

    //@Test
    public void testSelectEnergySecond(){
       Double d1 = new Double(46946.8);
       Double d2 = new Double(43377.3);
        Double d3 = (d1-d2)/d2;
       System.err.println(d3);
        DecimalFormat format = new DecimalFormat("#.0000");
        System.err.println(format.format(d3));
        Double d4 = Double.valueOf(format.format(d3));
        System.err.println(d4*100);
    }
}
