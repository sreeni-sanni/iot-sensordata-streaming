package com.iot.streamingdataapi.exception;

import com.iot.streamingdataapi.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

import static com.iot.streamingdataapi.constants.Constants.ACCESS_DENIED;
import static com.iot.streamingdataapi.constants.Constants.EXCEPTION_OCCURRED;

@Slf4j
@ControllerAdvice
public class SensorDataControllerAdvice {

    @ExceptionHandler(SensorDataApiException.class)
    public ResponseEntity<ErrorResponse> handleSensorDataApiException(SensorDataApiException exception, WebRequest webRequest) {
        log.error(EXCEPTION_OCCURRED, exception.getMessage(), exception);
        return ResponseEntity.badRequest().body(getErrorDetails(exception.getMessage(), HttpStatus.BAD_REQUEST, webRequest));
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException exception, WebRequest webRequest) {
        log.error(EXCEPTION_OCCURRED, exception.getMessage(), exception);
        return ResponseEntity.badRequest().body(getErrorDetails(exception.getMessage(), HttpStatus.BAD_REQUEST, webRequest));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(MethodArgumentNotValidException exception, WebRequest webRequest) {
        log.error(EXCEPTION_OCCURRED, exception.getMessage(), exception);
        return ResponseEntity.badRequest().body(getErrorDetails(exception.getMessage(), HttpStatus.BAD_REQUEST, webRequest));
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponse> handleJwtException(JwtException exception, WebRequest webRequest) {
        log.error(EXCEPTION_OCCURRED, exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(getErrorDetails(exception.getMessage(), HttpStatus.UNAUTHORIZED, webRequest));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception exception, WebRequest webRequest) {
        log.error(EXCEPTION_OCCURRED, exception.getMessage(), exception);
        return ResponseEntity.internalServerError().body(getErrorDetails(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest));
    }
    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAuthorizationDeniedException(AuthorizationDeniedException exception, WebRequest webRequest) {
        log.error(EXCEPTION_OCCURRED, exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(getErrorDetails(ACCESS_DENIED, HttpStatus.UNAUTHORIZED, webRequest));
    }
    private ErrorResponse getErrorDetails(String message, HttpStatus status, WebRequest webRequest) {
        return new ErrorResponse(message, status.value(), webRequest.getDescription(false), LocalDateTime.now());
    }
}
