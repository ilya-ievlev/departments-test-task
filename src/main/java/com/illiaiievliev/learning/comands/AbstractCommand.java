package com.illiaiievliev.learning.comands;

import com.illiaiievliev.learning.dto.EmployeeDto;
import com.illiaiievliev.learning.facade.dto_converter_facade.interfaces.DepartmentFacade;
import com.illiaiievliev.learning.facade.dto_converter_facade.interfaces.EmployeeFacade;
import com.illiaiievliev.learning.service.department_service.interfaces.DepartmentService;
import com.illiaiievliev.learning.service.employee_service.interfaces.EmployeeService;
import com.illiaiievliev.learning.service.exceptions.ServiceException;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public abstract class AbstractCommand {
    protected static final String GET_LIST_OF_DEPARTMENTS_LINK = "/getListOfDepartments";
    protected static final String DEPARTMENT_ID = "departmentId";
    protected static final String EMPLOYEE_ID = "employeeId";
    protected static final String DEPARTMENT = "department";
    protected static final String EMPLOYEE = "employee";
    protected static final String EMPLOYEE_POSITIONS = "employeePositions";
    protected static final String CONSTRAINT_VIOLATION_LIST = "constraintViolationList";
    protected static final String DEPARTMENT_LIST = "departmentList";
    protected static final String EMPLOYEE_LIST = "employeeList";
    protected static final String GET_EMPLOYEE_LIST_WITH_DEPARTMENT_ID_LINK = "/getEmployeeList?departmentId=";
    protected static final String GOTO_ERROR_PAGE_LINK = "/gotoErrorPage";
    protected final DepartmentService departmentService;
    protected final EmployeeService employeeService;
    protected final Validator validator;
    protected final DepartmentFacade departmentFacade;
    protected final EmployeeFacade employeeFacade;

    public AbstractCommand(DepartmentService departmentService, EmployeeService employeeService, Validator validator, DepartmentFacade departmentFacade, EmployeeFacade employeeFacade) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
        this.validator = validator;
        this.departmentFacade = departmentFacade;
        this.employeeFacade = employeeFacade;
    }

    public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException;

    public void forwardRequest(String jspPath, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = request.getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
        requestDispatcher.forward(request, response);
    }

    protected void setMainAttributesForSaveOrEditEmployee(EmployeeDto employeeDto, List<ConstraintViolation>constraintViolationList, HttpServletRequest request) throws ServiceException {
        List<String> employeePositions = employeeService.getPositions();
        request.setAttribute(EMPLOYEE_POSITIONS, employeePositions);
        request.setAttribute(EMPLOYEE, employeeDto);
        request.setAttribute(CONSTRAINT_VIOLATION_LIST, constraintViolationList);
    }

    public int getDepartmentIdFromRequest(HttpServletRequest request){
        String departmentStringId = request.getParameter(DEPARTMENT_ID);
        return Integer.parseInt(departmentStringId);
    }
}

