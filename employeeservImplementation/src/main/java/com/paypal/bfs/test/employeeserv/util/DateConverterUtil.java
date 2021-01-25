package com.paypal.bfs.test.employeeserv.util;

import org.apache.commons.beanutils.converters.DateConverter;

public class DateConverterUtil {
    public static DateConverter getDateConverter() {
        DateConverter dateConverter = new DateConverter();
        dateConverter.setPattern("dd/MM/yyyy");
        return dateConverter;
    }
}
