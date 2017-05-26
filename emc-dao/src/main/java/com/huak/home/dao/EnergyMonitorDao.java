package com.huak.home.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EnergyMonitorDao {

	List<Map<String, Object>> groupEnergy2curyear(Map<String, String> params);

	List<Map<String, Object>> groupEnergy2lastyear(Map<String, String> params);

}
