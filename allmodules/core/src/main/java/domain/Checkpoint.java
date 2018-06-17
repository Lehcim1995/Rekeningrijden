package domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
@XmlAccessorType(XmlAccessType.FIELD)
public class Checkpoint implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double lon;
    private double lat;

    private Date time;

    private int carId;

    public Checkpoint() {}

    public Checkpoint(DataObject dataObject)
    {
        this.lon = dataObject.getStepLon();
        this.lat = dataObject.getStepLat();
        this.time = dataObject.getDate();
        this.carId = dataObject.getCarId();
    }

    public Checkpoint(
            double lon,
            double lat,
            Date time)
    {
        this.lon = lon;
        this.lat = lat;
        this.time = time;
    }

    public Checkpoint(
            double stepLon,
            double stepLat)
    {
        this.lon = stepLon;
        this.lat = stepLat;
        this.time = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }
}
