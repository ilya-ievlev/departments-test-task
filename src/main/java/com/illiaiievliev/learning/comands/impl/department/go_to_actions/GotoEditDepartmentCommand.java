package com.illiaiievliev.learning.comands.impl.department.go_to_actions;

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

public class GotoEditDepartmentCommand extends AbstractCommand {
    private static final String EDIT_DEPARTMENT_JSP_PATH = "/WEB-INF/views/editDepartment.jsp";

    public GotoEditDepartmentCommand(DepartmentService departmentService, EmployeeService employeeService, Validator validator, DepartmentFacade departmentFacade, EmployeeFacade employeeFacade) {
        super(departmentService, employeeService, validator, departmentFacade, employeeFacade);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException {
        String departmentStringId = request.getParameter(DEPARTMENT_ID);
        int departmentId = Integer.parseInt(departmentStringId);
        DepartmentDto departmentDto = departmentFacade.getById(departmentId);
        request.setAttribute(DEPARTMENT, departmentDto);
        forwardRequest(EDIT_DEPARTMENT_JSP_PATH, request, response);
    }
}
