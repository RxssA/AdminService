package ie.atu.adminservice.controller;

import ie.atu.adminservice.UserServiceClient;
import ie.atu.adminservice.model.Booking;
import ie.atu.adminservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    private UserServiceClient userServiceClient;

    public BookingController(BookingService bookingService, UserServiceClient userServiceClient) {
        this.bookingService = bookingService;
        this.userServiceClient = userServiceClient;
    }

    @GetMapping
    public ResponseEntity<?> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.createBooking(booking));
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteBookingsByUserId(@PathVariable String userId) {
        bookingService.deleteBookingByUserId(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Booking> getUserDetails(@PathVariable String id) {
        return userServiceClient.getUserById(id);
    }
}
