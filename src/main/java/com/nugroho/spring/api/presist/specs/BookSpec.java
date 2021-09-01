package com.nugroho.spring.api.presist.specs;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import com.nugroho.spring.api.presist.models.Book;
import com.nugroho.spring.api.requests.v1.book.BookParams;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class BookSpec {
    public Specification<Book> filter(BookParams params) {
        var predicates = new ArrayList<Predicate>();
        return (root, query, cb) -> {
            predicates.add(cb.like(cb.lower(root.get("name")), params.getSearch()));
            return query.where(predicates.toArray(new Predicate[0])).getRestriction();
        };
    }
}
