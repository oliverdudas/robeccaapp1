package sk.dudas.appengine.robecca.service;

import com.google.gdata.data.MediaContent;
import com.google.gdata.data.photos.PhotoEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sk.dudas.appengine.robecca.provider.PicasaProvider;
import sk.dudas.appengine.robecca.service.cache.PhotoDto;
import sk.dudas.appengine.robecca.service.cache.CacheHolder;

import javax.annotation.PostConstruct;
import javax.cache.Cache;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 8.10.2012
 * Time: 16:40
 * To change this template use File | Settings | File Templates.
 */
@Service("picasaManager")
public class PicasaManagerImpl implements PicasaManager {

    private static final Logger logger = Logger.getLogger(PicasaManagerImpl.class.getName());

    private static final String USERNAME = "womans@womans.sk";
    private static final String PASSWORD = "womans852cs";
    public static final String PHOTO_ENTRY_LIST = "photoEntryList";
    private static final int DEFAULT_EXPIRATION = 3600;

    private Cache pictureCache;

    @Autowired
    public PicasaManagerImpl(@Qualifier("pictureCacheHolder") CacheHolder pictureCacheHolder) {
        this.pictureCache = pictureCacheHolder.getCache();
    }

    @SuppressWarnings("unused")
    @PostConstruct
    private void fillCache() {
        logger.info("Clearing pictureCache.");
        pictureCache.clear();

        logger.info("Filling caches.");
        getAlbumPhotoEntryList();
    }

    private List<PhotoEntry> getAlbumPhotoEntryList(PicasaProvider provider) {
        return provider.getAlbumPhotoEntryList("5667859215738016225");
    }

    public List<PhotoDto> getAlbumPhotoEntryList() {
        List<PhotoDto> list = getObjectFromCache(PHOTO_ENTRY_LIST);
        if (list == null) {
            logger.info("Retrieving albumPhotoEntryList from picasa.");
            list = convertPhotoEntryListToPhotoDtoList(getAlbumPhotoEntryList(new PicasaProvider(USERNAME, PASSWORD)));
            logger.info("Putting albumPhotoEntryList to cache.");
            putObjectToCache(PHOTO_ENTRY_LIST, list);
            return list;
        } else {
            logger.info("Returning albumPhotoEntryList from cache.");
            return list;
        }
    }

    private List<PhotoDto> convertPhotoEntryListToPhotoDtoList(List<PhotoEntry> entryList) {
        List<PhotoDto> dtoList = new ArrayList<PhotoDto>();
        for (PhotoEntry photoEntry : entryList) {
            dtoList.add(new PhotoDto(((MediaContent) photoEntry.getContent()).getUri(), photoEntry.getMediaThumbnails().get(1).getUrl()));
        }
        return dtoList;
    }

    private <C> void putObjectToCache(String key, C value) {
        pictureCache.put(key, value);
    }

    private <C> C getObjectFromCache(String key) {
        return (C) pictureCache.get(key);
    }

}
