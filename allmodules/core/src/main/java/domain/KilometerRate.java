package domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class KilometerRate implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @OneToOne (mappedBy = "kilometerRate")
    private Road road;
    private double kilometerPrice;
    private Date startDate;
    private Date endDate;

    public KilometerRate(Road road, double kilometerPrice, Date startDate, Date endDate) {
        this.road = road;
        this.kilometerPrice = kilometerPrice;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public KilometerRate() {
    }

    public KilometerRate(KilometerRate newKilometerRate) {
        this.road = newKilometerRate.getRoad();
        this.kilometerPrice = newKilometerRate.getKilometerPrice();
        this.startDate = newKilometerRate.getStartDate();
        this.endDate = newKilometerRate.getEndDate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Road getRoad() {
        return road;
    }

    public void setRoad(Road road) {
        this.road = road;
    }

    public double getKilometerPrice() {
        return kilometerPrice;
    }

    public void setKilometerPrice(double kilometerPrice) {
        this.kilometerPrice = kilometerPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
