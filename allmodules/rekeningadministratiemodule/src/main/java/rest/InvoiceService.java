package rest;

import classes.PdfCreator;
import dao.InvoiceDao;
import dao.OwnerDao;
import domain.Invoice;
import domain.Owner;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.List;

@Path("/invoice")
public class InvoiceService
{

    @Inject
    private InvoiceDao invoiceDao;

    @Inject
    private OwnerDao ownerDao;

    @Inject
    private PdfCreator pdfCreator;

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

    @GET
    @Path("/{id}/download")
    @Produces("application/pdf")
    public Response getFile(@PathParam("id") int id) {

        Invoice invoice = invoiceDao.getInvoiceByInvoiceId(id);


        File file = pdfCreator.createInvoicePdf(invoice);
//        File file = new File("D:\\School\\Javaprojects\\Rekeningrijden\\allmodules\\rekeningadministratiemodule\\src\\main\\resources\\invoices\\6_firstname.pdf");

        Response.ResponseBuilder response = Response.ok(file);
        response.header("Content-Disposition",
                "attachment; filename=" + file.getName());
        return response.build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cpr/{id}")
    public Response getInvoiceByUser(@PathParam("id") int cpr)
    {
        Owner o = ownerDao.findOwnerById(cpr);

        List<Invoice> i = invoiceDao.getInvoicesByOwner(o);

        if (i == null || i.isEmpty())
        {
            return Response.status(Response.Status.NOT_FOUND).entity("Could not found invoices for cpr " + cpr).build();
        }

        return Response.status(Response.Status.OK).entity(i).build();
    }
}
