package service;

import backrest.RekeningAdministratieREST;
import domain.Invoice;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class RekeningAdministratieService {

    @Inject
    RekeningAdministratieREST rest;

    public List<Invoice> GetInvoicesByVehicleAndOwner(int trackerId, int ownerId) {
        return rest.GetInvoices(trackerId, ownerId);
    }
}
