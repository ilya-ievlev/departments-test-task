package com.illiaiievliev.learning.validation.employee_validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
@net.sf.oval.configuration.annotation.Constraint(checkWith = EmployeePositionExistsCheck.class)
public @interface EmployeePositionExists {
}
