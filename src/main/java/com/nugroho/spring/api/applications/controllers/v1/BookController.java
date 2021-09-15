package com.nugroho.spring.api.applications.controllers.v1;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.nugroho.spring.api.applications.requests.v1.book.BookParams;
import com.nugroho.spring.api.global.Routes;
import com.nugroho.spring.api.presist.usecases.BookUseCase;
import com.nugroho.spring.api.utility.Response;
import com.nugroho.spring.api.utility.ResponseSuccess;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@RestController(value = Routes.API_V1 + Routes.BOOK)
@RequiredArgsConstructor
public class BookController {

    private BookUseCase useCase;

    @GetMapping
    public Response index(@RequestParam BookParams params) {
        var responseSuccess = new ResponseSuccess();
        responseSuccess.setData(useCase.getAll(params));
        return responseSuccess;
    }

    @GetMapping(path = Routes.ID)
    public Response detail(@PathVariable String id) {
        return new ResponseSuccess();
    }

}
