package rest;

import classes.Verplaatsing;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/verplaatsing")
public class VerplaatsingRestService
{
    @GET
    @Path("waypoint")
    public Response addWaypoint(Verplaatsing verplaatsing)
    {
        return null;
    }

    @GET
    @Path("waypointall")
    public Response addWaypoints(List<Verplaatsing> verplaatsings)
    {
        return null;
    }
}
