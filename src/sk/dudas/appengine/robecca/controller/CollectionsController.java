package sk.dudas.appengine.robecca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sk.dudas.appengine.robecca.service.PicasaManager;

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

    private static final String COLLECTION_VIEW = "collections/collection";

    @Autowired
    private PicasaManager picasaManager;

    @RequestMapping(value = "/collections/ladies.htm", method = RequestMethod.GET)
    public String ladies(HttpServletRequest request, ModelMap modelMap) {
        modelMap.put("dtos", picasaManager.getLadies());
        modelMap.put("collectionTitleKey", "ladys.collection");
        return COLLECTION_VIEW;
    }

    @RequestMapping(value = "/collections/handbags.htm", method = RequestMethod.GET)
    public String handbags(HttpServletRequest request, ModelMap modelMap) {
        modelMap.put("dtos", picasaManager.getHandbags());
        modelMap.put("collectionTitleKey", "ladys.handbags");
        return COLLECTION_VIEW;
    }

    @RequestMapping(value = "/collections/accessories.htm", method = RequestMethod.GET)
    public String accessories(HttpServletRequest request, ModelMap modelMap) {
        modelMap.put("dtos", picasaManager.getAccessories());
        modelMap.put("collectionTitleKey", "ladys.accessories");
        return COLLECTION_VIEW;
    }

    @RequestMapping(value = "/collections/baggages.htm", method = RequestMethod.GET)
    public String baggages(HttpServletRequest request, ModelMap modelMap) {
        modelMap.put("dtos", picasaManager.getBaggages());
        modelMap.put("collectionTitleKey", "ladys.baggages");
        return COLLECTION_VIEW;
    }
}
