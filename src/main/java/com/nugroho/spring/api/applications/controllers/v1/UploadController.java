package com.nugroho.spring.api.applications.controllers.v1;

import org.springframework.web.bind.annotation.RestController;

import com.nugroho.spring.api.applications.requests.v1.FileUploadDto;
import com.nugroho.spring.api.global.Routes;
import com.nugroho.spring.api.presist.usecases.UploadUseCase;
import com.nugroho.spring.api.utility.Global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@RestController
@RequestMapping(Routes.API_V1 + Routes.UPLOADS)
public class UploadController {

    @Autowired
    private UploadUseCase uploadUseCase;

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> upload(@ModelAttribute FileUploadDto fileUploadDto) throws Exception {
        uploadUseCase.save(fileUploadDto);
        return Global.resSuccess("Success create");
    }
}
