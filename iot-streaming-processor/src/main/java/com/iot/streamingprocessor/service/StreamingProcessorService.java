package com.iot.streamingprocessor.service;

import com.iot.streamingprocessor.model.Event;

public interface StreamingProcessorService {
    void publish(Event event);
}
