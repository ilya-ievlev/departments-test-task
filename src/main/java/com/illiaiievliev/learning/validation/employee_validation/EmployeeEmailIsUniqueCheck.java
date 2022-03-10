package com.illiaiievliev.learning.validation.employee_validation;

import com.illiaiievliev.learning.dto.EmployeeDto;
import com.illiaiievliev.learning.models.Employee;
import com.illiaiievliev.learning.service.employee_service.interfaces.EmployeeService;
import com.illiaiievliev.learning.service.exceptions.ServiceException;
import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;

public class EmployeeEmailIsUniqueCheck extends AbstractAnnotationCheck<EmployeeEmailIsUnique> {
    private final EmployeeService employeeService;

    public EmployeeEmailIsUniqueCheck(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate, OValContext context, Validator validator) throws OValException {
        if (validatedObject instanceof EmployeeDto) {
            EmployeeDto employee1 = (EmployeeDto) validatedObject;
            int currentEmployeeId = employee1.getEmployeeId();
            String currentEmployeeEmail = employee1.getEmail();
            try {
                Employee employee = employeeService.getEmployeeByEmail(currentEmployeeEmail);
                if (employee == null) {
                    return true;
                }
                return employee.getEmployeeId() == currentEmployeeId;
            } catch (ServiceException e) {
                return false;
            }
        } else {
            return false;
        }
    }
}
