package com.illiaiievliev.learning.service;

import com.illiaiievliev.learning.service.exceptions.ServiceException;

public interface Service<T> {
    void save(T t) throws ServiceException;

    T getById(int id) throws ServiceException;

    void delete(int id) throws ServiceException;

    void update(T t) throws ServiceException;

}
