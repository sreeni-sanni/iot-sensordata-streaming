package com.iot.sensordataapi.service;

import com.iot.sensordataapi.model.SensorAvgResponse;
import com.iot.sensordataapi.model.SensorMaxResponse;
import com.iot.sensordataapi.model.SensorMedianResponse;
import com.iot.sensordataapi.model.SensorMinResponse;

import java.util.List;

public interface SensorDataService {

    List<SensorMinResponse> sensorDataMinValue(List<String> sensorNames, String startDate, String endDate);
    List<SensorMaxResponse> sensorDataMaxValue(List<String> sensorNames, String startDate, String endDate);
    List<SensorAvgResponse> sensorDataAvgValue(List<String> sensorNames, String startDate, String endDate);
    List<SensorMedianResponse> sensorDataMedianValue(List<String> sensorNames, String startDate, String endDate);

}
