package rest;

import domain.Verplaatsing;
import services.VerplaatsingsService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/verplaatsing")
public class VerplaatsingRestEndpoint
{
    @Inject
    private VerplaatsingsService verplaatsingsService;

    @POST
    @Path("waypoint")
    public Response addWaypoint(Verplaatsing verplaatsing)
    {
        verplaatsingsService.create(verplaatsing);

        return Response.ok("added waypoint")
                       .build();
    }

    @POST
    @Path("waypointall")
    public Response addWaypoints(List<Verplaatsing> verplaatsings)
    {
        verplaatsings.forEach(verplaatsingsService::create);

        return Response.ok("added waypoints")
                       .build();
    }
}
