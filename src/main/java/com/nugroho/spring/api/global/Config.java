package com.nugroho.spring.api.global;

public class Config {

    // Cache config

    public static final String ITEM_CACHE = "itemCache";
    public static final String AUTHOR_CACHE = "authorCache";
    public static final String BOOK_CACHE = "bookCache";
    public static final String BOOK_CACHE_LIST = BOOK_CACHE + "List";
    public static final String AUTHOR_CACHE_LIST = AUTHOR_CACHE + "List";

    public static final String GENERATOR_CACHE_KEY = "generatorCacheKey";
    public static final String SEPARATOR_CACHE_KEY = "_";

    public static final String[] DATA_TO_CACHE = { Config.AUTHOR_CACHE, Config.BOOK_CACHE, Config.BOOK_CACHE_LIST,
            Config.AUTHOR_CACHE_LIST };

}
