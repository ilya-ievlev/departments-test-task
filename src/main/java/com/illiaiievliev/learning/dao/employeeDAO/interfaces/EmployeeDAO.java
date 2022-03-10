package com.illiaiievliev.learning.dao.employeeDAO.interfaces;

import com.illiaiievliev.learning.dao.DAO;
import com.illiaiievliev.learning.dao.exceptions.DAOException;
import com.illiaiievliev.learning.models.Employee;

import java.util.List;

public interface EmployeeDAO extends DAO<Employee> {
    List<Employee> findFromDepartment(int departmentId) throws DAOException;
    List<String> findEmployeePositions() throws DAOException;
    Employee findByEmail(String email) throws DAOException;
}
