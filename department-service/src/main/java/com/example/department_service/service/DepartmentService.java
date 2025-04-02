package com.example.department_service.service;

import com.example.department_service.dto.DepartmentDto;

public interface DepartmentService {

    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentDataByCode(String departmentCode);


}
