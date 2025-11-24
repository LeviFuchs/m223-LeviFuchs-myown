package ch.zli.m223.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee extends PanacheEntity {

    public String email;
    public String password;
    public String role;

    // Leerer Constructor
    public Employee() {
    }

    // Constructor mit Parametern
    public Employee(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getter und Setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}