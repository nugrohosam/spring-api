package com.nugroho.spring.api.presist.repos;

import com.nugroho.spring.api.presist.models.book.Book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import com.nugroho.spring.api.applications.requests.v1.book.BookParams;

public interface BookRepo extends JpaRepository<Book, Long> {
    public Page<Book> findWithCondition(Specification<Book> spec, Pageable page);

    class Spec {
        public Specification<Book> filter(BookParams params) {
            var predicates = new ArrayList<Predicate>();
            return (root, query, cb) -> {
                predicates.add(cb.like(cb.lower(root.get("name")), params.getSearch()));
                var predicateInArr = predicates.toArray(new Predicate[predicates.size()]);
                return query.where(predicateInArr).getRestriction();
            };
        }
    }
}