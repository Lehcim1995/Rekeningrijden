package classes;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="invoice_class")
@XmlRootElement
public class Invoice implements Serializable {

    @Id
    @GeneratedValue
    private int invoiceId;
    private String vehicleTrackerId;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Owner owner;
    private double overall;
    @Enumerated(EnumType.STRING)
    private PaymentEnum paymentStatus;
    private MonthEnum date;

    public Invoice(String vehicleTrackerId, Owner owner, double overall, PaymentEnum paymentStatus, MonthEnum date) {
        this.vehicleTrackerId = vehicleTrackerId;
        this.owner = owner;
        this.overall = overall;
        this.paymentStatus = paymentStatus;
        this.date = date;
    }

    public Invoice(){}

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getVehicleTrackerId() {
        return vehicleTrackerId;
    }

    public void setVehicleTrackerId(String vehicleTrackerId) {
        this.vehicleTrackerId = vehicleTrackerId;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public double getOverall() {
        return overall;
    }

    public void setOverall(double overall) {
        this.overall = overall;
    }

    public PaymentEnum getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentEnum paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public MonthEnum getDate() {
        return date;
    }

    public void setDate(MonthEnum date) {
        this.date = date;
    }
}

