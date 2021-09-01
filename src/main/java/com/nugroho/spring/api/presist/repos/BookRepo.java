package com.nugroho.spring.api.presist.repos;

import com.nugroho.spring.api.presist.models.Book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Long> {
    public Page<Book> findWithCondition(Specification<Book> spec, Pageable page);
}