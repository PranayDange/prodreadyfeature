package com.example.learnprodreadyfeature.prodreadyfeature.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String description;

}
