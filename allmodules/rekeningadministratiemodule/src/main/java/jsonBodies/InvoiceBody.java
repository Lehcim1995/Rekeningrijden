package jsonBodies;

import domain.MonthEnum;
import domain.Owner;
import domain.PaymentEnum;

import java.io.Serializable;

public class InvoiceBody implements Serializable {
    private String vehicleTrackerId;
    private Owner owner;
    private double overall;
    private PaymentEnum paymentStatus;

    public MonthEnum getMonth() {
        return month;
    }

    public void setMonth(MonthEnum month) {
        this.month = month;
    }

    private MonthEnum month;

    public InvoiceBody(){}

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
}
