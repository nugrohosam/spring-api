package com.nugroho.spring.api.applications.requests.v1;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FileUploadDto {

    private String type;
    private String name;
    private MultipartFile file;
    
}
