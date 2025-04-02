package com.example.department_service.controller;

import com.example.department_service.dto.DepartmentDto;
import com.example.department_service.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping("/save")
    public ResponseEntity<DepartmentDto> saveDepartmentData(@RequestBody DepartmentDto departmentDto){
        DepartmentDto result = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/get-department/{code}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("code") String departmentCode){
        DepartmentDto dto = departmentService.getDepartmentDataByCode(departmentCode);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
