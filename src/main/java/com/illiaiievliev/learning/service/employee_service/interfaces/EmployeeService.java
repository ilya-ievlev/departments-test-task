package com.illiaiievliev.learning.service.employee_service.interfaces;

import com.illiaiievliev.learning.models.Employee;
import com.illiaiievliev.learning.service.Service;
import com.illiaiievliev.learning.service.exceptions.ServiceException;

import java.util.List;

public interface EmployeeService extends Service<Employee> {
    List<Employee> getListOfEmployeesFromDepartment(int departmentId) throws ServiceException;
    List<String> getPositions() throws ServiceException;
    Employee getEmployeeByEmail(String email) throws ServiceException;
}
