package com.iot.streamingprocessor.exception;

import java.io.Serial;

public class StreamingProcessorException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public StreamingProcessorException(String message){
        super(message);
    }
}
