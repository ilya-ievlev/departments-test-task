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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditDepartmentCommand extends AbstractCommand {
    private static final Logger log = LogManager.getLogger(EditDepartmentCommand.class);
    private static final String EDIT_EMPLOYEE_JSP_PATH = "/WEB-INF/views/editDepartment.jsp";
    private static final String ORIGINAL_DEPARTMENT = "originalDepartment";

    public EditDepartmentCommand(DepartmentService departmentService, EmployeeService employeeService, Validator validator, DepartmentFacade departmentFacade, EmployeeFacade employeeFacade) {
        super(departmentService, employeeService, validator, departmentFacade, employeeFacade);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException {
        String departmentStringId = request.getParameter(DEPARTMENT_ID);
        int departmentId = Integer.parseInt(departmentStringId);
        log.debug("departmentId form EditDepartment: " + departmentId);

        DepartmentDto departmentDto = departmentFacade.getById(departmentId);
        DepartmentDtoFromRequest.updateDepartmentDtoFromRequest(request, departmentDto);
        List<ConstraintViolation> constraintViolationList = validator.validate(departmentDto);

        if (constraintViolationList.isEmpty()) {
            departmentFacade.update(departmentDto);
            response.sendRedirect(GET_LIST_OF_DEPARTMENTS_LINK);
        } else {
            DepartmentDto originalDepartmentDto = departmentFacade.getById(departmentId);
            request.setAttribute(ORIGINAL_DEPARTMENT, originalDepartmentDto);
            request.setAttribute(CONSTRAINT_VIOLATION_LIST, constraintViolationList);
            request.setAttribute(DEPARTMENT, departmentDto);
            forwardRequest(EDIT_EMPLOYEE_JSP_PATH, request, response);
        }
    }
}
