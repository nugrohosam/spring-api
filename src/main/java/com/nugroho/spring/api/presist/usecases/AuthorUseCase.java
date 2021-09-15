package com.nugroho.spring.api.presist.usecases;

import com.nugroho.spring.api.applications.requests.v1.author.AuthorCreateDto;
import com.nugroho.spring.api.applications.requests.v1.author.AuthorParams;
import com.nugroho.spring.api.presist.models.author.Author;
import com.nugroho.spring.api.presist.repos.author.AuthorRepo;
import com.nugroho.spring.api.presist.repos.author.AuthorSpec;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorUseCase {

    private AuthorRepo authorRepo;
    private AuthorSpec spec;

    public Page<Author> getAll(AuthorParams params) {
        var page = PageRequest.of(params.getPage(), params.getSize(), Sort.by("id").descending());
        return authorRepo.findAll(spec.filter(params), page);
    }

    public Author findById(Long id) throws Exception {
        return authorRepo.findById(id).orElseThrow(() -> new NotFoundException(""));
    }

    public Author create(AuthorCreateDto dto) throws Exception {
        var newAuthor = new Author();
        newAuthor.setName(dto.getName());
        return authorRepo.save(newAuthor);
    }
}
