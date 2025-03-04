package com.iot.sensorstreamingprocessor.service;

import com.iot.sensorstreamingprocessor.model.SensorData;

public interface StreamDataService {
    void publish(SensorData message);
}
