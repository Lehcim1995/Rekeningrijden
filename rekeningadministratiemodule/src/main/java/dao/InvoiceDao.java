package dao;

import classes.Invoice;

import java.util.List;

public interface InvoiceDao {

    Invoice create(Invoice invoice);

    Invoice update(Invoice invoice);

    List<Invoice> getAllInvoices();

    Invoice getInvoiceByTrackerId(String trackerId);

}
