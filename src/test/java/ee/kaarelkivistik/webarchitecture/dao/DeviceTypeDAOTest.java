package ee.kaarelkivistik.webarchitecture.dao;

import ee.kaarelkivistik.webarchitecture.Application;
import ee.kaarelkivistik.webarchitecture.models.DeviceType;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by kaarel on 23.05.16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class DeviceTypeDAOTest {

    @Autowired
    DeviceTypeDAO dao;

    @org.junit.Test
    public void testInsertAndDelete() throws Exception {
        String deviceTypeName = "fn4kfn4k3n34fk3nk3n";

        DeviceType deviceType = new DeviceType((short) 1, deviceTypeName);

        assertNull(deviceType.getId());

        dao.insert(deviceType);

        assertNotNull(deviceType.getId());

        DeviceType foundDeviceType = dao.findOne(deviceType.getId());

        assertEquals(deviceType, foundDeviceType);

        dao.delete(deviceType);

        foundDeviceType = dao.findOne(deviceType.getId());

        assertNull(foundDeviceType);
    }
}