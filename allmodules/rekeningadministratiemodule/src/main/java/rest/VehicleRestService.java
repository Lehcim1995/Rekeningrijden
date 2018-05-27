package rest;

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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCars()
    {
        GenericEntity<List<Vehicle>> i = new GenericEntity<List<Vehicle>>(vehicleService.getVehicles()) {};

        return Response.ok(i).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{kenteken}")
    public Response getCars(@PathParam("kenteken") String id)
    {
        Vehicle v = vehicleService.getVehicleByLicencePlate(id);

        return Response.ok(v).build();
    }
}
