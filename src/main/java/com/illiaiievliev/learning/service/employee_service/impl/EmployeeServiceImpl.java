package com.illiaiievliev.learning.service.employee_service.impl;

import com.illiaiievliev.learning.dao.employeeDAO.interfaces.EmployeeDAO;
import com.illiaiievliev.learning.dao.exceptions.DAOException;
import com.illiaiievliev.learning.models.Employee;
import com.illiaiievliev.learning.service.employee_service.interfaces.EmployeeService;
import com.illiaiievliev.learning.service.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger log = LogManager.getLogger(EmployeeServiceImpl.class);
    private static final String EXCEPTION_OCCURRED_IN_GET_LIST_OF_EMPLOYEES_FROM_DEPARTMENT_METHOD = "exception occurred in getListOfEmployeesFromDepartment method";
    private static final String EXCEPTION_OCCURRED_IN_GET_LIST_OF_EMPLOYEE_POSITIONS = "exception occurred in getListOfEmployeePositions";
    private static final String SERVICE_EXCEPTION_OCCURRED_IN_GET_EMPLOYEE_BY_EMAIL = "service exception occurred in getEmployeeByEmail";
    private static final String SERVICE_EXCEPTION_OCCURRED_IN_SAVE = "service exception occurred in save";
    private static final String SERVICE_EXCEPTION_OCCURRED_IN_GET_BY_ID = "service exception occurred in getById";
    private static final String SERVICE_EXCEPTION_OCCURRED_IN_DELETE_METHOD = "service exception occurred in delete method";
    private static final String SERVICE_EXCEPTION_OCCURRED_IN_UPDATE_METHOD = "service exception occurred in update method";
    private final EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> getListOfEmployeesFromDepartment(int departmentId) throws ServiceException {
        try {
            return employeeDAO.findFromDepartment(departmentId);
        } catch (DAOException e) {
            log.error(EXCEPTION_OCCURRED_IN_GET_LIST_OF_EMPLOYEES_FROM_DEPARTMENT_METHOD, e);
            throw new ServiceException(EXCEPTION_OCCURRED_IN_GET_LIST_OF_EMPLOYEES_FROM_DEPARTMENT_METHOD, e);
        }
    }

    @Override
    public List<String> getPositions() throws ServiceException {
        try {
            return employeeDAO.findEmployeePositions();
        } catch (DAOException e) {
            log.error(EXCEPTION_OCCURRED_IN_GET_LIST_OF_EMPLOYEE_POSITIONS, e);
            throw new ServiceException(EXCEPTION_OCCURRED_IN_GET_LIST_OF_EMPLOYEE_POSITIONS, e);
        }
    }

    @Override
    public Employee getEmployeeByEmail(String email) throws ServiceException {
        try {
            return employeeDAO.findByEmail(email);
        } catch (DAOException e) {
            log.error(SERVICE_EXCEPTION_OCCURRED_IN_GET_EMPLOYEE_BY_EMAIL, e);
            throw new ServiceException(SERVICE_EXCEPTION_OCCURRED_IN_GET_EMPLOYEE_BY_EMAIL,e);
        }
    }

    @Override
    public void save(Employee employee) throws ServiceException {
        try {
            employeeDAO.save(employee);
        } catch (DAOException e) {
            log.error(SERVICE_EXCEPTION_OCCURRED_IN_SAVE, e);
            throw new ServiceException(SERVICE_EXCEPTION_OCCURRED_IN_SAVE, e);
        }
    }

    @Override
    public Employee getById(int id) throws ServiceException {
        try {
            return employeeDAO.findById(id);
        } catch (DAOException e) {
            log.error(SERVICE_EXCEPTION_OCCURRED_IN_GET_BY_ID, e);
            throw new ServiceException(SERVICE_EXCEPTION_OCCURRED_IN_GET_BY_ID, e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            employeeDAO.delete(id);
        } catch (DAOException e) {
            log.error(SERVICE_EXCEPTION_OCCURRED_IN_DELETE_METHOD, e);
            throw new ServiceException(SERVICE_EXCEPTION_OCCURRED_IN_DELETE_METHOD, e);
        }
    }

    @Override
    public void update(Employee employee) throws ServiceException {
        try {
            employeeDAO.update(employee);
        } catch (DAOException e) {
            log.error(SERVICE_EXCEPTION_OCCURRED_IN_UPDATE_METHOD, e);
            throw new ServiceException(SERVICE_EXCEPTION_OCCURRED_IN_UPDATE_METHOD, e);
        }
    }

    protected EmployeeDAO getEmployeeDAO() {
        return employeeDAO;
    }

}
