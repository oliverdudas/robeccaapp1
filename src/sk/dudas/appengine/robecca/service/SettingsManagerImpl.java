package sk.dudas.appengine.robecca.service;

import com.google.appengine.api.datastore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.dudas.appengine.robecca.domain.MenuLabel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oli
 * Date: 9.12.2012
 * Time: 16:32
 * To change this template use File | Settings | File Templates.
 */
@Service("settingsManager")
public class SettingsManagerImpl implements SettingsManager {

    @Autowired
    private PicasaManager picasaManager;

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

    public MenuLabel storeMenuLabel(MenuLabel menuLabel) {
        if (menuLabel.isNew()) {
            return createMenuLabel(menuLabel);
        } else {
            return updateMenuLabel(menuLabel);
        }
    }

    public MenuLabel updateMenuLabel(MenuLabel menuLabel) {
        Transaction txn = datastore.beginTransaction();
        try {
            List<MenuLabel> menuLabels = getMenuLabels();

            removeFromOrder(menuLabels, menuLabel);
            updateOrder(menuLabels, menuLabel);

            datastore.put(MenuLabel.toEntities(menuLabels));

            txn.commit();
            return menuLabel;
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

    public MenuLabel createMenuLabel(MenuLabel menuLabel) {
        Transaction txn = datastore.beginTransaction();
        try {
            // assigning id to new menuLabel
            KeyRange keys = datastore.allocateIds(MenuLabel.ancestorKey(), MenuLabel.getKind(), 1);
            Key key = keys.getStart();
            menuLabel.setId(key.getId());

            // updating order of all menuLabels (not presisted!)
            List<MenuLabel> menuLabels = getMenuLabels();
            updateOrder(menuLabels, menuLabel);

            datastore.put(MenuLabel.toEntities(menuLabels));

            txn.commit();
            return menuLabel;
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

    public void resetCaches(String albumId) {
        picasaManager.resetNasaPonukaLabelsCache();
        picasaManager.resetNasaPonukaAlbumPhotoDtoList(albumId);
    }

    private void updateOrder(List<MenuLabel> menuLabels, MenuLabel newMenuLabel) {
        Long newItemOrder = newMenuLabel.getOrder();
        for (MenuLabel label : menuLabels) {
            if (label.getOrder().compareTo(newItemOrder) >= 0) {
                label.setOrder(label.getOrder() + 1);
            }
        }
        menuLabels.add(newMenuLabel);
    }

    private void removeFromOrder(List<MenuLabel> menuLabels, MenuLabel newMenuLabel) {
        Iterator<MenuLabel> iterator = menuLabels.iterator();
        boolean decrement = false;
        while (iterator.hasNext()) {
            MenuLabel menuLabel = iterator.next();
            if (menuLabel.getId().compareTo(newMenuLabel.getId()) == 0) {
                iterator.remove();
                decrement = true;
                continue;
            }

            if (decrement) {
                menuLabel.setOrder(menuLabel.getOrder() - 1);
            }
        }
    }

    public List<MenuLabel> getMenuLabels() {
        Query query = new Query(MenuLabel.getKind(), MenuLabel.ancestorKey()).addSort("order", Query.SortDirection.ASCENDING);
        List<Entity> list = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(20));
        List<MenuLabel> menuLabels = new ArrayList<MenuLabel>();
        for (Entity entity : list) {
            menuLabels.add(MenuLabel.toMenuLabel(entity));
        }
        return menuLabels;
    }

    public void deleteMenuLabel(MenuLabel menuLabel) {
        deleteMenuLabel(menuLabel.getId());
    }

    public void deleteMenuLabel(Long id) {
        deleteMenuLabel(MenuLabel.getEntityKey(id));
    }

    public MenuLabel getMenuLabel(Key key) {
        try {
            return MenuLabel.toMenuLabel(datastore.get(key));
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteMenuLabel(Key key) {
        Transaction txn = datastore.beginTransaction();
        try {
            MenuLabel menuLabel = getMenuLabel(key);
            Long order = menuLabel.getOrder();

            List<MenuLabel> menuLabels = getMenuLabels();
            for (MenuLabel label : menuLabels) {
                if (label.getOrder().compareTo(order) == 1) {
                    label.setOrder(label.getOrder() - 1);
                    datastore.put(label.toEntity());
                }
            }

            datastore.delete(txn, key);
            txn.commit();
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }

        }
    }

}
