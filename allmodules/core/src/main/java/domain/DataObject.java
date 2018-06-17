package domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DataObject
{

    private double stepLat;
    private double stepLon;
    private String id;
    private Date date;
    private long stepId;
    private int carId;

    public DataObject() {
    }

    public double getStepLat() {
        return stepLat;
    }

    public double getStepLon() {
        return stepLon;
    }

    public void setStepLat(double stepLat) {
        this.stepLat = stepLat;
    }

    public void setStepLon(double stepLon) {
        this.stepLon = stepLon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getStepId() {
        return stepId;
    }

    public void setStepId(long stepId) {
        this.stepId = stepId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }
}
