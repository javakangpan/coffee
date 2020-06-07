package com.kangpan.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.kangpan.enums.CaffeineCacheEnum;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 缓存配置
 */
@Configuration
@EnableCaching
public class CaffeineCacheConfig {
    @Bean
    @Primary
    public CacheManager caffeinecacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<CaffeineCache> list = new ArrayList<>();
        for(CaffeineCacheEnum c : CaffeineCacheEnum.values()) {
            list.add(new CaffeineCache(c.name(),
                    Caffeine.newBuilder()
                            .recordStats()
                            .expireAfterWrite(c.getTtl(), TimeUnit.SECONDS)
                            .maximumSize(c.getMaxSize())
                            .build()));

        }
        cacheManager.setCaches(list);
        return cacheManager;
    }
}
