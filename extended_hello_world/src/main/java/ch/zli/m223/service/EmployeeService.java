package ch.zli.m223.service;

import ch.zli.m223.model.Employee;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class EmployeeService {

    @Transactional
    public Employee createEmployee(String email, String password) {
        Employee employee = new Employee();
        employee.setEmail(email);
        employee.setPassword(password);
        // Role will be set automatically by @PrePersist in Employee entity
        employee.persist();
        return employee;
    }

    public Employee findByEmail(String email) {
        return Employee.find("email", email).firstResult();
    }

    public List<Employee> getAllEmployees() {
        return Employee.listAll();
    }

    public Employee findById(Long id) {
        return Employee.findById(id);
    }
}