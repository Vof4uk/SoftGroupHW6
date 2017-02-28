package ua.mykytenko.model;

import javax.persistence.*;

@Entity
@Table(name = "mechanics")
public class Mechanic {

    private int id;

    private String firstName;

    private String lastName;

    private ServiceStation serviceStation;

    public Mechanic() {
    }

    public Mechanic(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
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

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "service_station_id")
    public ServiceStation getServiceStation() {
        return serviceStation;
    }

    public void setServiceStation(ServiceStation serviceStation) {
        this.serviceStation = serviceStation;
    }

    @Override
    public String toString() {
        return "Mechanic{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", serviceStation=" + serviceStation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mechanic mechanic = (Mechanic) o;

        if (getId() != mechanic.getId()) return false;
        if (getFirstName() != null ? !getFirstName().equals(mechanic.getFirstName()) : mechanic.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(mechanic.getLastName()) : mechanic.getLastName() != null)
            return false;
        return getServiceStation() != null ? getServiceStation().equals(mechanic.getServiceStation()) : mechanic.getServiceStation() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getServiceStation() != null ? getServiceStation().hashCode() : 0);
        return result;
    }
}
