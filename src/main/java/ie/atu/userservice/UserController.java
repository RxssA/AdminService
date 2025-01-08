package ie.atu.userservice;

import ie.atu.adminservice.UserWithBookingsDTO;
import ie.atu.adminservice.model.Booking;
import ie.atu.adminservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @GetMapping("/{id}")
    public ResponseEntity<UserWithBookingsDTO> getUserById(@PathVariable("id") int id) {
        User user = userService.getUserById(String.valueOf(id));
        List<Booking> bookings = bookingService.getBookingsByUserId(String.valueOf(id));

        UserWithBookingsDTO userWithBookings = new UserWithBookingsDTO(user, bookings);
        return ResponseEntity.ok(userWithBookings);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Delete user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
