package com.iot.sensorstreamingprocessor.controller;

import com.iot.sensorstreamingprocessor.model.SensorData;
import com.iot.sensorstreamingprocessor.service.StreamDataService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class SensorDataController {
    StreamDataService streamDataService;

    @PostMapping("/write")
    public ResponseEntity<?> sensorData(@RequestBody @Valid SensorData sensorData){
        streamDataService.publish(sensorData);
        return ResponseEntity.ok("Success");
    }
}
