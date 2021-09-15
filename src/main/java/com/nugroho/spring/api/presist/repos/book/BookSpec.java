package com.nugroho.spring.api.presist.repos.book;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import com.nugroho.spring.api.applications.requests.v1.book.BookParams;
import com.nugroho.spring.api.presist.models.book.Book;

import org.springframework.data.jpa.domain.Specification;

public class BookSpec {
    public Specification<Book> filter(BookParams params) {
        var predicates = new ArrayList<Predicate>();
        return (root, query, cb) -> {
            predicates.add(cb.like(cb.lower(root.get("name")), params.getSearch()));
            var predicateInArr = predicates.toArray(new Predicate[predicates.size()]);
            return query.where(predicateInArr).getRestriction();
        };
    }
}
