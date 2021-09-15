package com.nugroho.spring.api.presist.repos;

import com.nugroho.spring.api.presist.models.author.Author;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import com.nugroho.spring.api.applications.requests.v1.author.AuthorParams;

public interface AuthorRepo extends JpaRepository<Author, Long> {
    public Page<Author> findWithCondition(Specification<Author> spec, Pageable page);

    class Spec {
        public Specification<Author> filter(AuthorParams params) {
            var predicates = new ArrayList<Predicate>();
            return (root, query, cb) -> {
                predicates.add(cb.like(cb.lower(root.get("name")), params.getSearch()));
                var predicatesInArr = predicates.toArray(new Predicate[predicates.size()]);
                return query.where(predicatesInArr).getRestriction();
            };
        }
    }
}
