package bean;

import classes.Invoice;
import service.InvoiceService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Named(value="invoiceBean")
public class invoiceBean {

    @Inject
    private InvoiceService invoiceService;

    private List<String> invoiceCalcs = new ArrayList<String>();

    public String getInvoiceCalcs() {
        invoiceCalcs.add("2+2 = 4 - 1 = 3 quick Mafs");
        invoiceCalcs.add("2+98384734 = 2");
        invoiceCalcs.add("4=== 84734 = 2");
        String returnString = "";
        for (String s:invoiceCalcs){
            returnString += "\n" + s;
        }
        return returnString;
    }
    public void setInvoiceCalcs(List<String> invoiceCals) {
        this.invoiceCalcs = invoiceCalcs;
    }

    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    public Invoice getInvoiceByTrackerId(String trackerId) {
        if (trackerId != null) {
            return invoiceService.getInvoiceByTrackerId(trackerId);
        }
        return null;
    }
}
