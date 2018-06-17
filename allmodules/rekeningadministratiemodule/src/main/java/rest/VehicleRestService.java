package rest;

import domain.Checkpoint;
import domain.Vehicle;
import service.VehicleService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/vehicle")
public class VehicleRestService
{
    @Inject
    VehicleService vehicleService;

    @Inject
    CollectVehicleLocationService collectVehicleLocationService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCars()
    {
        GenericEntity<List<Vehicle>> i = new GenericEntity<List<Vehicle>>(vehicleService.getVehicles()) {};

        return Response.ok(i)
                       .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{kenteken}")
    public Response getCars(@PathParam("kenteken") String id)
    {
        Vehicle v = vehicleService.getVehicleByLicencePlate(id);

        return Response.ok(v)
                       .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getcarbycartracker/{cartracker}")
    public Response getCarByTracker(@PathParam("cartracker") String cartracker)
    {
        Vehicle v = vehicleService.getVehicleByVehicleTracker(cartracker);
        return Response.ok(v).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{kenteken}/locationdata")
    public Response getCarsLocation(@PathParam("kenteken") String id)
    {

        GenericEntity<List<Checkpoint>> entity = new GenericEntity<List<Checkpoint>>(collectVehicleLocationService.getVehicleCheckpoints(id)) {};

        return Response.ok("nothing here")
                       .entity(entity)
                       .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("cars/{ownerid}")
    public Response getCarsOfOwner(@PathParam("ownerid") String ownerid)
    {
        List<Vehicle> carsOfOWner = vehicleService.getCarsOfOwner(ownerid);
        return Response.ok().entity(carsOfOWner).build();
    }
}
