package dao;

import classes.Invoice;
import classes.MonthEnum;
import classes.Owner;
import classes.PaymentEnum;

import java.util.List;

public interface InvoiceDao {

    Invoice create(Invoice invoice);

    Invoice update(Invoice invoice);

    boolean changePaymentStatusById(int invoiceId, String paymentStatus);

    Invoice getInvoiceByInvoiceId(int invoiceId);

    List<Invoice> getAllInvoices();

    Invoice getInvoiceByTrackerId(String trackerId);

    List<Invoice> getInvoicesByOwner(Owner owner);

    List<Invoice> getInvoicesByPaymentStatus(PaymentEnum paymentEnum);

    List<Invoice> getInvoicesByTrackerIdAndMonth(String trackerId, MonthEnum monthEnum);

    List<Invoice> getInvoicesByOwnerAndMonth(Owner owner, MonthEnum monthEnum);

    List<Invoice> getInvoicesByPaymentStatusAndMonth(PaymentEnum paymentEnum, MonthEnum monthEnum);
}
