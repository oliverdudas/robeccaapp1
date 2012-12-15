package sk.dudas.appengine.robecca.service;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import sk.dudas.appengine.robecca.BaseTest;
import sk.dudas.appengine.robecca.domain.MenuLabel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oli
 * Date: 9.12.2012
 * Time: 17:23
 * To change this template use File | Settings | File Templates.
 */

public class TestSettingsManagerImpl extends BaseTest {

    private LocalServiceTestHelper helper;

    @Autowired
    private SettingsManager settingsManager;

    @Before
    public void setUp() {
        LocalDatastoreServiceTestConfig localDatastoreServiceTestConfig = new LocalDatastoreServiceTestConfig();
//        localDatastoreServiceTestConfig.setBackingStoreLocation("out\\artifacts\\oliverdudasapp1_war_exploded\\WEB-INF\\appengine-generated\\local_db.bin");
        localDatastoreServiceTestConfig.setBackingStoreLocation("local_db.bin");
        localDatastoreServiceTestConfig.setNoStorage(false);
        helper = new LocalServiceTestHelper(localDatastoreServiceTestConfig);
        helper.setUp();
    }

    @After
    public void tearDown() {
        // remove tearDown to save test persistent actions
        helper.tearDown();
    }

    @Test
    @Rollback(value = true)
    public void testName() throws Exception {
        MenuLabel damskaKonfekcia = new MenuLabel("DÁMSKA KONFEKCIA", (long) 1);
        settingsManager.createMenuLabel(damskaKonfekcia);
        MenuLabel kabelky = new MenuLabel("KABELKY", (long) 2);
        settingsManager.createMenuLabel(kabelky);
        MenuLabel doplnky = new MenuLabel("DOPLNKY", (long) 3);
        settingsManager.createMenuLabel(doplnky);
        MenuLabel cestovneKufre = new MenuLabel("CESTOVNÉ KUFRE", (long) 3);
        settingsManager.createMenuLabel(cestovneKufre);

        settingsManager.deleteMenuLabel(doplnky);
        settingsManager.deleteMenuLabel(kabelky.getId());
//
        damskaKonfekcia.setName("teeest");
        settingsManager.updateMenuLabel(damskaKonfekcia);

        List<MenuLabel> menuLabelEntities = settingsManager.getMenuLabels();
        System.out.println("size: " + menuLabelEntities.size());
        for (MenuLabel menuLabel : menuLabelEntities) {
            System.out.println(menuLabel);
        }

    }
}
