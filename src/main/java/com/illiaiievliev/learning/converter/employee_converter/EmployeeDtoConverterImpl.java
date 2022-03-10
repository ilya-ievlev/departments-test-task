package com.illiaiievliev.learning.converter.employee_converter;

import com.illiaiievliev.learning.converter.DtoConverter;
import com.illiaiievliev.learning.dto.EmployeeDto;
import com.illiaiievliev.learning.models.Employee;

import java.util.Date;

public class EmployeeDtoConverterImpl implements DtoConverter<EmployeeDto, Employee> {

    @Override
    public EmployeeDto convertEntityToDto(Employee employee) {
        int employeeId = employee.getEmployeeId();
        String employeeName = employee.getEmployeeName();
        String position = employee.getPosition();
        Date birthday = employee.getBirthday();
        String email = employee.getEmail();
        int departmentId = employee.getDepartmentId();
        return new EmployeeDto(employeeId, employeeName, position, birthday, email, departmentId);
    }

    @Override
    public Employee convertDtoToEntity(EmployeeDto employeeDto) {
        int employeeId = employeeDto.getEmployeeId();
        String employeeName = employeeDto.getEmployeeName();
        String position = employeeDto.getPosition();
        Date birthday = employeeDto.getBirthday();
        String email = employeeDto.getEmail();
        int departmentId = employeeDto.getDepartmentId();
        return new Employee(employeeId, employeeName, position, birthday, email, departmentId);
    }
}
