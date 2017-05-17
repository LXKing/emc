package com.huak.org;

import com.github.pagehelper.PageHelper;
import com.huak.auth.model.Role;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.org.dao.NodeDao;
import com.huak.org.model.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by MR-BIN on 2017/5/16.
 */
@Service
public class NodeServiceImpl implements NodeService{

    @Autowired
    private NodeDao nodeDao;

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(Node record) {
        return 0;
    }

    @Override
    public int insertSelective(Node record) {
        return 0;
    }

    @Override
    public Node selectById(String id) {
        return null;
    }

    @Override
    public int update(Node record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Node record) {
        return 0;
    }

    @Override
    public PageResult<Node> queryByPage(Map<String, Object> paramsMap, Page page) {
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        return Convert.convert(nodeDao.selectPageByMap(paramsMap));
    }

    @Override
    public List<Map<String, Object>> exportExcel(Map<String, Object> paramsMap) {
        return nodeDao.export(paramsMap);
    }
}
