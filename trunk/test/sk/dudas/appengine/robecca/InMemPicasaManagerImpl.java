package sk.dudas.appengine.robecca;

import com.google.gdata.data.photos.PhotoEntry;
import sk.dudas.appengine.robecca.service.PicasaManager;
import sk.dudas.appengine.robecca.service.PicasaManagerImpl;
import sk.dudas.appengine.robecca.service.cache.PhotoDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemPicasaManagerImpl {

	Map<String,List<PhotoEntry>> db = new HashMap<String,List<PhotoEntry>>();
	
//    public List<PhotoDto> getLadies() {
//        return db.get(PicasaManagerImpl.LADIES_LIST);
//    }
}
