package com.illiaiievliev.learning.dao.mapper.impl;

import com.illiaiievliev.learning.dao.mapper.interfaces.DataMapper;
import com.illiaiievliev.learning.models.Department;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDataMapperImpl implements DataMapper<Department> {
    private static final String DEPARTMENT_ID = "department_id";
    private static final String DEPARTMENT_NAME = "department_name";

    @Override
    public Department createObjectFromResultSet(ResultSet rs) throws SQLException {
        return new Department(rs.getInt(DEPARTMENT_ID),
                rs.getString(DEPARTMENT_NAME));
    }
}
