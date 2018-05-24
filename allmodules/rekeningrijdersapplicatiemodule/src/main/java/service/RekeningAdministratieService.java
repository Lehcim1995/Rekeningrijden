package service;

import domain.Invoice;
import rest.CollectInvoicesREst;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class RekeningAdministratieService {

    @Inject
    CollectInvoicesREst rest;

    public List<Invoice> GetInvoicesByVehicleAndOwner(String trackerId, int ownerId) {
        return rest.getVehicleOwnerInvoices(trackerId, ownerId);
    }
}