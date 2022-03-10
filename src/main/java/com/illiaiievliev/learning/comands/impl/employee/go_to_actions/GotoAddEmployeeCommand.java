package com.illiaiievliev.learning.comands.impl.employee.go_to_actions;

import com.illiaiievliev.learning.comands.AbstractCommand;
import com.illiaiievliev.learning.dto.DepartmentDto;
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

public class GotoAddEmployeeCommand extends AbstractCommand {
    private static final Logger log = LogManager.getLogger(GotoAddEmployeeCommand.class);
    private static final String ADD_EMPLOYEE_JSP_PATH = "/WEB-INF/views/addEmployee.jsp";

    public GotoAddEmployeeCommand(DepartmentService departmentService, EmployeeService employeeService, Validator validator, DepartmentFacade departmentFacade, EmployeeFacade employeeFacade) {
        super(departmentService, employeeService, validator, departmentFacade, employeeFacade);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException {
        String departmentStringId = request.getParameter(DEPARTMENT_ID);
        int departmentId = Integer.parseInt(departmentStringId);
        log.debug("add employee departmentId = " + departmentId);
        DepartmentDto departmentDto = departmentFacade.getById(departmentId);
        List<String> employeePositions = employeeService.getPositions();

        request.setAttribute(EMPLOYEE_POSITIONS, employeePositions);
        request.setAttribute(DEPARTMENT, departmentDto);
        forwardRequest(ADD_EMPLOYEE_JSP_PATH, request, response);
    }
}
