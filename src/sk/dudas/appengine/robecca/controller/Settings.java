package sk.dudas.appengine.robecca.controller;

import sk.dudas.appengine.robecca.domain.MenuLabel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oli
 * Date: 14.12.2012
 * Time: 22:12
 * To change this template use File | Settings | File Templates.
 */
public class Settings {

    private List<MenuLabel> menuLabels;

    public List<MenuLabel> getMenuLabels() {
        return menuLabels;
    }

    public void setMenuLabels(List<MenuLabel> menuLabels) {
        this.menuLabels = menuLabels;
    }
}
