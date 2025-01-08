package ie.atu.adminservice.controller;

import ie.atu.adminservice.model.Booking;
import ie.atu.adminservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

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
}
