package com.example.learnprodreadyfeature.prodreadyfeature.clients;

import com.example.learnprodreadyfeature.prodreadyfeature.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeClient {

    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployeeById(Long employeeId);
    EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO);
}
