package com.example.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RescueNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public RescueNotFoundException(String message) {
        super(message);
    }

}
