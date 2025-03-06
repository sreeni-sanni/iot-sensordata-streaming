package com.iot.streamingdataapi.service;

import com.iot.streamingdataapi.model.SensorAvgResponse;
import com.iot.streamingdataapi.model.SensorMaxResponse;
import com.iot.streamingdataapi.model.SensorMedianResponse;
import com.iot.streamingdataapi.model.SensorMinResponse;

import java.util.List;

public interface SensorDataService {

    List<SensorMinResponse> sensorDataMinValue(List<String> sensorNames, String startDate, String endDate);
    List<SensorMaxResponse> sensorDataMaxValue(List<String> sensorNames, String startDate, String endDate);
    List<SensorAvgResponse> sensorDataAvgValue(List<String> sensorNames, String startDate, String endDate);
    List<SensorMedianResponse> sensorDataMedianValue(List<String> sensorNames, String startDate, String endDate);

}
