package com.paypal.bfs.test.employeeserv.exceptions;

public class InternalServerError extends RuntimeException {

    public InternalServerError(String message) {
        super(message);
    }

    public InternalServerError(final String message, final Throwable cause) {
        super(message, cause);
    }
}