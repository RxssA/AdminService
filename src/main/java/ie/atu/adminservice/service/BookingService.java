package ie.atu.adminservice.service;

import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ie.atu.adminservice.repository.BookingRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<?> getAllBookings() {
        return bookingRepository.findAll();
    }

    public void deleteBooking(String id) {
        bookingRepository.deleteById(id);
    }
}