package classes;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RateCategory implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @Enumerated(EnumType.STRING)
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

    public RateCategory() {

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
