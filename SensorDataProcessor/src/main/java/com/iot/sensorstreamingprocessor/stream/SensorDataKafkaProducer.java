package com.iot.sensorstreamingprocessor.stream;

import com.iot.sensorstreamingprocessor.constants.Constants;
import com.iot.sensorstreamingprocessor.model.SensorData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class SensorDataKafkaProducer{

    KafkaTemplate kafkaTemplate;

    public void send(SensorData message) {
        try{
            kafkaTemplate.send(Constants.TOPIC, message);
            log.info("Message sent successfully");
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }
}
