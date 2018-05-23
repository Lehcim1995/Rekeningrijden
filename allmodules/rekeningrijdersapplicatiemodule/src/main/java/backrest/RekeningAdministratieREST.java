package backrest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domain.Invoice;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class RekeningAdministratieREST {

    private final Gson gson = new Gson();

    private static String url = "localhost:8080/rekeningadministratiemodule/api/invoices/";
    private static HttpClient client;

    @PostConstruct
    public static void init() {
        client = HttpClientBuilder.create().build();
    }

    public static Owner LogIn() {
        return null;
    }

    public List<Invoice> GetInvoices(int vehicleTrackerID, int ownerID) {

        HttpGet request = new HttpGet(url + vehicleTrackerID + "/vehicleownerinvoices/" + ownerID);

        try {
            HttpResponse response = client.execute(request);
            String result = getResult(response);

            Type listType = new TypeToken<ArrayList<Invoice>>(){}.getType();
            List<Invoice> invoices = gson.fromJson(result, listType);

            return invoices;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getResult(HttpResponse response) throws IOException {

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        return rd.lines().collect(Collectors.joining());
    }
}