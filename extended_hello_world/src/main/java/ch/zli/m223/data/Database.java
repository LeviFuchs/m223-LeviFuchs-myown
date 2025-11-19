package ch.zli.m223.data;


import ch.zli.m223.model.User;
import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class Database {

    @Inject
    EntityManager entityManager;

    @Startup
    @Transactional
    public void seedDatabase() {
        createUsers();
    }

    public void createUsers() {
        var user1 = new User();
        user1.setFirstName("Levi");
        user1.setLastName("Fuchs");
        entityManager.persist(user1);

    }

}
