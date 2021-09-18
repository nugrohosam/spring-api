package com.nugroho.spring.api.applications.controllers.v1;

import org.springframework.web.bind.annotation.RestController;

import com.nugroho.spring.api.applications.requests.v1.author.AuthorParams;
import com.nugroho.spring.api.global.Routes;
import com.nugroho.spring.api.presist.usecases.AuthorUseCase;
import com.nugroho.spring.api.utility.Response;
import com.nugroho.spring.api.utility.ResponseSuccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@RestController
@RequestMapping(Routes.API_V1 + Routes.AUTHORS)
public class AuthorController {

    @Autowired
    private AuthorUseCase useCase;

    @GetMapping
    public Response index(@RequestParam AuthorParams params) {
        var responseSuccess = new ResponseSuccess();
        responseSuccess.setData(useCase.getAll(params));
        return responseSuccess;
    }

    @GetMapping(Routes.ID)
    public Response detail(@PathVariable String id) {
        return new ResponseSuccess();
    }

}
