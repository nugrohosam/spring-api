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

            // condition1 and (condition2 or condition3)
            predicates.add(cb.like(root.get(columnName), params.getSearch())); // 1
            // predicates.add( cb.or( cb.like(x, pattern), cb.like(x, pattern)) ) // 2 or 3

            var predicateInArr = predicates.toArray(new Predicate[0]);

            // predicates => .add .add .add [AND]
            // predicates => .add(cb.or( predicates   )) [OR]

            // [select] [kolom] [from / darimananya] [conditional] [orderingnya] [limitasi data]

            return query.where(predicateInArr).getRestriction();
        };
    }
}
