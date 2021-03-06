package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class KilometerRate implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    private Road road;
    private double kilometerPrice;
    private Date startDate;
    private Date endDate;
    @OneToOne
    private RateCategory rateCategoryEnum;

    public KilometerRate(Road road, double kilometerPrice, Date startDate, Date endDate, RateCategory rateCategoryEnum) {
        this.road = road;
        this.kilometerPrice = kilometerPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rateCategoryEnum = rateCategoryEnum;
    }

    public KilometerRate() {
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

    public RateCategory getRateCategoryEnum() {
        return rateCategoryEnum;
    }

    public void setRateCategoryEnum(RateCategory rateCategoryEnum) {
        this.rateCategoryEnum = rateCategoryEnum;
    }
}
