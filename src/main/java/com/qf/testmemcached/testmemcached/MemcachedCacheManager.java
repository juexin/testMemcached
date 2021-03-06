package com.qf.testmemcached.testmemcached;

import net.rubyeye.xmemcached.MemcachedClient;
import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MemcachedCacheManager extends AbstractTransactionSupportingCacheManager {
  
    private ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<String, Cache>();
    private Map<String, Integer> expireMap = new HashMap<String, Integer>();   //缓存的时间
    private MemcachedClient memcachedClient;   //xmemcached的客户端
  
    public MemcachedCacheManager(MemcachedClient memcachedClient) {
        this.memcachedClient=memcachedClient;
    }

    @Override  
    protected Collection<? extends Cache> loadCaches() {
        Collection<Cache> values = cacheMap.values();  
        return values;  
    }  
  
    @Override  
    public Cache getCache(String name) {  
        Cache cache = cacheMap.get(name);  
        if (cache == null) {  
            Integer expire = expireMap.get(name);  
            if (expire == null) {  
                expire = 0;  
                expireMap.put(name, expire);  
            }  
            cache = new MemcachedCache(name, expire.intValue(), memcachedClient);  
            cacheMap.put(name, cache);  
        }  
        return cache;  
    }  
  

    public void setConfigMap(Map<String, Integer> configMap) {  
        this.expireMap = configMap;  
    }  
  
}