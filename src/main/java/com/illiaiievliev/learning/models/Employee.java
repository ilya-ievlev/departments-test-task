package com.illiaiievliev.learning.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @Column(name = "employee_id", nullable = false, updatable = false, unique = true)
    private int employeeId;
    @Column(name = "employee_name", nullable = false, length = 120)
    private String employeeName;
    @Column(name = "position", nullable = false)
    private String position;
    @Column(name = "birthday", nullable = false)
    private Date birthday;
    @Column(name = "email", nullable = false, length = 120, unique = true)
    private String email;
    @Column(name = "department_id", nullable = false)
    private int departmentId;
    @OneToOne
    private Department department;

    public Employee() {
    }

    //for inputting data to db
    public Employee(String employeeName, String position, Date birthday, String email, int departmentId) {
        this.employeeName = employeeName;
        this.position = position;
        this.birthday = birthday;
        this.email = email;
        this.departmentId = departmentId;

    }

    //for outputting data from db
    public Employee(int employeeId, String employeeName, String position, Date birthday, String email, int departmentId) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.position = position;
        this.birthday = birthday;
        this.email = email;
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", position='" + position + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", departmentId=" + departmentId +
                '}';
    }

    //there is no setEmployeeId

    public int getEmployeeId() {
        return employeeId;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
