package sk.dudas.appengine.robecca.service;

import com.google.gdata.data.MediaContent;
import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.data.photos.PhotoEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sk.dudas.appengine.robecca.domain.Album;
import sk.dudas.appengine.robecca.domain.ImgMax;
import sk.dudas.appengine.robecca.domain.MenuLabel;
import sk.dudas.appengine.robecca.domain.RunReset;
import sk.dudas.appengine.robecca.provider.PicasaProvider;
import sk.dudas.appengine.robecca.service.cache.CacheHolder;
import sk.dudas.appengine.robecca.service.cache.PhotoDto;
import sk.dudas.appengine.robecca.service.cache.WebAlbumDto;

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
    public static final String WEB_ALBUM = "webAlbum";
    //    public static final String LADIES_LIST = "ladiesList";
//    public static final String HANDBAGS_LIST = "handbagsList";
//    public static final String BAGGAGES_LIST = "baggagesList";
//    public static final String ACCESSORIES_LIST = "accessoriesList";
    public static final String WELCOME_PICTURE_URL = "welcomePictureUrl";
    public static final String HOME_PICTURE_URLS = "homePictureUrls";
    public static final String COLLECTIONS_PICTURE_URLS = "collectionsPictureUrls";
    private static final String NASA_PONUKA_MENU_LABEL_LIST = "nasaPonukaMenuLabelList";
    private static final int DEFAULT_EXPIRATION = 3600;
    //    private static final String LADIES_ALBUM_ID = "5667859215738016225";
//    private static final String HANDBAGS_ALBUM_ID = "5667859766043741617";
//    private static final String BAGGAGES_ALBUM_ID = "5667860871635428049";
//    private static final String ACCESSORIES_ALBUM_ID = "5667860020300500193";
    private static final String WELCOME_PICTURE_ALBUM_ID = "5667857531879411857";
    private static final String HOME_PICTURES_ALBUM_ID = "5667856893510361217";
    private static final String COLLECTIONS_PICTURES_ALBUM_ID = "5667858842464374081";

    private Cache pictureCache;

    @Autowired
    private RunReset runReset;

    @Autowired
    private SettingsManager settingsManager;

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
//            getLadies();
//            getHandbags();
//            getAccessories();
//            getBaggages();
            getWelcomePictureUrl();
            getHomePictureUrls();
            getCollectionsPictureUrls();
        }
    }

    //-------------------
    //    WEB ALBUM
    //-------------------
    public List<AlbumEntry> getAlbumEntryList(PicasaProvider provider) {
        return provider.getAllAlbumEntryList();
    }

    public WebAlbumDto getWebAlbumDto() {
        WebAlbumDto webAlbumDto = getObjectFromCache(WEB_ALBUM);
        if (webAlbumDto == null) {
            logger.info("Retrieving list of albums from picasa.");
            webAlbumDto = convertAlbumEntryListToWebAlbum(getAlbumEntryList(new PicasaProvider(USERNAME, PASSWORD)));
            logger.info("Putting list of albums to cache.");
            putObjectToCache(WEB_ALBUM, webAlbumDto);
            return webAlbumDto;
        } else {
            logger.info("Returning list of albums from cache.");
            return webAlbumDto;
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

    //--------------------------------------
    //    NASA PONUKA MENU LABEL COLLECTIONS
    //--------------------------------------
    public List<MenuLabel> getNasaPonukaLabels() {
        List<MenuLabel> list = getObjectFromCache(NASA_PONUKA_MENU_LABEL_LIST);
        if (list == null) {
            logger.info("Retrieving nasa ponuka menu labels from datastore.");
            list = settingsManager.getMenuLabels();
            logger.info("Putting nasa ponuka menu labels to cache.");
            putObjectToCache(NASA_PONUKA_MENU_LABEL_LIST, list);
            return list;
        } else {
            logger.info("Returning nasa ponuka labels from cache.");
            return list;
        }
    }

    public List<MenuLabel> resetNasaPonukaLabelsCache() {
        pictureCache.remove(NASA_PONUKA_MENU_LABEL_LIST);
        return getNasaPonukaLabels();
    }

    //--------------------------
    //    NASA PONUKA MENU LABEL
    //--------------------------
    private List<PhotoEntry> getNasaPonukaMenuLabel(PicasaProvider provider, String albumId) {
        return provider.getAlbumPhotoEntryList(albumId, "?imgmax=" + ImgMax.s1024.getMaxSize());
    }

    public List<PhotoDto> getNasaPonukaMenuLabel(String albumId) {
        List<PhotoDto> list = getObjectFromCache(albumId);
        if (list == null) {
            logger.info("Retrieving ponuka menu label from picasa.");
            list = convertPhotoEntryListToPhotoDtoList(getNasaPonukaMenuLabel(new PicasaProvider(USERNAME, PASSWORD), albumId));
            logger.info("Putting ponuka menu label to cache.");
            putObjectToCache(albumId, list);
            return list;
        } else {
            logger.info("Returning ponuka menu label from cache.");
            return list;
        }
    }

    public List<PhotoDto> resetNasaPonukaAlbumPhotoDtoList(String albumId) {
        pictureCache.remove(albumId);
        return getNasaPonukaMenuLabel(albumId);
    }

//    ---------------------
//    ---------------------
//    ---------------------

    private List<PhotoDto> convertPhotoEntryListToPhotoDtoList(List<PhotoEntry> entryList) {
        List<PhotoDto> dtoList = new ArrayList<PhotoDto>();
        for (PhotoEntry photoEntry : entryList) {
            dtoList.add(new PhotoDto(((MediaContent) photoEntry.getContent()).getUri(), photoEntry.getMediaThumbnails().get(1).getUrl()));
        }
        return dtoList;
    }

    private WebAlbumDto convertAlbumEntryListToWebAlbum(List<AlbumEntry> albumEntryList) {
        List<Album> albums = new ArrayList<Album>();
        for (AlbumEntry albumEntry : albumEntryList) {
            albums.add(new Album(albumEntry.getGphotoId(), albumEntry.getTitle().getPlainText()));
        }
        return new WebAlbumDto(albums);
    }

    private <C> void putObjectToCache(String key, C value) {
        pictureCache.put(key, value);
    }

    private <C> C getObjectFromCache(String key) {
        return (C) pictureCache.get(key);
    }

}
