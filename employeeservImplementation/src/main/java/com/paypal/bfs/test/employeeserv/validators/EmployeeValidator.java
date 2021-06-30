package com.paypal.bfs.test.employeeserv.validators;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.exceptions.Errors;
import com.paypal.bfs.test.employeeserv.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

@Component
public class EmployeeValidator {

    private static final String FIRST_NAME = "First Name";
    private static final String LAST_NAME = "Last Name";
    private static final String DATE_OF_BIRTH = "Date of Birth";
    private static final String ADDRESS_LINE_1 = "Address Line 1";
    private static final String CITY = "city";
    private static final String STATE = "state";
    private static final String ZIP_CODE = "zip code";
    private static final String COUNTRY = "country";
    private static final int MAX_LENGTH = 255;
    private static final String REQUIRED = "This is required";
    private static final String INVALID_DOB = "Format of Date of Birth is not correct. Should be dd-mm-yyyy";
    private static final String MAX_LENGTH_IS = "Max length is ";

    public Optional<List<Errors>> getErrors(Employee employeeRequest) {
        List<Errors> errorsList = new ArrayList<>();

        checkForRequired(employeeRequest, errorsList);

        checkForLength(employeeRequest, errorsList);
        if (employeeRequest.getDateOfBirth() != null) {
            validateDate(employeeRequest, errorsList);
        }

        return errorsList.size() > 0 ? Optional.of(errorsList) : Optional.empty();

    }

    private void checkForLength(Employee employeeRequest, List<Errors> errorsList) {
        if (isMaxLength(employeeRequest.getFirstName(), MAX_LENGTH)) {
            errorsList.add(Errors.builder().field(FIRST_NAME).message(MAX_LENGTH_IS + MAX_LENGTH).build());
        }
        if (isMaxLength(employeeRequest.getLastName(), MAX_LENGTH)) {
            errorsList.add(Errors.builder().field(LAST_NAME).message(MAX_LENGTH_IS + MAX_LENGTH).build());
        }
        if (isMaxLength(employeeRequest.getAddress().getLine1(), MAX_LENGTH)) {
            errorsList.add(Errors.builder().field(ADDRESS_LINE_1).message(MAX_LENGTH_IS + MAX_LENGTH).build());
        }
        if (isMaxLength(employeeRequest.getAddress().getCountry(), MAX_LENGTH)) {
            errorsList.add(Errors.builder().field(COUNTRY).message(MAX_LENGTH_IS + MAX_LENGTH).build());
        }
        if (isMaxLength(employeeRequest.getAddress().getState(), MAX_LENGTH)) {
            errorsList.add(Errors.builder().field(STATE).message(MAX_LENGTH_IS + MAX_LENGTH).build());
        }
        if (isMaxLength(employeeRequest.getAddress().getZipcode(), 10)) {
            errorsList.add(Errors.builder().field(ZIP_CODE).message(MAX_LENGTH_IS + MAX_LENGTH).build());
        }
    }

    private boolean isMaxLength(String value, int maxLength) {
        return !isEmpty(value) && value.length() > maxLength;
    }

    private void checkForRequired(Employee employeeRequest, List<Errors> errorsList) {
        if (isEmpty(employeeRequest.getFirstName())) {
            errorsList.add(Errors.builder().field(FIRST_NAME).message(REQUIRED).build());
        }

        if (isEmpty(employeeRequest.getLastName())) {
            errorsList.add(Errors.builder().field(LAST_NAME).message(REQUIRED).build());
        }

        if (isEmpty(employeeRequest.getDateOfBirth())) {
            errorsList.add(Errors.builder().field(DATE_OF_BIRTH).message(REQUIRED).build());
        }

        if (isEmpty(employeeRequest.getAddress().getLine1())) {
            errorsList.add(Errors.builder().field(ADDRESS_LINE_1).message(REQUIRED).build());
        }

        if (isEmpty(employeeRequest.getAddress().getCity())) {
            errorsList.add(Errors.builder().field(CITY).message(REQUIRED).build());
        }
        if (isEmpty(employeeRequest.getAddress().getState())) {
            errorsList.add(Errors.builder().field(STATE).message(REQUIRED).build());
        }
        if (isEmpty(employeeRequest.getAddress().getZipcode())) {
            errorsList.add(Errors.builder().field(ZIP_CODE).message(REQUIRED).build());
        }
        if (isEmpty(employeeRequest.getAddress().getCountry())) {
            errorsList.add(Errors.builder().field(COUNTRY).message(REQUIRED).build());
        }
    }

    private boolean isEmpty(String value) {
        return null == value || "" == value;
    }

    private void validateDate(Employee employeeRequest, List<Errors> errorsList) {
        DateFormat dateFormat = new SimpleDateFormat(Utils.DATE_FORMAT_STRING);
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(employeeRequest.getDateOfBirth());
        } catch (ParseException e) {
            errorsList.add(Errors.builder().field(DATE_OF_BIRTH).message(INVALID_DOB).build());
        }
    }

}