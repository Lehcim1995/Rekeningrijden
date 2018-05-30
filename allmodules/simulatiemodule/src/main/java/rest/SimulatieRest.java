package rest;

import classes.DataObject;
import domain.Checkpoint;
import domain.Vehicle;
import services.SimulatieService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/simulatie")
public class SimulatieRest
{
    @Inject
    private SimulatieService simulatieService;

    @Inject
    private VehicleCollectionRest vehicleCollectionRest;

    @GET
    public Response getpoints()
    {

        return Response.ok()
                       .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response setPoints(DataObject dataObject) {
        System.out.println("Lon " + dataObject.getStepLon() + " \n" + "Lat " + dataObject.getStepLat() + " \n" + "car id " + dataObject.getCarId() + " \n" + "stepid " + dataObject.getStepId() + " \n" + "date " + dataObject.getDate());

        Checkpoint cp = new Checkpoint(dataObject.getStepLon(), dataObject.getStepLat());

        simulatieService.create(cp);

        return Response.ok(dataObject)
                       .build();
    }

    @GET
    @Path("car")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCars()
    {
        // TODO get cars from rekeningadministratiesysteem and return them

        GenericEntity<List<Vehicle>> i = new GenericEntity<List<Vehicle>>( vehicleCollectionRest.getCars()) {};

        return Response.ok(i)
                       .build();
    }
}
