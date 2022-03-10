package com.illiaiievliev.learning.converter;

public interface DtoConverter<T, E> {
    //T - DTO
    //E - Entity
    T convertEntityToDto(E e);

    E convertDtoToEntity(T t);
}
