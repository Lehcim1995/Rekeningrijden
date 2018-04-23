package initialization;

import classes.MonthEnum;
import classes.Owner;
import classes.PaymentEnum;
import service.InvoiceService;
import service.OwnerService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class initinvoices {

    @Inject
    private InvoiceService invoiceService;
    @Inject
    private OwnerService ownerService;

    public initinvoices() {

    }

    @PostConstruct
    public void initData(){
        try
        {
            ownerService.create(878746, "Stefano", "", "Verhoeve", "Havikstraat", "Sliedrecht", "INGB12039", "Cactus");
            //Owner stefano = new Owner(878746, "Stefano", "", "Verhoeve", "Havikstraat", "Sliedrecht", "INGB12039", "Cactus");
            ownerService.create(887746, "Jasper", "van" ,"Son", "Dokkumstraat", "Tilburg", "INGB128397", "Cactus");
            ownerService.create(874687, "Nick", "", "Liebregts", "Steenkoolfabriek", "Gilze", "INGB1823971", "Cactus");
            //Owner jasper = new Owner(887746, "Jasper", "van" ,"Son", "Dokkumstraat", "Tilburg", "INGB128397", "Cactus");
            //Owner nick = new Owner(874687, "Nick", "", "Liebregts", "Steenkoolfabriek", "Gilze", "INGB1823971", "Cactus");


            invoiceService.createInvoice("DEN0849", ownerService.findOwnerById(878746),  50, PaymentEnum.Open, MonthEnum.April);
            invoiceService.createInvoice("DEN0984", ownerService.findOwnerById(887746),  70, PaymentEnum.Open, MonthEnum.April);
            invoiceService.createInvoice("DEN9084", ownerService.findOwnerById(874687),  9050, PaymentEnum.Open, MonthEnum.April);

        }
        catch(Exception e){
            System.out.print("Something went wrong when initializing the invoices: " + e.getStackTrace());
        }
    }
}
