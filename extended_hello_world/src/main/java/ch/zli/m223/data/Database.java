package ch.zli.m223.data;

import java.math.BigDecimal;
import java.time.LocalDate;

import ch.zli.m223.model.Booking;
import ch.zli.m223.model.Employee;
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
        createEmployees();
        createUsers();
        createRooms();
        createBookings();
    }

    public void createEmployees() {
        // Admin Employee
        var employee1 = new Employee();
        employee1.setEmail("levi.fuchs@gmail.com");
        employee1.setPassword("admin123");
        entityManager.persist(employee1);

        // Regular User Employees
        var employee2 = new Employee();
        employee2.setEmail("noah.burren@example.com");
        employee2.setPassword("password123");
        entityManager.persist(employee2);

        var employee3 = new Employee();
        employee3.setEmail("sarah.mueller@example.com");
        employee3.setPassword("password456");
        entityManager.persist(employee3);

        var employee4 = new Employee();
        employee4.setEmail("tim.schmidt@example.com");
        employee4.setPassword("password789");
        entityManager.persist(employee4);
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

    public void createBookings() {
        // User 1 (Levi) war vom 14.09.2022 bis 18.09.2022 in Zimmer 101
        var booking1 = new Booking();
        booking1.setUser(entityManager.find(User.class, 1L));
        booking1.setRoom(entityManager.find(Room.class, 1L));
        booking1.setCheckInDate(LocalDate.of(2022, 9, 14));
        booking1.setCheckOutDate(LocalDate.of(2022, 9, 18));
        entityManager.persist(booking1);

        // User 2 (Noah) war vom 20.10.2022 bis 25.10.2022 in Zimmer 201
        var booking2 = new Booking();
        booking2.setUser(entityManager.find(User.class, 2L));
        booking2.setRoom(entityManager.find(Room.class, 3L));
        booking2.setCheckInDate(LocalDate.of(2022, 10, 20));
        booking2.setCheckOutDate(LocalDate.of(2022, 10, 25));
        entityManager.persist(booking2);

        // User 1 (Levi) war vom 01.12.2022 bis 05.12.2022 in Zimmer 301
        var booking3 = new Booking();
        booking3.setUser(entityManager.find(User.class, 1L));
        booking3.setRoom(entityManager.find(Room.class, 5L));
        booking3.setCheckInDate(LocalDate.of(2022, 12, 1));
        booking3.setCheckOutDate(LocalDate.of(2022, 12, 5));
        entityManager.persist(booking3);
    }

}