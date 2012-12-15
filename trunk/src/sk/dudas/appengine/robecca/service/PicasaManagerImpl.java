package sk.dudas.appengine.robecca.service;

import com.google.gdata.data.MediaContent;
import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.data.photos.PhotoEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sk.dudas.appengine.robecca.domain.ImgMax;
import sk.dudas.appengine.robecca.domain.RunReset;
import sk.dudas.appengine.robecca.provider.PicasaProvider;
import sk.dudas.appengine.robecca.service.cache.CacheHolder;
import sk.dudas.appengine.robecca.service.cache.PhotoDto;

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
    public static final String LADIES_LIST = "ladiesList";
    public static final String HANDBAGS_LIST = "handbagsList";
    public static final String BAGGAGES_LIST = "baggagesList";
    public static final String ACCESSORIES_LIST = "accessoriesList";
    public static final String WELCOME_PICTURE_URL = "welcomePictureUrl";
    public static final String HOME_PICTURE_URLS = "homePictureUrls";
    public static final String COLLECTIONS_PICTURE_URLS = "collectionsPictureUrls";
    private static final int DEFAULT_EXPIRATION = 3600;
    private static final String LADIES_ALBUM_ID = "5667859215738016225";
    private static final String HANDBAGS_ALBUM_ID = "5667859766043741617";
    private static final String BAGGAGES_ALBUM_ID = "5667860871635428049";
    private static final String ACCESSORIES_ALBUM_ID = "5667860020300500193";
    private static final String WELCOME_PICTURE_ALBUM_ID = "5667857531879411857";
    private static final String HOME_PICTURES_ALBUM_ID = "5667856893510361217";
    private static final String COLLECTIONS_PICTURES_ALBUM_ID = "5667858842464374081";

    private Cache pictureCache;

    @Autowired
    private RunReset runReset;

    @Autowired
    public PicasaManagerImpl(@Qualifier("pictureCacheHolder") CacheHolder pictureCacheHolder) {
        this.pictureCache = pictureCacheHolder.getCache();
    }

    @SuppressWarnings("unused")
    @PostConstruct
    private void fillCache() {
        reset();
    }

    public void reset() {
        if (runReset.isRun()) {
            logger.info("Clearing pictureCache.");
            pictureCache.clear();

            logger.info("Filling caches.");
            getLadies();
            getHandbags();
            getAccessories();
            getBaggages();
            getWelcomePictureUrl();
            getHomePictureUrls();
            getCollectionsPictureUrls();
        }
    }

    //-------------------
    //    WELCOME PICTURE
    //-------------------
    public String getWelcomePictureUrl(PicasaProvider provider) {
        AlbumEntry albumEntry = provider.getAlbumEntry(WELCOME_PICTURE_ALBUM_ID, "?thumbsize=" + ImgMax.s512.getMaxSize());
        return albumEntry.getMediaThumbnails().get(0).getUrl();
    }

    public String getWelcomePictureUrl() {
        String picture = getObjectFromCache(WELCOME_PICTURE_URL);
        if (picture == null) {
            logger.info("Retrieving welcome picture from picasa.");
            picture = getWelcomePictureUrl(new PicasaProvider(USERNAME, PASSWORD));
            logger.info("Putting welcome picture to cache.");
            putObjectToCache(WELCOME_PICTURE_URL, picture);
            return picture;
        } else {
            logger.info("Returning welcome picture from cache.");
            return picture;
        }
    }

    //-----------------
    //    HOME PICTURES
    //-----------------
    public List<PhotoEntry> getHomePictureUrls(PicasaProvider provider) {
        return provider.getAlbumPhotoEntryList(HOME_PICTURES_ALBUM_ID);
    }

    public List<PhotoDto> getHomePictureUrls() {
        List<PhotoDto> list = getObjectFromCache(HOME_PICTURE_URLS);
        if (list == null) {
            logger.info("Retrieving home pictures from picasa.");
            list = convertPhotoEntryListToPhotoDtoList(getHomePictureUrls(new PicasaProvider(USERNAME, PASSWORD)));
            logger.info("Putting home pictures to cache.");
            putObjectToCache(HOME_PICTURE_URLS, list);
            return list;
        } else {
            logger.info("Returning home pictures from cache.");
            return list;
        }
    }

    //-----------------
    //    COLLECTIONS PICTURES
    //-----------------
    public List<PhotoEntry> getCollectionsPictureUrls(PicasaProvider provider) {
        return provider.getAlbumPhotoEntryList(COLLECTIONS_PICTURES_ALBUM_ID);
    }

    public List<PhotoDto> getCollectionsPictureUrls() {
        List<PhotoDto> list = getObjectFromCache(COLLECTIONS_PICTURE_URLS);
        if (list == null) {
            logger.info("Retrieving collections pictures from picasa.");
            list = convertPhotoEntryListToPhotoDtoList(getCollectionsPictureUrls(new PicasaProvider(USERNAME, PASSWORD)));
            logger.info("Putting collections pictures to cache.");
            putObjectToCache(COLLECTIONS_PICTURE_URLS, list);
            return list;
        } else {
            logger.info("Returning collections pictures from cache.");
            return list;
        }
    }

    //----------
    //    LADIES
    //----------
    private List<PhotoEntry> getLadies(PicasaProvider provider) {
        return provider.getAlbumPhotoEntryList(LADIES_ALBUM_ID, "?imgmax=" + ImgMax.s1024.getMaxSize());
    }

    public List<PhotoDto> getLadies() {
        List<PhotoDto> list = getObjectFromCache(LADIES_LIST);
        if (list == null) {
            logger.info("Retrieving ladies from picasa.");
            list = convertPhotoEntryListToPhotoDtoList(getLadies(new PicasaProvider(USERNAME, PASSWORD)));
            logger.info("Putting ladies to cache.");
            putObjectToCache(LADIES_LIST, list);
            return list;
        } else {
            logger.info("Returning ladies from cache.");
            return list;
        }
    }

    //------------
    //    HANDBAGS
    //------------
    private List<PhotoEntry> getHandbags(PicasaProvider provider) {
        return provider.getAlbumPhotoEntryList(HANDBAGS_ALBUM_ID, "?imgmax=" + ImgMax.s1024.getMaxSize());
    }

    public List<PhotoDto> getHandbags() {
        List<PhotoDto> list = getObjectFromCache(HANDBAGS_LIST);
        if (list == null) {
            logger.info("Retrieving handbags from picasa.");
            list = convertPhotoEntryListToPhotoDtoList(getHandbags(new PicasaProvider(USERNAME, PASSWORD)));
            logger.info("Putting handbags to cache.");
            putObjectToCache(HANDBAGS_LIST, list);
            return list;
        } else {
            logger.info("Returning handbags from cache.");
            return list;
        }
    }

    //------------
    //    BAGGAGES
    //------------
    private List<PhotoEntry> getBaggages(PicasaProvider provider) {
        return provider.getAlbumPhotoEntryList(BAGGAGES_ALBUM_ID, "?imgmax=" + ImgMax.s1024.getMaxSize());
    }

    public List<PhotoDto> getBaggages() {
        List<PhotoDto> list = getObjectFromCache(BAGGAGES_LIST);
        if (list == null) {
            logger.info("Retrieving baggages from picasa.");
            list = convertPhotoEntryListToPhotoDtoList(getBaggages(new PicasaProvider(USERNAME, PASSWORD)));
            logger.info("Putting baggages to cache.");
            putObjectToCache(BAGGAGES_LIST, list);
            return list;
        } else {
            logger.info("Returning baggages from cache.");
            return list;
        }
    }

    //---------------
    //    ACCESSORIES
    //---------------
    private List<PhotoEntry> getAccessories(PicasaProvider provider) {
        return provider.getAlbumPhotoEntryList(ACCESSORIES_ALBUM_ID, "?imgmax=" + ImgMax.s1024.getMaxSize());
    }

    public List<PhotoDto> getAccessories() {
        List<PhotoDto> list = getObjectFromCache(ACCESSORIES_LIST);
        if (list == null) {
            logger.info("Retrieving accessories from picasa.");
            list = convertPhotoEntryListToPhotoDtoList(getAccessories(new PicasaProvider(USERNAME, PASSWORD)));
            logger.info("Putting accessories to cache.");
            putObjectToCache(ACCESSORIES_LIST, list);
            return list;
        } else {
            logger.info("Returning accessories from cache.");
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
