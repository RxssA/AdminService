package ie.atu.adminservice.controller;

import ie.atu.adminservice.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBookings_ShouldReturnResponse() {
        when(bookingService.getAllBookings()).thenReturn(java.util.Collections.emptyList());

        ResponseEntity<?> response = bookingController.getAllBookings();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(bookingService, times(1)).getAllBookings();
    }

    @Test
    void deleteBooking_ShouldReturnNoContent() {
        String bookingId = "1";
        doNothing().when(bookingService).deleteBooking(bookingId);

        ResponseEntity<Void> response = bookingController.deleteBooking(bookingId);

        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
        verify(bookingService, times(1)).deleteBooking(bookingId);
    }
}
