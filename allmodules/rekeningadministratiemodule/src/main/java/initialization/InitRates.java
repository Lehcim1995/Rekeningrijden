package initialization;

import domain.FuelEnum;
import domain.KilometerRate;
import domain.RateCategory;
import domain.Road;
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
            RateCategory rateRoad2 = rateService.create(FuelEnum.Diesel, 1.03, new Date(), null);

            KilometerRate kilometerRateRoad1 = rateService.create(road1, 11, new Date(), null, rateRoad1);
            KilometerRate kilometerRateRoad2 = rateService.create(road2, 17, new Date(), null, rateRoad2);

            road1.setKilometerRate(kilometerRateRoad1);
            road2.setKilometerRate(kilometerRateRoad2);



        } catch (Exception e) {
            System.out.print("Something went wrong when initializing the rates: " + e.getStackTrace());
        }
    }
}
