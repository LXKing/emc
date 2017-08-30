package com.huak.mdc;

import com.huak.common.Constants;
import com.huak.common.utils.ColumUtil;
import com.huak.common.utils.MultipartFileParam;
import com.huak.common.utils.MultipartFileUploadUtil;
import com.huak.mdc.vo.MeterCollectA;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.PageHelper;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.mdc.dao.MeterCollectDao;
import com.huak.mdc.model.MeterCollect;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.mdc<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/28<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class MeterCollectServiceImpl implements MeterCollectService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static AtomicLong counter = new AtomicLong(0L);

    @Override
    public String excelUpload(HttpServletRequest request) {
        String prefix = "req_count:" + counter.incrementAndGet() + ":";
        System.out.println(prefix + "start !!!");
        MultipartFileParam param = null;
        try {
            param = MultipartFileUploadUtil.parse(request);
            if (param.isMultipart()) {
                File file = new File(param.getFileName());
                XSSFSheet hssFSheet = null;
                XSSFWorkbook hssFWorkBook = null;
                XSSFRow xssfRow = null;
                MeterCollect meterCollect = null;
                List<MeterCollect> list = new ArrayList<>();
                try {
                    hssFWorkBook = new XSSFWorkbook(new FileInputStream(file));
                    for(int i = 0; i<= hssFWorkBook.getNumberOfSheets();i++){
                        hssFSheet = hssFWorkBook.getSheetAt(i);
                        for(int k = 1; k<=hssFSheet.getPhysicalNumberOfRows();k++){
                            xssfRow = hssFSheet.getRow(k);
                            meterCollect = (MeterCollect) digitData(xssfRow,MeterCollect.class);
                            list.add(meterCollect);
                        }
                    }
                    return "1";
                } catch (Exception e) {
                    logger.info("后台-计量器具导入出错"+ e);
                    return  "2";
                }
            }
        } catch (Exception e) {
            logger.info("后台-计量器具导入出错"+ e);
           return  "2";
        }
        return "";
    }
    private Object digitData(XSSFRow xssfRow , Class<?> classz) throws IllegalAccessException, InstantiationException, InvocationTargetException {
       Object obj = classz.newInstance();
        Method[] methods = classz.getMethods();
        Field[] fields = classz.getClass().getDeclaredFields();
        Map<String,Object> data = new HashMap<>();
        List<String> cells = new ArrayList<>();
        for (Field field : fields){
            if(!Constants.CELL_NAME.containsKey(ColumUtil.getColumn(field.getName()))){
                Constants.CELL_NAME.put( ColumUtil.getColumn(field.getName()),field.getName());
            }
        }
        Iterator iter = Constants.CELL_NAME.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key =  entry.getKey().toString();
            cells.add(key);
        }
        for(int i = 0; i < cells.size();i++){
            String tag = (null ==  xssfRow.getCell(i))?"":xssfRow.getCell(i).toString();
            if(tag.endsWith(".0")){
                tag =  tag.substring(0,tag.length() - 2);
            }
            String cellname = cells.get(i);
            for (Method method : methods) {
                    String methodName = method.getName();
                    // 如果对象的方法以set开头
                    if (methodName.startsWith("set")) {
                        // 根据方法名字得到数据表格中字段的名字
                        String columnName = methodName.substring(3, methodName.length());
                        columnName = ColumUtil.getColumn(columnName);
                    if(cellname.equals(columnName)){
                        // 得到方法的参数类型
                        Class[] parmts = method.getParameterTypes();
                        if (parmts[0] == String.class) {
                            if(tag != null ){
                                method.invoke(obj,tag.toString());
                            }else{
                                method.invoke(obj,"");
                            }
                        }
                        if (parmts[0] == int.class || parmts[0] == Integer.class) {
                            if(tag != null ){
                                method.invoke(obj, Integer.parseInt(data.get(columnName).toString()));
                            }else{
                                method.invoke(obj,0);
                            }
                        }
                        if ( parmts[0] == BigInteger.class) {
                            if(tag != null ){
                                method.invoke(obj, new BigInteger(data.get(columnName).toString()));
                            }else{
                                method.invoke(obj,0);
                            }
                        }
                        if (parmts[0] == Double.class ) {
                            if(tag != null ){
                                method.invoke(obj, Double.parseDouble(tag.toString()));
                            }else{
                                method.invoke(obj,0.0);
                            }
                        }
                        if (parmts[0] == Float.class ) {
                            if(tag != null ){
                                method.invoke(obj, Float.parseFloat(tag.toString()));
                            }else{
                                method.invoke(obj,0.0);
                            }
                        }
                        if (parmts[0] == BigDecimal.class ) {
                            if(tag != null ){
                                BigDecimal bigDecimal = new BigDecimal(tag.toString());
                                method.invoke(obj, bigDecimal.intValue());
                            }else{
                                method.invoke(obj,0.0);
                            }
                        }
                        if (parmts[0] == Date.class) {
                            method.invoke(obj,tag);
                        }

                    }
                }
            }
        }
        return obj;
    }




    @Resource
    MeterCollectDao meterCollectDao;
    @Override
    public int deleteByPrimaryKey(String id) {
        return meterCollectDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(MeterCollect record) {
        return meterCollectDao.insertSelective(record);
    }

    @Override
    public int insert(MeterCollect record) {
        return meterCollectDao.insert(record);
    }

    @Override
    public MeterCollect selectByPrimaryKey(String id) {
        return meterCollectDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(MeterCollect record) {
        return meterCollectDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageResult<MeterCollectA> queryByPage(Map<String, Object> paramsMap, Page page) {
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        return Convert.convert(meterCollectDao.selectPageByMap(paramsMap));
    }

    @Override
    public List<Map<String, Object>> exportExcel(Map<String, Object> paramsMap) throws IOException {
        return  meterCollectDao.selectAllByMap(paramsMap);
    }

    @Override
    public boolean checkName(Map<String, Object> paramsMap) {
        boolean flag=false;
        List<MeterCollect> list =  meterCollectDao.checkName(paramsMap);
        if(list.size()>0){
            flag=true;
        }else {
            flag=false;
        }
        return flag;
    }

    @Override
    public boolean checkCode(Map<String, Object> paramsMap) {
        boolean flag=false;
        List<MeterCollect> list =  meterCollectDao.checkCode(paramsMap);
        if(list.size()>0){
            flag=true;
        }else {
            flag=false;
        }
        return flag;
    }

    @Override
    public boolean checkNo(String serialNo) {
        boolean flag=false;
        List<MeterCollect> list =  meterCollectDao.checkSerialNo(serialNo);
        if(list.size()>0){
            flag=true;
        }else {
            flag=false;
        }
        return flag;
    }

    @Override
    public List<Map<String, Object>> getUnitInfo(String unitType) {
        return meterCollectDao.getUnitInfo(unitType);
    }
}
