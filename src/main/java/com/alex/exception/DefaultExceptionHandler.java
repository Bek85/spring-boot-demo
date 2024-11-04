package com.alex.exception;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class DefaultExceptionHandler {

  // Resource not found - 404
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiError> handle(ResourceNotFoundException ex, HttpServletRequest request) {

    ApiError apiError = new ApiError(request.getRequestURI(), ex.getMessage(), HttpStatus.NOT_FOUND.value(),
        ZonedDateTime.now(), List.of());
    return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
  }

  // Resource already exists - 409
  @ExceptionHandler(DuplicateResourceException.class)
  public ResponseEntity<ApiError> handle(DuplicateResourceException ex, HttpServletRequest request) {

    ApiError apiError = new ApiError(request.getRequestURI(), ex.getMessage(), HttpStatus.CONFLICT.value(),
        ZonedDateTime.now(), List.of());
    return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
  }

  // Bad request - 400
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiError> handle(
      MethodArgumentNotValidException e,
      HttpServletRequest request) {
    List<String> errors = e.getAllErrors().stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .toList();

    ApiError apiError = new ApiError(
        request.getRequestURI(),
        e.getMessage(),
        HttpStatus.BAD_REQUEST.value(),
        ZonedDateTime.now(),
        errors);
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }

  // Internal server error - 500
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handle(Exception ex, HttpServletRequest request) {

    ApiError apiError = new ApiError(request.getRequestURI(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
        ZonedDateTime.now(), List.of());
    return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
