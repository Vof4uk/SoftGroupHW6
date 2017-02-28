package ua.mykytenko.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name = "cars")
public class Car {

    private int id;
    private String model;
    private String maker;
    private int engineId;
    private Date makeDate;
    private int price;

    private Set<ServiceStation> serviceStations = new HashSet<>();

    public Car() {
    }

    public Car(int id, String model, String maker, int engineId, Date makeDate, int price) {
        this.id = id;
        this.model = model;
        this.maker = maker;
        this.engineId = engineId;
        this.makeDate = makeDate;
        this.price = price;
    }

    @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "model", nullable = false)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "maker", nullable = false)
    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    @Column(name = "engine_id", nullable = false)
    public int getEngineId() {
        return engineId;
    }

    public void setEngineId(int engineId) {
        this.engineId = engineId;
    }

    @Column(name = "make_date", nullable = false)
    public Date getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(Date makeDate) {
        this.makeDate = new Date(makeDate.getTime());
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "service_stations_clients", joinColumns = {@JoinColumn(name = "car_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "service_station_id", nullable = false)})
    public Set<ServiceStation> getServiceStations() {
        return serviceStations;
    }

    public void setServiceStations(Set<ServiceStation> serviceStations) {
        this.serviceStations = serviceStations;
    }

    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", maker='" + maker + '\'' +
                ", engineId=" + engineId +
                ", makeDate=" + makeDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (getId() != car.getId()) return false;
        if (getEngineId() != car.getEngineId()) return false;
        if (getPrice() != car.getPrice()) return false;
        if (getModel() != null ? !getModel().equals(car.getModel()) : car.getModel() != null) return false;
        if (getMaker() != null ? !getMaker().equals(car.getMaker()) : car.getMaker() != null) return false;
        return getMakeDate() != null ? getMakeDate().equals(car.getMakeDate()) : car.getMakeDate() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getModel() != null ? getModel().hashCode() : 0);
        result = 31 * result + (getMaker() != null ? getMaker().hashCode() : 0);
        result = 31 * result + getEngineId();
        result = 31 * result + (getMakeDate() != null ? getMakeDate().hashCode() : 0);
        result = 31 * result + getPrice();
        return result;
    }
}
