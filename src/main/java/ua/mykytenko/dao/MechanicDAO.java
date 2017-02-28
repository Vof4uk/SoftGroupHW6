package ua.mykytenko.dao;

import ua.mykytenko.model.Mechanic;

public interface MechanicDAO {
    Mechanic addMechanic(Mechanic mechanic);
    Mechanic updateMechanic(Mechanic mechanic);
    Mechanic getMechanicById(int id);
    boolean deleteMechanicById(int id);
}
