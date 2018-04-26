package bean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named(value="routerBean")
public class routerBean implements Serializable{

    public String goToInvoices(){
        return "/pages/invoices.xhtml?faces-redirect=true&includeViewParams=true";
    }

    public String showInvoice()
    {
        return "/pages/invoicedetails.xhtml?faces-redirect=true&includeViewParams=true";
    }

    public String goToCreateCar()
    {
        return "/pages/createcar.xhtml?faces-redirect=true&includeViewParams=true";
    }

    public String goToCreateCartracker()
    {
        return "/pages/createcartracker.xhtml?faces-redirect=true&includeViewParams=true";
    }

    public String goToHome()
    {
        return "/index.xhtml?faces-redirect=true&includeViewParams=true";
    }

    public String goToLinkcarowner()
    {
        return "/pages/linkcarowner.xhtml?faces-redirect=true&includeViewParams=true";
    }

    public String goToManageKilometerRate()
    {
        return "/pages/managekilometerrate.xhtml?faces-redirect=true&includeViewParams=true";
    }


}
