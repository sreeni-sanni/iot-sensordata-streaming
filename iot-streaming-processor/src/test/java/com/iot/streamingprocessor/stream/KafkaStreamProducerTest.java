package com.iot.streamingprocessor.stream;

import com.iot.streamingprocessor.model.Event;
import com.iot.streamingprocessor.model.MetricType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class KafkaStreamProducerTest {
    @InjectMocks
    KafkaStreamProducer kafkaStreamProducer;
    @Mock
    KafkaTemplate kafkaTemplate;

    @Test
    public void testSend(){
        Event event=new Event();
        event.setSensorName("THERMOSTAT");
        event.setMetricValue(85.5);
        event.setMetricType(MetricType.TEMPERATURE);
        event.setTimeStamp("2025-03-03T18:46:10.616");
        kafkaStreamProducer.send(event);
        verify(kafkaTemplate, times(1)).send("sensordata", event);

    }

}
