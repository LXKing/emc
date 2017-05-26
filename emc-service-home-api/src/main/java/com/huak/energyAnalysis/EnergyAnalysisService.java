package com.huak.energyAnalysis;

import java.util.Map;

public interface EnergyAnalysisService {

	Map<String, Object> groupEnergy();

	Map<String, Object> groupEnergy2Day(Map<String, String> params);

}
