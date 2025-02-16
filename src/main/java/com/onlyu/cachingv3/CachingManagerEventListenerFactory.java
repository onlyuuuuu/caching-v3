//package com.onlyu.cachingv3;
//
//import lombok.extern.slf4j.Slf4j;
//import org.hibernate.cache.CacheException;
//import org.springframework.cache.CacheManager;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Properties;
//
//@Slf4j
//@Configuration
//public class CachingManagerEventListenerFactory extends CacheManagerEventListenerFactory
//{
//    @Override
//    public CacheManagerEventListener createCacheManagerEventListener(CacheManager cacheManager, Properties properties)
//    {
//        log.info("Available properties: {}", properties);
//        return new CacheManagerEventListener()
//        {
//            @Override
//            public void init() throws CacheException
//            {
//                log.info("Cache Manager [{}] has been initialized! Info: {}", cacheManager.getName(), cacheManager);
//            }
//
//            @Override
//            public Status getStatus()
//            {
//                return cacheManager.getStatus();
//            }
//
//            @Override
//            public void dispose() throws CacheException
//            {
//                log.info("Shutting down Ehcache Manager [{}]", cacheManager.getName());
//            }
//
//            @Override
//            public void notifyCacheAdded(String cache)
//            {
//                log.info("Manager [{}]: cache [{}] added.", cacheManager.getName(), cache);
//            }
//
//            @Override
//            public void notifyCacheRemoved(String cache)
//            {
//                log.info("Manager [{}]: cache [{}] removed.", cacheManager.getName(), cache);
//            }
//        };
//    }
//}
