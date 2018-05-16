package rest;

import classes.Invoice;
import dao.InvoiceDao;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/invoice")
public class InvoiceService
{

    @Inject
    private InvoiceDao invoiceDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInvoices()
    {

        GenericEntity<List<Invoice>> items = new GenericEntity<List<Invoice>>(invoiceDao.getAllInvoices()) {};

        if (items.getEntity() == null || items.getEntity().isEmpty())
        {
            return Response.status(Response.Status.NOT_FOUND).entity("No invoices found").build();
        }

        return Response.status(Response.Status.OK).entity(items).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getInvoice(@PathParam("id") int id)
    {
        Invoice i = invoiceDao.getInvoiceByInvoiceId(id);

        if (i == null)
        {
            return Response.status(Response.Status.NOT_FOUND).entity("Could not found invoice with id " + id).build();
        }

        return Response.status(Response.Status.OK).entity(i).build();
    }
}
