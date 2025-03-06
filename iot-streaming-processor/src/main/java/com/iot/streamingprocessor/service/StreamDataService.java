package com.iot.streamingprocessor.service;

import com.iot.streamingprocessor.model.SensorData;

public interface StreamDataService {
    void publish(SensorData message);
}
