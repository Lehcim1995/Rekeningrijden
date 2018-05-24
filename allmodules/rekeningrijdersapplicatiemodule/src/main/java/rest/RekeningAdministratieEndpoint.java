package rest;

import domain.Invoice;
import rest.JsonBodyClasses.LoginProfileBody;
import service.RekeningAdministratieService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Stateless
@Path("/rekeningadministratie")
public class RekeningAdministratieEndpoint {

    @Inject
    RekeningAdministratieService rekeningAdministratieService;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser(LoginProfileBody loginProfileBody) {
        try {

            // Authenticate the user using the credentials provided
            rekeningAdministratieService.authenticate(loginProfileBody.getLogin(), loginProfileBody.getPassword());

            // Issue a token for the user
            String token = rekeningAdministratieService.issueToken(loginProfileBody.getLogin());

            // Return the token on the response
            return Response.ok("\"" + token + "\"")
                    .header(AUTHORIZATION, token)
                    .build();
        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }
    }

    @GET
    public String test() {
        return "test";
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/{vehicleId}/vehicleownerinvoices/{ownerId}")
    public List<Invoice> getInvoicesByVehicleAndOwner(@PathParam("vehicleId") int vehicleId, @PathParam("ownerId") int ownerId) {
        return rekeningAdministratieService.GetInvoicesByVehicleAndOwner(vehicleId, ownerId);

    }
}