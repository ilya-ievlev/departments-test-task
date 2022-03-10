package com.illiaiievliev.learning.dto;

import com.illiaiievliev.learning.validation.department_validation.DepartmentNameIsUnique;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Size;

public class DepartmentDto {

    private int departmentId;
    @DepartmentNameIsUnique
    @Size(max = 120, min = 3)
    @NotNull
    private String departmentName;

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public DepartmentDto(int departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public DepartmentDto(String departmentName) {
        this.departmentName = departmentName;
    }
}
