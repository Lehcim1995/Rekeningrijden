package rest;

import domain.Invoice;
import service.RekeningAdministratieService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Stateless
@Path("/RekeningAdministratie")
public class RekeningAdministratieEndpoint {

    @Inject
    RekeningAdministratieService service;

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/{vehicleId}/vehicleownerinvoices/{ownerId}")
    public List<Invoice> getInvoicesByVehicleAndOwner(@PathParam("vehicleId") int vehicleId, @PathParam("ownerId") int ownerId) {
        return service.GetInvoicesByVehicleAndOwner(vehicleId, ownerId);
    }
}