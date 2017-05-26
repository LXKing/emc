package com.huak.energy;

import java.util.Map;

public interface EnergyMonitorService {

	Map<String, Object> groupEnergy();

	Map<String, Object> groupEnergy2Day(Map<String, String> params);

}
