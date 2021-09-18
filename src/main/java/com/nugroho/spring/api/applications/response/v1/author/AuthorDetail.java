package com.nugroho.spring.api.applications.response.v1.author;

import com.nugroho.spring.api.presist.models.author.Author;

import lombok.Data;

@Data
public class AuthorDetail {
    
    private Long id;
    private String name;

    public static AuthorDetail mapping(Author author) {
        var authorDetail = new AuthorDetail();
        authorDetail.setId(author.getId());
        authorDetail.setName(author.getName());
        
        return authorDetail;
    }
}
