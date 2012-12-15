package sk.dudas.appengine.robecca.cache;

import org.springframework.beans.factory.FactoryBean;
import sk.dudas.appengine.robecca.service.cache.CacheHolder;

import javax.cache.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * Simple wrapper to expose a Cache in the Spring context.
 */
//@SuppressWarnings("unchecked")
//@Qualifier("pictureCacheHolder")
//@Component("pictureCacheHolder")
public class CacheHolderFactoryBeanTest implements FactoryBean {

    Map cacheProperties = Collections.emptyMap();

    public void setCacheProperties(Map cacheProperties) {
        this.cacheProperties = cacheProperties;
    }

    public Object getObject() throws Exception {
        return new CacheHolder(new Cache() {
            public void addListener(CacheListener cacheListener) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void evict() {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public Map getAll(Collection collection) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            public CacheEntry getCacheEntry(Object o) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            public CacheStatistics getCacheStatistics() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            public void load(Object o) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void loadAll(Collection collection) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public Object peek(Object o) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            public void removeListener(CacheListener cacheListener) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public int size() {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            public boolean isEmpty() {
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }

            public boolean containsKey(Object key) {
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }

            public boolean containsValue(Object value) {
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }

            public Object get(Object key) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            public Object put(Object key, Object value) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            public Object remove(Object key) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            public void putAll(Map m) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void clear() {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public Set keySet() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            public Collection values() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            public Set entrySet() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public Class<CacheHolder> getObjectType() {
        return CacheHolder.class;
    }

    public boolean isSingleton() {
        return true;
    }

}
