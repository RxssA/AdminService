package ie.atu.adminservice;

import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


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