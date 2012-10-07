package sk.dudas.appengine.robecca.service;

import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.gdata.data.photos.PhotoEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sk.dudas.appengine.robecca.provider.PicasaProvider;
import sk.dudas.appengine.robecca.service.cache.CacheHolder;

import javax.annotation.PostConstruct;
import javax.cache.Cache;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 8.10.2012
 * Time: 16:40
 * To change this template use File | Settings | File Templates.
 */
@Service("picasaManager")
public class PicasaManagerImpl implements PicasaManager {

    private static final String USERNAME = "womans@womans.sk";
    private static final String PASSWORD = "womans852cs";
    public static final String PHOTO_ENTRY_LIST = "photoEntryList";
    private static final int DEFAULT_EXPIRATION = 3600;

//    @Autowired
//    @Qualifier("memcacheServiceUser")
//    private MemcacheService memcacheService;

    private Cache pictureCache;

    @Autowired
    public PicasaManagerImpl(@Qualifier("pictureCacheHolder") CacheHolder pictureCacheHolder) {
        this.pictureCache = pictureCacheHolder.getCache();
    }

    @SuppressWarnings("unused")
    @PostConstruct
    private void fillCache() {
        pictureCache.clear();
        PicasaProvider provider = new PicasaProvider(USERNAME, PASSWORD);
        List<PhotoEntry> albumPhotoEntryList = provider.getAlbumPhotoEntryList("5667859215738016225");
//        memcacheService.put(PHOTO_ENTRY_LIST, albumPhotoEntryList, Expiration.byDeltaSeconds(DEFAULT_EXPIRATION));
        pictureCache.put(PHOTO_ENTRY_LIST, albumPhotoEntryList);
    }

    public List<PhotoEntry> getAlbumPhotoEntryList() {
        return (List<PhotoEntry>) pictureCache.get(PHOTO_ENTRY_LIST);
//        return (List<PhotoEntry>) memcacheService.get(PHOTO_ENTRY_LIST);
    }

}
