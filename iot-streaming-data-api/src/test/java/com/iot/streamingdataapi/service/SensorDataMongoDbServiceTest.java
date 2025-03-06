package com.iot.streamingdataapi.service;

import com.iot.streamingdataapi.model.*;
import com.iot.streamingdataapi.repository.SensorDataRepository;
import com.iot.streamingdataapi.service.impl.SensorDataMongoDbServiceImpl;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SensorDataMongoDbServiceTest {

    @InjectMocks
    SensorDataMongoDbServiceImpl sensorDataMongoDbServiceImpl;

    @Mock
    MongoTemplate mongoTemplate;

    @Mock
    SensorDataRepository sensorDataRepository;

    @Mock
    Document rawResults;

    @Test
    public void testGetMinValue(){
        List<String> sensorNames= Arrays.asList("FUEL_READING","THERMOSTAT");
        String startDate="2025-03-03T18:45:51.611";
        String endDate="2025-03-03T18:46:10.616";

        SensorMinResponse res=new SensorMinResponse("FUEL_READING",10.5);
        SensorMinResponse res1=new SensorMinResponse("THERMOSTAT",12.5);
        List<SensorMinResponse> sensorMinResponses=new ArrayList<>();
        sensorMinResponses.add(res);
        sensorMinResponses.add(res1);
        AggregationResults<SensorMinResponse> mockAggResult=new AggregationResults(sensorMinResponses,rawResults);
        when(mongoTemplate.aggregate(any(Aggregation.class),eq("sensorData"),eq(SensorMinResponse.class))).thenReturn(mockAggResult);
        List<SensorMinResponse> result=sensorDataMongoDbServiceImpl.getMinValue(sensorNames,startDate,endDate);
        assertEquals(result.size(), 2);
    }
    @Test
    public void testGetMaxValue(){
        List<String> sensorNames= Arrays.asList("FUEL_READING","THERMOSTAT");
        String startDate="2025-03-03T18:45:51.611";
        String endDate="2025-03-03T18:46:10.616";

        SensorMaxResponse res=new SensorMaxResponse("FUEL_READING",10.5);
        SensorMaxResponse res1=new SensorMaxResponse("THERMOSTAT",12.5);
        List<SensorMaxResponse> sensorMaxResponse=new ArrayList<>();
        sensorMaxResponse.add(res);
        sensorMaxResponse.add(res1);

        AggregationResults<SensorMaxResponse> mockAggResult=new AggregationResults(sensorMaxResponse,rawResults);
        when(mongoTemplate.aggregate(any(Aggregation.class),eq("sensorData"),eq(SensorMaxResponse.class))).thenReturn(mockAggResult);
        List<SensorMaxResponse> result=sensorDataMongoDbServiceImpl.getMaxValue(sensorNames,startDate,endDate);
        assertEquals(result.size(), 2);
    }

    @Test
    public void testGetAvgValue(){
        List<String> sensorNames= Arrays.asList("FUEL_READING","THERMOSTAT");
        String startDate="2025-03-03T18:45:51.611";
        String endDate="2025-03-03T18:46:10.616";

        SensorAvgResponse res=new SensorAvgResponse("FUEL_READING",10.5);
        SensorAvgResponse res1=new SensorAvgResponse("THERMOSTAT",12.5);
        List<SensorAvgResponse> sensorAvgResponses=new ArrayList<>();
        sensorAvgResponses.add(res);
        sensorAvgResponses.add(res1);

        AggregationResults<SensorAvgResponse> mockAggResult=new AggregationResults(sensorAvgResponses,rawResults);
        when(mongoTemplate.aggregate(any(Aggregation.class),eq("sensorData"),eq(SensorAvgResponse.class))).thenReturn(mockAggResult);
        List<SensorAvgResponse> result=sensorDataMongoDbServiceImpl.getAvgValue(sensorNames,startDate,endDate);
        assertEquals(result.size(), 2);
    }

    @Test
    public void testGetMedianValue(){
        List<String> sensorNames= Arrays.asList("FUEL_READING","THERMOSTAT");
        String startDate="2025-03-03T18:45:51.611";
        String endDate="2025-03-03T18:46:10.616";

        SensorData fuelData= new SensorData();
        fuelData.setSensorName("FUEL_READING");
        fuelData.setMetricValue(20.5);
        fuelData.setMetricType(MetricType.FUEL_LEVEL);
        fuelData.setTimeStamp("2025-03-03T18:45:51.611");

        SensorData thermoData= new SensorData();
        thermoData.setSensorName("THERMOSTAT");
        thermoData.setMetricValue(42.5);
        thermoData.setMetricType(MetricType.TEMPERATURE);
        thermoData.setTimeStamp("2025-03-03T18:45:51.611");

        List<SensorData> sensorDataList=new ArrayList<>();
        sensorDataList.add(fuelData);
        sensorDataList.add(thermoData);
        when(sensorDataRepository.findBySensorNameInAndTimeStampBetween(any(),any(),any())).thenReturn(sensorDataList);
        List<SensorMedianResponse> result=sensorDataMongoDbServiceImpl.getMedianValue(sensorNames,startDate,endDate);
        assertEquals(result.size(), 2);
    }

}
