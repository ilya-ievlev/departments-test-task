package com.illiaiievliev.learning.dao.employeeDAO.impl;

import com.illiaiievliev.learning.dao.HibernateDAOAbstract;
import com.illiaiievliev.learning.dao.employeeDAO.interfaces.EmployeeDAO;
import com.illiaiievliev.learning.dao.exceptions.DAOException;
import com.illiaiievliev.learning.models.Employee;
import org.hibernate.Query;

import java.util.List;


public class EmployeeHibernateDAOImpl extends HibernateDAOAbstract<Employee> implements EmployeeDAO {
    private static final String GET_ALL_HQL_QUERY = "FROM Employee";
    private static final String DELETE_FROM_EMPLOYEES_WHERE_EMPLOYEE_ID_HQL_QUERY = "DELETE FROM Employee WHERE employee_id = :id";
    private static final String FROM_EMPLOYEE_WHERE_DEPARTMENT_ID_HQL_QUERY = "FROM Employee WHERE department_id = :id";
    private static final String FROM_EMPLOYEE_WHERE_EMAIL_HQL_QUERY = "FROM Employee WHERE email = :email";
    private static final String EMAIL_PARAMETER_FOR_QUERY = "email";
    private static final String ID_PARAMETER_FOR_QUERY = "id";
    private final EmployeeDAO employeeDao;

    public EmployeeHibernateDAOImpl(EmployeeDAO employeeDao) {
        this.employeeDao = employeeDao;
    }

//    public EmployeeHibernateDAOImpl() {
//    }

    @Override
    public List<Employee> findAll() {
        return templateFindAll(GET_ALL_HQL_QUERY);
    }

    @Override
    public Employee findById(int id) {
        return templateFindById(Employee.class, id);
    }

    @Override
    public void update(Employee employee) throws DAOException {
        templateUpdate(employee);
    }

    @Override
    public void delete(int id) throws DAOException {
        templateDelete(DELETE_FROM_EMPLOYEES_WHERE_EMPLOYEE_ID_HQL_QUERY, id);
    }

    @Override
    public void save(Employee employee) throws DAOException {
        templateSave(employee);
    }

    @Override
    public List<Employee> findFromDepartment(int departmentId) throws DAOException {
        openCurrentSession();
        Query query = currentSession.createQuery(FROM_EMPLOYEE_WHERE_DEPARTMENT_ID_HQL_QUERY);
        query.setParameter(ID_PARAMETER_FOR_QUERY, departmentId);
        List<Employee> employeeList = (List<Employee>) query.list();
        closeCurrentSession();
        return employeeList;
    }

    @Override
    public List<String> findEmployeePositions() throws DAOException {
        return employeeDao.findEmployeePositions();
    }

    @Override
    public Employee findByEmail(String email) throws DAOException {
        openCurrentSession();
        Query query = currentSession.createQuery(FROM_EMPLOYEE_WHERE_EMAIL_HQL_QUERY);
        query.setParameter(EMAIL_PARAMETER_FOR_QUERY, email);
        if (query.list().isEmpty()) {
            return null;
        }
        Employee employee = (Employee) query.list().get(0);
        closeCurrentSession();
        return employee;
    }
}
