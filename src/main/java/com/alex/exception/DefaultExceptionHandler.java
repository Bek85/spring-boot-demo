package com.alex.exception;

import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class DefaultExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiError> handle(ResourceNotFoundException ex, HttpServletRequest request) {

    ApiError apiError = new ApiError(request.getRequestURI(), ex.getMessage(), HttpStatus.NOT_FOUND.value(),
        ZonedDateTime.now(), List.of());
    return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handle(Exception ex, HttpServletRequest request) {

    ApiError apiError = new ApiError(request.getRequestURI(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
        ZonedDateTime.now(), List.of());
    return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
