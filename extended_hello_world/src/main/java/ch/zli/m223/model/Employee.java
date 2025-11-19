package ch.zli.m223.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee extends PanacheEntity {

    private String email;

    private String password;

    private String role;

    @PrePersist
    @PreUpdate
    public void setRoleBasedOnEmail() {
        if ("levi.fuchs@gmail.com".equals(this.email)) {
            this.role = "admin";
        } else {
            this.role = "user";
        }
    }

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