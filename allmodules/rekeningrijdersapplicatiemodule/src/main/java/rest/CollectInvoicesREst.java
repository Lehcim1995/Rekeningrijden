package rest;

import classes.Invoice;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CollectInvoicesREst
{
    @Inject
    REstClient restClient;

    private static final String WEB_URL = "http://localhost:8080/";
    private static final String REST_END_POINT = WEB_URL + "rekeningadministratiemodule/rest/invoice";

    public List<Invoice> collectInvoices()
    {
        return restClient.getREstResponse(REST_END_POINT, List.class);
    }
}
