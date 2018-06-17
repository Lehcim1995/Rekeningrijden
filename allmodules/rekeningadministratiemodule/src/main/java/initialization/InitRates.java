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
import java.util.List;

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

            List<RateCategory> categories = road1.getRateCategories();
            List<RateCategory> categories2 = road2.getRateCategories();

            for (FuelEnum type : FuelEnum.values()) {
                categories.add(rateService.create(type, road1, 1, new Date(), null));
                categories2.add(rateService.create(type, road2, 1, new Date(), null));
            }


            KilometerRate kilometerRateRoad1 = rateService.create(road1, 11, new Date(), null);
            KilometerRate kilometerRateRoad2 = rateService.create(road2, 17, new Date(), null);

            road1.setKilometerRate(kilometerRateRoad1);
            road1.setRateCategories(categories);

            road2.setKilometerRate(kilometerRateRoad2);
            road2.setRateCategories(categories2);

            roadService.editRoad(road1);
            roadService.editRoad(road2);

        } catch (Exception e) {
            System.out.print("Something went wrong when initializing the rates: " + e.getStackTrace());
        }
    }
}
