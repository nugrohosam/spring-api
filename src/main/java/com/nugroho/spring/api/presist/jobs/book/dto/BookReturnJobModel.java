package com.nugroho.spring.api.presist.jobs.book.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookReturnJobModel {

    private String id;
    private String to;
    private String message;

}