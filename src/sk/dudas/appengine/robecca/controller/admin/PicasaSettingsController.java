package sk.dudas.appengine.robecca.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: oli
 * Date: 18.12.2012
 * Time: 14:13
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class PicasaSettingsController {

    @RequestMapping(value = "/admin/picasa.htm", method = RequestMethod.GET)
    public void list(ModelMap modelMap) {
    }

    @RequestMapping(value = "/admin/picasa.htm", method = RequestMethod.POST)
    public String save(
//            @ModelAttribute(value = "command") MenuLabel menuLabel,
            BindingResult bindingResult,
            SessionStatus sessionStatus,
            HttpServletRequest request) {

        return "redirect:/admin/picasa.htm";
    }
}
