package com.nugroho.spring.api.applications.response.v1.book;

import com.nugroho.spring.api.applications.response.Pagination;
import com.nugroho.spring.api.presist.models.book.Book;

import org.springframework.data.domain.Page;

public class BookList {

    public static Pagination<Book> paginate(Page<Book> dataPaginate) {
        var paginateData = new Pagination<Book>(dataPaginate);
        var listContent = list(dataPaginate);
        paginateData.setItems(listContent);
        return paginateData;
    }

    public static BookDetail[] list(Page<Book> dataPaginate) {
        return dataPaginate.getContent().stream().map(item -> {
            return BookDetail.mapping(item);
        }).toArray(BookDetail[]::new);
    }
}
