package com.nugroho.spring.api.applications.requests.v1.book;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class BookCreateDto {
    
    @NotBlank
    private String name;
}
