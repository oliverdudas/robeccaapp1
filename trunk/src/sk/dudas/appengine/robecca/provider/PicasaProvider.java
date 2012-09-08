package sk.dudas.appengine.robecca.provider;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.MediaContent;
import com.google.gdata.data.photos.*;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import sk.dudas.appengine.robecca.domain.ImgMax;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 16.4.2011
 * Time: 21:09
 * To change this template use File | Settings | File Templates.
 */
public class PicasaProvider {

    private static final String DEFAULT_USER_ENTRY_URL = getDefaultUserUrl("entry");
    private static final String DEFAULT_USER_FEED_URL = getDefaultUserUrl("feed");
    private static final String KIND_ALBUM = "?kind=album";
    private static final String DEFAULT_USER_ALBUM_ENTRY_URL = DEFAULT_USER_ENTRY_URL + "/albumid/";
    private static final String DEFAULT_USER_ALBUM_FEED_URL = DEFAULT_USER_FEED_URL + "/albumid/";
    private static final String MALFORMED_URLEXCEPTION = "MalformedURLException: ";
    private static final String IOEXCEPTION = "IOException: ";
    private static final String SERVICE_EXCEPTION = "ServiceException: ";
    private static final String AUTHENTICATION_EXCEPTION = "AuthenticationException: ";
    private static final String DEFAULT_PICASA_SERVICE_NAME = "defaultPicasaService";
    private PicasawebService picasawebService;
    private UserEntry userEntryCache = null;
    private UserFeed userFeedCache = null;
    private List<AlbumEntry> allAlbumEntryListCache = null;
    private List<AlbumEntry> albumEntryCacheList = null;
    private List<AlbumFeed> albumFeedCacheList = null;
    //    private List<PhotoEntry> allPhotoEntryListCache = null;
    private List<PhotoEntry> photoEntryCacheList = null;

    private static final Logger _logger = Logger.getLogger(PicasaProvider.class.getName());

    public PicasaProvider(String username, String password) {
        init(username, password, DEFAULT_PICASA_SERVICE_NAME);
    }

    public PicasaProvider(String username, String password, String serviceName) {
        init(username, password, serviceName);
    }

    private void init(String username, String password, String serviceName) {
        _logger.log(Level.INFO, "PicasaProvider.init");
        PicasawebService picasawebService = new PicasawebService(serviceName);
        try {
            picasawebService.setUserCredentials(username, password);
            this.picasawebService = picasawebService;
        } catch (AuthenticationException e) {
            throw new RuntimeException(AUTHENTICATION_EXCEPTION + e.getMessage());
        }
    }

    private static String getDefaultUserUrl(String type) {
        return "https://picasaweb.google.com/data/" + type + "/api/user/default";
    }

    /**
     * UserEntry
     *
     * @return
     */
    public UserEntry getUserEntry() {
        _logger.log(Level.INFO, "PicasaProvider.getUserEntry - start");
        if (userEntryCache != null) {
            _logger.log(Level.INFO, "PicasaProvider.getUserEntry - returns userEntry from cache");
            return userEntryCache;
        }
        try {
            UserEntry userEntry = picasawebService.getEntry(new URL(DEFAULT_USER_ENTRY_URL), UserEntry.class);
            this.userEntryCache = userEntry;
            _logger.log(Level.INFO, "PicasaProvider.getUserEntry - returns userEntry from picasawebservice");
            return userEntry;
        } catch (IOException e) {
            throw new RuntimeException(IOEXCEPTION + e.getMessage());
        } catch (ServiceException e) {
            throw new RuntimeException(SERVICE_EXCEPTION + e.getMessage());
        }
    }

    /**
     * UserFeed
     *
     * @return
     */
    private UserFeed getUserFeed() {
        _logger.log(Level.INFO, "PicasaProvider.getUserFeed - start");
        if (userFeedCache != null) {
            _logger.log(Level.INFO, "PicasaProvider.getUserFeed - returns userEntry from cache");
            return userFeedCache;
        }
        try {
            UserFeed userFeed = picasawebService.getFeed(new URL(DEFAULT_USER_FEED_URL + KIND_ALBUM), UserFeed.class);
            this.userFeedCache = userFeed;
            _logger.log(Level.INFO, "PicasaProvider.getUserFeed - returns userEntry from picasawebservice");
            return userFeed;
        } catch (IOException e) {
            throw new RuntimeException(IOEXCEPTION + e.getMessage());
        } catch (ServiceException e) {
            throw new RuntimeException(SERVICE_EXCEPTION + e.getMessage());
        }
    }

    /**
     * AlbumEntryList
     *
     * @return
     */
    public List<AlbumEntry> getAllAlbumEntryList() {
        _logger.log(Level.INFO, "PicasaProvider.getAllAlbumEntryList - start");
        if (allAlbumEntryListCache != null) {
            _logger.log(Level.INFO, "PicasaProvider.getAllAlbumEntryList - returns userEntry from cache");
            return allAlbumEntryListCache;
        }
        List<AlbumEntry> albumEntryList = getUserFeed().getAlbumEntries();
        this.allAlbumEntryListCache = albumEntryList;
        _logger.log(Level.INFO, "PicasaProvider.getAllAlbumEntryList - returns userEntry from picasawebservice");
        return albumEntryList;
    }

    /**
     * AlbumEntry
     *
     * @param albumId
     * @return
     */
    public AlbumEntry getAlbumEntry(String albumId) {
        if (albumEntryCacheList != null) {
            AlbumEntry cachedAlbumEntry = getCachedAlbumEntry(albumId);
            if (cachedAlbumEntry != null) {
                return cachedAlbumEntry;
            }
        }
        try {
            URL albumEntryUrl = new URL(DEFAULT_USER_ALBUM_ENTRY_URL + albumId);
            AlbumEntry albumEntry = picasawebService.getEntry(albumEntryUrl, AlbumEntry.class);
            addAlbumEntryToCache(albumEntry);
            return albumEntry;
        } catch (MalformedURLException e) {
            throw new RuntimeException(MALFORMED_URLEXCEPTION + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(IOEXCEPTION + e.getMessage());
        } catch (ServiceException e) {
            throw new RuntimeException(SERVICE_EXCEPTION + e.getMessage());
        }
    }

    /**
     * AlbumEntry
     *
     * @param photoEntry
     * @return
     */
    public AlbumEntry getAlbumEntry(PhotoEntry photoEntry) {
        String albumId = getAlbumId(photoEntry);
        return getAlbumEntry(albumId);
    }

    /**
     * Cached albumEntry
     *
     * @param gphotoId
     * @return
     */
    private AlbumEntry getCachedAlbumEntry(String gphotoId) {
        for (AlbumEntry cachedAlbumEntry : albumEntryCacheList) {
            if (cachedAlbumEntry.getGphotoId().equals(gphotoId)) {
                return cachedAlbumEntry;
            }
        }

        return null;
    }

    /**
     * Cached albumFeed
     *
     * @param gphotoId
     * @return
     */
    private AlbumFeed getCachedAlbumFeed(String gphotoId) {
        for (AlbumFeed cachedAlbumFeed : albumFeedCacheList) {
            if (cachedAlbumFeed.getGphotoId().equals(gphotoId)) {
                return cachedAlbumFeed;
            }
        }

        return null;
    }

    /**
     * Store AlbumEntry in cache
     *
     * @param gphotoEntry
     */
    private void addAlbumEntryToCache(AlbumEntry gphotoEntry) {
        if (albumEntryCacheList == null) {
            albumEntryCacheList = new ArrayList<AlbumEntry>();
        }
        albumEntryCacheList.add(gphotoEntry);
    }

    /**
     * Store AlbumFeed in cache
     *
     * @param albumFeed
     */
    private void addAlbumFeedToCache(AlbumFeed albumFeed) {
        if (albumFeedCacheList == null) {
            albumFeedCacheList = new ArrayList<AlbumFeed>();
        }
        albumFeedCacheList.add(albumFeed);
    }

    /**
     * Album id
     *
     * @param albumEntry
     * @return
     */
    public String getAlbumId(AlbumEntry albumEntry) {
        return albumEntry.getGphotoId();
    }

    /**
     * Album id
     *
     * @param albumFeed
     * @return
     */
    public String getAlbumId(AlbumFeed albumFeed) {
        return albumFeed.getGphotoId();
    }

    public String getAlbumId(PhotoEntry photoEntry) {
        return photoEntry.getAlbumId();
    }

    public String getPhotoId(PhotoEntry photoEntry) {
        return photoEntry.getGphotoId();
    }

    /**
     * AlbumFeed
     *
     * @param albumId
     * @return
     */
    private AlbumFeed getAlbumFeed(String albumId) {
        if (albumFeedCacheList != null) {
            AlbumFeed cachedAlbumFeed = getCachedAlbumFeed(albumId);
            if (cachedAlbumFeed != null) {
                return cachedAlbumFeed;
            }
        }
        try {
            URL albumFeedUrl = new URL(DEFAULT_USER_ALBUM_FEED_URL + albumId);
            AlbumFeed albumFeed = picasawebService.getFeed(albumFeedUrl, AlbumFeed.class);
            addAlbumFeedToCache(albumFeed);
            return albumFeed;
        } catch (IOException e) {
            throw new RuntimeException(IOEXCEPTION + e.getMessage());
        } catch (ServiceException e) {
            throw new RuntimeException(SERVICE_EXCEPTION + e.getMessage());
        }
    }

    /**
     * PhotoEntryList
     *
     * @param albumId
     * @return
     */
    public List<PhotoEntry> getAlbumPhotoEntryList(String albumId) {
//        if (allPhotoEntryListCache != null) { todo: cachovanie dajak vymysliet
//            return allPhotoEntryListCache;
//        }
        AlbumFeed albumFeed = getAlbumFeed(albumId);
        List<PhotoEntry> photoEntryList = albumFeed.getPhotoEntries();
//        this.allPhotoEntryListCache = photoEntryList;
        return photoEntryList;
    }

    /**
     * PhotoEntry
     *
     * @param albumId
     * @param photoId
     * @return
     */
    public PhotoEntry getPhotoEntry(String albumId, String photoId) {
        if (photoEntryCacheList != null) {
            PhotoEntry cachedPhotoEntry = getCachedPhotoEntry(albumId);
            if (cachedPhotoEntry != null) {
                return cachedPhotoEntry;
            }
        }
        try {
            PhotoEntry photoEntry = picasawebService.getEntry(new URL(DEFAULT_USER_ALBUM_ENTRY_URL + albumId + "/photoid/" + photoId), PhotoEntry.class);
            addPhotoEntryToCache(photoEntry);
            return photoEntry;
        } catch (IOException e) {
            throw new RuntimeException(IOEXCEPTION + e.getMessage());
        } catch (ServiceException e) {
            throw new RuntimeException(SERVICE_EXCEPTION + e.getMessage());
        }
    }

    /**
     * Cached photoEntry
     *
     * @param gphotoId
     * @return
     */
    private PhotoEntry getCachedPhotoEntry(String gphotoId) {
        for (PhotoEntry cachedPhotoEntry : photoEntryCacheList) {
            if (cachedPhotoEntry.getGphotoId().equals(gphotoId)) {
                return cachedPhotoEntry;
            }
        }

        return null;
    }

    /**
     * Store photoEntry in cache
     *
     * @param gphotoEntry
     */
    private void addPhotoEntryToCache(PhotoEntry gphotoEntry) {
        if (photoEntryCacheList == null) {
            photoEntryCacheList = new ArrayList<PhotoEntry>();
        }
        photoEntryCacheList.add(gphotoEntry);
    }

    public String getPhotoLink(String albumId, String photoId) {
        PhotoEntry photoEntry = getPhotoEntry(albumId, photoId);
        return getPhotoLink(photoEntry);
    }

    public String getPhotoLink(AlbumEntry albumEntry, String photoId) {
        String albumId = getAlbumId(albumEntry);
        PhotoEntry photoEntry = getPhotoEntry(albumId, photoId);
        return getPhotoLink(photoEntry);
    }

    public String getPhotoLink(PhotoEntry photoEntry) {
        return ((MediaContent) photoEntry.getContent()).getUri();
    }

    public String getPhotoLink(String albumId, String photoId, ImgMax imgMax) {
        try {
            PhotoEntry entry = picasawebService.getEntry(new URL(DEFAULT_USER_ALBUM_ENTRY_URL + albumId + "/photoid/" + photoId + "?imgmax=" + imgMax.getMaxSize()), PhotoEntry.class);
            return getPhotoLink(entry);
        } catch (IOException e) {
            throw new RuntimeException(IOEXCEPTION + e.getMessage());
        } catch (ServiceException e) {
            throw new RuntimeException(SERVICE_EXCEPTION + e.getMessage());
        }
    }

    public String getPhotoLink(AlbumEntry albumEntry, String photoId, ImgMax imgMax) {
        String albumId = getAlbumId(albumEntry);
        return getPhotoLink(albumId, photoId, imgMax);
    }

    public String getPhotoLink(PhotoEntry photoEntry, ImgMax imgMax) {
        String albumId = getAlbumId(photoEntry);
        String photoId = getPhotoId(photoEntry);
        return getPhotoLink(albumId, photoId, imgMax);
    }

}
