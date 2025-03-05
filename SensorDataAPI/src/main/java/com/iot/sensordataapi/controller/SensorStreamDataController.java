package com.iot.sensordataapi.controller;

import com.iot.sensordataapi.model.*;
import com.iot.sensordataapi.service.SensorDataService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableCaching
@AllArgsConstructor
@RequestMapping("/api/v1")
public class SensorStreamDataController {

    private final SensorDataService sensorDataService;

    @PostMapping("/min")
    public ResponseEntity<List<SensorMinResponse>> getMinSensorValue(@RequestBody @Valid SensorDataRequest sensorDataRequest) {
        return ResponseEntity.ok(sensorDataService.sensorDataMinValue(sensorDataRequest.sensorNames(), sensorDataRequest.startDate(), sensorDataRequest.endDate()));
    }

    @PostMapping("/max")
    public ResponseEntity<List<SensorMaxResponse>> getMaxSensorValue(@RequestBody @Valid SensorDataRequest sensorDataRequest) {
        return ResponseEntity.ok(sensorDataService.sensorDataMaxValue(sensorDataRequest.sensorNames(), sensorDataRequest.startDate(), sensorDataRequest.endDate()));
    }

    @PostMapping("/average")
    public ResponseEntity<List<SensorAvgResponse>> getAverageSensorValue(@RequestBody @Valid SensorDataRequest sensorDataRequest) {
        return ResponseEntity.ok(sensorDataService.sensorDataAvgValue(sensorDataRequest.sensorNames(), sensorDataRequest.startDate(), sensorDataRequest.endDate()));
    }

    @PostMapping("/median")
    public ResponseEntity<List<SensorMedianResponse>> getMedianSensorValue(@RequestBody @Valid SensorDataRequest sensorDataRequest) {
        return ResponseEntity.ok(sensorDataService.sensorDataMedianValue(sensorDataRequest.sensorNames(), sensorDataRequest.startDate(), sensorDataRequest.endDate()));
    }
}
