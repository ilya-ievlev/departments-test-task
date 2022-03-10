package com.illiaiievliev.learning.facade.dto_converter_facade.impl;

import com.illiaiievliev.learning.converter.DtoConverter;
import com.illiaiievliev.learning.dto.EmployeeDto;
import com.illiaiievliev.learning.facade.dto_converter_facade.interfaces.EmployeeFacade;
import com.illiaiievliev.learning.models.Employee;
import com.illiaiievliev.learning.service.employee_service.interfaces.EmployeeService;
import com.illiaiievliev.learning.service.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeFacadeImpl implements EmployeeFacade {
    private static final Logger log = LogManager.getLogger(EmployeeFacadeImpl.class);
    private final DtoConverter<EmployeeDto, Employee> employeeDtoConverter;
    private final EmployeeService employeeService;

    public EmployeeFacadeImpl(DtoConverter<EmployeeDto, Employee> employeeDtoConverter, EmployeeService employeeService) {
        this.employeeDtoConverter = employeeDtoConverter;
        this.employeeService = employeeService;
    }

    @Override
    public EmployeeDto getById(int id) throws ServiceException {
        Employee employee = employeeService.getById(id);
        return employeeDtoConverter.convertEntityToDto(employee);
    }

    @Override
    public void save(EmployeeDto employeeDto) throws ServiceException {
        Employee employee = employeeDtoConverter.convertDtoToEntity(employeeDto);
        log.debug("save employee to DB " + employee.toString());
        employeeService.save(employee);
    }

    @Override
    public void update(EmployeeDto employeeDto) throws ServiceException {
        Employee employee = employeeDtoConverter.convertDtoToEntity(employeeDto);
        log.debug("update employee to DB " + employee.toString());
        employeeService.update(employee);
    }

    @Override
    public List<EmployeeDto> getListOfEmployeeDtoFromDepartment(int departmentId) throws ServiceException {
        List<Employee> employeeList = employeeService.getListOfEmployeesFromDepartment(departmentId);
        return employeeList.stream().map(employeeDtoConverter::convertEntityToDto).collect(Collectors.toList());
    }
}
