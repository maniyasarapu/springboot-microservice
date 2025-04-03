package com.example.employee_service.service;

import com.example.employee_service.dto.APIResponseDto;
import com.example.employee_service.dto.EmployeeeDto;
import com.example.employee_service.entity.Employee;

public interface EmployeeService {

    Employee saveEmployeeData(EmployeeeDto employeeeDto);

    APIResponseDto getEmployeeById(Long employeeId);
}
