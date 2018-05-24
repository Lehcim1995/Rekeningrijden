package rest;

import domain.Vehicle;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class VehicleCollectionRest
{
    @Inject
    REstClient restClient;

    private static final String WEB_URL = "http://localhost:8080/";
    private static final String REST_END_POINT = WEB_URL + "rekeningadministratiemodule/rest/vehicle";

    public List<Vehicle> getCars()
    {
        return restClient.getREstResponse(REST_END_POINT, List.class);
    }
}
