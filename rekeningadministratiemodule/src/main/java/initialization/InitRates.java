package initialization;

import classes.FuelEnum;
import classes.KilometerRate;
import classes.RateCategory;
import classes.Road;
import service.RateService;
import service.RoadService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.Date;

@Singleton
@Startup
public class InitRates {

    @Inject
    private RateService rateService;

    @Inject
    private RoadService roadService;

    //@Inject
    //private OwnerService ownerService;

    public InitRates() {

    }

    @PostConstruct
    public void initData() {
        try {

            Road road1 = roadService.createRoad("Weg 1", null, null);
            Road road2 = roadService.createRoad("Weg 2", null, null);

            RateCategory rateRoad1 = rateService.create(FuelEnum.Benzine, 1.05, new Date(), null);
            //TODO: road meegeven ipv van string
            KilometerRate kilometerRateRoad1 = rateService.create("Weg 1", 11, new Date(), null, rateRoad1);


            RateCategory rateRoad2 = rateService.create(FuelEnum.Diesel, 1.03, new Date(), null);
            //TODO: road meegeven ipv van string
            KilometerRate kilometerRateRoad2 = rateService.create("Weg 2", 17, new Date(), null, rateRoad2);

        } catch (Exception e) {
            System.out.print("Something went wrong when initializing the rates: " + e.getStackTrace());
        }
    }
}
