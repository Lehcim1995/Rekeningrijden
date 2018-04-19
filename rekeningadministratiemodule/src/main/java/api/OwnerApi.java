package api;

import classes.Invoice;
import classes.Owner;
import jsonBodies.InvoiceBody;
import jsonBodies.OwnerBody;
import service.OwnerService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RequestScoped
@Path("/owner")
public class OwnerApi {

    @Inject
    private OwnerService ownerService;

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/{citizenId}")
    public Owner getOwnerById(@PathParam("citizenId") int citizenId) {
        return ownerService.findOwnerById(citizenId);
    }

    @POST
    @Produces(APPLICATION_JSON)
    public Response createOwner(OwnerBody ownerBody) {
        try {
            ownerService.create(ownerBody.getCitizenId(), ownerBody.getFirstName(), ownerBody.getMiddleName(), ownerBody.getLastName(), ownerBody.getAddress(), ownerBody.getCity(), ownerBody.getAccountNumber(), ownerBody.getPassword());
            return Response.ok("Ok").build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Produces(APPLICATION_JSON)
    public Response updateOwner(OwnerBody ownerBody) {
        try {
            ownerService.update(ownerBody.getCitizenId(), ownerBody.getFirstName(), ownerBody.getMiddleName(), ownerBody.getLastName(), ownerBody.getAddress(), ownerBody.getCity(), ownerBody.getAccountNumber(), ownerBody.getPassword());
            return Response.ok("Ok").build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(APPLICATION_JSON)
    public List<Owner> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @POST
    @Produces(APPLICATION_JSON)
    @Path("/{vehicleId}/{citizenId}")
    public Response linkVehicleToOwner(@PathParam("vehicleId") int vehicleId, @PathParam("citizenId") int citizenId) {
        try {
            ownerService.linkVehicleToOwner(vehicleId, citizenId);
            return Response.ok("Ok").build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(APPLICATION_JSON)
    @Path("/previousVehicle/{vehicleId}/{citizenId}")
    public Response linkPreviousVehicleToOwner(@PathParam("vehicleId") int vehicleId, @PathParam("citizenId") int citizenId) {
        try {
            ownerService.linkPreviousVehicleToOwner(vehicleId, citizenId);
            return Response.ok("Ok").build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }
}
