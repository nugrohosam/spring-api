package com.nugroho.spring.api.applications.controllers.v1;

import org.springframework.web.bind.annotation.RestController;

import com.nugroho.spring.api.applications.requests.v1.FileUploadDto;
import com.nugroho.spring.api.global.Routes;
import com.nugroho.spring.api.global.UploadDataType;
import com.nugroho.spring.api.presist.usecases.UploadUseCase;
import com.nugroho.spring.api.utility.Global;
import com.nugroho.spring.api.utility.Response;
import com.nugroho.spring.api.utility.ResponseSuccess;

import org.apache.commons.io.FilenameUtils;
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
    private ResponseSuccess response = new ResponseSuccess();

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Response> upload(@ModelAttribute FileUploadDto fileUploadDto) throws Exception {
        var path = uploadUseCase.save(fileUploadDto);
        var uploadType = UploadDataType.valueOf(fileUploadDto.getType().toUpperCase());

        switch (uploadType) {
            case IMAGE:
                var extension = FilenameUtils.getExtension(fileUploadDto.getFile().getOriginalFilename());
                uploadUseCase.compressImage(path, extension, 30);
            case CSV:
            case DOC:
            case DOCX:
            case PDF:
        }
        
        response.setMessage("Upload success");
        return Global.resSuccess(response);
    }
}
