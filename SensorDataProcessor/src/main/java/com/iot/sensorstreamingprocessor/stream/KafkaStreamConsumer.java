package com.iot.sensorstreamingprocessor.stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iot.sensorstreamingprocessor.model.SensorData;
import com.iot.sensorstreamingprocessor.repository.SensorDataRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.kafka.streams.StreamsBuilder;

import static com.iot.sensorstreamingprocessor.constants.Constants.TOPIC;

@Slf4j
@AllArgsConstructor
@Configuration
public class KafkaStreamConsumer {

    private final ObjectMapper objectMapper;
    private final SensorDataRepository sensorDataRepository;

    @Bean
    public KStream<String, String> process(StreamsBuilder streamBuilder) {
        KStream<String, String> kStream = streamBuilder.stream(TOPIC);
        kStream.filter((key, value) -> value != null && !value.isBlank())
                .mapValues(this::parseSensorData)
                .filter((key, sensor) -> sensor != null)
                .peek((key, sensorData) -> sensorDataRepository.save(sensorData));
        return kStream;
    }

    private SensorData parseSensorData(String sensorData) {
        try {
            return objectMapper.readValue(sensorData, SensorData.class);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
