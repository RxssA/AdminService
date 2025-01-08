package ie.atu.adminservice.service;

import ie.atu.adminservice.repository.BookingRepository;
import ie.atu.adminservice.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<?> getAllBookings() {
        return bookingRepository.findAll();
    }

    public void deleteBooking(String id) {
        if (!bookingRepository.existsById(id)) {
            throw new ResourceNotFoundException("Booking not found with id: " + id);
        }
        bookingRepository.deleteById(id);
    }
}
