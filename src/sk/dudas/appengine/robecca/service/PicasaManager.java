package sk.dudas.appengine.robecca.service;

import com.google.gdata.data.photos.PhotoEntry;
import sk.dudas.appengine.robecca.provider.PicasaProvider;
import sk.dudas.appengine.robecca.service.cache.PhotoDto;
import sk.dudas.appengine.robecca.service.cache.WebAlbumDto;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 8.10.2012
 * Time: 16:40
 * To change this template use File | Settings | File Templates.
 */
public interface PicasaManager {

    WebAlbumDto getWebAlbumDto();

    List<PhotoDto> getLadies();

    List<PhotoDto> getHandbags();

    List<PhotoDto> getBaggages();

    List<PhotoDto> getAccessories();

    String getWelcomePictureUrl();

    void reset();

    List<PhotoDto> getHomePictureUrls();

    List<PhotoDto> getCollectionsPictureUrls();
}
