package com.example.learnprodreadyfeature.prodreadyfeature.clients.impl;

import com.example.learnprodreadyfeature.prodreadyfeature.advice.ApiResponse;
import com.example.learnprodreadyfeature.prodreadyfeature.clients.EmployeeClient;
import com.example.learnprodreadyfeature.prodreadyfeature.dto.EmployeeDTO;
import com.example.learnprodreadyfeature.prodreadyfeature.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;
    Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        try {
            ApiResponse<List<EmployeeDTO>> employeeDtoList = restClient.get()
                    .uri("employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could not create the employee");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.error("successfully retrieved the employees from client : {}",employeeDtoList.getData());
            return employeeDtoList.getData();

        } catch (Exception ex) {
            log.error("Exception Occurred in getAllEmployees", ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        try {
            ApiResponse<EmployeeDTO> employeeResponse = restClient.get()
                    .uri("employees/{employeeId}", employeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeResponse.getData();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        try {
            ApiResponse<EmployeeDTO> employeeDTOApiResponse = restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        throw new ResourceNotFoundException("Could not create the employee");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });

            return employeeDTOApiResponse.getData();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
