package sk.dudas.appengine.robecca;

import com.google.gdata.data.photos.PhotoEntry;
import sk.dudas.appengine.robecca.service.PicasaManager;
import sk.dudas.appengine.robecca.service.PicasaManagerImpl;
import sk.dudas.appengine.robecca.service.cache.PhotoDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemPicasaManagerImpl {

	Map<String,List<PhotoDto>> db = new HashMap<String,List<PhotoDto>>();
	
//    Map<Long,Picture> db = new HashMap<Long, Picture>();

}
