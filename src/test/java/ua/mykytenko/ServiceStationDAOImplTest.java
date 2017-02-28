package ua.mykytenko;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.mykytenko.dao.ServiceStationDAO;
import ua.mykytenko.dao.impl.ServiceStationDAOImpl;
import ua.mykytenko.model.Mechanic;
import ua.mykytenko.model.ServiceStation;

public class ServiceStationDAOImplTest {
    private ServiceStationDAO stationDAO = new ServiceStationDAOImpl();

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/week6";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";

    @BeforeClass
    public static void initDb(){
        TestUtil.createTables(DRIVER, URL, USERNAME, PASSWORD);
    }

    @Before
    public void populateDB(){
        TestUtil.populateTables(DRIVER, URL, USERNAME, PASSWORD);
    }

    @Test
    public void testGetServiceStationById(){
        for (int i = 0; i < TestData.SERVICE_STATIONS.length; i++) {
            ServiceStation expected = TestData.SERVICE_STATIONS[i];
            ServiceStation actual = stationDAO.getServiceStationById(i + 1);
            Assert.assertEquals(expected, actual);
        }
    }

    @Test
    public void testAddServiceStation(){
        ServiceStation expected = TestData.NEW_SERVICE_STATION;
        int id = stationDAO.addServiceStation(expected).getId();
        ServiceStation actual = stationDAO.getServiceStationById(id);
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expected.getCars(), actual.getCars());
        Assert.assertNotEquals(expected.getMechanics(), actual.getMechanics());
        for (Mechanic m :actual.getMechanics()) {
            Assert.assertEquals(m.getServiceStation(), expected);
        }
    }

    @Test
    public void testGetNotFound(){
        Assert.assertEquals(null, stationDAO.getServiceStationById(Integer.MAX_VALUE - 1));
    }

    @Test
    public void testDelete(){
        stationDAO.deleteServiceStationById(1);
        Assert.assertEquals(null, stationDAO.getServiceStationById(1));
    }

    @Test
    public void testDeleteNotFound(){
        Assert.assertEquals(false, stationDAO.deleteServiceStationById(Integer.MAX_VALUE - 1));
    }

    @Test
    public void testUpdate(){
        ServiceStation ss = stationDAO.getServiceStationById(1);
        ss.setAddress("updated");
        stationDAO.updateServiceStation(ss);
        Assert.assertEquals(ss, stationDAO.getServiceStationById(1));
    }

    @Test
    public void testUpdateNotFound(){
        ServiceStation ss = stationDAO.getServiceStationById(1);
        ss.setId(Integer.MAX_VALUE - 1);
        Assert.assertEquals(null, stationDAO.updateServiceStation(ss));
    }

    @Test
    public void getNotFound(){
        Assert.assertEquals(null, stationDAO.getServiceStationById(Integer.MAX_VALUE - 1));
    }

}
