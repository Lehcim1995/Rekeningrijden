package rest;


import classes.Invoice;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.GenericEntity;

public class REstClient
{

    private Client client;

    public REstClient() {
        client = Client.create();
    }

    private <F> F getREstResponse(String url, Class<F> expectedObject)
    {
        F returnObject;

        WebResource webResource = client
                .resource(url);

        ClientResponse response = webResource.accept("application/json")
                                             .get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        String json = response.getEntity(String.class);

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        returnObject = gson.fromJson(json, expectedObject);

        return returnObject;
    }


    public void collectInvoices()
    {
        getREstResponse("", Invoice.class);
    }
}
