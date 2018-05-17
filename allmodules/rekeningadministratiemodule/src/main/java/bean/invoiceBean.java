package bean;

import domain.Invoice;
import domain.PaymentEnum;
import service.InvoiceService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SessionScoped
@Named(value="invoiceBean")
public class invoiceBean implements Serializable {

    @Inject
    private InvoiceService invoiceService;

    private PaymentEnum paymentStatus;
    private List<String> invoiceCalcs = new ArrayList<String>();
    private Invoice selectedInvoice;
    private List<PaymentEnum> enumList = Arrays.asList(PaymentEnum.values());


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
        //this.paymentStatus = selectedInvoice.getPaymentStatus();
        return paymentStatus.toString();
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = PaymentEnum.valueOf(paymentStatus);
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

    public List<PaymentEnum> getEnumList() {
        return enumList;
    }

    public void setEnumList(List<PaymentEnum> enumList) {
        this.enumList = enumList;
    }

    public void setSelectedInvoice(Invoice selectedInvoice) {
        this.selectedInvoice = selectedInvoice;
    }

    public void valueChanged(ValueChangeEvent event){
        invoiceService.changePaymentStatusById(selectedInvoice.getInvoiceId(), event.getNewValue().toString());
        paymentStatus = PaymentEnum.valueOf(event.getNewValue().toString());
    }

    public boolean isDisabled(String item) {
        return "Open".equals(item);
    }
}
