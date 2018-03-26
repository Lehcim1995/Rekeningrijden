package service;

import classes.Invoice;
import classes.MonthEnum;
import classes.Owner;
import classes.PaymentEnum;
import dao.InvoiceDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Stateless
public class InvoiceService {

    @Inject
    private InvoiceDao invoiceDao;

    public boolean createInvoice(String vehicleTrackerId, Owner owner, double overall, PaymentEnum paymentStatus, MonthEnum month) {
        Invoice invoice = new Invoice(vehicleTrackerId, owner, overall, paymentStatus, month);
        if (invoiceDao.create(invoice) != null) {
            return true;
        }
        return false;
    }

    public List<Invoice> getAllInvoices() {
        return invoiceDao.getAllInvoices();
    }
}
