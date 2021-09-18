package com.nugroho.spring.api.applications.response;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class Pagination<T> {
    
    private int totalPage;
    private int page;
    private long total;
    private Object[] items;

    public Pagination(Page<T> data) {
        page = data.getNumber() + 1;
        total = data.getTotalElements();
        totalPage = data.getTotalPages();
    }

}
