package rest;

import com.google.gson.reflect.TypeToken;
import domain.Invoice;
import domain.Owner;
import model.InvoiceListModel;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CollectInvoicesREst
{
    @Inject
    REstClient restClient;

    private static final String WEB_URL = "http://localhost:8080/";
    private static final String REST_END_POINT = WEB_URL + "rekeningadministratiemodule/rest/invoices";

    public List<Invoice> collectInvoices()
    {
        return restClient.getREstResponse(REST_END_POINT, List.class);
    }

    public List<Invoice> collectInvoicesOwner(Owner owner)
    {
        return collectInvoicesOwner(owner.getId());
    }

    public List<Invoice> collectInvoicesOwner(int ownerId) {
        return restClient.getREstResponse(REST_END_POINT + "/cpr/" + ownerId, List.class);
    }

    public Invoice getInvoice(int id)
    {
        return restClient.getREstResponse(REST_END_POINT + "/" + id, Invoice.class);
    }

    public List<Invoice> getVehicleOwnerInvoices(int trackerId, int ownerId) {
        return restClient.getREstResponse(REST_END_POINT + "/" + trackerId + "/vehicleownerinvoices/" + ownerId, InvoiceListModel.class).getInvoices();
    }
}
