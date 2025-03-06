package com.iot.streamingprocessor.service.impl;

import com.iot.streamingprocessor.model.Event;
import com.iot.streamingprocessor.service.StreamDataService;
import com.iot.streamingprocessor.stream.KafkaStreamProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StreamDataServiceImpl implements StreamDataService {

    KafkaStreamProducer kafkaProducer;

    @Override
    public void publish(Event event) {
        kafkaProducer.send(event);
    }
}
