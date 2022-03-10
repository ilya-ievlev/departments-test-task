package com.illiaiievliev.learning.dao.utils;

import com.illiaiievliev.learning.models.Department;
import com.illiaiievliev.learning.models.Employee;

public class FieldsToArrayUtil {
    private FieldsToArrayUtil() {
    }

    public static Object[] getEmployeeFieldsToArrayWithoutId(Employee employee) {
        Object[] args = new Object[5];
        args[0] = employee.getEmployeeName();
        args[1] = employee.getPosition();
        args[2] = convertJavaDateToSqlDate(employee.getBirthday());
        args[3] = employee.getEmail();
        args[4] = employee.getDepartmentId();
        return args;
    }

    public static Object[] getEmployeeFieldsToArrayWithId(Employee employee) {
        Object[] args = new Object[6];
        args[5] = employee.getEmployeeId();
        args[0] = employee.getEmployeeName();
        args[1] = employee.getPosition();
        args[2] = convertJavaDateToSqlDate(employee.getBirthday());
        args[3] = employee.getEmail();
        args[4] = employee.getDepartmentId();
        return args;
    }

    public static Object[] getDepartmentFieldsToArrayWithId(Department department) {
        Object[] args = new Object[2];
        args[0] = department.getDepartmentName();
        args[1] = department.getDepartmentId();
        return args;
    }

    public static Object[] getDepartmentFieldsToArrayWithoutId(Department department) {
        Object[] args = new Object[1];
        args[0] = department.getDepartmentName();
        return args;
    }

    private static java.sql.Date convertJavaDateToSqlDate(java.util.Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }
}
