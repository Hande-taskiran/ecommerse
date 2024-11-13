package com.project.ecommerse.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EcommerseException extends RuntimeException{
    private HttpStatus status;


    public EcommerseException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
