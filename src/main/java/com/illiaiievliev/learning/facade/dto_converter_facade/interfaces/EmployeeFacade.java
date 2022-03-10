package com.illiaiievliev.learning.facade.dto_converter_facade.interfaces;

import com.illiaiievliev.learning.dto.EmployeeDto;
import com.illiaiievliev.learning.models.Employee;
import com.illiaiievliev.learning.service.exceptions.ServiceException;

import java.util.List;

public interface EmployeeFacade extends Facade<EmployeeDto, Employee> {
    List<EmployeeDto> getListOfEmployeeDtoFromDepartment(int departmentId) throws ServiceException;
}
