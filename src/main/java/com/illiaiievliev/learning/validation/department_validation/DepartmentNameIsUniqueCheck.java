package com.illiaiievliev.learning.validation.department_validation;

import com.illiaiievliev.learning.dto.DepartmentDto;
import com.illiaiievliev.learning.models.Department;
import com.illiaiievliev.learning.service.department_service.interfaces.DepartmentService;
import com.illiaiievliev.learning.service.exceptions.ServiceException;
import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;


public class DepartmentNameIsUniqueCheck extends AbstractAnnotationCheck<DepartmentNameIsUnique> {
private final DepartmentService departmentService;

    public DepartmentNameIsUniqueCheck(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate, OValContext oValContext, Validator validator) throws OValException {
        if(validatedObject instanceof DepartmentDto) {
            int currentDepartmentId = ((DepartmentDto) validatedObject).getDepartmentId();
            String currentDepartmentName = ((DepartmentDto) validatedObject).getDepartmentName();
            try {
                Department department = departmentService.getByName(currentDepartmentName);
                if(department == null){
                    return true;
                }
                return department.getDepartmentId() == currentDepartmentId;
            } catch (ServiceException e){
                return false;
            }
        }
        else {
            return false;
        }
    }
}
