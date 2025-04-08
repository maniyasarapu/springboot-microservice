package com.example.employee_service.service;

import com.example.employee_service.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {

    @GetMapping("api/departments/get-department/{code}")
    DepartmentDto getDepartmentByCode(@PathVariable("code") String departmentCode);
}
