package com.illiaiievliev.learning.dao;

import com.illiaiievliev.learning.dao.exceptions.DAOException;
import com.illiaiievliev.learning.dao.exceptions.NoSuchElementDAOException;
import com.illiaiievliev.learning.dao.mapper.interfaces.DataMapper;
import com.illiaiievliev.learning.dao.utils.ConnectionToMySQL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public abstract class JdbcTemplate<T> {
    private static final String SQL_EXCEPTION_OCCURRED_IN_TEMPLATE_FIND_ALL = "SQL exception occurred in templateFindAll";
    private static final String SQL_EXCEPTION_OCCURRED_IN_TEMPLATE_FIND_BY_ID = "SQL exception occurred in templateFindById";
    private static final String THERE_IS_NO_ELEMENT_WITH_THIS_ID = "there is no element with this id";
    private static final String SQL_EXCEPTION_OCCURRED_IN_TEMPLATE_UPDATE = "SQL exception occurred in templateUpdate";
    private static final String SQL_EXCEPTION_OCCURRED_IN_TEMPLATE_DELETE = "SQL exception occurred in templateDelete";
    private static final String SQL_EXCEPTION_OCCURRED_IN_TEMPLATE_SAVE = "SQL exception occurred in templateSave";
    private final Connection conn = new ConnectionToMySQL().establishConnection();
    private static final Logger log = LogManager.getLogger(JdbcTemplate.class);

    public List<T> templateFindAll(String sqlQuery, DataMapper<T> dataMapper) throws DAOException {
        ResultSet resultSet;
        List<T> elementList = new LinkedList<>();
        try (Statement stmt = conn.createStatement()) {
            resultSet = stmt.executeQuery(sqlQuery);
            while (resultSet.next()) {
                elementList.add(dataMapper.createObjectFromResultSet(resultSet));
            }
            return elementList; //if there is no elements in db there will be empty elementList
        } catch (SQLException e) {
            log.error(SQL_EXCEPTION_OCCURRED_IN_TEMPLATE_FIND_ALL, e);
            throw new DAOException(SQL_EXCEPTION_OCCURRED_IN_TEMPLATE_FIND_ALL + e);
        }
    }

    public T templateFindById(int id, String sqlQuery, DataMapper<T> dataMapper) throws DAOException {
        ResultSet resultSet;
        try (PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return dataMapper.createObjectFromResultSet(resultSet);
            } else {
                log.warn(THERE_IS_NO_ELEMENT_WITH_THIS_ID);
                throw new NoSuchElementDAOException(THERE_IS_NO_ELEMENT_WITH_THIS_ID);
            }
        } catch (SQLException e) {
            log.error(SQL_EXCEPTION_OCCURRED_IN_TEMPLATE_FIND_BY_ID, e);
            throw new DAOException(SQL_EXCEPTION_OCCURRED_IN_TEMPLATE_FIND_BY_ID, e);
        }
    }

    public void templateUpdate(String sqlQuery, Object[] args) throws DAOException {
        try (PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery)) {
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(SQL_EXCEPTION_OCCURRED_IN_TEMPLATE_UPDATE, e);
            throw new DAOException(SQL_EXCEPTION_OCCURRED_IN_TEMPLATE_UPDATE, e);
        }
    }

    public void templateDelete(int id, String sqlQuery) throws DAOException {
        try (PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(SQL_EXCEPTION_OCCURRED_IN_TEMPLATE_DELETE, e);
            throw new DAOException(SQL_EXCEPTION_OCCURRED_IN_TEMPLATE_DELETE, e);
        }
    }

    public void templateSave(String sqlQuery, Object[] args) throws DAOException {
        try (PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery)) {
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(SQL_EXCEPTION_OCCURRED_IN_TEMPLATE_SAVE, e);
            throw new DAOException(SQL_EXCEPTION_OCCURRED_IN_TEMPLATE_SAVE, e);
        }
    }
}


