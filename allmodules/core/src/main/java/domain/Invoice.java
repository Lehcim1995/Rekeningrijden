package domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

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
    private double total;
    @Enumerated(EnumType.STRING)
    private PaymentEnum paymentStatus;
    private MonthEnum date;
    private int totalDistance;

    @OneToMany
    private List<InvoiceData> invoiceData;

    public Invoice(String vehicleTrackerId, Owner owner, double total, PaymentEnum paymentStatus, MonthEnum date) {
        this.vehicleTrackerId = vehicleTrackerId;
        this.owner = owner;
        this.total = total;
        this.paymentStatus = paymentStatus;
        this.date = date;
    }

    public Invoice(){}

    public List<InvoiceData> getInvoiceData() {
        return invoiceData;
    }

    public void setInvoiceData(List<InvoiceData> invoiceData) {
        this.invoiceData = invoiceData;
    }

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

    public double getTotal() {
        return total;
    }

    public void setTotal(double overall) {
        this.total = overall;
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

    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }
}

