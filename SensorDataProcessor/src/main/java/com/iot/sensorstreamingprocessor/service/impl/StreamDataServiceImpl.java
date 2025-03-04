package com.iot.sensorstreamingprocessor.service.impl;

import com.iot.sensorstreamingprocessor.model.SensorData;
import com.iot.sensorstreamingprocessor.service.StreamDataService;
import com.iot.sensorstreamingprocessor.stream.SensorDataKafkaProducer;
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
