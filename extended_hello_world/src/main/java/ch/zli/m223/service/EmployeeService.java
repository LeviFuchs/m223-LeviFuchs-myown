package ch.zli.m223.service;

import ch.zli.m223.model.Employee;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EmployeeService {

    @Transactional
    public Employee signup(Employee employee) {
        // Passwort hashen
        String hashed = org.mindrot.jbcrypt.BCrypt.hashpw(employee.password, org.mindrot.jbcrypt.BCrypt.gensalt());
        employee.password = hashed;

        // Rollen bestimmen
        if ("levi.fuchs@gmail.com".equalsIgnoreCase(employee.email.trim())) {
            employee.role = "Admin";
        } else {
            employee.role = "User";
        }

        // Persist verwenden (PanacheEntity Methode)
        employee.persist();
        return employee;
    }

    public Employee findByEmail(String email) {
        return Employee.find("email", email).firstResult();
    }

    public Employee findById(Long id) {
        return Employee.findById(id);
    }
}