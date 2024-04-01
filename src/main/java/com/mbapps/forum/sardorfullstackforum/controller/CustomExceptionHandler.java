package com.mbapps.forum.sardorfullstackforum.controller;

import com.mbapps.forum.sardorfullstackforum.model.CustomErrorResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<CustomErrorResponseModel> handleAllExceptions(Exception ex, WebRequest request) {
    CustomErrorResponseModel errorResponse = new CustomErrorResponseModel();
    errorResponse.setTimestamp(LocalDateTime.now());
    errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    errorResponse.setError("Internal Server Error");
    errorResponse.setMessage(ex.getMessage());
    errorResponse.setPath(((ServletWebRequest) request).getRequest().getRequestURI());
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
