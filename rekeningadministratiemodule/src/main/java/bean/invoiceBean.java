package bean;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Named(value="invoiceBean")
public class invoiceBean {

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



}
