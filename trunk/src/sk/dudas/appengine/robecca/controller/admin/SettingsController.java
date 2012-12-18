package sk.dudas.appengine.robecca.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import sk.dudas.appengine.robecca.domain.Album;
import sk.dudas.appengine.robecca.domain.MenuLabel;
import sk.dudas.appengine.robecca.service.PicasaManager;
import sk.dudas.appengine.robecca.service.SettingsManager;
import sk.dudas.appengine.robecca.service.cache.WebAlbumDto;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 3.4.2011
 * Time: 16:46
 * To change this template use File | Settings | File Templates.
 */

@Controller("settingsController")
public class SettingsController {

    @Autowired
    private SettingsManager settingsManager;
    @Autowired
    private PicasaManager picasaManager;

    @InitBinder(value = "command")
    public void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(String.class, "password", new CustomUserPasswordBinder());
        binder.registerCustomEditor(Album.class, new CustomAlbumBinder());
    }

    @ModelAttribute(value = "labels")
    public List<MenuLabel> getMenulabels() {
        return settingsManager.getMenuLabels();
    }

    @ModelAttribute(value = "webAlbum")
    public WebAlbumDto getWebAlbum() {
        return picasaManager.getWebAlbumDto();
    }

    @RequestMapping(value = "/admin/ponuka.htm", method = RequestMethod.GET)
    public void list(ModelMap modelMap) {
        modelMap.addAttribute("command", new MenuLabel());
        int size = getMenulabels().size();
        modelMap.addAttribute("labelsSize", size);
    }

    @RequestMapping(value = "/admin/ponukaForm.htm", method = RequestMethod.POST)
    public String save(@ModelAttribute(value = "command") MenuLabel menuLabel,
                       BindingResult bindingResult,
                       SessionStatus sessionStatus,
                       HttpServletRequest request) {

        new MenuLabelValidator().validate(menuLabel, bindingResult);
        if (!bindingResult.hasErrors()) {
            settingsManager.storeMenuLabel(menuLabel);
            settingsManager.resetCaches(menuLabel.getAlbum().getId());
            sessionStatus.setComplete();
            return "redirect:/admin/ponuka.htm";
        } else {
            int size = getMenulabels().size();
            request.setAttribute("labelsSize", size);
            request.setAttribute("labelsSizeItems", createLabelSizeItems(menuLabel.isNew()));
            request.setAttribute("hasErrors", true);
            return "admin/ponuka";
        }
    }

    private List<Long> createLabelSizeItems(boolean isNew) {
        List<Long> labelSizeItems = new ArrayList<Long>();
        int size = isNew ? getMenulabels().size() + 1 : getMenulabels().size();
        for (long i = 1; i < size + 1; i++) {
            labelSizeItems.add(i);
        }
        return labelSizeItems;
    }

    @RequestMapping(value = "/admin/ponukaDelete.htm", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id") Long id) {
        settingsManager.deleteMenuLabel(id);
        return "redirect:/admin/ponuka.htm";
    }

}
