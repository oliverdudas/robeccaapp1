package sk.dudas.appengine.robecca.service.cache;

import sk.dudas.appengine.robecca.domain.Album;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oli
 * Date: 15.12.2012
 * Time: 23:54
 * To change this template use File | Settings | File Templates.
 */
public class WebAlbumDto implements Serializable {

    private List<Album> albums;

    public WebAlbumDto(List<Album> albums) {
        this.albums = albums;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
