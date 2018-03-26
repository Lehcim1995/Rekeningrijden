package api;

import classes.Invoice;
import jsonBodies.InvoiceBody;
import service.InvoiceService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RequestScoped
@Path("/invoices")
public class InvoiceApi {

    @Inject
    private InvoiceService invoiceService;

    @GET
    @Produces(APPLICATION_JSON)
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @POST
    @Produces(APPLICATION_JSON)
    public Response createInvoice(InvoiceBody invoiceBody) {
        try {
            invoiceService.createInvoice(invoiceBody.getVehicleTrackerId(), invoiceBody.getOwner(), invoiceBody.getOverall(), invoiceBody.getPaymentStatus(), invoiceBody.getMonth());
            return Response.ok("Ok").build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }
}
