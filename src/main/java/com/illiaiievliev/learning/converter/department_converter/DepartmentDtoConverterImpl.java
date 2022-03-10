package com.illiaiievliev.learning.converter.department_converter;

import com.illiaiievliev.learning.converter.DtoConverter;
import com.illiaiievliev.learning.dto.DepartmentDto;
import com.illiaiievliev.learning.models.Department;

public class DepartmentDtoConverterImpl implements DtoConverter<DepartmentDto, Department> {

    @Override
    public DepartmentDto convertEntityToDto(Department department) {
        int departmentId = department.getDepartmentId();
        String departmentName = department.getDepartmentName();
        return new DepartmentDto(departmentId, departmentName);
    }

    @Override
    public Department convertDtoToEntity(DepartmentDto departmentDto){
        int departmentId = departmentDto.getDepartmentId();
        String departmentName = departmentDto.getDepartmentName();
        return new Department(departmentId, departmentName);
    }
}
