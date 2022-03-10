package com.illiaiievliev.learning.validation.employee_validation;

import com.illiaiievliev.learning.validation.department_validation.DepartmentNameIsUniqueCheck;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@net.sf.oval.configuration.annotation.Constraint(checkWith = EmployeeEmailIsUniqueCheck.class)
public @interface EmployeeEmailIsUnique {
}
