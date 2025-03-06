package com.iot.streamingprocessor.service;

import com.iot.streamingprocessor.model.Event;
import com.iot.streamingprocessor.model.MetricType;
import com.iot.streamingprocessor.service.impl.StreamingProcessorServiceImpl;
import com.iot.streamingprocessor.stream.KafkaStreamProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StreamingProcessorServiceTest {

    @InjectMocks
    StreamingProcessorServiceImpl streamDataService;
    @Mock
    KafkaStreamProducer sensorDataKafkaProducer;

    @Test
    public void testPublish(){
        Event event=new Event();
        event.setSensorName("THERMOSTAT");
        event.setMetricValue(85.5);
        event.setMetricType(MetricType.TEMPERATURE);
        event.setTimeStamp("2025-03-03T18:46:10.616");
        streamDataService.publish(event);
        verify(sensorDataKafkaProducer, times(1)).send(event);
    }

}
