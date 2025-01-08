package ie.atu.adminservice.service;

import ie.atu.adminservice.exception.ResourceNotFoundException;
import ie.atu.adminservice.model.Booking;
import ie.atu.adminservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(String id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
    }

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public void deleteBookingByUserId(String userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        if (bookings.isEmpty()) {
            throw new ResourceNotFoundException("No bookings found for userId: " + userId);
        }
        bookingRepository.deleteAll(bookings);
    }

    public List<Booking> getBookingsByUserId(String userId) {
        return bookingRepository.findByUserId(userId);
    }
}
