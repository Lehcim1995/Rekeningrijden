package bean;

import classes.Invoice;
import service.InvoiceService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named(value="invoiceBean")
public class invoiceBean implements Serializable {

    @Inject
    private InvoiceService invoiceService;

    private String paymentStatus = "";
    private List<String> invoiceCalcs = new ArrayList<String>();
    private Invoice selectedInvoice;


    @PostConstruct
    public void init(){

    }

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

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
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

    public Invoice getSelectedInvoice() {
        return selectedInvoice;
    }

    public void setSelectedInvoice(Invoice selectedInvoice) {
        this.selectedInvoice = selectedInvoice;
    }

    public String showInvoice()
    {
        return "/index.xhtml?faces-redirect=true&includeViewParams=true";
    }
}
