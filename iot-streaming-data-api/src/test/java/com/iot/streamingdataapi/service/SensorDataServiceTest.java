package com.iot.streamingdataapi.service;

import com.iot.streamingdataapi.model.SensorAvgResponse;
import com.iot.streamingdataapi.model.SensorMaxResponse;
import com.iot.streamingdataapi.model.SensorMedianResponse;
import com.iot.streamingdataapi.model.SensorMinResponse;
import com.iot.streamingdataapi.service.impl.SensorDataServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SensorDataServiceTest {

    @InjectMocks
    SensorDataServiceImpl serviceImpl;

    @Mock
    SensorDataMongoDbService mongoDbService;

    @Test
    public void testSensorDataMinValue(){
        List<String> sensorNames= Arrays.asList("FUEL_READING","THERMOSTAT");
        String startDate="2025-03-03T18:45:51.611";
        String endDate="2025-03-03T18:46:10.616";
        SensorMinResponse res=new SensorMinResponse("FUEL_READING",10.5);
        SensorMinResponse res1=new SensorMinResponse("THERMOSTAT",12.5);
        List<SensorMinResponse> sensorMinResponses=new ArrayList<>();
        sensorMinResponses.add(res);
        sensorMinResponses.add(res1);
        when(mongoDbService.getMinValue(any(),any(),any())).thenReturn(sensorMinResponses);
        List<SensorMinResponse> result=serviceImpl.sensorDataMinValue(sensorNames,startDate,endDate);
        assertNotNull(result);
        assertEquals(result.size(), 2);
    }

    @Test
    public void testSensorDataMaxValue(){
        List<String> sensorNames= Arrays.asList("FUEL_READING","THERMOSTAT");
        String startDate="2025-03-03T18:45:51.611";
        String endDate="2025-03-03T18:46:10.616";
        SensorMaxResponse res=new SensorMaxResponse("FUEL_READING",10.5);
        SensorMaxResponse res1=new SensorMaxResponse("THERMOSTAT",12.5);
        List<SensorMaxResponse> sensorMaxResponses=new ArrayList<>();
        sensorMaxResponses.add(res);
        sensorMaxResponses.add(res1);
        when(mongoDbService.getMaxValue(any(),any(),any())).thenReturn(sensorMaxResponses);
        List<SensorMaxResponse> result=serviceImpl.sensorDataMaxValue(sensorNames,startDate,endDate);
        assertNotNull(result);
        assertEquals(result.size(), 2);
    }

    @Test
    public void testSensorDataAvgValue(){
        List<String> sensorNames= Arrays.asList("FUEL_READING","THERMOSTAT");
        String startDate="2025-03-03T18:45:51.611";
        String endDate="2025-03-03T18:46:10.616";
        SensorAvgResponse res=new SensorAvgResponse("FUEL_READING",10.5);
        SensorAvgResponse res1=new SensorAvgResponse("THERMOSTAT",12.5);
        List<SensorAvgResponse> sensorAvgResponses=new ArrayList<>();
        sensorAvgResponses.add(res);
        sensorAvgResponses.add(res1);
        when(mongoDbService.getAvgValue(any(),any(),any())).thenReturn(sensorAvgResponses);
        List<SensorAvgResponse> result=serviceImpl.sensorDataAvgValue(sensorNames,startDate,endDate);
        assertNotNull(result);
        assertEquals(result.size(), 2);
    }
    @Test
    public void testSensorDataMedianValue(){
        List<String> sensorNames= Arrays.asList("FUEL_READING","THERMOSTAT");
        String startDate="2025-03-03T18:45:51.611";
        String endDate="2025-03-03T18:46:10.616";
        SensorMedianResponse res=new SensorMedianResponse("FUEL_READING",10.5);
        SensorMedianResponse res1=new SensorMedianResponse("THERMOSTAT",12.5);
        List<SensorMedianResponse> sensorMedianResponses=new ArrayList<>();
        sensorMedianResponses.add(res);
        sensorMedianResponses.add(res1);
        when(mongoDbService.getMedianValue(any(),any(),any())).thenReturn(sensorMedianResponses);
        List<SensorMedianResponse> result=serviceImpl.sensorDataMedianValue(sensorNames,startDate,endDate);
        assertNotNull(result);
        assertEquals(result.size(), 2);

    }
}
