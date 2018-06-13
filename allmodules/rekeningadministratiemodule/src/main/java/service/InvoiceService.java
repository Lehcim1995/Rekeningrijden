package service;

import dao.InvoiceDao;
import dao.OwnerDao;
import domain.*;
import rest.REstClient;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Stateless
public class InvoiceService implements Serializable{

    @Inject
    private InvoiceDao invoiceDao;

    @Inject
    private OwnerDao ownerDao;

    public boolean createInvoiceWithVehicle(Vehicle vehicle, Date month)
    {
        //
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int monthValue = localDate.getMonthValue();


        return false;
    }

    public boolean createInvoiceWithVehicle(Vehicle vehicle, MonthEnum monthEnum)
    {
        // get verplaatsingen
        // Calculate the distance for every verplaatsing
        // Get all the road prices
        // Get all vehicle prices
        // Save the invoice

        REstClient client = new REstClient();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate localDate = LocalDate.now(); // TODO get month
        LocalDate localDateStart = localDate.minusMonths(1);
        localDateStart = localDateStart.withDayOfMonth(localDateStart.lengthOfMonth());
        LocalDate localDateEnd = localDate.plusMonths(1);
        localDateEnd = localDateEnd.withDayOfMonth(1);

        String url = "localhost:8080/verplaatsingsmodule/rest/verplaatsing/" + vehicle.getID() + "/waypoints?";
        url += "startdate=" + localDateStart.format(dtf) + "&";
        url += "enddate=" + localDateEnd.format(dtf);

        List<Verplaatsing> verplaatsings = client.getREstResponse(url, List.class);

        AtomicReference<Float> total = new AtomicReference<>((float) 0);

        List<InvoiceData> invoiceData = new ArrayList<>();

        verplaatsings.forEach(verplaatsing -> {

            total.updateAndGet(v -> (float) (v + verplaatsing.distance() * 1.1f));
            invoiceData.add(new InvoiceData());

        });

        Invoice invoice = new Invoice(vehicle.getTracker().getID(), vehicle.getOwner(), total.get(),PaymentEnum.Open,  monthEnum);
        invoice.setInvoiceData(invoiceData);

        invoiceDao.create(invoice);

        return true;
    }



    public boolean createInvoice(String vehicleTrackerId, Owner owner, double overall, PaymentEnum paymentStatus, MonthEnum month) {
        Invoice invoice = new Invoice(vehicleTrackerId, owner, overall, paymentStatus, month);
        if (invoiceDao.create(invoice) != null) {
            return true;
        }
        return false;
    }

    public boolean changePaymentStatusById(int invoiceId, String paymentStatus) {
        return invoiceDao.changePaymentStatusById(invoiceId, paymentStatus);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceDao.getAllInvoices();
    }

    public Invoice getInvoiceByTrackerId(String trackerId) {
        return invoiceDao.getInvoiceByTrackerId(trackerId);
    }

    public List<Invoice> getInvoicesByOwner(Owner owner){
        return invoiceDao.getInvoicesByOwner(owner);
    }

    public List<Invoice> getInvoicesByPaymentStatus(PaymentEnum paymentEnum) {
        return invoiceDao.getInvoicesByPaymentStatus(paymentEnum);
    }

    public List<Invoice> getInvoicesByTrackerIdAndMonth(String trackerId, MonthEnum monthEnum) {
        return invoiceDao.getInvoicesByTrackerIdAndMonth(trackerId, monthEnum);
    }

    public List<Invoice> getInvoicesByOwnerAndMonth(Owner owner, MonthEnum monthEnum) {
        return invoiceDao.getInvoicesByOwnerAndMonth(owner, monthEnum);
    }

    public List<Invoice> getInvoicesByPaymentStatusAndMonth(PaymentEnum paymentEnum, MonthEnum monthEnum) {
        return invoiceDao.getInvoicesByPaymentStatusAndMonth(paymentEnum, monthEnum);
    }

    public List<Invoice> getInvoicesByVehicleAndOwner(String vehicleId, int ownerById) {
        return invoiceDao.getInvoicesByVehicleAndOwner(vehicleId, ownerDao.findOwnerById(ownerById));
    }
}
