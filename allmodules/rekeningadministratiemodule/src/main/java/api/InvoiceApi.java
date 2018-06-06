package api;

import domain.Invoice;
import domain.MonthEnum;
import domain.PaymentEnum;
import jsonBodies.InvoiceBody;
import model.InvoiceListModel;
import service.InvoiceService;
import service.OwnerService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RequestScoped
@Path("/invoices")
public class InvoiceApi {

    @Inject
    private InvoiceService invoiceService;

    @Inject
    private OwnerService ownerService;

    @GET
    @Produces(APPLICATION_JSON)
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/tracker/{trackerId}")
    public Invoice getInvoiceByTrackerId(@PathParam("trackerId") String trackerId) {
        return invoiceService.getInvoiceByTrackerId(trackerId);
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/{vehicleId}/vehicleownerinvoices/{ownerId}")
    public InvoiceListModel getInvoicesByVehicleAndOwner(@PathParam("vehicleId") String vehicleId, @PathParam("ownerId") int ownerId) {
        InvoiceListModel model = new InvoiceListModel(invoiceService.getInvoicesByVehicleAndOwner(vehicleId, ownerId));
        return model;
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/owner/{ownerId}")
    public List<Invoice> getInvoicesByOwner(@PathParam("ownerId") int ownerId) {
        return invoiceService.getInvoicesByOwner(ownerService.findOwnerById(ownerId));
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/payment/{paymentStatus}")
    public List<Invoice> getInvoicesByPaymentStatus(@PathParam("paymentStatus") PaymentEnum paymentEnum) {
        return invoiceService.getInvoicesByPaymentStatus(paymentEnum);
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/tracker/{trackerId}/{month}")
    public List<Invoice> getInvoicesByTrackerIdAndMonth(@PathParam("trackerId") String trackerId, @PathParam("month") MonthEnum monthEnum) {
        return invoiceService.getInvoicesByTrackerIdAndMonth(trackerId, monthEnum);
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/owner/{ownerId}/{month}")
    public List<Invoice> getInvoicesByOwnerAndMonth(@PathParam("ownerId") int ownerId, @PathParam("month") MonthEnum monthEnum) {
        return invoiceService.getInvoicesByOwnerAndMonth(ownerService.findOwnerById(ownerId), monthEnum);
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/payment/{paymentStatus}/{month}")
    public List<Invoice> getInvoicesByPaymentStatusAndMonth(@PathParam("paymentStatus") PaymentEnum paymentEnum, @PathParam("month") MonthEnum monthEnum) {
        return invoiceService.getInvoicesByPaymentStatusAndMonth(paymentEnum, monthEnum);
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
