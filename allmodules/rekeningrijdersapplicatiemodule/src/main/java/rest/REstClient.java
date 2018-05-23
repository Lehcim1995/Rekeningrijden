package rest;


import classes.Invoice;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

@Stateless
public class REstClient
{
    private Client client;

    public REstClient() {
        client = Client.create();
    }

    @PostConstruct
    public void init()
    {
        client = Client.create();
    }

    /**
     * A get response on a Url and an expected object (can be a list)
     *
     * @param url The url for the call
     * @param expectedObject The class of the expected object
     * @param <F> // The generic of the class
     * @return Object of type <F>
     */
    public <F> F getREstResponse(String url, Class<F> expectedObject)
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
}
