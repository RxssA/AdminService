package ie.atu.adminservice.service;

import ie.atu.adminservice.exception.ResourceNotFoundException;
import ie.atu.adminservice.model.Booking;
import ie.atu.adminservice.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBookings_ShouldReturnBookings() {
        when(bookingRepository.findAll()).thenReturn(java.util.Collections.emptyList());
        assertNotNull(bookingService.getAllBookings());
        verify(bookingRepository, times(1)).findAll();
    }

    @Test
    void deleteBooking_ExistingId_ShouldDeleteBooking() {
        String bookingId = "1";
        when(bookingRepository.existsById(bookingId)).thenReturn(true);

        bookingService.deleteBooking(bookingId);

        verify(bookingRepository, times(1)).deleteById(bookingId);
    }

    @Test
    void deleteBooking_NonExistingId_ShouldThrowException() {
        String bookingId = "999";
        when(bookingRepository.existsById(bookingId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> bookingService.deleteBooking(bookingId));
        verify(bookingRepository, never()).deleteById(bookingId);
    }
}
