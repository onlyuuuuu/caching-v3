//package com.onlyu.cachingv3;
//
//import lombok.extern.slf4j.Slf4j;
//import org.hibernate.cache.CacheException;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Properties;
//
//@Slf4j
//@Configuration
//public class CachingEventListenerFactory extends CacheEventListenerFactory
//{
//    @Override
//    public CacheEventListener createCacheEventListener(Properties properties)
//    {
//        log.info("Available properties: {}", properties);
//        return new CacheEventListener()
//        {
//            @Override
//            public void notifyElementRemoved(Ehcache ehcache, Element element) throws CacheException
//            {
//                log.info("EhCache root [{}]: element [{}] removed.", ehcache.getName(), element);
//            }
//
//            @Override
//            public void notifyElementPut(Ehcache ehcache, Element element) throws CacheException
//            {
//                log.info("EhCache root [{}]: element [{}] inserted.", ehcache.getName(), element);
//            }
//
//            @Override
//            public void notifyElementUpdated(Ehcache ehcache, Element element) throws CacheException
//            {
//                log.info("EhCache root [{}]: element [{}] updated.", ehcache.getName(), element);
//            }
//
//            @Override
//            public void notifyElementExpired(Ehcache ehcache, Element element)
//            {
//                log.info("EhCache root [{}]: element [{}] expired.", ehcache.getName(), element);
//            }
//
//            @Override
//            public void notifyElementEvicted(Ehcache ehcache, Element element)
//            {
//                log.info("EhCache root [{}]: element [{}] evicted.", ehcache.getName(), element);
//            }
//
//            @Override
//            public void notifyRemoveAll(Ehcache ehcache)
//            {
//                log.info("EhCache root [{}]: all elements evicted.", ehcache.getName());
//            }
//
//            @Override
//            public void dispose()
//            {
//                log.info("Shutting down Ehcache");
//            }
//
//            @Override
//            public Object clone() throws CloneNotSupportedException
//            {
//                throw new CloneNotSupportedException();
//            }
//        };
//    }
//}
