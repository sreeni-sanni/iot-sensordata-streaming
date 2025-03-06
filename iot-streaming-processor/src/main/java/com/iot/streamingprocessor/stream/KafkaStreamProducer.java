package com.iot.streamingprocessor.stream;

import com.iot.streamingprocessor.constants.Constants;
import com.iot.streamingprocessor.model.Event;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class KafkaStreamProducer {

    KafkaTemplate kafkaTemplate;

    public void send(Event message) {
        try {
            kafkaTemplate.send(Constants.TOPIC, message);
        } catch (Exception ex) {
            log.error("Failed due to exception: {}", ex.getMessage());
        }
    }
}
