package classes;

import java.util.Date;

public class Invoice {

    private int invoiceId;
    private String vehicleTrackerId;
    private Owner owner;
    private double overall;
    private PaymentEnum paymentStatus;
    private Date date;

    public Invoice(int invoiceId, String vehicleTrackerId, Owner owner, double overall, PaymentEnum paymentStatus, Date date) {
        this.invoiceId = invoiceId;
        this.vehicleTrackerId = vehicleTrackerId;
        this.owner = owner;
        this.overall = overall;
        this.paymentStatus = paymentStatus;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

