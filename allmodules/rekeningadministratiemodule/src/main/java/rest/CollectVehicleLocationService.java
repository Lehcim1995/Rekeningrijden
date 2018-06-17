package rest;

import domain.Checkpoint;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CollectVehicleLocationService
{
//    private static final String WEB_URL = "http://localhost:8080/";
    private static final String BASE_URL = "http://localhost:8080/";
    private static final String REST_URL = BASE_URL + "verplaatsingsmodule/rest/vehicle/";

    @Inject
    REstClient restClient;

    public List<Checkpoint> getVehicleCheckpoints(String plate)
    {
//        restClient.getREstResponse(REST_URL, List.class);

        return restClient.getREstResponse(REST_URL + plate + "/waypoints", List.class);
    }
}
