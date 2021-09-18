package com.nugroho.spring.api.kernel.configs;

import java.lang.reflect.Method;
import java.time.Duration;

import com.nugroho.spring.api.applications.requests.v1.Params;
import com.nugroho.spring.api.global.Config;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.util.StringUtils;

@Configuration
@EnableCaching
public class CacheConfing {

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(60)).disableCachingNullValues()
                .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder
                .withCacheConfiguration(Config.AUTHOR_CACHE,
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10)))
                .withCacheConfiguration(Config.BOOK_CACHE,
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10)))
                .withCacheConfiguration(Config.BOOK_CACHE_LIST,
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)))
                .withCacheConfiguration(Config.AUTHOR_CACHE_LIST,
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)))
                .withCacheConfiguration("customerCache",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)));
    }

    @Bean(Config.GENERATOR_CACHE_KEY)
    public KeyGenerator keyGeneratorCache() {
        return new CacheKeyGeneratorList();
    }
}

class CacheKeyGeneratorList implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        var tagetClass = target.getClass().getSimpleName();
        var methodGet = method.getName();
        var key = tagetClass + "_" + methodGet;
        var isFound = false;

        // Define key search from params
        for (Object param : params) {
            if (Params.isInstanceOf(param)) {
                isFound = true;
                key += "_" + Params.generateOfKey((Params) param);
            }
        }

        if (!isFound) {
            key += "_" + StringUtils.arrayToDelimitedString(params, "_");
        }

        return key;
    }

}
