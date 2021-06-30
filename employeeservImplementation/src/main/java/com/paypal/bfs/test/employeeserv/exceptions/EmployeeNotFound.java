package com.paypal.bfs.test.employeeserv.exceptions;

public class EmployeeNotFound extends RuntimeException {

    public EmployeeNotFound(String message) {
        super(message);
    }

    public EmployeeNotFound(final String message, final Throwable cause) {
        super(message, cause);
    }
}