package com.project.ecommerse.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<EcommerseErrorResponse> handleException(EcommerseException ecommerseException){
        return new ResponseEntity<>(new EcommerseErrorResponse(ecommerseException.getMessage(), LocalDateTime.now(), ecommerseException.getStatus().value()), ecommerseException.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<EcommerseErrorResponse> handleException(Exception exception){
        return new ResponseEntity<>(new EcommerseErrorResponse(exception.getMessage(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }




}
