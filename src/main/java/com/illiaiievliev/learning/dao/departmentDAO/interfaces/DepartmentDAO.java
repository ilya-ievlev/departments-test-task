package com.illiaiievliev.learning.dao.departmentDAO.interfaces;

import com.illiaiievliev.learning.dao.DAO;
import com.illiaiievliev.learning.dao.exceptions.DAOException;
import com.illiaiievliev.learning.models.Department;


public interface DepartmentDAO extends DAO<Department> {
    Department findByName(String name) throws DAOException;
}
