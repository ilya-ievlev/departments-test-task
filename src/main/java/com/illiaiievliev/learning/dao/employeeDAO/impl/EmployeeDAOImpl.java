package com.illiaiievliev.learning.dao.employeeDAO.impl;

import com.illiaiievliev.learning.dao.JdbcTemplate;
import com.illiaiievliev.learning.dao.employeeDAO.interfaces.EmployeeDAO;
import com.illiaiievliev.learning.dao.exceptions.DAOException;
import com.illiaiievliev.learning.dao.mapper.impl.EmployeeDataMapperImpl;
import com.illiaiievliev.learning.dao.utils.ConnectionToMySQL;
import com.illiaiievliev.learning.dao.utils.FieldsToArrayUtil;
import com.illiaiievliev.learning.models.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeDAOImpl extends JdbcTemplate<Employee> implements EmployeeDAO {
    private static final String SQL_QUERY_SAVE_EMPLOYEE = "INSERT INTO employees VALUES (NULL, ?, ?, ?, ?, ?)";
    private static final String SQL_QUERY_EMPLOYEE_FIND_ALL = "SELECT * FROM employees";
    private static final String SQL_QUERY_EMPLOYEE_FIND_BY_ID = "SELECT * FROM employees WHERE employee_id = ?";
    private static final String SQL_QUERY_UPDATE_EMPLOYEE = "UPDATE employees SET employee_name = ?, position = ?, birthday = ?, email = ?, department_id = ? WHERE employee_id = ?";
    private static final String SQL_QUERY_DELETE_EMPLOYEE = "DELETE FROM employees WHERE employee_id = ?";
    private static final String SQL_QUERY_GET_EMPLOYEES_FROM_DEPARTMENT = "SELECT * FROM employees WHERE department_id = ?";
    private static final String SQL_QUERY_GET_EMPLOYEE_POSITIONS = "SHOW COLUMNS FROM employees WHERE FIELD = 'position'";
    private static final Pattern pattern = Pattern.compile("'(\\w+)'");
    private static final String SQL_QUERY_FIND_EMPLOYEE_BY_EMAIL = "SELECT * FROM employees WHERE email = ?";
    private static final Logger log = LogManager.getLogger(EmployeeDAOImpl.class);
    private static final String SQL_EXCEPTION_OCCURRED_IN_FIND_EMPLOYEES_FROM_DEPARTMENT = "SQL exception occurred in findEmployeesFromDepartment";
    private static final String SQL_EXCEPTION_OCCURRED_IN_FIND_EMPLOYEE_BY_EMAIL = "SQL exception occurred in findEmployeeByEmail";
    private static final String SQL_EXCEPTION_OCCURRED_IN_FIND_EMPLOYEE_POSITIONS = "SQL exception occurred in findEmployeePositions";
    private final Connection conn = new ConnectionToMySQL().establishConnection();
    private final EmployeeDataMapperImpl employeeDataMapper = new EmployeeDataMapperImpl();


    @Override
    public List<Employee> findAll() throws DAOException {
        return templateFindAll(SQL_QUERY_EMPLOYEE_FIND_ALL, employeeDataMapper);
    }

    @Override
    public Employee findById(int id) throws DAOException {
        return templateFindById(id, SQL_QUERY_EMPLOYEE_FIND_BY_ID, employeeDataMapper);
    }

    @Override
    public void update(Employee employee) throws DAOException {
        Object[] args = FieldsToArrayUtil.getEmployeeFieldsToArrayWithId(employee);
        templateUpdate(SQL_QUERY_UPDATE_EMPLOYEE, args);
    }

    @Override
    public void delete(int id) throws DAOException {
        templateDelete(id, SQL_QUERY_DELETE_EMPLOYEE);
    }

    @Override
    public void save(Employee employee) throws DAOException {
        Object[] args = FieldsToArrayUtil.getEmployeeFieldsToArrayWithoutId(employee);
        templateSave(SQL_QUERY_SAVE_EMPLOYEE, args);
    }

    @Override
    public List<Employee> findFromDepartment(int departmentId) throws DAOException {
        ResultSet resultSet;
        List<Employee> employeeList = new LinkedList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL_QUERY_GET_EMPLOYEES_FROM_DEPARTMENT)) {
            preparedStatement.setInt(1, departmentId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeList.add(employeeDataMapper.createObjectFromResultSet(resultSet));
            }
            return employeeList;
        } catch (SQLException e) {
            log.error(SQL_EXCEPTION_OCCURRED_IN_FIND_EMPLOYEES_FROM_DEPARTMENT, e);
            throw new DAOException(SQL_EXCEPTION_OCCURRED_IN_FIND_EMPLOYEES_FROM_DEPARTMENT, e);
        }
    }

    @Override
    public Employee findByEmail(String email) throws DAOException {
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL_QUERY_FIND_EMPLOYEE_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return employeeDataMapper.createObjectFromResultSet(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            log.error(SQL_EXCEPTION_OCCURRED_IN_FIND_EMPLOYEE_BY_EMAIL, e);
            throw new DAOException(SQL_EXCEPTION_OCCURRED_IN_FIND_EMPLOYEE_BY_EMAIL, e);
        }
    }

    @Override
    public List<String> findEmployeePositions() throws DAOException {
        List<String> employeePosition = new LinkedList<>();
        ResultSet resultSet;
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL_QUERY_GET_EMPLOYEE_POSITIONS)) {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String typeString = resultSet.getString("Type");
                Matcher matcher = pattern.matcher(typeString);
                while (matcher.find()) {
                    employeePosition.add(typeString.substring(matcher.start(), matcher.end()).replace("'", ""));
                }
            }
            return employeePosition;
        } catch (SQLException e) {
            log.error(SQL_EXCEPTION_OCCURRED_IN_FIND_EMPLOYEE_POSITIONS, e);
            throw new DAOException(SQL_EXCEPTION_OCCURRED_IN_FIND_EMPLOYEE_POSITIONS, e);
        }
    }
}
