package sk.dudas.appengine.robecca.controller;

import com.google.gdata.data.photos.AlbumEntry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sk.dudas.appengine.robecca.provider.PicasaProvider;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 3.4.2011
 * Time: 16:46
 * To change this template use File | Settings | File Templates.
 */

@Controller("collectionsController")
public class CollectionsController {
    public static final String USERNAME = "k.drappanova@gmail.com";
    public static final String PASSWORD = "gastonko";

    @RequestMapping(value = "/collections/ladies.htm", method = RequestMethod.GET)
    public void ladies(HttpServletRequest request, ModelMap modelMap) {
        PicasaProvider provider = new PicasaProvider(USERNAME, PASSWORD);
        modelMap.put("picasaPhotoEntries", provider.getAlbumPhotoEntryList("5648133367209219073"));
    }

    @RequestMapping(value = "/collections/handbags.htm", method = RequestMethod.GET)
    public void handbags(HttpServletRequest request, ModelMap modelMap) {
    }

    @RequestMapping(value = "/collections/accessories.htm", method = RequestMethod.GET)
    public void accessories(HttpServletRequest request, ModelMap modelMap) {
    }

    @RequestMapping(value = "/collections/baggages.htm", method = RequestMethod.GET)
    public void baggages(HttpServletRequest request, ModelMap modelMap) {
    }
}
