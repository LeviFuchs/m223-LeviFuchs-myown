package ch.zli.m223.service;

import java.util.List;
import ch.zli.m223.model.User;

import ch.zli.m223.model.Booking;
import ch.zli.m223.model.Room;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class BookingService {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    UserService userService;
    @Inject
    RoomService roomService;

    public List<Booking> getAllBookings() {
        return entityManager
                .createQuery("SELECT b FROM Booking b", Booking.class)
                .getResultList();
    }

    public Booking getBookingById(Long id) {
        return entityManager.find(Booking.class, id);
    }

    public List<Booking> getBookingsByUser(Long userId) {
        return entityManager
                .createQuery("SELECT b FROM Booking b WHERE b.user.id = :userId", Booking.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public List<Booking> getBookingsByRoom(Long roomId) {
        return entityManager
                .createQuery("SELECT b FROM Booking b WHERE b.room.id = :roomId", Booking.class)
                .setParameter("roomId", roomId)
                .getResultList();
    }

    @Transactional
    public Booking createBooking(Booking booking) {
        // fetch user
        User user = userService.getUserById(booking.getUser().getId());
        booking.setUser(user);
        // fetch room
        Room room = roomService.getRoomById(booking.getRoom().getId());
        booking.setRoom(room);

        entityManager.persist(booking);
        return booking;
    }

    @Transactional
    public Booking updateBooking(Long id, Booking updatedBooking) {
        Booking existingBooking = entityManager.find(Booking.class, id);

        if (existingBooking != null) {
            existingBooking.setUser(updatedBooking.getUser());
            existingBooking.setRoom(updatedBooking.getRoom());
            existingBooking.setCheckInDate(updatedBooking.getCheckInDate());
            existingBooking.setCheckOutDate(updatedBooking.getCheckOutDate());
            return entityManager.merge(existingBooking);
        }

        return null;
    }

    @Transactional
    public boolean deleteBooking(Long id) {
        Booking booking = entityManager.find(Booking.class, id);

        if (booking != null) {
            entityManager.remove(booking);
            return true;
        }

        return false;
    }
}