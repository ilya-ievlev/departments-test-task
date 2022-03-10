package com.illiaiievliev.learning.comands.impl.employee.actions;

import com.illiaiievliev.learning.comands.AbstractCommand;
import com.illiaiievliev.learning.facade.dto_converter_facade.interfaces.DepartmentFacade;
import com.illiaiievliev.learning.facade.dto_converter_facade.interfaces.EmployeeFacade;
import com.illiaiievliev.learning.service.department_service.interfaces.DepartmentService;
import com.illiaiievliev.learning.service.employee_service.interfaces.EmployeeService;
import com.illiaiievliev.learning.service.exceptions.ServiceException;
import net.sf.oval.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteEmployeeCommand extends AbstractCommand {
    private static final Logger log = LogManager.getLogger(DeleteEmployeeCommand.class);

    public DeleteEmployeeCommand(DepartmentService departmentService, EmployeeService employeeService, Validator validator, DepartmentFacade departmentFacade, EmployeeFacade employeeFacade) {
        super(departmentService, employeeService, validator, departmentFacade, employeeFacade);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException {
        String employeeStringId = request.getParameter(EMPLOYEE_ID);
        int employeeId = Integer.parseInt(employeeStringId);
        log.debug("delete employee employeeId = " + employeeId);
        employeeService.delete(employeeId);
        String departmentStringId = request.getParameter(DEPARTMENT_ID);
        int departmentId = Integer.parseInt(departmentStringId);
        response.sendRedirect(GET_EMPLOYEE_LIST_WITH_DEPARTMENT_ID_LINK + departmentId);
    }
}
