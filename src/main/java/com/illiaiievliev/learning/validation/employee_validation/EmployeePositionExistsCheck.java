package com.illiaiievliev.learning.validation.employee_validation;

import com.illiaiievliev.learning.service.employee_service.interfaces.EmployeeService;
import com.illiaiievliev.learning.service.exceptions.ServiceException;
import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;

import java.util.List;

public class EmployeePositionExistsCheck extends AbstractAnnotationCheck<EmployeePositionExists> {
    private final EmployeeService employeeService;

    public EmployeePositionExistsCheck(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate, OValContext context, Validator validator) throws OValException {
        try {
            List<String> employeePositionsList = employeeService.getPositions();
            return employeePositionsList.contains(valueToValidate);
        } catch (ServiceException e) {
            return false;
        }
    }
}
