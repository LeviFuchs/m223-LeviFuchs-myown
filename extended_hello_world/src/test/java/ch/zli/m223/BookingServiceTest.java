package ch.zli.m223;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ch.zli.m223.model.Booking;
import ch.zli.m223.model.Room;
import ch.zli.m223.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import ch.zli.m223.service.BookingService;
import ch.zli.m223.service.RoomService;
import ch.zli.m223.service.UserService;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private UserService userService;

    @Mock
    private RoomService roomService;

    @Mock
    private TypedQuery<Booking> typedQuery;

    @InjectMocks
    private BookingService bookingService;

    private User testUser;
    private Room testRoom;
    private Booking testBooking;

    @BeforeEach
    void setUp() {
        // Test User erstellen
        testUser = new User();
        testUser.setId(1L);
        testUser.setFirstName("Test");
        testUser.setLastName("User");

        // Test Room erstellen
        testRoom = new Room();
        testRoom.setId(1L);
        testRoom.setRoomNumber("101");
        testRoom.setFloor(1);
        testRoom.setPricePerNight(new BigDecimal("120.00"));

        // Test Booking erstellen
        testBooking = new Booking();
        testBooking.setId(1L);
        testBooking.setUser(testUser);
        testBooking.setRoom(testRoom);
        testBooking.setCheckInDate(LocalDate.of(2024, 1, 1));
        testBooking.setCheckOutDate(LocalDate.of(2024, 1, 5));
    }

    @Test
    void getAllBookings_ShouldReturnAllBookings() {
        // Arrange
        Booking booking2 = new Booking();
        booking2.setId(2L);
        List<Booking> expectedBookings = Arrays.asList(testBooking, booking2);

        when(entityManager.createQuery("SELECT b FROM Booking b", Booking.class))
                .thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(expectedBookings);

        // Act
        List<Booking> result = bookingService.getAllBookings();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedBookings, result);
        verify(entityManager).createQuery("SELECT b FROM Booking b", Booking.class);
        verify(typedQuery).getResultList();
    }

    @Test
    void createBooking_ShouldCreateBooking_WhenValidDataProvided() {
        // Arrange
        Booking newBooking = new Booking();
        User user = new User();
        user.setId(1L);
        Room room = new Room();
        room.setId(1L);
        newBooking.setUser(user);
        newBooking.setRoom(room);
        newBooking.setCheckInDate(LocalDate.of(2024, 6, 1));
        newBooking.setCheckOutDate(LocalDate.of(2024, 6, 5));

        when(userService.getUserById(1L)).thenReturn(testUser);
        when(roomService.getRoomById(1L)).thenReturn(testRoom);

        // Act
        Booking result = bookingService.createBooking(newBooking);

        // Assert
        assertNotNull(result);
        assertEquals(testUser, result.getUser());
        assertEquals(testRoom, result.getRoom());
        verify(userService).getUserById(1L);
        verify(roomService).getRoomById(1L);
        verify(entityManager).persist(newBooking);
    }

    @Test
    void deleteBooking_ShouldReturnTrue_WhenBookingExists() {
        // Arrange
        when(entityManager.find(Booking.class, 1L)).thenReturn(testBooking);

        // Act
        boolean result = bookingService.deleteBooking(1L);

        // Assert
        assertTrue(result);
        verify(entityManager).find(Booking.class, 1L);
        verify(entityManager).remove(testBooking);
    }
}