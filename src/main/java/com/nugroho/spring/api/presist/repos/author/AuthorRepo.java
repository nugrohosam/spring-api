package com.nugroho.spring.api.presist.repos.author;

import com.nugroho.spring.api.presist.models.author.Author;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Long> {
    
    public Page<Author> findAll(Specification<Author> spec, Pageable page);

}
