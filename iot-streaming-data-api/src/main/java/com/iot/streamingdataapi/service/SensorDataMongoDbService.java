package com.iot.streamingdataapi.service;

import com.iot.streamingdataapi.model.SensorAvgResponse;
import com.iot.streamingdataapi.model.SensorMaxResponse;
import com.iot.streamingdataapi.model.SensorMedianResponse;
import com.iot.streamingdataapi.model.SensorMinResponse;

import java.util.List;

public interface SensorDataMongoDbService {

    List<SensorMinResponse> getMinValue(List<String> sensorNames, String startDate, String endDate);

    List<SensorMaxResponse> getMaxValue(List<String> sensorNames, String startDate, String endDate);

    List<SensorAvgResponse> getAvgValue(List<String> sensorNames, String startDate, String endDate);

    List<SensorMedianResponse> getMedianValue(List<String> sensorNames, String startDate, String endDate);
}
