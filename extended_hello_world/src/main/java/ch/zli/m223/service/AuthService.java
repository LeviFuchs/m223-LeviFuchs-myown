package ch.zli.m223.service;

import ch.zli.m223.model.Employee;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashSet;

@ApplicationScoped
public class AuthService {

    @Inject
    EmployeeService employeeService;

    public Response generateToken(String email, String password) {
        try {
            // Find employee by email
            Employee employee = employeeService.findByEmail(email);

            if (employee == null || !employee.getPassword().equals(password)) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Invalid credentials")
                        .build();
            }

            // Generate token with the employee's role (admin or user)
            String token = Jwt.upn(employee.getEmail())
                    .issuer("https://example.com/issuer")
                    .groups(new HashSet<>(Arrays.asList(employee.getRole())))
                    .sign();

            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}