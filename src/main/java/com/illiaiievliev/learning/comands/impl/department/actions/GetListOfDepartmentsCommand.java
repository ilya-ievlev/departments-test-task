package com.illiaiievliev.learning.comands.impl.department.actions;

import com.illiaiievliev.learning.comands.AbstractCommand;
import com.illiaiievliev.learning.dto.DepartmentDto;
import com.illiaiievliev.learning.facade.dto_converter_facade.interfaces.DepartmentFacade;
import com.illiaiievliev.learning.facade.dto_converter_facade.interfaces.EmployeeFacade;
import com.illiaiievliev.learning.service.department_service.interfaces.DepartmentService;
import com.illiaiievliev.learning.service.employee_service.interfaces.EmployeeService;
import com.illiaiievliev.learning.service.exceptions.ServiceException;
import net.sf.oval.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetListOfDepartmentsCommand extends AbstractCommand {
    private static final String LIST_OF_DEPARTMENTS_JSP_PATH = "/WEB-INF/views/departmentList.jsp";

    public GetListOfDepartmentsCommand(DepartmentService departmentService, EmployeeService employeeService, Validator validator, DepartmentFacade departmentFacade, EmployeeFacade employeeFacade) {
        super(departmentService, employeeService, validator, departmentFacade, employeeFacade);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException {
        List<DepartmentDto> departmentDtoList = departmentFacade.getListOfDepartmentDto();
        request.setAttribute(DEPARTMENT_LIST, departmentDtoList);
        forwardRequest(LIST_OF_DEPARTMENTS_JSP_PATH, request, response);
    }
}
