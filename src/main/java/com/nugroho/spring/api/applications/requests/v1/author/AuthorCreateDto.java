package com.nugroho.spring.api.applications.requests.v1.author;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AuthorCreateDto {

    @NotBlank
    private String name;

    @NotNull
    private Date birthDate;
}
