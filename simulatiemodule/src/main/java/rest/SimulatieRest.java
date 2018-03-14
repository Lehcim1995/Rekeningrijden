package rest;

import classes.Checkpoint;
import classes.DataObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/simulatie")
public class SimulatieRest
{
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
        System.out.println("Lon" + dataObject.getStepLon());
        System.out.println("Lat" + dataObject.getStepLat());
        System.out.println("id" + dataObject.getId());

        Checkpoint cp = new Checkpoint(dataObject.getStepLon(), dataObject.getStepLat());

        return Response.ok(dataObject).build();
    }
}
