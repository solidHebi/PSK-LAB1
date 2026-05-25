package com.psk_1.psk12.controller;

import com.psk_1.psk12.Exceptions.CopyModifiedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CopyModifiedException.class)
    public ResponseEntity<String> handleOptimisticLock(
            CopyModifiedException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }
}
