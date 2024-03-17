package com.mbapps.forum.sardorfullstackforum.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponseModel> handleDataNotFoundException(DataNotFoundException ex) {
        ErrorResponseModel errorResponse = new ErrorResponseModel(HttpStatus.NOT_FOUND.value(), ex.getMessage(), false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

}