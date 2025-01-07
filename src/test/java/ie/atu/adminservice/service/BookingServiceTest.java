package ie.atu.adminservice.service;

import ie.atu.adminservice.model.Booking;
import ie.atu.adminservice.repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    public BookingServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBookings_ShouldReturnBookings() {
        // Arrange
        Booking booking = new Booking();
        booking.setId("1");
        booking.setDetails("Booking for room 101");
        when(bookingRepository.findAll()).thenReturn(Collections.singletonList(booking));

        // Act
        List<?> bookings = bookingService.getAllBookings();

        // Assert
        assertNotNull(bookings);
        assertEquals(1, bookings.size());
        assertEquals("1", ((Booking) bookings.get(0)).getId());
    }

    @Test
    void deleteBooking_ShouldCallRepositoryDelete() {
        // Arrange
        String bookingId = "1";
        doNothing().when(bookingRepository).deleteById(bookingId);

        // Act
        bookingService.deleteBooking(bookingId);

        // Assert
        verify(bookingRepository, times(1)).deleteById(bookingId);
    }
}
