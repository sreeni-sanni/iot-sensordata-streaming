package com.iot.sensordataapi.service.impl;

import com.iot.sensordataapi.model.*;
import com.iot.sensordataapi.service.SensorDataMongoDbService;
import com.iot.sensordataapi.repository.SensorDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.iot.sensordataapi.constants.Constants.*;

@AllArgsConstructor
@Service
public class SensorDataMongoDbServiceImpl implements SensorDataMongoDbService {

    private MongoTemplate MongoTemplate;
    private SensorDataRepository sensorDataRepository;

    public List<SensorMinResponse> getMinValue(List<String> sensorNames, String startDate, String endDate) {
        GroupOperation group = Aggregation.group(SENSOR_NAME).min(METRIC_VALUE).as(VALUE);
        ProjectionOperation project = Aggregation.project().and(VALUE).as(MINVALUE).and(ID).as(SENSOR_NAME).andExclude(ID);
        AggregationResults<SensorMinResponse> results = MongoTemplate.aggregate(aggregation(sensorNames, startDate, endDate, group, project), SENSORDATA, SensorMinResponse.class);
        return new ArrayList<>(results.getMappedResults());
    }

    public List<SensorMaxResponse> getMaxValue(List<String> sensorNames, String startDate, String endDate) {
        GroupOperation group = Aggregation.group(SENSOR_NAME).max(METRIC_VALUE).as(VALUE);
        ProjectionOperation project = Aggregation.project().and(VALUE).as(MIXVALUE).and(ID).as(SENSOR_NAME).andExclude(ID);
        AggregationResults<SensorMaxResponse> results = MongoTemplate.aggregate(aggregation(sensorNames, startDate, endDate, group, project), SENSORDATA, SensorMaxResponse.class);
        return new ArrayList<>(results.getMappedResults());
    }

    public List<SensorAvgResponse> getAvgValue(List<String> sensorNames, String startDate, String endDate) {
        GroupOperation group = Aggregation.group(SENSOR_NAME).avg(METRIC_VALUE).as(VALUE);
        ProjectionOperation project = Aggregation.project().and(VALUE).as(AVGVALUE).and(ID).as(SENSOR_NAME).andExclude(ID);
        AggregationResults<SensorAvgResponse> results = MongoTemplate.aggregate(aggregation(sensorNames, startDate, endDate, group, project), SENSORDATA, SensorAvgResponse.class);
        return new ArrayList<>(results.getMappedResults());
    }

    public List<SensorMedianResponse> getMedianValue(List<String> sensorNames, String startDate, String endDate) {
        List<SensorMedianResponse> responses = new ArrayList<>();
        List<SensorData> sensorDataList = sensorDataRepository.findBySensorNameInAndTimeStampBetween(sensorNames, startDate, endDate);
        Map<String, List<Double>> groupData = sensorDataList.stream().collect(Collectors.groupingBy(SensorData::getSensorName, Collectors.mapping(SensorData::getMetricValue, Collectors.toList())));
        groupData.forEach((k, v) -> {
            List<Double> metricValuesList = sensorDataList.stream().map(SensorData::getMetricValue).sorted().toList();
            double medianValue;
            if (metricValuesList.size() % 2 == 0) {
                medianValue = (metricValuesList.get(metricValuesList.size() / 2 - 1) + metricValuesList.get(metricValuesList.size() / 2)) / 2;
            } else {
                medianValue = metricValuesList.get(metricValuesList.size() / 2);
            }
            SensorMedianResponse res = new SensorMedianResponse(k, medianValue);
            responses.add(res);
        });
        return responses;
    }

    private Aggregation aggregation(List<String> sensorNames, String startDate, String endDate, GroupOperation group, ProjectionOperation project) {
        return Aggregation.newAggregation(matchOperation(sensorNames, startDate, endDate), group, project);
    }

    private MatchOperation matchOperation(List<String> sensorNames, String startDate, String endDate) {
        return Aggregation.match(Criteria.where(SENSOR_NAME).in(sensorNames).and(TIMESTAMP).gte(startDate).lte(endDate));
    }

}
