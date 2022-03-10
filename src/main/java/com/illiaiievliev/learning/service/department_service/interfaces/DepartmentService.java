package com.illiaiievliev.learning.service.department_service.interfaces;

import com.illiaiievliev.learning.models.Department;
import com.illiaiievliev.learning.service.Service;
import com.illiaiievliev.learning.service.exceptions.ServiceException;

import java.util.List;

public interface DepartmentService extends Service<Department> {
    List<Department> getAll() throws ServiceException;

    Department getByName(String name) throws ServiceException;
}
