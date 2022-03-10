package com.illiaiievliev.learning.validation.employee_validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@net.sf.oval.configuration.annotation.Constraint(checkWith = EmployeeBirthdayIsValidCheck.class)
public @interface EmployeeBirthdayIsValid {
}
