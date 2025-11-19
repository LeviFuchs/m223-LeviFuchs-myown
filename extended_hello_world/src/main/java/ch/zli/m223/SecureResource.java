package ch.zli.m223;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/secure")
public class SecureResource {

    @GET
    @Path("/data")
    @RolesAllowed("admin")
    public Response getSecureData() {
        return Response.ok("This is protected data.").build();
    }
}