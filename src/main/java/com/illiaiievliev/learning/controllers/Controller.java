package com.illiaiievliev.learning.controllers;

import com.illiaiievliev.learning.comands.AbstractCommand;
import com.illiaiievliev.learning.comands.impl.error_page.GotoDefaultErrorAbstractCommand;
import com.illiaiievliev.learning.comands.impl.department.actions.DeleteDepartmentCommand;
import com.illiaiievliev.learning.comands.impl.department.actions.EditDepartmentCommand;
import com.illiaiievliev.learning.comands.impl.department.actions.GetListOfDepartmentsCommand;
import com.illiaiievliev.learning.comands.impl.department.actions.SaveDepartmentCommand;
import com.illiaiievliev.learning.comands.impl.department.go_to_actions.GotoAddDepartment;
import com.illiaiievliev.learning.comands.impl.department.go_to_actions.GotoEditDepartmentCommand;
import com.illiaiievliev.learning.comands.impl.employee.actions.DeleteEmployeeCommand;
import com.illiaiievliev.learning.comands.impl.employee.actions.EditEmployeeCommand;
import com.illiaiievliev.learning.comands.impl.employee.actions.GetEmployeesFromDepartmentCommand;
import com.illiaiievliev.learning.comands.impl.employee.actions.SaveEmployeeCommand;
import com.illiaiievliev.learning.comands.impl.employee.go_to_actions.GotoAddEmployeeCommand;
import com.illiaiievliev.learning.comands.impl.employee.go_to_actions.GotoEditEmployeeCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Controller extends HttpServlet {
    private static final Map<String, AbstractCommand> STRING_COMMAND_MAP = fillTheCommandMap();
    private final GotoDefaultErrorAbstractCommand gotoDefaultErrorCommand = new GotoDefaultErrorAbstractCommand();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            STRING_COMMAND_MAP.getOrDefault(request.getRequestURI(), gotoDefaultErrorCommand).execute(request, response);
        } catch (Exception e) {
            gotoDefaultErrorCommand.execute(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    private static Map<String, AbstractCommand> fillTheCommandMap() {
        Map<String, AbstractCommand> stringCommandMap = new HashMap<>();

        stringCommandMap.put("/getEmployeeList", new GetEmployeesFromDepartmentCommand());
        stringCommandMap.put("/editDepartment", new EditDepartmentCommand());
        stringCommandMap.put("/gotoEditDepartment", new GotoEditDepartmentCommand());
        stringCommandMap.put("/deleteDepartment", new DeleteDepartmentCommand());
        stringCommandMap.put("/addDepartment", new SaveDepartmentCommand());
        stringCommandMap.put("/gotoAddDepartment", new GotoAddDepartment());
        stringCommandMap.put("/deleteEmployee", new DeleteEmployeeCommand());
        stringCommandMap.put("/gotoEditEmployee", new GotoEditEmployeeCommand());
        stringCommandMap.put("/editEmployee", new EditEmployeeCommand());
        stringCommandMap.put("/addEmployee", new SaveEmployeeCommand());
        stringCommandMap.put("/gotoAddEmployee", new GotoAddEmployeeCommand());
        stringCommandMap.put("/getListOfDepartments", new GetListOfDepartmentsCommand());
        stringCommandMap.put("/gotoErrorPage", new GotoDefaultErrorAbstractCommand());
        return stringCommandMap;
    }
}
