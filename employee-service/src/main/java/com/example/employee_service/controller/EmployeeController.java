package com.example.employee_service.controller;

import com.example.employee_service.dto.APIResponseDto;
import com.example.employee_service.dto.EmployeeeDto;
import com.example.employee_service.entity.Employee;
import com.example.employee_service.repository.EmployeeRepository;
import com.example.employee_service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("save")
    public ResponseEntity<EmployeeeDto> saveEmployeeData(@RequestBody EmployeeeDto employeeeDto){
        Employee emp = employeeService.saveEmployeeData(employeeeDto);
        EmployeeeDto resEmp = new EmployeeeDto(emp.getId(),
                emp.getFirstName(),
                emp.getLastName(),
                emp.getEmail(),
                emp.getDepartmentCode());
        return new ResponseEntity<>(resEmp,HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getEmployeeData(@PathVariable("id") Long employeeeId){
        APIResponseDto apiResponseDto = employeeService.getEmployeeById(employeeeId);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }


}
