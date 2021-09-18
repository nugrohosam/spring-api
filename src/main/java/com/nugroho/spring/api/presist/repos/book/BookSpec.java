package com.nugroho.spring.api.presist.repos.book;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import com.nugroho.spring.api.applications.requests.v1.book.BookParams;
import com.nugroho.spring.api.presist.models.book.Book;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class BookSpec {

    private final String columnName = "name";

    public Specification<Book> filter(BookParams params) {
        var predicates = new ArrayList<Predicate>();
        return (root, query, cb) -> {
            predicates.add(cb.like(root.get(columnName), params.getSearch()));
            var predicateInArr = predicates.toArray(new Predicate[0]);
            return query.where(predicateInArr).getRestriction();
        };
    }
}
