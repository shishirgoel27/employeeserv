package com.paypal.bfs.test.employeeserv.exception;

public class CreateEmployeeException extends RuntimeException {
    public CreateEmployeeException(Exception rootCause) {
        super(rootCause);
    }
}
