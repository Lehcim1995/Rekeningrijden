package initialization;

import domain.*;
import org.apache.activemq.state.Tracked;
import service.InvoiceService;
import service.OwnerService;
import service.VehicleService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.Date;

@Singleton
@Startup
public class initinvoices {

    @Inject
    private InvoiceService invoiceService;
    @Inject
    private OwnerService ownerService;
    @Inject
    private VehicleService vehicleService;

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


            Vehicle v = vehicleService.createVehicleParam("ez 123 32", new Date(), 4200, FuelEnum.Diesel.toString());
            Owner o = ownerService.findOwnerById(878746);
            v.addOwner(o);
            VehicleTracker vt = vehicleService.createVehicleTrackerId("DEN0849", "TrackIT");
            v.setTracker(vt);

            invoiceService.createInvoice(v.getTracker().getID(), ownerService.findOwnerById(878746),  50, PaymentEnum.Open, MonthEnum.April);
            invoiceService.createInvoice(v.getTracker().getID(), ownerService.findOwnerById(878746),  70, PaymentEnum.Open, MonthEnum.Mei);
            invoiceService.createInvoice(v.getTracker().getID(), ownerService.findOwnerById(878746),  48, PaymentEnum.Open, MonthEnum.Juni);

        }
        catch(Exception e){
            System.out.print("Something went wrong when initializing the invoices: " + e.getStackTrace());
        }
    }
}
