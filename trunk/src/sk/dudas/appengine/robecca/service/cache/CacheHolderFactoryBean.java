package sk.dudas.appengine.robecca.service.cache;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.cache.Cache;
import javax.cache.CacheManager;
import java.util.Collections;
import java.util.Map;

/**
 * Simple wrapper to expose a Cache in the Spring context.
 */
//@SuppressWarnings("unchecked")
//@Qualifier("pictureCacheHolder")
//@Component("pictureCacheHolder")
public class CacheHolderFactoryBean implements FactoryBean {

    Map cacheProperties = Collections.emptyMap();

    public void setCacheProperties(Map cacheProperties) {
        this.cacheProperties = cacheProperties;
    }

    public Object getObject() throws Exception {
        Cache cache = CacheManager.getInstance().getCacheFactory().createCache(cacheProperties);
        return new CacheHolder(cache);
    }

    public Class<CacheHolder> getObjectType() {
        return CacheHolder.class;
    }

    public boolean isSingleton() {
        return true;
    }

}
