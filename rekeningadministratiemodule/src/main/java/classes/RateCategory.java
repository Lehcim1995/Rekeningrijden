package classes;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Date;

public class RateCategory implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private FuelEnum fuelEnum;
    private double percentagePrice;
    private Date startDate;
    private Date endDate;

    public RateCategory(FuelEnum fuelEnum, double percentagePrice, Date startDate, Date endDate) {
        this.fuelEnum = fuelEnum;
        this.percentagePrice = percentagePrice;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FuelEnum getFuelEnum() {
        return fuelEnum;
    }

    public void setFuelEnum(FuelEnum fuelEnum) {
        this.fuelEnum = fuelEnum;
    }

    public double getPercentagePrice() {
        return percentagePrice;
    }

    public void setPercentagePrice(double percentagePrice) {
        this.percentagePrice = percentagePrice;
    }
}
