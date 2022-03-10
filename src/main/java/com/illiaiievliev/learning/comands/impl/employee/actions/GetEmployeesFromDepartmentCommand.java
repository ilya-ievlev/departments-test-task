package com.illiaiievliev.learning.comands.impl.employee.actions;

import com.illiaiievliev.learning.comands.AbstractCommand;
import com.illiaiievliev.learning.dto.DepartmentDto;
import com.illiaiievliev.learning.dto.EmployeeDto;
import com.illiaiievliev.learning.facade.dto_converter_facade.interfaces.DepartmentFacade;
import com.illiaiievliev.learning.facade.dto_converter_facade.interfaces.EmployeeFacade;
import com.illiaiievliev.learning.service.department_service.interfaces.DepartmentService;
import com.illiaiievliev.learning.service.employee_service.interfaces.EmployeeService;
import com.illiaiievliev.learning.service.exceptions.ServiceException;
import net.sf.oval.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetEmployeesFromDepartmentCommand extends AbstractCommand {
    private static final Logger log = LogManager.getLogger(GetEmployeesFromDepartmentCommand.class);
    private static final String EMPLOYEE_LIST_JSP_PATH = "/WEB-INF/views/employeeList.jsp";

    public GetEmployeesFromDepartmentCommand(DepartmentService departmentService, EmployeeService employeeService, Validator validator, DepartmentFacade departmentFacade, EmployeeFacade employeeFacade) {
        super(departmentService, employeeService, validator, departmentFacade, employeeFacade);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException {
        String departmentStringId = request.getParameter(DEPARTMENT_ID);
        int departmentId = Integer.parseInt(departmentStringId);
        log.debug("get employees from department, departmentId = " + departmentId);
        DepartmentDto departmentDto = departmentFacade.getById(departmentId);
        List<EmployeeDto> employeeDtoList = employeeFacade.getListOfEmployeeDtoFromDepartment(departmentId);

        request.setAttribute(DEPARTMENT, departmentDto);
        request.setAttribute(EMPLOYEE_LIST, employeeDtoList);
        forwardRequest(EMPLOYEE_LIST_JSP_PATH, request, response);
    }
}
