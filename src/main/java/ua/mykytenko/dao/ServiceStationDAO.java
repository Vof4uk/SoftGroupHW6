package ua.mykytenko.dao;

import ua.mykytenko.model.ServiceStation;

public interface ServiceStationDAO {
    ServiceStation addServiceStation(ServiceStation serviceStation);
    ServiceStation updateServiceStation(ServiceStation serviceStation);
    ServiceStation getServiceStationById(int id);
    boolean deleteServiceStationById(int id);
}
