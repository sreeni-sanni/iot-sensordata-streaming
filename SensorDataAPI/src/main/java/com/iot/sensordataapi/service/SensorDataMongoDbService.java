package com.iot.sensordataapi.service;

import com.iot.sensordataapi.model.SensorAvgResponse;
import com.iot.sensordataapi.model.SensorMaxResponse;
import com.iot.sensordataapi.model.SensorMedianResponse;
import com.iot.sensordataapi.model.SensorMinResponse;

import java.util.List;

public interface SensorDataMongoDbService {

    List<SensorMinResponse> getMinValue(List<String> sensorNames, String startDate, String endDate);

    List<SensorMaxResponse> getMaxValue(List<String> sensorNames, String startDate, String endDate);

    List<SensorAvgResponse> getAvgValue(List<String> sensorNames, String startDate, String endDate);

    List<SensorMedianResponse> getMedianValue(List<String> sensorNames, String startDate, String endDate);
}
