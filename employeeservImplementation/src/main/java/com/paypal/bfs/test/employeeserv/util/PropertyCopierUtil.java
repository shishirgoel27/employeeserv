package com.paypal.bfs.test.employeeserv.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

public class PropertyCopierUtil<S,D> {

    public static <S,D,T> void copyProperties(S source, D destination, Class<T> convertTo) throws Exception{
        DateConverter dateConverter = DateConverterUtil.getDateConverter();
        ConvertUtils.register(dateConverter, convertTo);
        BeanUtils.copyProperties(destination, source);
    }
}
