package model;

import domain.Invoice;

import java.io.Serializable;
import java.util.List;

public class InvoiceListModel implements Serializable {

    private List<Invoice> invoices;

    public InvoiceListModel(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}
