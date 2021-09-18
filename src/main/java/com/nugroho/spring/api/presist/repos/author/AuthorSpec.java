package com.nugroho.spring.api.presist.repos.author;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import com.nugroho.spring.api.applications.requests.v1.author.AuthorParams;
import com.nugroho.spring.api.presist.models.author.Author;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AuthorSpec {
    public Specification<Author> filter(AuthorParams params) {
        var predicates = new ArrayList<Predicate>();
        return (root, query, cb) -> {
            predicates.add(cb.like(cb.lower(root.get("name")), params.getSearch()));
            var predicateInArr = predicates.toArray(new Predicate[predicates.size()]);
            return query.where(predicateInArr).getRestriction();
        };
    }
}
