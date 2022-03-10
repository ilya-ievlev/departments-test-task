package com.illiaiievliev.learning.comands.impl.department.actions;

import com.illiaiievliev.learning.comands.AbstractCommand;
import com.illiaiievliev.learning.comands.utils.DepartmentDtoFromRequest;
import com.illiaiievliev.learning.dto.DepartmentDto;
import com.illiaiievliev.learning.facade.dto_converter_facade.interfaces.DepartmentFacade;
import com.illiaiievliev.learning.facade.dto_converter_facade.interfaces.EmployeeFacade;
import com.illiaiievliev.learning.service.department_service.interfaces.DepartmentService;
import com.illiaiievliev.learning.service.employee_service.interfaces.EmployeeService;
import com.illiaiievliev.learning.service.exceptions.ServiceException;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class SaveDepartmentCommand extends AbstractCommand {
    private static final String ADD_DEPARTMENT_JSP_PATH = "/WEB-INF/views/addDepartment.jsp";

    public SaveDepartmentCommand(DepartmentService departmentService, EmployeeService employeeService, Validator validator, DepartmentFacade departmentFacade, EmployeeFacade employeeFacade) {
        super(departmentService, employeeService, validator, departmentFacade, employeeFacade);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {
        DepartmentDto departmentDto = DepartmentDtoFromRequest.createDepartmentDtoFromRequest(request);
        List<ConstraintViolation> constraintViolationList = validator.validate(departmentDto);
        if (constraintViolationList.isEmpty()) {
            departmentFacade.save(departmentDto);
            response.sendRedirect(GET_LIST_OF_DEPARTMENTS_LINK);
        } else {
            request.setAttribute(CONSTRAINT_VIOLATION_LIST, constraintViolationList);
            request.setAttribute(DEPARTMENT, departmentDto);
            forwardRequest(ADD_DEPARTMENT_JSP_PATH, request, response);
        }
    }
}

