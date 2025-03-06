package com.iot.streamingdataapi.service.impl;

import com.iot.streamingdataapi.model.SensorAvgResponse;
import com.iot.streamingdataapi.model.SensorMaxResponse;
import com.iot.streamingdataapi.model.SensorMedianResponse;
import com.iot.streamingdataapi.model.SensorMinResponse;
import com.iot.streamingdataapi.service.SensorDataMongoDbService;
import com.iot.streamingdataapi.service.SensorDataService;
import com.iot.streamingdataapi.utils.DateValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class SensorDataServiceImpl implements SensorDataService {

    private SensorDataMongoDbService mongoDbService;

    @Override
    public List<SensorMinResponse> sensorDataMinValue(List<String> sensorNames, String startDate, String endDate) {
        DateValidator.isValidTimeFrame(startDate,endDate);
        return mongoDbService.getMinValue(sensorNames, startDate, endDate);
    }

    @Override
    public List<SensorMaxResponse> sensorDataMaxValue(List<String> sensorNames, String startDate, String endDate) {
        DateValidator.isValidTimeFrame(startDate,endDate);
        return mongoDbService.getMaxValue(sensorNames, startDate, endDate);
    }

    @Override
    public List<SensorAvgResponse> sensorDataAvgValue(List<String> sensorNames, String startDate, String endDate) {
        DateValidator.isValidTimeFrame(startDate,endDate);
        return mongoDbService.getAvgValue(sensorNames, startDate, endDate);
    }

    @Override
    public List<SensorMedianResponse> sensorDataMedianValue(List<String> sensorNames, String startDate, String endDate) {
        DateValidator.isValidTimeFrame(startDate,endDate);
        return mongoDbService.getMedianValue(sensorNames, startDate, endDate);
    }
}
