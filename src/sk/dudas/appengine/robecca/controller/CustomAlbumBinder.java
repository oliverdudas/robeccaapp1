package sk.dudas.appengine.robecca.controller;

import org.apache.commons.lang.StringUtils;
import sk.dudas.appengine.robecca.domain.Album;

import java.beans.PropertyEditorSupport;

/**
 * Created with IntelliJ IDEA.
 * User: oli
 * Date: 16.12.2012
 * Time: 1:25
 * To change this template use File | Settings | File Templates.
 */
public class CustomAlbumBinder extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        Album value = (Album) getValue();
        if (value != null) {
            return value.getIdAndTitle();
        }
        return "";
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isNotBlank(text)) {
            String[] split = text.split(",");
            String id = split[0];
            String title = split[1];
            setValue(new Album(id, title));
        } else {
            setValue(null);
        }
    }
}
