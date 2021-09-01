package com.nugroho.spring.api.presist.usecases;

import com.nugroho.spring.api.presist.models.Book;
import com.nugroho.spring.api.presist.repos.BookRepo;
import com.nugroho.spring.api.presist.specs.BookSpec;
import com.nugroho.spring.api.requests.v1.book.BookParams;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookUseCase {
    
    private BookSpec bookSpec;
    private BookRepo bookRepo;

    public Page<Book> getAll(BookParams params) {
        var page = PageRequest.of(params.getPage(), params.getSize(), Sort.by("id").descending());
        return bookRepo.findWithCondition(bookSpec.filter(params), page);
    }

    public Book findById(Long id) throws Exception {
        return bookRepo.findById(id).orElseThrow(() -> new NotFoundException(""));
    }
}
