package sk.dudas.appengine.robecca.service.cache;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 10/8/12
 * Time: 9:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class PhotoDto implements Serializable {

    private String contentUri;
    private String thumbUrl;

    public PhotoDto(String contentUri, String thumbUrl) {
        this.contentUri = contentUri;
        this.thumbUrl = thumbUrl;
    }

    public String getContentUri() {
        return contentUri;
    }

    public void setContentUri(String contentUri) {
        this.contentUri = contentUri;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }
}
