package com.nugroho.spring.api.applications.requests.v1;

import lombok.Data;

@Data
public class Params {
    protected String search;
    protected int page;
    protected int size;
}
