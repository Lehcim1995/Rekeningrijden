package rest;

import classes.Checkpoint;
import classes.DataObject;
import services.SimulatieService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/simulatie")
public class SimulatieRest
{
    @Inject
    private SimulatieService simulatieService;

    @GET
    public Response getpoints()
    {
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response setPoints(DataObject dataObject)
    {
        System.out.println("Lon " + dataObject.getStepLon());
        System.out.println("Lat " + dataObject.getStepLat());
        System.out.println("id " + dataObject.getId());

        Checkpoint cp = new Checkpoint(dataObject.getStepLon(), dataObject.getStepLat());

        simulatieService.create(cp);

        return Response.ok(dataObject).build();
    }
}
