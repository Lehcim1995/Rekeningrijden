package rest;

import domain.Verplaatsing;
import services.VerplaatsingsService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @GET
    @Path("/{car_id}/waypoints")
    public Response getWayPointFromCar(
            @PathParam("car_id") String licencePlate,
            @QueryParam("limit") int limit,
            @DefaultValue("") @QueryParam("startdate") String startDate,
            @DefaultValue("") @QueryParam("enddate") String endDate)
    {

        List<Verplaatsing> v;

        if (startDate.isEmpty() || endDate.isEmpty())
        {
            v = verplaatsingsService.getVerplaatsingsForVehicle(licencePlate);
        }
        else
        {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date startDateDate;
            Date endDateDate;
            try
            {
                startDateDate = df.parse(startDate);
                endDateDate = df.parse(endDate);

                v = verplaatsingsService.getVerplaatsingsForVehicle(licencePlate, startDateDate, endDateDate);
            }
            catch (ParseException e)
            {
                e.printStackTrace();

                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                               .entity(e.getMessage())
                               .build();
            }


        }

        GenericEntity<List<Verplaatsing>> entity = new GenericEntity<List<Verplaatsing>>(v) {};
        return Response.ok()
                       .entity(entity)
                       .build();
    }
}
