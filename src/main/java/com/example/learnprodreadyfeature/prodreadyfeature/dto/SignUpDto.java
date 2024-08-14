package com.example.learnprodreadyfeature.prodreadyfeature.dto;

import com.example.learnprodreadyfeature.prodreadyfeature.entities.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDto {
    private String email;
    private String pass;
    private String name;
    private Set<Role> roles;
}
