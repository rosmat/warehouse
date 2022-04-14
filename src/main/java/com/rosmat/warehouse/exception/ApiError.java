package com.rosmat.warehouse.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ApiError {
    private LocalDateTime localDateTime;
    private HttpStatus status;
    private String message;
}
