package com.huak.org.dao;


import org.springframework.stereotype.Repository;
import com.huak.org.model.vo.CostVo;
import java.util.Map;

@Repository
public interface TopAllDao {

    public String selectTopEtotalByMap(Map<String, String> paramsMap);

    public String selectCarbonTotalByMap(Map<String, String> paramsMap);

    public CostVo selectCostTotalByMap(Map<String, String> paramsMap);

    public String selectYardageByMap(Map<String, String> paramsMap);

    public String selectPriceAreaByMap(Map<String, String> paramsMap);

    public String selectTopFeedTotalByMap(Map<String, String> paramsMap);

    public String selectTopFeedCarbonTotalByMap(Map<String, String> paramsMap);

    public CostVo selectFeedCostTotalByMap(Map<String, String> paramsMap);

}