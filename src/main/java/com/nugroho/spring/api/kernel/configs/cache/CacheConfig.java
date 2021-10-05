package com.nugroho.spring.api.kernel.configs.cache;

import com.nugroho.spring.api.global.Config;
import com.nugroho.spring.api.utility.Global;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;

public class CacheConfig {

    protected String[] cacheNames = { Config.AUTHOR_CACHE, Config.BOOK_CACHE, Config.BOOK_CACHE_LIST,
            Config.AUTHOR_CACHE_LIST };

    @Bean(Config.GENERATOR_CACHE_KEY)
    public KeyGenerator keyGeneratorCache() {
        return (target, method, params) -> Global.generateCacheKey(target, method, params);
    }
}
