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
    private String carid;
    private Date date;
    private double costs;

    public InvoiceData() {
    }

    public InvoiceData(
            String carid,
            Date date,
            double costs)
    {
        this.carid = carid;
        this.date = date;
        this.costs = costs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
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
