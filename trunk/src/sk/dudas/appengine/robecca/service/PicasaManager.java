package sk.dudas.appengine.robecca.service;

import sk.dudas.appengine.robecca.domain.MenuLabel;
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

    List<MenuLabel> getNasaPonukaLabels();

    List<MenuLabel> resetNasaPonukaLabelsCache();

    List<PhotoDto> getNasaPonukaMenuLabel(String albumId);

    public List<PhotoDto> resetNasaPonukaAlbumPhotoDtoList(String albumId);

    String getWelcomePictureUrl();

    void reset();

    List<PhotoDto> getHomePictureUrls();

    List<PhotoDto> getCollectionsPictureUrls();
}
