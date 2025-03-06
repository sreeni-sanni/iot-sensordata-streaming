package com.iot.streamingprocessor.controller;

import com.iot.streamingprocessor.model.Event;
import com.iot.streamingprocessor.service.StreamDataService;
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
    public ResponseEntity<?> sensorData(@RequestBody @Valid Event event){
        streamDataService.publish(event);
        return ResponseEntity.ok("Success");
    }
}
