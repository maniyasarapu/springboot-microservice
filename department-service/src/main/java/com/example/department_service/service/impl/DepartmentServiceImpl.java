package com.example.department_service.service.impl;

import com.example.department_service.dto.DepartmentDto;
import com.example.department_service.entity.Department;
import com.example.department_service.repository.DepartmentRepository;
import com.example.department_service.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepo;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = new Department(departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode());
        Department savedDepartmentData = departmentRepo.save(department);
        DepartmentDto savedDepartmentDto = new DepartmentDto(savedDepartmentData.getId(),
                savedDepartmentData.getDepartmentName(),
                savedDepartmentData.getDepartmentDescription(),
                savedDepartmentData.getDepartmentCode());
        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentDataByCode(String departmentCode) {
        Department department = departmentRepo.findByDepartmentCode(departmentCode);
        System.out.println("department ::"+ department);
        DepartmentDto savedDepartmentDto = new DepartmentDto(department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode());
        return savedDepartmentDto;
    }

}
