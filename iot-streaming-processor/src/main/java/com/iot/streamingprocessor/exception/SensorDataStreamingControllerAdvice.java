package com.iot.streamingprocessor.exception;

import com.iot.streamingprocessor.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class SensorDataStreamingControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception exception, WebRequest webRequest) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.internalServerError().body(getErrorDetails(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest));
    }

    @ExceptionHandler(SensorDataProcessorException.class)
    public ResponseEntity<ErrorResponse> handleSensorDataApiException(SensorDataProcessorException exception, WebRequest webRequest) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.badRequest().body(getErrorDetails(exception.getMessage(), HttpStatus.BAD_REQUEST, webRequest));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(MethodArgumentNotValidException exception, WebRequest webRequest) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.badRequest().body(getErrorDetails(exception.getMessage(), HttpStatus.BAD_REQUEST, webRequest));
    }

    private ErrorResponse getErrorDetails(String message, HttpStatus status, WebRequest webRequest) {
        return new ErrorResponse(message, status.value(), webRequest.getDescription(false), LocalDateTime.now());
    }
}
