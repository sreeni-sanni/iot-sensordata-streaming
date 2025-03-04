package com.iot.sensorstreamingprocessor.exception;

import java.io.Serial;

public class SensorDataProcessorException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public SensorDataProcessorException(String message){
        super(message);
    }
}
