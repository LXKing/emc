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
    //@Test
    public void createData(){
        Random random = new Random();
        String[] coms = {"74ee3b6752254435b724b6672f9fde8d","74ee3b6752254435b724b6672f9fde8d",
                "74ee3b6752254435b724b6672f9fde8d","74ee3b6752254435b724b6672f9fde8d",
                "74ee3b6752254435b724b6672f9fde8d","cb5aed0798f24caf8637902163ecf8a3"};
        String[] units = {"03e96330ed774ea1a1971e367f2123c3","22c690e8488c4b21b517172e71cbb8d0",
                "dae08419bf1c406f843c23bc683d7dd7","1254ef0923704e2980991cf847064a40",
                "4d005c40767e4cf9a12724412a5d7faa","a9db06b820bf41e19adc2d08dacf17c1",
                "d7f39faad8f44fd8bc4b21490c33ef85"};
        String[] types = {"1","2",
                "3","4",
                "5","6",
                "7"};
        Double[] dosages = {8d,10d,12d,14d,16d,18d,20d};
        Double[] areas = {2d,4d,8d,16d};
        Double[] coals = {1d,0.9d,0.5d,1.2d};
        BigDecimal[] prices = {new BigDecimal(0.23d),new BigDecimal(0.94d),new BigDecimal(1.8d),new BigDecimal(3d)};
        Double[] wtemps = {-6d,-1d,0d,2d,6d,16d,20d};
        Double[] cwtemps = {0d,1d,2d,6d,8d,16d,18d};
        Double[] ccoefs = {0.223d,0.45d,0.9d,1.7d,2.1d,1d,1.3d};
        Double[] itemps = {22d,24d,26d,28d};
        Double[] citemps = {22d,24d,26d,28d};

        for(int i = 0;i<500;i++){
            EnergyMonitor em = new EnergyMonitor();
            em.setId(UUIDGenerator.getUUID());
            em.setComid(coms[random.nextInt(6)]);
            em.setUnitid(units[random.nextInt(6)]);
            em.setNodeid("1");
            em.setDosageTime(getTime(i));
            em.setTypeid(types[random.nextInt(6)]);
            em.setDosage(dosages[random.nextInt(6)]);
            em.setArea(areas[random.nextInt(3)]);
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

    private static String getTime(int i){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
        Calendar c = Calendar.getInstance();
        c.set(2017,5,1);
        c.set(Calendar.HOUR,i);
        return  format.format(c.getTime());
    }

   // @Test
    public void testTime(){
        for (int i = 0;i<40;i++){
            System.err.println(getTime(i));
        }
    }

    @Test
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
