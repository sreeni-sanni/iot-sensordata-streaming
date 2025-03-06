package com.iot.streamingprocessor.service.impl;

import com.iot.streamingprocessor.model.SensorData;
import com.iot.streamingprocessor.service.StreamDataService;
import com.iot.streamingprocessor.stream.SensorDataKafkaProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StreamDataServiceImpl implements StreamDataService {

    SensorDataKafkaProducer kafkaProducer;

    @Override
    public void publish(SensorData message) {
        kafkaProducer.send(message);
    }
}
