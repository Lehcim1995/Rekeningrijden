package classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class InvoiceData
{
    @Id
    @GeneratedValue
    private long id;
    private String vehicleId;
    private Date date;
    private double costs;

    public InvoiceData() {
    }

    public InvoiceData(
            String vehicleId,
            Date date,
            double costs)
    {
        this.vehicleId = vehicleId;
        this.date = date;
        this.costs = costs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String carid) {
        this.vehicleId = carid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getCosts() {
        return costs;
    }

    public void setCosts(double costs) {
        this.costs = costs;
    }
}
