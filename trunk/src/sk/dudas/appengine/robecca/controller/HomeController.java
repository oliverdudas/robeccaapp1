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

@Controller("homeController")
public class HomeController {

    @Autowired
    private PicasaManager picasaManager;

    @RequestMapping(value = "/home.htm", method = RequestMethod.GET)
    public void home(ModelMap modelMap, HttpServletRequest request) {
        modelMap.put("homePictureUrls", picasaManager.getHomePictureUrls());
    }

    @RequestMapping(value = "/welcome.htm", method = RequestMethod.GET)
    public void welcome(ModelMap modelMap, HttpServletRequest request) {
        modelMap.put("welcomePictureUrl", picasaManager.getWelcomePictureUrl());
    }

    @RequestMapping(value = "/shops.htm", method = RequestMethod.GET)
    public void shops(HttpServletRequest request) {
    }

    @RequestMapping(value = "/where.htm", method = RequestMethod.GET)
    public void where(HttpServletRequest request) {
    }

    @RequestMapping(value = "/reset.htm", method = RequestMethod.GET)
    public String reset(HttpServletRequest request) {
        picasaManager.reset();
        return "redirect:/home.htm";
    }
}
