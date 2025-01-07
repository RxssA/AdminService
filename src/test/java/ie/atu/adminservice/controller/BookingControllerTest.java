package ie.atu.adminservice.controller;

import ie.atu.adminservice.service.BookingService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    public BookingControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBookings_ShouldReturnResponse() {
        // Arrange
        when(bookingService.getAllBookings()).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<?>> response = bookingController.getAllBookings();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(0, response.getBody().size());
    }

    @Test
    void deleteBooking_ShouldReturnNoContent() {
        // Arrange
        String bookingId = "1";
        doNothing().when(bookingService).deleteBooking(bookingId);

        // Act
        ResponseEntity<Void> response = bookingController.deleteBooking(bookingId);

        // Assert
        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
        verify(bookingService, times(1)).deleteBooking(bookingId);
    }
}
