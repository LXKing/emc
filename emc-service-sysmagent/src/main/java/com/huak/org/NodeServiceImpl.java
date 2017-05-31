package com.huak.org;

import com.github.pagehelper.PageHelper;
import com.huak.base.dao.DateDao;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.org.dao.NodeDao;
import com.huak.org.dao.OrgDao;
import com.huak.org.model.Node;
import com.huak.org.model.Org;
import com.huak.org.model.vo.NodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by MR-BIN on 2017/5/16.
 */
@Service
public class NodeServiceImpl implements NodeService{

    @Autowired
    private NodeDao nodeDao;

    @Autowired
    private DateDao dateDao;

    @Autowired
    private OrgDao orgDao;

    @Override
    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(String id) {
        if(orgDao.deleteByPrimaryKey(id)>0){
           return  nodeDao.deleteByPrimaryKey(id);
        }
        return 0;
    }

    @Override
    @Transactional(readOnly = false)
    public int insert(Node record) {
        return 0;
    }

    @Override
    @Transactional(readOnly = false)
    public int insertSelective(NodeVo record) {
        Org org = new Org();
        org.setComId(record.getComId());
        org.setpOrgId(record.getpOrgId());
        org.setArea(record.getArea());
        org.setCreateTime(dateDao.getTime());
        org.setCreator(record.getCreator());
        org.setId(record.getId());
        org.setOrgCode(record.getOrgCode());
        org.setMemo(record.getMemo());
        org.setOrgName(record.getOrgName());
        org.setSeq(record.getSeq());
        org.setTypeId(record.getTypeId());
        Node node = new Node();
        node.setAddr(record.getAddr());
        node.setCityId(record.getCityId());
        node.setCountyId(record.getCountyId());
        node.setDwellArea(record.getDwellArea());
        node.setId(record.getId());
        node.setLat(record.getLat());
        node.setLng(record.getLng());
        node.setManageTypeId(record.getManageTypeId());
        node.setProvinceId(record.getProvinceId());
        node.setPublicArea(record.getPublicArea());
        node.setTownId(record.getTownId());
        node.setVillageId(record.getVillageId());
        node.setStatus((byte)0);
        try {
            if(orgDao.insert(org)>0){
                return  nodeDao.insert(node);
            }
        }catch (Exception e){
            return 0;
        }

        return 0;
    }

    private Node getNodeInstance(Object record, Class<?> clazb, Class<?> clazz) {

        Method[] methodb = clazb.getMethods();
        Method[] methoda = clazz.getMethods();

        for (Method method : methodb){
            for (Method method1 :methoda){

                if(method.getName().equals(method1.getName())){
//                    method1.invoke(record.g)
                }
            }
        }
//        for (Method method : methodb) {
//            String methodName = method.getName();
//            // 如果对象的方法以set开头
//            if (methodName.startsWith("set")) {
//                // 根据方法名字得到数据表格中字段的名字
//                String columnName = methodName.substring(3, methodName.length());
//                columnName = tableColumn(columnName).toUpperCase();
//                // 得到方法的参数类型
//                Class[] parmts = method.getParameterTypes();
//                if (parmts[0] == String.class) {
//                    // 如果参数为String类型，则从结果集中按照列名取得对应的值，并且执行改set方法
//                    try{
//                        Object tag = data.get(columnName);
//                        if(tag != null ){
//                            method.invoke(c,data.get(columnName).toString());
//                        }else{
//                            method.invoke(c,"");
//                        }
//
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//
//                }
//
//                if (parmts[0] == int.class || parmts[0] == Integer.class) {
//                    Object tag = data.get(columnName);
//                    if(tag != null ){
//                        method.invoke(c, Integer.parseInt(data.get(columnName).toString()));
//                    }else{
//                        method.invoke(c,0);
//                    }
//                }
//
//                if ( parmts[0] == BigInteger.class) {
//                    Object tag = data.get(columnName);
//                    if(tag != null ){
//                        method.invoke(c, new BigInteger(data.get(columnName).toString()));
//                    }else{
//                        method.invoke(c,0);
//                    }
//                }
//
//
//                if (parmts[0] == Double.class ) {
//                    Object tag = data.get(columnName);
//                    if(tag != null ){
//                        method.invoke(c, Double.parseDouble(tag.toString()));
//                    }else{
//                        method.invoke(c,0.0);
//                    }
//                }
//
//                if (parmts[0] == Float.class ) {
//                    Object tag = data.get(columnName);
//                    if(tag != null ){
//                        method.invoke(c, Float.parseFloat(tag.toString()));
//                    }else{
//                        method.invoke(c,0.0);
//                    }
//                }
//
//                if (parmts[0] == BigDecimal.class ) {
//                    Object tag = data.get(columnName);
//                    if(tag != null ){
//                        BigDecimal bigDecimal = new BigDecimal(tag.toString());
//                        method.invoke(c, bigDecimal.intValue());
//                    }else{
//                        method.invoke(c,0.0);
//                    }
//                }
//
//                if (parmts[0] == Date.class) {
//                    method.invoke(c,data.get(columnName));
//                }
//            }
//
//        }


        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Node selectById(String id) {
        return null;
    }

    @Override
    @Transactional(readOnly = false)
    public int update(NodeVo record) {
        Org org = new Org();
        org.setId(record.getId());
        org.setComId(record.getComId());
        org.setpOrgId(record.getpOrgId());
        org.setArea(record.getArea());
        org.setCreateTime(dateDao.getTime());
        org.setCreator(record.getCreator());
        org.setId(record.getId());
        org.setOrgCode(record.getOrgCode());
        org.setMemo(record.getMemo());
        org.setOrgName(record.getOrgName());
        org.setSeq(record.getSeq());
        org.setTypeId(record.getTypeId());
        org.setShortName(record.getShortName());
        Node node = new Node();
        node.setId(record.getId());
        node.setAddr(record.getAddr());
        node.setCityId(record.getCityId());
        node.setCountyId(record.getCountyId());
        node.setDwellArea(record.getDwellArea());
        node.setId(record.getId());
        node.setLat(record.getLat());
        node.setLng(record.getLng());
        node.setManageTypeId(record.getManageTypeId());
        node.setProvinceId(record.getProvinceId());
        node.setPublicArea(record.getPublicArea());
        node.setTownId(record.getTownId());
        node.setVillageId(record.getVillageId());
        node.setStatus(record.getStatus());
        if(orgDao.updateByPrimaryKey(org)>0){
            return  nodeDao.updateByPrimaryKey(node);
        }
        return 0;
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKey(Node record) {
        return 0;
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult<NodeVo> queryByPage(Map<String, Object> paramsMap, Page page) {
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        List<NodeVo> nodes = new ArrayList<>();
        try {
            nodes = nodeDao.selectPageByMap(paramsMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Convert.convert(nodes);
    }

    @Override
    @Transactional(readOnly = true)
    public NodeVo selectVoById(String id) {
        NodeVo node = nodeDao.selectVoById(id);
        return node;
    }

    @Override
    public List<Map<String, Object>> exportExcel(Map<String, Object> paramsMap) {
        return nodeDao.export(paramsMap);
    }
}
