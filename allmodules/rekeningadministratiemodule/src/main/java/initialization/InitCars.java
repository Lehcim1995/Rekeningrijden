package initialization;

import classes.VehicleParser;
import service.VehicleService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class InitCars
{

    @Inject
    VehicleService vehicleService;

    @PostConstruct
    public void initData()
    {
        try
        {
            // TODO link must be gud
            new VehicleParser().Parse("D:/School/Javaprojects/Rekeningrijden/allmodules/rekeningadministratiemodule/src/main/resources/newdata.xml")
                               .forEach(vehicle -> {
                                   try
                                   {
                                       vehicleService.createVehicle(vehicle);
                                   }
                                   catch (Exception e)
                                   {
                                       System.out.println("Vehicle " + vehicle.getLicensePlate() + " is wrong");
                                       System.out.println(e.getMessage());
                                   }
                               });
        }
        catch (Exception e)
        {

        }
//        catch (CreationException e)
//        {
//            System.out.println("startup creating vehicles didnt work");
//            e.printStackTrace();
//        }
    }

}
