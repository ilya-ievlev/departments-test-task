package com.illiaiievliev.learning.comands.utils;

import com.illiaiievliev.learning.dto.DepartmentDto;

import javax.servlet.http.HttpServletRequest;


public class DepartmentDtoFromRequest {
    private static final String NEW_DEPARTMENT_NAME = "newDepartmentName";
    private static final String DEPARTMENT_NEW_NAME = "departmentNewName";

    public static DepartmentDto createDepartmentDtoFromRequest(HttpServletRequest request) {
        String departmentName = request.getParameter(NEW_DEPARTMENT_NAME);
        return new DepartmentDto(departmentName);
    }

    public static void updateDepartmentDtoFromRequest(HttpServletRequest request, DepartmentDto departmentDto) {
        departmentDto.setDepartmentName(request.getParameter(DEPARTMENT_NEW_NAME));
    }

    private DepartmentDtoFromRequest() {
    }
}
