package ua.mykytenko;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.mykytenko.dao.MechanicDAO;
import ua.mykytenko.dao.impl.MechanicDAOImpl;
import ua.mykytenko.model.Mechanic;

public class MechanicDAOImplTest {
    private MechanicDAO mechanicDAO = new MechanicDAOImpl();

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
    public void testGetMechanicById(){
        for (int i = 0; i < TestData.MECHANICS.length; i++) {
            Mechanic expected = TestData.MECHANICS[i];
            Mechanic actual = mechanicDAO.getMechanicById(i + 1);
            Assert.assertEquals(expected, actual);
        }
    }

    @Test
    public void testAddMechanic(){
        int id = mechanicDAO.addMechanic(TestData.NEW_MECHANIC).getId();
        Assert.assertEquals(TestData.NEW_MECHANIC, mechanicDAO.getMechanicById(id));
    }

    @Test
    public void testGetMechanicNotFound(){
        Assert.assertEquals(null, mechanicDAO.getMechanicById(Integer.MAX_VALUE - 1));
    }

    @Test
    public void testDeleteMechanic(){
        Assert.assertNotEquals(null, mechanicDAO.getMechanicById(1));
        mechanicDAO.deleteMechanicById(1);
        Assert.assertEquals(null, mechanicDAO.getMechanicById(1));
    }

    @Test
    public void testDeleteNotFound(){
        Assert.assertEquals(false, mechanicDAO.deleteMechanicById(Integer.MAX_VALUE - 1));
    }

    @Test
    public void testUpdate(){
        Mechanic mechanic = mechanicDAO.getMechanicById(1);
        mechanic.setFirstName("fname upd");
        mechanic.setLastName("lname upd");
        mechanicDAO.updateMechanic(mechanic);
        Assert.assertEquals(mechanic,mechanicDAO.getMechanicById(mechanic.getId()));
    }

    @Test
    public void testUpdateNotFound(){
        Mechanic m = mechanicDAO.getMechanicById(1);
        m.setId(Integer.MAX_VALUE - 1);
        Assert.assertEquals(null, mechanicDAO.updateMechanic(m));
    }

    @Test
    public void getNotFound(){
        Assert.assertEquals(null, mechanicDAO.getMechanicById(Integer.MAX_VALUE - 1));
    }
}
