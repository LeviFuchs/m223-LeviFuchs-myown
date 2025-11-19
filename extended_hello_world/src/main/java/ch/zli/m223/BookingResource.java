package ch.zli.m223;

import java.util.List;

import ch.zli.m223.model.Booking;
import ch.zli.m223.service.BookingService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/bookings")
public class BookingResource {

    @Inject
    BookingService bookingService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookingById(@PathParam("id") Long id) {
        Booking booking = bookingService.getBookingById(id);
        if (booking != null) {
            return Response.ok(booking).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Booking> getBookingsByUser(@PathParam("userId") Long userId) {
        return bookingService.getBookingsByUser(userId);
    }

    @GET
    @Path("/room/{roomId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Booking> getBookingsByRoom(@PathParam("roomId") Long roomId) {
        return bookingService.getBookingsByRoom(roomId);
    }

    @POST
    @RolesAllowed("admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBooking(Booking booking) {
        Booking createdBooking = bookingService.createBooking(booking);
        return Response.status(Response.Status.CREATED).entity(createdBooking).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBooking(@PathParam("id") Long id, Booking booking) {
        Booking updatedBooking = bookingService.updateBooking(id, booking);
        if (updatedBooking != null) {
            return Response.ok(updatedBooking).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBooking(@PathParam("id") Long id) {
        boolean deleted = bookingService.deleteBooking(id);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}