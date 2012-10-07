package sk.dudas.appengine.robecca.service;

import com.google.gdata.data.photos.PhotoEntry;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 8.10.2012
 * Time: 16:40
 * To change this template use File | Settings | File Templates.
 */
public interface PicasaManager {

    List<PhotoEntry> getAlbumPhotoEntryList();
}
