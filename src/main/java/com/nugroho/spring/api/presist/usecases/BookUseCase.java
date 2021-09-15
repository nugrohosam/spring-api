package com.nugroho.spring.api.presist.usecases;

import com.nugroho.spring.api.applications.requests.v1.book.BookCreateDto;
import com.nugroho.spring.api.applications.requests.v1.book.BookParams;
import com.nugroho.spring.api.presist.models.book.Book;
import com.nugroho.spring.api.presist.repos.book.BookRepo;
import com.nugroho.spring.api.presist.repos.book.BookSpec;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookUseCase {

    private BookRepo bookRepo;
    private BookSpec spec;

    public Page<Book> getAll(BookParams params) {
        var page = PageRequest.of(params.getPage(), params.getSize(), Sort.by("id").descending());
        return bookRepo.findAll(spec.filter(params), page);
    }

    public Book findById(Long id) throws Exception {
        return bookRepo.findById(id).orElseThrow(() -> new NotFoundException(""));
    }

    public Book create(BookCreateDto dto) throws Exception {
        var newBook = new Book();
        newBook.setName(dto.getName());
        return bookRepo.save(newBook);
    }
}
