package classes;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class KilometerRate implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    //TODO: Make from String road a Road road object
    private String road;
    private double kilometerPrice;
    private Date startDate;
    private Date endDate;
    private RateCategory rateCategoryEnum;

    public KilometerRate(String road, double kilometerPrice, Date startDate, Date endDate, RateCategory rateCategoryEnum) {
        this.road = road;
        this.kilometerPrice = kilometerPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rateCategoryEnum = rateCategoryEnum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
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
