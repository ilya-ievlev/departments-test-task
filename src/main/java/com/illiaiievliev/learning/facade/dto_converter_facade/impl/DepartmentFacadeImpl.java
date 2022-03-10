package com.illiaiievliev.learning.facade.dto_converter_facade.impl;

import com.illiaiievliev.learning.converter.DtoConverter;
import com.illiaiievliev.learning.dto.DepartmentDto;
import com.illiaiievliev.learning.facade.dto_converter_facade.interfaces.DepartmentFacade;
import com.illiaiievliev.learning.models.Department;
import com.illiaiievliev.learning.service.department_service.interfaces.DepartmentService;
import com.illiaiievliev.learning.service.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentFacadeImpl implements DepartmentFacade {
    private static final Logger log = LogManager.getLogger(DepartmentFacadeImpl.class);
    private final DtoConverter<DepartmentDto, Department> departmentDtoConverter;
    private final DepartmentService departmentService;

    public DepartmentFacadeImpl(DtoConverter<DepartmentDto, Department> departmentDtoConverter, DepartmentService departmentService) {
        this.departmentDtoConverter = departmentDtoConverter;
        this.departmentService = departmentService;
    }

    @Override
    public DepartmentDto getById(int departmentId) throws ServiceException {
        Department department = departmentService.getById(departmentId);
        return departmentDtoConverter.convertEntityToDto(department);
    }

    @Override
    public void save(DepartmentDto departmentDto) throws ServiceException {
        Department department = departmentDtoConverter.convertDtoToEntity(departmentDto);
        log.debug("save department to DB: " + department.toString());
        departmentService.save(department);
    }

    @Override
    public void update(DepartmentDto departmentDto) throws ServiceException {
        Department department = departmentDtoConverter.convertDtoToEntity(departmentDto);
        log.debug("update department to DB" + department.toString());
        departmentService.update(department);
    }

    @Override
    public List<DepartmentDto> getListOfDepartmentDto() throws ServiceException {
        List<Department> departmentList = departmentService.getAll();
        return departmentList.stream().map(departmentDtoConverter::convertEntityToDto).collect(Collectors.toList());
    }
}
