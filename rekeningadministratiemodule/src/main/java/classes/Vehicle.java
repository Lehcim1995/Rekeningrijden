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
    private int weight;
    private String licensePlate;
    @Enumerated(EnumType.STRING)
    private FuelEnum fueltype;
    private Date buildYear;
    @OneToOne
    private Owner owner;

    public Vehicle() {}

    public Vehicle(Vehicle vehicle) {
        this.rateCategorie = vehicle.getRateCategorie();
        this.licensePlate = vehicle.getLicensePlate();
        this.buildYear = vehicle.getBuildYear();
    }

    public Vehicle(String rateCategorie, String licensePlate, Date buildYear, int weight, FuelEnum fueltype) {
        this.rateCategorie = rateCategorie;
        this.licensePlate = licensePlate;
        this.buildYear = buildYear;
        this.weight = weight;
        this.fueltype = fueltype;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public FuelEnum getFueltype() {
        return fueltype;
    }

    public void setFueltype(FuelEnum fueltype) {
        this.fueltype = fueltype;
    }
}
