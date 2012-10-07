package sk.dudas.appengine.robecca;

import com.google.gdata.data.photos.PhotoEntry;
import sk.dudas.appengine.robecca.service.PicasaManager;
import sk.dudas.appengine.robecca.service.PicasaManagerImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemPicasaManagerImpl implements PicasaManager {

	Map<String,List<PhotoEntry>> db = new HashMap<String,List<PhotoEntry>>();
	
    public List<PhotoEntry> getAlbumPhotoEntryList() {
        return db.get(PicasaManagerImpl.PHOTO_ENTRY_LIST);
    }
}
