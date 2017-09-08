package com.huak.mdc;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.huak.base.dao.DateDao;
import com.huak.common.Constants;
import com.huak.common.UUIDGenerator;
import com.huak.common.utils.ColumUtil;
import com.huak.mdc.model.EnergyDataHis;
import com.huak.org.dao.CompanyDao;
import com.huak.org.model.Company;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    @Resource
    MeterCollectDao meterCollectDao;
    @Resource
    private EnergyDataHisService energyDataHisService;
    @Resource
    private CompanyDao companyDao;
    @Resource
    DateDao dateDao;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static AtomicLong counter = new AtomicLong(0L);

    /**
     * 上传入库
     *
     * @param path
     * @param s
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public Map<String, Object> excelUpload(String path, String s) throws IOException {
        List<Map<String,Object>> tempdata = meterCollectDao.selectByMaps(new HashMap<String, Object>());
        System.out.println("-------------------------path:"+path+"-------------------------------");
        String prefix = "req_count:" + counter.incrementAndGet() + ":";
        System.out.println(prefix + "start !!!");
        FileInputStream io = null;
        Map<String,Object> result = new HashMap<>();
        result.put(Constants.FLAG,"1");
        StringBuffer message = new StringBuffer();
        try {
                XSSFSheet hssFSheet = null;
                XSSFWorkbook hssFWorkBook = null;
                XSSFRow xssfRow = null;
                MeterCollect meterCollect = null;
                List<MeterCollect> list = new ArrayList<>();
                io = new FileInputStream(path);
                hssFWorkBook = new XSSFWorkbook(io);
                for(int i = 0; i< hssFWorkBook.getNumberOfSheets();i++){
                    hssFSheet = hssFWorkBook.getSheetAt(i);
                    for(int k = 1; k<hssFSheet.getPhysicalNumberOfRows();k++){
                        xssfRow = hssFSheet.getRow(k);
                        try {
                            meterCollect = (MeterCollect) digitData(xssfRow,MeterCollect.class);
                            list.add(meterCollect);
                        }catch (Exception e){
                            message.append("第"+(k)+"行数据有问题：新增失败");
                            message.append(",");
                            result.put("flag","2");
                            break;
                        }

                    }
                }
                for (int m = 0; m<list.size();m++){
                    MeterCollect data = list.get(m);
                    if(null != data.getIsauto() ){
                        if(data.getIsauto().equals("自动采集")){
                            data.setIsauto((byte)0);
                        }
                        if(data.getIsauto().equals("手工")){
                            data.setIsauto((byte)1);
                        }
                    }
                    if(null != data.getIsprestore() ){
                        if(data.getIsprestore().equals("不是")){
                            data.setIsprestore((byte)0);
                        }
                        if(data.getIsprestore().equals("是")){
                            data.setIsprestore((byte)1);
                        }
                    }
                    if(null != data.getIsreal() ){
                        if(data.getIsreal().equals("否")){
                            data.setIsreal((byte)0);
                        }
                        if(data.getIsreal().equals("单位总表")){
                            data.setIsreal((byte)1);
                        }
                        if(data.getIsreal().equals("系统总表")){
                            data.setIsreal((byte)2);
                        }
                    }
                    if(null != data.getIsdelete() ){
                        if(data.getIsdelete().equals("软删除标识")){
                            data.setIsdelete((byte)0);
                        }
                        if(data.getIsdelete().equals("未删除")){
                            data.setIsdelete((byte)1);
                        }
                    }
                    if(null != data.getUnitType() ){
                        if(data.getUnitType().equals("热源")){
                            data.setUnitType((byte)1);
                        }
                        if(data.getUnitType().equals("一次网")){
                            data.setUnitType((byte)2);
                        }
                        if(data.getUnitType().equals("换热站")){
                            data.setUnitType((byte)3);
                        }
                        if(data.getUnitType().equals("二次线")){
                            data.setUnitType((byte)4);
                        }
                        if(data.getUnitType().equals("用户")){
                            data.setUnitType((byte)5);
                        }

                    }

                    boolean index = false;
                    String codeFlag = data.getCode()+"-"+data.getComId();
                    for (Map f :tempdata){
                        if(f.containsValue(codeFlag)){
                            index = true;
                            break;
                        }
                    }
                    if(!index){
                        data.setId(UUIDGenerator.getUUID());
                        try{
                            meterCollectDao.insert(data);
                            Map<String,Object> meter = new HashMap<>();
                            meter.put("code",codeFlag);
                            tempdata.add(meter);

                        }catch (Exception e){
                            message.append("第"+(m+1)+"行数据有问题：新增失败");
                            message.append(",");
                            result.put("flag","2");
                        }
                    }else{
                        try{
                            meterCollectDao.updateByPrimaryKey(data);
                        }catch (Exception e){
                            message.append("第"+(m+1)+"行数据有问题：更新失败");
                            message.append(",");
                            result.put("flag","2");
                        }

                    }
                }

                io.close();
        } catch (Exception e) {
            result.put("flag","2");
            logger.info("后台-计量器具导入出错"+ e);
        }finally {
            if(null != io){
                io.close();
            }
        }
        result.put("message",message);
         System.out.println(message);
        return  result;
    }


    /**
     * 后台-上传入库-数据解析
     * @param xssfRow
     * @param classz
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     */
    private Object digitData(XSSFRow xssfRow , Class<?> classz) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        Object obj = null;
           obj =Class.forName(classz.getName()).newInstance();
           Method[] methods = classz.getMethods();
           Field[] fields = classz.getClass().getDeclaredFields();
           Map<String,Object> data = new HashMap<>();
           List<String> cells = new ArrayList<>();
           /*自动化匹配*/
//           for (Field field : fields){
//               if(!Constants.CELL_NAME.containsKey(ColumUtil.getColumn(field.getName()))){
//                   Constants.CELL_NAME.put( ColumUtil.getColumn(field.getName()),field.getName());
//               }
//           }
           Iterator iter = Constants.CELL_NAME.entrySet().iterator();
           while (iter.hasNext()) {
               Map.Entry entry = (Map.Entry) iter.next();
               String key =  entry.getKey().toString();
               cells.add(key);
           }
           for(int i = 0; i < cells.size();i++){
               Object tag = getValue(xssfRow.getCell(i));

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
                               if(tag.toString().endsWith(".0")){
                                   tag =  tag.toString().substring(0, tag.toString().length() - 2);
                               }
                               if(tag != null ){
                                   method.invoke(obj,tag.toString());
                               }else{
                                   method.invoke(obj,"");
                               }
                           }
                           if (parmts[0] == byte.class || parmts[0] == Byte.class) {
                               System.out.println(columnName+"----type:"+parmts[0]+"-----------value:"+tag);
                               if(tag.toString().endsWith(".0")){
                                   tag =  new Byte(tag.toString().substring(0, tag.toString().length() - 2));
                               }
                               if(tag != null ){
                                   method.invoke(obj, (byte)tag);
                               }else{
                                   method.invoke(obj,0);
                               }
                           }
                           if (parmts[0] == int.class || parmts[0] == Integer.class) {
                               if(tag != null ){
                                   method.invoke(obj, Integer.parseInt(tag.toString()));
                               }else{
                                   method.invoke(obj,0);
                               }
                           }
                           if ( parmts[0] == BigInteger.class) {
                               if(tag != null ){
                                   method.invoke(obj, new BigInteger(tag.toString()));
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


    private Object getValue(XSSFCell hssfCell){
        if(hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN){
            return String.valueOf( hssfCell.getBooleanCellValue());
        }else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC){
            return String.valueOf( hssfCell.getNumericCellValue());
        }else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_STRING){
            return String.valueOf( hssfCell.getStringCellValue());
        }else{
            return String.valueOf( hssfCell.getStringCellValue());
        }

    }


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
    public List<Map<String, Object>> getUnitInfo(Map<String, Object> params) {
        return meterCollectDao.getUnitInfo(params);
    }

    /**
     * 填报数据查询
     * @param params
     * @return
     */
    @Override
    public List<Map<String, Object>> getMeterDatas(Map<String, Object> params) {
        String time = dateDao.getTime().substring(0,13);

        if(!params.containsKey("collectTime")){
            params.put("collectTime",time);
        }else{
            if(StringUtils.isBlank(params.get("collectTime").toString())){
                params.put("collectTime",time);
            }else{
                params.put("collectTime",params.get("collectTime").toString().substring(0,13));
            }
        }
        return meterCollectDao.getMeterDatas(params);
    }

    /**
     * 安全与后台-数据填报
     * @param jo
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public boolean fillData(JSONObject jo) {
        List<EnergyDataHis> datalist0 = new ArrayList<>();//实表
        List<String> ids = new ArrayList<>();//虚表
        List<Map<String,Object>> datas = (List<Map<String, Object>>) jo.get("data");
        String comId = (String) jo.get("comId");
        Company company = companyDao.selectByPrimaryKey(comId);
        String collectTime = "";
        for(Map data:datas){
            String flag = data.get("realFlag").toString();
           if(flag.equals("0")){
               if(StringUtils.isBlank(collectTime)){
                   collectTime = data.get("collectTime").toString()+":00:00";
               }
               EnergyDataHis energy0 = new EnergyDataHis();
               energy0.setCollectId(data.get("id").toString());
               energy0.setCollectTime(collectTime);
               energy0.setIschange((byte) 0);
               energy0.setIsprestore((byte) 0);
               energy0.setCollectNum(Double.parseDouble(data.get("num").toString()));
               datalist0.add(energy0);
           }
            if(flag.equals("1")){
                ids.add(data.get("id").toString());
            }
        }
        try {
            List<MeterCollect> meterCollects = meterCollectDao.selectFictitiousMeters(ids);
            if(energyDataHisService.saveEnergyDatas(datalist0,company)){
                energyDataHisService.saveVirtualDatas(meterCollects,collectTime,company);
            };
            return true;
        }catch (Exception e){
            logger.error("安全与后台-数据填报异常！"+e);
        }
        return false;
    }


}
