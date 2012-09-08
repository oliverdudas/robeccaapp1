package sk.dudas.appengine.robecca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/home.htm", method = RequestMethod.GET)
    public void home(HttpServletRequest request) {
    }

    @RequestMapping(value = "/welcome.htm", method = RequestMethod.GET)
    public void welcome(HttpServletRequest request) {
    }

    @RequestMapping(value = "/collections/collections.htm", method = RequestMethod.GET)
    public void collections(HttpServletRequest request) {
    }

    @RequestMapping(value = "/shops.htm", method = RequestMethod.GET)
    public void shops(HttpServletRequest request) {
    }

    @RequestMapping(value = "/where.htm", method = RequestMethod.GET)
    public void where(HttpServletRequest request) {
    }
}
