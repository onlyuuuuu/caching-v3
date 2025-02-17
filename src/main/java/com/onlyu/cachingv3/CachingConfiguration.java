package com.onlyu.cachingv3;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.*;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.core.config.DefaultConfiguration;
import org.ehcache.event.*;
import org.ehcache.impl.config.persistence.DefaultPersistenceConfiguration;
import org.ehcache.jsr107.*;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.*;

import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;

@Slf4j
@Configuration
@EnableCaching
public class CachingConfiguration extends CachingConfigurerSupport
{
    @Bean
    public CacheManagerCustomizer<ConcurrentMapCacheManager> cacheManagerCustomizer() {
        return cacheManager ->
        {
            cacheManager.setAllowNullValues(false);
            cacheManager.setStoreByValue(false);
        };
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        CachingProvider provider = Caching.getCachingProvider();
        EhcacheCachingProvider ehcacheProvider = (EhcacheCachingProvider) provider;
        File swap = Paths.get(System.getProperty("java.io.tmpdir"), "caching-v2").toFile();
        log.info("Temporary local swap location: {}", swap.getAbsolutePath());
        DefaultConfiguration configuration = new DefaultConfiguration
        (
            ehcacheProvider.getDefaultClassLoader(),
            new DefaultPersistenceConfiguration(swap)
        );
        javax.cache.CacheManager cacheManager = ehcacheProvider.getCacheManager(ehcacheProvider.getDefaultURI(), configuration);
        CacheEventListenerConfigurationBuilder cacheEventListenerConfiguration = CacheEventListenerConfigurationBuilder
            .newEventListenerConfiguration
            (event ->
                {
                    log.info("Cache event {}: {}, Key: {}, New Value: {}, Old Value: {}", event.getType(), event, event.getKey(), event.getNewValue(), event.getOldValue());
                },
                EventType.CREATED,
                EventType.UPDATED,
                EventType.EVICTED,
                EventType.EXPIRED,
                EventType.REMOVED
            )
                .unordered().asynchronous();
        // https://stackoverflow.com/questions/43412225/set-up-both-ttl-and-tti-in-ehcache-3-xml-configuration
        CacheConfiguration<Object, Object> cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder
        (
            Object.class, Object.class,
            ResourcePoolsBuilder.newResourcePoolsBuilder()
                .heap(2, MemoryUnit.GB)
                .offheap(3, MemoryUnit.GB)
                .disk(4, MemoryUnit.GB, true)
        )
        .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(2)))
        .withService(cacheEventListenerConfiguration)
        .build();
        cacheManager.createCache("defaultCache", Eh107Configuration.fromEhcacheCacheConfiguration(cacheConfiguration));
        return new JCacheCacheManager(cacheManager);
    }
}
