package com.nugroho.spring.api.applications.controllers.v1;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.nugroho.spring.api.applications.requests.v1.author.AuthorCreateDto;
import com.nugroho.spring.api.applications.requests.v1.author.AuthorParams;
import com.nugroho.spring.api.applications.requests.v1.author.AuthorUpdateDto;
import com.nugroho.spring.api.applications.response.v1.author.AuthorDetail;
import com.nugroho.spring.api.applications.response.v1.author.AuthorList;
import com.nugroho.spring.api.global.Routes;
import com.nugroho.spring.api.presist.usecases.AuthorUseCase;
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
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@RestController
@RequestMapping(Routes.API_V1 + Routes.AUTHORS)
public class AuthorController {

    @Autowired
    private AuthorUseCase useCase;
    private ResponseSuccess res = new ResponseSuccess();

    @GetMapping
    public ResponseEntity<Response> index(@RequestParam AuthorParams params) {
        var dataPaginate = useCase.getAll(params);
        res.setData(AuthorList.paginate(dataPaginate));
        return Global.resSuccess(res);
    }

    @GetMapping(Routes.ID)
    public ResponseEntity<Response> detail(@PathVariable Long id) throws Exception {
        var data = useCase.findById(id);
        res.setData(AuthorDetail.mapping(data));
        return Global.resSuccess(res);
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody AuthorCreateDto authorDto) throws Exception {
        useCase.create(authorDto);
        return Global.resSuccess("Success create");
    }

    @PutMapping(path = Routes.ID)
    public ResponseEntity<String> update(@PathVariable(name = "id") Long id,
            @Valid @RequestBody AuthorUpdateDto authorDto) throws Exception {
        useCase.update(id, authorDto);
        return Global.resSuccess("Success create");
    }

}
