package com.paypal.bfs.test.employeeserv.exceptions;

import java.util.List;

public class ValidationError extends RuntimeException {
    private List<Errors> errors;

    public List<Errors> getErrors() {
        return errors;
    }

    public void setErrors(List<Errors> errors) {
        this.errors = errors;
    }

    public ValidationError(List<Errors> errors) {
        this.errors = errors;
    }

}
