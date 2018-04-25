package initialization;

import classes.FuelEnum;
import classes.RateCategory;
import service.RateService;
import service.RoadService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.Date;

@Singleton
@Startup
public class InitRoads {

    @Inject
    private RoadService roadService;

    public InitRoads() {

    }

    @PostConstruct
    public void initData() {
        try {

        } catch (Exception e) {
            System.out.print("Something went wrong when initializing the rates: " + e.getStackTrace());
        }
    }
}
