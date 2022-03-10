package com.illiaiievliev.learning.comands.impl.employee.actions;

import com.illiaiievliev.learning.comands.AbstractCommand;
import com.illiaiievliev.learning.comands.utils.EmployeeDtoFromRequest;
import com.illiaiievliev.learning.dto.DepartmentDto;
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


public class SaveEmployeeCommand extends AbstractCommand {
    private static final String ADD_EMPLOYEE_JSP_PATH = "/WEB-INF/views/addEmployee.jsp";
    private static final Logger log = LogManager.getLogger(SaveEmployeeCommand.class);

    public SaveEmployeeCommand(DepartmentService departmentService, EmployeeService employeeService, Validator validator, DepartmentFacade departmentFacade, EmployeeFacade employeeFacade) {
        super(departmentService, employeeService, validator, departmentFacade, employeeFacade);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException {
        int departmentId = getDepartmentIdFromRequest(request);
        log.debug("save employee departmentId = " + departmentId);
        try {
            EmployeeDto employeeDto = EmployeeDtoFromRequest.createEmployeeDtoFromRequest(request);
            List<ConstraintViolation> constraintViolationList = validator.validate(employeeDto);

            if (constraintViolationList.isEmpty()) {
                employeeFacade.save(employeeDto);
                response.sendRedirect(GET_EMPLOYEE_LIST_WITH_DEPARTMENT_ID_LINK + departmentId);
            } else {
                DepartmentDto departmentDto = departmentFacade.getById(departmentId);
                setMainAttributesForSaveOrEditEmployee(employeeDto, constraintViolationList, request);
                request.setAttribute(DEPARTMENT, departmentDto);
                forwardRequest(ADD_EMPLOYEE_JSP_PATH, request, response);
            }
        } catch (ParseException e) {
            response.sendRedirect(GOTO_ERROR_PAGE_LINK);
        }
    }
}
