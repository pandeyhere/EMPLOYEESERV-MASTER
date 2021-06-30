package com.paypal.bfs.test.employeeserv.impl;

import java.util.ArrayList;
import java.util.List;

import com.paypal.bfs.test.employeeserv.exceptions.EmployeeNotFound;
import com.paypal.bfs.test.employeeserv.exceptions.Errors;
import com.paypal.bfs.test.employeeserv.exceptions.InternalServerError;
import com.paypal.bfs.test.employeeserv.exceptions.ValidationError;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    public RestExceptionHandler() {
        super();
    }

    @ExceptionHandler({ ConstraintViolationException.class, DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleBadRequest(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getLocalizedMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST,
                request);
    }

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<Object> handleInternalServerErrorRequest(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getLocalizedMessage(), new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(EmployeeNotFound.class)
    public ResponseEntity<Object> handleEmployeeNotFoundException(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getLocalizedMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(ValidationError.class)
    public ResponseEntity<Object> handleValidationErrorRequest(ValidationError ex, WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (Errors error : ex.getErrors()) {
            errors.add(error.getField() + ": " + error.getMessage());
        }
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
