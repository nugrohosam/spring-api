package com.nugroho.spring.api.applications.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.nugroho.spring.api.utility.Global;
import com.nugroho.spring.api.utility.Response;
import com.nugroho.spring.api.utility.ResponseSuccess;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@RestController
@RequestMapping
public class IndexController {

    @GetMapping
    public ResponseEntity<Response> index(@RequestParam String param) {
        var response = new ResponseSuccess();
        response.setMessage("This is service author, book");
        return Global.resSuccess(response);
    }

}
