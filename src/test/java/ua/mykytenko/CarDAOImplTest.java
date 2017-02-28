package ua.mykytenko;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.mykytenko.dao.CarDAO;
import ua.mykytenko.dao.impl.CarDAOImpl;
import ua.mykytenko.model.Car;

public class CarDAOImplTest {
    private CarDAO carDAO = new CarDAOImpl();

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
    public void testGetCarById(){
        for (int i = 0; i < TestData.CARS.length; i++) {
            Car expected = TestData.CARS[i];
            Car actual = carDAO.getCarById(i + 1);
            Assert.assertEquals(expected, actual);
            Assert.assertEquals(expected.getServiceStations(), actual.getServiceStations());
        }
    }

    @Test
    public void testAddCar(){
        carDAO.addCar(TestData.NEW_CAR);
        Car car = carDAO.getCarById(TestData.CARS.length + 1);
        Assert.assertEquals(TestData.NEW_CAR, car);
        Assert.assertEquals(TestData.NEW_CAR.getServiceStations(), car.getServiceStations());
    }

    @Test
    public void testGetNotFound(){
        Assert.assertEquals(null, carDAO.getCarById(Integer.MAX_VALUE - 1));
    }

    @Test
    public void testDelete(){
        Assert.assertNotEquals(null, carDAO.getCarById(1));
        carDAO.deleteCarById(1);
        Assert.assertEquals(null, carDAO.getCarById(1));
    }

    @Test
    public void testDeleteNotFound(){
        Assert.assertEquals(false, carDAO.deleteCarById(Integer.MAX_VALUE -  1));
    }

    @Test
    public void testUpdate(){
        Car car = carDAO.getCarById(1);
        car.setModel("model upd");
        car.setMaker("maker upd");
        car.setPrice(10000000);
        carDAO.updateCar(car);
        Assert.assertEquals(car, carDAO.getCarById(1));
    }

    @Test
    public void testUpdateNotFound(){
        Car car = carDAO.getCarById(1);
        car.setId(Integer.MAX_VALUE - 1);
        Car actual = carDAO.updateCar(car);
        Assert.assertEquals(null, actual);
    }

    @Test
    public void getNotFound(){
        Assert.assertEquals(null, carDAO.getCarById(Integer.MAX_VALUE - 1));
    }

}
