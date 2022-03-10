package com.illiaiievliev.learning.comands.utils;

import com.illiaiievliev.learning.dto.EmployeeDto;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;

public class EmployeeDtoFromRequest {

    private static final String DEPARTMENT_ID = "departmentId";
    private static final String EMPLOYEE_NAME = "employeeName";
    private static final String EMPLOYEE_POSITION = "employeePosition";
    private static final String EMPLOYEE_BIRTHDAY = "employeeBirthday";
    private static final String EMPLOYEE_EMAIL = "employeeEmail";
    private static final String EMPLOYEE_NEW_NAME = "employeeNewName";

    public static EmployeeDto createEmployeeDtoFromRequest(HttpServletRequest request) throws ParseException {
        int departmentId = Integer.parseInt(request.getParameter(DEPARTMENT_ID));
        String employeeName = request.getParameter(EMPLOYEE_NAME);
        String position = request.getParameter(EMPLOYEE_POSITION);
        String birthdayString = request.getParameter(EMPLOYEE_BIRTHDAY);
        Date birthday = ParseDateUtil.parseFromStringToDateFormat("yyyy-MM-dd", birthdayString);
        String email = request.getParameter(EMPLOYEE_EMAIL);
        return new EmployeeDto(employeeName, position, birthday, email, departmentId);
    }

    public static void updateEmployeeDtoFromRequest(HttpServletRequest request, EmployeeDto employeeDto) throws ParseException {
        employeeDto.setEmployeeName(request.getParameter(EMPLOYEE_NEW_NAME));
        employeeDto.setPosition(request.getParameter(EMPLOYEE_POSITION));
        String birthdayString = request.getParameter(EMPLOYEE_BIRTHDAY);
        Date birthday = ParseDateUtil.parseFromStringToDateFormat("yyyy-MM-dd", birthdayString);
        employeeDto.setBirthday(birthday);
        employeeDto.setEmail(request.getParameter(EMPLOYEE_EMAIL));
    }

    private EmployeeDtoFromRequest() {
    }
}
