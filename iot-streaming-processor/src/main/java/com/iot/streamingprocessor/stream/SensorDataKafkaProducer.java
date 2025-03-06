package com.iot.streamingprocessor.stream;

import com.iot.streamingprocessor.constants.Constants;
import com.iot.streamingprocessor.model.SensorData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class SensorDataKafkaProducer {

    KafkaTemplate kafkaTemplate;

    public void send(SensorData message) {
        try {
            kafkaTemplate.send(Constants.TOPIC, message);
            log.info("Message sent to topic " + Constants.TOPIC);
        } catch (Exception ex) {
            log.error("Failed due to exception: {}", ex.getMessage());
        }
    }
}
