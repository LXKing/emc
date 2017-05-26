package com.huak.org;

import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.org.model.Node;
import com.huak.org.model.vo.NodeVo;

import java.util.List;
import java.util.Map;

/**
 * Created by MR-BIN on 2017/5/16.
 */
public interface NodeService {
    int deleteByPrimaryKey(String id);

    int insert(Node record);

    public int insertSelective(NodeVo record);

    Node selectById(String id);

    int update(NodeVo record);

    int updateByPrimaryKey(Node record);

    public PageResult<NodeVo> queryByPage(Map<String, Object> paramsMap, Page page);

    List<Map<String,Object>> exportExcel(Map<String, Object> paramsMap);

    NodeVo selectVoById(String id);


}
