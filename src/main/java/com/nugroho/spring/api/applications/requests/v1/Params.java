package com.nugroho.spring.api.applications.requests.v1;

import lombok.Data;

@Data
public class Params {
    
    protected String search;
    protected int page;
    protected int size;

    public int getSize() {
        return size > 0 ? size : 15;
    }

    public int getPage() {
        return page > 0 ? (page - 1) : 0;
    }

    public String getSearch() {
        return search != null ? "%" + search + "%" : "%%";
    }

    public static boolean isInstanceOfMe(Object classInstance){
        return classInstance instanceof Params;
    }

    public static String generateOfKey(Params param) {
        return param.getSearch() + "_" + param.getPage() + param.getSize();
    }
}
