package domain;

import annotations.IgnoreInTable;
import annotations.Name;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class InvoiceData
{
    @Id
    @GeneratedValue
    @IgnoreInTable
    private long id;
    @IgnoreInTable
    private String vehicleId;
    @Name(name = "Datum")
    private Date date;
    @Name(name = "Kosten")
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
