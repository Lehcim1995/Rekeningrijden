package initialization;

import classes.MonthEnum;
import classes.Owner;
import classes.PaymentEnum;
import service.InvoiceService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class initinvoices {

    @Inject
    private InvoiceService invoiceService;

    public initinvoices() {

    }

    @PostConstruct
    public void initData(){
        try
        {
            Owner stefano = new Owner(878746, "Stefano", null, "Verhoeve", "Havikstraat", "Sliedrecht", "INGB12039", "Cactus");
            Owner jasper = new Owner(887746, "Jasper", "van" ,"Son", "Dokkumstraat", "Tilburg", "INGB128397", "Cactus");
            Owner nick = new Owner(874687, "Nick", null, "Liebregts", "Steenkoolfabriek", "Gilze", "INGB1823971", "Cactus");


            invoiceService.createInvoice("DEN0849", stefano,  50, PaymentEnum.Open, MonthEnum.April);
            invoiceService.createInvoice("DEN0984", jasper,  70, PaymentEnum.Open, MonthEnum.April);
            invoiceService.createInvoice("DEN9084", nick,  9050, PaymentEnum.Open, MonthEnum.April);

        }
        catch(Exception e){
            System.out.print("Something went wrong when initializing the invoices: " + e.getStackTrace());
        }
    }
}
