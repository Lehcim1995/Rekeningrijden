package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class VehicleTracker implements Serializable {

    @Id
    @XmlTransient
    @JsonIgnore
    private String ID;
    private String manufacturer;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tracker")
    private Vehicle vehicle;
    private Date addDate;

    public VehicleTracker() {}

    public VehicleTracker(VehicleTracker tracker) {
        this.ID = tracker.getID();
        this.addDate = new Date();
    }

    public VehicleTracker(String id) {
        this.ID = id;
        this.addDate = new Date();
    }
    public VehicleTracker(String id, String manufacturer)
    {
        this.ID = id;
        this.manufacturer = manufacturer;
        this.addDate = new Date();
    }

    public String getID() {
        return ID;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setVehicle(Vehicle vehicle) {
        if (this.vehicle == null) this.vehicle = vehicle;
    }

    public Date getAddDate() {
        return addDate;
    }
}
