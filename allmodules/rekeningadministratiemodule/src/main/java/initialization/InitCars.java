package initialization;

import classes.VehicleParser;
import service.VehicleService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

@Singleton
@Startup
public class InitCars
{

    @Inject
    VehicleService vehicleService;

    @Context
    private ServletContext context;

    @PostConstruct
    public void initData()
    {
        try
        {
            String path = context.getRealPath("") + "/resources/cars/newdata.xml";

            new VehicleParser().Parse(path)
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
