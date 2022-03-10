package com.illiaiievliev.learning.comands.impl.department.go_to_actions;

import com.illiaiievliev.learning.comands.AbstractCommand;
import com.illiaiievliev.learning.facade.dto_converter_facade.interfaces.DepartmentFacade;
import com.illiaiievliev.learning.facade.dto_converter_facade.interfaces.EmployeeFacade;
import com.illiaiievliev.learning.service.department_service.interfaces.DepartmentService;
import com.illiaiievliev.learning.service.employee_service.interfaces.EmployeeService;
import net.sf.oval.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GotoAddDepartment extends AbstractCommand {
    private static final String ADD_DEPARTMENT_JSP_PATH = "/WEB-INF/views/addDepartment.jsp";

    public GotoAddDepartment(DepartmentService departmentService, EmployeeService employeeService, Validator validator, DepartmentFacade departmentFacade, EmployeeFacade employeeFacade) {
        super(departmentService, employeeService, validator, departmentFacade, employeeFacade);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardRequest(ADD_DEPARTMENT_JSP_PATH, request, response);
    }
}
