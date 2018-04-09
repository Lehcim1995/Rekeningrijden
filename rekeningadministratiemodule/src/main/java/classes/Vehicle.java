package classes;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Date;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    @OneToOne
    private VehicleTracker tracker;
    private String rateCategorie;
    private String licensePlate;
    private Date buildYear;
    @OneToOne
    private Owner owner;

    public Vehicle() {}

    public Vehicle(Vehicle vehicle) {
        this.rateCategorie = vehicle.getRateCategorie();
        this.licensePlate = vehicle.getLicensePlate();
        this.buildYear = vehicle.getBuildYear();
    }

    public Vehicle(String rateCategorie, String licensePlate, Date buildYear) {
        this.rateCategorie = rateCategorie;
        this.licensePlate = licensePlate;
        this.buildYear = buildYear;
    }

    public int getID() {
        return ID;
    }

    public VehicleTracker getTracker() {
        return tracker;
    }

    public void setTracker(VehicleTracker tracker) {
        if (this.tracker == null) this.tracker = tracker;
    }

    public String getRateCategorie() {
        return rateCategorie;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Date getBuildYear() {
        return buildYear;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
