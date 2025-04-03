package com.example.employee_service.service.impl;

import com.example.employee_service.dto.EmployeeeDto;
import com.example.employee_service.entity.Employee;
import com.example.employee_service.repository.EmployeeRepository;
import com.example.employee_service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployeeData(EmployeeeDto employeeeDto) {
        Employee emp = new Employee(employeeeDto.getId(),
                employeeeDto.getFirstName(),
                employeeeDto.getLastName(),
                employeeeDto.getEmail());
        Employee savedEmployeeData = employeeRepository.save(emp);
        return savedEmployeeData;

    }

    @Override
    public EmployeeeDto getEmployeeById(Long employeeId) {
        Employee emp = employeeRepository.findById(employeeId).get();
        EmployeeeDto employeeeDto = new EmployeeeDto(emp.getId(),
                emp.getFirstName(),
                emp.getLastName(),
                emp.getEmail());
        return employeeeDto;
    }
}
