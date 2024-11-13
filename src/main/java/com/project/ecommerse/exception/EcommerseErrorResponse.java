package com.project.ecommerse.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class EcommerseErrorResponse {
    private String message;
    private LocalDateTime timestamp;
    private int status;
}
