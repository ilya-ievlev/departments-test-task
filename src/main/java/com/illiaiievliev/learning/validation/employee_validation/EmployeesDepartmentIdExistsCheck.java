package com.illiaiievliev.learning.validation.employee_validation;

import com.illiaiievliev.learning.models.Department;
import com.illiaiievliev.learning.service.department_service.interfaces.DepartmentService;
import com.illiaiievliev.learning.service.exceptions.ServiceException;
import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;

public class EmployeesDepartmentIdExistsCheck extends AbstractAnnotationCheck<EmployeesDepartmentIdExists> {
    private final DepartmentService departmentService;

    public EmployeesDepartmentIdExistsCheck(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate, OValContext context, Validator validator) throws OValException {
        if (valueToValidate instanceof Integer) {
            try {
                Department department = departmentService.getById((Integer) valueToValidate);
                return department != null;
            } catch (ServiceException e) {
                return false;
            }
        }
        return false;
    }
}
