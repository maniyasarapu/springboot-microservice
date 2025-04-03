package com.example.employee_service.dto;

import com.example.employee_service.entity.Employee;

public class APIResponseDto {
    private EmployeeeDto employeeeDto;
    private DepartmentDto departmentDto;

    public EmployeeeDto getEmployeeeDto() {
        return employeeeDto;
    }

    public void setEmployeeeDto(EmployeeeDto employeeeDto) {
        this.employeeeDto = employeeeDto;
    }

    public DepartmentDto getDepartmentDto() {
        return departmentDto;
    }

    public void setDepartmentDto(DepartmentDto departmentDto) {
        this.departmentDto = departmentDto;
    }

    public APIResponseDto(EmployeeeDto employeeeDto, DepartmentDto departmentDto) {
        this.employeeeDto = employeeeDto;
        this.departmentDto = departmentDto;
    }

    public APIResponseDto(){

    }

    @Override
    public String toString() {
        return "APIResponseDto{" +
                "employeeeDto=" + employeeeDto +
                ", departmentDto=" + departmentDto +
                '}';
    }
}
