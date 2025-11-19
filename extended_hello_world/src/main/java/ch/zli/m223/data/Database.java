package ch.zli.m223.data;

import java.math.BigDecimal;

import ch.zli.m223.model.Room;
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
        createRooms();
    }

    public void createUsers() {
        var user1 = new User();
        user1.setFirstName("Levi");
        user1.setLastName("Fuchs");
        entityManager.persist(user1);
        var user2 = new User();
        user2.setFirstName("Noah");
        user2.setLastName("Burren");
        entityManager.persist(user2);
    }

    public void createRooms() {
        var room1 = new Room();
        room1.setRoomNumber("101");
        room1.setFloor(1);
        room1.setPricePerNight(new BigDecimal("120.00"));
        entityManager.persist(room1);

        var room2 = new Room();
        room2.setRoomNumber("102");
        room2.setFloor(1);
        room2.setPricePerNight(new BigDecimal("150.00"));
        entityManager.persist(room2);

        var room3 = new Room();
        room3.setRoomNumber("201");
        room3.setFloor(2);
        room3.setPricePerNight(new BigDecimal("180.00"));
        entityManager.persist(room3);

        var room4 = new Room();
        room4.setRoomNumber("202");
        room4.setFloor(2);
        room4.setPricePerNight(new BigDecimal("200.00"));
        entityManager.persist(room4);

        var room5 = new Room();
        room5.setRoomNumber("301");
        room5.setFloor(3);
        room5.setPricePerNight(new BigDecimal("250.00"));
        entityManager.persist(room5);
    }

}
