package com.nugroho.spring.api.presist.repos.book;

import com.nugroho.spring.api.presist.models.book.Book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Long> {
    
    public Page<Book> findAll(Specification<Book> spec, Pageable page);

}