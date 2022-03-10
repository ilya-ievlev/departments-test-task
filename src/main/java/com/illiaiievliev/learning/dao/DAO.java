package com.illiaiievliev.learning.dao;

import com.illiaiievliev.learning.dao.exceptions.DAOException;

import java.util.List;

public interface DAO<T> {
    List<T> findAll() throws DAOException;

    T findById(int id) throws DAOException;

    void update(T t) throws DAOException;

    void delete(int id) throws DAOException;

    void save (T t) throws DAOException;

}
