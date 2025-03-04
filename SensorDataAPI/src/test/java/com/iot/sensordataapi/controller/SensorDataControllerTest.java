package com.iot.sensordataapi.controller;

import com.iot.sensordataapi.model.*;
import com.iot.sensordataapi.service.SensorDataService;
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
public class SensorDataControllerTest {

    @Mock
    SensorDataService sensorDataService;

    @InjectMocks
    SensorStreamDataController sensorStreamDataController;

    @Test
    public void testGetMinSensorValue()  {
        SensorMinResponse res=new SensorMinResponse("THERMOSTAT",12.5);
        List<SensorMinResponse> sensorMinResponses=new ArrayList<>();
        sensorMinResponses.add(res);
        when(sensorDataService.sensorDataMinValue(any(),any(),any())).thenReturn(sensorMinResponses);
        var response  = sensorStreamDataController.getMinSensorValue(getSensorDataRequest());
        assertNotNull(response.getBody());
        assertEquals(response.getBody().size(),1);
        assertEquals(response.getBody().get(0).minValue(),12.5);
    }
    @Test
    public void testGetMaxSensorValue()  {
        SensorMaxResponse res=new SensorMaxResponse("THERMOSTAT",12.5);
        List<SensorMaxResponse> sensorMaxResponse=new ArrayList<>();
        sensorMaxResponse.add(res);
        when(sensorDataService.sensorDataMaxValue(any(),any(),any())).thenReturn(sensorMaxResponse);
        var response  = sensorStreamDataController.getMaxSensorValue(getSensorDataRequest());
        assertNotNull(response.getBody());
        assertEquals(response.getBody().size(),1);
        assertEquals(response.getBody().get(0).maxValue(),12.5);
    }
    @Test
    public void testGetAvgSensorValue()  {
        SensorAvgResponse res=new SensorAvgResponse("THERMOSTAT",12.5);
        List<SensorAvgResponse> sensorAvgResponses=new ArrayList<>();
        sensorAvgResponses.add(res);
        when(sensorDataService.sensorDataAvgValue(any(),any(),any())).thenReturn(sensorAvgResponses);
        var response  = sensorStreamDataController.getAverageSensorValue(getSensorDataRequest());
        assertNotNull(response.getBody());
        assertEquals(response.getBody().size(),1);
        assertEquals(response.getBody().get(0).avgValue(),12.5);
    }
    @Test
    public void testGetMedianSensorValue()  {
        SensorMedianResponse res=new SensorMedianResponse("THERMOSTAT",12.5);
        List<SensorMedianResponse> sensorMedianResponses=new ArrayList<>();
        sensorMedianResponses.add(res);
        when(sensorDataService.sensorDataMedianValue(any(),any(),any())).thenReturn(sensorMedianResponses);
        var response  = sensorStreamDataController.getMedianSensorValue(getSensorDataRequest());
        assertNotNull(response.getBody());
        assertEquals(response.getBody().size(),1);
        assertEquals(response.getBody().get(0).medianValue(),12.5);
    }

 private SensorDataRequest getSensorDataRequest(){
     List<String> sensorNames= Arrays.asList("FUEL_READING","THERMOSTAT");
     String startDate="2025-03-03T18:45:51.611";
     String endDate="2025-03-03T18:46:10.616";
     return new SensorDataRequest(sensorNames,startDate,endDate);
 }

}
