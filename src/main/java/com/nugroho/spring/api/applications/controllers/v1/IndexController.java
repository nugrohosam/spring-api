package com.nugroho.spring.api.applications.controllers.v1;

import org.springframework.web.bind.annotation.RestController;

import com.nugroho.spring.api.global.Routes;
import com.nugroho.spring.api.global.Middleware;
import com.nugroho.spring.api.utility.Global;
import com.nugroho.spring.api.utility.Response;
import com.nugroho.spring.api.utility.ResponseSuccess;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@RestController
@RequestMapping(Routes.API_V1)
public class IndexController {

    private ResponseSuccess response = new ResponseSuccess();

    @GetMapping("/blabla/{id}")
    @PreAuthorize("hasPermission(returnObject, '" + Middleware.CHECK_AUTH + "')")
    public ResponseEntity<Response> index() {
        response.setMessage("This is service author, book");
        return Global.resSuccess(response);
    }

}
