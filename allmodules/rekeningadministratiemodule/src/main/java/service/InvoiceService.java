package service;

import dao.InvoiceDao;
import domain.Invoice;
import domain.MonthEnum;
import domain.Owner;
import domain.PaymentEnum;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Stateless
public class InvoiceService implements Serializable{

    @Inject
    private InvoiceDao invoiceDao;

    public boolean createInvoice(String vehicleTrackerId, Owner owner, double overall, PaymentEnum paymentStatus, MonthEnum month) {
        Invoice invoice = new Invoice(vehicleTrackerId, owner, overall, paymentStatus, month);
        if (invoiceDao.create(invoice) != null) {
            return true;
        }
        return false;
    }

    public boolean changePaymentStatusById(int invoiceId, String paymentStatus) {
        return invoiceDao.changePaymentStatusById(invoiceId, paymentStatus);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceDao.getAllInvoices();
    }

    public Invoice getInvoiceByTrackerId(String trackerId) {
        return invoiceDao.getInvoiceByTrackerId(trackerId);
    }

    public List<Invoice> getInvoicesByOwner(Owner owner){
        return invoiceDao.getInvoicesByOwner(owner);
    }

    public List<Invoice> getInvoicesByPaymentStatus(PaymentEnum paymentEnum) {
        return invoiceDao.getInvoicesByPaymentStatus(paymentEnum);
    }

    public List<Invoice> getInvoicesByTrackerIdAndMonth(String trackerId, MonthEnum monthEnum) {
        return invoiceDao.getInvoicesByTrackerIdAndMonth(trackerId, monthEnum);
    }

    public List<Invoice> getInvoicesByOwnerAndMonth(Owner owner, MonthEnum monthEnum) {
        return invoiceDao.getInvoicesByOwnerAndMonth(owner, monthEnum);
    }

    public List<Invoice> getInvoicesByPaymentStatusAndMonth(PaymentEnum paymentEnum, MonthEnum monthEnum) {
        return invoiceDao.getInvoicesByPaymentStatusAndMonth(paymentEnum, monthEnum);
    }

    public List<Invoice> getInvoicesByVehicleAndOwner(int vehicleId, Owner ownerById) {
        return invoiceDao.getInvoicesByVehicleAndOwner(vehicleId, ownerById);
    }
}
