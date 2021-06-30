package com.paypal.bfs.test.employeeserv.service;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.database.entities.AddressEntity;
import com.paypal.bfs.test.employeeserv.database.entities.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.utils.Utils;

import org.springframework.stereotype.Component;
import static com.paypal.bfs.test.employeeserv.utils.Utils.CONVERT_DATE_STRING;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class EmployeeMapper {

    public EmployeeEntity convertToEntity(Employee employee) throws ParseException {
        EmployeeEntity employeesEntity = new EmployeeEntity();
        employeesEntity.setFirstName(employee.getFirstName());
        employeesEntity.setLastName(employee.getLastName());
        employeesEntity.setDateOfBirth(new SimpleDateFormat(Utils.DATE_FORMAT_STRING).parse(employee.getDateOfBirth()));
        employeesEntity.setAddress(convertToAddressEntity(employee.getAddress()));
        return employeesEntity;
    }

    private AddressEntity convertToAddressEntity(Address address) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity(address.getCity());
        addressEntity.setCountry(address.getCountry());
        addressEntity.setState(address.getState());
        addressEntity.setZipcode(address.getZipcode());
        addressEntity.setLine1(address.getLine1());
        addressEntity.setLine2(address.getLine2());
        return addressEntity;
    }

    public Employee convertToApiModel(EmployeeEntity employeesEntity) {
        Employee employee = new Employee();
        employee.setId(employeesEntity.getId());
        employee.setFirstName(employeesEntity.getFirstName());
        employee.setLastName(employeesEntity.getLastName());
        employee.setDateOfBirth(CONVERT_DATE_STRING.apply(employeesEntity.getDateOfBirth()));
        employee.setAddress(convertToAddressModel(employeesEntity.getAddress()));
        return employee;
    }

    private Address convertToAddressModel(AddressEntity addressEntity) {
        Address address = new Address();
        address.setLine1(addressEntity.getLine1());
        address.setLine2(addressEntity.getLine2());
        address.setCity(addressEntity.getCity());
        address.setState(addressEntity.getState());
        address.setCountry(addressEntity.getCountry());
        address.setZipcode(addressEntity.getZipcode());
        return address;
    }

}
