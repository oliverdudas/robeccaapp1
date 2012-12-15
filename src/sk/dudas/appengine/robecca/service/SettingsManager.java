package sk.dudas.appengine.robecca.service;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import sk.dudas.appengine.robecca.domain.MenuLabel;
import sk.dudas.appengine.robecca.service.cache.PhotoDto;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 8.10.2012
 * Time: 16:40
 * To change this template use File | Settings | File Templates.
 */
public interface SettingsManager {

    MenuLabel storeMenuLabel(MenuLabel menuLabel);

    MenuLabel createMenuLabel(MenuLabel menuLabel);

    MenuLabel updateMenuLabel(MenuLabel menuLabel);

    List<MenuLabel> getMenuLabels();

    void deleteMenuLabel(MenuLabel menuLabel);

    void deleteMenuLabel(Key key);

    void deleteMenuLabel(Long id);

    MenuLabel getMenuLabel(Key key);

}
