package service;

import classes.Owner;
import classes.PaymentEnum;
import dao.InvoiceDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@Stateless
public class InvoiceService {

    private AtomicLong nextId = new AtomicLong(0L);

    @Inject
    private InvoiceDao invoiceDao;

    public boolean createInvoice(String vehicleTrackerId, Owner owner, double overall, PaymentEnum paymentStatus) {
        Date invoiceDate = new Date();
        return true;
    }
}
