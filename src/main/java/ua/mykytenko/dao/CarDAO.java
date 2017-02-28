package ua.mykytenko.dao;

import ua.mykytenko.model.Car;

public interface CarDAO {
    Car addCar(Car car);
    Car updateCar(Car car);
    Car getCarById(int id);
    boolean deleteCarById(int id);
}
