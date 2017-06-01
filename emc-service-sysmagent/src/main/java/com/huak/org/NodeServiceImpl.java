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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private NodeDao nodeDao;

    @Autowired
    private DateDao dateDao;

    @Override
    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(String id) {
           return  nodeDao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int insert(Node record) {
        return 0;
    }

    @Override
    @Transactional(readOnly = false)
    public int insertSelective(Node record) {
        try {
                return  nodeDao.insert(record);
        }catch (Exception e){
            logger.error("新增热力站失败！");
        }
        return 0;
    }



    @Override
    @Transactional(readOnly = true)
    public Node selectById(String id) {
        return null;
    }

    @Override
    @Transactional(readOnly = false)
    public int update(Node record) {

        try {
            return nodeDao.updateByPrimaryKey(record);
        }catch (Exception e){logger.error("修改热力站失败！");}
        return 0;
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKey(Node record) {
        return 0;
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult<Node> queryByPage(Map<String, Object> paramsMap, Page page) {
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        List<Node> nodes = new ArrayList<>();
        try {
            nodes = nodeDao.selectPageByMap(paramsMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Convert.convert(nodes);
    }


    @Override
    public List<Map<String, Object>> exportExcel(Map<String, Object> paramsMap) {
        return nodeDao.export(paramsMap);
    }

    @Override
    public List<Map<String, Object>> selectStationByMap(Map<String, Object> paramsMap) {
        return nodeDao.selectStationByMap(paramsMap);
    }
}
