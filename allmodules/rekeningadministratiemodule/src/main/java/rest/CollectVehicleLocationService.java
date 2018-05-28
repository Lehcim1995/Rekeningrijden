package rest;

import domain.Checkpoint;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CollectVehicleLocationService
{
    private static final String BASE_URL = "";
    private static final String REST_URL = BASE_URL + "";

    @Inject
    REstClient restClient;

    public List<Checkpoint> getVehicleCheckpoints(String plate)
    {
        restClient.getREstResponse(REST_URL, List.class);

        return new ArrayList<>();
    }
}
