package com.paypal.bfs.test.employeeserv.exception.mapper;

import com.paypal.bfs.test.employeeserv.exception.CreateEmployeeException;
import com.paypal.bfs.test.employeeserv.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleException(EmployeeNotFoundException employeeNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(employeeNotFoundException.getMessage());
    }

    @ExceptionHandler(CreateEmployeeException.class)
    public ResponseEntity<String> handleException(CreateEmployeeException createEmployeeException) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(createEmployeeException.getCause().getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception anyException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(anyException.getMessage());
    }

}
