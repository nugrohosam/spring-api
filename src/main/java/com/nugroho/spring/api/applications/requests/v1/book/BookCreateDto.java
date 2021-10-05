package com.nugroho.spring.api.applications.requests.v1.book;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class BookCreateDto {

    @NotBlank
    private String name;

    @NotNull
    private Date releaseDate;
}
