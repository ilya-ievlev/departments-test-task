package com.illiaiievliev.learning.dto;

import com.illiaiievliev.learning.validation.employee_validation.EmployeeBirthdayIsValid;
import com.illiaiievliev.learning.validation.employee_validation.EmployeeEmailIsUnique;
import com.illiaiievliev.learning.validation.employee_validation.EmployeePositionExists;
import com.illiaiievliev.learning.validation.employee_validation.EmployeesDepartmentIdExists;
import net.sf.oval.constraint.Email;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Size;

import java.util.Date;

public class EmployeeDto {

    private int employeeId;

    @Size(max=120, min = 3)
    @NotNull
    private String employeeName;

    @EmployeePositionExists
    @NotNull
    private String position;

    @EmployeeBirthdayIsValid
    @NotNull
    private Date birthday;

    @EmployeeEmailIsUnique
    @Email
    @Size(max=120, min=5)
    private String email;

    @EmployeesDepartmentIdExists
    @NotNull
    private int departmentId;

    public EmployeeDto(int employeeId, String employeeName, String position, Date birthday, String email, int departmentId) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.position = position;
        this.birthday = birthday;
        this.email = email;
        this.departmentId = departmentId;
    }

    public EmployeeDto(String employeeName, String position, Date birthday, String email, int departmentId) {
        this.employeeName = employeeName;
        this.position = position;
        this.birthday = birthday;
        this.email = email;
        this.departmentId = departmentId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
