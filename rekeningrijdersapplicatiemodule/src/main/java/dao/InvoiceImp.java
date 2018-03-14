package dao;

import classes.Invoice;
import classes.Owner;

import java.util.concurrent.ConcurrentHashMap;

public class InvoiceImp implements InvoiceDAO {

    private ConcurrentHashMap<String, Invoice> vehicleInvoices;


    @Override
    public void create(Invoice invoice) {
        vehicleInvoices.put(invoice.getVehicleTrackerId(), invoice);
    }

    @Override
    public void edit(Invoice invoice) {
        this.delete(invoice);
        vehicleInvoices.put(invoice.getVehicleTrackerId(), invoice);
    }

    @Override
    public void delete(Invoice invoice) {
        vehicleInvoices.remove(invoice.getVehicleTrackerId());
    }

    @Override
    public Invoice findByOwner(Owner owner) {
        return null;
    }
}
