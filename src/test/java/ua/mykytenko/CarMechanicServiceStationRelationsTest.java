package ua.mykytenko;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.mykytenko.dao.CarDAO;
import ua.mykytenko.dao.MechanicDAO;
import ua.mykytenko.dao.ServiceStationDAO;
import ua.mykytenko.dao.impl.CarDAOImpl;
import ua.mykytenko.dao.impl.MechanicDAOImpl;
import ua.mykytenko.dao.impl.ServiceStationDAOImpl;
import ua.mykytenko.model.Car;
import ua.mykytenko.model.Mechanic;
import ua.mykytenko.model.ServiceStation;

import java.util.Set;

public class CarMechanicServiceStationRelationsTest {
    private CarDAO carDAO = new CarDAOImpl();
    private MechanicDAO mechanicDAO = new MechanicDAOImpl();
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
    public void testCarsInServiceStations(){
        ServiceStation ss = stationDAO.getServiceStationById(1);
        Car[] cars = ss.getCars().toArray(new Car[ss.getCars().size()]);
        Car car = cars[0];
        Assert.assertTrue(stationDAO.getServiceStationById(1).getCars().contains(car));
        carDAO.deleteCarById(car.getId());
        Assert.assertFalse(stationDAO.getServiceStationById(1).getCars().contains(car));
    }

    @Test
    public void testServiceStationsInCars(){
        Car car = carDAO.getCarById(1);
        ServiceStation[] serviceStations = car.getServiceStations().toArray(new ServiceStation[car.getServiceStations().size()]);
        ServiceStation ss = serviceStations[0];
        Assert.assertTrue(carDAO.getCarById(1).getServiceStations().contains(ss));
        stationDAO.deleteServiceStationById(ss.getId());
        Assert.assertFalse(carDAO.getCarById(1).getServiceStations().contains(ss));
    }

    @Test
    public void testMechanicsInServiceStations(){
        ServiceStation ss = stationDAO.getServiceStationById(1);
        Mechanic[] mechanics = ss.getMechanics().toArray( new Mechanic[ss.getMechanics().size()]);
        mechanicDAO.deleteMechanicById(mechanics[0].getId());
        Assert.assertFalse(stationDAO.getServiceStationById(1).getMechanics().contains(mechanics[0]));
    }

    @Test
    public void testServiceStationInMechanic(){
        Mechanic mechanic = mechanicDAO.getMechanicById(2);
        ServiceStation ss = mechanic.getServiceStation();
        Set<Mechanic> mechanics = ss.getMechanics();
        mechanics.remove(mechanic);
        ss.setMechanics(mechanics);
        stationDAO.updateServiceStation(ss);
        Assert.assertNull(mechanicDAO.getMechanicById(2).getServiceStation());
    }

}
