package com.example.employee_service.service.impl;

import com.example.employee_service.dto.APIResponseDto;
import com.example.employee_service.dto.DepartmentDto;
import com.example.employee_service.dto.EmployeeeDto;
import com.example.employee_service.entity.Employee;
import com.example.employee_service.repository.EmployeeRepository;
import com.example.employee_service.service.APIClient;
import com.example.employee_service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

//    @Autowired
//    private RestTemplate restTemplate;

//    @Autowired
//    private WebClient webClient;

    @Autowired
    private APIClient apiClient;

    @Override
    public Employee saveEmployeeData(EmployeeeDto employeeeDto) {
        Employee emp = new Employee(employeeeDto.getId(),
                employeeeDto.getFirstName(),
                employeeeDto.getLastName(),
                employeeeDto.getEmail(),
                employeeeDto.getDepartmentCode());
        Employee savedEmployeeData = employeeRepository.save(emp);
        return savedEmployeeData;

    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {
        Employee emp = employeeRepository.findById(employeeId).get();

       // ResponseEntity<DepartmentDto> responseEntity =restTemplate.getForEntity("http://localhost:8080/api/departments/get-department/"+emp.getDepartmentCode(), DepartmentDto.class);
       // DepartmentDto departmentDto = responseEntity.getBody();

//        DepartmentDto departmentDto = webClient.get().uri("http://localhost:8080/api/departments/get-department/"+emp.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();

       DepartmentDto departmentDto = apiClient.getDepartmentByCode(emp.getDepartmentCode());

        EmployeeeDto employeeeDto = new EmployeeeDto(emp.getId(),
                emp.getFirstName(),
                emp.getLastName(),
                emp.getEmail(),
                emp.getDepartmentCode());

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setDepartmentDto(departmentDto);
        apiResponseDto.setEmployeeeDto(employeeeDto);
        return apiResponseDto;
    }
}
