package com.nugroho.spring.api.applications.response.v1.book;

import com.nugroho.spring.api.presist.models.book.Book;

import lombok.Data;

@Data
public class BookDetail {
    
    private Long id;
    private String name;

    public static BookDetail mapping(Book book) {
        var bookDetail = new BookDetail();
        bookDetail.setId(book.getId());
        bookDetail.setName(book.getName());
        
        return bookDetail;
    }
}
