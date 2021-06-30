package com.paypal.bfs.test.employeeserv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.validators.EmployeeValidator;
import com.paypal.bfs.test.employeeserv.exceptions.Errors;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class EmployeeValidatorTest {
    Employee employee;
    @InjectMocks
    private EmployeeValidator employeeValidator;
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testAddressLine1ValidationFailure() {

        Address address = new Address();
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setCountry("India");
        address.setZipcode("560066");
        employee = new Employee();
        employee.setFirstName("manish");
        employee.setLastName("pandey");
        employee.setDateOfBirth("18-05-1983");
        employee.setAddress(address);
        Optional<java.util.List<Errors>> errors = employeeValidator.getErrors(employee);
        for (Errors error : errors.get()) {
            assertEquals(error.field, "Address Line 1");
            assertEquals(error.message, "This is required");
        }

    }

    @Test
    public void testMultipleValidationFailure() {

        Address address = new Address();
        address.setState("Karnataka");
        address.setCountry("India");
        address.setZipcode("560066");
        employee = new Employee();
        employee.setFirstName("manish");
        employee.setLastName("pandey");
        employee.setAddress(address);
        Optional<java.util.List<Errors>> errors = employeeValidator.getErrors(employee);
        Map<String, String> errorMap = new HashMap<>();
        for (Errors error : errors.get()) {
            errorMap.put(error.field, error.message);
        }
        assertTrue(errorMap.containsKey("Date of Birth"));
        assertEquals(errorMap.get("Date of Birth"), "This is required");
        assertEquals(errorMap.get("city"), "This is required");

    }

    @Test

    public void testAddressCityValidationFailure() {

        Address address = new Address();
        address.setLine1("Line1");
        address.setState("Karnataka");
        address.setCountry("India");
        address.setZipcode("560066");
        employee = new Employee();
        employee.setFirstName("manish");
        employee.setLastName("pandey");
        employee.setDateOfBirth("18-05-1983");
        employee.setAddress(address);
        Optional<java.util.List<Errors>> errors = employeeValidator.getErrors(employee);
        for (Errors error : errors.get()) {
            assertEquals(error.field, "city");
            assertEquals(error.message, "This is required");
        }

    }

    @Test

    public void testAddressStateValidationFailure() {

        Address address = new Address();
        address.setLine1("Line1");
        address.setCity("Bangalore");
        address.setCountry("India");
        address.setZipcode("560066");
        employee = new Employee();
        employee.setFirstName("manish");
        employee.setLastName("pandey");
        employee.setDateOfBirth("18-05-1983");
        employee.setAddress(address);
        Optional<java.util.List<Errors>> errors = employeeValidator.getErrors(employee);
        for (Errors error : errors.get()) {
            assertEquals(error.field, "state");
            assertEquals(error.message, "This is required");
        }

    }

    @Test

    public void testAddressCountryValidationFailure() {

        Address address = new Address();
        address.setLine1("Line1");
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setZipcode("560066");
        employee = new Employee();
        employee.setFirstName("manish");
        employee.setLastName("pandey");
        employee.setDateOfBirth("18-05-1983");
        employee.setAddress(address);
        Optional<java.util.List<Errors>> errors = employeeValidator.getErrors(employee);
        for (Errors error : errors.get()) {
            assertEquals(error.field, "country");
            assertEquals(error.message, "This is required");
        }
    }

    @Test

    public void testAddressZipCodeValidationFailure() {

        Address address = new Address();
        address.setLine1("Line1");
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setCountry("India");
        employee = new Employee();
        employee.setFirstName("manish");
        employee.setLastName("pandey");
        employee.setDateOfBirth("18-05-1983");
        employee.setAddress(address);
        Optional<java.util.List<Errors>> errors = employeeValidator.getErrors(employee);
        for (Errors error : errors.get()) {
            assertEquals(error.field, "zip code");
            assertEquals(error.message, "This is required");
        }
    }

    @Test

    public void testFirstNameValidationFailure() {

        Address address = new Address();
        address.setLine1("Line1");
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setCountry("India");
        address.setZipcode("560066");
        employee = new Employee();
        employee.setLastName("pandey");
        employee.setDateOfBirth("18-05-1983");
        employee.setAddress(address);
        Optional<java.util.List<Errors>> errors = employeeValidator.getErrors(employee);
        for (Errors error : errors.get()) {
            assertEquals(error.field, "First Name");
            assertEquals(error.message, "This is required");
        }
    }

    @Test

    public void testLastNameValidationFailure() {

        Address address = new Address();
        address.setLine1("Line1");
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setCountry("India");
        address.setZipcode("560066");
        employee = new Employee();
        employee.setFirstName("manish");
        employee.setDateOfBirth("18-05-1983");
        employee.setAddress(address);
        Optional<java.util.List<Errors>> errors = employeeValidator.getErrors(employee);
        for (Errors error : errors.get()) {
            assertEquals(error.field, "Last Name");
            assertEquals(error.message, "This is required");
        }
    }

    @Test

    public void testDOBValidationFailure() {

        Address address = new Address();
        address.setLine1("Line1");
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setCountry("India");
        address.setZipcode("560066");
        employee = new Employee();
        employee.setFirstName("manish");
        employee.setLastName("pandey");
        employee.setAddress(address);
        Optional<java.util.List<Errors>> errors = employeeValidator.getErrors(employee);
        for (Errors error : errors.get()) {
            assertEquals(error.field, "Date of Birth");
            assertEquals(error.message, "This is required");
        }
    }

    @Test

    public void testDOBFormatValidationFailure() {

        Address address = new Address();
        address.setLine1("Line1");
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setCountry("India");
        address.setZipcode("560066");
        employee = new Employee();
        employee.setFirstName("manish");
        employee.setLastName("pandey");
        employee.setDateOfBirth("18/05/1983");
        employee.setAddress(address);
        Optional<java.util.List<Errors>> errors = employeeValidator.getErrors(employee);
        for (Errors error : errors.get()) {
            assertEquals(error.field, "Date of Birth");
            assertEquals(error.message, "Format of Date of Birth is not correct. Should be dd-mm-yyyy");
        }
    }

    @Test
    public void testValidateWithPositive() {
        Address address = new Address();
        address.setLine1("Line1");
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setCountry("India");
        address.setZipcode("560037");
        Employee employee = new Employee();
        employee.setFirstName("test-first-name");
        employee.setLastName("test-last-name");
        employee.setDateOfBirth("20-04-1994");
        employee.setAddress(address);
        Optional<java.util.List<Errors>> errors = employeeValidator.getErrors(employee);
        assertEquals(errors.isPresent(), false);
    }
}