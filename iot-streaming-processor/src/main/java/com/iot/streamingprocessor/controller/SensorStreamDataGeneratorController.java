package com.iot.streamingprocessor.controller;

import com.iot.streamingprocessor.data.SensorDataGenerator;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableCaching
@AllArgsConstructor
public class SensorStreamDataGeneratorController {

    private final SensorDataGenerator sensorSampleDataGenerator;

    @GetMapping(value = "/generator/start", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> startTask() {
        sensorSampleDataGenerator.startStreamGenerator();
        return ResponseEntity.ok("The Stream Data Generator started successfully!");
    }

    @GetMapping(value = "/generator/stop", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> stopTask() {
        sensorSampleDataGenerator.stopStreamGenerator();
        return ResponseEntity.ok("The Stream Data Generator stopped successfully!");
    }
}