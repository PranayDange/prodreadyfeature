package com.example.learnprodreadyfeature.prodreadyfeature.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {

    private Long id;

    private String name;

    private String email;

    private Integer age;

    private String role; //ADMIN, USER

    private Double salary;

    private LocalDate dateOfJoining;

    private Boolean isActive;
}
