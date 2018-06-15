package utilities;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import models.Product;

import javax.inject.Inject;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by janlatha on 6/14/2018.
 */
public class CacheManager {

    private static Cache<String, Object> guavaCacheMap = null;
    private static Cache<String, Object> elasticCacheMap = null;
    private CacheLoader<String, String> loader;
    private LoadingCache<String, String> cache;

    private RedisCacheUtility redisCacheUtility = new RedisCacheUtility();

    public CacheManager() {


        if (elasticCacheMap == null) {
            elasticCacheMap = CacheBuilder.newBuilder()
                    .maximumSize(10000)
                    .expireAfterWrite(6, TimeUnit.MINUTES)
                    .recordStats()
                    .build();
        }
        if (guavaCacheMap == null) {
            guavaCacheMap = CacheBuilder.newBuilder()
                    .maximumSize(10000)
                    .expireAfterWrite(4, TimeUnit.MINUTES)
                    .recordStats()
                    .build();
        }


            loader = new CacheLoader<String, String>() {

                @Override
                public String load(String key) {
                    return redisCacheUtility.getDetails(key);
                }
            };

            cache = CacheBuilder.newBuilder().recordStats().build(loader);


    }

    public String get(String key) throws ExecutionException {
        return cache.get(key);
    }

    public Object getCachedKeyValue(String key, Callable<?> myLoader) {
        Object myObj = null;
        try {
            myObj = getCachedKeyValue(key);
            if (null == myObj) {
                myObj = myLoader.call();
                putCachedKeyValue(key, myObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myObj;
    }

    public Object getCachedKeyValue(String key) {
        Object retValue = null;
        try {
            if (key != null) {
                retValue = elasticCacheMap.getIfPresent(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retValue;
    }

    public void putCachedKeyValue(String key, Object value) {
        try {
            if (key != null && value != null) {
                elasticCacheMap.put(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
