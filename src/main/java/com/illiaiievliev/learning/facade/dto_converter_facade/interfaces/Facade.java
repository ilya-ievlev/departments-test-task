package com.illiaiievliev.learning.facade.dto_converter_facade.interfaces;

import com.illiaiievliev.learning.service.exceptions.ServiceException;

public interface Facade<T, E> {
    //T - DTO
    //E - Entity
    T getById(int id) throws ServiceException;
    void save(T t) throws ServiceException;
    void update(T t) throws ServiceException;
}

