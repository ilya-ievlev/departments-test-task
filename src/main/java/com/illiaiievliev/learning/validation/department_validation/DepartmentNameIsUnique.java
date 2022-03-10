package com.illiaiievliev.learning.validation.department_validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
@net.sf.oval.configuration.annotation.Constraint(checkWith = DepartmentNameIsUniqueCheck.class)
public @interface DepartmentNameIsUnique {
}
