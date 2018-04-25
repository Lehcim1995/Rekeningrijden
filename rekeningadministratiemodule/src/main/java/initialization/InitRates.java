package initialization;

import classes.FuelEnum;
import classes.RateCategory;
import service.RateService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Date;

public class InitRates {

    @Inject
    private RateService rateService;
    //@Inject
    //private OwnerService ownerService;

    public InitRates() {

    }

    @PostConstruct
    public void initData() {
        try {
            RateCategory rateRoad1 = rateService.create(FuelEnum.Benzine, 1.05, new Date(), null);
            rateService.create("Weg 1", 11, new Date(), null, rateRoad1);

            RateCategory rateRoad2 = rateService.create(FuelEnum.Diesel, 1.03, new Date(), null);
            rateService.create("Weg 2", 17, new Date(), null, rateRoad2);

        } catch (Exception e) {
            System.out.print("Something went wrong when initializing the rates: " + e.getStackTrace());
        }
    }
}
