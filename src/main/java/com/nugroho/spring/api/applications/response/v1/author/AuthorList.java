package com.nugroho.spring.api.applications.response.v1.author;

import com.nugroho.spring.api.applications.response.Pagination;
import com.nugroho.spring.api.presist.models.author.Author;

import org.springframework.data.domain.Page;

public class AuthorList {

    public static Pagination<Author> paginate(Page<Author> dataPaginate) {
        var paginateData = new Pagination<Author>(dataPaginate);
        var listContent = list(dataPaginate);
        paginateData.setItems(listContent);
        return paginateData;
    }

    public static AuthorDetail[] list(Page<Author> dataPaginate) {
        return dataPaginate.getContent().stream().map(item -> {
            return AuthorDetail.mapping(item);
        }).toArray(AuthorDetail[]::new);
    }
}
