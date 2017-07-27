package com.huak.task.dao;

import com.huak.task.model.EnergyAnalySisdata;

import java.util.List;

public interface EnergyAnalySisdataDao {
    int deleteByPrimaryKey(String id);

    int insert(EnergyAnalySisdata record);

    int insertSelective(EnergyAnalySisdata record);

    EnergyAnalySisdata selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EnergyAnalySisdata record);

    int updateByPrimaryKey(EnergyAnalySisdata record);

    List<EnergyAnalySisdata> selectEnergyAnalyByUnitid(String id);

    void selectFinalDataHourById(String id);

    void selectPowerById(String id);

    void selectHeatById(String id);

    void selectQiById(String id);
}