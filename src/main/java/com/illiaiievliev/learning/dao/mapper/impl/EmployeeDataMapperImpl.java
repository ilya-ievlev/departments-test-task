package com.illiaiievliev.learning.dao.mapper.impl;

import com.illiaiievliev.learning.dao.mapper.interfaces.DataMapper;
import com.illiaiievliev.learning.models.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDataMapperImpl implements DataMapper<Employee> {
    private static final String EMPLOYEE_ID = "employee_id";
    private static final String EMPLOYEE_NAME = "employee_name";
    private static final String POSITION = "position";
    private static final String BIRTHDAY = "birthday";
    private static final String EMAIL = "email";
    private static final String DEPARTMENT_ID = "department_id";

    @Override
    public Employee createObjectFromResultSet(ResultSet resultSet) throws SQLException {
        return new Employee(resultSet.getInt(EMPLOYEE_ID),
                resultSet.getString(EMPLOYEE_NAME),
                resultSet.getString(POSITION),
                resultSet.getDate(BIRTHDAY),
                resultSet.getString(EMAIL),
                resultSet.getInt(DEPARTMENT_ID));
    }
}
