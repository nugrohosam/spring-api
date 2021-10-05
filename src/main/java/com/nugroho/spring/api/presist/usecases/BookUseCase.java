package com.nugroho.spring.api.presist.usecases;

import com.nugroho.spring.api.applications.requests.v1.book.BookCreateDto;
import com.nugroho.spring.api.applications.requests.v1.book.BookParams;
import com.nugroho.spring.api.applications.requests.v1.book.BookUpdateDto;
import com.nugroho.spring.api.global.Config;
import com.nugroho.spring.api.presist.models.book.Book;
import com.nugroho.spring.api.presist.repos.book.BookRepo;
import com.nugroho.spring.api.presist.repos.book.BookSpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class BookUseCase {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private BookSpec spec;

    @Cacheable(value = Config.BOOK_CACHE_LIST, keyGenerator = Config.GENERATOR_CACHE_KEY)
    public Page<Book> getAll(BookParams params) {
        var page = PageRequest.of(params.getPage(), params.getSize(), Sort.by("id").descending());
        return bookRepo.findAll(spec.filter(params), page);
    }

    @Cacheable(value = Config.BOOK_CACHE, key = "#id")
    public Book findById(Long id) throws Exception {
        return bookRepo.findById(id).orElseThrow(() -> new NotFoundException(""));
    }

    @CacheEvict(value = Config.BOOK_CACHE_LIST, allEntries = true)
    public Book create(BookCreateDto dto) throws Exception {
        var newBook = new Book();
        newBook.setName(dto.getName());
        newBook.setReleaseDate(dto.getReleaseDate());
        return bookRepo.save(newBook);
    }

    @Caching(evict = { @CacheEvict(value = Config.BOOK_CACHE_LIST, allEntries = true),
            @CacheEvict(value = Config.BOOK_CACHE, key = "#id") })
    public Book update(Long id, BookUpdateDto dto) throws Exception {
        var newBook = findById(id);
        newBook.setName(dto.getName());
        return bookRepo.save(newBook);
    }
}
