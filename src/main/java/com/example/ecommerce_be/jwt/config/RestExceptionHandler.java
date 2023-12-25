package com.example.ecommerce_be.jwt.config;

import com.example.ecommerce_be.dto.ErrorDto;
import com.example.ecommerce_be.jwt.exceptions.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHandler {
  @ExceptionHandler(value = {AppException.class})

  @ResponseBody
  public ResponseEntity<ErrorDto> handleException(AppException ex){
    return ResponseEntity.status(ex.getHttpStatus())
      .body(new ErrorDto(ex.getMessage()));
  }
}
