package com.illiaiievliev.learning.facade.dto_converter_facade.interfaces;

import com.illiaiievliev.learning.dto.DepartmentDto;
import com.illiaiievliev.learning.models.Department;
import com.illiaiievliev.learning.service.exceptions.ServiceException;

import java.util.List;

public interface DepartmentFacade extends Facade<DepartmentDto, Department> {
    List<DepartmentDto> getListOfDepartmentDto() throws ServiceException;
}
