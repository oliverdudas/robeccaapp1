package sk.dudas.appengine.robecca.domain;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oli
 * Date: 9.12.2012
 * Time: 16:56
 * To change this template use File | Settings | File Templates.
 */
public class MenuLabel implements Serializable {

    private static final String DEFAULT = "default";
    private static final String PROPERTY_NAME = "name";
    private static final String PROPERTY_ORDER = "order";

    private Long id;
    private String name;
    private Long order;

    public MenuLabel() {
    }

    public MenuLabel(String name, Long order) {
        this.name = name;
        this.order = order;
    }

    public MenuLabel(Long id, String name, Long order) {
        this.id = id;
        this.name = name;
        this.order = order;
    }

    public static Key ancestorKey() {
        return KeyFactory.createKey("Settings", DEFAULT);
    }

    public static Key getEntityKey(Long id) {
        return KeyFactory.createKey(ancestorKey(), MenuLabel.class.getSimpleName(), id);
    }

    public Entity toEntity(Key key) {
        Entity entity = new Entity(key);
        entity.setProperty(PROPERTY_NAME, this.name);
        entity.setProperty(PROPERTY_ORDER, this.order);
        return entity;
    }

    public Entity toEntity() {
        return toEntity(getEntityKey(this.id));
    }

    public static List<Entity> toEntities(List<MenuLabel> menuLabels) {
        List<Entity> entities = new ArrayList<Entity>();
        for (MenuLabel menuLabel : menuLabels) {
            entities.add(menuLabel.toEntity());
        }
        return entities;
    }

    public static MenuLabel toMenuLabel(Entity entity) {
        return new MenuLabel(
                entity.getKey().getId(),
                (String) entity.getProperty(PROPERTY_NAME),
                (Long) entity.getProperty(PROPERTY_ORDER)
        );
    }

    public static String getKind() {
        return MenuLabel.class.getSimpleName();
    }

    public boolean isNew() {
        return id == null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MenuLabel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", order=" + order +
                '}';
    }
}
