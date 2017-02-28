package ua.mykytenko.model;

import org.hibernate.proxy.HibernateProxyHelper;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "service_stations")
public class ServiceStation {

    private int id;

    private String address;

    private Set<Car> cars = new HashSet<>();

    private Set<Mechanic> mechanics = new HashSet<>();

    public ServiceStation() {
    }

    public ServiceStation(int id, String address) {
        this.id = id;
        this.address = address;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "address", nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "serviceStations")
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "service_stations_clients", inverseJoinColumns = {@JoinColumn(name = "car_id", nullable = false)},
            joinColumns = {@JoinColumn(name = "service_station_id", nullable = false)})
    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "serviceStation", cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    public Set<Mechanic> getMechanics() {
        return mechanics;
    }

    public void setMechanics(Set<Mechanic> mechanics) {
        this.mechanics = mechanics;
    }

    @Override
    public String toString() {
        return "ServiceStation{" +
                "id=" + id +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != HibernateProxyHelper.getClassWithoutInitializingProxy(o)) return false;

        ServiceStation that = (ServiceStation) o;

        if (getId() != that.getId()) return false;
        return getAddress() != null ? getAddress().equals(that.getAddress()) : that.getAddress() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        return result;
    }
}
