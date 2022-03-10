package com.illiaiievliev.learning.dao.mapper.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataMapper<T> {
    T createObjectFromResultSet(ResultSet rs) throws SQLException;
}
