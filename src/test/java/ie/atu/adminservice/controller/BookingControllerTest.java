package ie.atu.adminservice.controller;

import ie.atu.adminservice.UserServiceClient;
import ie.atu.adminservice.model.Booking;
import ie.atu.adminservice.service.BookingService;
import org.bson.types.ObjectId;  // Import ObjectId from MongoDB
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BookingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookingService bookingService;

    @Mock
    private UserServiceClient userServiceClient;

    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Ensures mocks are initialized
        mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
    }

    @Test
    void getAllBookings_ShouldReturnBookings() throws Exception {
        // Mocking the service call to return an empty list
        when(bookingService.getAllBookings()).thenReturn(Collections.emptyList());

        // Perform GET request and verify response
        mockMvc.perform(get("/api/bookings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray()) // Check it's an array
                .andExpect(jsonPath("$").isEmpty()); // Check it's empty

        // Verify that the service method was called once
        verify(bookingService, times(1)).getAllBookings();
    }

    @Test
    void deleteBookingsByUserId_ShouldReturnNoContent() throws Exception {
        // Perform DELETE request and verify response
        mockMvc.perform(delete("/api/bookings/user/123"))
                .andExpect(status().isNoContent()); // Expect No Content status

        // Verify that the service method was called once
        verify(bookingService, times(1)).deleteBookingByUserId("123");
    }
}
