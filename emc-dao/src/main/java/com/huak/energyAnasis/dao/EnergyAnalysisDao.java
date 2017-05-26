package com.huak.energyAnasis.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface EnergyAnalysisDao {

	List<Map<String, Object>> groupEnergy2curyear(Map<String, String> params);

	List<Map<String, Object>> groupEnergy2lastyear(Map<String, String> params);

}
