package com.illiaiievliev.learning.dao.departmentDAO.impl;

import com.illiaiievliev.learning.dao.JdbcTemplate;
import com.illiaiievliev.learning.dao.departmentDAO.interfaces.DepartmentDAO;
import com.illiaiievliev.learning.dao.exceptions.DAOException;
import com.illiaiievliev.learning.dao.mapper.impl.DepartmentDataMapperImpl;
import com.illiaiievliev.learning.dao.mapper.interfaces.DataMapper;
import com.illiaiievliev.learning.dao.utils.ConnectionToMySQL;
import com.illiaiievliev.learning.dao.utils.FieldsToArrayUtil;
import com.illiaiievliev.learning.models.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

public class DepartmentDAOImpl extends JdbcTemplate<Department> implements DepartmentDAO {
    private static final String SQL_QUERY_SAVE_DEPARTMENT = "INSERT INTO departments VALUES (NULL, ?)";
    private static final String SQL_QUERY_DELETE_DEPARTMENT = "DELETE FROM departments WHERE department_id = ?";
    private static final String SQL_QUERY_UPDATE_DEPARTMENT = "UPDATE departments SET department_name = ? WHERE department_id = ?";
    private static final String SQL_QUERY_DEPARTMENT_FIND_BY_NAME = "SELECT * FROM departments WHERE department_name = ?";
    private static final String SQL_QUERY_DEPARTMENT_FIND_ALL = "SELECT * FROM departments";
    private static final String SQL_QUERY_DEPARTMENT_FIND_BY_ID = "SELECT * FROM departments WHERE department_id = ?";
    private static final String EXCEPTION_OCCURRED_IN_FIND_BY_NAME = "there is exception occurred in findByName";
    private static final Logger log = LogManager.getLogger(DepartmentDAOImpl.class);
    private final Connection conn = new ConnectionToMySQL().establishConnection();
    private final DataMapper<Department> departmentDataMapper = new DepartmentDataMapperImpl();

    @Override
    public List<Department> findAll() throws DAOException {
        return templateFindAll(SQL_QUERY_DEPARTMENT_FIND_ALL, departmentDataMapper);
    }

    @Override
    public Department findById(int id) throws DAOException {
        return templateFindById(id, SQL_QUERY_DEPARTMENT_FIND_BY_ID, departmentDataMapper);
    }

    @Override
    public Department findByName(String name) throws DAOException {
        ResultSet resultSet;
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL_QUERY_DEPARTMENT_FIND_BY_NAME)) {
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return departmentDataMapper.createObjectFromResultSet(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_OCCURRED_IN_FIND_BY_NAME, e);
            throw new DAOException(EXCEPTION_OCCURRED_IN_FIND_BY_NAME, e);
        }
    }

    @Override
    public void update(Department department) throws DAOException {
        Object[] args = FieldsToArrayUtil.getDepartmentFieldsToArrayWithId(department);
        templateUpdate(SQL_QUERY_UPDATE_DEPARTMENT, args);
    }

    @Override
    public void delete(int id) throws DAOException {
        templateDelete(id, SQL_QUERY_DELETE_DEPARTMENT);
    }

    @Override
    public void save(Department department) throws DAOException {
        Object[] args = FieldsToArrayUtil.getDepartmentFieldsToArrayWithoutId(department);
        templateSave(SQL_QUERY_SAVE_DEPARTMENT, args);
    }
}
