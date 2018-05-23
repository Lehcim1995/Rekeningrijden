package rest;

import classes.DataObject;
import domain.Checkpoint;
import services.SimulatieService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/simulatie")
public class SimulatieRest {
    @Inject
    private SimulatieService simulatieService;

    @GET
    public Response getpoints() {
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response setPoints(DataObject dataObject) {
        System.out.println("Lon " + dataObject.getStepLon() + " \n" +
                "Lat " + dataObject.getStepLat() + " \n" +
                "id " + dataObject.getId() + " \n" +
                "stepid " + dataObject.getStepId() + " \n" +
                "date " + "//todo date");

        Checkpoint cp = new Checkpoint(dataObject.getStepLon(), dataObject.getStepLat());

        simulatieService.create(cp, dataObject.getId());

        return Response.ok(dataObject).build();
    }
}
