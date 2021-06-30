package com.paypal.bfs.test.employeeserv.impl;

import java.util.List;
import java.util.Optional;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.exceptions.EmployeeNotFound;
import com.paypal.bfs.test.employeeserv.exceptions.InternalServerError;
import com.paypal.bfs.test.employeeserv.exceptions.ValidationError;
import com.paypal.bfs.test.employeeserv.exceptions.Errors;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;
import com.paypal.bfs.test.employeeserv.validators.EmployeeValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeValidator employeeValidator;

    public EmployeeResourceImpl(EmployeeService employeeService, EmployeeValidator employeeValidator) {
        this.employeeService = employeeService;
        this.employeeValidator = employeeValidator;
    }

    @Override
    public ResponseEntity<Employee> employeeGetById(String id) {

        Optional<Employee> employee = employeeService.byId(id);

        if (employee.isPresent()) {
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);
        } else {
            throw new EmployeeNotFound("Employee does not exist");
        }

    }

    @Override
    public ResponseEntity<Employee> createEmployee(Employee employeeRequest) {
        Employee empResponse = null;

        Optional<List<Errors>> error = employeeValidator.getErrors(employeeRequest);
        if (error.isPresent()) {
            throw new ValidationError(error.get());
        }
        try {
            empResponse = employeeService.create(employeeRequest);
        } catch (Exception e) {
            throw new InternalServerError("Internal Server Error", e);
        }

        return new ResponseEntity<>(empResponse, HttpStatus.CREATED);

    }

}
