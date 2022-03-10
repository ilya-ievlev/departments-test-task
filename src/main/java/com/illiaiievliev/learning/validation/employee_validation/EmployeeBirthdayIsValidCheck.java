package com.illiaiievliev.learning.validation.employee_validation;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class EmployeeBirthdayIsValidCheck extends AbstractAnnotationCheck<EmployeeBirthdayIsValid> {

    private static final int MAX_AGE = 80;
    private static final int MIN_AGE = 18;

    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate, OValContext context, Validator validator) throws OValException {
        if (valueToValidate instanceof Date) {
            LocalDate validatedDate = convertToLocalDateFromInstant((Date) valueToValidate);
            LocalDate localDate = LocalDate.now();
            LocalDate maxAge = localDate.minusYears(MAX_AGE);
            LocalDate minAge = localDate.minusYears(MIN_AGE);
            return !validatedDate.isBefore(maxAge) && !validatedDate.isAfter(minAge);
        } else {
            return false;
        }
    }

    private LocalDate convertToLocalDateFromInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
