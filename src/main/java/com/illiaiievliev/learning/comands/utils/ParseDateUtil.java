package com.illiaiievliev.learning.comands.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseDateUtil {

    private static final String EMPTY_DATE_STRING = "";

    private ParseDateUtil() {
    }

    public static Date parseFromStringToDateFormat(String pattern, String stringDate) throws ParseException {
        if(stringDate.equals(EMPTY_DATE_STRING)){
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(pattern);
        return format.parse(stringDate);
    }
}
