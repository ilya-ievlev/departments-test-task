package com.illiaiievliev.learning.comands.impl.employee.go_to_actions;

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

public class GotoEditEmployeeCommand extends AbstractCommand {
    private static final Logger log = LogManager.getLogger(GotoEditEmployeeCommand.class);
    private static final String EDIT_EMPLOYEE_JSP_PATH = "/WEB-INF/views/editEmployee.jsp";

    public GotoEditEmployeeCommand(DepartmentService departmentService, EmployeeService employeeService, Validator validator, DepartmentFacade departmentFacade, EmployeeFacade employeeFacade) {
        super(departmentService, employeeService, validator, departmentFacade, employeeFacade);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException {
        String employeeStringId = request.getParameter(EMPLOYEE_ID);
        int employeeId = Integer.parseInt(employeeStringId);
        log.debug("goto edit employee employeeId" + employeeId);
        List<String> employeePositions = employeeService.getPositions();
        EmployeeDto employeeDto = employeeFacade.getById(employeeId);
//        List<Department> departmentList = departmentService.getAll();
//        List<DepartmentDto> departmentDtoList = convertDepartmentDtoFacade.convertListOfEntitiesToDto(departmentList);
        DepartmentDto departmentDto = departmentFacade.getById(employeeDto.getDepartmentId());

        request.setAttribute(DEPARTMENT, departmentDto);
//        request.setAttribute(DEPARTMENT_LIST, departmentDtoList);
        request.setAttribute(EMPLOYEE, employeeDto);
        request.setAttribute(EMPLOYEE_POSITIONS, employeePositions);
        forwardRequest(EDIT_EMPLOYEE_JSP_PATH, request, response);
    }
}
