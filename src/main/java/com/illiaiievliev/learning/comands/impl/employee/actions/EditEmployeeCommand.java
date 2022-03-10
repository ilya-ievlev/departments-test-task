package com.illiaiievliev.learning.comands.impl.employee.actions;

import com.illiaiievliev.learning.comands.AbstractCommand;
import com.illiaiievliev.learning.comands.utils.EmployeeDtoFromRequest;
import com.illiaiievliev.learning.dto.EmployeeDto;
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
import java.text.ParseException;
import java.util.List;


public class EditEmployeeCommand extends AbstractCommand {
    private static final Logger log = LogManager.getLogger(EditEmployeeCommand.class);
    private static final String ORIGINAL_EMPLOYEE = "originalEmployee";
    private static final String EDIT_EMPLOYEE_JSP_PATH = "/WEB-INF/views/editEmployee.jsp";

    public EditEmployeeCommand(DepartmentService departmentService, EmployeeService employeeService, Validator validator, DepartmentFacade departmentFacade, EmployeeFacade employeeFacade) {
        super(departmentService, employeeService, validator, departmentFacade, employeeFacade);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String employeeStringId = request.getParameter(EMPLOYEE_ID);
        int employeeId = Integer.parseInt(employeeStringId);
        log.debug("edit employee employeeId = " + employeeId);
        EmployeeDto employeeDto = employeeFacade.getById(employeeId);
        try {
            EmployeeDtoFromRequest.updateEmployeeDtoFromRequest(request, employeeDto);
        } catch (ParseException e) {
            response.sendRedirect(GOTO_ERROR_PAGE_LINK);
        }
        List<ConstraintViolation> constraintViolationList = validator.validate(employeeDto);
        if (constraintViolationList.isEmpty()) {
            employeeFacade.update(employeeDto);
            response.sendRedirect(GET_EMPLOYEE_LIST_WITH_DEPARTMENT_ID_LINK + employeeDto.getDepartmentId());
        } else {
            EmployeeDto originalEmployeeDto = employeeFacade.getById(employeeId);
            request.setAttribute(ORIGINAL_EMPLOYEE, originalEmployeeDto);
            setMainAttributesForSaveOrEditEmployee(employeeDto, constraintViolationList, request);
            forwardRequest(EDIT_EMPLOYEE_JSP_PATH, request, response);
        }
    }
}
