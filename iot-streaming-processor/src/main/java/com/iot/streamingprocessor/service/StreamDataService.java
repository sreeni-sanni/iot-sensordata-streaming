package com.iot.streamingprocessor.service;

import com.iot.streamingprocessor.model.Event;

public interface StreamDataService {
    void publish(Event event);
}
