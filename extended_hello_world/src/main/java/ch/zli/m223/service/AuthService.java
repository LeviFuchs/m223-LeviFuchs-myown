package ch.zli.m223.service;

import ch.zli.m223.model.Employee;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class AuthService {

    @Inject
    EmployeeService employeeService;

    public Response login(String email, String password) {
        Employee employee = employeeService.findByEmail(email);

        if (employee == null) {
            return Response.status(401).entity("Employee not found").build();
        }

        boolean pwValid = org.mindrot.jbcrypt.BCrypt.checkpw(password, employee.password);
        if (!pwValid) {
            return Response.status(401).entity("Invalid password").build();
        }

        // Token mit Rolle erstellen - groups() erwartet eine Collection
        String token = Jwt.upn(employee.email)
                .issuer("https://example.com/issuer")
                .groups(employee.role)
                .sign();

        return Response.ok(token).build();
    }
}