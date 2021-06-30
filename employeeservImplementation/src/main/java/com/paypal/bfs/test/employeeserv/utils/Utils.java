package com.paypal.bfs.test.employeeserv.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;

public class Utils {

    public static final String DATE_FORMAT_STRING = "dd-mm-yyyy";

    public static final Function<Date, String> CONVERT_DATE_STRING = date -> {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STRING);
        dateFormat.setLenient(false);
        return dateFormat.format(date);
    };

}
