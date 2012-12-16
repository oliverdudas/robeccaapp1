package sk.dudas.appengine.robecca.domain;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: oli
 * Date: 15.12.2012
 * Time: 23:55
 * To change this template use File | Settings | File Templates.
 */
public class Album implements Serializable {

    private String id;
    private String title;

    public Album(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIdAndTitle() {
        return this.id + "," + this.title;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
