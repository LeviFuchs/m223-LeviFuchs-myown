package ch.zli.m223;

import ch.zli.m223.service.AuthService;
import ch.zli.m223.service.EmployeeService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SecureResource {

    @Inject
    AuthService authService;

    @Inject
    EmployeeService employeeService;

    @POST
    @Path("/login")
    public Response getSecureData(LoginRequest req) {
        return authService.login(req.email, req.password);
    }

    public static class LoginRequest {
        public String email;
        public String password;
    }

}