package ua.mykytenko;

import ua.mykytenko.model.Car;
import ua.mykytenko.model.Mechanic;
import ua.mykytenko.model.ServiceStation;

import java.util.*;

public class TestData {

    public static final Car[] CARS;
    public static final Car NEW_CAR;
    public static final Mechanic[] MECHANICS;
    public static final Mechanic NEW_MECHANIC;
    public static final ServiceStation[] SERVICE_STATIONS;
    public static final ServiceStation NEW_SERVICE_STATION;

    static {
        CARS = new Car[4];
        CARS[0] = new Car(1, "x5", "bmw", 5454, new Date(109, 10, 21), 20000);
        CARS[1] = new Car(2, "lanos", "daewoo", 4994, new Date(101, 0, 1), 5000);
        CARS[2] = new Car(3, "clk", "mercedez", 88733, new Date(114, 1, 14), 50000);
        CARS[3] = new Car(4, "pajero", "mitsubishi", 8773, new Date(107, 3, 15), 17000);

        SERVICE_STATIONS = new ServiceStation[4];
        SERVICE_STATIONS[0] = new ServiceStation(1, "pushkina, 10");
        SERVICE_STATIONS[1] = new ServiceStation(2, "shevchenka, 112");
        SERVICE_STATIONS[2] = new ServiceStation(3, "энгельса, 666");
        SERVICE_STATIONS[3] = new ServiceStation(4, "5th avenue, 4432");

        CARS[0].setServiceStations(new HashSet<ServiceStation>(Arrays.asList(SERVICE_STATIONS[0], SERVICE_STATIONS[1])));
        CARS[1].setServiceStations(new HashSet<ServiceStation>(Arrays.asList(SERVICE_STATIONS[1], SERVICE_STATIONS[2])));
        CARS[2].setServiceStations(new HashSet<ServiceStation>(Arrays.asList(new ServiceStation[]{SERVICE_STATIONS[0]})));

        SERVICE_STATIONS[0].setCars(new HashSet<Car>(Arrays.asList(CARS[0], CARS[2])));
        SERVICE_STATIONS[1].setCars(new HashSet<Car>(Arrays.asList(CARS[0], CARS[1])));
        SERVICE_STATIONS[2].setCars(new HashSet<Car>(Arrays.asList(new Car[]{CARS[1]})));

        MECHANICS = new Mechanic[12];
        MECHANICS[0] = new Mechanic(1, "Дим Димыч", "Вишня");
        MECHANICS[1] = new Mechanic(2, "Степан", "Січ");
        MECHANICS[2] = new Mechanic(3, "Анна", "Каренина");
        MECHANICS[3] = new Mechanic(4, "Дональд", "Трамп");
        MECHANICS[4] = new Mechanic(5, "Бонифаций", "Максимус");
        MECHANICS[5] = new Mechanic(6, "Данило", "Иванов");
        MECHANICS[6] = new Mechanic(7, "Роберт", "Дент");
        MECHANICS[7] = new Mechanic(8, "Харви", "Дент");
        MECHANICS[8] = new Mechanic(9, "Алла", "Пугачева");
        MECHANICS[9] = new Mechanic(10, "Антонио", "Фокаччо");
        MECHANICS[10] = new Mechanic(11, "Грэг", "просто Грэг");
        MECHANICS[11] = new Mechanic(12, "Иван", "Лежибока");

        MECHANICS[0].setServiceStation(SERVICE_STATIONS[0]);
        MECHANICS[1].setServiceStation(SERVICE_STATIONS[0]);
        MECHANICS[2].setServiceStation(SERVICE_STATIONS[1]);
        MECHANICS[3].setServiceStation(SERVICE_STATIONS[1]);
        MECHANICS[4].setServiceStation(SERVICE_STATIONS[1]);
        MECHANICS[5].setServiceStation(SERVICE_STATIONS[2]);
        MECHANICS[6].setServiceStation(SERVICE_STATIONS[2]);
        MECHANICS[7].setServiceStation(SERVICE_STATIONS[2]);
        MECHANICS[8].setServiceStation(SERVICE_STATIONS[3]);
        MECHANICS[9].setServiceStation(SERVICE_STATIONS[3]);
        MECHANICS[10].setServiceStation(SERVICE_STATIONS[3]);

        SERVICE_STATIONS[0].setMechanics(new HashSet<Mechanic>(Arrays.asList(MECHANICS[0], MECHANICS[1])));
        SERVICE_STATIONS[1].setMechanics(new HashSet<Mechanic>(Arrays.asList(MECHANICS[2], MECHANICS[3], MECHANICS[4])));
        SERVICE_STATIONS[2].setMechanics(new HashSet<Mechanic>(Arrays.asList(MECHANICS[5], MECHANICS[6], MECHANICS[7])));
        SERVICE_STATIONS[3].setMechanics(new HashSet<Mechanic>(Arrays.asList(MECHANICS[8], MECHANICS[9], MECHANICS[10])));

        NEW_CAR = new Car(5, "new model", "new maker", 99, new Date(100, 0, 1), 100);
        NEW_CAR.setServiceStations(new HashSet<ServiceStation>(Arrays.asList(SERVICE_STATIONS[0], SERVICE_STATIONS[1], SERVICE_STATIONS[2])));
        NEW_MECHANIC = new Mechanic(12, "new fname", "new lname");
        NEW_MECHANIC.setServiceStation(SERVICE_STATIONS[3]);
        NEW_SERVICE_STATION = new ServiceStation(4, "new address");
        NEW_SERVICE_STATION.setMechanics(new HashSet<Mechanic>(Arrays.asList(MECHANICS[3], MECHANICS[5], MECHANICS[9])));
        NEW_SERVICE_STATION.setCars(new HashSet<Car>(Arrays.asList(CARS[0], CARS[2], CARS[3])));
    }
}
