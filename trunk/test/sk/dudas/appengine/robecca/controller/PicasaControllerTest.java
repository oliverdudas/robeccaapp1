package sk.dudas.appengine.robecca.controller;

import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.data.photos.PhotoEntry;
import com.google.gdata.util.ServiceException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sk.dudas.appengine.robecca.domain.ImgMax;
import sk.dudas.appengine.robecca.provider.PicasaProvider;
import sk.dudas.appengine.robecca.service.PicasaManager;

import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 8.10.2012
 * Time: 16:25
 * To change this template use File | Settings | File Templates.
 */
public class PicasaControllerTest extends BaseControllerTest {

    private static final String USERNAME = "womans@womans.sk";
    private static final String PASSWORD = "womans852cs";
    private static final String WELCOME_PICTURE_ALBUM_ID = "5667857531879411857";

    @Autowired
    private PicasaManager picasaManager;

    @Test
    public void testPicasa() throws ServiceException, IOException {
//        PicasawebService picasawebService = PicasawebServiceUtil.getPicasawebService("picasService", USERNAME, PASSWORD);
        PicasaProvider provider = new PicasaProvider(USERNAME, PASSWORD);
        AlbumEntry albumEntry = provider.getAlbumEntry(WELCOME_PICTURE_ALBUM_ID, "?thumbsize=" + ImgMax.s512.getMaxSize());

        System.out.println(albumEntry.getMediaThumbnails().get(0).getUrl());
    }

    @Test
    public void testPhotoEntryList() throws Exception {
//        List<PhotoEntry> list = picasaManager.getLadies();
//        System.out.println("SIZE: " + list.size());
    }
}
