package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/verplaatsing")
public class VerplaatsingRestService
{
    @GET
    public Response getStuff()
    {
        return Response.ok("Works").build();
    }
}
