package com.nugroho.spring.api.applications.requests.v1.author;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AuthorCreateDto {
    
    @NotBlank
    private String name;
}
