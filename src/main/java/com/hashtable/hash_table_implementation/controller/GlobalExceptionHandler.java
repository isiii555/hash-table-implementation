package com.hashtable.hash_table_implementation.controller;

import com.hashtable.hash_table_implementation.exception.ErrorResponse;
import com.hashtable.hash_table_implementation.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.FileNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleException(NotFoundException notFoundException) {
        ErrorResponse employeeErrorResponse = new ErrorResponse();
        employeeErrorResponse.setMessage(notFoundException.getMessage());
        employeeErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        employeeErrorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(employeeErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleException(FileNotFoundException fileNotFoundException) {
        ErrorResponse employeeErrorResponse = new ErrorResponse();
        employeeErrorResponse.setMessage(fileNotFoundException.getMessage());
        employeeErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        employeeErrorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(employeeErrorResponse, HttpStatus.NOT_FOUND);
    }
}
