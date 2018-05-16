package dao;

import domain.Invoice;
import domain.MonthEnum;
import domain.Owner;
import domain.PaymentEnum;

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
