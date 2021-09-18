package com.nugroho.spring.api.applications.controllers.v1;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.nugroho.spring.api.applications.requests.v1.book.BookCreateDto;
import com.nugroho.spring.api.applications.requests.v1.book.BookParams;
import com.nugroho.spring.api.applications.requests.v1.book.BookUpdateDto;
import com.nugroho.spring.api.applications.response.v1.book.BookDetail;
import com.nugroho.spring.api.applications.response.v1.book.BookList;
import com.nugroho.spring.api.global.Routes;
import com.nugroho.spring.api.presist.usecases.BookUseCase;
import com.nugroho.spring.api.utility.Global;
import com.nugroho.spring.api.utility.Response;
import com.nugroho.spring.api.utility.ResponseSuccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@RestController
@RequestMapping(Routes.API_V1 + Routes.BOOKS)
public class BookController {

    @Autowired
    private BookUseCase useCase;

    @GetMapping
    public ResponseEntity<Response> index(BookParams params) {
        var res = new ResponseSuccess();
        var dataPaginate = useCase.getAll(params);
        res.setData(BookList.paginate(dataPaginate));
        return Global.resSuccess(res);
    }

    @GetMapping(path = Routes.ID)
    public ResponseEntity<Response> detail(@PathVariable(name = "id") Long id) throws Exception {
        var data = useCase.findById(id);
        var res = new ResponseSuccess();
        res.setData(BookDetail.mapping(data));
        return Global.resSuccess(res);
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody BookCreateDto bookDto) throws Exception {
        useCase.create(bookDto);
        return Global.resSuccess("Success create");
    }

    @PutMapping(path = Routes.ID)
    public ResponseEntity<String> update(@PathVariable(name = "id") Long id, @Valid @RequestBody BookUpdateDto bookDto) throws Exception {
        useCase.update(id, bookDto);
        return Global.resSuccess("Success udpate");
    }

}
