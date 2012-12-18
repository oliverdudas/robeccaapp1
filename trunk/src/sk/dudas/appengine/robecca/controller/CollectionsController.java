package sk.dudas.appengine.robecca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sk.dudas.appengine.robecca.domain.MenuLabel;
import sk.dudas.appengine.robecca.service.PicasaManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 3.4.2011
 * Time: 16:46
 * To change this template use File | Settings | File Templates.
 */

@Controller("collectionsController")
public class CollectionsController {

    private static final String COLLECTION_VIEW = "collections/collection";

    @Autowired
    private PicasaManager picasaManager;

    @RequestMapping(value = "/collections/collections.htm", method = RequestMethod.GET)
    public void collections(ModelMap modelMap) {
        modelMap.put("menuLabels", picasaManager.getNasaPonukaLabels());
        modelMap.put("collectionsPictureUrls", picasaManager.getCollectionsPictureUrls());
    }

    @RequestMapping(value = "/collections/album.htm", method = RequestMethod.GET)
    public String menuLabel(@RequestParam Long id,
                            ModelMap modelMap) {
        List<MenuLabel> menuLabels = picasaManager.getNasaPonukaLabels();
        MenuLabel menuLabel = getMenuLabelById(id, menuLabels);
        modelMap.put("menuLabels", menuLabels);
        modelMap.put("dtos", picasaManager.getNasaPonukaMenuLabel(menuLabel.getAlbum().getId()));
        modelMap.put("collectionTitleKey", menuLabel.getName());
        return COLLECTION_VIEW;
    }

    private MenuLabel getMenuLabelById(Long id, List<MenuLabel> menuLabels) {
        for (MenuLabel menuLabel : menuLabels) {
            if (menuLabel.getId().compareTo(id) == 0) {
                return menuLabel;
            }
        }

        throw new RuntimeException("No menu label exist with id: " + id + ". Try to reset the cache!");
    }

}
