package com.paypal.bfs.test.employeeserv.service;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.database.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.database.entities.EmployeeEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public Optional<Employee> byId(String id) {

        Optional<EmployeeEntity> et = employeeRepository.findById(Integer.valueOf(id));
        if (et.isPresent()) {
            Employee e = employeeMapper.convertToApiModel(et.get());
            if (Objects.nonNull(e)) {
                return Optional.of(e);
            }

        }
        return Optional.empty();

    }

    public Employee create(Employee employeeRequest) throws Exception {
        EmployeeEntity employee = employeeMapper.convertToEntity(employeeRequest);

        // Implements Idempotency
        Example<EmployeeEntity> example = Example.of(employee);
        Optional<EmployeeEntity> actual = employeeRepository.findOne(example);
        if (!actual.isPresent()) {
            employee = employeeRepository.save(employeeMapper.convertToEntity(employeeRequest));
            if (Objects.nonNull(employee)) {
                return employeeMapper.convertToApiModel(employee);

            }
        }
        return employeeMapper.convertToApiModel(actual.get());

    }

}