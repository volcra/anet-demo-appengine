package com.volcra.anet.demo.infrastructure.caching.memcache

import com.google.appengine.api.memcache.MemcacheService
import org.springframework.cache.Cache
import org.springframework.cache.support.SimpleValueWrapper

import static org.springframework.cache.Cache.ValueWrapper

@groovy.transform.CompileStatic
class MemCache implements Cache {
    final MemcacheService nativeCache
    String name = ''

    MemCache(MemcacheService nativeCache) {
        this.nativeCache = nativeCache
    }

    @Override
    ValueWrapper get(Object key) {
        def object = nativeCache.get(key)

        object == null ? null : new SimpleValueWrapper(object)
    }

    @Override
    void put(Object key, Object value) {
        nativeCache.put key, value
    }

    @Override
    void evict(Object key) {
        nativeCache.delete key
    }

    @Override
    void clear() {
        nativeCache.clearAll()
    }
}
