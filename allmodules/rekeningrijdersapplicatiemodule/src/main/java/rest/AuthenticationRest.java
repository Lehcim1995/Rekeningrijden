package rest;

import model.RegisteredModel;
import rest.JsonBodyClasses.RegisterModel;
import service.AuthenticationService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/authenticate")
public class AuthenticationRest {

    @Inject
    REstClient restClient;

    @Inject
    AuthenticationService authenticationService;

    private static final String WEB_URL = "http://localhost:8080";
    private static final String REST_END_POINT = WEB_URL + "/rekeningadministratiemodule/api/owner";

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(RegisterModel profile) {

        // TODO return profile and show password
        if(authenticationService.findByBSN(profile.getBsn()) != null) {
            return Response.status(Response.Status.CONFLICT).entity("Account already registered").build();
        }

        RegisteredModel adminInfo = restClient.getREstResponse(REST_END_POINT + "/" + profile.getEmail() + "/register/" + profile.getBsn(), RegisteredModel.class);
        if (adminInfo != null) {
            RegisteredModel owner = authenticationService.RegisterProfile(adminInfo);
            return Response.ok(owner).build();
        }

        return Response.status(Response.Status.NOT_FOUND).entity("Was not able to register your account").build();
    }
}
